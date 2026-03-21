package com.pctic.common.utils;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.szgt.user.UserSessionUtils;

/***
 * @description 当前登录用户信息获取
 * @author leigang
 * @date 2020年4月17日 10:25:15
 *
 */
public class UserUtils {

    public static SysUserInfo getUserInfo() {
        String userSession = UserSessionUtils.getUserSession();
        return JSON.parseObject(userSession, SysUserInfo.class);
    }

}
