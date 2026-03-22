package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 滚动预测10+2
 *
 * @author oo
 * @date 2023-10-31
 */
@Data
@TableName("dc_report_rolling_bugdet_tenmon")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "滚动预测10+2对象", description = "滚动预测10+2表")
public class DcReportRollingBugdetTenmon extends LogicEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    //@Excel(name = "项目ID")
    private String organizationId;
    
    /**
     * 井型
     */
    @ApiModelProperty(value = "井型")
    @Excel(name = "井型")
    @DictField(dictType = "dc_pk_well_type")
    private String type;
    
    /**
     * 申报名称
     */
    @ApiModelProperty(value = "申报名称")
    @Excel(name = "申报名称")
    private String declareName;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;
    
    /**
     * 预算科目 关联字典表
     */
    @ApiModelProperty(value = "预算科目 关联字典表")
    @Excel(name = "预算科目 关联字典表")
    @DictField(dictType = "well_config")
    private String budgetSubjects;
    
    /**
     * 年度预算
     */
    @ApiModelProperty(value = "年度预算")
    @Excel(name = "年度预算")
    private BigDecimal forecastsYear;
    
    /**
     * 滚动预测（预算）
     */
    @ApiModelProperty(value = "滚动预测（预算）")
    @Excel(name = "滚动预测")
    private BigDecimal forecasts;
    
    /**
     * 预算差异
     */
    @ApiModelProperty(value = "预算差异")
    @Excel(name = "预算差异")
    private BigDecimal forecastsDiff;
    
    /**
     * 工作量
     */
    @ApiModelProperty(value = "工作量")
    @Excel(name = "工作量")
    private BigDecimal workYear;
    
    /**
     * 工作量 预测
     */
    @ApiModelProperty(value = "工作量 预测")
    @Excel(name = "工作量 预测")
    private BigDecimal workForecasts;
    
    /**
     * 工作量差异
     */
    @ApiModelProperty(value = "工作量差异")
    @Excel(name = "工作量差异")
    private BigDecimal workDiff;
}