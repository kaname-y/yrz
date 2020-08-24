package com.itqf.controller;

import com.itqf.entity.User;
import com.itqf.service.CacheService;
import com.itqf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  2:46 下午
 * 描述:
 */
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;


    /*
    根据uname查询uid
     */
    @RequestMapping("/hgetuid/{uname}")
    public int hgetLoginUid(@PathVariable("uname") String uname) {
        int uid = cacheService.hgetLoginUid(uname);
        return uid;
    }

    /*
    存储新登录的用户
     */
    @RequestMapping("/hsetLogin/{uname}")
    public boolean hsetLogin(@PathVariable("uname") String uname) {
        boolean b = cacheService.hsetLogin(uname);
        return b;
    }


    /*
    存储新注册的用户
     */
    @ResponseBody
    @RequestMapping("/hsetuser")
    public boolean hsetuser(@RequestBody User user) {
        log.error("接收到的注册用户是{}", user);
        System.out.println("user = " + user);
        Boolean b = cacheService.addRegeistUser(user);
        System.out.println("b = " + b);
        return b;
    }


    /*
    存储hash
     */
    @ResponseBody
    @RequestMapping("/hset/{key}/{hashkey}/{value}")
    public boolean hset(@PathVariable("key") String key,
                        @PathVariable("hashkey") Object hashkey,
                        @PathVariable("value") Object value) {
        boolean hset = cacheService.hset(key, hashkey, value);
        return hset;
    }

    /*
    key-value形式存储hash
     */
    @ResponseBody
    @RequestMapping("/hashadd/{key}")
    public boolean hashKeyValueset(@PathVariable("key") String key,
                                   @RequestBody Map<String, Object> map) {
        boolean hashset = cacheService.hashset(key, map);
        return true;
    }

    /*
    删除一个或者多个hash key
     */
    @ResponseBody
    @RequestMapping("/hashdelete/{key}/{hashkey}")
    public Long hashDelete(@PathVariable("key") String key,
                           @PathVariable("hashkey") Object hashkey) {
        long i = cacheService.hashDelete(key, hashkey);
        return i;
    }


    /*
    根据key hashkey查询
     */
    @ResponseBody
    @RequestMapping("/searchByKey2/{key}/{hashkey}")
    public String searchByKeyAndHashKey(@PathVariable("key") String key,
                                        @PathVariable("hashkey") String hashkey) {
        Object hget = cacheService.hget(key, hashkey);
        return hget.toString();
    }

    /*
    根据key查询 用户所有信息
     */
    @ResponseBody

    @RequestMapping("/searchByUid/{uid}")
    public User searchByUid(@PathVariable("uid") int uid) {
        User user = cacheService.searchByUid(uid);
        return user;
    }


    //调用数据库已查询的所有数据，加到缓存中
    @ResponseBody
    @RequestMapping("/all")
    public boolean addAll() {
//        List<User> users = userService.searchAllUsers();

        Boolean aBoolean = cacheService.addAll();
        return aBoolean;
    }

    @ResponseBody
    @RequestMapping("/add/{key}/{value}")
    public boolean addtest(@PathVariable("key") String key, @PathVariable("value") String value) {

        Boolean testadd = cacheService.testadd(key, value);
        return testadd;
    }
}
