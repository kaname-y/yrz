package com.itqf.service;

import com.itqf.entity.User;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  5:58 下午
 * 描述:
 */
public interface UserService {


    //查询数据库中是否存在改用户名
    User searchUname(String uname);

    int regeistUser(User user);

    //根据手机号查用户的uid
    int searchUserByPhone(String uphone);

    //对新输入的密码进行加盐处理
    String exchangePassword(String upassword,String username);

    //修改密码
    int changePasswordInDB(int uid, String newpassword);

    //根据邮箱查用户的uid
    int searchuUserByEmail(String email);


}
