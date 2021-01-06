package com.gzgz.cloud.common.asserts;

/**
 * @ClassName: IErrorCode
 * @Description:封装API的错误码
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:56
 * @Version: 1.0
 */
public interface IErrorCode {
    long getCode();
    String getMessage();

}
