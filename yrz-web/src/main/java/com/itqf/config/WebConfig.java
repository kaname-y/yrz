package com.itqf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  3:27 下午
 * 描述:  解决跨域
 */
//@Configuration
//public class WebConfig {
//
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setMaxAge(3600L);
//        corsConfiguration.addAllowedOrigin("http://localhost:15000"); // 允许http://localhost:8080域名使用
//        corsConfiguration.addAllowedHeader("*"); // 允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
//        return new CorsFilter(source);
//    }
//}
