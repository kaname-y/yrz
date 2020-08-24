package com.itqf.service;

import com.itqf.entity.User;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  3:31 下午
 * 描述:
 */
public interface UserService {

    //查询数据库中所有的用户
    List<User> searchAllUsers();

    //根据用户名查询id
    int searchUidByName(String uname);


}
