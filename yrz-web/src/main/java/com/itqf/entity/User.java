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
 * 时间: 2020/8/16  2:22 下午
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int uid;

    private String uname;

    private String upassword;

    @JsonProperty("udate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date udate;

    @JsonProperty("ubirthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ubirthday;

    private String uphone;

    private String uemail;

    private int ustatue;

    private String uimg;

    private int umoney;

    private String usalt;

    private int usex;

    private String uarea;


    private static final long serialVersionUID = 1L;
}