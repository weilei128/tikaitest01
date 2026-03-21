package com.pcitc.szgt.contract.common.model;

public class SimpleOrgInfo {

    /**
     * 主键
     */
    private Integer fId;

    /**
     * 组织机构唯一编码
     */
    private String fCode;

    /**
     * 组织机构全称
     */
    private String fName;

    /**
     * 组织机构简称
     */
    private String fShortName;

    /**
     * 上级机构id
     */
    private Integer fkParentId;

    /**
     * 上级组织机构名称
     */
    private String fkParentName;

    /**
     * 组织机构地址
     */
    private String fAddress;

    /**
     * 描述
     */
    private String fDescription;

    /**
     * 组织机构层级
     */
    private Integer fLevel;

    /**
     * 启用状态：0启用/1未启用
     */
    private Integer fState;

    /**
     * 类型：0部门，1公司
     */
    private Integer fType;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfShortName() {
        return fShortName;
    }

    public void setfShortName(String fShortName) {
        this.fShortName = fShortName;
    }

    public Integer getFkParentId() {
        return fkParentId;
    }

    public void setFkParentId(Integer fkParentId) {
        this.fkParentId = fkParentId;
    }

    public String getFkParentName() {
        return fkParentName;
    }

    public void setFkParentName(String fkParentName) {
        this.fkParentName = fkParentName;
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Integer getfLevel() {
        return fLevel;
    }

    public void setfLevel(Integer fLevel) {
        this.fLevel = fLevel;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }
}
