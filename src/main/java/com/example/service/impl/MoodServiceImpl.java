package com.example.service.impl;

import com.example.activemq.MoodProducer;
import com.example.entity.Mood;
import com.example.repository.MoodRepository;
import com.example.service.MoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MoodServiceImpl implements MoodService {
    @Resource
    private MoodRepository moodRepository;

    @Override
    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }

    @Resource
    private MoodProducer moodProducer;
    private Destination destination = new ActiveMQQueue("mood.queue.asyn.save");

    @Override
    public String asynSave(Mood mood) {
        moodProducer.sendMessage(destination, mood);
        return "success";
    }
}
