package com.itqf.service.impl;

import com.itqf.entity.User;
import com.itqf.service.CacheService;
import com.itqf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.*;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  3:10 下午
 * 描述:
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public int hgetLoginUid(String uname) {
        int uid = (int) redisTemplate.opsForValue().get(uname);
        return uid;
    }

    @Override
    public boolean hsetLogin(String uname) {
        int uid = userService.searchUidByName(uname);
        redisTemplate.opsForValue().set(uname, uid);
        return true;
    }

    @Override
    public Boolean addRegeistUser(User user) {
        String uid = String.valueOf(user.getUid());
        Map<Object, Object> map = new HashMap<>();
        map.put("uname", user.getUname());
        map.put("upassword", user.getUpassword());
        map.put("uimg", user.getUimg());
        map.put("uemail", user.getUemail());
        map.put("uphone", user.getUphone());
        map.put("ubirthday", user.getUbirthday());
        map.put("udate", user.getUdate());
        map.put("ustatue", user.getUstatue());
        map.put("umoney", user.getUmoney());
        map.put("usalt", user.getUsalt());
        map.put("usex", user.getUsex());
        map.put("uarea", user.getUarea());
        redisTemplate.opsForHash().putAll(uid, map);

        return true;
    }

    @Override
    public Boolean addAll() {
        List<User> users = userService.searchAllUsers();
        for (User user : users) {
            int uid = user.getUid();
            String suid = String.valueOf(uid);

            //获取所有的key
//            Map entries = redisTemplate.opsForHash().entries(suid);
//            System.out.println("entries = " + entries);

            Map<Object, Object> map = new HashMap<>();
            map.put("uname", user.getUname());
            map.put("upassword", user.getUpassword());
            map.put("uimg", user.getUimg());
            map.put("uemail", user.getUemail());
            map.put("uphone", user.getUphone());
            map.put("ubirthday", user.getUbirthday());
            map.put("udate", user.getUdate());
            map.put("ustatue", user.getUstatue());
            map.put("umoney", user.getUmoney());
            map.put("usalt", user.getUsalt());
            map.put("usex", user.getUsex());
            map.put("uarea", user.getUarea());

            redisTemplate.opsForHash().putAll(suid, map);

            //添加新的key
//            redisTemplate.opsForHash().put(suid, "sname", user.getUname());
//            redisTemplate.opsForHash().put(suid, "spassword", user.getUpassword());
        }

        return true;
    }

    @Override
    public Map<Object, Object> getAllKey(String key) {
        Map keys = redisTemplate.opsForHash().entries(key);
        return keys;
    }

    @Override
    public Boolean testadd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
        return true;
    }


    @Override
    public User searchByUid(int uid) {

        Map keyvalues = redisTemplate.opsForHash().entries(String.valueOf(uid));

        User user = new User();
        user.setUid(uid);
        user.setUname((String) keyvalues.get("uname"));

        /*
        Set set = keyvalues.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next(); //这样获得到的是键数对
        }
   */
        return user;
    }

    @Override
    public Object hget(String key, String hashkey) {
        Object o = redisTemplate.opsForHash().get(key, hashkey);

        if (o != null) {
            return o.toString();
        } else {
            return "null";
        }

    }

    @Override
    public long hashDelete(String key, Object hashkey) {

        Long i = redisTemplate.opsForHash().delete(key, hashkey);
        return i;

    }

    @Override
    public boolean hashset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    @Override
    public boolean hset(String key, Object hashkey, Object value) {
        redisTemplate.opsForHash().put(key, hashkey, value);
        return true;
    }
}
