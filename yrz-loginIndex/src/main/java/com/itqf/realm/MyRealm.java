package com.itqf.realm;

import com.itqf.entity.User;
import com.itqf.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  10:06 下午
 * 描述:
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        return null;
    }

    //认证

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //得到用户名和密码
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        String username = usernamePasswordToken.getUsername();
//        System.out.println("username = " + username);
//        String password = new String(usernamePasswordToken.getPassword());
//        System.out.println("password = " + password);
//
//        User user = userService.searchUname(username);
//        System.out.println("user = " + user);
//        if (user == null) {
//            throw new UnknownAccountException("账户不存在");
//        }
//        if (!password.equals(user.getUpassword())) {
//            throw new UnknownAccountException("密码错误");
//        }
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getUpassword(), this.getName());
//        return simpleAuthenticationInfo;


        String uname = (String) token.getPrincipal();
        System.out.println("uname = " + uname);

        User user = userService.searchUname(uname);
        System.out.println("user ======== " + user);
        if (user == null) {
            return null;
        } else {
            //能查到
            String usalt = user.getUsalt();
            System.out.println("usalt ======= " + usalt);

            ByteSource salt = ByteSource.Util.bytes(usalt);
            System.out.println("salt 111= " + salt);

            SimpleAuthenticationInfo myRealm = new SimpleAuthenticationInfo(user,user.getUpassword(), salt, this.getName());
            return myRealm;

        }
    }
}
