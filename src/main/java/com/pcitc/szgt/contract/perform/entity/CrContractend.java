package com.pcitc.szgt.contract.perform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-04
 */
public class CrContractend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractEndID")
    private String ContractEndID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("EndReason")
    private Integer EndReason;

    @TableField("EndTime")
    private LocalDateTime EndTime;

    @TableField("IsNormal")
    private Integer IsNormal;

    @TableField("EndDescription")
    private String EndDescription;

    @TableField("OverDescription")
    private String OverDescription;

    @TableField("State")
    private Integer State;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("Category")
    private Integer Category;

    @TableField("TerminateTime")
    private LocalDateTime TerminateTime;

    @TableField("OffereePerformRemark")
    private String OffereePerformRemark;

    @TableField("PerformApprise")
    private Integer PerformApprise;

    @TableField("PerformAppriseText")
    private String PerformAppriseText;

    @TableField("OurSignatory")
    private String OurSignatory;

    @TableField("OpponentSignatory")
    private String OpponentSignatory;

    @TableField("SignDate")
    private LocalDateTime SignDate;

    @TableField("EffectiveType")
    private Integer EffectiveType;

    @TableField("EffectiveDate")
    private LocalDateTime EffectiveDate;

    @TableField("EffectiveElements")
    private String EffectiveElements;

    @TableField("IsSign")
    private Integer IsSign;

    @TableField("SignInputDate")
    private LocalDateTime SignInputDate;

    @TableField("TextServer")
    private String TextServer;

    @TableField("ServerTime")
    private LocalDateTime ServerTime;

    @TableField("TextSource")
    private Integer TextSource;

    @TableField("SealDate")
    private LocalDateTime SealDate;

    @TableField("SealTimes")
    private Integer SealTimes;

    @TableField("SealRemark")
    private String SealRemark;

    @TableField("SealPerson")
    private String SealPerson;

    @TableField("IsSeal")
    private Integer IsSeal;

    @TableField("SealInputDate")
    private LocalDateTime SealInputDate;

    @TableField("IsUseDistribute")
    private Integer IsUseDistribute;

    @TableField("EndNo")
    private String EndNo;

    @TableField("ModifiedReason")
    private String ModifiedReason;

    @TableField("BackRemarks")
    private String BackRemarks;

    public String getContractEndID() {
        return ContractEndID;
    }

    public void setContractEndID(String ContractEndID) {
        this.ContractEndID = ContractEndID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public Integer getEndReason() {
        return EndReason;
    }

    public void setEndReason(Integer EndReason) {
        this.EndReason = EndReason;
    }
    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime EndTime) {
        this.EndTime = EndTime;
    }
    public Integer getIsNormal() {
        return IsNormal;
    }

    public void setIsNormal(Integer IsNormal) {
        this.IsNormal = IsNormal;
    }
    public String getEndDescription() {
        return EndDescription;
    }

    public void setEndDescription(String EndDescription) {
        this.EndDescription = EndDescription;
    }
    public String getOverDescription() {
        return OverDescription;
    }

    public void setOverDescription(String OverDescription) {
        this.OverDescription = OverDescription;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
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
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }
    public LocalDateTime getTerminateTime() {
        return TerminateTime;
    }

    public void setTerminateTime(LocalDateTime TerminateTime) {
        this.TerminateTime = TerminateTime;
    }
    public String getOffereePerformRemark() {
        return OffereePerformRemark;
    }

    public void setOffereePerformRemark(String OffereePerformRemark) {
        this.OffereePerformRemark = OffereePerformRemark;
    }
    public Integer getPerformApprise() {
        return PerformApprise;
    }

    public void setPerformApprise(Integer PerformApprise) {
        this.PerformApprise = PerformApprise;
    }
    public String getPerformAppriseText() {
        return PerformAppriseText;
    }

    public void setPerformAppriseText(String PerformAppriseText) {
        this.PerformAppriseText = PerformAppriseText;
    }
    public String getOurSignatory() {
        return OurSignatory;
    }

    public void setOurSignatory(String OurSignatory) {
        this.OurSignatory = OurSignatory;
    }
    public String getOpponentSignatory() {
        return OpponentSignatory;
    }

    public void setOpponentSignatory(String OpponentSignatory) {
        this.OpponentSignatory = OpponentSignatory;
    }
    public LocalDateTime getSignDate() {
        return SignDate;
    }

    public void setSignDate(LocalDateTime SignDate) {
        this.SignDate = SignDate;
    }
    public Integer getEffectiveType() {
        return EffectiveType;
    }

    public void setEffectiveType(Integer EffectiveType) {
        this.EffectiveType = EffectiveType;
    }
    public LocalDateTime getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(LocalDateTime EffectiveDate) {
        this.EffectiveDate = EffectiveDate;
    }
    public String getEffectiveElements() {
        return EffectiveElements;
    }

    public void setEffectiveElements(String EffectiveElements) {
        this.EffectiveElements = EffectiveElements;
    }
    public Integer getIsSign() {
        return IsSign;
    }

    public void setIsSign(Integer IsSign) {
        this.IsSign = IsSign;
    }
    public LocalDateTime getSignInputDate() {
        return SignInputDate;
    }

    public void setSignInputDate(LocalDateTime SignInputDate) {
        this.SignInputDate = SignInputDate;
    }
    public String getTextServer() {
        return TextServer;
    }

    public void setTextServer(String TextServer) {
        this.TextServer = TextServer;
    }
    public LocalDateTime getServerTime() {
        return ServerTime;
    }

    public void setServerTime(LocalDateTime ServerTime) {
        this.ServerTime = ServerTime;
    }
    public Integer getTextSource() {
        return TextSource;
    }

    public void setTextSource(Integer TextSource) {
        this.TextSource = TextSource;
    }
    public LocalDateTime getSealDate() {
        return SealDate;
    }

    public void setSealDate(LocalDateTime SealDate) {
        this.SealDate = SealDate;
    }
    public Integer getSealTimes() {
        return SealTimes;
    }

    public void setSealTimes(Integer SealTimes) {
        this.SealTimes = SealTimes;
    }
    public String getSealRemark() {
        return SealRemark;
    }

    public void setSealRemark(String SealRemark) {
        this.SealRemark = SealRemark;
    }
    public String getSealPerson() {
        return SealPerson;
    }

    public void setSealPerson(String SealPerson) {
        this.SealPerson = SealPerson;
    }
    public Integer getIsSeal() {
        return IsSeal;
    }

    public void setIsSeal(Integer IsSeal) {
        this.IsSeal = IsSeal;
    }
    public LocalDateTime getSealInputDate() {
        return SealInputDate;
    }

    public void setSealInputDate(LocalDateTime SealInputDate) {
        this.SealInputDate = SealInputDate;
    }
    public Integer getIsUseDistribute() {
        return IsUseDistribute;
    }

    public void setIsUseDistribute(Integer IsUseDistribute) {
        this.IsUseDistribute = IsUseDistribute;
    }
    public String getEndNo() {
        return EndNo;
    }

    public void setEndNo(String EndNo) {
        this.EndNo = EndNo;
    }
    public String getModifiedReason() {
        return ModifiedReason;
    }

    public void setModifiedReason(String ModifiedReason) {
        this.ModifiedReason = ModifiedReason;
    }
    public String getBackRemarks() {
        return BackRemarks;
    }

    public void setBackRemarks(String BackRemarks) {
        this.BackRemarks = BackRemarks;
    }

    @Override
    public String toString() {
        return "CrContractend{" +
        "ContractEndID=" + ContractEndID +
        ", ContractID=" + ContractID +
        ", EndReason=" + EndReason +
        ", EndTime=" + EndTime +
        ", IsNormal=" + IsNormal +
        ", EndDescription=" + EndDescription +
        ", OverDescription=" + OverDescription +
        ", State=" + State +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", Category=" + Category +
        ", TerminateTime=" + TerminateTime +
        ", OffereePerformRemark=" + OffereePerformRemark +
        ", PerformApprise=" + PerformApprise +
        ", PerformAppriseText=" + PerformAppriseText +
        ", OurSignatory=" + OurSignatory +
        ", OpponentSignatory=" + OpponentSignatory +
        ", SignDate=" + SignDate +
        ", EffectiveType=" + EffectiveType +
        ", EffectiveDate=" + EffectiveDate +
        ", EffectiveElements=" + EffectiveElements +
        ", IsSign=" + IsSign +
        ", SignInputDate=" + SignInputDate +
        ", TextServer=" + TextServer +
        ", ServerTime=" + ServerTime +
        ", TextSource=" + TextSource +
        ", SealDate=" + SealDate +
        ", SealTimes=" + SealTimes +
        ", SealRemark=" + SealRemark +
        ", SealPerson=" + SealPerson +
        ", IsSeal=" + IsSeal +
        ", SealInputDate=" + SealInputDate +
        ", IsUseDistribute=" + IsUseDistribute +
        ", EndNo=" + EndNo +
        ", ModifiedReason=" + ModifiedReason +
        ", BackRemarks=" + BackRemarks +
        "}";
    }
}
