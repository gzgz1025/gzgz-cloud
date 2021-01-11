package com.gzgz.cloud.sms.biz.domain.service.impl;

import com.gzgz.cloud.sms.dal.mapper.SmsChannelMapper;
import com.gzgz.cloud.sms.biz.domain.service.SmsChannelService;
import com.gzgz.cloud.sms.dal.model.SmsChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SmsChannelServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/22 11:02
 * @Version: 1.0
 */
@Service
public class SmsChannelServiceImpl implements SmsChannelService {
    @Autowired
    private SmsChannelMapper channelMapper;

    @Override
    public SmsChannel getSmsChannelByID(Long id) {
        SmsChannel channel = new SmsChannel();
        channel.setId(id);
        channel.setIsValid(Boolean.TRUE);
        return channelMapper.selectOne(channel);
    }
}
