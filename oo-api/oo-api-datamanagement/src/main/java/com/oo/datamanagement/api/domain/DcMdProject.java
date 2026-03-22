package com.oo.datamanagement.api.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.web.domain.TranslateVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 地质单元--区块管理
 *
 * @author oo
 * @date 2023-08-24
 */
@Data
@TableName("dc_md_project")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "地质单元--区块管理对象", description = "地质单元--区块管理表")
public class DcMdProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 项目简称
     */
    @ApiModelProperty(value = "项目简称")
    @Excel(name = "项目简称")
    private String name;

    /**
     * 项目全称
     */
    @ApiModelProperty(value = "项目全称")
    @Excel(name = "项目全称")
    private String nameDetail;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @Excel(name = "父级ID")
    private String parentId;

    /**
     * 项目类型 4：油田 5：区块 6：井场
     */
    @ApiModelProperty(value = "项目类型 4：油田 5：区块 6：井场")
    @Excel(name = "项目类型 4：油田 5：区块 6：井场")
    @DictField(dictType = "base_field_type")
    private String type;

    /**
     * 所属的组织机构
     */
    @ApiModelProperty(value = "所属的组织机构")
    @Excel(name = "所属的组织机构")
    private String organizationId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Excel(name = "排序号")
    private Integer orderNum;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /**
     * 空间属性 存储的是WKT格式的空间描述信息 如：点 POINT(0,5)  线 LINESTRING(0 0,10 10,20 25,50 60)
     面 POLYGON((0 0,10 0 10 10,0 10,0 0),(5 5,7 5,7 7,5 7,5 5))
     */
    @ApiModelProperty(value = "空间属性 存储的是WKT格式的空间描述信息 如：点 POINT(0,5)  线 LINESTRING(0 0,10 10,20 25,50 60) 面 POLYGON((0 0,10 0 10 10,0 10,0 0),(5 5,7 5,7 7,5 7,5 5))")
    @Excel(name = "空间属性 存储的是WKT格式的空间描述信息 如：点 POINT(0,5)  线 LINESTRING(0 0,10 10,20 25,50 60) 面 POLYGON((0 0,10 0 10 10,0 10,0 0),(5 5,7 5,7 7,5 7,5 5))")
    private String geomWkt;

    /**
     * 空间属性类型 0：点； 1：线； 2：面 ；3：其他
     */
    @ApiModelProperty(value = "空间属性类型 0：点； 1：线； 2：面 ；3：其他")
    @Excel(name = "空间属性类型 0：点； 1：线； 2：面 ；3：其他")
    private Integer geomType;

    /** 翻译菜单名称数组 */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();
}
