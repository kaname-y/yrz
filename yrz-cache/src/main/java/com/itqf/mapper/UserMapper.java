package com.itqf.mapper;

import com.itqf.entity.User;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  3:34 下午
 * 描述:
 */
public interface UserMapper {

    List<User> searchAllUser();

    int  searchUidByUname(String uname);
}
