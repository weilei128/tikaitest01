package com.oo.reportforms.domain;

import com.oo.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportInteractTraceView extends DcReportInteractTrace {
    @Excel(name = "计划归属部门")
    private String deptName;
}
