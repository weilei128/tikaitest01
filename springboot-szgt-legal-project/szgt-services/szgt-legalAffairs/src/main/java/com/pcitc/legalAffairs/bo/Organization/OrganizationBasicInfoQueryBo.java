package com.pcitc.legalAffairs.bo.Organization;

public class OrganizationBasicInfoQueryBo {
    /**
     * 机构名称
     */
    private String fkOrgName;

    /**
     * 机构类型名称
     */
    private String fOrgTypeName;

    /**
     * 所属单位
     */
    private String fAffiliatedUnit;
    /**
     * 设置状态：0.未设置，1已设置,2草稿，
     */
    private Integer fSetStatus;
    /**
     * 层级
     */
    private Integer fLevel;
    private int pageIndex;
    private int pageSize;

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getfOrgTypeName() {
        return fOrgTypeName;
    }

    public void setfOrgTypeName(String fOrgTypeName) {
        this.fOrgTypeName = fOrgTypeName;
    }

    public String getfAffiliatedUnit() {
        return fAffiliatedUnit;
    }

    public void setfAffiliatedUnit(String fAffiliatedUnit) {
        this.fAffiliatedUnit = fAffiliatedUnit;
    }

    public Integer getfSetStatus() {
        return fSetStatus;
    }

    public void setfSetStatus(Integer fSetStatus) {
        this.fSetStatus = fSetStatus;
    }

    public Integer getfLevel() {
        return fLevel;
    }

    public void setfLevel(Integer fLevel) {
        this.fLevel = fLevel;
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
