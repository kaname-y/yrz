package com.itqf.advice;

;
import com.itqf.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/19  5:08 下午
 * 描述:  事务的增强类
 */
@Component
@Aspect
@Slf4j
public class TransactionAdvice {

    @Autowired
    private HttpServletRequest request;


    @Before("com.itqf.advice.MyAspect.addBeforeLog()")
    public void before() {
        //执行目标方法之前执行

        String method = request.getMethod();
        String addr = request.getRemoteAddr();
        String time = getTime();
        log.info("开始执行操作的时间为：{} ,请求的ip为: {}，执行的方法为: {}", time, addr, method);
        System.out.println("开始操作");
    }

    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        //joinPoint就是切点! 目标方法

        System.out.println("环绕前");
        Object obj = joinPoint.proceed(); //执行切点
        System.out.println("环绕后");

        return obj;

    }

    public void returning() {
        System.out.println("事务提交!");
    }

    public void returningException(JoinPoint joinPoint) {
        System.out.println("事务回滚");
    }

    @After("com.itqf.advice.MyAspect.addBeforeLog()")
    public void after() {

        String addr = request.getRemoteAddr();

        String time = getTime();
        System.out.println("操作执行结束!");
        log.info("执行操作结束的时间为：{} ,请求的ip为{}", time, addr);


    }

    public String getTime() {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dataFormat.format(new Date());
        return format;
    }


}
