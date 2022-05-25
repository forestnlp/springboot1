package com.example.service.impl;

import com.example.entity.UserRoleRel;
import com.example.repository.UserRoleRelRepository;
import com.example.service.UserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleRelServiceImpl implements UserRoleRelService {

    @Resource
    private UserRoleRelRepository userRoleRelRepository;

    @Override
    public List<UserRoleRel> findbyUserId(Integer uid) {
        return userRoleRelRepository.findByUid(uid);
    }
}
