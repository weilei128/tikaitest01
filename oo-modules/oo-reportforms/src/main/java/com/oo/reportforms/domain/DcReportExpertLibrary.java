package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 技术岗-专家库
 *
 * @author oo
 * @date 2023-09-18
 */
@Data
@TableName("dc_report_expert_library")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "技术岗-专家库对象", description = "技术岗-专家库表")
public class DcReportExpertLibrary extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id")
    private String id;
    
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String name;
    
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @Excel(name = "单位")
    private String unit;
    
    /**
     * 技术职称
     */
    @ApiModelProperty(value = "技术职称")
    @Excel(name = "技术职称")
    private String technicalTitle;
    
    /**
     * 专家等级
     */
    @ApiModelProperty(value = "专家等级")
    @Excel(name = "专家等级")
    private String expertLevel;
    
    /**
     * 监督/工程师等级
     */
    @ApiModelProperty(value = "监督/工程师等级")
    @Excel(name = "监督/工程师等级")
    private String engineerLevel;
    
    /**
     * 专业特长
     */
    @ApiModelProperty(value = "专业特长")
    @Excel(name = "专业特长")
    private String speciality;
    
    /**
     * 是否退休 关联字典表
     */
    @ApiModelProperty(value = "是否退休 关联字典表")
    @Excel(name = "是否退休")
    @DictField(dictType = "if_retire")
    private String ifRetire;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}