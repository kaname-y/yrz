package com.itqf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  2:47 下午
 * 描述:
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.itqf.mapper")
public class CacheStartApp {
    public static void main(String[] args) {
        SpringApplication.run(CacheStartApp.class, args);
    }
}
