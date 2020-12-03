package com.gzgz.cloud.authority.common.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.gzgz.cloud.authority.common.asserts.Result;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:全局异常处理
 * @Author: pzl
 * @CreateDate: 2020/11/18 10:00
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("发生未知异常：", e);
        return failResult("100000", "未知异常，请联系客服");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleException(IllegalArgumentException e) {
        log.error("参数错误：", e);
        return failResult("100001", e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleException(HttpRequestMethodNotSupportedException ex) {
        log.error("请求方式错误：{}", ex.getMessage());
        return failResult("100001", "请求方式错误,请检查接口请求方式(GET,POST...)");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleException(HttpMediaTypeNotSupportedException ex) {
        log.error("请求Content-type错误：{}", ex.getMessage());
        return failResult("100001", "不支持的Content-type类型");
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型错误：", e);
        return failResult("100001", "参数类型错误");
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result handleException(IllegalStateException ex) {
        log.error("状态错误：", ex);
        return failResult("100002", ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result handleException(BindException ex) {
        log.error("表单绑定或校验失败：{}", ex.getMessage());
        List<ObjectError> list = ex.getAllErrors();
        StringJoiner errorMsg = new StringJoiner(",");
        for (ObjectError objectError : list) {
            errorMsg.add(objectError.getDefaultMessage());
        }
        return failResult("100003", "参数格式错误，请返回重试");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleException(MethodArgumentNotValidException ex) {
        log.error("表单绑定或校验失败：{}", ex.getBindingResult().getAllErrors());
        List<ObjectError> list = ex.getBindingResult().getAllErrors();
        HashSet<String> errorMsg = new HashSet<>();
        for (ObjectError objectError : list) {
            errorMsg.add(objectError.getDefaultMessage());
        }
        return failResult("100004", String.join(",", errorMsg));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleException(DuplicateKeyException e) {
        log.error("违反数据库唯一约束：", e);
        return failResult("100006", "数据重复");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleException(DataIntegrityViolationException e) {
        log.error("违反数据库约束错误：", e);
        return failResult("100006", "违反数据库约束错误");
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public Result handleException(MyBatisSystemException e) {
        log.error("数据库错误：", e);
        return failResult("100007", "数据库错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleException(HttpMessageNotReadableException ex) {
        log.error("参数格式错误：{}", ex.getMessage());
        return failResult("100011", "参数格式错误");
    }

    @ExceptionHandler(JsonParseException.class)
    public Result handleException(JsonParseException ex) {
        log.error("json格式错误：{}", ex.getMessage());
        return failResult("100012", "json格式错误");
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleException(MaxUploadSizeExceededException ex) {
        log.error("上传文件超过大小限制：{}", ex.getMessage());
        return failResult("100008", "上传文件超过大小限制");
    }

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return Result.fail(e.getErrorCode().toString());
        }
        return Result.fail(e.getMessage());
    }

    private Result failResult(String code, String message) {
        Result failResult = new Result();
        failResult.setCode(code);
        failResult.setMessage(message);
        failResult.setSuccess(false);
        return failResult;
    }

}
