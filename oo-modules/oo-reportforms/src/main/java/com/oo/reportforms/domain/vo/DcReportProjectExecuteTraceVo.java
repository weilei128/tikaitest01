package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportProjectExecuteTrace;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportProjectExecuteTraceVo extends DcReportProjectExecuteTrace {

    @Excel(name = "")
    private String serialNumber;

    /** 项目名称*/
    @Excel(name = "项目名称")
    private String name;

    /** 分年度执行情况*/
    private List<DcReportProjectExecuteTraceYearVo> dcReportProjectExecuteTraceYearVoList;
}
