package com.itqf.controller;

import com.itqf.entity.User;
import com.itqf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  3:44 下午
 * 描述:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/all")
    public List<User> users(){
        List<User> users = service.searchAllUsers();
        return users;
    }
}
