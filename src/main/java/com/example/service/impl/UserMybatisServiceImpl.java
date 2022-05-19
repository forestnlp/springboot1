package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserMybatisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserMybatisServiceImpl implements UserMybatisService {

    @Resource
    private UserDao userDao;

    @Override
    public User findUserByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name,password);
    }
}
