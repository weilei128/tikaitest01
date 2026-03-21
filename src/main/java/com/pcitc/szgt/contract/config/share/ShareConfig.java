package com.pcitc.szgt.contract.config.share;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "share")
public class ShareConfig {
    private String host;

    private String port;

    private String context;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return host + ":" + port + context;
    }
}
