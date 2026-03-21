package com.pcitc.system.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SysMenuBo {

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
        this.fkMenuGroupName = fkMenuGroupName;
    }

    public String getfUrl() {
        return fUrl;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl;
    }

    public String getfIcon() {
        return fIcon;
    }

    public void setfIcon(String fIcon) {
        this.fIcon = fIcon;
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
        this.fDescription = fDescription;
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
}