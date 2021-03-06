package com.gzgz.cloud.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: AuthorityApplication
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/3 17:06
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.gzgz.cloud.authority.dal")
public class AuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class,args);
    }
}
