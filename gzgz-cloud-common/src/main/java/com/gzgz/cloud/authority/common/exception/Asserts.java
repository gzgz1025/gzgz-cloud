package com.gzgz.cloud.authority.common.exception;


import com.gzgz.cloud.authority.common.asserts.IErrorCode;

/**
 * @ClassName: Asserts
 * @Description:断言处理类，用于抛出各种API异常
 * @Author: pzl
 * @CreateDate: 2020/11/18 10:00
 * @Version: 1.0
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
