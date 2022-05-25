package com.example.service;

import com.example.entity.UserRoleRel;

import java.util.List;

public interface UserRoleRelService {
    List<UserRoleRel> findbyUserId(Integer uid);
}
