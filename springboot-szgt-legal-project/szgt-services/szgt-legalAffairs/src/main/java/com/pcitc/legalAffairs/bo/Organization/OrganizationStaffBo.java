package com.pcitc.legalAffairs.bo.Organization;

public class OrganizationStaffBo {
    /**
     * 法律机构职员信息主键
     */
    private Integer fId;
    /**
     * 法律机构id
     */
    private Integer fkLawFirmId;
    /**
     * 姓名
     */
    private String fName ;
    /**
     * 性别:0：男，1：女
     */
    private short fSex;
    /**
     * 岗位
     */
    private String fPosition;
    /**
     * 岗位职责
     */
    private String fPositionDuties;
    /**
     * 定岗文件名称
     */
    private String fFixPositionFileName;
    /**
     * 定岗文件存储地址
     */
    private String fFixPositionFileUrl;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getFkLawFirmId() {
        return fkLawFirmId;
    }

    public void setFkLawFirmId(Integer fkLawFirmId) {
        this.fkLawFirmId = fkLawFirmId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public short getfSex() {
        return fSex;
    }

    public void setfSex(short fSex) {
        this.fSex = fSex;
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
    }

    public String getfPositionDuties() {
        return fPositionDuties;
    }

    public void setfPositionDuties(String fPositionDuties) {
        this.fPositionDuties = fPositionDuties;
    }

    public String getfFixPositionFileName() {
        return fFixPositionFileName;
    }

    public void setfFixPositionFileName(String fFixPositionFileName) {
        this.fFixPositionFileName = fFixPositionFileName;
    }

    public String getfFixPositionFileUrl() {
        return fFixPositionFileUrl;
    }

    public void setfFixPositionFileUrl(String fFixPositionFileUrl) {
        this.fFixPositionFileUrl = fFixPositionFileUrl;
    }
}
