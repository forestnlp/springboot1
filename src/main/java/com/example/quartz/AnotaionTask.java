package com.example.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class AnotaionTask {

    private static final Logger logger = LogManager.getLogger(AnotaionTask.class);

    @Scheduled(cron = "*/180 * * * * *")
    public void runbyexpression(){
        logger.info("我是一个被注解方法定义的定时任务");
    }

}
