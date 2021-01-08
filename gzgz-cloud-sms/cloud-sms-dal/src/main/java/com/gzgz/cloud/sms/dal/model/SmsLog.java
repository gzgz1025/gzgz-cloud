package com.gzgz.cloud.sms.dal.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sms_log")
public class SmsLog {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 请求流水号
     */
    @Column(name = "api_request_no")
    private Long apiRequestNo;

    /**
     * 渠道ID
     */
    @Column(name = "channel_id")
    private Long channelId;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 发送状态
     */
    @Column(name = "send_status")
    private String sendStatus;

    /**
     * 发送结果
     */
    @Column(name = "send_result")
    private String sendResult;

    /**
     * 发送内容
     */
    @Column(name = "send_content")
    private String sendContent;

    /**
     * 系统编码
     */
    @Column(name = "system_code")
    private String systemCode;

    /**
     * 业务编码
     */
    @Column(name = "business_type")
    private String businessType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取请求流水号
     *
     * @return api_request_no - 请求流水号
     */
    public Long getApiRequestNo() {
        return apiRequestNo;
    }

    /**
     * 设置请求流水号
     *
     * @param apiRequestNo 请求流水号
     */
    public void setApiRequestNo(Long apiRequestNo) {
        this.apiRequestNo = apiRequestNo;
    }

    /**
     * 获取渠道ID
     *
     * @return channel_id - 渠道ID
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * 设置渠道ID
     *
     * @param channelId 渠道ID
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取发送状态
     *
     * @return send_status - 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置发送状态
     *
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取发送结果
     *
     * @return send_result - 发送结果
     */
    public String getSendResult() {
        return sendResult;
    }

    /**
     * 设置发送结果
     *
     * @param sendResult 发送结果
     */
    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    /**
     * 获取发送内容
     *
     * @return send_content - 发送内容
     */
    public String getSendContent() {
        return sendContent;
    }

    /**
     * 设置发送内容
     *
     * @param sendContent 发送内容
     */
    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    /**
     * 获取系统编码
     *
     * @return system_code - 系统编码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置系统编码
     *
     * @param systemCode 系统编码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * 获取业务编码
     *
     * @return business_type - 业务编码
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务编码
     *
     * @param businessType 业务编码
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", apiRequestNo=").append(apiRequestNo);
        sb.append(", channelId=").append(channelId);
        sb.append(", mobile=").append(mobile);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", sendResult=").append(sendResult);
        sb.append(", sendContent=").append(sendContent);
        sb.append(", systemCode=").append(systemCode);
        sb.append(", businessType=").append(businessType);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}