package com.itqf.controller;

import com.alibaba.fastjson.JSON;
import com.itqf.common.Commons;
import com.itqf.dto.FindpassordDto;
import com.itqf.entity.User;
import com.itqf.feign.CacheService;
import com.itqf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  5:57 下午
 * 描述:
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @ResponseBody
    @RequestMapping("/test/{key}/{hashkey}")
    public Object hhh(@PathVariable("key") String key,
                      @PathVariable("hashkey") String hashkey) {
        Object hget = cacheService.hget(key, hashkey);

        String s = JSON.toJSONString(hget);
        System.out.println("hget.toString() = " + s);
        return s;
    }

    /*
    这里是去数据库中查询用户名是否存在， 用户名唯一
     */
    @ResponseBody
    @RequestMapping("/search/{uname}")
    public int searchUser(@PathVariable("uname") String uname) {
        User user = userService.searchUname(uname);
        System.out.println("user = " + user);
        if (user != null) {
            //不能注册，已存在
            return Commons.hasuser;
        } else {
            //可以注册
            return Commons.nouser;
        }

    }

    @ResponseBody
    @RequestMapping("/searchuname")
    public int searchUsername(@RequestParam("uname") String uname) {
        System.out.println("uname = " + uname);
        String[] split = uname.split(",");
        String unamee = split[1];
        System.out.println("unamee = " + unamee);
        User user = userService.searchUname(unamee);
        System.out.println("user = " + user);
        if (user != null) {
            //不能注册，已存在
            return Commons.hasuser;
        } else {
            //可以注册
            return Commons.nouser;
        }

    }

    @ResponseBody
    @RequestMapping("/regeister")
    public int regeistUser(User user) {
        System.out.println("user = " + user);

        //查询是否存在
//        User user1 = userService.searchUname(user.getUname());
//        System.out.println("user1 = " + user1);
//        if (user1 != null) {
//            return Commons.hasuser;
//        } else {
        int i = userService.regeistUser(user);
        System.out.println("i = " + i);
        if (i > 0) {
            //注册成功

            //获取自增后的主键，由缓存模块加到redis里面
            int uid = user.getUid();
            System.out.println("uid = " + uid);
            boolean b = cacheService.hsetuser(user);
            System.out.println("b = " + b);
            return Commons.regeistsuccess;
        } else {
            return Commons.regeistfalse;
        }

    }


    /*
    登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public int userLogin(User user) {

        System.out.println("user = " + user);

        Subject subject = SecurityUtils.getSubject();

//        Md5Hash md5Hash = new Md5Hash(user.getUpassword(), user.getUname(), 1024);
//        String newpassword = md5Hash.toString();
//        System.out.println("newpassword = " + newpassword);
//        user.setUpassword(newpassword);


        UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpassword());

        try {
            subject.login(token);

            User loginUser = (User) subject.getPrincipal();
            System.out.println("loginUser = " + loginUser);

//            session.setAttribute("user", loginUser);

            //这里用户登录成功后，把用户的信息发送到mq里面， 由web项目监听，获取用户信息
//            rabbitTemplate.convertAndSend(RabbitMqCommons.TOPIC_PRE_SEND, loginUser);


            //用户登录成功后，把用户id存储一个标记在redis里面
            boolean b = cacheService.hsetLogin(user.getUname());
            if (b) {
                return Commons.loginsuccess;
            } else {
                return Commons.loginfalse;
            }


        } catch (Exception e) {
            return Commons.loginfalse;
        }

    }


    /*
    找回密码
     */
    @ResponseBody
    @RequestMapping("/findpassword")
    public int findPassword(FindpassordDto findpassordDto, HttpSession session) {
        System.out.println("findpassordDto = " + findpassordDto);

        //先根据手机号查询用户，去缓存中查，
        // 由于在设计时手机号不是主键，缓存中是以id做主键设计的，
        // 这里先用手机号去数据库中查询，查到id，在去缓存中查询用户信息，单纯就是为了练习才这么做


        String findway = findpassordDto.getFindway();

        if (findway.contains("@")) {
            //邮箱修改

            //这里查找之前是否用此验证方式发过验证码
            String checkNumBefore = (String) session.getAttribute(findway);
            System.out.println("checkNumBefore = " + checkNumBefore);
            if (checkNumBefore == null) {
                //验证码没有查到， 可能到期，可能没用此邮箱发送
                return Commons.differentFindWay;
            } else {


                //在这里改密码
                String checkNum = findpassordDto.getChecknum();
                if (checkNumBefore != null && checkNumBefore.equals(checkNum)) {
                    int uid = userService.searchuUserByEmail(findway);
                    System.out.println("uid = " + uid);
                    if (uid == 0) {
                        //在数据库中没有查询到绑定改手机号的用户
                        return Commons.nothisPoneUser;
                    } else {
                        int i = changeCacheAndDB(uid, findpassordDto);
                        if (i > 0) {

                            //删除存在共享域中的 以手机号为名的 验证码
                            session.removeAttribute(findway);
                            return Commons.findpassworddsuccess;
                        } else {
                            return Commons.findpasswordfalse;
                        }
                    }
                } else {
                    return Commons.differentFindWay;
                }


            }


        } else {
            //手机号修改， 调用手机号的api发验证码

            String uphone = findpassordDto.getFindway();
            String checkNumBefore = (String) session.getAttribute(uphone);
            if (checkNumBefore == null) {
                return Commons.differentFindWay;
            } else {
                String checknum = findpassordDto.getChecknum();
                if (checkNumBefore.equalsIgnoreCase(checknum)) {

                    int uid = userService.searchUserByPhone(uphone);
                    System.out.println("uid = " + uid);

                    if (uid == 0) {
                        //在数据库中没有查询到绑定改手机号的用户
                        return Commons.nothisPoneUser;
                    } else {

                        int i = changeCacheAndDB(uid, findpassordDto);
                        if (i > 0) {
                            session.removeAttribute(findway);
                            return Commons.findpassworddsuccess;
                        } else {
                            return Commons.findpasswordfalse;
                        }
                    }
                } else {

                    return Commons.differentFindWay;
                }
            }


        }


    }


    /*
    两种方式修改密码，都要修改缓存
     */
    public int changeCacheAndDB(int uid, FindpassordDto findpassordDto) {
        //先修改数据库，再改缓存
        //查到用户id后去缓存中查询用户信息,这里直接修改了用户的密码

        String uname = (String) cacheService.hget(String.valueOf(uid), "uname");
        System.out.println("uname = " + uname);

        String upassword = findpassordDto.getUpassword();//用户输入的密码
        String newpassword = userService.exchangePassword(upassword, uname);//加密后新密码
        System.out.println("newpassword = " + newpassword);

        //修改数据库
        int i = userService.changePasswordInDB(uid, newpassword);
        if (i > 0) {
            //数据库修改成功，再改缓存

            HashMap<String, Object> map = new HashMap<>();
            map.put("upassword", newpassword);
            boolean b = cacheService.hashKeyValueset(String.valueOf(uid), map);
            System.out.println("b = " + b);
            if (b) {
                //都成功才是修改成功
                return Commons.findpassworddsuccess;
            } else {
                return Commons.findpasswordfalse;
            }
        } else {
            return Commons.findpasswordfalse;
        }

    }


}
