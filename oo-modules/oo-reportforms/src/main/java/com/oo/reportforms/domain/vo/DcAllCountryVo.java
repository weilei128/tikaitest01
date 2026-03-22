package com.oo.reportforms.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DcAllCountryVo {
    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private String id;

    /**
     * 井名
     */
    @ApiModelProperty(value = "项目简称")
    private String name;

//    /**
//     * 中文井名
//     */
//    @ApiModelProperty(value = "项目全称")
//    private String nameDetail;
}
