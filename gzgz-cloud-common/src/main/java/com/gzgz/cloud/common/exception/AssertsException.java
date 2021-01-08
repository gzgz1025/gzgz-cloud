package com.gzgz.cloud.common.exception;


import com.google.common.collect.Sets;
import com.gzgz.cloud.common.enums.CommonErrorCode;

import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: Asserts
 * @Description:断言处理类，用于抛出各种API异常
 * @Author: pzl
 * @CreateDate: 2020/11/18 10:00
 * @Version: 1.0
 */
public class AssertsException extends ApiException {
    private static final long serialVersionUID = 1L;

    private String code;
    private Set<String> errors;

    public AssertsException() {
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
    }

    public AssertsException(String message) {
        super(message);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.errors.add(message);
    }

    public AssertsException(String message, Throwable cause) {
        super(message, cause);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.errors.add(message);
    }

    public AssertsException(Throwable cause) {
        super(cause);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.errors.add(cause.getMessage());
    }

    public AssertsException(String message, String code) {
        super(message);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.code = code;
        this.errors.add(message);
    }

    public AssertsException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.code = code;
        this.errors.add(message);
    }

    public AssertsException(Throwable cause, String code) {
        super(cause);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.code = code;
        this.errors.add(cause.getMessage());
    }

    public AssertsException(String message, String code, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, code, cause, enableSuppression, writableStackTrace);
        this.code = CommonErrorCode.BUSINESS_ERROR.code();
        this.errors = Sets.newHashSet();
        this.code = code;
        this.errors.add(message);
    }

    public AssertsException addError(String msg) {
        this.errors.add(msg);
        return this;
    }

    public String getMessage() {
        String msg;
        if (this.errors.isEmpty()) {
            msg = super.getMessage();
        } else {
            StringBuilder sb = new StringBuilder(this.errors.size() * 15);
            Iterator var3 = this.errors.iterator();

            while(var3.hasNext()) {
                String message = (String)var3.next();
                sb.append(message).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
            msg = sb.toString();
        }

        return msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.getMessage();
    }

    public static void throwIt(String message) {
        throw new AssertsException(message);
    }

    public static void throwIt(String message, String code) {
        throw new AssertsException(message, code);
    }

    public static void throwIt(String format, Object... args) {
        throw new AssertsException(String.format(format, args), (String)null, (Throwable)null, false, false);
    }

    public static void throwIt(boolean result, String format, Object... args) {
        if (result) {
            throw new AssertsException(String.format(format, args), (String)null, (Throwable)null, false, false);
        }
    }
}
