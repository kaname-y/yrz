package com.itqf.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/13  1:21 下午
 * 描述:
 */
public class SendMail {
    public static void sendForMail(String title, String msg,
                                   String fromSendHost, String fromSend, String fromSendPassword,
                                   String toUser) throws GeneralSecurityException, MessagingException {
        // 发送邮件
        // 1.设置邮箱相关
        Properties properties = new Properties();
        properties.put("mail.host", "smtp.qq.com");// 设置邮箱的地址
        properties.put("mail.transport.protocol", "smtp");// 设置邮件协议
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置开启ssl 安全套接字
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();// https支持
        mailSSLSocketFactory.setTrustAllHosts(true);// 信任主机
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);// 设置用于检查ssl证书的工厂对象

        // 2.创建会话
//        Session session = Session.getDefaultInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("1172176880@qq.com", "qiwswxywwvldgddd");// 设置账户随机码
//            }
//        });
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromSend, fromSendPassword);// 设置账户随机码
            }
        });
        session.setDebug(true);
        // 3.创建邮件内容
        Transport transport = session.getTransport();//  连接
        transport.connect(fromSendHost, fromSend, fromSendPassword);

//        transport.connect("smtp.qq.com", "1172176880@qq.com", "vrfctbmmqplkfejb");
        Message message = new MimeMessage(session);// 创建了消息对象,也就是邮件内容对象
        //设置发件人
        message.setFrom(new InternetAddress(fromSend));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
        //设置标题
        message.setSubject(title);
        //设置内容
        message.setContent(msg, "text/html;charset=utf-8");
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        //关闭链接
        transport.close();
    }
}