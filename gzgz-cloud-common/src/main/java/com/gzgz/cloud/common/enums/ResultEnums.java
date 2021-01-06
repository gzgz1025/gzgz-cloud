package com.gzgz.cloud.common.enums;

import com.gzgz.cloud.common.asserts.IErrorCode;

/**
 * @ClassName: ResultEnums
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:56
 * @Version: 1.0
 */
public enum ResultEnums implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    private ResultEnums(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultEnums{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
