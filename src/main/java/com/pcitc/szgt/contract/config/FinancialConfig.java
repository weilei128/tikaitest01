package com.pcitc.szgt.contract.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = "financial")
public class FinancialConfig {
    private String sysCode;
    private String key;
    private String url;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean check(){
        return !StringUtils.isEmpty(sysCode) && !StringUtils.isEmpty(key) && !StringUtils.isEmpty(url);
    }
}
