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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EmayProvider
 * @Description:亿美短信
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:14
 * @Version: 1.0
 */
@Slf4j
@Service("EmaySmsProvider")
public class EmaySmsProvider extends AbstractSMSProvider {

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
        String segId = IdUtil.randomUUID();
        //序列号
        String sn = json.get("cdkey") == null ? "" : json.get("cdkey").toString();
        //密码
        String passwd = json.get("password") == null ? "" : json.get("password").toString();
        //签名
        String sign = json.get("sign") == null ? "" : json.get("sign").toString();
        //超时设置
        int timeout = json.get("timeout") == null ? 10000 : Integer.parseInt(json.get("timeout").toString());
        //发送地址
        String sendUrl = json.get("sendUrl") == null ? "" : json.get("sendUrl").toString();

        content = getContent(sign, content);

        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("cdkey", sn);
        dataMap.put("password", passwd);
        dataMap.put("seqid", segId);
        dataMap.put("phone", mobileNo);
        dataMap.put("addserial", "");
        dataMap.put("smspriority", "1");
        dataMap.put("message", content);

        try {
            HttpRequest httpRequest = HttpRequest.post(sendUrl).form(dataMap).connectTimeout(timeout / 2)
                    .readTimeout(timeout / 2);
            if (httpRequest.code() != 200) {
                throw new RuntimeException("http StatusCode=" + httpRequest.code());
            }
            String httpBody = httpRequest.body();
            log.info("发送短信请求返回body：" + httpBody);
            Result resultBase = unmashall(httpBody, tagNames);
            log.info("发送短信完成 {mobile:{},content:{},result:{}}", mobileNo, content, resultBase);
            //记录日志
            //eventBus.publishAsync(LogFactory.createSMSSendLog( Long.valueOf(segId), systemCode, businessType, channel.getId(), mobileNos, resultBase.getCode(), resultBase.getMessage(), content));
            return resultBase;
        } catch (Exception e) {
            log.warn("发送短信失败 号码:{},内容:{}", mobileNo, content, e);
            throw new RuntimeException("短信请求失败" + e.getMessage());
        }
    }

    protected Result unmashall(String body, List<String> tagNames) {
        Result resultBase = new Result();
        Map<String, String> response = convertXML(StringUtils.trim(body), tagNames);
        if (response != null) {
            String errorCode = response.get("error");
            String message = response.get("message");
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
//                throw new BusinessException(message, errorCode);
                log.info("发送信息失败，错误代码：{},错误信息：{}", errorCode, message);
                resultBase.setCode(errorCode);
                resultBase.setMessage(message);
                resultBase.setSuccess(Boolean.FALSE);
                return resultBase;
            }
        } else {
            throw new RuntimeException("response null");
        }
    }

    private Map<String, String> convertXML(String xml, List<String> tagNames) {
        Map<String, String> map = Maps.newHashMap();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            for (String tagName : tagNames) {
                String tagText = doc.getElementsByTagName(tagName).item(0).getTextContent();
                map.put(tagName, tagText);
            }
        } catch (Exception e) {
            log.error("解析XML{}失败{}", xml, e.getMessage());
        }
        return map;
    }

    private String getContent(Object sign, String content) {
        if (sign == null) {
            sign = "";
        } else {
            if (!sign.toString().startsWith("【")) {
                sign = "【" + sign.toString().trim() + "】";
            }
        }
        return sign + content;
    }

    private static Map<String, String> codeMapping = new HashMap<>();

    static {
        codeMapping.put("-1", "系统异常");
        codeMapping.put("-101", "命令不被支持");
        codeMapping.put("-102", "RegistryTransInfo删除信息失败");
        codeMapping.put("-103", "RegistryInfo更新信息失败");
        codeMapping.put("-104", "请求超过限制");
        codeMapping.put("-111", "企业注册失败");
        codeMapping.put("-117", "发送短信失败");
        codeMapping.put("-118", "接收MO失败");
        codeMapping.put("-119", "接收Report失败");
        codeMapping.put("-120", "修改密码失败");
        codeMapping.put("-122", "号码注销激活失败");
        codeMapping.put("-110", "号码注册激活失败");
        codeMapping.put("-123", "查询单价失败");
        codeMapping.put("-124", "查询余额失败");
        codeMapping.put("-125", "设置MO转发失败");
        codeMapping.put("-126", "路由信息失败");
        codeMapping.put("-127", "计费失败0余额");
        codeMapping.put("-128", "计费失败余额不足");
        codeMapping.put("-1100", "序列号错误,序列号不存在内存中,或尝试攻击的用户");
        codeMapping.put("-1103", "序列号Key错误");
        codeMapping.put("-1102", "序列号密码错误");
        codeMapping.put("-1104", "路由失败，请联系系统管理员");
        codeMapping.put("-1105", "注册号状态异常, 未用 1");
        codeMapping.put("-1107", "注册号状态异常, 停用 3");
        codeMapping.put("-1108", "注册号状态异常, 停止 5");
        codeMapping.put("-113", "充值失败");
        codeMapping.put("-1131", "充值卡无效");
        codeMapping.put("-1132", "充值密码无效");
        codeMapping.put("-1133", "充值卡绑定异常");
        codeMapping.put("-1134", "充值状态无效");
        codeMapping.put("-1135", "充值金额无效");
        codeMapping.put("-190", "数据操作失败");

        codeMapping.put("-1901", "数据库插入操作失败");
        codeMapping.put("-1902", "数据库更新操作失败");
        codeMapping.put("-1903", "数据库删除操作失败");
    }
}
