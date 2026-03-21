package com.pcitc.szgt.contract.config.oauthconfig;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WrapUser extends org.springframework.security.core.userdetails.User {

    private UserInfo userInfo;

    public WrapUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public WrapUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
