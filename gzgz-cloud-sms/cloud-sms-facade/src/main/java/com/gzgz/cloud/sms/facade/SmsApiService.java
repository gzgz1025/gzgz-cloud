package com.gzgz.cloud.sms.facade;

import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.facade.dto.SmsRequestDTO;
import com.gzgz.cloud.sms.facade.dto.SmsTemplateRequestDTO;

/**
 * @ClassName: SmsPlatformService
 * @Description:短信接口
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:04
 * @Version: 1.0
 */
public interface SmsApiService {
    /**
     * 自定义内容手机号发送
     *
     * @param smsRequest
     * @return
     */
    Result send(SmsRequestDTO smsRequest);


    /**
     * 自定义内容异步发送
     *
     * @param smsRequest
     * @return
     */
    void sendAsync(SmsRequestDTO smsRequest);


    /**
     * 模板内容手机号发送
     *
     * @param smsRequest
     * @return
     */
    Result sendTemplate(SmsTemplateRequestDTO smsRequest);


    /**
     * 模板内容异步发送
     *
     * @param smsRequest
     * @return
     */
    void sendTemplateAsync(SmsTemplateRequestDTO smsRequest);


}
