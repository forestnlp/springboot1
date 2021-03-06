package com.example.service;

import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public interface UserService {
    User findbyId(int id);
    List<User> findAll();
    Future<List<User>> findAsynAll();
    User save(User user);
    void delete(int id);

    Page<User> findAll(Pageable pageable);

    List<User> findByName(String name);

    List<User> findByNameLike(String name);

    List<User> findByIdIn(Collection<Integer> ids);

    //retry
    User findByNameAndPasswordRetry(String name,String password);
}
