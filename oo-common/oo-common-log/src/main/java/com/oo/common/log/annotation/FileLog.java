package com.oo.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.log.enums.FileOperType;
import com.oo.common.log.enums.OperatorType;

/**
 * 文件操作日志记录注解
 *
 * @author ruoyi
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileLog
{
    /**
     * 操作类型
     */
    public FileOperType fileOperType() default FileOperType.OTHER;

    /**
     * 参数索引（文件id参数的索引位置）
     */
    public int argIndex() default 0;
}
