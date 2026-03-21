package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 诉前争议我方信息
 */
@TableName(value = "fw_litigate_dispute_ourside")
public class FwLitigateDisputeOurside implements Serializable {
	
	@TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 争议信息ID
     */
    private Long fkDisputeId;

    /**
     * 争议名称
     */
    private String fkDisputeName;

    /**
     * 我方案件当事人属性
     */
    private String fLitigantStatus;

    /**
     * 我方案件当事人
     */
    private String fLitigantName;

    /**
     * 我方案件当事人ID
     */
    private Long fkLitigantId;

    /**
     * 承办单位ID
     */
    private Long fkOrgId;

    /**
     * 承办单位名称
     */
    private String fkOrgName;

    /**
     * 联系人
     */
    private String fContact;

    /**
     * 联系电话
     */
    private String fPhone;

    /**
     * 电子邮件
     */
    private String fEmail;

    /**
     * 我方法律地位
     */
    private String fLegalStatus;

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

    public String getfLitigantStatus() {
        return fLitigantStatus;
    }

    public void setfLitigantStatus(String fLitigantStatus) {
        this.fLitigantStatus = fLitigantStatus;
    }

    public String getfLitigantName() {
        return fLitigantName;
    }

    public void setfLitigantName(String fLitigantName) {
        this.fLitigantName = fLitigantName;
    }

    public Long getFkLitigantId() {
        return fkLitigantId;
    }

    public void setFkLitigantId(Long fkLitigantId) {
        this.fkLitigantId = fkLitigantId;
    }

    public Long getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(Long fkOrgId) {
        this.fkOrgId = fkOrgId;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getfContact() {
        return fContact;
    }

    public void setfContact(String fContact) {
        this.fContact = fContact;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfLegalStatus() {
        return fLegalStatus;
    }

    public void setfLegalStatus(String fLegalStatus) {
        this.fLegalStatus = fLegalStatus;
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
}