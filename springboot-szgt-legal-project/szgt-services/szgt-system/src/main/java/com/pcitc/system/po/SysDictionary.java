package com.pcitc.system.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;


@TableName(value = "sys_dictionary")
public class SysDictionary {
    @TableId
    private Integer fId;

    private String fKey;

    private String fName;

    private String fCnName;

    private String fContent;

    private Integer fkDictionaryCategoryId;

    private String fkDictionaryCategoryName;

    private String fCategory;

    private Integer fState;

    private Integer fSort;

    private Integer fType;

    private Integer fIsdel;

    private String fCreateUser;

    private String fCreateName;

    private Date fCreateTime;

    private String fUpdateUser;

    private String fUpdateName;

    private Date fUpdateTime;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfKey() {
        return fKey;
    }

    public void setfKey(String fKey) {
        this.fKey = fKey == null ? null : fKey.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfCnName() {
        return fCnName;
    }

    public void setfCnName(String fCnName) {
        this.fCnName = fCnName == null ? null : fCnName.trim();
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent == null ? null : fContent.trim();
    }

    public Integer getFkDictionaryCategoryId() {
        return fkDictionaryCategoryId;
    }

    public void setFkDictionaryCategoryId(Integer fkDictionaryCategoryId) {
        this.fkDictionaryCategoryId = fkDictionaryCategoryId;
    }

    public String getFkDictionaryCategoryName() {
        return fkDictionaryCategoryName;
    }

    public void setFkDictionaryCategoryName(String fkDictionaryCategoryName) {
        this.fkDictionaryCategoryName = fkDictionaryCategoryName == null ? null : fkDictionaryCategoryName.trim();
    }

    public String getfCategory() {
        return fCategory;
    }

    public void setfCategory(String fCategory) {
        this.fCategory = fCategory == null ? null : fCategory.trim();
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
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