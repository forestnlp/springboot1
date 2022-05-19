package com.example.activemq;

import com.example.entity.Mood;
import com.example.service.MoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MoodConsumer {

    @JmsListener(destination = "mood.queue")
    public void receiveQueue(String text) {
        System.out.println("接收到一条消息，内容是："+text);
    }

    @Resource
    private MoodService service;
    @JmsListener(destination = "mood.queue.asyn.save")
    public void receiveQueue(Mood mood){
        service.save(mood);
    }

}
