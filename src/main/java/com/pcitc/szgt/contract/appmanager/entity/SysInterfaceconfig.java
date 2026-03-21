package com.pcitc.szgt.contract.appmanager.entity;

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
 * @since 2020-02-28
 */
public class SysInterfaceconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("OUID")
    private Integer ouid;

    @TableField("InterfaceId")
    private Integer InterfaceId;

    @TableField("OUBelongType")
    private Integer OUBelongType;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("MakeSend")
    private String MakeSend;

    @TableField("ChangeSend")
    private String ChangeSend;

    @TableField("TerminateSend")
    private String TerminateSend;

    @TableField("PaymentApplySend")
    private String PaymentApplySend;

    @TableField("SDCollect")
    private String SDCollect;

    @TableField("MaterialCodeSet")
    private String MaterialCodeSet;

    @TableField("IsDisplayAllOrder")
    private String IsDisplayAllOrder;

    @TableField("IsDisplayAllProject")
    private String IsDisplayAllProject;

    @TableField("TransferSendERP")
    private String TransferSendERP;

    @TableField("ChildInterfaceId")
    private Integer ChildInterfaceId;

    @TableField("PMExtension")
    private Integer PMExtension;

    @TableField("IsUseLongMaterialName")
    private Integer IsUseLongMaterialName;

    @TableField("IsDisplayQualityStandards")
    private Integer IsDisplayQualityStandards;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("IsReturnBackSend")
    private Integer IsReturnBackSend;

    @TableField("IsAutoSend")
    private Integer IsAutoSend;

    @TableField("IsDisplayPMMoney")
    private Integer IsDisplayPMMoney;

    @TableField("PaymentApplyCheckedSend")
    private Integer PaymentApplyCheckedSend;

    @TableField("PMForTJSH")
    private Integer PMForTJSH;

    @TableField("IsUseMROExtension")
    private Integer IsUseMROExtension;

    @TableField("IsMergerMaterialName")
    private Integer IsMergerMaterialName;

    @TableField("IsUseEC")
    private Integer IsUseEC;

    @TableField("ZQGenerateContract")
    private Integer ZQGenerateContract;

    @TableField("QFGenerateContract")
    private Integer QFGenerateContract;

    @TableField("SQGenerateContract")
    private Integer SQGenerateContract;

    @TableField("QQGenerateContract")
    private Integer QQGenerateContract;

    @TableField("IsUsePaymentExt")
    private Integer IsUsePaymentExt;

    @TableField("RFCConfig")
    private String RFCConfig;

    @TableField("IsUseSelfERP")
    private Integer IsUseSelfERP;

    @TableField("CompanyCode")
    private String CompanyCode;

    @TableField("ContractType")
    private String ContractType;

    @TableField("XQGenerateContract")
    private Integer XQGenerateContract;

    @TableField("IsUseERPMass")
    private Integer IsUseERPMass;

    @TableField("IsUseTax")
    private Integer IsUseTax;

    @TableField("IsDisplayAllPM")
    private Integer IsDisplayAllPM;

    public Integer getOuid() {
        return ouid;
    }

    public void setOuid(Integer ouid) {
        this.ouid = ouid;
    }
    public Integer getInterfaceId() {
        return InterfaceId;
    }

    public void setInterfaceId(Integer InterfaceId) {
        this.InterfaceId = InterfaceId;
    }
    public Integer getOUBelongType() {
        return OUBelongType;
    }

    public void setOUBelongType(Integer OUBelongType) {
        this.OUBelongType = OUBelongType;
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
    public String getMakeSend() {
        return MakeSend;
    }

    public void setMakeSend(String MakeSend) {
        this.MakeSend = MakeSend;
    }
    public String getChangeSend() {
        return ChangeSend;
    }

    public void setChangeSend(String ChangeSend) {
        this.ChangeSend = ChangeSend;
    }
    public String getTerminateSend() {
        return TerminateSend;
    }

    public void setTerminateSend(String TerminateSend) {
        this.TerminateSend = TerminateSend;
    }
    public String getPaymentApplySend() {
        return PaymentApplySend;
    }

    public void setPaymentApplySend(String PaymentApplySend) {
        this.PaymentApplySend = PaymentApplySend;
    }
    public String getSDCollect() {
        return SDCollect;
    }

    public void setSDCollect(String SDCollect) {
        this.SDCollect = SDCollect;
    }
    public String getMaterialCodeSet() {
        return MaterialCodeSet;
    }

    public void setMaterialCodeSet(String MaterialCodeSet) {
        this.MaterialCodeSet = MaterialCodeSet;
    }
    public String getIsDisplayAllOrder() {
        return IsDisplayAllOrder;
    }

    public void setIsDisplayAllOrder(String IsDisplayAllOrder) {
        this.IsDisplayAllOrder = IsDisplayAllOrder;
    }
    public String getIsDisplayAllProject() {
        return IsDisplayAllProject;
    }

    public void setIsDisplayAllProject(String IsDisplayAllProject) {
        this.IsDisplayAllProject = IsDisplayAllProject;
    }
    public String getTransferSendERP() {
        return TransferSendERP;
    }

    public void setTransferSendERP(String TransferSendERP) {
        this.TransferSendERP = TransferSendERP;
    }
    public Integer getChildInterfaceId() {
        return ChildInterfaceId;
    }

    public void setChildInterfaceId(Integer ChildInterfaceId) {
        this.ChildInterfaceId = ChildInterfaceId;
    }
    public Integer getPMExtension() {
        return PMExtension;
    }

    public void setPMExtension(Integer PMExtension) {
        this.PMExtension = PMExtension;
    }
    public Integer getIsUseLongMaterialName() {
        return IsUseLongMaterialName;
    }

    public void setIsUseLongMaterialName(Integer IsUseLongMaterialName) {
        this.IsUseLongMaterialName = IsUseLongMaterialName;
    }
    public Integer getIsDisplayQualityStandards() {
        return IsDisplayQualityStandards;
    }

    public void setIsDisplayQualityStandards(Integer IsDisplayQualityStandards) {
        this.IsDisplayQualityStandards = IsDisplayQualityStandards;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public Integer getIsReturnBackSend() {
        return IsReturnBackSend;
    }

    public void setIsReturnBackSend(Integer IsReturnBackSend) {
        this.IsReturnBackSend = IsReturnBackSend;
    }
    public Integer getIsAutoSend() {
        return IsAutoSend;
    }

    public void setIsAutoSend(Integer IsAutoSend) {
        this.IsAutoSend = IsAutoSend;
    }
    public Integer getIsDisplayPMMoney() {
        return IsDisplayPMMoney;
    }

    public void setIsDisplayPMMoney(Integer IsDisplayPMMoney) {
        this.IsDisplayPMMoney = IsDisplayPMMoney;
    }
    public Integer getPaymentApplyCheckedSend() {
        return PaymentApplyCheckedSend;
    }

    public void setPaymentApplyCheckedSend(Integer PaymentApplyCheckedSend) {
        this.PaymentApplyCheckedSend = PaymentApplyCheckedSend;
    }
    public Integer getPMForTJSH() {
        return PMForTJSH;
    }

    public void setPMForTJSH(Integer PMForTJSH) {
        this.PMForTJSH = PMForTJSH;
    }
    public Integer getIsUseMROExtension() {
        return IsUseMROExtension;
    }

    public void setIsUseMROExtension(Integer IsUseMROExtension) {
        this.IsUseMROExtension = IsUseMROExtension;
    }
    public Integer getIsMergerMaterialName() {
        return IsMergerMaterialName;
    }

    public void setIsMergerMaterialName(Integer IsMergerMaterialName) {
        this.IsMergerMaterialName = IsMergerMaterialName;
    }
    public Integer getIsUseEC() {
        return IsUseEC;
    }

    public void setIsUseEC(Integer IsUseEC) {
        this.IsUseEC = IsUseEC;
    }
    public Integer getZQGenerateContract() {
        return ZQGenerateContract;
    }

    public void setZQGenerateContract(Integer ZQGenerateContract) {
        this.ZQGenerateContract = ZQGenerateContract;
    }
    public Integer getQFGenerateContract() {
        return QFGenerateContract;
    }

    public void setQFGenerateContract(Integer QFGenerateContract) {
        this.QFGenerateContract = QFGenerateContract;
    }
    public Integer getSQGenerateContract() {
        return SQGenerateContract;
    }

    public void setSQGenerateContract(Integer SQGenerateContract) {
        this.SQGenerateContract = SQGenerateContract;
    }
    public Integer getQQGenerateContract() {
        return QQGenerateContract;
    }

    public void setQQGenerateContract(Integer QQGenerateContract) {
        this.QQGenerateContract = QQGenerateContract;
    }
    public Integer getIsUsePaymentExt() {
        return IsUsePaymentExt;
    }

    public void setIsUsePaymentExt(Integer IsUsePaymentExt) {
        this.IsUsePaymentExt = IsUsePaymentExt;
    }
    public String getRFCConfig() {
        return RFCConfig;
    }

    public void setRFCConfig(String RFCConfig) {
        this.RFCConfig = RFCConfig;
    }
    public Integer getIsUseSelfERP() {
        return IsUseSelfERP;
    }

    public void setIsUseSelfERP(Integer IsUseSelfERP) {
        this.IsUseSelfERP = IsUseSelfERP;
    }
    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }
    public String getContractType() {
        return ContractType;
    }

    public void setContractType(String ContractType) {
        this.ContractType = ContractType;
    }
    public Integer getXQGenerateContract() {
        return XQGenerateContract;
    }

    public void setXQGenerateContract(Integer XQGenerateContract) {
        this.XQGenerateContract = XQGenerateContract;
    }
    public Integer getIsUseERPMass() {
        return IsUseERPMass;
    }

    public void setIsUseERPMass(Integer IsUseERPMass) {
        this.IsUseERPMass = IsUseERPMass;
    }
    public Integer getIsUseTax() {
        return IsUseTax;
    }

    public void setIsUseTax(Integer IsUseTax) {
        this.IsUseTax = IsUseTax;
    }
    public Integer getIsDisplayAllPM() {
        return IsDisplayAllPM;
    }

    public void setIsDisplayAllPM(Integer IsDisplayAllPM) {
        this.IsDisplayAllPM = IsDisplayAllPM;
    }

    @Override
    public String toString() {
        return "SysInterfaceconfig{" +
        "ouid=" + ouid +
        ", InterfaceId=" + InterfaceId +
        ", OUBelongType=" + OUBelongType +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", MakeSend=" + MakeSend +
        ", ChangeSend=" + ChangeSend +
        ", TerminateSend=" + TerminateSend +
        ", PaymentApplySend=" + PaymentApplySend +
        ", SDCollect=" + SDCollect +
        ", MaterialCodeSet=" + MaterialCodeSet +
        ", IsDisplayAllOrder=" + IsDisplayAllOrder +
        ", IsDisplayAllProject=" + IsDisplayAllProject +
        ", TransferSendERP=" + TransferSendERP +
        ", ChildInterfaceId=" + ChildInterfaceId +
        ", PMExtension=" + PMExtension +
        ", IsUseLongMaterialName=" + IsUseLongMaterialName +
        ", IsDisplayQualityStandards=" + IsDisplayQualityStandards +
        ", Oulabel=" + Oulabel +
        ", IsReturnBackSend=" + IsReturnBackSend +
        ", IsAutoSend=" + IsAutoSend +
        ", IsDisplayPMMoney=" + IsDisplayPMMoney +
        ", PaymentApplyCheckedSend=" + PaymentApplyCheckedSend +
        ", PMForTJSH=" + PMForTJSH +
        ", IsUseMROExtension=" + IsUseMROExtension +
        ", IsMergerMaterialName=" + IsMergerMaterialName +
        ", IsUseEC=" + IsUseEC +
        ", ZQGenerateContract=" + ZQGenerateContract +
        ", QFGenerateContract=" + QFGenerateContract +
        ", SQGenerateContract=" + SQGenerateContract +
        ", QQGenerateContract=" + QQGenerateContract +
        ", IsUsePaymentExt=" + IsUsePaymentExt +
        ", RFCConfig=" + RFCConfig +
        ", IsUseSelfERP=" + IsUseSelfERP +
        ", CompanyCode=" + CompanyCode +
        ", ContractType=" + ContractType +
        ", XQGenerateContract=" + XQGenerateContract +
        ", IsUseERPMass=" + IsUseERPMass +
        ", IsUseTax=" + IsUseTax +
        ", IsDisplayAllPM=" + IsDisplayAllPM +
        "}";
    }
}
