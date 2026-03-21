package com.pcitc.legalAffairs.vo.Intermediary;

import java.math.BigDecimal;

public class FwOrgLawGroupEvaluatePageVo {
    /**
     *律师考评信息主键
     */
    private Long fId;
    /**
     * 关联律师团队信息主键
     */
    private Long fkLegalTeamId;
    /**
     * 团队业务能力
     */
    private Integer fTeamBusiAbili;
    /**
     * 团队工作态度
     */
    private Integer fTeamWorkAtti;
    /**
     * 团队服务质量
     */
    private Integer fTeamServeQua;
    /**
     * 团队工时费率
     */
    private Integer fTeamManhour;
    /**
     * 团队服务创效
     */
    private Integer fTeamServeEffect;

    /**
     * 不良记录系数
     */
    private BigDecimal fBadRecordCoef;
    /**
     * 重大影响系数
     */
    private BigDecimal fBigAffectCoef;
    /**
     * 最终系数
     */
    private BigDecimal fFinalCoef;
    /**
     * 最终分数
     */
    private BigDecimal fFinalPoints;
    /**
     * 考评总结
     */
    private String fEvalSum;


    /**
     * 关联中介机构信息主键
     */
    private Long fkOrgBasicId;

    /**
     * 律师名称
     */
    private String fLawyerName;



    /**
     * 中介机构名称
     */
    private String fOrgName;
    /**
     * 统一社会信用代码
     */
    private String fSocialCreditCode;
    /**
     * 机构类型名称
     */
    private String fOrgTypeName;
    /**
     * 机构类型编码
     */
    private String fOrgTypeCode;

    /**
     * 负责人
     */
    private String fResponsibleOfficer;
    /**
     * 联系方式
     */
    private String fContactWay;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkLegalTeamId() {
        return fkLegalTeamId;
    }

    public void setFkLegalTeamId(Long fkLegalTeamId) {
        this.fkLegalTeamId = fkLegalTeamId;
    }

    public Integer getfTeamBusiAbili() {
        return fTeamBusiAbili;
    }

    public void setfTeamBusiAbili(Integer fTeamBusiAbili) {
        this.fTeamBusiAbili = fTeamBusiAbili;
    }

    public Integer getfTeamWorkAtti() {
        return fTeamWorkAtti;
    }

    public void setfTeamWorkAtti(Integer fTeamWorkAtti) {
        this.fTeamWorkAtti = fTeamWorkAtti;
    }

    public Integer getfTeamServeQua() {
        return fTeamServeQua;
    }

    public void setfTeamServeQua(Integer fTeamServeQua) {
        this.fTeamServeQua = fTeamServeQua;
    }

    public Integer getfTeamManhour() {
        return fTeamManhour;
    }

    public void setfTeamManhour(Integer fTeamManhour) {
        this.fTeamManhour = fTeamManhour;
    }

    public Integer getfTeamServeEffect() {
        return fTeamServeEffect;
    }

    public void setfTeamServeEffect(Integer fTeamServeEffect) {
        this.fTeamServeEffect = fTeamServeEffect;
    }

    public BigDecimal getfBadRecordCoef() {
        return fBadRecordCoef;
    }

    public void setfBadRecordCoef(BigDecimal fBadRecordCoef) {
        this.fBadRecordCoef = fBadRecordCoef;
    }

    public BigDecimal getfBigAffectCoef() {
        return fBigAffectCoef;
    }

    public void setfBigAffectCoef(BigDecimal fBigAffectCoef) {
        this.fBigAffectCoef = fBigAffectCoef;
    }

    public BigDecimal getfFinalCoef() {
        return fFinalCoef;
    }

    public void setfFinalCoef(BigDecimal fFinalCoef) {
        this.fFinalCoef = fFinalCoef;
    }

    public BigDecimal getfFinalPoints() {
        return fFinalPoints;
    }

    public void setfFinalPoints(BigDecimal fFinalPoints) {
        this.fFinalPoints = fFinalPoints;
    }

    public String getfEvalSum() {
        return fEvalSum;
    }

    public void setfEvalSum(String fEvalSum) {
        this.fEvalSum = fEvalSum;
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

    public String getfOrgName() {
        return fOrgName;
    }

    public void setfOrgName(String fOrgName) {
        this.fOrgName = fOrgName;
    }

    public String getfSocialCreditCode() {
        return fSocialCreditCode;
    }

    public void setfSocialCreditCode(String fSocialCreditCode) {
        this.fSocialCreditCode = fSocialCreditCode;
    }

    public String getfOrgTypeName() {
        return fOrgTypeName;
    }

    public void setfOrgTypeName(String fOrgTypeName) {
        this.fOrgTypeName = fOrgTypeName;
    }

    public String getfOrgTypeCode() {
        return fOrgTypeCode;
    }

    public void setfOrgTypeCode(String fOrgTypeCode) {
        this.fOrgTypeCode = fOrgTypeCode;
    }

    public String getfResponsibleOfficer() {
        return fResponsibleOfficer;
    }

    public void setfResponsibleOfficer(String fResponsibleOfficer) {
        this.fResponsibleOfficer = fResponsibleOfficer;
    }

    public String getfContactWay() {
        return fContactWay;
    }

    public void setfContactWay(String fContactWay) {
        this.fContactWay = fContactWay;
    }
}
