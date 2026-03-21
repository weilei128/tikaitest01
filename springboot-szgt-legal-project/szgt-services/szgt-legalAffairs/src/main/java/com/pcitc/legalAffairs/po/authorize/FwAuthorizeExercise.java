package com.pcitc.legalAffairs.po.authorize;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 行权报告信息
 */
@TableName(value = "fw_authorize_exercise")
public class FwAuthorizeExercise implements Serializable {
    
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;

    /**
     * 行权状态
     */
    private Integer fStatus;

    /**
     * 行权完成日期
     */
    private Date fCompleteDate;

    /**
     * 行权说明/未行权说明
     */
    private String fDescription;

    /**
     * 授权状态
     */
    private Integer fAuthorizeStatus;

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
     * 原件寄回
     */
    private Integer fReturnOriginal;

    /**
     * 收件人
     */
    private String fRecipient;

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

    public Long getFkAuthorizeId() {
        return fkAuthorizeId;
    }

    public void setFkAuthorizeId(Long fkAuthorizeId) {
        this.fkAuthorizeId = fkAuthorizeId;
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    public Date getfCompleteDate() {
        return fCompleteDate;
    }

    public void setfCompleteDate(Date fCompleteDate) {
        this.fCompleteDate = fCompleteDate;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Integer getfAuthorizeStatus() {
        return fAuthorizeStatus;
    }

    public void setfAuthorizeStatus(Integer fAuthorizeStatus) {
        this.fAuthorizeStatus = fAuthorizeStatus;
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

    public Integer getfReturnOriginal() {
        return fReturnOriginal;
    }

    public void setfReturnOriginal(Integer fReturnOriginal) {
        this.fReturnOriginal = fReturnOriginal;
    }

    public String getfRecipient() {
        return fRecipient;
    }

    public void setfRecipient(String fRecipient) {
        this.fRecipient = fRecipient;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FwAuthorizeExercise other = (FwAuthorizeExercise) that;
        return (this.getfId() == null ? other.getfId() == null : this.getfId().equals(other.getfId()))
            && (this.getFkAuthorizeId() == null ? other.getFkAuthorizeId() == null : this.getFkAuthorizeId().equals(other.getFkAuthorizeId()))
            && (this.getfStatus() == null ? other.getfStatus() == null : this.getfStatus().equals(other.getfStatus()))
            && (this.getfCompleteDate() == null ? other.getfCompleteDate() == null : this.getfCompleteDate().equals(other.getfCompleteDate()))
            && (this.getfDescription() == null ? other.getfDescription() == null : this.getfDescription().equals(other.getfDescription()))
            && (this.getfAuthorizeStatus() == null ? other.getfAuthorizeStatus() == null : this.getfAuthorizeStatus().equals(other.getfAuthorizeStatus()))
            && (this.getFkAttachmentId() == null ? other.getFkAttachmentId() == null : this.getFkAttachmentId().equals(other.getFkAttachmentId()))
            && (this.getFkAttachmentPath() == null ? other.getFkAttachmentPath() == null : this.getFkAttachmentPath().equals(other.getFkAttachmentPath()))
            && (this.getFkAttachmentName() == null ? other.getFkAttachmentName() == null : this.getFkAttachmentName().equals(other.getFkAttachmentName()))
            && (this.getFkAttachmentExt() == null ? other.getFkAttachmentExt() == null : this.getFkAttachmentExt().equals(other.getFkAttachmentExt()))
            && (this.getfReturnOriginal() == null ? other.getfReturnOriginal() == null : this.getfReturnOriginal().equals(other.getfReturnOriginal()))
            && (this.getfRecipient() == null ? other.getfRecipient() == null : this.getfRecipient().equals(other.getfRecipient()))
            && (this.getfSort() == null ? other.getfSort() == null : this.getfSort().equals(other.getfSort()))
            && (this.getfIsdel() == null ? other.getfIsdel() == null : this.getfIsdel().equals(other.getfIsdel()))
            && (this.getfCreateId() == null ? other.getfCreateId() == null : this.getfCreateId().equals(other.getfCreateId()))
            && (this.getfCreateUser() == null ? other.getfCreateUser() == null : this.getfCreateUser().equals(other.getfCreateUser()))
            && (this.getfCreateName() == null ? other.getfCreateName() == null : this.getfCreateName().equals(other.getfCreateName()))
            && (this.getfCreateTime() == null ? other.getfCreateTime() == null : this.getfCreateTime().equals(other.getfCreateTime()))
            && (this.getfUpdateId() == null ? other.getfUpdateId() == null : this.getfUpdateId().equals(other.getfUpdateId()))
            && (this.getfUpdateUser() == null ? other.getfUpdateUser() == null : this.getfUpdateUser().equals(other.getfUpdateUser()))
            && (this.getfUpdateName() == null ? other.getfUpdateName() == null : this.getfUpdateName().equals(other.getfUpdateName()))
            && (this.getfUpdateTime() == null ? other.getfUpdateTime() == null : this.getfUpdateTime().equals(other.getfUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfId() == null) ? 0 : getfId().hashCode());
        result = prime * result + ((getFkAuthorizeId() == null) ? 0 : getFkAuthorizeId().hashCode());
        result = prime * result + ((getfStatus() == null) ? 0 : getfStatus().hashCode());
        result = prime * result + ((getfCompleteDate() == null) ? 0 : getfCompleteDate().hashCode());
        result = prime * result + ((getfDescription() == null) ? 0 : getfDescription().hashCode());
        result = prime * result + ((getfAuthorizeStatus() == null) ? 0 : getfAuthorizeStatus().hashCode());
        result = prime * result + ((getFkAttachmentId() == null) ? 0 : getFkAttachmentId().hashCode());
        result = prime * result + ((getFkAttachmentPath() == null) ? 0 : getFkAttachmentPath().hashCode());
        result = prime * result + ((getFkAttachmentName() == null) ? 0 : getFkAttachmentName().hashCode());
        result = prime * result + ((getFkAttachmentExt() == null) ? 0 : getFkAttachmentExt().hashCode());
        result = prime * result + ((getfReturnOriginal() == null) ? 0 : getfReturnOriginal().hashCode());
        result = prime * result + ((getfRecipient() == null) ? 0 : getfRecipient().hashCode());
        result = prime * result + ((getfSort() == null) ? 0 : getfSort().hashCode());
        result = prime * result + ((getfIsdel() == null) ? 0 : getfIsdel().hashCode());
        result = prime * result + ((getfCreateId() == null) ? 0 : getfCreateId().hashCode());
        result = prime * result + ((getfCreateUser() == null) ? 0 : getfCreateUser().hashCode());
        result = prime * result + ((getfCreateName() == null) ? 0 : getfCreateName().hashCode());
        result = prime * result + ((getfCreateTime() == null) ? 0 : getfCreateTime().hashCode());
        result = prime * result + ((getfUpdateId() == null) ? 0 : getfUpdateId().hashCode());
        result = prime * result + ((getfUpdateUser() == null) ? 0 : getfUpdateUser().hashCode());
        result = prime * result + ((getfUpdateName() == null) ? 0 : getfUpdateName().hashCode());
        result = prime * result + ((getfUpdateTime() == null) ? 0 : getfUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fId=").append(fId);
        sb.append(", fkAuthorizeId=").append(fkAuthorizeId);
        sb.append(", fStatus=").append(fStatus);
        sb.append(", fCompleteDate=").append(fCompleteDate);
        sb.append(", fDescription=").append(fDescription);
        sb.append(", fAuthorizeStatus=").append(fAuthorizeStatus);
        sb.append(", fkAttachmentId=").append(fkAttachmentId);
        sb.append(", fkAttachmentPath=").append(fkAttachmentPath);
        sb.append(", fkAttachmentName=").append(fkAttachmentName);
        sb.append(", fkAttachmentExt=").append(fkAttachmentExt);
        sb.append(", fReturnOriginal=").append(fReturnOriginal);
        sb.append(", fRecipient=").append(fRecipient);
        sb.append(", fSort=").append(fSort);
        sb.append(", fIsdel=").append(fIsdel);
        sb.append(", fCreateId=").append(fCreateId);
        sb.append(", fCreateUser=").append(fCreateUser);
        sb.append(", fCreateName=").append(fCreateName);
        sb.append(", fCreateTime=").append(fCreateTime);
        sb.append(", fUpdateId=").append(fUpdateId);
        sb.append(", fUpdateUser=").append(fUpdateUser);
        sb.append(", fUpdateName=").append(fUpdateName);
        sb.append(", fUpdateTime=").append(fUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}