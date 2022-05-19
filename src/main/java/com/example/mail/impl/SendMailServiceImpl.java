package com.example.mail.impl;

import com.example.entity.User;
import com.example.mail.SendMailService;
import com.example.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    JavaMailSender mailSender;

    @Resource
    private UserService service;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger logger = LogManager.getLogger(SendMailServiceImpl.class);

    @Override
    public boolean sendmail(List<User> users) {
        if(users==null||users.size()<=0) return false;
        try {
            for(User u:users){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessage.setFrom(from);
                mimeMessage.setSubject("这是一封程序自动发送的邮件");
                mimeMessage.setText(u.getUsername()+",你的pass卡="+u.getPassword());
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("19085498@qq.com"));

                this.mailSender.send(mimeMessage);
            }
            return true;
        } catch (MessagingException e) {
            logger.error("sendmail expection ,user=%s,",users,e);
            return false;
        }
    }
}
