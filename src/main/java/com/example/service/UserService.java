package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User findbyId(int id);
    List<User> findAll();
    void save(User user);
    void delete(int id);
}
