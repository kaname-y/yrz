package com.itqf.service;

import com.itqf.entity.User;
import org.springframework.stereotype.Service;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/21  3:50 下午
 * 描述:
 */
public interface UserService {

    User searchByUname(String uname);


}
