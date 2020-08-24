package com.itqf.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/19  5:09 下午
 * 描述: 时间和出错的增强类
 */
@Component
public class TimeAndErrorAdvice {

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis() * 1000;


        Object proceed = proceedingJoinPoint.proceed();


        long end = System.currentTimeMillis() * 1000;

        //写到本地磁盘

        long time = end - start;

        //proceedingJoinPoint 切点身上要获取 目标对象.方法的名字 获取目标类的名字

        String methodName = proceedingJoinPoint.getSignature().getName(); //获取目标方法的名字

        String name = proceedingJoinPoint.getClass().getName(); //获取目标类的名字


        System.out.println(name + "类内部的:" + methodName + "方法消耗时间:" + time);

        return proceed;
    }

    /**
     * Throwable throwable 只能作为错误通知传递!
     * joinPoint 除了环绕都可以传递
     *
     * @param joinPoint
     * @param throwable 给你一个对象 让你获取错误信息
     */
    //import org.aspectj.lang.JoinPoint;
    public void returnException(JoinPoint joinPoint, Throwable throwable) {

        //记住 环绕通知是切点!其他的通知都有连接点!可以写 可以不写!
        //写是为方便获取目标类和方法的信息

        String name = joinPoint.getSignature().getName();
        String name1 = joinPoint.getTarget().getClass().getName();

        String message = throwable.getMessage();

        System.out.println("发送邮件");
        System.out.println("类:" + name1 + " 方法:" + name + " 错误内容:" + message);
    }


}
