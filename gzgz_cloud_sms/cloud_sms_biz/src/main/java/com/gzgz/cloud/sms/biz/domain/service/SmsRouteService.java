package com.gzgz.cloud.sms.biz.domain.service;


import com.gzgz.cloud.sms.biz.domain.provider.SmsProvider;
import com.gzgz.cloud.sms.dal.model.SmsRoute;

/**
 * @ClassName: SmsRouteService
 * @Description:短信路由
 * @Author: pzl
 * @CreateDate: 2020/12/21 15:21
 * @Version: 1.0
 */
public interface SmsRouteService {
    /**
     * 通过系统代码和业务类型查找路由
     * @param systemCode
     * @param businessType
     * @return
     */
    SmsRoute getSmsRoute(String systemCode, String businessType);

    /**
     * 具体厂商实现方法
     * @param providerCode
     * @return
     */
    SmsProvider getSmsProvider(String providerCode);
}
