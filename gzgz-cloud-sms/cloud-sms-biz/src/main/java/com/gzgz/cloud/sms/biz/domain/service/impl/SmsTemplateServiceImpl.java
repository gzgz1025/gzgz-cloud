package com.gzgz.cloud.sms.biz.domain.service.impl;

import com.gzgz.cloud.sms.dal.mapper.SmsTemplateMapper;
import com.gzgz.cloud.sms.biz.domain.service.SmsTemplateService;
import com.gzgz.cloud.sms.dal.model.SmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SmsTemplateServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/22 16:20
 * @Version: 1.0
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
    @Autowired
    private SmsTemplateMapper templateMapper;

    @Override
    public SmsTemplate getSmsTemplateByCode(String templateCode) {
        SmsTemplate template = new SmsTemplate();
        template.setTemplateCode(templateCode);
        template.setIsValid(Boolean.TRUE);
        return templateMapper.selectOne(template);
    }
}
