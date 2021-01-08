package com.gzgz.cloud.sms.assemble;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: SmsMain
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/6 9:53
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.gzgz.cloud.sms.dal")
public class SmsMain {
    public static void main(String[] args) {
        SpringApplication.run(SmsMain.class, args);
    }
}
