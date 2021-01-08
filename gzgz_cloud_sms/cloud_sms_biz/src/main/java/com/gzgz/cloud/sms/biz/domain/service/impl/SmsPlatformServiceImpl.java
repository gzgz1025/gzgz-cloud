package com.gzgz.cloud.sms.biz.domain.service.impl;

import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.common.exception.AssertsException;
import com.gzgz.cloud.sms.biz.domain.provider.SmsProvider;
import com.gzgz.cloud.sms.biz.domain.service.SmsChannelService;
import com.gzgz.cloud.sms.biz.domain.service.SmsPlatformService;
import com.gzgz.cloud.sms.biz.domain.service.SmsRouteService;
import com.gzgz.cloud.sms.biz.domain.service.SmsTemplateService;
import com.gzgz.cloud.sms.dal.model.SmsChannel;
import com.gzgz.cloud.sms.dal.model.SmsRoute;
import com.gzgz.cloud.sms.dal.model.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/**
 * @ClassName: SmsServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/21 17:56
 * @Version: 1.0
 */
@Service
@Slf4j
public class SmsPlatformServiceImpl implements SmsPlatformService {
    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private SmsRouteService routeService;
    @Autowired
    private SmsChannelService channelService;
    @Autowired
    private SmsTemplateService templateService;


    @Override
    public Result send(List<String> mobileNos, String content, String systemCode, String businessType) {
        //获取渠道
        SmsChannel channel = getChannel(systemCode, businessType);
        //获取渠道对应具体供应商实现方法
        SmsProvider smsProvider = routeService.getSmsProvider(channel.getProviderCode());
        //发送短信
        return smsProvider.send(mobileNos, content, channel, systemCode, businessType);
    }

    @Override
    public void sendAsync(List<String> mobileNos, String content, String systemCode, String businessType) {
        executor.execute(() -> {
            Result resultBase = send(mobileNos, content, systemCode, businessType);
            log.info("异步发送短信,发送代码：{}，发送结果：{}", resultBase.getCode(), resultBase.getMessage());
        });
    }

    @Override
    public Result send(List<String> mobileNos, String templateCode, Map<String, String> templateParam, String systemCode, String businessType) {
        //获取渠道 如有模板参数则先校验
        SmsChannel channel = getChannel(systemCode, businessType);
        //获取模板
        String content = getContent(templateCode, templateParam);
        //获取渠道对应具体供应商实现方法
        SmsProvider smsProvider = routeService.getSmsProvider(channel.getProviderCode());
        //发送短信
        return smsProvider.send(mobileNos, content, channel, systemCode, businessType);
    }

    @Override
    public void sendAsync(List<String> mobileNos, String templateCode, Map<String, String> templateParam, String systemCode, String businessType) {
        executor.execute(() -> {
            Result resultBase = send(mobileNos, templateCode, templateParam, systemCode, businessType);
            log.info("异步发送模板短信，发送代码：{}，发送结果：{}", resultBase.getCode(), resultBase.getMessage());
        });
    }


    /**
     * 获取渠道内容
     *
     * @param systemCode
     * @param businessType
     * @return
     */
    private SmsChannel getChannel(String systemCode, String businessType) {
        //查询路由
        SmsRoute smsRoute = routeService.getSmsRoute(systemCode, businessType);
        AssertsException.throwIt(smsRoute==null,"路由无效或无相关路由配置");
        //获取渠道
        SmsChannel channel = channelService.getSmsChannelByID(smsRoute.getChannelId());
        AssertsException.throwIt(channel==null,"渠道无效或无相关渠道配置");
        return channel;
    }

    /**
     * 获取模板内容
     *
     * @param templateCode
     * @param templateParam
     * @return
     */
    private String getContent(String templateCode, Map<String, String> templateParam) {
        //获取模板
        SmsTemplate smsTemplate = templateService.getSmsTemplateByCode(templateCode);
        AssertsException.throwIt(smsTemplate==null,"模板无效或无相关模板配置");
        //数据库中的参数
        String resultParam = smsTemplate.getTemplateParam();
        String content = smsTemplate.getTemplateContent();
        //验证模板参数
        if (ObjectUtils.isNotEmpty(templateParam)) {
            //验证参数
            validateTemplateParam(resultParam, templateParam);
            //替换模板参数
            content = replaceTemplateContent(content, resultParam, templateParam);
        }
        return content;
    }

    /**
     * 验证参数
     *
     * @param templateParams
     * @param templateParam
     */
    private void validateTemplateParam(String templateParams, Map<String, String> templateParam) {
        AssertsException.throwIt(!Pattern.matches("^[a-zA-Z0-9,\u4e00-\u9fa5]+$", templateParams),"数据库中多个模板参数必须以英文逗号隔开");
        AssertsException.throwIt(templateParams.split(",").length != templateParam.size(),"传入模板参数个数不对");
        Stream.of(templateParams.split(",")).forEach(s -> {
            AssertsException.throwIt(StringUtils.isEmpty(templateParam.get(s)),"传入参数不对");
        });
    }

    /**
     * 替换模板参数
     *
     * @param content
     * @param resultParam
     * @param templateParam
     * @return
     */
    private String replaceTemplateContent(String content, String resultParam, Map<String, String> templateParam) {
        AtomicReference<String> str = new AtomicReference<>(content);
        Stream.of(resultParam.split(",")).forEach((s -> {
            str.set(StringUtils.replace(str.get(), joinSpecialChar(s), templateParam.get(s)));
        }));
        return str.get();
    }

    private String joinSpecialChar(String str) {
        return "${" + str + "}";
    }
}
