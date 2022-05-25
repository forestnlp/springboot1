package com.example.repository;

import com.example.entity.UserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRelRepository extends JpaRepository<UserRoleRel,Integer> {
    List<UserRoleRel> findByUid(@Param("uid")Integer uid);
}
