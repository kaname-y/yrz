package com.itqf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  6:28 下午
 * 描述:
 */
//@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.itqf.mappers")
@EnableFeignClients
//@EnableCircuitBreaker
@ServletComponentScan("classpath:ServletContextListener.xml") //扫描监听器
public class WebmasterStartApp {
    public static void main(String[] args) {
        SpringApplication.run(WebmasterStartApp.class, args);
    }
}
