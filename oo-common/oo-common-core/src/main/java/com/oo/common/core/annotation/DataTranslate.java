package com.oo.common.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段翻译注解
 *
 * @author
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataTranslate
{

    /**
     * 翻译字段id
     */
    public String fieldId() default "";

    /**
     * 分类
     */
    public String category() default "";
}
