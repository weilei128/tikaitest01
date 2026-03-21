package com.pcitc.szgt.contract.config.pageoffice;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pageoffice")
public class PageOfficeConfig {
    private String context;
    private String opencontext;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOpencontext() {
        return opencontext;
    }

    public void setOpencontext(String opencontext) {
        this.opencontext = opencontext;
    }
}
