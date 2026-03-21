package com.pcitc.legalAffairs.bo.Organization;

import java.util.Date;

public class OrganizationBasicInfoBo {
    /**
     * 法律机构信息主键
     */
    private Integer fId;
    /**
     * 所属单位
     */
    private String fAffiliatedUnit;
    /**
     * 组织节点
     */
    private String fOrgNode;
    /**
     * 机构ID
     */
    private String fkOrgId ;
    /**
     * 机构名称
     */
    private String fkOrgName;
    /**
     * 机构类型编码
     */
    private String fOrgTypeCode;
    /**
     * 机构类型名称
     */
    private String fOrgTypeName;
    /**
     * 海外机构名称
     */
    private String fOverseaOrgName;
    /**
     * 通讯地址
     */
    private String fPostalAddress;
    /**
     * 邮政编码
     */
    private String fPostCode;
    /**
     * 区号
     */
    private String fAreaCode;
    /**
     * 单位传真
     */
    private String fUnitFax;
    /**
     * 机构职责
     */
    private String fOrgDuties;
    /**
     * 文号
     */
    private String fReferenceNumber;
    /**
     * 文件名称
     */
    private String fFileName;
    /**
     * 文件存储路径
     */
    private String fFileUrl;
    /**
     * 设置状态：0.未设置，1已设置,2草稿，
     */
    private Integer fSetStatus;

    private String fCreateuser;

    private String fCreatename;

    private Date fCreatetime;

    private String fUpdateuser;

    private String fUpdatename;

    private Date fUpdatetime;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfAffiliatedUnit() {
        return fAffiliatedUnit;
    }

    public void setfAffiliatedUnit(String fAffiliatedUnit) {
        this.fAffiliatedUnit = fAffiliatedUnit;
    }

    public String getfOrgNode() {
        return fOrgNode;
    }

    public void setfOrgNode(String fOrgNode) {
        this.fOrgNode = fOrgNode;
    }

    public String getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(String fkOrgId) {
        this.fkOrgId = fkOrgId;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getfOrgTypeCode() {
        return fOrgTypeCode;
    }

    public void setfOrgTypeCode(String fOrgTypeCode) {
        this.fOrgTypeCode = fOrgTypeCode;
    }

    public String getfOrgTypeName() {
        return fOrgTypeName;
    }

    public void setfOrgTypeName(String fOrgTypeName) {
        this.fOrgTypeName = fOrgTypeName;
    }

    public String getfOverseaOrgName() {
        return fOverseaOrgName;
    }

    public void setfOverseaOrgName(String fOverseaOrgName) {
        this.fOverseaOrgName = fOverseaOrgName;
    }

    public String getfPostalAddress() {
        return fPostalAddress;
    }

    public void setfPostalAddress(String fPostalAddress) {
        this.fPostalAddress = fPostalAddress;
    }

    public String getfPostCode() {
        return fPostCode;
    }

    public void setfPostCode(String fPostCode) {
        this.fPostCode = fPostCode;
    }

    public String getfAreaCode() {
        return fAreaCode;
    }

    public void setfAreaCode(String fAreaCode) {
        this.fAreaCode = fAreaCode;
    }

    public String getfUnitFax() {
        return fUnitFax;
    }

    public void setfUnitFax(String fUnitFax) {
        this.fUnitFax = fUnitFax;
    }

    public String getfOrgDuties() {
        return fOrgDuties;
    }

    public void setfOrgDuties(String fOrgDuties) {
        this.fOrgDuties = fOrgDuties;
    }

    public String getfReferenceNumber() {
        return fReferenceNumber;
    }

    public void setfReferenceNumber(String fReferenceNumber) {
        this.fReferenceNumber = fReferenceNumber;
    }

    public String getfFileName() {
        return fFileName;
    }

    public void setfFileName(String fFileName) {
        this.fFileName = fFileName;
    }

    public String getfFileUrl() {
        return fFileUrl;
    }

    public void setfFileUrl(String fFileUrl) {
        this.fFileUrl = fFileUrl;
    }

    public Integer getfSetStatus() {
        return fSetStatus;
    }

    public void setfSetStatus(Integer fSetStatus) {
        this.fSetStatus = fSetStatus;
    }

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }
}
