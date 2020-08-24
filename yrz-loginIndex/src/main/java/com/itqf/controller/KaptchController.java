package com.itqf.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  7:01 下午
 * 描述:
 */
@Controller
public class KaptchController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    int code = 0;


    /*
    生成验证码
     */
    @RequestMapping("createCode")
    public void createCode(HttpSession session, HttpServletResponse response) throws IOException {
        response.setDateHeader ("Expires", 0);
        response.setHeader ("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader ("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader ("Pragma", "no-cache");
        response.setContentType ("image/jpeg");
        //生成验证码
        String capText = defaultKaptcha.createText ();
        session.setAttribute ("code", capText);
        //向客户端写出
        BufferedImage bi = defaultKaptcha.createImage (capText);
        ServletOutputStream out = response.getOutputStream ();
        ImageIO.write (bi, "jpg", out);
        try {
            out.flush ();
        } finally {
            out.close ();
        }
    }


    /*
   判断验证码
    */
    @ResponseBody
    @RequestMapping("testCode")
    public int testCode(String checkCode, HttpSession session) {
        System.out.println ("checkCode = " + checkCode);

        // 获取真实 的验证码
        String cap = (String) session.getAttribute ("code");
        System.out.println ("cap = " + cap);
        if (cap.equalsIgnoreCase (checkCode)) {
            code = 1;
            return code;
        } else {
            code = 0;
            return code;
        }
    }

}
