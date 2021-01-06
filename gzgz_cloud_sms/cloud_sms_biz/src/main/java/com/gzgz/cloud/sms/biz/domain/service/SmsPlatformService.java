package com.gzgz.cloud.sms.biz.domain.service;


import com.gzgz.cloud.common.asserts.Result;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: SmsService
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/21 17:20
 * @Version: 1.0
 */
public interface SmsPlatformService {

    Result send(List<String> mobileNos, String content, String systemCode, String businessType);

    void sendAsync(List<String> mobileNos, String content, String systemCode, String businessType);

    Result send(List<String> mobileNos, String templateCode, Map<String, String> templateParam, String systemCode, String businessType);

    void sendAsync(List<String> mobileNos, String templateCode, Map<String, String> templateParam, String systemCode, String businessType);

}
