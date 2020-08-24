package com.itqf.mappers;

import com.itqf.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  6:10 下午
 * 描述:
 */
public interface UserMapper {

    //    @Select("select * from user where uname!=null and uname=#{uname}")
    User searchUname(String uname);

    int regeistUser(User user);

    Integer searchUserByPhone(String uphone);

    int changePassword(@Param("uid") int uid, @Param("upassword") String upassword);

    int searchUserByEmail(String uemail);
}
