package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 纠纷结案信息表
 */
@TableName(value = "fw_litigate_dispute_settled")
public class FwLitigateDisputeSettled implements Serializable {
	
	@TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 纠纷ID
     */
    private Long fkDisputeId;

    /**
     * 纠纷名称
     */
    private String fkDisputeName;

    /**
     * 办理结果
     */
    private String fResult;

    /**
     * 管理建议
     */
    private String fAdvice;

    /**
     * 结案时间
     */
    private Date fDate;

    /**
     * 挽回损失(万)
     */
    private BigDecimal fLossRecovered;

    /**
     * 挽回损失币种
     */
    private Integer fLossRecoveredCurrency;

    /**
     * 避免损失(万)
     */
    private BigDecimal fLossAvoided;

    /**
     * 避免损失币种
     */
    private Integer fLossAvoidedCurrency;

    /**
     * 生效判决书ID
     */
    private Long fkAttachmentId;

    /**
     * 生效判决书路径
     */
    private String fkAttachmentPath;

    /**
     * 生效判决书文件名
     */
    private String fkAttachmentName;

    /**
     * 生效判决书扩展名
     */
    private String fkAttachmentExt;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableLogic
    private Integer fIsdel;

    private Long fCreateId;

    /**
     * 创建人账号
     */
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    private String fCreateName;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    private Long fUpdateId;

    /**
     * 修改人账号
     */
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    private String fUpdateName;

    /**
     * 修改时间
     */
    private Date fUpdateTime;

    /**
     *  败诉金额
     */
    private BigDecimal flossLosingAmount;

    /**
     * 败诉金额币种
     */
    private Integer flossLosingAmountCurrency;


    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkDisputeId() {
        return fkDisputeId;
    }

    public void setFkDisputeId(Long fkDisputeId) {
        this.fkDisputeId = fkDisputeId;
    }

    public String getFkDisputeName() {
        return fkDisputeName;
    }

    public void setFkDisputeName(String fkDisputeName) {
        this.fkDisputeName = fkDisputeName;
    }

    public String getfResult() {
        return fResult;
    }

    public void setfResult(String fResult) {
        this.fResult = fResult;
    }

    public String getfAdvice() {
        return fAdvice;
    }

    public void setfAdvice(String fAdvice) {
        this.fAdvice = fAdvice;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public BigDecimal getfLossRecovered() {
        return fLossRecovered;
    }

    public void setfLossRecovered(BigDecimal fLossRecovered) {
        this.fLossRecovered = fLossRecovered;
    }

    public Integer getfLossRecoveredCurrency() {
        return fLossRecoveredCurrency;
    }

    public void setfLossRecoveredCurrency(Integer fLossRecoveredCurrency) {
        this.fLossRecoveredCurrency = fLossRecoveredCurrency;
    }

    public BigDecimal getfLossAvoided() {
        return fLossAvoided;
    }

    public void setfLossAvoided(BigDecimal fLossAvoided) {
        this.fLossAvoided = fLossAvoided;
    }

    public Integer getfLossAvoidedCurrency() {
        return fLossAvoidedCurrency;
    }

    public void setfLossAvoidedCurrency(Integer fLossAvoidedCurrency) {
        this.fLossAvoidedCurrency = fLossAvoidedCurrency;
    }

    public Long getFkAttachmentId() {
        return fkAttachmentId;
    }

    public void setFkAttachmentId(Long fkAttachmentId) {
        this.fkAttachmentId = fkAttachmentId;
    }

    public String getFkAttachmentPath() {
        return fkAttachmentPath;
    }

    public void setFkAttachmentPath(String fkAttachmentPath) {
        this.fkAttachmentPath = fkAttachmentPath;
    }

    public String getFkAttachmentName() {
        return fkAttachmentName;
    }

    public void setFkAttachmentName(String fkAttachmentName) {
        this.fkAttachmentName = fkAttachmentName;
    }

    public String getFkAttachmentExt() {
        return fkAttachmentExt;
    }

    public void setFkAttachmentExt(String fkAttachmentExt) {
        this.fkAttachmentExt = fkAttachmentExt;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public Long getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Long fCreateId) {
        this.fCreateId = fCreateId;
    }

    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser;
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Long getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Long fUpdateId) {
        this.fUpdateId = fUpdateId;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser;
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    public BigDecimal getFlossLosingAmount() {
        return flossLosingAmount;
    }

    public void setFlossLosingAmount(BigDecimal flossLosingAmount) {
        this.flossLosingAmount = flossLosingAmount;
    }

    public Integer getFlossLosingAmountCurrency() {
        return flossLosingAmountCurrency;
    }

    public void setFlossLosingAmountCurrency(Integer flossLosingAmountCurrency) {
        this.flossLosingAmountCurrency = flossLosingAmountCurrency;
    }
}