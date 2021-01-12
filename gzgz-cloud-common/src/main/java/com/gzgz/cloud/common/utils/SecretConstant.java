package com.gzgz.cloud.common.utils;

/**
 * @ClassName: SecretConstant
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/12 11:57
 * @Version: 1.0
 */
public class SecretConstant {
    //签名秘钥 自定义
    public static final String APP_SECRET = "***********";

    //token过期时间, 这里设置的是一天的有效时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    //用于JWT加密的密匙 自定义
    public static final String DATAKEY = "************";
}
