package com.gzgz.cloud.authority.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能： 以秒为单位生成唯一的序列号 <br/>
 * 生成格式：YYMMddHHmmssXXXXXXX <br/>
 * XXXXXXX：代表序列号，从1开始
 * 例子: 1808312321280000001 或者 1808312321280001234 <br/>
 * <p>局限性： 每秒生成最大范围number (1000万-1) 个数</p>
 */
public class GenerateDateTimeUniqueID {

    private static final DateFormat DF = new SimpleDateFormat("yyMMddHHmmss");
    private static volatile long LAST_TIME = -1;
    private static final AtomicInteger COUNT = new AtomicInteger();

    private GenerateDateTimeUniqueID() {
    }

    public static long getUniqueId(Integer number) {
        Date date = new Date();
        String dateStr = DF.format(date);
        long curTime = Long.parseLong(dateStr);
        int curCount = 0;

        synchronized (GenerateDateTimeUniqueID.class) {
            if (curTime < LAST_TIME) {
                curTime = LAST_TIME;
            } else if (curTime > LAST_TIME) {
                LAST_TIME = curTime;
                System.out.println(Thread.currentThread().getName() + "-" + COUNT.get());
                COUNT.set(0);
            }
            curCount = COUNT.incrementAndGet();
        }

        return curTime * number + curCount;
    }

    public static String getUniqueIdStr(Integer number) {
        return String.valueOf(getUniqueId(number));
    }

    public static Long getUniqueIdLong(Integer number) {
        return getUniqueId(number);
    }

}

