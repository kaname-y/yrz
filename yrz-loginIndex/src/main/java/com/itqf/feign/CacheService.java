package com.itqf.feign;

import com.itqf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/18  4:18 下午
 * 描述:
 */
@FeignClient("yrz-cache")
public interface CacheService {


    /*
    把登录后的用户id 和一个标记符号写进redis
     */
    @ResponseBody
    @RequestMapping("/cache/hsetLogin/{uname}")
    public boolean hsetLogin(@PathVariable("uname") String uname);


    /*
    把新注册的用户添加到redis中
     */
    @ResponseBody
    @RequestMapping("/cache/hsetuser")
    public boolean hsetuser(@RequestBody User user);

    /*
    根据用户id查询hash中用户所有信息
     */
    @ResponseBody
    @RequestMapping("/cache/searchByUid/{uid}")
    User searchByUid(@PathVariable("uid") int uid);

    @ResponseBody
//    @RequestLine("/cache/searchByKey2/{key}/{hashkey}")
    @RequestMapping("/cache/searchByKey2/{key}/{hashkey}")
    String hget(@PathVariable("key") String key,
                @PathVariable("hashkey") String hashkey);

    @ResponseBody
    @RequestMapping(value = "/cache/hashadd/{key}")
    public boolean hashKeyValueset(@PathVariable("key") String key,
                                   @RequestBody Map<String, Object> map);
}

