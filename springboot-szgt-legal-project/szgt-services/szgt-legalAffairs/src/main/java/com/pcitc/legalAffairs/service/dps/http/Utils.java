package com.pcitc.legalAffairs.service.dps.http;

import org.springframework.util.StringUtils;

/***
 * @description 工具类
 * @author leigang
 * @date 2020年4月10日 14:34:19
 *
 */
public class Utils {

    public static String getHandlerStr(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        String[] split = content.split("_");
        return split[split.length - 1];
    }

}
