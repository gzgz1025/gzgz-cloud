package com.gzgz.cloud.sms.biz.domain.event;

import com.gzgz.cloud.sms.biz.domain.service.SmsLogService;
import com.gzgz.cloud.sms.dal.model.SmsLog;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: SaveSmsLogEventHandler
 * @Description:保存短信日志
 * @Author: pzl
 * @CreateDate: 2020/12/31 14:05
 * @Version: 1.0
 */
//@EventHandler
@Slf4j
public class SaveSmsLogEventHandler {
    @Autowired
    private SmsLogService smsLogService;
    private static final Integer MAX_INSERT = 500;

    @Handler(delivery = Invoke.Asynchronously)
    public void handle(SaveSmsLogEvent event) {
        //分割次数
        int limit = (event.getMobileNos().size() + MAX_INSERT - 1) / MAX_INSERT;

        Stream.iterate(0, n -> n + 1).limit(limit).forEach(a -> {
            List<String> mobileNoList = event.getMobileNos().stream().skip(a * MAX_INSERT).limit(MAX_INSERT).collect(Collectors.toList());
            List<SmsLog> smsLogs=new ArrayList<>();
            mobileNoList.forEach(mobile -> {
                SmsLog smsLog = new SmsLog();
                smsLog.setApiRequestNo(event.getApiRequestNo());
                smsLog.setChannelId(event.getChannelId());
                smsLog.setSystemCode(event.getSystemCode());
                smsLog.setBusinessType(event.getBusinessType());
                smsLog.setSendStatus(event.getSendStatus());
                smsLog.setSendResult(event.getSendResult());
                smsLog.setSendContent(event.getSendContent());
                smsLog.setCreateTime(event.getCreateTime());
                smsLog.setMobile(mobile);
                smsLogs.add(smsLog);
            });
            try {
                smsLogService.saveLog(smsLogs);
                log.info("短信日志保存成功");
            } catch (Exception e) {
                log.warn("短信日志保存失败：", e);
            }
        });
    }
}
