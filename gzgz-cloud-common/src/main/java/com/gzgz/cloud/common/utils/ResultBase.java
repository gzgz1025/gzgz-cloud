package com.gzgz.cloud.common.utils;

import java.io.Serializable;

/**
 * @ClassName: ResultBase
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/12 15:35
 * @Version: 1.0
 */
public class ResultBase implements Serializable {
    private String message;
    private String code;
    private boolean success;

    public ResultBase() {
    }

    public static ResultBase success() {
        ResultBase result = new ResultBase();
        result.setSuccess(true);
        result.setCode("SUCCESS");
        result.setMessage("");
        return result;
    }

    public static ResultBase success(String message) {
        ResultBase result = new ResultBase();
        result.setSuccess(true);
        result.setCode("SUCCESS");
        result.setMessage(message);
        return result;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return "ResultBase(super=" + super.toString() + ", message=" + this.getMessage() + ", code=" + this.getCode() + ", success=" + this.isSuccess() + ")";
    }
}
