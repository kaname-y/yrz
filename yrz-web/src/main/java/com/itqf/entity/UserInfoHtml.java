package com.itqf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/21  5:26 下午
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoHtml implements Serializable {

    private int uid;

    private String uname;

    @JsonProperty("ubirthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ubirthday;

    private String uphone;

    private String uemail;

    private int usex;

    private String province;
    private String city;
    private String area;

    private String phonecheck;
    private String emailcheck;

    private static final long serialVersionUID = 1L;

}
