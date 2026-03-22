package com.oo.datamanagement.domain.vo;

import com.oo.datamanagement.api.domain.DcMdWell;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel(value = "单井信息vo", description = "单井信息vo")
public class MdWellVo extends DcMdWell {
    @ApiModelProperty(value = "组织机构名称")
    private String organizationName;

    @ApiModelProperty(value = "区块名称")
    private String projectName;

    @ApiModelProperty(value = "是否作业者Id")
    private String ifworkId;

    @ApiModelProperty(value = "一级井别Id")
    private String welltypeId;

    @ApiModelProperty(value = "二级井别Id")
    private String wellsubtypeId;

    @ApiModelProperty(value = "三级井别Id")
    private String usertxt6Id;

    @ApiModelProperty(value = "井型Id")
    private String typeId;

    @ApiModelProperty(value = "是否作业者")
    private String ifworkName;

    @ApiModelProperty(value = "一级井别")
    private String welltypeName;

    @ApiModelProperty(value = "二级井别")
    private String wellsubtypeName;

    @ApiModelProperty(value = "三级井别")
    private String usertxt6Name;

    @ApiModelProperty(value = "井型")
    private String typeName;

    @ApiModelProperty(value = "井项目所属国家id")
    private String companyId;

    @ApiModelProperty(value = "井项目所属国家id")
    private String companyName;


}
