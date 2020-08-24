package com.itqf.controller;

import com.itqf.utils.SendMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.util.UUID;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/13  1:38 下午
 * 描述:
 */
@RequestMapping("/mail")
@RestController
public class MailController {

    @Value("${sendmail.fromSend}")
    private String fromSend;
    @Value("${sendmail.fromSendPassword}")
    private String fromSendPassword;

    @Value("${sendmail.fromSendHost}")
    private String fromSendHost;

    /*
    index首页邮箱找回密码
     */
    @RequestMapping("/findpw")
    public boolean findpassword(String findway,
                                HttpSession session) throws GeneralSecurityException, MessagingException {
        System.out.println("findway = " + findway);

        String checkNum = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);


//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(findway, checkNum);
//        System.out.println("map = " + map);
        session.setAttribute(findway, checkNum);

        session.setMaxInactiveInterval(60 * 10);


        String title = "找回密码";
        String msg = "当前验证码是： " + checkNum + " ，10分钟内有效。";
//        String fromSendHost = "smtp.qq.com";
        System.out.println("fromSend = =--=-=-==--=-=-=-==-=-" + fromSend);
        System.out.println("fromSendPassword = -=-=-=-=-=-=-=-=-=-" + fromSendPassword);
        SendMail.sendForMail(title, msg, fromSendHost, fromSend, fromSendPassword, findway);
        return true;
    }
}
