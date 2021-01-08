package com.gzgz.cloud.sms.facade.dto;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: SmsRequestDTO
 * @Description:自定义内容发送
 * @Author: pzl
 * @CreateDate: 2020/12/18 17:18
 * @Version: 1.0
 */
@Setter
@Getter
@ApiModel
public class SmsRequestDTO  {
    /**
     * 多个手机号
     */
    @NotNull(message = "手机号不能为空")
    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "多个手机号")
    private List<String> mobileNos;

    /**
     * 系统
     */
    @NotBlank(message = "系统代码不能为空")
    @ApiModelProperty(value = "系统代码")
    private String systemCode;

    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空")
    @ApiModelProperty(value = "业务类型")
    private String businessType;

    /**
     * 短信内容
     */
    @NotBlank(message = "短信内容不能为空")
    @ApiModelProperty(value = "短信内容")
    private String content;

    /**
     * 单个手机号
     * @param mobileNo
     */
    @ApiModelProperty(value = "单个手机号")
    public void setMobileNo(String mobileNo){
        if(ObjectUtils.isNotEmpty(mobileNo)){
            mobileNos = Lists.newArrayListWithCapacity(1);
        }
        mobileNos.add(mobileNo);
    }
}
