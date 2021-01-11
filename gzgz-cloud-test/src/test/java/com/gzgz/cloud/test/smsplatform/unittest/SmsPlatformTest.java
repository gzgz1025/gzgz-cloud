package com.gzgz.cloud.test.smsplatform.unittest;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gzgz.cloud.common.asserts.Result;
import com.gzgz.cloud.sms.dal.mapper.SmsChannelMapper;
import com.gzgz.cloud.sms.dal.mapper.SmsLogMapper;
import com.gzgz.cloud.sms.dal.mapper.SmsRouteMapper;
import com.gzgz.cloud.sms.dal.mapper.SmsTemplateMapper;
import com.gzgz.cloud.sms.dal.model.SmsChannel;
import com.gzgz.cloud.sms.dal.model.SmsLog;
import com.gzgz.cloud.sms.dal.model.SmsRoute;
import com.gzgz.cloud.sms.dal.model.SmsTemplate;
import com.gzgz.cloud.sms.facade.SmsApiService;
import com.gzgz.cloud.sms.facade.dto.SmsRequestDTO;
import com.gzgz.cloud.sms.facade.dto.SmsTemplateRequestDTO;
import com.gzgz.cloud.sms.facade.enums.SMSProviderEnum;
import com.gzgz.cloud.test.base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SmsPlatformTest extends TestBase {
    @Autowired
    private SmsApiService smsApiService;
    @Autowired
    private SmsChannelMapper smsChannelMapper;
    @Autowired
    private SmsRouteMapper smsRouteMapper;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;
    @Autowired
    private SmsLogMapper smsLogMapper;

    @BeforeEach
    public void before() {
        // 1. 准备数据
        String emapSendUrl = "http://localhost:8107/mock/smsplatform/emay/sendSms";
        SmsChannel emaySmsChannel = new SmsChannel();
        emaySmsChannel.setId(1L);
        emaySmsChannel.setChannelName("亿美测试渠道");
        emaySmsChannel.setChannelConfig("{\"sendUrl\":\"" + emapSendUrl + "\"}");
        emaySmsChannel.setProviderCode(SMSProviderEnum.EMAY.name());
        emaySmsChannel.setProviderName(SMSProviderEnum.EMAY.message());
        emaySmsChannel.setIsValid(true);
        emaySmsChannel.setCreateTime(new Date());
        emaySmsChannel.setUpdateTime(new Date());
        smsChannelMapper.insertSelective(emaySmsChannel);

        SmsRoute emaySmsRoute = new SmsRoute();
        emaySmsRoute.setId(1L);
        emaySmsRoute.setRouteName("亿美测试路由");
        emaySmsRoute.setRouteDescribe("亿美测试路由");
        emaySmsRoute.setSystemCode("ABC");
        emaySmsRoute.setBusinessType("aaa");
        emaySmsRoute.setIsValid(true);
        emaySmsRoute.setChannelId(1L);
        emaySmsRoute.setCreateTime(new Date());
        emaySmsRoute.setUpdateTime(new Date());
        smsRouteMapper.insertSelective(emaySmsRoute);

        String baiWuSendUrl = "http://localhost:8107/mock/smsplatform/baiwu/sendSms";
        SmsChannel baiWuSmsChannel = new SmsChannel();
        baiWuSmsChannel.setId(2L);
        baiWuSmsChannel.setChannelName("百悟测试渠道");
        baiWuSmsChannel.setChannelConfig("{\"sendUrl\":\"" + baiWuSendUrl + "\"}");
        baiWuSmsChannel.setProviderCode(SMSProviderEnum.BAIWU.name());
        baiWuSmsChannel.setProviderName(SMSProviderEnum.BAIWU.message());
        baiWuSmsChannel.setIsValid(true);
        baiWuSmsChannel.setCreateTime(new Date());
        baiWuSmsChannel.setUpdateTime(new Date());
        smsChannelMapper.insertSelective(baiWuSmsChannel);

        SmsRoute baiWuSmsRoute = new SmsRoute();
        baiWuSmsRoute.setId(2L);
        baiWuSmsRoute.setRouteName("百悟测试路由");
        baiWuSmsRoute.setRouteDescribe("百悟测试路由");
        baiWuSmsRoute.setSystemCode("ABC");
        baiWuSmsRoute.setBusinessType("bbb");
        baiWuSmsRoute.setIsValid(true);
        baiWuSmsRoute.setChannelId(2L);
        baiWuSmsRoute.setCreateTime(new Date());
        baiWuSmsRoute.setUpdateTime(new Date());
        smsRouteMapper.insertSelective(baiWuSmsRoute);

        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setId(1L);
        smsTemplate.setTemplateCode("TEST");
        smsTemplate.setTemplateName("测试模板");
        smsTemplate.setTemplateContent("测试模板");
        smsTemplate.setTemplateParam("");
        smsTemplate.setChannelName("");
        smsTemplate.setIsValid(true);
        smsTemplate.setCreateTime(new Date());
        smsTemplate.setUpdateTime(new Date());
        smsTemplateMapper.insertSelective(smsTemplate);
    }

    @AfterEach
    public void after() {
        // 5. 清理初始化数据及业务数据
        smsChannelMapper.deleteByPrimaryKey(1L);
        smsRouteMapper.deleteByPrimaryKey(1L);

        smsChannelMapper.deleteByPrimaryKey(2L);
        smsRouteMapper.deleteByPrimaryKey(2L);

        smsTemplateMapper.deleteByPrimaryKey(1L);
        smsLogMapper.delete(null);
    }

    @ParameterizedTest
    @CsvSource({
            "1001,13212345678,ABC,aaa,测试一下,true,'短信发送成功',1",//成功
            "1002,13212345678,ABC,bbb,测试一下,true,'短信发送成功',2",//成功
            "1003,'13212345678,13212345679',ABC,aaa,测试一下,true,'短信发送成功',1",//成功
            "1004,13212345678,XXX,yyy,测试一下,false,路由无效或无相关路由配置！,1",//系统编码和业务类型不存在
            "1005,13212345678,ABC,yyy,测试一下,false,路由无效或无相关路由配置！,1",//业务类型不存在
            "1006,13212345678,XXX,aaa,测试一下,false,路由无效或无相关路由配置！,1",//系统编码不存在
            "1007,13212345678,ABC,bbb,测试一下,false,渠道无效或无相关渠道配置！,2",//百悟渠道被禁用
            "1008,13212345678,ABC,aaa,测试一下,false,路由无效或无相关路由配置！,1",//ABC路由被禁用
            "1009,13212345678,ABC,bbb,测试一下,false,渠道无效或无相关渠道配置！,2",//百悟渠道未创建
            "1010,'',ABC,bbb,测试一下,false,手机号不能为空,2",//手机号未传入
            "1011,13212345678,'',bbb,测试一下,false,系统代码不能为空,2",//系统编码未传入
            "1012,13212345678,ABC,'',测试一下,false,业务类型不能为空,2",//业务类型未传入
            "1013,13212345678,ABC,bbb,'',false,短信内容不能为空,2",//短信内容未传入
            "1014,13312345678,ABC,bbb,'测试一下',false,发送短信失败,2",//百悟短信发送失败,code码未匹配上
            "1015,'13312345678,13412345679',ABC,bbb,'测试一下',false,发送短信失败,2",//百悟批量短信发送失败
            "1016,'13512345678',ABC,bbb,'测试一下',false,未知异常,2",//百悟请求超时
            "1017,12112345678,ABC,bbb,'测试一下',false,余额不足,2",//百悟短信发送失败,code码匹配为100
            "1018,14012345678,ABC,aaa,'测试一下',false,请求超过限制,1",//亿美短信发送失败,code码匹配为-104
    })
    public void testSend(int testId,String mobileNos, String systemCode, String businessType, String content,
                         boolean sendStatus, String resultMessage ,String channelId)  throws InterruptedException{
        //1.准备数据
        if(testId==1007){
            SmsChannel smsChannel=new SmsChannel();
            smsChannel.setId(2L);
            smsChannel.setIsValid(Boolean.FALSE);
            smsChannelMapper.updateByPrimaryKeySelective(smsChannel);
        }
        if(testId==1008){
            SmsRoute smsRoute=new SmsRoute();
            smsRoute.setId(1L);
            smsRoute.setIsValid(Boolean.FALSE);
            smsRouteMapper.updateByPrimaryKeySelective(smsRoute);
        }
        if(testId==1009){
            smsChannelMapper.deleteByPrimaryKey(2L);
        }
        if(testId==1016){
            SmsChannel smsChannel=new SmsChannel();
            smsChannel.setId(2L);
            String baiWuSendUrl = "http://localhost:8107/mock/smsplatform/123";
            //smsChannel.setChannelConfig("{\"sendUrl\":\"" + baiWuSendUrl + "\"}");
            //http://192.168.10.11:9080/msg/send 连接超时
            //http://localhost:8107/mock/smsplatform/baiwu/sendSms 读取超时
            smsChannel.setChannelConfig("{'sendUrl':'http://192.168.10.11:9080/msg/send','timeout':'5000'}");
            smsChannelMapper.updateByPrimaryKeySelective(smsChannel);
        }

        try {
            // 2. 调用接口
            SmsRequestDTO smsRequest = new SmsRequestDTO();
            List<String> mobileList = Arrays.asList(mobileNos.split(","));
            smsRequest.setMobileNos(mobileList);
            smsRequest.setSystemCode(systemCode);
            smsRequest.setBusinessType(businessType);
            smsRequest.setContent(content);
            Result result = smsApiService.send(smsRequest);
            log.info(result.toString());
            // 3. 验证结果
            Assertions.assertEquals(sendStatus, result.isSuccess());
            Assertions.assertEquals(resultMessage, result.getMessage());
            // 4. 验证数据
            if(testId==1001||testId==1002||testId==1003 || testId==1014 || testId==1015){
                TimeUnit.SECONDS.sleep(10);
                for(int i=0; i<mobileList.size();i++){
                    SmsLog smsLogExample = new SmsLog();
                    smsLogExample.setMobile(mobileList.get(i));
                    List<SmsLog> smsLogList = smsLogMapper.select(smsLogExample);
                    smsLogList.forEach(e->{
                        Assertions.assertNotNull(e.getId());
                        Assertions.assertNotNull(e.getApiRequestNo());
                        Assertions.assertEquals(Long.parseLong(channelId),e.getChannelId());
                        Assertions.assertEquals(smsLogExample.getMobile(),e.getMobile());
                        Assertions.assertEquals(String.valueOf(sendStatus==true?0:1),e.getSendStatus());
                        Assertions.assertEquals(resultMessage,e.getSendResult());
                        Assertions.assertEquals("2".equals(channelId)?content:"【】"+content,e.getSendContent());
                        Assertions.assertEquals(systemCode,e.getSystemCode());
                        Assertions.assertEquals(businessType,e.getBusinessType());
                        Assertions.assertNotNull(e.getCreateTime());
                    });

                }
            }

        }catch (Exception e) {
            Assertions.assertEquals(resultMessage, e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "13212345678,ABC,aaa,'测试一下${参数1}','参数1','{\"参数1\":\"参数内容\"}',true,''",
            "13212345678,ABC,aaa,'测试一下${参数1},这里还有个${参数2}','参数1,参数2','{\"参数1\":\"参数内容\",\"参数2\":\"参数2的内容\",}',true,''",
            "13212345678,ABC,bbb,'测试一下${参数1}','参数1','{\"参数1\":\"参数内容\"}',true,''",
            "13212345678,XXX,xxx,'测试一下${参数1}','参数1','{\"参数1\":\"参数内容\"}',false,'路由无效或无相关路由配置！'",
            "13212345678,ABC,aaa,'测试一下${参数1}','参数1','{\"参数1\":\"参数内容\",\"参数2\":\"参数2的内容\"}',false,'传入模板参数个数不对'",
            "13212345678,ABC,aaa,'测试一下${参数1}','参数1','{\"参数2\":\"参数2内容\"}',false,'传入参数不对'",
    })
    public void testSendTemplate(String mobileNos, String systemCode, String businessType, String templateContent, String templateParamConfig, String templateParam,
                                 boolean resultSuccess, String resultMessage) {

        Map<String, String> templateParamMap = JSON.parseObject(templateParam, new TypeReference<Map<String, String>>() {
        });

        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setId(1L);
        smsTemplate.setTemplateContent(templateContent);
        smsTemplate.setTemplateParam(templateParamConfig);
        smsTemplateMapper.updateByPrimaryKeySelective(smsTemplate);

        // 2. 调用接口
        SmsTemplateRequestDTO smsRequest = new SmsTemplateRequestDTO();
        smsRequest.setMobileNos(Arrays.asList(mobileNos.split(",")));
        smsRequest.setSystemCode(systemCode);
        smsRequest.setBusinessType(businessType);
        smsRequest.setTemplateCode("TEST");
        smsRequest.setTemplateParam(templateParamMap);
        Result result = smsApiService.sendTemplate(smsRequest);

        // 3. 验证结果
        Assertions.assertEquals(resultSuccess, result.isSuccess(), "发送短信不成功：" + result);
        if (!result.isSuccess()) {
            Assertions.assertEquals(resultMessage, result.getMessage(), "发送短信不成功：" + result);
        }

        // 4. 验证数据
    }
}
