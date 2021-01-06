package com.gzgz.cloud.sms.biz.domain.service.impl;

import com.zds.boot.common.exception.BusinessException;
import com.zds.channel.biz.smsplatform.domain.provider.SmsProvider;
import com.zds.channel.biz.smsplatform.domain.service.SmsRouteService;
import com.zds.channel.dal.mapper.SmsRouteMapper;
import com.zds.channel.dal.model.SmsRoute;
import com.zds.channel.facade.api.smsplatform.enums.SMSProviderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: SmsRouteServiceImpl
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/21 16:30
 * @Version: 1.0
 */
@Service
@Slf4j
public class SmsRouteServiceImpl implements SmsRouteService, ApplicationContextAware {

    @Autowired
    private SmsRouteMapper smsRouteMapper;
    private SmsProvider smsProvider;
    private ApplicationContext applicationContext;

    private static Map<String, SmsProvider> PROVIDER_MAP = new HashMap<>();

    @PostConstruct
    public void init() {
        PROVIDER_MAP = applicationContext.getBeansOfType(SmsProvider.class);
    }

    @Override
    public SmsRoute getSmsRoute(String systemCode, String businessType) {
        SmsRoute route = new SmsRoute();
        route.setSystemCode(systemCode);
        route.setBusinessType(businessType);
        route.setIsValid(Boolean.TRUE);
        return smsRouteMapper.selectOne(route);
    }

    @Override
    public SmsProvider getSmsProvider(String providerCode) {
        SMSProviderEnum providerEnum = Enum.valueOf(SMSProviderEnum.class, providerCode);
        smsProvider = PROVIDER_MAP.get(providerEnum.code());
        BusinessException.throwIt(smsProvider == null, "短信smsProvider未注入");
        return smsProvider;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
