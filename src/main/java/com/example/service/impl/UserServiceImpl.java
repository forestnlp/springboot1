package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserRepository userRepository;

    //Key
    private static final String ALL_USER="ALL_USER_LIST";

    @Override
    public User findbyId(int id) {
        //在redis中查找
        List<User> range = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if(range!=null&&range.size()>0){
            for(User user:range){
                if(user.getId()==id)
                    return user;
            }
        }
        //在db中查找

        Optional<User> byId = userRepository.findById(id);
        if(byId==null) return null;
        User user = byId.isPresent()?byId.get():null;
        if(user!=null){
            redisTemplate.opsForList().leftPush(ALL_USER,user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try{
            System.out.println("开始查询库表任务");
            long start = System.currentTimeMillis();
            List<User> all = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，总共耗时="+(end-start)+"毫秒");
            return all;
        }
        catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Async
    public Future<List<User>> findAsynAll() {
        try{
            System.out.println("开始查询库表任务");
            long start = System.currentTimeMillis();
            List<User> all = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，总共耗时="+(end-start)+"毫秒");
            return new AsyncResult<List<User>>(all);
        }
        catch (Exception e){
            return new AsyncResult<List<User>>(null);
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        User save = userRepository.save(user);
        //if(1/0==0) return null;
        return save;
    }

    @Override
    public void delete(int id) {
         userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByUsernameLike(name);
    }

    @Override
    public List<User> findByIdIn(Collection<Integer> ids) {
        return userRepository.findByIdIn(ids);
    }
}
