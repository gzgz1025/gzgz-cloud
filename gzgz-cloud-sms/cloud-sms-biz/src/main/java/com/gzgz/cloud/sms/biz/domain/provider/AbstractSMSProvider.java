package com.gzgz.cloud.sms.biz.domain.provider;


import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.dal.model.SmsChannel;

import java.util.List;

/**
 * @ClassName: AbstractSMSFactory
 * @Description:抽象工厂类
 * @Author: pzl
 * @CreateDate: 2020/12/22 15:56
 * @Version: 1.0
 */
public abstract class AbstractSMSProvider implements SmsProvider {

    @Override
    public Result send(List<String> mobileNos, String content, SmsChannel channel, String systemCode, String businessType) {
        return doSend(mobileNos, content, channel, systemCode, businessType);
    }

    public abstract Result doSend(List<String> mobileNos, String content, SmsChannel channel, String systemCode, String businessType);

}
