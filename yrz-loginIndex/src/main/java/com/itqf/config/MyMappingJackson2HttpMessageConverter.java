package com.itqf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/20  1:16 上午
 * 描述:  feignconfig的配置
 */
//
//public class MyMappingJackson2HttpMessageConverter extends org.springframework.http.converter.json.MappingJackson2HttpMessageConverter {
//
//    public MyMappingJackson2HttpMessageConverter() {
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.TEXT_PLAIN);
//        setSupportedMediaTypes(mediaTypes);
//
////        // 先将原先支持的MediaType列表拷出
////        List<MediaType> mediaTypeList = new ArrayList<>(this.getSupportedMediaTypes());
////        //加入对text/plain的支持
////        mediaTypeList.add(new MediaType("text", "plain", StandardCharsets.UTF_8));
////        //将已经加入了text/json的MediaType支持列表设置为其支持的媒体类型列表
////        this.setSupportedMediaTypes(mediaTypeList);
//    }
//
//
//}
