package com.itqf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  10:37 上午
 * 描述:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStartApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStartApp.class, args);
    }
}
