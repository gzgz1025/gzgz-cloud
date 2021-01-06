package com.gzgz.cloud.sms.biz.appservice;

import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.biz.domain.service.SmsPlatformService;
import com.gzgz.cloud.sms.facade.SmsApiService;
import com.gzgz.cloud.sms.facade.dto.SmsRequestDTO;
import com.gzgz.cloud.sms.facade.dto.SmsTemplateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName: SmsAppServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/21 17:31
 * @Version: 1.0
 */
@Service
public class SmsAppServiceImpl implements SmsApiService {
    @Autowired
    private SmsPlatformService smsPlatformService;

    @Override
    @Transactional
    public Result send(SmsRequestDTO dto) {
        return smsPlatformService.send(dto.getMobileNos(),dto.getContent(),dto.getSystemCode(),dto.getBusinessType());
    }

    @Override
    @Transactional
    public void sendAsync(SmsRequestDTO dto) {
        smsPlatformService.sendAsync(dto.getMobileNos(),dto.getContent(),dto.getSystemCode(),dto.getBusinessType());
    }


    @Override
    @Transactional
    public Result sendTemplate(SmsTemplateRequestDTO dto) {
        return smsPlatformService.send(dto.getMobileNos(),dto.getTemplateCode(),dto.getTemplateParam(),dto.getSystemCode(),dto.getBusinessType());
    }


    @Override
    @Transactional
    public void sendTemplateAsync(SmsTemplateRequestDTO dto) {
        smsPlatformService.sendAsync(dto.getMobileNos(),dto.getTemplateCode(),dto.getTemplateParam(),dto.getSystemCode(),dto.getBusinessType());
    }
}
