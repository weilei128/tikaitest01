package com.oo.reportforms.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CountryByWellVo {
    /**
     * 国家id
     */
    @ApiModelProperty(value = "国家id")
    private String countryId;

    /**
     * 井名
     */
    @ApiModelProperty(value = "井名")
    private String countryName;


}
