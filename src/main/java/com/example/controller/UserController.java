package com.example.controller;

import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/all")
    public String test(Model model){
        List<User> all = userService.findAll();
        model.addAttribute("users",all);
        return "user";
    }

    @RequestMapping("/except")

    public String findAll(Model model){
        List<User> all = userService.findAll();
        model.addAttribute("users",all);
        throw new BusinessException("业务异常");
    }


    @RequestMapping("/retry")

    public String findByNameAndPasswordRetry(Model model){
        User zhangsan = userService.findByNameAndPasswordRetry("zhangsan", "123456");
        model.addAttribute("users",zhangsan);
        return "success";
    }
}
