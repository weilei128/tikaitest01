package com.pcitc.legalAffairs.vo.person;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author 法律人员信息表
 */
public class FwExcelOrganVo {


    /**
     * 姓名
     */
    @Excel(name= "所属单位", orderNum= "0")
    private String fAffiliatedUnit;

    /**
     * 所属组织名称
     */
    @Excel(name= "法律机构名称", orderNum= "1")
    private String fkOrgName;

    /**
     * 所属组织层级
     */
    @Excel(name= "法律机构类型", orderNum= "2")
    private String fOrgTypeName;

    /**
     * 职务
     */
    @Excel(name= "状态", orderNum= "3")
    private String fSetStatus;

    public String getfAffiliatedUnit() {
        return fAffiliatedUnit;
    }

    public void setfAffiliatedUnit(String fAffiliatedUnit) {
        this.fAffiliatedUnit = fAffiliatedUnit;
    }

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

    public String getfSetStatus() {
        return fSetStatus;
    }

    public void setfSetStatus(String fSetStatus) {
        this.fSetStatus = fSetStatus;
    }
}