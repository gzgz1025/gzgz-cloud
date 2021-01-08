package com.gzgz.cloud.test.smsplatform.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@Profile("local")
@RestController
@RequestMapping("/mock/smsplatform/baiwu")
public class MockBaiWuSmsController {

    @PostMapping("/sendSms")
    public Object sendSms(String mobile, String msg_content, String corp_id, String corp_pwd, String corp_service, String corp_msg_id, String ext) throws InterruptedException {
        log.info("模拟【百悟】短信发送：mobile={}, msg_content={}", mobile, msg_content);
        if(mobile.startsWith("133")||mobile.startsWith("134")){
            return "1";
        }else if(mobile.startsWith("135")){
            TimeUnit.MINUTES.sleep(1);
            return "1";
        }else if(mobile.startsWith("121")){
            return "100";
        }else if(mobile.startsWith("122")){
            return "101";
        }else if(mobile.startsWith("123")){
            return "102";
        }else if(mobile.startsWith("124")){
            return "103";
        }else if(mobile.startsWith("125")){
            return "104";
        }else if(mobile.startsWith("126")){
            return "105";
        }else if(mobile.startsWith("127")){
            return "106";
        }else if(mobile.startsWith("128")){
            return "107";
        }else if(mobile.startsWith("129")){
            return "108";
        }else if(mobile.startsWith("140")){
            return "109";
        }else if(mobile.startsWith("141")){
            return "110";
        }else if(mobile.startsWith("142")){
            return "9";
        }else{
            return "0";
        }




    }

}
