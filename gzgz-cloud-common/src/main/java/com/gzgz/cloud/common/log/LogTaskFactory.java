package com.gzgz.cloud.common.log;

import com.gzgz.cloud.mbg.module.SmsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimerTask;

/**
 * @ClassName: LogTaskFactory
 * @Description:时间异步 还有EVENTBUS实现方法
 * @Author: pzl
 * @CreateDate: 2020/12/29 14:43
 * @Version: 1.0
 */
@Slf4j
@Component
@Deprecated
public class LogTaskFactory {
//    @Autowired
//    private  SmsLogService smsLogService;

    private static LogTaskFactory logTaskFactory;

    @PostConstruct
    public void init() {
        logTaskFactory=this;
        //logTaskFactory.smsLogService = this.smsLogService;
    }

    public static TimerTask smslog(Long id,Long segid,String systemCode,String businessType,Long channelId,String mobileNo,String sendStatus,String sendResult,String sendContent ){
        return new TimerTask() {
            @Override
            public void run() {
                SmsLog smslog=LogFactory.createSMSSendLog(id, segid, systemCode, businessType, channelId, mobileNo, sendStatus, sendResult, sendContent);
                log.info("日志记录:{}",smslog);
                try {
                    //logTaskFactory.smsLogService.saveLog(smslog);
                }catch (Exception e){
                    log.info("日志保存失败,异常:{}",e);
                }
            }
        };
    }
}
