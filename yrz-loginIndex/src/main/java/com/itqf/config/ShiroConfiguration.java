package com.itqf.config;

import com.itqf.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  10:15 下午
 * 描述:
 */
@Configuration
public class ShiroConfiguration {


//    @Bean
//    public SimpleCookie simpleCookie() {
//        SimpleCookie cookie = new SimpleCookie("rememberMe");
//        cookie.setMaxAge(60 * 10);//单位秒
//        return cookie;
//
//    }

//    @Bean
//    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie) {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(simpleCookie);
//        return cookieRememberMeManager;
//    }
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(CookieRememberMeManager cookieRememberMeManager) {
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//
//        defaultWebSecurityManager.setRealm(createRealm());
//
//        //设置记住我
//        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
//        return defaultWebSecurityManager;
//    }


    //1,创建 SessionManager 管理会话
    @Bean(name = "sessionManager")//<bean class="">
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置过期时间
        sessionManager.setGlobalSessionTimeout(1000 * 60 * 30);
        //设置后台线程  清理过期的会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置地址比拼接sessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    //2,创建SecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(SessionManager sessionManager, MyRealm myRealm) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);

        //cookie管理
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        Cookie cookie = cookieRememberMeManager.getCookie();
        cookie.setMaxAge(60 * 10);
        cookie.setPath("/");

        securityManager.setRememberMeManager(cookieRememberMeManager);

        //设置自定义realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public MyRealm createRealm() {
        MyRealm myRealm = new MyRealm();

        //设置加密方式和次数
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("Md5");
        matcher.setHashIterations(1024);

        myRealm.setCredentialsMatcher(matcher);
        return myRealm;


    }




    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {

        //创建一个过滤链
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        chain.addPathDefinition("/404", "anon");
        chain.addPathDefinition("logout", "logout");
        chain.addPathDefinition("/user/show", "authc");


        chain.addPathDefinition("/**", "anon");
        return chain;
    }


//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean ();
//        shiroFilterFactoryBean.setSecurityManager (securityManager);
//        return shiroFilterFactoryBean;
//
//    }

//    @Bean // ShiroFilter
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        return shiroFilter;
//    }


}
