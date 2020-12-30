package com.gzgz.cloud.authority.common.log;

import com.gzgz.cloud.module.SmsLog;

import java.util.Date;

/**
 * @ClassName: LogFactory
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/29 14:36
 * @Version: 1.0
 */
public class LogFactory {

    /**
     * 创建短信发送日志
     * @param id
     * @param segid
     * @param systemCode
     * @param businessType
     * @param channelId
     * @param mobileNo
     * @param sendStatus
     * @param sendResult
     * @param sendContent
     * @return
     */
    public static SmsLog createSMSSendLog(Long id, Long segid, String systemCode, String businessType, Long channelId, String mobileNo, String sendStatus, String sendResult, String sendContent ){
        SmsLog smsLog=new SmsLog();
        smsLog.setId(id);
        smsLog.setApiRequestNo(segid);
        smsLog.setSystemCode(systemCode);
        smsLog.setBusinessType(businessType);
        smsLog.setChannelId(channelId);
        smsLog.setMobile(mobileNo);
        smsLog.setSendStatus(sendStatus);
        smsLog.setSendResult(sendResult);
        smsLog.setSendContent(sendContent);
        smsLog.setCreateTime(new Date());
        return smsLog;
    }
}
