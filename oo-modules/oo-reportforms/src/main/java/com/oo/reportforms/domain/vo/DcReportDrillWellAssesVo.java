package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportDrillWellAsses;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportDrillWellAssesVo extends DcReportDrillWellAsses {
    @Excel(name = "")
    private String serialNumber;

    /** 国家名称*/
    @Excel(name = "BU")
    private String name;

    /** 关键绩效指标-评分部门，关联部门表-考核工作组评价*/
    private String scoreDeptGroupKeyName;

    /** 差异化激励约束性指标-评分部门，关联部门表-考核工作组评价*/
    private String scoreDeptGroupDiffName;




}
