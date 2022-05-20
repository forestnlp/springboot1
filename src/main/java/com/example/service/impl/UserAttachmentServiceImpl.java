package com.example.service.impl;

import com.example.entity.UserAttachment;
import com.example.repository.UserAttachmentRepository;
import com.example.service.UserAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAttachmentServiceImpl implements UserAttachmentService {

    @Resource
    private UserAttachmentRepository userAttachmentRepository;

    @Override
    public UserAttachment save(UserAttachment userAttachment) {
        return userAttachmentRepository.save(userAttachment);
    }
}
