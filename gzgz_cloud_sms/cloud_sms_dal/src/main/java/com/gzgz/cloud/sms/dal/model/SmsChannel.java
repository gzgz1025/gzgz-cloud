package com.gzgz.cloud.sms.dal.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sms_channel")
public class SmsChannel {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 渠道名称
     */
    @Column(name = "channel_name")
    private String channelName;

    /**
     * 渠道配置
     */
    @Column(name = "channel_config")
    private String channelConfig;

    /**
     * 供应商代码
     */
    @Column(name = "provider_code")
    private String providerCode;

    /**
     * 供应商名称
     */
    @Column(name = "provider_name")
    private String providerName;

    /**
     * 有效标志
     */
    @Column(name = "is_valid")
    private Boolean isValid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取渠道名称
     *
     * @return channel_name - 渠道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     *
     * @param channelName 渠道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取渠道配置
     *
     * @return channel_config - 渠道配置
     */
    public String getChannelConfig() {
        return channelConfig;
    }

    /**
     * 设置渠道配置
     *
     * @param channelConfig 渠道配置
     */
    public void setChannelConfig(String channelConfig) {
        this.channelConfig = channelConfig;
    }

    /**
     * 获取供应商代码
     *
     * @return provider_code - 供应商代码
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     * 设置供应商代码
     *
     * @param providerCode 供应商代码
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    /**
     * 获取供应商名称
     *
     * @return provider_name - 供应商名称
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * 设置供应商名称
     *
     * @param providerName 供应商名称
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * 获取有效标志
     *
     * @return is_valid - 有效标志
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * 设置有效标志
     *
     * @param isValid 有效标志
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channelName=").append(channelName);
        sb.append(", channelConfig=").append(channelConfig);
        sb.append(", providerCode=").append(providerCode);
        sb.append(", providerName=").append(providerName);
        sb.append(", isValid=").append(isValid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}