package com.itqf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  5:53 下午
 * 描述: 拦截器
 */
//@Configuration
//public class MyInterceptors extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        HandlerInterceptor handlerInterceptor = new HandlerInterceptor(){
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//                    throws Exception {
//                System.out.println("开始执行........");
//                return true;
//            }
//
//            @Override
//            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                   ModelAndView modelAndView) throws Exception {
//            }
//
//            @Override
//            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                        Exception ex) throws Exception {
//
//            }
//        };
//        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
//    }
//}
