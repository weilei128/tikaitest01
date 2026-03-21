package com.pcitc.szgt.contract.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = "maindata")
public class MainDataConfig {
    private String url;
    private String modelId;
    private String sysCode;
    private String lastfile;
    private String inModelId;
    private String inSysCode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getLastfile() {
        return lastfile;
    }

    public void setLastfile(String lastfile) {
        this.lastfile = lastfile;
    }

    public String getInModelId() {
        return inModelId;
    }

    public void setInModelId(String inModelId) {
        this.inModelId = inModelId;
    }

    public String getInSysCode() {
        return inSysCode;
    }

    public void setInSysCode(String inSysCode) {
        this.inSysCode = inSysCode;
    }

    public boolean check(){
        return !StringUtils.isEmpty(url) &&
                !StringUtils.isEmpty(modelId) &&
                !StringUtils.isEmpty(sysCode) &&
                !StringUtils.isEmpty(inModelId) &&
                !StringUtils.isEmpty(inSysCode);
    }

}

