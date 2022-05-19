package com.example.service.impl;

import com.example.entity.Mood;
import com.example.repository.MoodRepository;
import com.example.service.MoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodRepository moodRepository;

    @Override
    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }
}
