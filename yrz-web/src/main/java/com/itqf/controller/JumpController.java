package com.itqf.controller;

import com.itqf.entity.User;
import com.itqf.feign.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  2:39 下午
 * 描述:
 */
@Controller
public class JumpController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/")
    public String jumpindex(@RequestParam("uname") String uname, HttpSession session) {

        int uid = cacheService.hgetLoginUid(uname);

        if (uid > 0) {
            //用户已登录,从缓存中查询用户的信息
            User user = cacheService.searchByUid(uid);
            session.setAttribute("user", user);

//            return "forward:/index";
            return "index";
        } else {
            return "403";
        }

    }

    @RequestMapping("/test")
    public String jumpTest() {
        System.out.println("true aaaaaaa= " + true);
        return "test";
    }

    @RequestMapping("/yrz/index")
    public String jumpyrzIndex() {
        System.out.println("trueindexx = " + true);
        return "index";
    }


    @RequestMapping("/index")
    public static String jumpIndex() {

        System.out.println("trueindex = " + true);
        return "index";
    }

}
