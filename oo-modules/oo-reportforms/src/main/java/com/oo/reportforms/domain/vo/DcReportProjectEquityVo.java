package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportProjectEquity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportProjectEquityVo extends DcReportProjectEquity {
    @Excel(name = "国家")
    private String companyName;//国家名
    private String blockId;//区块id
    @Excel(name = "区块")
    private String blockName;//区块名
}
