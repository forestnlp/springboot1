package com.example.activemq;

import com.example.entity.Mood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination,final String message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public void sendMessage(Destination destination,final Mood message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

}
