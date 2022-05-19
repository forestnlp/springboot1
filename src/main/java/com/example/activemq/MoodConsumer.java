package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MoodConsumer {

    @JmsListener(destination = "mood.queue")
    public void receiveQueue(String text) {
        System.out.println("接收到一条消息，内容是："+text);
    }

}
