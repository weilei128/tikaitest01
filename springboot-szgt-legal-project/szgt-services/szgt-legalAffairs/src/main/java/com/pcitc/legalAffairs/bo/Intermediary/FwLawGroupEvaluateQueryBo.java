package com.pcitc.legalAffairs.bo.Intermediary;

public class FwLawGroupEvaluateQueryBo {
    /**
     * 中介机构名称
     */
    private String fOrgName;
    /**
     * 机构类型名称
     */
    private String fOrgTypeName;
    /**
     * 机构类型编码
     */
    private String fOrgTypeCode;

    /**
     * 负责人
     */
    private String fResponsibleOfficer;
    /**
     * 联系方式
     */
    private String fContactWay;

    private int pageIndex;

    private int pageSize;

    public String getfOrgName() {
        return fOrgName;
    }

    public void setfOrgName(String fOrgName) {
        this.fOrgName = fOrgName;
    }

    public String getfOrgTypeName() {
        return fOrgTypeName;
    }

    public void setfOrgTypeName(String fOrgTypeName) {
        this.fOrgTypeName = fOrgTypeName;
    }

    public String getfOrgTypeCode() {
        return fOrgTypeCode;
    }

    public void setfOrgTypeCode(String fOrgTypeCode) {
        this.fOrgTypeCode = fOrgTypeCode;
    }

    public String getfResponsibleOfficer() {
        return fResponsibleOfficer;
    }

    public void setfResponsibleOfficer(String fResponsibleOfficer) {
        this.fResponsibleOfficer = fResponsibleOfficer;
    }

    public String getfContactWay() {
        return fContactWay;
    }

    public void setfContactWay(String fContactWay) {
        this.fContactWay = fContactWay;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
