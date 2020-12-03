package com.gzgz.cloud.authority.common.exception;


import com.gzgz.cloud.authority.common.asserts.IErrorCode;

/**
 * @ClassName: ApiException
 * @Description:自定义API异常
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:59
 * @Version: 1.0
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
