package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.BaseEntity;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 年度预算-Output-Well
 *
 * @author oo
 * @date 2023-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_report_output_well_bugdet")
@ApiModel(value = "年度预算-Output-Well", description = "年度预算-Output-Well")
public class DcReportOutputWellBugdet extends LogicEntity {
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
     * 井型id
     */
    @ApiModelProperty(value = "井型id")
    @Excel(name = "井类型")
    @DictField(dictType = "dc_pk_well_type")
    private String type;

    /**
     * 申报名称
     */
    @ApiModelProperty(value = "申报名称")
    @Excel(name = "FID项目名称/申报项目名称")
    private String declareName;

    /**
     * 预算科目
     */
    @ApiModelProperty(value = "预算科目")
    @DictField(dictType = "well_config")
    @Excel(name = "预算科目")
    private String budgetSubjects;

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
