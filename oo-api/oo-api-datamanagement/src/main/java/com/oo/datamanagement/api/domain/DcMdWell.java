package com.oo.datamanagement.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 单井基础信息
 *
 * @author oo
 * @date 2023-08-24
 */
@Data
@TableName("dc_md_well")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "单井基础信息对象", description = "单井基础信息表")
public class DcMdWell extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一id")
    private String id;

    /**
     * 井名
     */
    @ApiModelProperty(value = "井名")
    @Excel(name = "井名")
    private String wellname;

    /**
     * 中文井名
     */
    @ApiModelProperty(value = "中文井名")
    @Excel(name = "中文井名")
    private String chinesewellname;

    /**
     * 所属组织机构
     */
    @ApiModelProperty(value = "所属组织机构")
    @Excel(name = "所属组织机构")
    private String organizationId;

    /**
     * 所属项目
     */
    @ApiModelProperty(value = "所属项目")
    @Excel(name = "所属项目")
    private String projectId;

    /**
     * 是否作业者 1 true 0 false
     */
    @ApiModelProperty(value = "是否作业者 1 true 0 false")
    @Excel(name = "是否作业者 1 true 0 false")
    @DictField(dictType = "if_or_no")
    private String ifwork;

    /**
     * 一级井别明文
     */
    @ApiModelProperty(value = "一级井别明文")
    @Excel(name = "一级井别明文")
    @DictField(dictType = "well_type")
    private String welltype;

    /**
     * 二级井别明文
     */
    @ApiModelProperty(value = "二级井别明文")
    @Excel(name = "二级井别明文")
    @DictField(dictType = "wellsubtype")
    private String wellsubtype;

    /**
     * 三级井别明文
     */
    @ApiModelProperty(value = "三级井别明文")
    @Excel(name = "三级井别明文")
    @DictField(dictType = "usertxt6")
    private String usertxt6;

    /**
     * 井型明文
     */
    @ApiModelProperty(value = "井型明文")
    @Excel(name = "井型明文")
    @DictField(dictType = "dc_pk_well_type")
    private String type;

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
     * 投产日期
     */
    @ApiModelProperty(value = "投产日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prodDate;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Excel(name = "排序号")
    private Integer orderNum;
}
