package com.gzgz.cloud.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName: EventHandler
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 14:50
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EventHandler {
}
