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
public class CrContracttransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TransferId")
    private String TransferId;

    @TableField("ContractID")
    private String ContractID;

    @TableField("TransferType")
    private Integer TransferType;

    @TableField("Applicant")
    private Integer Applicant;

    @TableField("TransferReason")
    private String TransferReason;

    @TableField("OffereeId1")
    private String OffereeId1;

    @TableField("OffereeId2")
    private String OffereeId2;

    @TableField("OffereeId3")
    private String OffereeId3;

    @TableField("OurSignatory")
    private String OurSignatory;

    @TableField("OurSealedTime")
    private LocalDateTime OurSealedTime;

    @TableField("OpponentSignatory")
    private String OpponentSignatory;

    @TableField("OpponenSealedTime")
    private LocalDateTime OpponenSealedTime;

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

    @TableField("TransferNo")
    private String TransferNo;

    @TableField("ModifiedReason")
    private String ModifiedReason;

    @TableField("BackRemarks")
    private String BackRemarks;

    @TableField("MySignBodyCode")
    private Integer MySignBodyCode;

    @TableField("MySignBodyName")
    private String MySignBodyName;

    @TableField("CompanyType")
    private Integer CompanyType;

    @TableField("RemarkOffereeId1")
    private String RemarkOffereeId1;

    public String getTransferId() {
        return TransferId;
    }

    public void setTransferId(String TransferId) {
        this.TransferId = TransferId;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public Integer getTransferType() {
        return TransferType;
    }

    public void setTransferType(Integer TransferType) {
        this.TransferType = TransferType;
    }
    public Integer getApplicant() {
        return Applicant;
    }

    public void setApplicant(Integer Applicant) {
        this.Applicant = Applicant;
    }
    public String getTransferReason() {
        return TransferReason;
    }

    public void setTransferReason(String TransferReason) {
        this.TransferReason = TransferReason;
    }
    public String getOffereeId1() {
        return OffereeId1;
    }

    public void setOffereeId1(String OffereeId1) {
        this.OffereeId1 = OffereeId1;
    }
    public String getOffereeId2() {
        return OffereeId2;
    }

    public void setOffereeId2(String OffereeId2) {
        this.OffereeId2 = OffereeId2;
    }
    public String getOffereeId3() {
        return OffereeId3;
    }

    public void setOffereeId3(String OffereeId3) {
        this.OffereeId3 = OffereeId3;
    }
    public String getOurSignatory() {
        return OurSignatory;
    }

    public void setOurSignatory(String OurSignatory) {
        this.OurSignatory = OurSignatory;
    }
    public LocalDateTime getOurSealedTime() {
        return OurSealedTime;
    }

    public void setOurSealedTime(LocalDateTime OurSealedTime) {
        this.OurSealedTime = OurSealedTime;
    }
    public String getOpponentSignatory() {
        return OpponentSignatory;
    }

    public void setOpponentSignatory(String OpponentSignatory) {
        this.OpponentSignatory = OpponentSignatory;
    }
    public LocalDateTime getOpponenSealedTime() {
        return OpponenSealedTime;
    }

    public void setOpponenSealedTime(LocalDateTime OpponenSealedTime) {
        this.OpponenSealedTime = OpponenSealedTime;
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
    public String getTransferNo() {
        return TransferNo;
    }

    public void setTransferNo(String TransferNo) {
        this.TransferNo = TransferNo;
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
    public Integer getMySignBodyCode() {
        return MySignBodyCode;
    }

    public void setMySignBodyCode(Integer MySignBodyCode) {
        this.MySignBodyCode = MySignBodyCode;
    }
    public String getMySignBodyName() {
        return MySignBodyName;
    }

    public void setMySignBodyName(String MySignBodyName) {
        this.MySignBodyName = MySignBodyName;
    }
    public Integer getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(Integer CompanyType) {
        this.CompanyType = CompanyType;
    }
    public String getRemarkOffereeId1() {
        return RemarkOffereeId1;
    }

    public void setRemarkOffereeId1(String RemarkOffereeId1) {
        this.RemarkOffereeId1 = RemarkOffereeId1;
    }

    @Override
    public String toString() {
        return "CrContracttransfer{" +
        "TransferId=" + TransferId +
        ", ContractID=" + ContractID +
        ", TransferType=" + TransferType +
        ", Applicant=" + Applicant +
        ", TransferReason=" + TransferReason +
        ", OffereeId1=" + OffereeId1 +
        ", OffereeId2=" + OffereeId2 +
        ", OffereeId3=" + OffereeId3 +
        ", OurSignatory=" + OurSignatory +
        ", OurSealedTime=" + OurSealedTime +
        ", OpponentSignatory=" + OpponentSignatory +
        ", OpponenSealedTime=" + OpponenSealedTime +
        ", State=" + State +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
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
        ", TransferNo=" + TransferNo +
        ", ModifiedReason=" + ModifiedReason +
        ", BackRemarks=" + BackRemarks +
        ", MySignBodyCode=" + MySignBodyCode +
        ", MySignBodyName=" + MySignBodyName +
        ", CompanyType=" + CompanyType +
        ", RemarkOffereeId1=" + RemarkOffereeId1 +
        "}";
    }
}
