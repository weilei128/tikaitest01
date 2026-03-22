package com.oo.reportforms.domain.vo;
import lombok.Data;

@Data
public class DcReportDrillWellAssesKey {
    /** 国家ID，关联国家层级关系主数据 */
    private String country;

    /** 年份*/
    private String year;
}
