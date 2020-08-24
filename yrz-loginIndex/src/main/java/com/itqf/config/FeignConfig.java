package com.itqf.config;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/20  1:09 上午
 * 描述:
 */
//@Configuration
//public class FeignConfig {
//    @Bean
//    public Decoder mappingJackson2HttpMessageConverter() {
//        MyMappingJackson2HttpMessageConverter messageConverter = new MyMappingJackson2HttpMessageConverter();
//
//      ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(messageConverter);
//        return new SpringDecoder(objectFactory);
//    }
//}
