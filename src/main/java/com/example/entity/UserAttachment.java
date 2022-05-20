package com.example.entity;

import org.springframework.data.annotation.Id;

public class UserAttachment {
    @Id
    private String id;
    private String userId;
    private String fileName;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
