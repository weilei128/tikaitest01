package com.pcitc.legalAffairs.bo.Organization;

import java.util.Date;

public class OrganizationWrokRewardBo {
    /**
     * 法律工作获奖信息主键
     */
    private Integer fId;
    /**
     * 法律机构id
     */
    private Integer fkLawFirmId;
    /**
     * 获奖名称
     */
    private String fRewardName;
    /**
     * 获奖级别编码
     */
    private String fRewardLevelCode;
    /**
     * 获奖级别名称
     */
    private String fRewardLevelName;
    /**
     * 获奖日期
     */
    private Date fRewardDate;
    /**
     * 颁奖机构名称
     */
    private String fAwardsOrgName;

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

    public String getfRewardName() {
        return fRewardName;
    }

    public void setfRewardName(String fRewardName) {
        this.fRewardName = fRewardName;
    }

    public String getfRewardLevelCode() {
        return fRewardLevelCode;
    }

    public void setfRewardLevelCode(String fRewardLevelCode) {
        this.fRewardLevelCode = fRewardLevelCode;
    }

    public String getfRewardLevelName() {
        return fRewardLevelName;
    }

    public void setfRewardLevelName(String fRewardLevelName) {
        this.fRewardLevelName = fRewardLevelName;
    }

    public Date getfRewardDate() {
        return fRewardDate;
    }

    public void setfRewardDate(Date fRewardDate) {
        this.fRewardDate = fRewardDate;
    }

    public String getfAwardsOrgName() {
        return fAwardsOrgName;
    }

    public void setfAwardsOrgName(String fAwardsOrgName) {
        this.fAwardsOrgName = fAwardsOrgName;
    }
}
