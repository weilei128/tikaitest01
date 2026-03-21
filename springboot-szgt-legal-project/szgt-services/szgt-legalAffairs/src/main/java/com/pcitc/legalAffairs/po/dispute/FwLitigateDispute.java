package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 诉前争议信息表
 */
@TableName(value = "fw_litigate_dispute")
public class FwLitigateDispute implements Serializable {
	
	@TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 案件编号
     */
    private String fCode;
    
    /**
     * 案号
     */
    private String fCaseCode;

    /**
     * 纠纷名称
     */
    private String fName;
    
    /**
     * 案件行政区域-省
     */
    private String fProvince;
    /**
     * 案件行政区域-市
     */
    private String fPrefecture;
    /**
     * 案件行政区域-县区
     */
    private String fRegion;
    
    /**
     * 保存时间
     */
    private Date fSaveTime;

    /**
     * 是否涉外纠纷
     */
    private Byte fIsForeignRelated;

    /**
     * 案发时间
     */
    private Date fIncidentDate;

    /**
     * 纠纷类别
     */
    private String fType;
    
    /**
     * 纠纷类别2
     */
    @TableField("f_Type_2")
    private String fType2;
    
    /**
     * 纠纷类别3
     */
    @TableField("f_Type_3")
    private String fType3;

    /**
     * 是否涉刑
     */
    private Byte fIsCriminalInvolved;

    /**
     * 内外部纠纷 0内部 1外部
     */
    private Long fIsExternal;

    /**
     * 处理方式
     */
    private Long fSettleMethod;

    /**
     * 纠纷性质 0一般 1重大
     */
    private Long fIsMajor;

    /**
     * 涉及金额
     */
    private BigDecimal fRelatedAmount;

    /**
     * 涉及金额的币种
     */
    private Long fRelatedCurrency;

    /**
     * 是否涉及维稳
     */
    private Byte fIsAboutPetition;

    /**
     * 信访案件ID
     */
    private Long fkPetitionId;

    /**
     * 信访案件名称
     */
    private String fkPetitionName;

    /**
     * 是否债权清收
     */
    private Byte fIsDebtCollection;

    /**
     * 是否财产保全
     */
    private Byte fIsPropertyPreservation;

    /**
     * 是否有担保
     */
    private Byte fIsGuaranted;
    
    /**
     * 是否有预计损失
     */
    private Byte fHasLossEstimated;
    
    /**
     * 预计损失(万)
     */
    private BigDecimal fEstimatedLossAmount;
    
    /**
     * 预计损失币种
     */
    private Long fEstimatedLossCurrency;

    /**
     * 案件描述
     */
    private String fDescription;

    /**
     * 拟处理方案
     */
    private String fPlan;

    /**
     * 附件ID
     */
    private Long fkAttachmentId;

    /**
     * 附件路径
     */
    private String fkAttachmentPath;

    /**
     * 附件文件名
     */
    private String fkAttachmentName;

    /**
     * 附件扩展名
     */
    private String fkAttachmentExt;

    /**
     * 填报部门ID
     */
    private Long fkReportedOrgId;

    /**
     * 填报部门编码
     */
    private String fkReportedOrgCode;

    /**
     * 填报部门名称
     */
    private String fkReportedOrgName;

    /**
     * 填报人ID
     */
    private Long fkReportedPersonId;
    
    /**
     * 填报人姓名
     */
    private String fkReportedPersonName;
    
    /**
     * 属性
     */
    private Integer fkAttribute;

    /**
     * 关联历史案件ID
     */
    private Long fkHistoryId;
    
    /**
     * 关联历史案件名称
     */
    private String fkHistoryName;
    
    /**
     * 是否进入诉讼
     */
    private Byte fIsDiscard;

    /**
     * 单据状态(办结 废弃)
     */
    private Byte fStatus;

    /**
     * 废弃原因
     */
    private String fDiscardDescription;

    /**
     * 办结时间
     */
    private Date fSettleDate;

    /**
     * 办结信息
     */
    private String fSettleDescription;

    /**
     * 备注
     */
    private String fRemark;

    /**
     * 是否已归档
     */
    private Integer fArchived;

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

    /***
     * 工作流状态id(1.诉求争议录入审批完成 2.诉求争议录入审批退回 3.纠纷填报审批完成 4.纠纷填报审批退回)
     */
    private Integer fWorkFlowId;
    
    private Integer fUserId;
    /**
     * 来自 0-本系统 1-合同
     */
    private Integer fSource;
    /**
     * 外部系统ID
     */
    private String fExternalId;
    
    private Integer fSend;

    /**
     * 财产保全额
     */
    private BigDecimal propertyPreservation;
    /**
     * 是否经济类纠纷
     */
    private Integer isEconomic;
    /**
     * 经济类纠纷-金额
     */
    private BigDecimal economicAmt;
    /**
     * 经济类纠纷-利息
     */
    private BigDecimal economicInterest;
    /**
     * 经济类纠纷-违约金
     */
    private BigDecimal economicLiquidatedDamage;
    /**
     * 经济类纠纷-股权
     */
    private BigDecimal economicEquity;
    /**
     * 被冻结金额
     */
    private BigDecimal amtFrozen;
    /**
     * 冻结他人金额
     */
    private BigDecimal oppositeAmtFrozen;
    /**
     * 风险等级
     */
    private String riskLvl;

    public Integer getfWorkFlowId() {
        return fWorkFlowId;
    }

    public void setfWorkFlowId(Integer fWorkFlowId) {
        this.fWorkFlowId = fWorkFlowId;
    }

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
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

    public Byte getfIsForeignRelated() {
        return fIsForeignRelated;
    }

    public void setfIsForeignRelated(Byte fIsForeignRelated) {
        this.fIsForeignRelated = fIsForeignRelated;
    }

    public Date getfIncidentDate() {
        return fIncidentDate;
    }

    public void setfIncidentDate(Date fIncidentDate) {
        this.fIncidentDate = fIncidentDate;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public Byte getfIsCriminalInvolved() {
        return fIsCriminalInvolved;
    }

    public void setfIsCriminalInvolved(Byte fIsCriminalInvolved) {
        this.fIsCriminalInvolved = fIsCriminalInvolved;
    }

    public Long getfIsExternal() {
        return fIsExternal;
    }

    public void setfIsExternal(Long fIsExternal) {
        this.fIsExternal = fIsExternal;
    }

    public Long getfSettleMethod() {
        return fSettleMethod;
    }

    public void setfSettleMethod(Long fSettleMethod) {
        this.fSettleMethod = fSettleMethod;
    }

    public Long getfIsMajor() {
        return fIsMajor;
    }

    public void setfIsMajor(Long fIsMajor) {
        this.fIsMajor = fIsMajor;
    }

    public BigDecimal getfRelatedAmount() {
        return fRelatedAmount;
    }

    public void setfRelatedAmount(BigDecimal fRelatedAmount) {
        this.fRelatedAmount = fRelatedAmount;
    }

    public Long getfRelatedCurrency() {
        return fRelatedCurrency;
    }

    public void setfRelatedCurrency(Long fRelatedCurrency) {
        this.fRelatedCurrency = fRelatedCurrency;
    }

    public Byte getfIsAboutPetition() {
        return fIsAboutPetition;
    }

    public void setfIsAboutPetition(Byte fIsAboutPetition) {
        this.fIsAboutPetition = fIsAboutPetition;
    }

    public Long getFkPetitionId() {
        return fkPetitionId;
    }

    public void setFkPetitionId(Long fkPetitionId) {
        this.fkPetitionId = fkPetitionId;
    }

    public String getFkPetitionName() {
        return fkPetitionName;
    }

    public void setFkPetitionName(String fkPetitionName) {
        this.fkPetitionName = fkPetitionName;
    }

    public Byte getfIsDebtCollection() {
        return fIsDebtCollection;
    }

    public void setfIsDebtCollection(Byte fIsDebtCollection) {
        this.fIsDebtCollection = fIsDebtCollection;
    }

    public Byte getfIsPropertyPreservation() {
        return fIsPropertyPreservation;
    }

    public void setfIsPropertyPreservation(Byte fIsPropertyPreservation) {
        this.fIsPropertyPreservation = fIsPropertyPreservation;
    }

    public Byte getfIsGuaranted() {
        return fIsGuaranted;
    }

    public void setfIsGuaranted(Byte fIsGuaranted) {
        this.fIsGuaranted = fIsGuaranted;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfPlan() {
        return fPlan;
    }

    public void setfPlan(String fPlan) {
        this.fPlan = fPlan;
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

    public Long getFkReportedOrgId() {
        return fkReportedOrgId;
    }

    public void setFkReportedOrgId(Long fkReportedOrgId) {
        this.fkReportedOrgId = fkReportedOrgId;
    }

	public String getFkReportedOrgCode() {
		return fkReportedOrgCode;
	}

	public void setFkReportedOrgCode(String fkReportedOrgCode) {
		this.fkReportedOrgCode = fkReportedOrgCode;
	}

    public String getFkReportedOrgName() {
        return fkReportedOrgName;
    }

    public void setFkReportedOrgName(String fkReportedOrgName) {
        this.fkReportedOrgName = fkReportedOrgName;
    }

    public Integer getFkAttribute() {
        return fkAttribute;
    }

    public void setFkAttribute(Integer fkAttribute) {
        this.fkAttribute = fkAttribute;
    }

    public Byte getfIsDiscard() {
        return fIsDiscard;
    }

    public void setfIsDiscard(Byte fIsDiscard) {
        this.fIsDiscard = fIsDiscard;
    }

    public Byte getfStatus() {
        return fStatus;
    }

    public void setfStatus(Byte fStatus) {
        this.fStatus = fStatus;
    }

    public String getfDiscardDescription() {
        return fDiscardDescription;
    }

    public void setfDiscardDescription(String fDiscardDescription) {
        this.fDiscardDescription = fDiscardDescription;
    }

    public Date getfSettleDate() {
        return fSettleDate;
    }

    public void setfSettleDate(Date fSettleDate) {
        this.fSettleDate = fSettleDate;
    }

    public String getfSettleDescription() {
        return fSettleDescription;
    }

    public void setfSettleDescription(String fSettleDescription) {
        this.fSettleDescription = fSettleDescription;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfArchived() {
        return fArchived;
    }

    public void setfArchived(Integer fArchived) {
        this.fArchived = fArchived;
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

	public Long getfEstimatedLossCurrency() {
		return fEstimatedLossCurrency;
	}

	public void setfEstimatedLossCurrency(Long fEstimatedLossCurrency) {
		this.fEstimatedLossCurrency = fEstimatedLossCurrency;
	}

	public BigDecimal getfEstimatedLossAmount() {
		return fEstimatedLossAmount;
	}

	public void setfEstimatedLossAmount(BigDecimal fEstimatedLossAmount) {
		this.fEstimatedLossAmount = fEstimatedLossAmount;
	}

	public Byte getfHasLossEstimated() {
		return fHasLossEstimated;
	}

	public void setfHasLossEstimated(Byte fHasLossEstimated) {
		this.fHasLossEstimated = fHasLossEstimated;
	}

	public Long getFkHistoryId() {
		return fkHistoryId;
	}

	public void setFkHistoryId(Long fkHistoryId) {
		this.fkHistoryId = fkHistoryId;
	}

	public String getFkHistoryName() {
		return fkHistoryName;
	}

	public void setFkHistoryName(String fkHistoryName) {
		this.fkHistoryName = fkHistoryName;
	}

	public String getfType2() {
		return fType2;
	}

	public void setfType2(String fType2) {
		this.fType2 = fType2;
	}

	public String getfType3() {
		return fType3;
	}

	public void setfType3(String fType3) {
		this.fType3 = fType3;
	}

	public Integer getfUserId() {
		return fUserId;
	}

	public void setfUserId(Integer fUserId) {
		this.fUserId = fUserId;
	}

	public Date getfSaveTime() {
		return fSaveTime;
	}

	public void setfSaveTime(Date fSaveTime) {
		this.fSaveTime = fSaveTime;
	}

	public Long getFkReportedPersonId() {
		return fkReportedPersonId;
	}

	public void setFkReportedPersonId(Long fkReportedPersonId) {
		this.fkReportedPersonId = fkReportedPersonId;
	}

	public String getFkReportedPersonName() {
		return fkReportedPersonName;
	}

	public void setFkReportedPersonName(String fkReportedPersonName) {
		this.fkReportedPersonName = fkReportedPersonName;
	}

	public String getfCaseCode() {
		return fCaseCode;
	}

	public void setfCaseCode(String fCaseCode) {
		this.fCaseCode = fCaseCode;
	}

	public String getfRegion() {
		return fRegion;
	}

	public void setfRegion(String fRegion) {
		this.fRegion = fRegion;
	}

	public String getfProvince() {
		return fProvince;
	}

	public void setfProvince(String fProvince) {
		this.fProvince = fProvince;
	}

	public String getfPrefecture() {
		return fPrefecture;
	}

	public void setfPrefecture(String fPrefecture) {
		this.fPrefecture = fPrefecture;
	}

	public Integer getfSource() {
		return fSource;
	}

	public void setfSource(Integer fSource) {
		this.fSource = fSource;
	}

	public String getfExternalId() {
		return fExternalId;
	}

	public void setfExternalId(String fExternalId) {
		this.fExternalId = fExternalId;
	}

	public Integer getfSend() {
		return fSend;
	}

	public void setfSend(Integer fSend) {
		this.fSend = fSend;
	}

    public BigDecimal getPropertyPreservation() {
        return propertyPreservation;
    }

    public void setPropertyPreservation(BigDecimal propertyPreservation) {
        this.propertyPreservation = propertyPreservation;
    }

    public Integer getIsEconomic() {
        return isEconomic;
    }

    public void setIsEconomic(Integer isEconomic) {
        this.isEconomic = isEconomic;
    }

    public BigDecimal getEconomicAmt() {
        return economicAmt;
    }

    public void setEconomicAmt(BigDecimal economicAmt) {
        this.economicAmt = economicAmt;
    }

    public BigDecimal getEconomicInterest() {
        return economicInterest;
    }

    public void setEconomicInterest(BigDecimal economicInterest) {
        this.economicInterest = economicInterest;
    }

    public BigDecimal getEconomicLiquidatedDamage() {
        return economicLiquidatedDamage;
    }

    public void setEconomicLiquidatedDamage(BigDecimal economicLiquidatedDamage) {
        this.economicLiquidatedDamage = economicLiquidatedDamage;
    }

    public BigDecimal getEconomicEquity() {
        return economicEquity;
    }

    public void setEconomicEquity(BigDecimal economicEquity) {
        this.economicEquity = economicEquity;
    }

    public BigDecimal getAmtFrozen() {
        return amtFrozen;
    }

    public void setAmtFrozen(BigDecimal amtFrozen) {
        this.amtFrozen = amtFrozen;
    }

    public BigDecimal getOppositeAmtFrozen() {
        return oppositeAmtFrozen;
    }

    public void setOppositeAmtFrozen(BigDecimal oppositeAmtFrozen) {
        this.oppositeAmtFrozen = oppositeAmtFrozen;
    }

    public String getRiskLvl() {
        return riskLvl;
    }

    public void setRiskLvl(String riskLvl) {
        this.riskLvl = riskLvl;
    }
}