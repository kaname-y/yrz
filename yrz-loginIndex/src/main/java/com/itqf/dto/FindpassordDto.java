package com.itqf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/18  3:54 下午
 * 描述:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindpassordDto {
    public String findway; //用户发过来的找回方式， 直接接收字符串，可以是邮箱也可以是手机号，后面校验
    public String checknum; //手机验证码
    public String upassword;

}
