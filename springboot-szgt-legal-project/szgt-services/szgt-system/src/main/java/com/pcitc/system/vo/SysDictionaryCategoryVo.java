package com.pcitc.system.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SysDictionaryCategoryVo {

    private List<SysDictionaryCategoryVo> childNodeList = new ArrayList<>();

    private Integer fId;

    private String fName;

    private String fCnName;

    private Integer fkParentId;

    private String fkParentName;

    private Integer fLevel;

    private Integer fState;

    private Integer fSort;

    private Integer fType;

    public List<SysDictionaryCategoryVo> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<SysDictionaryCategoryVo> childNodeList) {
        this.childNodeList = childNodeList;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfCnName() {
        return fCnName;
    }

    public void setfCnName(String fCnName) {
        this.fCnName = fCnName;
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
}