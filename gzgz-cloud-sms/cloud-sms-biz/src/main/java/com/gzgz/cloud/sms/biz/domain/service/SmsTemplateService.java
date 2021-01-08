package com.gzgz.cloud.sms.biz.domain.service;


import com.gzgz.cloud.sms.dal.model.SmsTemplate;

/**
 * @ClassName: SmsTemplateService
 * @Description:短信模板
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:25
 * @Version: 1.0
 */
public interface SmsTemplateService {
    /**
     * 通过模板代码查找短信模板
     * @param templateCode
     * @return
     */
    SmsTemplate getSmsTemplateByCode(String templateCode);
}
