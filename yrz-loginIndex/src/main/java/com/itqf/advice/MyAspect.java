package com.itqf.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/19  9:40 下午
 * 描述:
 */
@Aspect
public class MyAspect {


    @Pointcut("execution(* com.itqf.service.impl.*Impl.*(..))")
    public void addBeforeLog(){}

    @Pointcut("execution(* com.itqf.service.impl.*Impl.*(..))")
    public void addAfterLog(){}


    //修改密码时
//    @Pointcut("execution(* com.itqf.service.impl.*Impl.*(..))")
//    public void transPassword(){}

}
