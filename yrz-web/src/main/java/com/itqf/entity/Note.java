package com.itqf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: 梁家宝
 * 项目名: PersonalUse
 * 时间: 2020/8/16  3:43 上午
 * 描述:  文章实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    private int nid;

    private String nname;

    private int nstatue;

    private String nimg;

    private String ntext;

    private String nsrc;
}
