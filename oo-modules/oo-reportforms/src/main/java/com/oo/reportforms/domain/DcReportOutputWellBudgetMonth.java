package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import com.oo.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 年度预算-Output-Well-月份
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@TableName("dc_report_output_well_budget_month")
@ApiModel(value = "年度预算-Output-Well-月份", description = "年度预算-Output-Well-月份")
public class DcReportOutputWellBudgetMonth implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @MppMultiId
    @TableField("OUTPUT_WELL_ID")
    @ApiModelProperty(value = "关联dc_report_output_well_budget主键ID")
    private String outputWellId;

    /**
     * 月份
     */
    @MppMultiId
    @TableField("MONTH")
    @ApiModelProperty(value = "月份")
    @Excel(name = "月份")
    private String month;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @Excel(name = "值")
    @TableField(value="VALUE",insertStrategy = FieldStrategy.IGNORED)
    private BigDecimal value;
}
