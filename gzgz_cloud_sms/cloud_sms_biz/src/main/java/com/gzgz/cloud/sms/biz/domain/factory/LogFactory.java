package com.gzgz.cloud.sms.biz.domain.factory;


import com.gzgz.cloud.sms.biz.domain.event.SaveSmsLogEvent;

import java.util.Date;
import java.util.List;

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
     *
     * @param segId
     * @param systemCode
     * @param businessType
     * @param channelId
     * @param mobileNos
     * @param sendStatus
     * @param sendResult
     * @param sendContent
     * @return
     */
    public static SaveSmsLogEvent createSMSSendLog(Long segId, String systemCode, String businessType, Long channelId, List<String> mobileNos, String sendStatus, String sendResult, String sendContent) {
        SaveSmsLogEvent event = new SaveSmsLogEvent();
        event.setApiRequestNo(segId);
        event.setSystemCode(systemCode);
        event.setBusinessType(businessType);
        event.setChannelId(channelId);
        event.setMobileNos(mobileNos);
        event.setSendStatus(sendStatus);
        event.setSendResult(sendResult);
        event.setSendContent(sendContent);
        event.setCreateTime(new Date());
        return event;
    }
}
