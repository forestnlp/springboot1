package com.example.actuator;

import com.example.service.UserService;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id="userEndPoints")
public class UserEndPoint {

    @Resource
    private UserService userService;

    @ReadOperation
    public Map<String,Object> invoke(){
        Map<String,Object> map = new HashMap<>();
        map.put("current_time",new Date());
        map.put("user_num",userService.findAll().size());
        return map;
    }
}
