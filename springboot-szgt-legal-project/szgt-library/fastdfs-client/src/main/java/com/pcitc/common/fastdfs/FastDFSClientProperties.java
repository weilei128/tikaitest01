package com.pcitc.common.fastdfs;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

import static org.csource.fastdfs.ClientGlobal.*;

@Data
@ToString
@ConfigurationProperties(prefix = "fastdfs")
public class FastDFSClientProperties {

    private String connectTimeoutInSeconds = "";
    private String networkTimeoutInSeconds = "";
    private String charset = "";
    private String httpAntiStealToken = "";
    private String httpSecretKey = "";
    private String httpTrackerHttpPort = "";
    private String trackerServers = "";


    public Properties generateProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, this.connectTimeoutInSeconds);
        properties.setProperty(PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, this.networkTimeoutInSeconds);
        properties.setProperty(PROP_KEY_CHARSET, this.charset);
        properties.setProperty(PROP_KEY_HTTP_ANTI_STEAL_TOKEN, this.httpAntiStealToken);
        properties.setProperty(PROP_KEY_HTTP_SECRET_KEY, this.httpSecretKey);
        properties.setProperty(PROP_KEY_HTTP_TRACKER_HTTP_PORT, this.httpTrackerHttpPort);
        properties.setProperty(PROP_KEY_TRACKER_SERVERS, this.trackerServers);
        return properties;
    }

}
