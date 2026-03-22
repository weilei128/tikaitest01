package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.annotation.Excel;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;


/**
 * 国际公司钻完井工作量及预算执行情况-开发井
 *
 * @author oo
 * @date 2023-10-23
 */
@Data
@TableName("dc_report_budget_exe_development")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际公司钻完井工作量及预算执行情况-开发井对象", description = "国际公司钻完井工作量及预算执行情况-开发井表")
public class DcReportBudgetExeDevelopment extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一键")
    private String id;
    
    /**
     * bu
     */
    @ApiModelProperty(value = "bu")
    @Excel(name = "bu")
    private String bu;
    
    /**
     * 识别码
     */
    @ApiModelProperty(value = "识别码")
    @Excel(name = "识别码")
    private String code;
    
    /**
     * 项目id关联项目表
     */
    @ApiModelProperty(value = "项目id关联项目表")
    @Excel(name = "项目id关联项目表")
    private String organizationId;
    
    /**
     * 权益(%)
     */
    @ApiModelProperty(value = "权益(%)")
    @Excel(name = "权益(%)")
    private BigDecimal workingInterest;
    
    /**
     * 工作计划及预算-D
     */
    @ApiModelProperty(value = "工作计划及预算-D")
    @Excel(name = "工作计划及预算-D")
    private BigDecimal wellCountPlanD;
    
    /**
     * 工作计划及预算-C
     */
    @ApiModelProperty(value = "工作计划及预算-C")
    @Excel(name = "工作计划及预算-C")
    private BigDecimal wellCountPlanC;
    
    /**
     * 工作计划及预算-D&C
     */
    @ApiModelProperty(value = "工作计划及预算-D&C")
    @Excel(name = "工作计划及预算-D&C")
    private BigDecimal budgePlanDc;
    
    /**
     * 上月所钻井数及资本支出-D
     */
    @ApiModelProperty(value = "上月所钻井数及资本支出-D")
    @Excel(name = "上月所钻井数及资本支出-D")
    private BigDecimal wellCountCostD;
    
    /**
     * 上月所钻井数及资本支出-C
     */
    @ApiModelProperty(value = "上月所钻井数及资本支出-C")
    @Excel(name = "上月所钻井数及资本支出-C")
    private BigDecimal wellCountCostC;
    
    /**
     * 上月所钻井数及资本支出-D&C
     */
    @ApiModelProperty(value = "上月所钻井数及资本支出-D&C")
    @Excel(name = "上月所钻井数及资本支出-D&C")
    private BigDecimal budgeCostDc;
    
    /**
     * 计划累计-D
     */
    @ApiModelProperty(value = "计划累计-D")
    @Excel(name = "计划累计-D")
    private BigDecimal wellCountPlanedD;
    
    /**
     * 计划累计-C
     */
    @ApiModelProperty(value = "计划累计-C")
    @Excel(name = "计划累计-C")
    private BigDecimal wellCountPlanedC;
    
    /**
     * 计划累计-D&C
     */
    @ApiModelProperty(value = "计划累计-D&C")
    @Excel(name = "计划累计-D&C")
    private BigDecimal budgePlanedDc;
    
    /**
     * 实际累计-D
     */
    @ApiModelProperty(value = "实际累计-D")
    @Excel(name = "实际累计-D")
    private BigDecimal wellCountAccumulatedD;
    
    /**
     * 实际累计-C
     */
    @ApiModelProperty(value = "实际累计-C")
    @Excel(name = "实际累计-C")
    private BigDecimal wellCountAccumulatedC;
    
    /**
     * 实际累计-D&C
     */
    @ApiModelProperty(value = "实际累计-D&C")
    @Excel(name = "实际累计-D&C")
    private BigDecimal budgeAccumulatedDc;
    
    /**
     * 实际累计-%
     */
    @ApiModelProperty(value = "实际累计-%")
    @Excel(name = "实际累计-%")
    private BigDecimal budgeAccumulatedRate;
    
    /**
     * 全年预测-D
     */
    @ApiModelProperty(value = "全年预测-D")
    @Excel(name = "全年预测-D")
    private BigDecimal wellCountForecastingD;
    
    /**
     * 全年预测-C
     */
    @ApiModelProperty(value = "全年预测-C")
    @Excel(name = "全年预测-C")
    private BigDecimal wellCountForecastingC;
    
    /**
     * 全年预测-D&C
     */
    @ApiModelProperty(value = "全年预测-D&C")
    @Excel(name = "全年预测-D&C")
    private BigDecimal budgeForecastingDc;
    
    /**
     * 全年预测-%
     */
    @ApiModelProperty(value = "全年预测-%")
    @Excel(name = "全年预测-%")
    private BigDecimal budgeForecastingRate;
    
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;
    
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateBy;
    
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    
    /**
     * 删除人
     */
    @ApiModelProperty(value = "删除人")
    @Excel(name = "删除人")
    private String delUser;
    
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date delTime;
}