package com.itqf.service.impl;

import com.itqf.entity.User;
import com.itqf.mappers.UserMapper;
import com.itqf.service.UserService;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  5:59 下午
 * 描述:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User searchUname(String uname) {

        User user = userMapper.searchUname(uname);
        return user;
    }

    @Override
    public int regeistUser(User user) {
        String upassword = user.getUpassword();
//        String salt = UUID.randomUUID().toString();
//        user.setUsalt(salt);
        user.setUsalt(user.getUname());

        //处理密码
        Md5Hash md5Hash = new Md5Hash(upassword, user.getUsalt(), 1024);
        upassword = md5Hash.toString();

        user.setUpassword(upassword);
        int i = userMapper.regeistUser(user);
        return i;
    }

    @Override
    public int searchUserByPhone(String uphone) {
        int i = userMapper.searchUserByPhone(uphone);
        return i;
    }

    @Override
    public String exchangePassword(String upassword, String username) {
        //处理密码
        Md5Hash md5Hash = new Md5Hash(upassword, username, 1024);
        upassword = md5Hash.toString();

        return upassword;
    }

    @Override
    public int changePasswordInDB(int uid, String newpassword) {
        int i = userMapper.changePassword(uid, newpassword);
        return i;
    }

    @Override
    public int searchuUserByEmail(String uemail) {
        int i = userMapper.searchUserByEmail(uemail);
        return i;
    }
}
