package com.pcitc.szgt.contract.textmanage.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TextDetailResultVo {

    /**
     * 主键
     */
    private String FileTemplateID;

    /**
     * 文本名称
     */
    private String TextName;

    /**
     * 文本编号
     */
    private String TextCode;

    /**
     * 使用说明
     */
    private String ApplicantExplain;

    /**
     * 文本状态，默认已启用，1草稿 2待审核 3退回 4待启用 5已启用
     */
    private Integer Status;

    /**
     * 删除状态 0否 1是
     */
    private Integer LogicDel;

    /**
     * 合同类别ID,以,分割
     */
    private String TypeTextID;

    /**
     * 合同类别名称,以,分割
     */
    private String TypeText;

    /**
     * 文本使用环节,0订立 1变更
     */
    private Integer TextModelType;

    /**
     * 版本号,每提交一次升一个版本号01,02,03
     */
    private BigDecimal Version;

    /**
     * 使用范围
     */
    private String PubCorps;

    /**
     * 前一个版本id
     */
    private String ParentID;

    /**
     * 标识（同一文本不同版本值相同）
     */
    private String Identity;

    /**
     * 修改说明
     */
    private String ModifiedExplain;

    /**
     * 申请人
     */
    private String Applicant;

    /**
     * 申请时间
     */
    private LocalDateTime ApplicantTime;

    /**
     * 申请企业
     */
    private String ApplicantOrg;

    public String getFileTemplateID() {
        return FileTemplateID;
    }

    public void setFileTemplateID(String fileTemplateID) {
        FileTemplateID = fileTemplateID;
    }

    public String getTextName() {
        return TextName;
    }

    public void setTextName(String textName) {
        TextName = textName;
    }

    public String getTextCode() {
        return TextCode;
    }

    public void setTextCode(String textCode) {
        TextCode = textCode;
    }

    public String getApplicantExplain() {
        return ApplicantExplain;
    }

    public void setApplicantExplain(String applicantExplain) {
        ApplicantExplain = applicantExplain;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer logicDel) {
        LogicDel = logicDel;
    }

    public String getTypeTextID() {
        return TypeTextID;
    }

    public void setTypeTextID(String typeTextID) {
        TypeTextID = typeTextID;
    }

    public String getTypeText() {
        return TypeText;
    }

    public void setTypeText(String typeText) {
        TypeText = typeText;
    }

    public Integer getTextModelType() {
        return TextModelType;
    }

    public void setTextModelType(Integer textModelType) {
        TextModelType = textModelType;
    }

    public BigDecimal getVersion() {
        return Version;
    }

    public void setVersion(BigDecimal version) {
        Version = version;
    }

    public String getPubCorps() {
        return PubCorps;
    }

    public void setPubCorps(String pubCorps) {
        PubCorps = pubCorps;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getModifiedExplain() {
        return ModifiedExplain;
    }

    public void setModifiedExplain(String modifiedExplain) {
        ModifiedExplain = modifiedExplain;
    }

    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String applicant) {
        Applicant = applicant;
    }

    public LocalDateTime getApplicantTime() {
        return ApplicantTime;
    }

    public void setApplicantTime(LocalDateTime applicantTime) {
        ApplicantTime = applicantTime;
    }

    public String getApplicantOrg() {
        return ApplicantOrg;
    }

    public void setApplicantOrg(String applicantOrg) {
        ApplicantOrg = applicantOrg;
    }
}
