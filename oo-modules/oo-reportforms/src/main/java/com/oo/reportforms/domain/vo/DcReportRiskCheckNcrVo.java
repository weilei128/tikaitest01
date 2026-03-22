package com.oo.reportforms.domain.vo;

import com.oo.common.core.annotation.Excel;
import com.oo.reportforms.domain.DcReportProjectExecuteTrace;
import com.oo.reportforms.domain.DcReportRiskCheckNcr;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DcReportRiskCheckNcrVo extends DcReportRiskCheckNcr {
    @Excel(name = "Well NO./井号")
    private String wellName;//井名

/*//    @Excel(name = "Resources Type/检查类型")
    private String resourceTypeName;//检查类型名称

//    @Excel(name = "Facilities/检查单位")
    private String facilitiesName;

//    @Excel(name = "Risk  Criticality Ranking/风险等级")
    private String riskCriticalityRankingName;

    //@Excel(name = "NC Status/不符合项目前状态")
    private String ncStatusName;*/
}
