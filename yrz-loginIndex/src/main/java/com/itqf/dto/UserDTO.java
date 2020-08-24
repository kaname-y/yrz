package com.itqf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/17  10:51 下午
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String captcha;
    private boolean rememberMe;
}
