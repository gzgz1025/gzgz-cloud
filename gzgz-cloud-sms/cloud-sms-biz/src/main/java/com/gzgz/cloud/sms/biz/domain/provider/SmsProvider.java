package com.gzgz.cloud.sms.biz.domain.provider;

import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.dal.model.SmsChannel;

import java.util.List;

/**
 * @ClassName: SMSFactory
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/22 15:56
 * @Version: 1.0
 */
public interface SmsProvider {

    /**
     * 发送短信
     *
     * @param mobileNos
     * @param content
     */
    Result send(List<String> mobileNos, String content, SmsChannel channel, String systemCode, String businessType);

}
