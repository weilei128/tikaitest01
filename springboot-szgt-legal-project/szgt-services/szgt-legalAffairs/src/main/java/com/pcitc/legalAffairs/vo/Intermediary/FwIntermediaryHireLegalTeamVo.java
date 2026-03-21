package com.pcitc.legalAffairs.vo.Intermediary;

public class FwIntermediaryHireLegalTeamVo {
    /**
     * 法律团队信息主键
     */
    private Long fId;
    /**
     * 关联中介机构信息主键
     */
    private Long fkOrgBasicId;
    /**
     * 律师名称
     */
    private String fLawyerName;
    /**
     * 专业领域名称
     */
    private String fProFieldName;
    /***
     * 专业领域编码
     */
    private String fProFieldCode;
    /**
     * 主要团队成员
     */
    private String fMainMembers;
    /**
     * 代表性业绩
     */
    private String fReprePerform;
    /**
     * 关联附件主键
     */
    private Long fkPerformAttachId;
    /**
     * 代表性业绩附件名称
     */
    private String fkPerformFileName;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkOrgBasicId() {
        return fkOrgBasicId;
    }

    public void setFkOrgBasicId(Long fkOrgBasicId) {
        this.fkOrgBasicId = fkOrgBasicId;
    }

    public String getfLawyerName() {
        return fLawyerName;
    }

    public void setfLawyerName(String fLawyerName) {
        this.fLawyerName = fLawyerName;
    }

    public String getfProFieldName() {
        return fProFieldName;
    }

    public void setfProFieldName(String fProFieldName) {
        this.fProFieldName = fProFieldName;
    }

    public String getfProFieldCode() {
        return fProFieldCode;
    }

    public void setfProFieldCode(String fProFieldCode) {
        this.fProFieldCode = fProFieldCode;
    }

    public String getfMainMembers() {
        return fMainMembers;
    }

    public void setfMainMembers(String fMainMembers) {
        this.fMainMembers = fMainMembers;
    }

    public String getfReprePerform() {
        return fReprePerform;
    }

    public void setfReprePerform(String fReprePerform) {
        this.fReprePerform = fReprePerform;
    }

    public Long getFkPerformAttachId() {
        return fkPerformAttachId;
    }

    public void setFkPerformAttachId(Long fkPerformAttachId) {
        this.fkPerformAttachId = fkPerformAttachId;
    }

    public String getFkPerformFileName() {
        return fkPerformFileName;
    }

    public void setFkPerformFileName(String fkPerformFileName) {
        this.fkPerformFileName = fkPerformFileName;
    }
}
