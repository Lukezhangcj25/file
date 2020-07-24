package com.info.sms.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Luke 2020/7/24 10:09
 */
@Component
@Slf4j
public class HotTagTasks {
    @Scheduled(fixedRate = 50000)
    public  void reportCurrentTime(){
        log.info("The time is now{}",new Date());
    }
}
