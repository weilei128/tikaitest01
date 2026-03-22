package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportProjectBriefly;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportProjectBrieflyVo extends DcReportProjectBriefly {
    @Excel(name = "")
    private String serialNumber;

    @Excel(name = "油田名称")
    private String projectName;

    @Excel(name = "项目名称")
    private String organizationName;

//    @Excel(name = "井型")
//    private String wellTypeName;

    @Excel(name = "审查会时间")
    private String censonDate;
}
