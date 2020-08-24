package com.itqf.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int uid;

    private String uname;

    private String upassword;

    private Date udate;

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