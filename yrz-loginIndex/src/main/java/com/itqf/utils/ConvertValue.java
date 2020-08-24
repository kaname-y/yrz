package com.itqf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 作者: 梁家宝
 * 项目名: regeist-login-forget
 * 时间: 2020/7/20  12:27 上午
 * 描述:
 */
public class ConvertValue {

    public static <T> T convertValue(Object bean, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(bean, clazz);
        } catch (Exception e) {
//            log.error("错误的转换：BeanUtil.convertValue() --->" + e.getMessage());
            return null;
        }
    }

}