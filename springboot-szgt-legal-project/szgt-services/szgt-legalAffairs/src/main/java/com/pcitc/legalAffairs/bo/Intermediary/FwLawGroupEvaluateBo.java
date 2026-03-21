package com.pcitc.legalAffairs.bo.Intermediary;

import java.math.BigDecimal;
import java.util.Date;

public class FwLawGroupEvaluateBo {
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

    public Long getFkLegalTeamId() {
        return fkLegalTeamId;
    }

    public void setFkLegalTeamId(Long fkLegalTeamId) {
        this.fkLegalTeamId = fkLegalTeamId;
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
}
