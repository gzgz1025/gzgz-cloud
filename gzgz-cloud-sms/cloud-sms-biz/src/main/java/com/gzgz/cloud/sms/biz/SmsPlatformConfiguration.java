package com.gzgz.cloud.sms.biz;

import com.gzgz.cloud.sms.dal.mapper.SmsRouteMapper;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackageClasses = SmsRouteMapper.class)
public class SmsPlatformConfiguration {
}
