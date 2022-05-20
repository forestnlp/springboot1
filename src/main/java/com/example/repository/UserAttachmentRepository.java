package com.example.repository;

import com.example.entity.UserAttachment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAttachmentRepository extends MongoRepository<UserAttachment,String> {
}
