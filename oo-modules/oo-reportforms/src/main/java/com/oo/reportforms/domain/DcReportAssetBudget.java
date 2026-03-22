package com.oo.reportforms.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 年度预算-By Asset&Project(FID)
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_report_asset_budget")
@ApiModel(value = "年度预算-By Asset&Project(FID)", description = "年度预算-By Asset&Project(FID)")
public class DcReportAssetBudget extends LogicEntity {
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
    @Excel(name = "项目ID")
    private String organizationId;


    /**
     * 申报名称
     */
    @ApiModelProperty(value = "申报名称")
    @Excel(name = "FID项目名称/申报项目名称")
    private String declareName;


    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @Excel(name = "年份")
    private String year;

    /**
     * 预算-正式
     */
    @ApiModelProperty(value = "预算-正式")
    @Excel(name = "预算正式")
    private BigDecimal budgetFormal;

    /**
     * 预算-待批
     */
    @ApiModelProperty(value = "预算-待批")
    @Excel(name = "预算待批")
    private BigDecimal budgetApproval;

    /**
     * 预算-合计
     */
    @ApiModelProperty(value = "预算-合计")
    @Excel(name = "预算合计")
    private BigDecimal budgetTotal;

    /**
     * 工作量-正式
     */
    @ApiModelProperty(value = "工作量-正式")
    @Excel(name = "工作量正式")
    private BigDecimal workFormal;

    /**
     * 工作量-待批
     */
    @ApiModelProperty(value = "工作量-待批")
    @Excel(name = "工作量待批")
    private BigDecimal workApproval;

    /**
     * 工作量-合计
     */
    @ApiModelProperty(value = "工作量-合计")
    @Excel(name = "工作量合计")
    private BigDecimal workTotal;

}
