package com.pcitc.system.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SysMenuGroupVo {

    private List<SysMenuVo> childNodeList = new ArrayList<>();

    private Integer fId;

    private String fCode;

    private String fName;

    private String fDescription;

    private Integer fState;

    private Integer fSort;

    public List<SysMenuVo> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<SysMenuVo> childNodeList) {
        this.childNodeList = childNodeList;
    }

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