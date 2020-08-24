package com.itqf.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/18  5:54 下午
 * 描述:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

public class SearchStartApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchStartApp.class, args);
    }
}
