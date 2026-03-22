package com.oo.system.api.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * 多语言配置
 *
 * @author oo
 * @date 2023-08-09
 */
@Data
@TableName("sys_translate")
@ApiModel(value = "多语言配置对象", description = "多语言配置表")
public class SysTranslate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 字段id
     */
    @MppMultiId
    @ApiModelProperty(value = "字段id")
    private Long fieldId;
    
    /**
     * 语言标识
     */
    @MppMultiId
    @ApiModelProperty(value = "语言标识")
    private String lang;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String category;
    
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
}