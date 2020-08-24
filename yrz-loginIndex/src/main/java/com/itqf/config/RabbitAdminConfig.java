package com.itqf.config;

import com.itqf.common.RabbitMqCommons;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  1:53 下午
 * 描述:
 */
@Component
public class RabbitAdminConfig {
    /**
     * 消费者数量，默认10
     */
    public static final int DEFAULT_CONCURRENT = 10;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    public static final int DEFAULT_PREFETCH_COUNT = 50;


    @Bean
    public Queue preSendQueue() {
        return new Queue(RabbitMqCommons.TOPIC_PRE_SEND, true);
    }

    @Bean
    public Queue smsLogQueue() {
        return new Queue(RabbitMqCommons.TOPIC_SEND_LOG, true);
    }

    @Bean
    public Queue pushSmsReportQueue() {
        return new Queue(RabbitMqCommons.TOPIC_PUSH_REPORT, true);
    }

    /**
     * RabbitAdmin类封装了对RabbitMQ管理端的操作
     * 比如Exchange操作,Queue操作,Binding操作
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
        factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
