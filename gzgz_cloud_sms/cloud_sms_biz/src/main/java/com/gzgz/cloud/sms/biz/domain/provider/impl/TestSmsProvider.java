package com.gzgz.cloud.sms.biz.domain.provider.impl;

import com.zds.boot.common.facade.ResultBase;
import com.zds.boot.common.utils.Ids;
import com.zds.channel.biz.smsplatform.domain.factory.LogFactory;
import com.zds.channel.biz.smsplatform.domain.provider.AbstractSMSProvider;
import com.zds.channel.dal.model.SmsChannel;
import com.zds.cloud.starter.event.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("TestSmsProvider")
public class TestSmsProvider extends AbstractSMSProvider {

    @Autowired
    private EventBus eventBus;

    @Override
    public ResultBase doSend(List<String> mobileNos, String content, SmsChannel channel, String systemCode, String businessType) {
        log.info("测试环境不真实发送短信，直接返回成功：mobileNos={}, content={}, channel={}, systemCode={}, businessType={}",
                mobileNos, content, channel, systemCode, businessType);
        eventBus.publishAsync(LogFactory.createSMSSendLog( Ids.gidLong(), systemCode, businessType, channel.getId(), mobileNos, "0", "1", content));
        return ResultBase.success("测试环境短信模拟发送成功");
    }
}
