package com.gzgz.cloud.sms.web.controller;

import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.facade.SmsApiService;
import com.gzgz.cloud.sms.facade.dto.SmsRequestDTO;
import com.gzgz.cloud.sms.facade.dto.SmsTemplateRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SmsController
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/31 17:40
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sms/")
@Api(tags = "短信发送接口")
public class SmsPlatformController {
    @Autowired
    private SmsApiService smsApiService;

    @RequestMapping("/send")
    @ApiOperation(value = "发送短信")
    public Result send(SmsRequestDTO request) {
        return smsApiService.send(request);
    }

    @RequestMapping("/sendAsync")
    @ApiOperation(value = "异步发送短信")
    public void sendAsync(SmsRequestDTO request) {
        smsApiService.sendAsync(request);
    }

    @RequestMapping("/sendTemplate")
    @ApiOperation(value = "按模板发送短信")
    public Result sendTemplate(SmsTemplateRequestDTO request) {
        return smsApiService.sendTemplate(request);
    }

    @RequestMapping("/sendTemplateAsync")
    @ApiOperation(value = "按模板异步发送短信")
    public void sendTemplateAsync(SmsTemplateRequestDTO request) {
        smsApiService.sendTemplateAsync(request);
    }
}
