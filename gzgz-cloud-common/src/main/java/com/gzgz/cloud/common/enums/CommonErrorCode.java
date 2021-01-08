package com.gzgz.cloud.common.enums;

import com.gzgz.cloud.common.utils.Messageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ResultEnums
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/11/18 9:56
 * @Version: 1.0
 */
public enum CommonErrorCode implements Messageable {
    DB_ERROR("DB_ERROR", "数据库错误"),
    IO_ERROR("IO_ERROR", "IO异常"),
    NET_ERROR("NET_ERROR", "网络异常"),
    BUSINESS_ERROR("BUSINESS_ERROR", "业务异常"),
    TRANSACTION_ERROR("TRANSACTION_ERROR", "事务异常"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知异常"),
    INVALID_PARAM("INVALID_PARAM", "请求参数非法"),
    PARAM_FORMAT_ERROR("PARAM_FORMAT_ERROR", "参数格式错误"),
    REQUEST_REPEATED("REQUEST_REPEATED", "重复的请求"),
    METHOD_REQUEST_ERROR("METHOD_REQUEST_ERROR","请求方式错误"),
    DB_UNION_ERROR("DB_UNION_ERROR","违反数据库约束"),
    JSON_ERROR("JSON_ERROR","json格式错误"),
    FILE_SIZE_ERROR("FILE_SIZE_ERROR","上传文件超过大小限制");

    private final String code;
    private final String message;

    private CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static CommonErrorCode getByCode(String code) {
        CommonErrorCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CommonErrorCode _enum = var1[var3];
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }

        return null;
    }

    public static List<CommonErrorCode> getAllEnum() {
        List<CommonErrorCode> list = new ArrayList(values().length);
        CommonErrorCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CommonErrorCode _enum = var1[var3];
            list.add(_enum);
        }

        return list;
    }

    public static List<String> getAllEnumCode() {
        List<String> list = new ArrayList(values().length);
        CommonErrorCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CommonErrorCode _enum = var1[var3];
            list.add(_enum.code());
        }

        return list;
    }

    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        } else {
            CommonErrorCode _enum = getByCode(code);
            return _enum == null ? null : _enum.getMessage();
        }
    }

    public static String getCode(CommonErrorCode _enum) {
        return _enum == null ? null : _enum.getCode();
    }
}
