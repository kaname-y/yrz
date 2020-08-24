package com.itqf.feign;

import com.itqf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/21  3:51 下午
 * 描述:
 */
@FeignClient("yrz-cache")
public interface CacheService {

    @RequestMapping("/cache/hgetuid/{uname}")
    public int hgetLoginUid(@PathVariable("uname") String uname);


    @RequestMapping("/cache/searchByUid/{uid}")
    public User searchByUid(@PathVariable("uid") int uid);

}
