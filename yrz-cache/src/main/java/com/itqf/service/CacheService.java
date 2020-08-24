package com.itqf.service;

import com.itqf.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  2:46 下午
 * 描述:
 */
public interface CacheService {

    //查询新登录的用户id
    int hgetLoginUid(String uname);

    //把新登录的用户加到缓存里
    boolean hsetLogin(@PathVariable("uname") String uname);


    //把新注册的用户加到缓存里
    Boolean addRegeistUser(User user);

    //把所有数据加到缓存中
    Boolean addAll();

    //获取key-hash的所有的key
    Map<Object, Object> getAllKey(String key);

    Boolean testadd(String key, String value);

    User searchByUid(int uid);

    //获取hash中指定键的数值
    Object hget(String key, String hashkey);

    //删除hash中指定的健
    long hashDelete(String key, Object hashkey);

    //key-value形式存储hash
    boolean hashset(String key, Map<String, Object> map);

    //存储hash
    boolean hset(String key, Object hashkey, Object value);


}
