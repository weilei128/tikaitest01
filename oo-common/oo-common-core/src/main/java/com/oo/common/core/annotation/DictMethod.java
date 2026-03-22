package com.oo.common.core.annotation;

import java.lang.annotation.*;

/**
 * 字典自动添加注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictMethod {

    /**
     * 使用字典的实体类
     */
    Class<?> dtoClass();
}
