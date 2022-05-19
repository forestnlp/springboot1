package com.example.quartz;

import com.example.entity.User;
import com.example.mail.SendMailService;
import com.example.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configurable
@EnableScheduling
public class MailSendTask {

    private static final Logger logger = LogManager.getLogger(MailSendTask.class);

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "*/60 * * * * *")
    public void runbyexpression(){
        List<User> all = userService.findAll();
        if(all==null||all.size()<=0) return;
        sendMailService.sendmail(all);
        logger.info("定时发送邮件了");
    }

}
