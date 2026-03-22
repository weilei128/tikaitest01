package com.oo.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.security.auth.login.Configuration;
import java.lang.annotation.*;

/**
 * 自定义feign注解
 * 添加basePackages路径
 * 
 * @author ruoyi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients //(defaultConfiguration = FeignConfiguration.class)
public @interface EnableRyFeignClients
{
    String[] value() default {};

    String[] basePackages() default { "com.oo" };

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
