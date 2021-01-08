package com.gzgz.cloud.test.smsplatform.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Profile("local")
@RestController
@RequestMapping("/mock/smsplatform/emay")
public class MockEmaySmsController {


    @PostMapping("/sendSms")
    public Object sendSms(String phone, String message, String cdkey, String password, String segid, String addserial, String smspriority) {
        log.info("模拟【亿美】短信发送：phone={}, message={}", phone, message);
        if(phone.startsWith("140")){
            String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<response>" +
                    "   <error>-104</error>" +
                    "   <message>请求超过限制</message>" +
                    "</response>";
            return result;
        }else{
            String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<response>" +
                    "   <error>0</error>" +
                    "   <message>成功</message>" +
                    "</response>";
            return result;
        }

    }

}
