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
 * 国际公司钻完井工作量及预算执行情况-探井
 *
 * @author oo
 * @date 2023-10-23
 */
@Data
@TableName("dc_report_budget_exe_exploration")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际公司钻完井工作量及预算执行情况-探井对象", description = "国际公司钻完井工作量及预算执行情况-探井表")
public class DcReportBudgetExeExploration extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键ID")
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
     * 工作计划及预算-wellcount-D
     */
    @ApiModelProperty(value = "工作计划及预算-wellcount-D")
    @Excel(name = "工作计划及预算-wellcount-D")
    private BigDecimal wellCountPlanD;
    
    /**
     * 工作计划及预算-budge-D
     */
    @ApiModelProperty(value = "工作计划及预算-budge-D")
    @Excel(name = "工作计划及预算-budge-D")
    private BigDecimal budgePlanD;
    
    /**
     * 上月所钻井数及资本支出-wellcount-D
     */
    @ApiModelProperty(value = "上月所钻井数及资本支出-wellcount-D")
    @Excel(name = "上月所钻井数及资本支出-wellcount-D")
    private BigDecimal wellCountCostD;
    
    /**
     * 上月所钻井数及资本支出-budge-D
     */
    @ApiModelProperty(value = "上月所钻井数及资本支出-budge-D")
    @Excel(name = "上月所钻井数及资本支出-budge-D")
    private BigDecimal budgeCostD;
    
    /**
     * 计划累计-D
     */
    @ApiModelProperty(value = "计划累计-D")
    @Excel(name = "计划累计-D")
    private BigDecimal wellCountPlanedD;
    
    /**
     * 计划累计-total
     */
    @ApiModelProperty(value = "计划累计-total")
    @Excel(name = "计划累计-total")
    private BigDecimal costPlanedTotal;
    
    /**
     * 实际累计-wellcount-D
     */
    @ApiModelProperty(value = "实际累计-wellcount-D")
    @Excel(name = "实际累计-wellcount-D")
    private BigDecimal wellCountAccumulatedD;
    
    /**
     * 实际累计- wellcount-%
     */
    @ApiModelProperty(value = "实际累计- wellcount-%")
    @Excel(name = "实际累计- wellcount-%")
    private BigDecimal wellCountAccumulatedRate;
    
    /**
     * 实际累计- budge_D
     */
    @ApiModelProperty(value = "实际累计- budge_D")
    @Excel(name = "实际累计- budge_D")
    private BigDecimal costAccumulatedD;
    
    /**
     * 实际累计-budge_%
     */
    @ApiModelProperty(value = "实际累计-budge_%")
    @Excel(name = "实际累计-budge_%")
    private BigDecimal costAccumulatedRate;
    
    /**
     * 全年预测-well_count-D
     */
    @ApiModelProperty(value = "全年预测-well_count-D")
    @Excel(name = "全年预测-well_count-D")
    private BigDecimal wellCountForecastingD;
    
    /**
     * 全年预测-wellcount-%
     */
    @ApiModelProperty(value = "全年预测-wellcount-%")
    @Excel(name = "全年预测-wellcount-%")
    private BigDecimal wellCountForecastingRate;
    
    /**
     * 全年预测-cost_d
     */
    @ApiModelProperty(value = "全年预测-cost_d")
    @Excel(name = "全年预测-cost_d")
    private BigDecimal costForecastingD;
    
    /**
     * 全年预测-cost-%
     */
    @ApiModelProperty(value = "全年预测-cost-%")
    @Excel(name = "全年预测-cost-%")
    private BigDecimal costForecastingRate;
    
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