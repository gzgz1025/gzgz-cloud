package com.gzgz.cloud.sms.dal.mapper;

import com.gzgz.cloud.sms.dal.model.SmsLog;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SmsLogMapper extends Mapper<SmsLog>, InsertListMapper<SmsLog> {
}