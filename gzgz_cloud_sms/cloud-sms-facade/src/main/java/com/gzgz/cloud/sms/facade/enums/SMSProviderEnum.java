package com.gzgz.cloud.sms.facade.enums;

import com.gzgz.cloud.common.utils.Messageable;

/**
 * @ClassName: SuppliersEnums
 * @Description:短信平台供应厂商
 * @Author: pzl
 * @CreateDate: 2020/12/21 14:30
 * @Version: 1.0
 */
public enum SMSProviderEnum implements Messageable {
    TEST("TestSmsProvider","测试短信渠道"),
    EMAY("EmaySmsProvider", "亿美"),
    BAIWU("BaiWuSmsProvider", "百悟");

    private final String code;
    private final String message;

    SMSProviderEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
