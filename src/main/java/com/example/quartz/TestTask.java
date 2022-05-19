package com.example.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestTask {
    private static final Logger loger = LogManager.getLogger(TestTask.class);

    public void run(){
        loger.info("定时器运行了！");
    }
}
