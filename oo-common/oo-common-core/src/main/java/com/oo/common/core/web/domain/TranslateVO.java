package com.oo.common.core.web.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TranslateVO {
    private static final long serialVersionUID = 1L;

    /**
     * 字段id
     */
    @ApiModelProperty(value = "字段id")
    private Long fieldId;

    /**
     * 语言标识
     */
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
