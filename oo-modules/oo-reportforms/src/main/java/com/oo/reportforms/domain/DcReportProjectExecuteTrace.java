package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_report_project_execute_trace")
@ApiModel(value = "海外项目投资执行跟踪", description = "海外项目投资执行跟踪")
public class DcReportProjectExecuteTrace extends LogicEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 项目名称 关联组织机构表的项目id
     */
    @TableId("organization_id")
    @ApiModelProperty(value = "项目名称 关联组织机构表的项目id")
    private String organizationId;

    /**
     * 作业者
     */
    @TableField("worker")
    @ApiModelProperty(value = "作业者")
    @Excel(name = "作业者")
    private String worker;

    /**
     * 中海油股权
     */
    @TableField("equity")
    @ApiModelProperty(value = "中海油股权")
    @Excel(name = "中海油股权")
    private float equity;

    /**
     * 发文时间
     */
    @TableField("post_date")
    @ApiModelProperty(value = "发文时间")
    @Excel(name = "发文时间")
    private Date postDate;

    /**
     * 文件号
     */
    @TableField("file_code")
    @ApiModelProperty(value = "文件号")
    @Excel(name = "文件号")
    private String fileCode;

    /**
     * 中海油份额-批准概算（万美元）
     */
    @TableField("portion_budget")
    @ApiModelProperty(value = "中海油份额-批准概算（万美元）")
    @Excel(name = "批准概算中海油份额")
    private String portionBudget;

    /**
     * 全额-批准概算（万美元）
     */
    @TableField("full_amount_budget")
    @ApiModelProperty(value = "全额-批准概算（万美元）")
    @Excel(name = "批准概算全额")
    private String fullAmountBudget;

    /**
     * 中海油份额-实际发生（万美元）
     */
    @TableField("portion_real")
    @ApiModelProperty(value = "中海油份额-实际发生（万美元）")
    @Excel(name = "实际发生中海油份额")
    private String portionReal;

    /**
     * 全额-实际发生（万美元）
     */
    @TableField("full_amount_real")
    @ApiModelProperty(value = "全额-实际发生（万美元）")
    @Excel(name = "实际发生全额")
    private String fullAmountReal;

    /**
     * 分年度执行情况-json存储
     */
    @TableField("execute_json")
    @ApiModelProperty(value = "分年度执行情况-json存储")
    private String executeJson;
}
