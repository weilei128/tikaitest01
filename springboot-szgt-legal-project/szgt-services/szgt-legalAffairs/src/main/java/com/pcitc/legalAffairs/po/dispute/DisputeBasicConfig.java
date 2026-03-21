package com.pcitc.legalAffairs.po.dispute;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "fw_dispute_new_basic_config")
public class DisputeBasicConfig {

    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    private Long fkOrgId;

    private String fkOrgName;

    private Integer fkDictionaryTypeId;

    private String fkDictionaryTypeName;

//    private Integer fSelectValue;
//
//    private String fSelectName;

    private Integer fSort;

    private Integer fState;

    private Integer fType;

    private Integer fIsdel;

    private Long fCreateId;

    private String fCreateUser;

    private String fCreateName;

    private Date fCreateTime;

    private Long fUpdateId;

    private String fUpdateUser;

    private String fUpdateName;

    private Date fUpdateTime;

    private String fContent;

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(Long fkOrgId) {
        this.fkOrgId = fkOrgId;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName == null ? null : fkOrgName.trim();
    }

    public Integer getFkDictionaryTypeId() {
        return fkDictionaryTypeId;
    }

    public void setFkDictionaryTypeId(Integer fkDictionaryTypeId) {
        this.fkDictionaryTypeId = fkDictionaryTypeId;
    }

    public String getFkDictionaryTypeName() {
        return fkDictionaryTypeName;
    }

    public void setFkDictionaryTypeName(String fkDictionaryTypeName) {
        this.fkDictionaryTypeName = fkDictionaryTypeName == null ? null : fkDictionaryTypeName.trim();
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public Long getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Long fCreateId) {
        this.fCreateId = fCreateId;
    }

    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser == null ? null : fCreateUser.trim();
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName == null ? null : fCreateName.trim();
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Long getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Long fUpdateId) {
        this.fUpdateId = fUpdateId;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser == null ? null : fUpdateUser.trim();
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName == null ? null : fUpdateName.trim();
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}