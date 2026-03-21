package com.pcitc.legalAffairs.vo.Intermediary;

public class FwIntermediaryMarketEvalVo {
    /**
     * 主键
     */
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 不良记录
     */
    private String fBadRecord;
    /**
     * 单位名称
     */
    private String fCompanyName;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public String getfBadRecord() {
        return fBadRecord;
    }

    public void setfBadRecord(String fBadRecord) {
        this.fBadRecord = fBadRecord;
    }

    public String getfCompanyName() {
        return fCompanyName;
    }

    public void setfCompanyName(String fCompanyName) {
        this.fCompanyName = fCompanyName;
    }
}
