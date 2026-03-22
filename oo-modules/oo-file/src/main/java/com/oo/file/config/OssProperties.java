package com.oo.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Oss配置信息
 *
 * @author oo
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    private Boolean enabled;

    private String name;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * 区域
     */
    private String region;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

    /**
     * 桶名
     */
    private String bucketName = "test";

    /**
     * 最大线程数，默认： 100
     */
    private Integer maxConnections = 100;

    /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle模式 {http://endpoint/bucketname}
     * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式{http://bucketname.endpoint}
     * 只是url的显示不一样
     */
    private Boolean pathStyleAccess = true;

//    public OssProperties() {
//    }
//
//    public Boolean getEnabled() {
//        return this.enabled;
//    }
//
//    public String getName() { return this.name; }
//
//    public String getEndpoint() {
//        return this.endpoint;
//    }
//
//    public String getRegion() {
//        return this.region;
//    }
//
//    public String getAccessKey() {
//        return this.accessKey;
//    }
//
//    public String getSecretKey() {
//        return this.secretKey;
//    }
//
//    public String getBucketName() {
//        return this.bucketName;
//    }
//
//    public void setEnabled(final Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public void setName(final String name) {
//        this.name = name;
//    }
//
//    public void setEndpoint(final String endpoint) {
//        this.endpoint = endpoint;
//    }
//
//    public void setRegion(final String region) {
//        this.region = region;
//    }
//
//    public void setAccessKey(final String accessKey) {
//        this.accessKey = accessKey;
//    }
//
//    public void setSecretKey(final String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    public void setBucketName(final String bucketName) {
//        this.bucketName = bucketName;
//    }
}
