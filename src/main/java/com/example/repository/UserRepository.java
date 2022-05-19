package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String name);

    List<User> findByUsernameLike(String name);

    List<User> findByIdIn(Collection<Integer> ids);
}
