package com.itqf.service.impl;

import com.itqf.entity.User;
import com.itqf.mapper.UserMapper;
import com.itqf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  3:33 下午
 * 描述:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> searchAllUsers() {
        List<User> users = userMapper.searchAllUser();
        return users;
    }

    @Override
    public int searchUidByName(String uname) {
        int uid = userMapper.searchUidByUname(uname);
        return uid;
    }
}
