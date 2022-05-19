package com.example;

import com.example.activemq.MoodProducer;
import com.example.entity.Mood;
import com.example.entity.User;
import com.example.service.MoodService;
import com.example.service.UserMybatisService;
import com.example.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Springboot1ApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private UserMybatisService userMybatisService;

    @Resource
    private MoodService moodService;

    @Resource
    private MoodProducer moodProducer;

    @Test
    public void mysqlTest(){
        String sql = "select id,username,password from t_user";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        users.forEach(System.out::println);
    }

    @Test
    public void testjpa(){
        User user = userService.findbyId(1);
        System.out.println(user);

        User user2 = new User();
        user2.setId(3);
        user2.setUsername("wangwu");
        user2.setPassword("$2a$10$6Uo59ROdhsE3OIxme9n5w.HEcOB96rvphDw9RBaObmPxmKg.XqEXa");
        User save = userService.save(user2);
        System.out.println(save);

        List<User> all = userService.findAll();
        System.out.println(all);

        user2.setUsername("王五");
        User save2 = userService.save(user2);
        System.out.println(save2);

        List<User> all2 = userService.findAll();
        System.out.println(all2);

        userService.delete(3);
    }

    @Test
    public void testjpa2(){
        List<User> lisi = userService.findByName("zhangsan");
        System.out.println(lisi);

        List<User> users = userService.findByNameLike("%z%");
        System.out.println(users);

        List<Integer> list = Arrays.asList(1);
        List<User> byIdIn = userService.findByIdIn(list);
        System.out.println(byIdIn);

        Pageable request = PageRequest.of(0,10);
        Page<User> all = userService.findAll(request);
        System.out.println(all.getSize()+","+all.getTotalPages());
        System.out.println(all.getContent());
    }

    @Test
    public void testjpa3(){
        User user = new User();
        user.setId(5);
        user.setUsername("zhaoliu");
        user.setPassword("$2a$10$6Uo59ROdhsE3OIxme9n5w.HEcOB96rvphDw9RBaObmPxmKg.XqEXa");
        User save = userService.save(user);
        System.out.println(save);
    }

    @Test
    public void testredis(){
        redisTemplate.opsForValue().set("currentDate", LocalDateTime.now());
        System.out.println(redisTemplate.opsForValue().get("currentDate"));

        redisTemplate.opsForValue().set("currentDate", LocalDateTime.of(2006,07,01,15,30,30));
        System.out.println(redisTemplate.opsForValue().get("currentDate"));

        redisTemplate.opsForList().leftPush("interested",1);
        redisTemplate.opsForList().leftPush("interested",2);
        redisTemplate.opsForList().leftPush("interested",3);
        redisTemplate.opsForList().leftPush("interested",4);

        System.out.println(redisTemplate.opsForList().range("interested",0,-1));
    }

    @Test
    public void testredis2(){

        redisTemplate.delete("ALL_USER_LIST");

        User user1 = userService.findbyId(1);
        Long all_user_list = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("redis count1 =" + all_user_list);

        User user2 = userService.findbyId(2);
        Long all_user_list2 = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("redis count2 =" + all_user_list2);

        User user3 = userService.findbyId(3);
        Long all_user_list3= redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("redis count3 =" + all_user_list3);
    }

    @Test
    public void testmybatis(){
        User zhangsan = userMybatisService.findUserByNameAndPassword("zhangsan", "$2a$10$QGOAiuA8ua2OK/AF4/2W3.GWd9oBNQImlDjRDmKNNm1QsR6wdT6Fy");
        System.out.println(zhangsan);
    }

    @Test
    public void testMood(){
        Mood mood = new Mood();
        mood.setId(1);
        mood.setContent("我感觉学什么都不太难");
        mood.setUser_id(1);
        mood.setPraiseNum(0);
        mood.setPublishTime(new Date());
        Mood save = moodService.save(mood);
        System.out.println(save);
    }

    @Test
    public void testActiveMq(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("mood.queue");
        moodProducer.sendMessage(activeMQQueue,"这是我的一条消息哦！");
    }

}
