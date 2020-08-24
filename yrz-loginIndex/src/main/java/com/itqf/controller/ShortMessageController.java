package com.itqf.controller;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/13  5:10 下午
 * 描述:
 */
@RestController
@RequestMapping("/shortmsg")
public class ShortMessageController {

    @Value("${yunpian.apikey}")
    private String apikey;

    @RequestMapping("/findpw")
    public boolean findpassword(String findway, HttpSession session) {
        System.out.println("findway = " + findway);
        String checkNum = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        String text = "【梁家宝】您的验证码是" + checkNum + "。";

        YunpianClient clnt = new YunpianClient(apikey).init();

        //发送短信API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, findway);
        param.put(YunpianClient.TEXT, text);
//        param.put(YunpianClient.TEXT, "【梁家宝】您的验证码是8888。");

        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),
        // 其他说明:r.getDetail(),调用异常:r.getThrowable()

        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().*
        // 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

        //释放clnt
        clnt.close();
        System.out.println("r = " + r);

        if (r.getCode() == 0) {
            session.setAttribute(findway, checkNum);
            session.setMaxInactiveInterval(60 * 10);
            return true;
        } else {
            return false;
        }

    }
}
