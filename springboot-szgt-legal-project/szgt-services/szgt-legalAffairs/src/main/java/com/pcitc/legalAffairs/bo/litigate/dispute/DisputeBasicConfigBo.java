package com.pcitc.legalAffairs.bo.litigate.dispute;

import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeBasicConfigVo;

import java.util.List;

public class DisputeBasicConfigBo {

    private Long fId;

    private Long fkOrgId;

    private String fkOrgName;

    private Integer fkDictionaryTypeId;

    private String fkDictionaryTypeName;

    private Integer fSelectValue;

    private String fSelectName;

    private Integer fState;

    private Integer fType;

//    private String fContent;

    private List<DisputeBasicConfigVo> basicConfig;

    public List<DisputeBasicConfigVo> getBasicConfig() {
        return basicConfig;
    }

    public void setBasicConfig(List<DisputeBasicConfigVo> basicConfig) {
        this.basicConfig = basicConfig;
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


    public Integer getfSelectValue() {
        return fSelectValue;
    }

    public void setfSelectValue(Integer fSelectValue) {
        this.fSelectValue = fSelectValue;
    }

    public String getfSelectName() {
        return fSelectName;
    }

    public void setfSelectName(String fSelectName) {
        this.fSelectName = fSelectName == null ? null : fSelectName.trim();
    }
}