package com.gzgz.cloud.common.exception;


import com.gzgz.cloud.common.enums.CommonErrorCode;
import com.gzgz.cloud.common.utils.Messageable;

/**
 * @ClassName: ApiException
 * @Description:自定义API异常
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:59
 * @Version: 1.0
 */
public class ApiException extends RuntimeException implements Messageable {
    private static final long serialVersionUID = 1L;

    private String code;

    public ApiException() {
        super();
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
    }

    public ApiException(String message) {
        super(message);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
    }

    public ApiException(Throwable cause) {
        super(cause);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
    }

    public ApiException(String code, String message) {
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
        this.code = code;
    }

    public ApiException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
        this.code = code;
    }

    public ApiException(Throwable cause, String code) {
        super(cause);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
        this.code = code;
    }

    public ApiException(String message, String code, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = CommonErrorCode.UNKNOWN_ERROR.getCode();
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return null;
    }
}
