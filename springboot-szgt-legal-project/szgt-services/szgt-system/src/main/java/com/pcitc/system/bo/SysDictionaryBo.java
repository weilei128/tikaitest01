package com.pcitc.system.bo;

public class SysDictionaryBo {
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
        this.fKey = fKey;
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

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
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
        this.fkDictionaryCategoryName = fkDictionaryCategoryName;
    }

    public String getfCategory() {
        return fCategory;
    }

    public void setfCategory(String fCategory) {
        this.fCategory = fCategory;
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