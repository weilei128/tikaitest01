package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

public class FwIntermediaryQualifiInfoBo {
    /**
     * 中介机构资质信息主键
     */
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 专业领域名称
     */
    private String fProfessionName;
    /**
     * 专业领域编码
     */
    private String fProfessionCode;
    /**
     * 该领域主要合伙人或顾问
     */
    private String fPartnerCounselor;
    /**
     * 主要合伙人或顾问总人数
     */
    private Integer fPartnerTotal;
    /**
     * 该领域主要律师
     */
    private String fLawyer;
    /**
     * 主要律师总数
     */
    private Integer fLawyerTotal;
    /**
     * 该业务领域代表性业绩
     */
    private String fRepresPerfor;
    /**
     * 代表性业绩附件文件名称
     */
    private String fRepresPerforFileName;
    /**
     * 代表性业绩附件
     */
    private String fkPerforAttachId;

    private String fCreateuser;
    private String fCreatename;
    private Date fCreatetime;
    private String fUpdateuser;
    private String fUpdatename;
    private Date fUpdatetime;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public String getfProfessionName() {
        return fProfessionName;
    }

    public void setfProfessionName(String fProfessionName) {
        this.fProfessionName = fProfessionName;
    }

    public String getfProfessionCode() {
        return fProfessionCode;
    }

    public void setfProfessionCode(String fProfessionCode) {
        this.fProfessionCode = fProfessionCode;
    }

    public String getfPartnerCounselor() {
        return fPartnerCounselor;
    }

    public void setfPartnerCounselor(String fPartnerCounselor) {
        this.fPartnerCounselor = fPartnerCounselor;
    }

    public Integer getfPartnerTotal() {
        return fPartnerTotal;
    }

    public void setfPartnerTotal(Integer fPartnerTotal) {
        this.fPartnerTotal = fPartnerTotal;
    }

    public String getfLawyer() {
        return fLawyer;
    }

    public void setfLawyer(String fLawyer) {
        this.fLawyer = fLawyer;
    }

    public Integer getfLawyerTotal() {
        return fLawyerTotal;
    }

    public void setfLawyerTotal(Integer fLawyerTotal) {
        this.fLawyerTotal = fLawyerTotal;
    }

    public String getfRepresPerfor() {
        return fRepresPerfor;
    }

    public void setfRepresPerfor(String fRepresPerfor) {
        this.fRepresPerfor = fRepresPerfor;
    }

    public String getfRepresPerforFileName() {
        return fRepresPerforFileName;
    }

    public void setfRepresPerforFileName(String fRepresPerforFileName) {
        this.fRepresPerforFileName = fRepresPerforFileName;
    }

    public String getFkPerforAttachId() {
        return fkPerforAttachId;
    }

    public void setFkPerforAttachId(String fkPerforAttachId) {
        this.fkPerforAttachId = fkPerforAttachId;
    }

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }
}
