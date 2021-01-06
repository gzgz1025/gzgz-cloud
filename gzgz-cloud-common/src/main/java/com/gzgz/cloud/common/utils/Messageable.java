package com.gzgz.cloud.common.utils;

import java.io.Serializable;

/**
 * @ClassName: CodeEnum
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/11/27 9:48
 * @Version: 1.0
 */
public interface Messageable extends Serializable {
    String code();

    String message();
}
