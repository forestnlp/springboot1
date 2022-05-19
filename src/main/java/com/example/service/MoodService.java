package com.example.service;

import com.example.entity.Mood;

public interface MoodService {
    Mood save(Mood mood);
    String asynSave(Mood mood);
}
