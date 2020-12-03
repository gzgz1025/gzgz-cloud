package com.gzgz.cloud.authority.common.utils;


/**
 * @ClassName: EnumUtil
 * @Description:获取value返回枚举对象
 * @Author: pzl
 * @CreateDate: 2020/11/27 9:38
 * @Version: 1.0
 */
public class EnumUtil {
    /**
     * 获取value返回枚举对象
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends EnumMessage> T getByCode(String code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
