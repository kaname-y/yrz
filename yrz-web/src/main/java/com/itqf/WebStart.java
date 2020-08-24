package com.itqf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  1:10 上午
 * 描述:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);
    }
}
