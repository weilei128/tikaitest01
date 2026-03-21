package com.pctic.common.utils;


import java.util.UUID;

/***
 * @description 工具类
 * @author leigang
 * @date 2020年2月20日 09:57:03
 *
 */
public class UUIDUtils {

    public static String getUUID() {
        return getUUID("");
    }

    public static String getUUID(String replacement) {
        return UUID.randomUUID().toString().replaceAll("-", replacement);
    }


}
