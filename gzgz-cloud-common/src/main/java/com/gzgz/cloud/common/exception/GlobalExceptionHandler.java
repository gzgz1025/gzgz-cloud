package com.gzgz.cloud.common.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.common.enums.CommonErrorCode;
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
        return Result.fail(CommonErrorCode.UNKNOWN_ERROR.code(), CommonErrorCode.UNKNOWN_ERROR.message());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleException(IllegalArgumentException e) {
        log.error("参数错误：", e);
        return Result.fail(CommonErrorCode.INVALID_PARAM.code(), CommonErrorCode.INVALID_PARAM.message());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleException(HttpRequestMethodNotSupportedException ex) {
        log.error("请求方式错误：{}", ex.getMessage());
        return Result.fail(CommonErrorCode.METHOD_REQUEST_ERROR.code(), CommonErrorCode.METHOD_REQUEST_ERROR.message());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleException(DuplicateKeyException e) {
        log.error("违反数据库唯一约束：", e);
        return Result.fail(CommonErrorCode.DB_UNION_ERROR.code(), CommonErrorCode.DB_UNION_ERROR.message());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleException(DataIntegrityViolationException e) {
        log.error("违反数据库约束错误：", e);
        return Result.fail(CommonErrorCode.DB_UNION_ERROR.code(), CommonErrorCode.DB_UNION_ERROR.message());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public Result handleException(MyBatisSystemException e) {
        log.error("数据库错误：", e);
        return Result.fail(CommonErrorCode.DB_ERROR.code(), CommonErrorCode.DB_ERROR.message());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleException(HttpMessageNotReadableException ex) {
        log.error("参数格式错误：{}", ex.getMessage());
        return Result.fail(CommonErrorCode.PARAM_FORMAT_ERROR.code(), CommonErrorCode.PARAM_FORMAT_ERROR.message());
    }

    @ExceptionHandler(JsonParseException.class)
    public Result handleException(JsonParseException ex) {
        log.error("json格式错误：{}", ex.getMessage());
        return Result.fail(CommonErrorCode.JSON_ERROR.code(), CommonErrorCode.JSON_ERROR.message());
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleException(MaxUploadSizeExceededException ex) {
        log.error("上传文件超过大小限制：{}", ex.getMessage());
        return Result.fail(CommonErrorCode.FILE_SIZE_ERROR.code(), CommonErrorCode.FILE_SIZE_ERROR.message());
    }

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result handle(ApiException e) {
        if (e.getCode() != null) {
            return Result.fail(e.getCode(), e.getMessage());
        }
        return Result.fail(e.getMessage());
    }

}
