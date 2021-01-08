package com.gzgz.cloud.sms.biz.domain.service;


import com.gzgz.cloud.sms.dal.model.SmsChannel;

/**
 * @ClassName: SmsChannelService
 * @Description:短信渠道
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:19
 * @Version: 1.0
 */
public interface SmsChannelService {
    /**
     * 通过ID查找对应得渠道
     * @param id
     * @return
     */
    SmsChannel getSmsChannelByID(Long id);
}
