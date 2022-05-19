package com.example.service;

import com.example.entity.User;

public interface UserMybatisService {
    User findUserByNameAndPassword(String name, String password);
}
