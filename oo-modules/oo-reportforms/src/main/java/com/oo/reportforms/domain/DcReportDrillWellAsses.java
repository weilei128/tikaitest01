package com.oo.reportforms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import com.oo.common.core.annotation.Excel;
import com.oo.common.core.web.domain.LogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dc_report_drill_well_asses")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "（计划费控岗06）附件2：涉及钻完井的考核项目（手动）", description = "（（计划费控岗06）附件2：涉及钻完井的考核项目（手动）")
public class DcReportDrillWellAsses extends LogicEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 国家ID，关联国家层级关系主数据
     */
    @MppMultiId
    @TableField("COUNTRY")
    @ApiModelProperty(value = "国家ID，关联国家层级关系主数据")
    private String country;

    /**
     * 年份
     */
    @MppMultiId
    @TableField("YEAR")
    @ApiModelProperty(value = "年份")
    private String year;

    /**
     * 关键绩效指标-关键绩效指标
     */
    @TableField("PERFORMAN_METRICS_KEY")
    @ApiModelProperty(value = "关键绩效指标-关键绩效指标")
    @Excel(name = "关键绩效指标")
    private String performanMetricsKey;

    /**
     * 关键绩效指标-指标定义/计量单位
     */
    @TableField("METRICS_DEFINED_KEY")
    @ApiModelProperty(value = "关键绩效指标-指标定义/计量单位")
    @Excel(name = "关键绩效指标-指标定义 / 计量单位")
    private String metricsDefinedKey;

    /**
     * 关键绩效指标-权重
     */
    @TableField("WEIGHT_KEY")
    @ApiModelProperty(value = "关键绩效指标-权重")
    @Excel(name = "关键绩效指标-权重")
    private String weightKey;

    /**
     * 关键绩效指标-基本目标（80分）
     */
    @TableField("BASIC_KEY")
    @ApiModelProperty(value = "关键绩效指标-基本目标（80分）")
    @Excel(name = "关键绩效指标-目标值-基本目标（80分）")
    private BigDecimal basicKey;

    /**
     * 关键绩效指标-奋斗目标（90分）
     */
    @TableField("TRUGGLE_KEY")
    @ApiModelProperty(value = "关键绩效指标-奋斗目标（90分）")
    @Excel(name = "关键绩效指标-目标值-基本目标（90分）")
    private BigDecimal truggleKey;

    /**
     * 关键绩效指标-挑战目标（90分）
     */
    @TableField("CHALLENGE_KEY")
    @ApiModelProperty(value = "关键绩效指标-挑战目标（100分）")
    @Excel(name = "关键绩效指标-目标值-基本目标（100分）")
    private BigDecimal challengeKey;

    /**
     * 关键绩效指标-预测全年完成值-业务单元自评
     */
    @TableField("FORECAST_FINISH_SELF_KEY")
    @ApiModelProperty(value = "关键绩效指标-预测全年完成值-业务单元自评")
    @Excel(name = "关键绩效指标-业务单元自评-预测全年完成值")
    private String forecastFinishSelfKey;

    /**
     * 关键绩效指标-得分-业务单元自评
     */
    @TableField("SCORE_SELF_KEY")
    @ApiModelProperty(value = "关键绩效指标-得分-业务单元自评")
    @Excel(name = "关键绩效指标-业务单元自评-得分")
    private BigDecimal scoreSelfKey;

    /**
     * 关键绩效指标-说明-业务单元自评
     */
    @TableField("DESCRIPTION_SELF_KEY")
    @ApiModelProperty(value = "关键绩效指标-说明-业务单元自评")
    @Excel(name = "关键绩效指标-业务单元自评-说明")
    private String descriptionSelfKey;

    /**
     * 关键绩效指标-预测全年完成值-考核工作组评价
     */
    @TableField("FORECAST_FINISH_GROUP_KEY")
    @ApiModelProperty(value = "关键绩效指标-预测全年完成值-考核工作组评价")
    @Excel(name = "关键绩效指标-考评工作组评价-预测全年完成值")
    private String forecastFinishGroupKey;

    /**
     * 关键绩效指标-得分-考核工作组评价
     */
    @TableField("SCORE_GROUP_KEY")
    @ApiModelProperty(value = "关键绩效指标-得分-考核工作组评价")
    @Excel(name = "关键绩效指标-考评工作组评价-得分")
    private BigDecimal scoreGroupKey;

    /**
     * 关键绩效指标-说明-考核工作组评价
     */
    @TableField("DESCRIPTION_GROUP_KEY")
    @ApiModelProperty(value = "关键绩效指标-说明-考核工作组评价")
    @Excel(name = "关键绩效指标-考评工作组评价-说明")
    private String descriptionGroupKey;

    /**
     * 关键绩效指标-评分部门，关联部门表-考核工作组评价
     */
    @TableField("SCORE_DEPT_GROUP_KEY")
    @ApiModelProperty(value = "关键绩效指标-评分部门，关联部门表-考核工作组评价")
    @Excel(name = "关键绩效指标-考评工作组评价-评分部门")
    private Long scoreDeptGroupKey;

    /**
     * 差异化激励约束性指标-考核指标
     */
    @TableField("ASSESS_METRICS_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-考核指标")
    @Excel(name = "差异化激励约束性指标-考核指标")
    private String assessMetricsDiff;

    /**
     * 差异化激励约束性指标-指标定义/计量单位
     */
    @TableField("METRICS_DEFINED_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-指标定义/计量单位")
    @Excel(name = "差异化激励约束性指标-指标定义 / 计量单位")
    private String metricsDefinedDiff;

    /**
     * 差异化激励约束性指标-基本目标（-1）
     */
    @TableField("BASIC_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-基本目标（-1）")
    @Excel(name = "差异化激励约束性指标-目标值（-1）")
    private BigDecimal basicDiff;

    /**
     * 差异化激励约束性指标-奋斗目标（0）
     */
    @TableField("TRUGGLE_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-奋斗目标（0）")
    @Excel(name = "差异化激励约束性指标-目标值（0）")
    private BigDecimal truggleDiff;

    /**
     * 差异化激励约束性指标-挑战目标（1）
     */
    @TableField("CHALLENGE_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-挑战目标（1）")
    @Excel(name = "差异化激励约束性指标-目标值（1）")
    private BigDecimal challengeDiff;

    /**
     * 差异化激励约束性指标-预测全年完成值-业务单元自评
     */
    @TableField("FORECAST_FINISH_SELF_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-预测全年完成值-业务单元自评")
    @Excel(name = "差异化激励约束性指标-业务单元自评-预测全年完成值")
    private String forecastFinishSelfDiff;

    /**
     * 差异化激励约束性指标-得分-业务单元自评
     */
    @TableField("SCORE_SELF_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-得分-业务单元自评")
    @Excel(name = "差异化激励约束性指标-业务单元自评-得分")
    private BigDecimal scoreSelfDiff;

    /**
     * 差异化激励约束性指标-说明-业务单元自评
     */
    @TableField("DESCRIPTION_SELF_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-说明-业务单元自评")
    @Excel(name = "差异化激励约束性指标-业务单元自评-说明")
    private String descriptionSelfDiff;

    /**
     * 差异化激励约束性指标-预测全年完成值-考核工作组评价
     */
    @TableField("FORECAST_FINISH_GROUP_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-预测全年完成值-考核工作组评价")
    @Excel(name = "差异化激励约束性指标-考评工作组评价-预测全年完成值")
    private String forecastFinishGroupDiff;

    /**
     * 差异化激励约束性指标-得分-考核工作组评价
     */
    @TableField("SCORE_GROUP_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-得分-考核工作组评价")
    @Excel(name = "差异化激励约束性指标-考评工作组评价-得分")
    private BigDecimal scoreGroupDiff;

    /**
     * 差异化激励约束性指标-说明-考核工作组评价
     */
    @TableField("DESCRIPTION_GROUP_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-说明-考核工作组评价")
    @Excel(name = "差异化激励约束性指标-考评工作组评价-说明")

    private String descriptionGroupDiff;

    /**
     * 差异化激励约束性指标-评分部门，关联部门表-考核工作组评价
     */
    @TableField("SCORE_DEPT_GROUP_DIFF")
    @ApiModelProperty(value = "差异化激励约束性指标-评分部门，关联部门表-考核工作组评价")
    @Excel(name = "差异化激励约束性指标-考评工作组评价-评分部门")
    private Long scoreDeptGroupDiff;

    /**
     * 通用类激励约束性指标-考核指标
     */
    @TableField("ASSESS_METRICS_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-考核指标")
    @Excel(name = "通用类激励约束指标-考核指标")
    private String assessMetricsGeneral;

    /**
     * 通用类激励约束性指标-得分范围1
     */
    @TableField("BASIC_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-得分范围1")
    @Excel(name = "通用类激励约束指标-得分范围（1）")
    private String basicGeneral;

    /**
     * 通用类激励约束性指标-得分范围2
     */
    @TableField("TRUGGLE_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-得分范围2")
    @Excel(name = "通用类激励约束指标-得分范围（2）")
    private String truggleGeneral;

    /**
     * 通用类激励约束性指标-得分范围3
     */
    @TableField("CHALLENGE_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-得分范围3")
    @Excel(name = "通用类激励约束指标-得分范围（3）")
    private String challengeGeneral;

    /**
     * 通用类激励约束性指标-扣分-业务单元自评
     */
    @TableField("DEDUCTED_SCORES_SELF_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-扣分-业务单元自评")
    @Excel(name = "通用类激励约束指标-业务单元自评-扣分")
    private BigDecimal deductedScoresSelfGeneral;

    /**
     * 通用类激励约束性指标-加分-业务单元自评
     */
    @TableField("ADD_SCORES_SELF_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-加分-业务单元自评")
    @Excel(name = "通用类激励约束指标-业务单元自评-加分")
    private BigDecimal addScoresSelfGeneral;

    /**
     * 通用类激励约束性指标-说明-业务单元自评
     */
    @TableField("DESCRIPTION_SELF_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-说明-业务单元自评")
    @Excel(name = "通用类激励约束指标-业务单元自评-说明")
    private String descriptionSelfGeneral;

    /**
     * 通用类激励约束性指标-支持材料-业务单元自评
     */
    @TableField("SUPPORT_STUFF_SELF_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-支持材料-业务单元自评")
    @Excel(name = "通用类激励约束指标-业务单元自评-支持材料")
    private String supportStuffSelfGeneral;

    /**
     * 通用类激励约束性指标-扣分-考评工作组评价
     */
    @TableField("DEDUCTED_SCORES_GROUP_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-扣分-考评工作组评价")
    @Excel(name = "通用类激励约束指标-考评工作组评价-扣分")
    private BigDecimal deductedScoresGroupGeneral;

    /**
     * 通用类激励约束性指标-加分-考评工作组评价
     */
    @TableField("ADD_SCORES_GROUP_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-加分-考评工作组评价")
    @Excel(name = "通用类激励约束指标-考评工作组评价-加分")
    private BigDecimal addScoresGroupGeneral;

    /**
     * 通用类激励约束性指标-说明-考评工作组评价
     */
    @TableField("DESCRIPTION_GROUP_GENERAL")
    @ApiModelProperty(value = "通用类激励约束性指标-说明-考评工作组评价")
    @Excel(name = "通用类激励约束指标-考评工作组评价-说明")
    private String descriptionGroupGeneral;

    /**
     * 得分汇总统计-关键绩效指标-业务单元自评得分
     */
    @TableField("KEY_SELF_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-关键绩效指标-业务单元自评得分")
    @Excel(name = "得分汇总统计-业务单元自评得分-关键绩效指标")
    private String keySelfSummary;

    /**
     * 得分汇总统计-差异化激励约束指标-业务单元自评得分
     */
    @TableField("DIFF_SELF_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-差异化激励约束指标-业务单元自评得分")
    @Excel(name = "得分汇总统计-业务单元自评得分-差异化激励约束指标")
    private String diffSelfSummary;

    /**
     * 得分汇总统计-通用类激励约束指标-业务单元自评得分
     */
    @TableField("GENERAL_SELF_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-通用类激励约束指标-业务单元自评得分")
    @Excel(name = "得分汇总统计-业务单元自评得分-通用类激励约束指标")
    private String generalSelfSummary;

    /**
     * 得分汇总统计-关键绩效指标-考评工作组评价得分
     */
    @TableField("KEY_GROUP_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-关键绩效指标-考评工作组评价得分")
    @Excel(name = "得分汇总统计-考评工作组评价得分-关键绩效指标")
    private String keyGroupSummary;

    /**
     * 得分汇总统计-差异化激励约束指标-考评工作组评价得分
     */
    @TableField("DIFF_GROUP_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-差异化激励约束指标-考评工作组评价得分")
    @Excel(name = "得分汇总统计-考评工作组评价得分-差异化激励约束指标")
    private String diffGroupSummary;

    /**
     * 得分汇总统计-通用类激励约束指标-考评工作组评价得分
     */
    @TableField("GENERAL_GROUP_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-通用类激励约束指标-考评工作组评价得分")
    @Excel(name = "得分汇总统计-考评工作组评价得分-通用类激励约束指标")
    private String generalGroupSummary;

    /**
     * 得分汇总统计-合计
     */
    @TableField("TOTLA_SUMMARY")
    @ApiModelProperty(value = "得分汇总统计-合计")
    @Excel(name = "得分汇总统计-合计")
    private BigDecimal totlaSummary;
}
