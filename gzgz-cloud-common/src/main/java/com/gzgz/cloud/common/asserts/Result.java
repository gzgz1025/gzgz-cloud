package com.gzgz.cloud.common.asserts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: Result
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:49
 * @Version: 1.0
 */
@ApiModel
public class Result<T> {
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("错误码")
    private String code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    public Result() {
    }

    public boolean isSuccess() {
        return this.success && "success".equals(this.code);
    }

    @JsonIgnore
    public boolean isProcessing() {
        return this.success && "processing".equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !this.success;
    }

    public static Result<String> success(String message) {
        return success(message, "");
    }

    public static <T> Result<T> success(T data) {
        return success("成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result();
        result.setData(data);
        result.setSuccess(true);
        result.setCode("success");
        result.setMessage(message);
        return result;
    }

    public static Result<String> processing(String message) {
        return processing(message, "");
    }

    public static <T> Result<T> processing(T data) {
        return success("处理中", data);
    }

    public static <T> Result<T> processing(String message, T data) {
        Result<T> result = new Result();
        result.setData(data);
        result.setSuccess(true);
        result.setCode("processing");
        result.setMessage(message);
        return result;
    }

    public static Result fail(String message) {
        return fail("fail", message);
    }

    public static Result fail(String code, String message) {
        Result<?> result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public String toString() {
        return "Result(success=" + this.isSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
