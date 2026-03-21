package com.pcitc.legalAffairs.bo.punish;

import java.math.BigDecimal;
import java.util.List;

public class PunishInfoBo {
	private Long fId;

    /**
     * 工作流状态id(1.审批完成 2.审批退回)
     */
    private Integer fWorkFlowId;

    /**
     * 被罚企业ID
     */
    private Long fCompanyId;

    /**
     * 被罚企业名称
     */
    private String fCompanyName;

    /**
     * 统一社会信用代码
     */
    private String fUscCode;

    /**
     * 处罚决定文号
     */
    private String fDocNo;

    /**
     * 处罚类别
     */
    private String fPunishType;
    
    /**
     * 罚款金额
     */
    private BigDecimal fFineAmt;
    
    /**
     * 没收金额
     */
    private BigDecimal fConfiscateAmt;

    /**
     * 处罚事由
     */
    private String fCause;

    /**
     * 处罚内容
     */
    private String fPunishContent;

    /**
     * 执行情况
     */
    private String fImplememtation;

    /**
     * 处罚日期
     */	
	private String fPunishDateString;

    /**
     * 行政处罚机关
     */
    private String fPunishAgency;

    /**
     * 是否发案
     */
    private Integer fIsIncidence;

    /**
     * 填报部门ID
     */
    private Long fReportOrgId;

    /**
     * 填报部门
     */
    private String fReportOrgName;

    /**
     * 填报人ID
     */
    private Long fReportPersonId;

    /**
     * 填报人
     */
    private String fReportPersonName;

    /**
     * 填报人联系方式
     */
    private String fReportPersonTel;

    /**
     * 填报日期
     */
	private String fReportDateString;

	private Integer fState;
	
	private List<PunishProgressBo> progress;
	
	private List<PunishFileBo> files;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Integer getfWorkFlowId() {
        return fWorkFlowId;
    }

    public void setfWorkFlowId(Integer fWorkFlowId) {
        this.fWorkFlowId = fWorkFlowId;
    }

    public Long getfCompanyId() {
        return fCompanyId;
    }

    public void setfCompanyId(Long fCompanyId) {
        this.fCompanyId = fCompanyId;
    }

    public String getfCompanyName() {
        return fCompanyName;
    }

    public void setfCompanyName(String fCompanyName) {
        this.fCompanyName = fCompanyName;
    }

    public String getfUscCode() {
        return fUscCode;
    }

    public void setfUscCode(String fUscCode) {
        this.fUscCode = fUscCode;
    }

    public String getfDocNo() {
        return fDocNo;
    }

    public void setfDocNo(String fDocNo) {
        this.fDocNo = fDocNo;
    }

    public String getfPunishType() {
        return fPunishType;
    }

    public void setfPunishType(String fPunishType) {
        this.fPunishType = fPunishType;
    }

    public BigDecimal getfFineAmt() {
		return fFineAmt;
	}

	public void setfFineAmt(BigDecimal fFineAmt) {
		this.fFineAmt = fFineAmt;
	}

	public BigDecimal getfConfiscateAmt() {
		return fConfiscateAmt;
	}

	public void setfConfiscateAmt(BigDecimal fConfiscateAmt) {
		this.fConfiscateAmt = fConfiscateAmt;
	}

	public String getfCause() {
        return fCause;
    }

    public void setfCause(String fCause) {
        this.fCause = fCause;
    }

    public String getfPunishContent() {
        return fPunishContent;
    }

    public void setfPunishContent(String fPunishContent) {
        this.fPunishContent = fPunishContent;
    }

    public String getfImplememtation() {
        return fImplememtation;
    }

    public void setfImplememtation(String fImplememtation) {
        this.fImplememtation = fImplememtation;
    }
	
	public String getfPunishDateString() {
		return fPunishDateString;
	}

	public void setfPunishDateString(String fPunishDateString) {
		this.fPunishDateString = fPunishDateString;
	}

    public String getfPunishAgency() {
        return fPunishAgency;
    }

    public void setfPunishAgency(String fPunishAgency) {
        this.fPunishAgency = fPunishAgency;
    }

    public Integer getfIsIncidence() {
        return fIsIncidence;
    }

    public void setfIsIncidence(Integer fIsIncidence) {
        this.fIsIncidence = fIsIncidence;
    }

    public Long getfReportOrgId() {
        return fReportOrgId;
    }

    public void setfReportOrgId(Long fReportOrgId) {
        this.fReportOrgId = fReportOrgId;
    }

    public String getfReportOrgName() {
        return fReportOrgName;
    }

    public void setfReportOrgName(String fReportOrgName) {
        this.fReportOrgName = fReportOrgName;
    }

    public Long getfReportPersonId() {
        return fReportPersonId;
    }

    public void setfReportPersonId(Long fReportPersonId) {
        this.fReportPersonId = fReportPersonId;
    }

    public String getfReportPersonName() {
        return fReportPersonName;
    }

    public void setfReportPersonName(String fReportPersonName) {
        this.fReportPersonName = fReportPersonName;
    }

    public String getfReportPersonTel() {
        return fReportPersonTel;
    }

    public void setfReportPersonTel(String fReportPersonTel) {
        this.fReportPersonTel = fReportPersonTel;
    }

	public String getfReportDateString() {
		return fReportDateString;
	}

	public void setfReportDateString(String fReportDateString) {
		this.fReportDateString = fReportDateString;
	}

	public Integer getfState() {
		return fState;
	}

	public void setfState(Integer fState) {
		this.fState = fState;
	}

	public List<PunishProgressBo> getProgress() {
		return progress;
	}

	public void setProgress(List<PunishProgressBo> progress) {
		this.progress = progress;
	}

	public List<PunishFileBo> getFiles() {
		return files;
	}

	public void setFiles(List<PunishFileBo> files) {
		this.files = files;
	}

}
