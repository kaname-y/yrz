package com.itqf.common;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  1:33 下午
 * 描述:
 */
public class RabbitMqCommons {

    public final static String TOPIC_PRE_SEND = "yrz_pre__topic";

    //下发日志TOPIC
    public final static String TOPIC_SEND_LOG = "yrz_send_log_topic";

    //推送状态报告TOPIC
    public final static String TOPIC_PUSH_REPORT = "yrz_push_report_topic";

    //状态报告更新TOPIC
    public final static String TOPIC_UPDATE_REPORT = "yrz_report_update_topic";

//    //待发送短信网关队列 + 网关ID号
//    public final static String TOPIC_SMS_GATEWAY = "lcsd_sms_send_gateway";


}
