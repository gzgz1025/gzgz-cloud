package com.gzgz.cloud.sms.dal.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sms_route")
public class SmsRoute {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 路由名称
     */
    @Column(name = "route_name")
    private String routeName;

    /**
     * 路由描述
     */
    @Column(name = "route_describe")
    private String routeDescribe;

    /**
     * 系统编码
     */
    @Column(name = "system_code")
    private String systemCode;

    /**
     * 业务类型
     */
    @Column(name = "business_type")
    private String businessType;

    /**
     * 有效标志
     */
    @Column(name = "is_valid")
    private Boolean isValid;

    /**
     * 渠道ID
     */
    @Column(name = "channel_id")
    private Long channelId;

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
     * 获取路由名称
     *
     * @return route_name - 路由名称
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * 设置路由名称
     *
     * @param routeName 路由名称
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * 获取路由描述
     *
     * @return route_describe - 路由描述
     */
    public String getRouteDescribe() {
        return routeDescribe;
    }

    /**
     * 设置路由描述
     *
     * @param routeDescribe 路由描述
     */
    public void setRouteDescribe(String routeDescribe) {
        this.routeDescribe = routeDescribe;
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
     * 获取业务类型
     *
     * @return business_type - 业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型
     *
     * @param businessType 业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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
        sb.append(", routeName=").append(routeName);
        sb.append(", routeDescribe=").append(routeDescribe);
        sb.append(", systemCode=").append(systemCode);
        sb.append(", businessType=").append(businessType);
        sb.append(", isValid=").append(isValid);
        sb.append(", channelId=").append(channelId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}