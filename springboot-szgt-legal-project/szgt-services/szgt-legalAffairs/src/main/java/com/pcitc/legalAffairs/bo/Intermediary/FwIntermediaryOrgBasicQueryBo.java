package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

public class FwIntermediaryOrgBasicQueryBo {
    /**
     * 中介机构名称
     */
    private String fOrgName;
    /**
     * 工作流状态
     */
    private Integer fWorkFlowId;
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
     * 成立时间(起始)
     */
    private Date fRegisterStartTime;
    /**
     * 终止时间(起始)
     */
    private Date fRegisterEndTime;
    /**
     * 负责人
     */
    private String fResponsibleOfficer;
    /**
     * 登记住所
     */
    private String fRegisterDomicile;
    /**
     * 实际详细办公地址
     */
    private String fRealOfficeAddress;
    /**
     * 律师或助理人员数量区间最小值
     */
    private Integer fLawyersTotalMin;
    /**
     * 律师或助理人员数量区间最大值
     */
    private Integer fLawyersTotalMax;
    /**
     * 合伙人或专业人员数量区间最小值
     */
    private Integer fPartnerCareermanTotalMin;
    /**
     * 合伙人或专业人员数量区间最大值
     */
    private Integer fPartnerCareermanTotalMax;

    /**
     * 专业领域名称
     */
    private String fProfessionName;
    /**
     * 专业领域编码
     */
    private String fProfessionCode;
    /**
     * 是否有商标代理机构资质，0：否，1：是
     */
    private Short fIsProxyQuali ;
    /**
     * 是否为专利代理机构
     */
    private Short fIsPatentAgency;
    /**
     * 是否启用 0-否 1-是
     */
    private Byte fState;

    private int pageIndex;
    private int pageSize;
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

    public Date getfRegisterStartTime() {
        return fRegisterStartTime;
    }

    public void setfRegisterStartTime(Date fRegisterStartTime) {
        this.fRegisterStartTime = fRegisterStartTime;
    }

    public Date getfRegisterEndTime() {
        return fRegisterEndTime;
    }

    public void setfRegisterEndTime(Date fRegisterEndTime) {
        this.fRegisterEndTime = fRegisterEndTime;
    }

    public String getfResponsibleOfficer() {
        return fResponsibleOfficer;
    }

    public void setfResponsibleOfficer(String fResponsibleOfficer) {
        this.fResponsibleOfficer = fResponsibleOfficer;
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

    public Integer getfLawyersTotalMin() {
        return fLawyersTotalMin;
    }

    public void setfLawyersTotalMin(Integer fLawyersTotalMin) {
        this.fLawyersTotalMin = fLawyersTotalMin;
    }

    public Integer getfLawyersTotalMax() {
        return fLawyersTotalMax;
    }

    public void setfLawyersTotalMax(Integer fLawyersTotalMax) {
        this.fLawyersTotalMax = fLawyersTotalMax;
    }

    public Integer getfPartnerCareermanTotalMin() {
        return fPartnerCareermanTotalMin;
    }

    public void setfPartnerCareermanTotalMin(Integer fPartnerCareermanTotalMin) {
        this.fPartnerCareermanTotalMin = fPartnerCareermanTotalMin;
    }

    public Integer getfPartnerCareermanTotalMax() {
        return fPartnerCareermanTotalMax;
    }

    public void setfPartnerCareermanTotalMax(Integer fPartnerCareermanTotalMax) {
        this.fPartnerCareermanTotalMax = fPartnerCareermanTotalMax;
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	public Integer getfWorkFlowId() {
		return fWorkFlowId;
	}

	public void setfWorkFlowId(Integer fWorkFlowId) {
		this.fWorkFlowId = fWorkFlowId;
	}

	public Byte getfState() {
		return fState;
	}

	public void setfState(Byte fState) {
		this.fState = fState;
	}
}
