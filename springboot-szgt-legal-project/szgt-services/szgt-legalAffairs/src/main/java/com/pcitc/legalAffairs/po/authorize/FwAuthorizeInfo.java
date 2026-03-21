package com.pcitc.legalAffairs.po.authorize;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 事项授权信息表
 */
@TableName(value = "fw_authorize_info")
public class FwAuthorizeInfo implements Serializable {
	
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 工作流状态id
     */
    private Integer fWorkFlowId;
    private Integer fUserId;

    /**
     * 流水号
     */
    private String fSerialNo;

    /**
     * 授权书序号
     */
    private String fAuthorizationNo;

    /**
     * 经办人ID
     */
    private Long fManagerId;

    /**
     * 经办人姓名
     */
    private String fManagerName;

    /**
     * 经办人所属部门ID
     */
    private Long fkManagerOrgId;

    /**
     * 经办人所属部门名称
     */
    private String fkManagerOrgName;

    /**
     * 经办人电子邮箱
     */
    private String fManagerEmail;

    /**
     * 经办人联系方式
     */
    private String fManagerContact;

    /**
     * 授权类型
     */
    private String fType;

    /**
     * 使用模板ID
     */
    private Long fkTemplateId;
    
    /**
     * 模板路径
     */
    private String fTemplatePath;
    
    /**
     * 模板文件名
     */
    private String fTemplateName;
    
    /**
     * 模板扩展名
     */
    private String fTemplateExt;
    
    /**
     * 模板类型
     */
    private String fTemplateType;

    /**
     * 授权人ID
     */
    private Long fkAuthorizerId;

    /**
     * 授权人姓名
     */
    private String fkAuthorizerName;

    /**
     * 授权人所属机构
     */
    private Long fkAuthorizerOrgId;

    /**
     * 授权人所属机构类型
     */
    private String fkAuthorizerOrgType;

    /**
     * 授权人所属机构名称
     */
    private String fkAuthorizerOrgName;

    /**
     * 法定代表/负责人
     */
    private String fkLegalRepresentative;

    /**
     * 被授权人类型
     */
    private String fLicenseeType;

    /**
     * 被授权人ID
     */
    private Long fkLicenseeId;

    /**
     * 被授权人名称
     */
    private String fLicenseeName;

    /**
     * 被授权人统一社会信用代码
     */
    private String fUscCode;

    /**
     * 被授权人法定代表人/负责人
     */
    private String fLicenseeLegalRepresentative;

    /**
     * 被授权人所属单位ID
     */
    private Long fkLicenseeOrgId;

    /**
     * 被授权人所属单位名称
     */
    private String fkLicenseeOrgName;

    /**
     * 被授权人身份证号
     */
    private String fLicenseeIdCardNo;

    /**
     * 被授权人联系方式
     */
    private String fLicenseeContact;

    /**
     * 被授权人职务
     */
    private String fLicenseePosition;

    /**
     * 授权事项
     */
    private String fAuthorizeMatters;

    /**
     * 授权期限
     */
    private String fAuthorizeLimit;

    /**
     * 授权依据文件ID
     */
    private Long fAccordanceFileId;

    /**
     * 授权依据文件路径
     */
    private String fAccordanceFilePath;

    /**
     * 授权依据文件名
     */
    private String fAccordanceFileName;

    /**
     * 授权依据文件扩展名
     */
    private String fAccordanceFileExt;

    /**
     * 附件ID
     */
    private Long fAdditionalFileId;

    /**
     * 附件路径
     */
    private String fAdditionalFilePath;

    /**
     * 附件文件名
     */
    private String fAdditionalFileName;

    /**
     * 附件扩展名
     */
    private String fAdditionalFileExt;

    /**
     * 注意事项
     */
    private String fCaution;

    /**
     * 备注
     */
    private String fRemark;

    /**
     * 是否用印
     */
    private Byte fUseStamp;

    /**
     * 用印类型(用,分隔)
     */
    private String fUseStampType;

    /**
     * 是否涉密
     */
    private Byte fIsSecret;

    /**
     * 发往单位
     */
    private String fSendTo;

    /**
     * 事由
     */
    private String fCause;

    /**
     * 授权书打印人ID
     */
    private Long fAuthorizationPrintPersonId;

    /**
     * 授权书打印人姓名
     */
    private String fAuthorizationPrintPersonName;

    /**
     * 行权报告填报人ID
     */
    private Long fExerciseReporterId;

    /**
     * 行权报告填报人姓名
     */
    private String fExerciseReporterName;

    /**
     * 取件方式
     */
    private String fPickupMethod;

    /**
     * 事项授权状态
     */
    private Integer fStatus;

    /**
     * 是否废弃
     */
    private Integer fIsDiscard;

    /**
     * 申请时间
     */
    private Date fApplyTime;

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

    private static final long serialVersionUID = 1L;

    public Integer getfWorkFlowId() {
        return fWorkFlowId;
    }

    public void setfWorkFlowId(Integer fWorkFlowId) {
        this.fWorkFlowId = fWorkFlowId;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfSerialNo() {
        return fSerialNo;
    }

    public void setfSerialNo(String fSerialNo) {
        this.fSerialNo = fSerialNo;
    }

    public String getfAuthorizationNo() {
        return fAuthorizationNo;
    }

    public void setfAuthorizationNo(String fAuthorizationNo) {
        this.fAuthorizationNo = fAuthorizationNo;
    }

    public Long getfManagerId() {
        return fManagerId;
    }

    public void setfManagerId(Long fManagerId) {
        this.fManagerId = fManagerId;
    }

    public String getfManagerName() {
        return fManagerName;
    }

    public void setfManagerName(String fManagerName) {
        this.fManagerName = fManagerName;
    }

    public Long getFkManagerOrgId() {
        return fkManagerOrgId;
    }

    public void setFkManagerOrgId(Long fkManagerOrgId) {
        this.fkManagerOrgId = fkManagerOrgId;
    }

    public String getFkManagerOrgName() {
        return fkManagerOrgName;
    }

    public void setFkManagerOrgName(String fkManagerOrgName) {
        this.fkManagerOrgName = fkManagerOrgName;
    }

    public String getfManagerEmail() {
        return fManagerEmail;
    }

    public void setfManagerEmail(String fManagerEmail) {
        this.fManagerEmail = fManagerEmail;
    }

    public String getfManagerContact() {
        return fManagerContact;
    }

    public void setfManagerContact(String fManagerContact) {
        this.fManagerContact = fManagerContact;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfTemplateType() {
        return fTemplateType;
    }

    public void setfTemplateType(String fTemplateType) {
        this.fTemplateType = fTemplateType;
    }

    public Long getFkAuthorizerId() {
        return fkAuthorizerId;
    }

    public void setFkAuthorizerId(Long fkAuthorizerId) {
        this.fkAuthorizerId = fkAuthorizerId;
    }

    public String getFkAuthorizerName() {
        return fkAuthorizerName;
    }

    public void setFkAuthorizerName(String fkAuthorizerName) {
        this.fkAuthorizerName = fkAuthorizerName;
    }

    public Long getFkAuthorizerOrgId() {
        return fkAuthorizerOrgId;
    }

    public void setFkAuthorizerOrgId(Long fkAuthorizerOrgId) {
        this.fkAuthorizerOrgId = fkAuthorizerOrgId;
    }

    public String getFkAuthorizerOrgType() {
        return fkAuthorizerOrgType;
    }

    public void setFkAuthorizerOrgType(String fkAuthorizerOrgType) {
        this.fkAuthorizerOrgType = fkAuthorizerOrgType;
    }

    public String getFkAuthorizerOrgName() {
        return fkAuthorizerOrgName;
    }

    public void setFkAuthorizerOrgName(String fkAuthorizerOrgName) {
        this.fkAuthorizerOrgName = fkAuthorizerOrgName;
    }

    public String getFkLegalRepresentative() {
        return fkLegalRepresentative;
    }

    public void setFkLegalRepresentative(String fkLegalRepresentative) {
        this.fkLegalRepresentative = fkLegalRepresentative;
    }

    public String getfLicenseeType() {
        return fLicenseeType;
    }

    public void setfLicenseeType(String fLicenseeType) {
        this.fLicenseeType = fLicenseeType;
    }

    public Long getFkLicenseeId() {
        return fkLicenseeId;
    }

    public void setFkLicenseeId(Long fkLicenseeId) {
        this.fkLicenseeId = fkLicenseeId;
    }

    public String getfLicenseeName() {
        return fLicenseeName;
    }

    public void setfLicenseeName(String fLicenseeName) {
        this.fLicenseeName = fLicenseeName;
    }

    public String getfUscCode() {
        return fUscCode;
    }

    public void setfUscCode(String fUscCode) {
        this.fUscCode = fUscCode;
    }

    public String getfLicenseeLegalRepresentative() {
        return fLicenseeLegalRepresentative;
    }

    public void setfLicenseeLegalRepresentative(String fLicenseeLegalRepresentative) {
        this.fLicenseeLegalRepresentative = fLicenseeLegalRepresentative;
    }

    public Long getFkLicenseeOrgId() {
        return fkLicenseeOrgId;
    }

    public void setFkLicenseeOrgId(Long fkLicenseeOrgId) {
        this.fkLicenseeOrgId = fkLicenseeOrgId;
    }

    public String getFkLicenseeOrgName() {
        return fkLicenseeOrgName;
    }

    public void setFkLicenseeOrgName(String fkLicenseeOrgName) {
        this.fkLicenseeOrgName = fkLicenseeOrgName;
    }

    public String getfLicenseeIdCardNo() {
        return fLicenseeIdCardNo;
    }

    public void setfLicenseeIdCardNo(String fLicenseeIdCardNo) {
        this.fLicenseeIdCardNo = fLicenseeIdCardNo;
    }

    public String getfLicenseeContact() {
        return fLicenseeContact;
    }

    public void setfLicenseeContact(String fLicenseeContact) {
        this.fLicenseeContact = fLicenseeContact;
    }

    public String getfLicenseePosition() {
        return fLicenseePosition;
    }

    public void setfLicenseePosition(String fLicenseePosition) {
        this.fLicenseePosition = fLicenseePosition;
    }

    public String getfAuthorizeMatters() {
        return fAuthorizeMatters;
    }

    public void setfAuthorizeMatters(String fAuthorizeMatters) {
        this.fAuthorizeMatters = fAuthorizeMatters;
    }

    public String getfAuthorizeLimit() {
        return fAuthorizeLimit;
    }

    public void setfAuthorizeLimit(String fAuthorizeLimit) {
        this.fAuthorizeLimit = fAuthorizeLimit;
    }

    public Long getfAccordanceFileId() {
        return fAccordanceFileId;
    }

    public void setfAccordanceFileId(Long fAccordanceFileId) {
        this.fAccordanceFileId = fAccordanceFileId;
    }

    public String getfAccordanceFilePath() {
        return fAccordanceFilePath;
    }

    public void setfAccordanceFilePath(String fAccordanceFilePath) {
        this.fAccordanceFilePath = fAccordanceFilePath;
    }

    public String getfAccordanceFileName() {
        return fAccordanceFileName;
    }

    public void setfAccordanceFileName(String fAccordanceFileName) {
        this.fAccordanceFileName = fAccordanceFileName;
    }

    public String getfAccordanceFileExt() {
        return fAccordanceFileExt;
    }

    public void setfAccordanceFileExt(String fAccordanceFileExt) {
        this.fAccordanceFileExt = fAccordanceFileExt;
    }

    public Long getfAdditionalFileId() {
        return fAdditionalFileId;
    }

    public void setfAdditionalFileId(Long fAdditionalFileId) {
        this.fAdditionalFileId = fAdditionalFileId;
    }

    public String getfAdditionalFilePath() {
        return fAdditionalFilePath;
    }

    public void setfAdditionalFilePath(String fAdditionalFilePath) {
        this.fAdditionalFilePath = fAdditionalFilePath;
    }

    public String getfAdditionalFileName() {
        return fAdditionalFileName;
    }

    public void setfAdditionalFileName(String fAdditionalFileName) {
        this.fAdditionalFileName = fAdditionalFileName;
    }

    public String getfAdditionalFileExt() {
        return fAdditionalFileExt;
    }

    public void setfAdditionalFileExt(String fAdditionalFileExt) {
        this.fAdditionalFileExt = fAdditionalFileExt;
    }

    public String getfCaution() {
        return fCaution;
    }

    public void setfCaution(String fCaution) {
        this.fCaution = fCaution;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public Byte getfUseStamp() {
        return fUseStamp;
    }

    public void setfUseStamp(Byte fUseStamp) {
        this.fUseStamp = fUseStamp;
    }

    public String getfUseStampType() {
        return fUseStampType;
    }

    public void setfUseStampType(String fUseStampType) {
        this.fUseStampType = fUseStampType;
    }

    public Byte getfIsSecret() {
        return fIsSecret;
    }

    public void setfIsSecret(Byte fIsSecret) {
        this.fIsSecret = fIsSecret;
    }

    public String getfSendTo() {
        return fSendTo;
    }

    public void setfSendTo(String fSendTo) {
        this.fSendTo = fSendTo;
    }

    public String getfCause() {
        return fCause;
    }

    public void setfCause(String fCause) {
        this.fCause = fCause;
    }

    public Long getfAuthorizationPrintPersonId() {
        return fAuthorizationPrintPersonId;
    }

    public void setfAuthorizationPrintPersonId(Long fAuthorizationPrintPersonId) {
        this.fAuthorizationPrintPersonId = fAuthorizationPrintPersonId;
    }

    public String getfAuthorizationPrintPersonName() {
        return fAuthorizationPrintPersonName;
    }

    public void setfAuthorizationPrintPersonName(String fAuthorizationPrintPersonName) {
        this.fAuthorizationPrintPersonName = fAuthorizationPrintPersonName;
    }

    public Long getfExerciseReporterId() {
        return fExerciseReporterId;
    }

    public void setfExerciseReporterId(Long fExerciseReporterId) {
        this.fExerciseReporterId = fExerciseReporterId;
    }

    public String getfExerciseReporterName() {
        return fExerciseReporterName;
    }

    public void setfExerciseReporterName(String fExerciseReporterName) {
        this.fExerciseReporterName = fExerciseReporterName;
    }

    public String getfPickupMethod() {
        return fPickupMethod;
    }

    public void setfPickupMethod(String fPickupMethod) {
        this.fPickupMethod = fPickupMethod;
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    public Integer getfIsDiscard() {
        return fIsDiscard;
    }

    public void setfIsDiscard(Integer fIsDiscard) {
        this.fIsDiscard = fIsDiscard;
    }

    public Date getfApplyTime() {
        return fApplyTime;
    }

    public void setfApplyTime(Date fApplyTime) {
        this.fApplyTime = fApplyTime;
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

	public Long getFkTemplateId() {
		return fkTemplateId;
	}

	public void setFkTemplateId(Long fkTemplateId) {
		this.fkTemplateId = fkTemplateId;
	}

	public Integer getfUserId() {
		return fUserId;
	}

	public void setfUserId(Integer fUserId) {
		this.fUserId = fUserId;
	}

	public String getfTemplatePath() {
		return fTemplatePath;
	}

	public void setfTemplatePath(String fTemplatePath) {
		this.fTemplatePath = fTemplatePath;
	}

	public String getfTemplateName() {
		return fTemplateName;
	}

	public void setfTemplateName(String fTemplateName) {
		this.fTemplateName = fTemplateName;
	}

	public String getfTemplateExt() {
		return fTemplateExt;
	}

	public void setfTemplateExt(String fTemplateExt) {
		this.fTemplateExt = fTemplateExt;
	}
}