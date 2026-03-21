package com.pcitc.legalAffairs.po.authorize;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 被授权人信息
 */
@TableName(value = "fw_authorize_licensee")
public class FwAuthorizeLicensee implements Serializable {
	
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;
    
    /**
     * 被授权人类型
     */
    private String fType;

    /**
     * 被授权人名称
     */
    private String fName;

    /**
     * 统一社会信用代码
     */
    private String fUscCode;

    /**
     * 法定代表人/负责人
     */
    private String fLegalRepresentative;

    /**
     * 所属单位ID
     */
    private Byte fkOrgId;

    /**
     * 所属单位名称
     */
    private String fkOrgName;

    /**
     * 身份证号
     */
    private String fIdCardNo;

    /**
     * 联系方式
     */
    private String fContact;

    /**
     * 职务
     */
    private String fPosition;

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

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfUscCode() {
        return fUscCode;
    }

    public void setfUscCode(String fUscCode) {
        this.fUscCode = fUscCode;
    }

    public String getfLegalRepresentative() {
        return fLegalRepresentative;
    }

    public void setfLegalRepresentative(String fLegalRepresentative) {
        this.fLegalRepresentative = fLegalRepresentative;
    }

    public Byte getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(Byte fkOrgId) {
        this.fkOrgId = fkOrgId;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getfIdCardNo() {
        return fIdCardNo;
    }

    public void setfIdCardNo(String fIdCardNo) {
        this.fIdCardNo = fIdCardNo;
    }

    public String getfContact() {
        return fContact;
    }

    public void setfContact(String fContact) {
        this.fContact = fContact;
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
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

	public Long getFkAuthorizeId() {
		return fkAuthorizeId;
	}

	public void setFkAuthorizeId(Long fkAuthorizeId) {
		this.fkAuthorizeId = fkAuthorizeId;
	}
}