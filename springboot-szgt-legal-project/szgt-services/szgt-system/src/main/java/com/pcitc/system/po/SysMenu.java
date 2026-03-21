package com.pcitc.system.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "sys_menu")
public class SysMenu {
    @TableId
    private Integer fId;

    private String fCode;

    private String fName;

    private Integer fkMenuGroupId;

    private String fkMenuGroupName;

    private String fUrl;

    private String fIcon;

    private Integer fkParentId;

    private String fkParentName;

    private Integer fLevel;

    private String fDescription;

    private Integer fState;

    private Integer fSort;

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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public Integer getFkMenuGroupId() {
        return fkMenuGroupId;
    }

    public void setFkMenuGroupId(Integer fkMenuGroupId) {
        this.fkMenuGroupId = fkMenuGroupId;
    }

    public String getFkMenuGroupName() {
        return fkMenuGroupName;
    }

    public void setFkMenuGroupName(String fkMenuGroupName) {
        this.fkMenuGroupName = fkMenuGroupName == null ? null : fkMenuGroupName.trim();
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl == null ? null : fUrl.trim();
    }

    public String getfIcon() {
        return fIcon;
    }

    public void setfIcon(String fIcon) {
        this.fIcon = fIcon == null ? null : fIcon.trim();
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
        this.fkParentName = fkParentName == null ? null : fkParentName.trim();
    }

    public Integer getfLevel() {
        return fLevel;
    }

    public void setfLevel(Integer fLevel) {
        this.fLevel = fLevel;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
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