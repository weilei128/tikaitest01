package com.oo.common.security.feign;

import feign.Feign;
import feign.querymap.BeanQueryMapEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;

/**
 * Feign 配置注册
 *
 * @author ruoyi
 **/
@Configuration
public class FeignAutoConfiguration
{
    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return new FeignRequestInterceptor();
    }

//    /**
//     * 替换解析 queryMap 的类，实现父类中变量的映射
//     * @return
//     */
//    @Bean
//    public Feign.Builder feignBuilder() {
//        return Feign.builder()
//                .queryMapEncoder(new BeanQueryMapEncoder());
//    }
}
