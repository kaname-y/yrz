package com.itqf.mq;

import com.itqf.common.RabbitMqCommons;

import com.itqf.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  2:04 下午
 * 描述:
 */
@Component
@Slf4j
public class ReceiveFromMq {

    //声明全局静态对象
    static User user = new User();

    @RabbitListener(queues = RabbitMqCommons.TOPIC_PRE_SEND, containerFactory = "pointTaskContainerFactory")
    public static void receiveUser(User user) {
        log.info("接收到的登录用户是:user={}", user);
        ReceiveFromMq.user = user;


    }


}
