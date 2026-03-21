package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

public class FwIntermediaryOrgBasicBo {
    /**
     * 主键
     */
    private Long fId;
    /**
     * 关联法律机构聘用信息主键
     */
    private Long fkHireInfoId;
    
    private String fCode;
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
     * 成立时间
     */
    private Date fRegisterTime;
    /**
     * 负责人
     */
    private String fResponsibleOfficer;
    /**
     * 联系方式
     */
    private String fContactWay;
    /**
     * 登记住所
     */
    private String fRegisterDomicile;
    /**
     * 实际详细办公地址
     */
    private String fRealOfficeAddress;
    /**
     * 邮政编码
     */
    private String fPostNumber;
    /**
     * 机构单位代码
     */
    private String fOrgUnitCode;
    /**
     * 律师或助理人员数量
     */
    private Integer fLawyersTotal;
    /**
     * 合伙人或专业人员数量
     */
    private Integer fPartnerCareermanTotal;
    /**
     * 是否有商标代理机构资质，0：否，1：是
     */
    private Short fIsProxyQuali ;
    /**
     * 是否为专利代理机构
     */
    private Short fIsPatentAgency;
    /**
     * 中介机构简介名称
     */
    private String fOrgIntroName;
    /**
     * 中介机构附件文件id
     */
    private String fkOrgIntrFileId;
    /**
     * 执业许可证名称
     */
    private String fLicensePracticeName;
    /**
     * 执业许可证附件文件id
     */
    private  String fkLicensePracticeFileId;
    /**
     * 入库申请书名称
     */
    private String fStorageApplyName;
    /**
     * 入库申请书附件文件id
     */
    private String fkStorageApplyFileId;
    /**
     * 主要客户名单
     */
    private String fMainCustomer;
    /**
     * 与广投集团以往合作情况
     */
    private String fCooperHistory;
    /**
     * 获奖情况
     */
    private String fAwardHistory;
    /**
     * 准入情况说明
     */
    private String fAdmitState;
    /**
     * '附件信息主键
     */
    private Long fkAttachId;
    /**
     * 附件文件名称
     */
    private String fkAttachFileName;
    /**
     * 经办主办部门名称
     */
    private String fkHandleOrgName;
    /**
     * 经办主办部门id
     */
    private Long fkHandleOrgId;
    /**
     * 经办人名称
     */
    private String fAgentName;
    /**
     * 是否准入 0-不准入 1-准入
     */
    private Byte fIsValid;
    /**
     * 解除准入原因
     */
    private String fCause;
    /**
     * 被聘用次数
     */
    private Integer fHiredTimes;
    
    private Date fAdmitTime;

    private String fCreateuser;
    private String fCreatename;
    private Date fCreatetime;
    private String fUpdateuser;
    private String fUpdatename;
    private Date fUpdatetime;
    /**
     * 是否启用，0：不启用；1：启用
     */
    private Integer fState;

    public Long getFkHireInfoId() {
        return fkHireInfoId;
    }

    public void setFkHireInfoId(Long fkHireInfoId) {
        this.fkHireInfoId = fkHireInfoId;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
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

    public Date getfRegisterTime() {
        return fRegisterTime;
    }

    public void setfRegisterTime(Date fRegisterTime) {
        this.fRegisterTime = fRegisterTime;
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

    public String getfRegisterDomicile() {
        return fRegisterDomicile;
    }

    public void setfRegisterDomicile(String fRegisterDomicile) {
        this.fRegisterDomicile = fRegisterDomicile;
    }

    public String getfRealOfficeAddress() {
        return fRealOfficeAddress;
    }

    public void setfRealOfficeAddress(String fRealOfficeAddress) {
        this.fRealOfficeAddress = fRealOfficeAddress;
    }

    public String getfPostNumber() {
        return fPostNumber;
    }

    public void setfPostNumber(String fPostNumber) {
        this.fPostNumber = fPostNumber;
    }

    public String getfOrgUnitCode() {
        return fOrgUnitCode;
    }

    public void setfOrgUnitCode(String fOrgUnitCode) {
        this.fOrgUnitCode = fOrgUnitCode;
    }

    public Integer getfLawyersTotal() {
        return fLawyersTotal;
    }

    public void setfLawyersTotal(Integer fLawyersTotal) {
        this.fLawyersTotal = fLawyersTotal;
    }

    public Integer getfPartnerCareermanTotal() {
        return fPartnerCareermanTotal;
    }

    public void setfPartnerCareermanTotal(Integer fPartnerCareermanTotal) {
        this.fPartnerCareermanTotal = fPartnerCareermanTotal;
    }

    public Short getfIsProxyQuali() {
        return fIsProxyQuali;
    }

    public void setfIsProxyQuali(Short fIsProxyQuali) {
        this.fIsProxyQuali = fIsProxyQuali;
    }

    public Short getfIsPatentAgency() {
        return fIsPatentAgency;
    }

    public void setfIsPatentAgency(Short fIsPatentAgency) {
        this.fIsPatentAgency = fIsPatentAgency;
    }

    public String getfOrgIntroName() {
        return fOrgIntroName;
    }

    public void setfOrgIntroName(String fOrgIntroName) {
        this.fOrgIntroName = fOrgIntroName;
    }

    public String getFkOrgIntrFileId() {
        return fkOrgIntrFileId;
    }

    public void setFkOrgIntrFileId(String fkOrgIntrFileId) {
        this.fkOrgIntrFileId = fkOrgIntrFileId;
    }

    public String getfLicensePracticeName() {
        return fLicensePracticeName;
    }

    public void setfLicensePracticeName(String fLicensePracticeName) {
        this.fLicensePracticeName = fLicensePracticeName;
    }

    public String getFkLicensePracticeFileId() {
        return fkLicensePracticeFileId;
    }

    public void setFkLicensePracticeFileId(String fkLicensePracticeFileId) {
        this.fkLicensePracticeFileId = fkLicensePracticeFileId;
    }

    public String getfStorageApplyName() {
        return fStorageApplyName;
    }

    public void setfStorageApplyName(String fStorageApplyName) {
        this.fStorageApplyName = fStorageApplyName;
    }

    public String getFkStorageApplyFileId() {
        return fkStorageApplyFileId;
    }

    public void setFkStorageApplyFileId(String fkStorageApplyFileId) {
        this.fkStorageApplyFileId = fkStorageApplyFileId;
    }

    public String getfMainCustomer() {
        return fMainCustomer;
    }

    public void setfMainCustomer(String fMainCustomer) {
        this.fMainCustomer = fMainCustomer;
    }

    public String getfCooperHistory() {
        return fCooperHistory;
    }

    public void setfCooperHistory(String fCooperHistory) {
        this.fCooperHistory = fCooperHistory;
    }

    public String getfAwardHistory() {
        return fAwardHistory;
    }

    public void setfAwardHistory(String fAwardHistory) {
        this.fAwardHistory = fAwardHistory;
    }

    public String getfAdmitState() {
        return fAdmitState;
    }

    public void setfAdmitState(String fAdmitState) {
        this.fAdmitState = fAdmitState;
    }

    public Long getFkAttachId() {
        return fkAttachId;
    }

    public void setFkAttachId(Long fkAttachId) {
        this.fkAttachId = fkAttachId;
    }

    public String getFkAttachFileName() {
        return fkAttachFileName;
    }

    public void setFkAttachFileName(String fkAttachFileName) {
        this.fkAttachFileName = fkAttachFileName;
    }

    public String getFkHandleOrgName() {
        return fkHandleOrgName;
    }

    public void setFkHandleOrgName(String fkHandleOrgName) {
        this.fkHandleOrgName = fkHandleOrgName;
    }

    public Long getFkHandleOrgId() {
        return fkHandleOrgId;
    }

    public void setFkHandleOrgId(Long fkHandleOrgId) {
        this.fkHandleOrgId = fkHandleOrgId;
    }

    public String getfAgentName() {
        return fAgentName;
    }

    public void setfAgentName(String fAgentName) {
        this.fAgentName = fAgentName;
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

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

	public String getfCode() {
		return fCode;
	}

	public void setfCode(String fCode) {
		this.fCode = fCode;
	}

	public Byte getfIsValid() {
		return fIsValid;
	}

	public void setfIsValid(Byte fIsValid) {
		this.fIsValid = fIsValid;
	}

	public String getfCause() {
		return fCause;
	}

	public void setfCause(String fCause) {
		this.fCause = fCause;
	}

	public Integer getfHiredTimes() {
		return fHiredTimes;
	}

	public void setfHiredTimes(Integer fHiredTimes) {
		this.fHiredTimes = fHiredTimes;
	}

	public Date getfAdmitTime() {
		return fAdmitTime;
	}

	public void setfAdmitTime(Date fAdmitTime) {
		this.fAdmitTime = fAdmitTime;
	}
}
