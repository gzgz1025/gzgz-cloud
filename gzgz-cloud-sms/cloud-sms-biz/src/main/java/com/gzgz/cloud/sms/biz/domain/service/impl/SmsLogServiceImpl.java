package com.gzgz.cloud.sms.biz.domain.service.impl;

import com.gzgz.cloud.mbg.mapper.SmsLogMapper;
import com.gzgz.cloud.sms.biz.domain.service.SmsLogService;
import com.gzgz.cloud.sms.dal.model.SmsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName: SmsLogServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/24 11:54
 * @Version: 1.0
 */
@Service
@Slf4j
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogMapper logMapper;

    @Override
    public void saveLog(List<SmsLog> smsLogs) {
        logMapper.insertList(smsLogs);
    }
}
