package com.pcitc.legalAffairs.po.Intermediary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

/**
 *
 */
@TableName("fw_intermediary_qualifi_info")
public class FwIntermediaryQualifiInfo extends BasePojo {
    /**
     * 中介机构资质信息主键
     */
    @TableId(value = "f_ID",type = IdType.AUTO)
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
     * 代表性业绩附件id
     */
    private String fkPerforAttachId;

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
}
