package com.gzgz.cloud.sms.biz.domain.service;


import com.gzgz.cloud.sms.dal.model.SmsLog;

import java.util.List;

/**
 * @ClassName: SmsLogService
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/24 11:53
 * @Version: 1.0
 */
public interface SmsLogService {
    void saveLog(List<SmsLog> smsLogs);
}
