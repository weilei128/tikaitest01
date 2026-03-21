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
 * @since 2020-03-02
 */
public class CrContractchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractChangeID")
    private String ContractChangeID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("CcNo")
    private String CcNo;

    @TableField("Proposer")
    private Integer Proposer;

    @TableField("ChangeReason")
    private String ChangeReason;

    @TableField("OurSignatory")
    private String OurSignatory;

    @TableField("OurSignatoryName")
    private String OurSignatoryName;

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

    @TableField("CteatedDate")
    private LocalDateTime CteatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("IsReviewProcess")
    private Integer IsReviewProcess;

    @TableField("IsChangSendToERP")
    private Integer IsChangSendToERP;

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

    @TableField("TextType")
    private Integer TextType;

    @TableField("TextModel")
    private String TextModel;

    @TableField("ModifiedReason")
    private String ModifiedReason;

    @TableField("BackRemarks")
    private String BackRemarks;

    @TableField("IsSendTender")
    private Integer IsSendTender;

    @TableField("Z_TextName")
    private String zTextname;

    @TableField("Z_AttachmentName")
    private String zAttachmentname;

    public String getContractChangeID() {
        return ContractChangeID;
    }

    public void setContractChangeID(String ContractChangeID) {
        this.ContractChangeID = ContractChangeID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getCcNo() {
        return CcNo;
    }

    public void setCcNo(String CcNo) {
        this.CcNo = CcNo;
    }
    public Integer getProposer() {
        return Proposer;
    }

    public void setProposer(Integer Proposer) {
        this.Proposer = Proposer;
    }
    public String getChangeReason() {
        return ChangeReason;
    }

    public void setChangeReason(String ChangeReason) {
        this.ChangeReason = ChangeReason;
    }
    public String getOurSignatory() {
        return OurSignatory;
    }

    public void setOurSignatory(String OurSignatory) {
        this.OurSignatory = OurSignatory;
    }
    public String getOurSignatoryName() {
        return OurSignatoryName;
    }

    public void setOurSignatoryName(String OurSignatoryName) {
        this.OurSignatoryName = OurSignatoryName;
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
    public LocalDateTime getCteatedDate() {
        return CteatedDate;
    }

    public void setCteatedDate(LocalDateTime CteatedDate) {
        this.CteatedDate = CteatedDate;
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
    public Integer getIsReviewProcess() {
        return IsReviewProcess;
    }

    public void setIsReviewProcess(Integer IsReviewProcess) {
        this.IsReviewProcess = IsReviewProcess;
    }
    public Integer getIsChangSendToERP() {
        return IsChangSendToERP;
    }

    public void setIsChangSendToERP(Integer IsChangSendToERP) {
        this.IsChangSendToERP = IsChangSendToERP;
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
    public Integer getTextType() {
        return TextType;
    }

    public void setTextType(Integer TextType) {
        this.TextType = TextType;
    }
    public String getTextModel() {
        return TextModel;
    }

    public void setTextModel(String TextModel) {
        this.TextModel = TextModel;
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
    public Integer getIsSendTender() {
        return IsSendTender;
    }

    public void setIsSendTender(Integer IsSendTender) {
        this.IsSendTender = IsSendTender;
    }
    public String getzTextname() {
        return zTextname;
    }

    public void setzTextname(String zTextname) {
        this.zTextname = zTextname;
    }
    public String getzAttachmentname() {
        return zAttachmentname;
    }

    public void setzAttachmentname(String zAttachmentname) {
        this.zAttachmentname = zAttachmentname;
    }

    @Override
    public String toString() {
        return "CrContractchange{" +
        "ContractChangeID=" + ContractChangeID +
        ", ContractID=" + ContractID +
        ", CcNo=" + CcNo +
        ", Proposer=" + Proposer +
        ", ChangeReason=" + ChangeReason +
        ", OurSignatory=" + OurSignatory +
        ", OurSignatoryName=" + OurSignatoryName +
        ", OurSealedTime=" + OurSealedTime +
        ", OpponentSignatory=" + OpponentSignatory +
        ", OpponenSealedTime=" + OpponenSealedTime +
        ", State=" + State +
        ", CreatedBy=" + CreatedBy +
        ", CteatedDate=" + CteatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", IsReviewProcess=" + IsReviewProcess +
        ", IsChangSendToERP=" + IsChangSendToERP +
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
        ", TextType=" + TextType +
        ", TextModel=" + TextModel +
        ", ModifiedReason=" + ModifiedReason +
        ", BackRemarks=" + BackRemarks +
        ", IsSendTender=" + IsSendTender +
        ", zTextname=" + zTextname +
        ", zAttachmentname=" + zAttachmentname +
        "}";
    }
}
