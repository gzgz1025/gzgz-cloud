package com.gzgz.cloud.sms.biz.domain.event;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: SaveSmsLogEvent
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/31 14:09
 * @Version: 1.0
 */
@Data
public class SaveSmsLogEvent {
    private Long apiRequestNo;

    private Long channelId;

    private List<String> mobileNos;

    private String sendStatus;

    private String sendResult;

    private String sendContent;

    private String systemCode;

    private String businessType;

    private Date createTime;
}
