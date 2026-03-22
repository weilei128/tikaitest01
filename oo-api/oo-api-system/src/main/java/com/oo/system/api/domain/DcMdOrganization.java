package com.oo.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.web.domain.TranslateVO;
import com.oo.system.api.vo.DcMdWellVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 组织机构层级关系--项目管理
 *
 * @author oo
 * @date 2023-08-24
 */
@Data
@TableName("dc_md_organization")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织机构层级关系--项目管理对象", description = "组织机构层级关系--项目管理表")
public class DcMdOrganization extends BaseEntity {

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
     * 项目类型 1：公司 2：国家 3：项目
     */
    @ApiModelProperty(value = "项目类型 1：公司 2：国家 3：项目")
    @Excel(name = "项目类型 1：公司 2：国家 3：项目")
    @DictField(dictType = "base_organization_type")
    private String type;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Excel(name = "排序号")
    private Integer orderNum;

    /**
     * 是否是重点国家项目 关联字典表
     */
    @ApiModelProperty(value = "是否是重点国家项目")
    @Excel(name = "是否是重点国家项目")
    @DictField(dictType = "if_or_no")
    private String ifFocus;


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
    /**
     * 子集
     */
    @TableField(exist = false)
    private List<DcMdWellVo> children = new ArrayList<>();

    public void addChild(DcMdWellVo child) {
        this.children.add(child);
    }

    /** 翻译菜单名称数组 */
    @TableField(exist = false)
    private List<TranslateVO> transList = new ArrayList<TranslateVO>();
}
