package com.oo.common.core.annotation;

import java.lang.annotation.*;

/**
 * 标记此注解时翻译PO对象
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodTranslate {

    /**
     * 翻译的DTO类
     */
    Class<?> dtoClass();
}
