package com.gzgz.cloud.sms.biz.domain.provider.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.biz.domain.factory.LogFactory;
import com.gzgz.cloud.sms.biz.domain.provider.AbstractSMSProvider;
import com.gzgz.cloud.sms.dal.model.SmsChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: BaiWuProvider
 * @Description:百悟短信
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:56
 * @Version: 1.0
 */
@Service("BaiWuSmsProvider")
@Slf4j
public class BaiWuSmsProvider extends AbstractSMSProvider {

    private static final int SUCCESS_CODE = 0;
    private List<String> tagNames = Arrays.asList("error", "message");
    /*@Autowired
    private EventBus eventBus;*/

    @Override
    public Result doSend(List<String> mobileNos, String content, SmsChannel channel, String systemCode, String businessType) {
        String mobileNo = Joiner.on(",").join(mobileNos);
        //获取渠道配置信息
        JSONObject json = JSONObject.parseObject(channel.getChannelConfig());
        log.info("厂商:{},配置：{}", channel.getProviderName(), channel.getChannelConfig());
        //流水号
        String serId = IdUtil.randomUUID();
        //访问接口账户id
        String cropId = json.get("corp_id") == null ? "" : json.get("corp_id").toString();
        //访问接口账户密码
        String corpPwd = json.get("corp_pwd") == null ? "" : json.get("corp_pwd").toString();
        //业务代码
        String corpService = json.get("corp_service") == null ? "" : json.get("corp_service").toString();
        //超时设置
        int timeout = json.get("timeout") == null ? 10000 : Integer.parseInt(json.get("timeout").toString());
        //发送地址
        String sendUrl=json.get("send_url") == null ? "" : json.get("send_url").toString();

        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("corp_id", cropId);
        dataMap.put("corp_pwd", corpPwd);
        dataMap.put("corp_service", corpService);
        dataMap.put("mobile", mobileNo);
        //用户自行分配扩展号
        dataMap.put("ext", "8888");
        //用户发送短信时自己定义的短信id
        dataMap.put("corp_msg_id", serId);
        dataMap.put("msg_content", content);

        try {
            HttpRequest httpRequest = HttpRequest.post(sendUrl).form(dataMap).connectTimeout(timeout / 2)
                    .readTimeout(timeout / 2);
            if (httpRequest.code() != 200) {
                throw new RuntimeException("http StatusCode=" + httpRequest.code());
            }
            String httpBody = httpRequest.body();
            log.info("发送短信请求返回代码：" + httpBody);
            Result resultBase = unmashall(httpBody, tagNames);
            log.info("发送短信完成 {mobile:{},content:{},result:{}}", mobileNo, content, resultBase);

            //日志记录
            //eventBus.publishAsync(LogFactory.createSMSSendLog( Long.valueOf(serId), systemCode, businessType, channel.getId(), mobileNos, resultBase.getCode(), resultBase.getMessage(), content));
            return resultBase;
        } catch (Exception e) {
            log.warn("发送短信失败 号码:{},内容:{}", mobileNo, content, e);
            throw new RuntimeException("短信请求失败" + e.getMessage());
        }
    }

    protected Result unmashall(String body, List<String> tagNames) {
        Result resultBase = new Result();
        String errorCode = body.indexOf("#") == -1 ? body : body.substring(0, body.indexOf("#"));
        String message = "";
        if (SUCCESS_CODE == Integer.parseInt(errorCode)) {
            resultBase.setCode(errorCode);
            resultBase.setMessage("短信发送成功");
            resultBase.setSuccess(Boolean.TRUE);
            return resultBase;
        } else {
            if (Strings.isNullOrEmpty(message)) {
                message = codeMapping.get(errorCode);
                if (Strings.isNullOrEmpty(message)) {
                    message = "发送短信失败," + message;
                }
            }
            log.info("发送信息失败，错误代码：{} ,错误信息：{}", errorCode, message);
            resultBase.setCode(errorCode);
            resultBase.setMessage(message);
            resultBase.setSuccess(Boolean.FALSE);
            return resultBase;
        }
    }

    private static Map<String, String> codeMapping = new HashMap<>();

    static {
        codeMapping.put("100", "余额不足");
        codeMapping.put("101", "账号关闭");
        codeMapping.put("102", "短信内容超过1000字（包括1000字）或为空");
        codeMapping.put("103", "手机号码超过200个或合法手机号码为空或者与通道类型不匹配");
        codeMapping.put("104", "corp_msg_id超过50个字符或没有传corp_msg_id字段");
        codeMapping.put("106", "用户名不存在");
        codeMapping.put("107", "密码错误");
        codeMapping.put("108", "指定访问ip错误");
        codeMapping.put("109", "业务代码不存在或者通道关闭");
        codeMapping.put("110", "扩展号不合法");
        codeMapping.put("9", "访问地址不存在");
    }
}
