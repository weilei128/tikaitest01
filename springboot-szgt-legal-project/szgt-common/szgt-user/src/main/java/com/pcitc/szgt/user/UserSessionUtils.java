package com.pcitc.szgt.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/***
 *  获取用户信息工具类
 *
 */
public class UserSessionUtils {

    public static SecurityContext getSecurityContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }

    public static OAuth2Authentication getOAuth2Authentication() {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) getSecurityContext().getAuthentication();
        return auth2Authentication;
    }

    public static Authentication getUserAuthentication() {
        return getOAuth2Authentication().getUserAuthentication();
    }

    public static String getUserSession() {
        return (String) getUserAuthentication().getDetails();
    }


}
