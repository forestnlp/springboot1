package com.example.service.impl;

import com.example.entity.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository repository;

    @Override
    public Role find(Integer id) {
        return repository.getById(id);
    }
}
