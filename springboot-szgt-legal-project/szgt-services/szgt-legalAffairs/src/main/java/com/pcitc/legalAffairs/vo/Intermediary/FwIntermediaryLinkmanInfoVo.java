package com.pcitc.legalAffairs.vo.Intermediary;

public class FwIntermediaryLinkmanInfoVo {
    /**
     * 中介机构联系人信息主键
     */
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 联系人名称
     */
    private String fLinkmanName;
    /**
     * 移动电话号码
     */
    private String fMobileNum;
    /**
     * 固定电话号码
     */
    private String fFixlineNum;
    /**
     * 联系电邮
     */
    private String fContactEmail;

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

    public String getfLinkmanName() {
        return fLinkmanName;
    }

    public void setfLinkmanName(String fLinkmanName) {
        this.fLinkmanName = fLinkmanName;
    }

    public String getfMobileNum() {
        return fMobileNum;
    }

    public void setfMobileNum(String fMobileNum) {
        this.fMobileNum = fMobileNum;
    }

    public String getfFixlineNum() {
        return fFixlineNum;
    }

    public void setfFixlineNum(String fFixlineNum) {
        this.fFixlineNum = fFixlineNum;
    }

    public String getfContactEmail() {
        return fContactEmail;
    }

    public void setfContactEmail(String fContactEmail) {
        this.fContactEmail = fContactEmail;
    }
}
