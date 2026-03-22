package com.oo.common.core.annotation;

import java.lang.annotation.*;

/**
 * 字典绑定字段注解
 *
 * @author
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictField {

    /**
     * 字典类型
     */
    public String dictType() default "";
}
