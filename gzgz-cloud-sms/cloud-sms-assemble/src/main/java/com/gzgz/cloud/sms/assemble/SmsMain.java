package com.gzgz.cloud.sms.assemble;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SmsMain
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/6 9:53
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gzgz.cloud.sms.*"})
public class SmsMain {
    public static void main(String[] args) {
        SpringApplication.run(SmsMain.class, args);
    }
}
