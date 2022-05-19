package com.example.mail;

import com.example.entity.User;

import java.util.List;

public interface SendMailService {
    boolean sendmail(List<User> user);
}
