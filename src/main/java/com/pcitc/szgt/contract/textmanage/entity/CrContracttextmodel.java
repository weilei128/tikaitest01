package com.pcitc.szgt.contract.textmanage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-20
 */
public class CrContracttextmodel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("FileTemplateID")
    private String FileTemplateID;

    /**
     * 当前用户所在部门ID
     */
    @TableField("OrgID")
    private Integer OrgID;

    /**
     * 文本名称
     */
    @TableField("TextName")
    private String TextName;

    /**
     * 文本编号，规则：ZB-年度-文本类别-版本号-四位流水号，如ZB-20-文本类别-01-0001
     */
    @TableField("TextCode")
    private String TextCode;

    /**
     * 文本类别
     */
    @TableField("BusiID")
    private String BusiID;

    /**
     * 文本类别名称
     */
    @TableField("BusiType")
    private String BusiType;

    /**
     * 合同类别1,多个类别以,分割
     */
    @TableField("Type1")
    private String Type1;

    /**
     * 合同类别2,多个类别以,分割
     */
    @TableField("Type2")
    private String Type2;

    /**
     * 合同类别3,多个类别以,分割
     */
    @TableField("Type3")
    private String Type3;

    /**
     * 申请人，当前用户名
     */
    @TableField("Applicant")
    private String Applicant;

    /**
     * 申请时间
     */
    @TableField("ApplicantTime")
    private LocalDateTime ApplicantTime;

    /**
     * 使用说明
     */
    @TableField("ApplicantExplain")
    private String ApplicantExplain;

    /**
     * 复核人
     */
    @TableField("CheckedBy")
    private String CheckedBy;

    /**
     * 复核时间
     */
    @TableField("CheckedTime")
    private LocalDateTime CheckedTime;

    /**
     * 复核意见
     */
    @TableField("CheckedIdea")
    private String CheckedIdea;

    /**
     * 发布人
     */
    @TableField("Publisher")
    private String Publisher;

    /**
     * 发布时间
     */
    @TableField("PublishTime")
    private LocalDateTime PublishTime;

    /**
     * 文本状态，默认已启用，1草稿 2待审核 3退回 4待启用 5已启用
     */
    @TableField("Status")
    private Integer Status;

    /**
     * 删除状态 0否 1是
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 修改时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 企业标识
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 合同类别ID,以,分割
     */
    @TableField("TypeTextID")
    private String TypeTextID;

    /**
     * 合同类别名称,以,分割
     */
    @TableField("TypeText")
    private String TypeText;

    /**
     * 文本使用环节,0订立 1变更
     */
    @TableField("TextModelType")
    private Integer TextModelType;

    /**
     * 版本号,每提交一次升一个版本号01,02,03
     */
    @TableField("Version")
    private BigDecimal Version;

    /**
     * 使用范围
     */
    @TableField("PubCorps")
    private String PubCorps;

    /**
     * 创建人
     */
    @TableField("CreatorID")
    private String CreatorID;

    /**
     * 创建人姓名
     */
    @TableField("CreatorName")
    private String CreatorName;

    /**
     * 创建人所在部门
     */
    @TableField("CreatorDepartmentID")
    private Integer CreatorDepartmentID;

    /**
     * 创建人所在部门名称
     */
    @TableField("CreatorDepartmentName")
    private String CreatorDepartmentName;

    /**
     * 创建人所在企业/单位
     */
    @TableField("CreatorUnitID")
    private Integer CreatorUnitID;

    /**
     * 创建人所在企业/单位名称
     */
    @TableField("CreatorUnitName")
    private String CreatorUnitName;

    /**
     * 废弃时间
     */
    @TableField("DiscardDate")
    private LocalDateTime DiscardDate;

    /**
     * 废弃原因
     */
    @TableField("DiscardReason")
    private String DiscardReason;

    /**
     * 前一个版本id
     */
    @TableField("ParentID")
    private String ParentID;

    /**
     * 标识（同一文本不同版本值相同）
     */
    @TableField("Identity")
    private String Identity;

    /**
     * 修改说明
     */
    @TableField("ModifiedExplain")
    private String ModifiedExplain;

    /**
     * 是否为最新版本
     */
    @TableField("IsPrime")
    private Integer IsPrime;

    public String getFileTemplateID() {
        return FileTemplateID;
    }

    public void setFileTemplateID(String FileTemplateID) {
        this.FileTemplateID = FileTemplateID;
    }
    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer OrgID) {
        this.OrgID = OrgID;
    }
    public String getTextName() {
        return TextName;
    }

    public void setTextName(String TextName) {
        this.TextName = TextName;
    }
    public String getTextCode() {
        return TextCode;
    }

    public void setTextCode(String TextCode) {
        this.TextCode = TextCode;
    }
    public String getBusiID() {
        return BusiID;
    }

    public void setBusiID(String BusiID) {
        this.BusiID = BusiID;
    }
    public String getBusiType() {
        return BusiType;
    }

    public void setBusiType(String BusiType) {
        this.BusiType = BusiType;
    }
    public String getType1() {
        return Type1;
    }

    public void setType1(String Type1) {
        this.Type1 = Type1;
    }
    public String getType2() {
        return Type2;
    }

    public void setType2(String Type2) {
        this.Type2 = Type2;
    }
    public String getType3() {
        return Type3;
    }

    public void setType3(String Type3) {
        this.Type3 = Type3;
    }
    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String Applicant) {
        this.Applicant = Applicant;
    }
    public LocalDateTime getApplicantTime() {
        return ApplicantTime;
    }

    public void setApplicantTime(LocalDateTime ApplicantTime) {
        this.ApplicantTime = ApplicantTime;
    }
    public String getApplicantExplain() {
        return ApplicantExplain;
    }

    public void setApplicantExplain(String ApplicantExplain) {
        this.ApplicantExplain = ApplicantExplain;
    }
    public String getCheckedBy() {
        return CheckedBy;
    }

    public void setCheckedBy(String CheckedBy) {
        this.CheckedBy = CheckedBy;
    }
    public LocalDateTime getCheckedTime() {
        return CheckedTime;
    }

    public void setCheckedTime(LocalDateTime CheckedTime) {
        this.CheckedTime = CheckedTime;
    }
    public String getCheckedIdea() {
        return CheckedIdea;
    }

    public void setCheckedIdea(String CheckedIdea) {
        this.CheckedIdea = CheckedIdea;
    }
    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }
    public LocalDateTime getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(LocalDateTime PublishTime) {
        this.PublishTime = PublishTime;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public String getTypeTextID() {
        return TypeTextID;
    }

    public void setTypeTextID(String TypeTextID) {
        this.TypeTextID = TypeTextID;
    }
    public String getTypeText() {
        return TypeText;
    }

    public void setTypeText(String TypeText) {
        this.TypeText = TypeText;
    }
    public Integer getTextModelType() {
        return TextModelType;
    }

    public void setTextModelType(Integer TextModelType) {
        this.TextModelType = TextModelType;
    }
    public BigDecimal getVersion() {
        return Version;
    }

    public void setVersion(BigDecimal Version) {
        this.Version = Version;
    }
    public String getPubCorps() {
        return PubCorps;
    }

    public void setPubCorps(String PubCorps) {
        this.PubCorps = PubCorps;
    }
    public String getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(String CreatorID) {
        this.CreatorID = CreatorID;
    }
    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String CreatorName) {
        this.CreatorName = CreatorName;
    }
    public Integer getCreatorDepartmentID() {
        return CreatorDepartmentID;
    }

    public void setCreatorDepartmentID(Integer CreatorDepartmentID) {
        this.CreatorDepartmentID = CreatorDepartmentID;
    }
    public String getCreatorDepartmentName() {
        return CreatorDepartmentName;
    }

    public void setCreatorDepartmentName(String CreatorDepartmentName) {
        this.CreatorDepartmentName = CreatorDepartmentName;
    }
    public Integer getCreatorUnitID() {
        return CreatorUnitID;
    }

    public void setCreatorUnitID(Integer CreatorUnitID) {
        this.CreatorUnitID = CreatorUnitID;
    }
    public String getCreatorUnitName() {
        return CreatorUnitName;
    }

    public void setCreatorUnitName(String CreatorUnitName) {
        this.CreatorUnitName = CreatorUnitName;
    }
    public LocalDateTime getDiscardDate() {
        return DiscardDate;
    }

    public void setDiscardDate(LocalDateTime DiscardDate) {
        this.DiscardDate = DiscardDate;
    }
    public String getDiscardReason() {
        return DiscardReason;
    }

    public void setDiscardReason(String DiscardReason) {
        this.DiscardReason = DiscardReason;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getModifiedExplain() {
        return ModifiedExplain;
    }

    public void setModifiedExplain(String modifiedExplain) {
        ModifiedExplain = modifiedExplain;
    }

    public Integer getIsPrime() {
        return IsPrime;
    }

    public void setIsPrime(Integer isPrime) {
        IsPrime = isPrime;
    }

    @Override
    public String toString() {
        return "CrContracttextmodel{" +
        "FileTemplateID=" + FileTemplateID +
        ", OrgID=" + OrgID +
        ", TextName=" + TextName +
        ", TextCode=" + TextCode +
        ", BusiID=" + BusiID +
        ", BusiType=" + BusiType +
        ", Type1=" + Type1 +
        ", Type2=" + Type2 +
        ", Type3=" + Type3 +
        ", Applicant=" + Applicant +
        ", ApplicantTime=" + ApplicantTime +
        ", ApplicantExplain=" + ApplicantExplain +
        ", CheckedBy=" + CheckedBy +
        ", CheckedTime=" + CheckedTime +
        ", CheckedIdea=" + CheckedIdea +
        ", Publisher=" + Publisher +
        ", PublishTime=" + PublishTime +
        ", Status=" + Status +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", TypeTextID=" + TypeTextID +
        ", TypeText=" + TypeText +
        ", TextModelType=" + TextModelType +
        ", Version=" + Version +
        ", PubCorps=" + PubCorps +
        ", CreatorID=" + CreatorID +
        ", CreatorName=" + CreatorName +
        ", CreatorDepartmentID=" + CreatorDepartmentID +
        ", CreatorDepartmentName=" + CreatorDepartmentName +
        ", CreatorUnitID=" + CreatorUnitID +
        ", CreatorUnitName=" + CreatorUnitName +
        ", DiscardDate=" + DiscardDate +
        ", DiscardReason=" + DiscardReason +
        ", ParentID=" + ParentID +
        ", Identity=" + Identity +
        ", ModifiedExplain=" + ModifiedExplain +
        ", IsPrime=" + IsPrime +
        "}";
    }
}
