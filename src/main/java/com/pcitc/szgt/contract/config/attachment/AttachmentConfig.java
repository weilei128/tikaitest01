package com.pcitc.szgt.contract.config.attachment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "filestorage")
public class AttachmentConfig {
    private String host;

    private String port;

    private String ip;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    // 域名地址
    @Override
    public String toString() {
        return host + ":" + port + context;
    }

    // ip地址
    public String toIpString(){
        return ip + ":" + port + context;
    }


}
