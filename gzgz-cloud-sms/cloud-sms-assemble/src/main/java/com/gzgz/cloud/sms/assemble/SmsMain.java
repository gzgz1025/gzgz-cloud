package com.gzgz.cloud.sms.assemble;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SmsMain
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/6 9:53
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gzgz.cloud.sms.*"})
@MapperScan("com.gzgz.cloud.sms.dal.mapper")
public class SmsMain {
    public static void main(String[] args) {
        SpringApplication.run(SmsMain.class, args);
    }
}
