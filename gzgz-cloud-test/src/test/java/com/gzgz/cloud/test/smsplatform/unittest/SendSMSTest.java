package com.gzgz.cloud.test.smsplatform.unittest;

import com.gzgz.cloud.sms.facade.SmsApiService;
import com.gzgz.cloud.sms.facade.dto.SmsTemplateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: SendSMSTest
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/29 11:08
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
public class SendSMSTest {
    @Autowired
    private SmsApiService smsApiService;

    @Test
    public void test() {
        List<String> list=new ArrayList<>();
        list.add("15802339724");
        list.add("");
        list.add("asdfv");


        /*SmsRequestDTO dto = new SmsRequestDTO();
        dto.setMobileNo("15802339546");
        //dto.setMobileNos(list);
        dto.setSystemCode("PROPERTY");
        dto.setBusinessType("UNIVERSAL");
        dto.setContent("测试");
        smsApiService.send(SingleRequest.from(dto));*/

        SmsTemplateRequestDTO dto=new SmsTemplateRequestDTO();
        //dto.setMobileNo("15802339546");
        dto.setMobileNos(list);
        dto.setSystemCode("TEST");
        dto.setBusinessType("TEST");
        dto.setTemplateCode("SYNERGY_REQUEST");
        Map<String,String> map=new HashMap<>();
        dto.setTemplateParam(map);
        smsApiService.sendTemplate(dto);
    }
}
