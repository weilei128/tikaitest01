package com.pcitc.szgt.contract.perform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-02
 */
public class CrContractchangebiddetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AccordingID")
    private String AccordingID;

    @TableField("AccordingSourde")
    private Integer AccordingSourde;

    @TableField("AccordingType")
    private Integer AccordingType;

    @TableField("ContractCode")
    private String ContractCode;

    @TableField("OrderID")
    private String OrderID;

    @TableField("MaterialGroupCode")
    private String MaterialGroupCode;

    @TableField("MaterialGroupName")
    private String MaterialGroupName;

    @TableField("MaterialCode")
    private String MaterialCode;

    @TableField("MaterialName")
    private String MaterialName;

    @TableField("Norms")
    private String Norms;

    @TableField("Units")
    private String Units;

    @TableField("Number")
    private BigDecimal Number;

    @TableField("Price")
    private BigDecimal Price;

    @TableField("Rate")
    private BigDecimal Rate;

    @TableField("DeliveryDate")
    private LocalDateTime DeliveryDate;

    @TableField("ReferCount")
    private Integer ReferCount;

    @TableField("LogicDel")
    private Integer LogicDel;

    @TableField("Remark")
    private String Remark;

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

    @TableField("ItemId")
    private String ItemId;

    @TableField("OrderName")
    private String OrderName;

    @TableField("WorkCenter")
    private String WorkCenter;

    @TableField("ProcessShortText")
    private String ProcessShortText;

    @TableField("FrameCode")
    private String FrameCode;

    @TableField("RowTotal")
    private BigDecimal RowTotal;

    @TableField("TotalAmount")
    private BigDecimal TotalAmount;

    @TableField("ERPSource")
    private String ERPSource;

    @TableField("ERPMode")
    private Integer ERPMode;

    @TableField("CompanyID")
    private String CompanyID;

    @TableField("CardBrand")
    private String CardBrand;

    @TableField("ProductMaker")
    private String ProductMaker;

    @TableField("Remark01")
    private String Remark01;

    @TableField("Remark02")
    private String Remark02;

    @TableField("Remark03")
    private String Remark03;

    @TableField("Remark04")
    private String Remark04;

    @TableField("Remark05")
    private String Remark05;

    @TableField("Remark06")
    private String Remark06;

    @TableField("Remark07")
    private String Remark07;

    @TableField("Remark08")
    private String Remark08;

    @TableField("Remark09")
    private String Remark09;

    @TableField("Remark10")
    private String Remark10;

    @TableField("Grade")
    private String Grade;

    @TableField("MaterialCode8")
    private String MaterialCode8;

    @TableField("MaterialLevel")
    private String MaterialLevel;

    @TableField("Currency")
    private Integer Currency;

    @TableField("CurrencyName")
    private String CurrencyName;

    @TableField("ProductDemo")
    private String ProductDemo;

    @TableField("ContractChangeId")
    private String ContractChangeId;

    @TableField("ContractId")
    private String ContractId;

    @TableField("PriceNoFax")
    private BigDecimal PriceNoFax;

    @TableField("DriverFeeFax")
    private BigDecimal DriverFeeFax;

    @TableField("DriverFee")
    private BigDecimal DriverFee;

    @TableField("MatrialPurchaseFee")
    private BigDecimal MatrialPurchaseFee;

    @TableField("MatrialFax")
    private BigDecimal MatrialFax;

    @TableField("PurchaseDemo")
    private String PurchaseDemo;

    @TableField("GroupFlag")
    private Integer GroupFlag;

    @TableField("MassFrameNum")
    private String MassFrameNum;

    @TableField("ERPMassRecID")
    private String ERPMassRecID;

    @TableField("ERPMassContractId")
    private String ERPMassContractId;

    @TableField("ServiceCode")
    private String ServiceCode;

    @TableField("ServiceDesc")
    private String ServiceDesc;

    @TableField("WBSCode")
    private String WBSCode;

    @TableField("WBSName")
    private String WBSName;

    @TableField("ProcessType")
    private Integer ProcessType;

    @TableField("ChargeManCode")
    private String ChargeManCode;

    @TableField("ChargeManName")
    private String ChargeManName;

    @TableField("TaxCode")
    private String TaxCode;

    @TableField("SourceAccordingID")
    private String SourceAccordingID;

    @TableField("Z_ChangeApplyMoney")
    private BigDecimal zChangeapplymoney;

    @TableField("Z_OwnerApprovedMoney")
    private BigDecimal zOwnerapprovedmoney;

    @TableField("oldItemID")
    private String oldItemID;

    public String getAccordingID() {
        return AccordingID;
    }

    public void setAccordingID(String AccordingID) {
        this.AccordingID = AccordingID;
    }
    public Integer getAccordingSourde() {
        return AccordingSourde;
    }

    public void setAccordingSourde(Integer AccordingSourde) {
        this.AccordingSourde = AccordingSourde;
    }
    public Integer getAccordingType() {
        return AccordingType;
    }

    public void setAccordingType(Integer AccordingType) {
        this.AccordingType = AccordingType;
    }
    public String getContractCode() {
        return ContractCode;
    }

    public void setContractCode(String ContractCode) {
        this.ContractCode = ContractCode;
    }
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }
    public String getMaterialGroupCode() {
        return MaterialGroupCode;
    }

    public void setMaterialGroupCode(String MaterialGroupCode) {
        this.MaterialGroupCode = MaterialGroupCode;
    }
    public String getMaterialGroupName() {
        return MaterialGroupName;
    }

    public void setMaterialGroupName(String MaterialGroupName) {
        this.MaterialGroupName = MaterialGroupName;
    }
    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String MaterialCode) {
        this.MaterialCode = MaterialCode;
    }
    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName = MaterialName;
    }
    public String getNorms() {
        return Norms;
    }

    public void setNorms(String Norms) {
        this.Norms = Norms;
    }
    public String getUnits() {
        return Units;
    }

    public void setUnits(String Units) {
        this.Units = Units;
    }
    public BigDecimal getNumber() {
        return Number;
    }

    public void setNumber(BigDecimal Number) {
        this.Number = Number;
    }
    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }
    public BigDecimal getRate() {
        return Rate;
    }

    public void setRate(BigDecimal Rate) {
        this.Rate = Rate;
    }
    public LocalDateTime getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(LocalDateTime DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }
    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer ReferCount) {
        this.ReferCount = ReferCount;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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
    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }
    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String OrderName) {
        this.OrderName = OrderName;
    }
    public String getWorkCenter() {
        return WorkCenter;
    }

    public void setWorkCenter(String WorkCenter) {
        this.WorkCenter = WorkCenter;
    }
    public String getProcessShortText() {
        return ProcessShortText;
    }

    public void setProcessShortText(String ProcessShortText) {
        this.ProcessShortText = ProcessShortText;
    }
    public String getFrameCode() {
        return FrameCode;
    }

    public void setFrameCode(String FrameCode) {
        this.FrameCode = FrameCode;
    }
    public BigDecimal getRowTotal() {
        return RowTotal;
    }

    public void setRowTotal(BigDecimal RowTotal) {
        this.RowTotal = RowTotal;
    }
    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal TotalAmount) {
        this.TotalAmount = TotalAmount;
    }
    public String getERPSource() {
        return ERPSource;
    }

    public void setERPSource(String ERPSource) {
        this.ERPSource = ERPSource;
    }
    public Integer getERPMode() {
        return ERPMode;
    }

    public void setERPMode(Integer ERPMode) {
        this.ERPMode = ERPMode;
    }
    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String CompanyID) {
        this.CompanyID = CompanyID;
    }
    public String getCardBrand() {
        return CardBrand;
    }

    public void setCardBrand(String CardBrand) {
        this.CardBrand = CardBrand;
    }
    public String getProductMaker() {
        return ProductMaker;
    }

    public void setProductMaker(String ProductMaker) {
        this.ProductMaker = ProductMaker;
    }
    public String getRemark01() {
        return Remark01;
    }

    public void setRemark01(String Remark01) {
        this.Remark01 = Remark01;
    }
    public String getRemark02() {
        return Remark02;
    }

    public void setRemark02(String Remark02) {
        this.Remark02 = Remark02;
    }
    public String getRemark03() {
        return Remark03;
    }

    public void setRemark03(String Remark03) {
        this.Remark03 = Remark03;
    }
    public String getRemark04() {
        return Remark04;
    }

    public void setRemark04(String Remark04) {
        this.Remark04 = Remark04;
    }
    public String getRemark05() {
        return Remark05;
    }

    public void setRemark05(String Remark05) {
        this.Remark05 = Remark05;
    }
    public String getRemark06() {
        return Remark06;
    }

    public void setRemark06(String Remark06) {
        this.Remark06 = Remark06;
    }
    public String getRemark07() {
        return Remark07;
    }

    public void setRemark07(String Remark07) {
        this.Remark07 = Remark07;
    }
    public String getRemark08() {
        return Remark08;
    }

    public void setRemark08(String Remark08) {
        this.Remark08 = Remark08;
    }
    public String getRemark09() {
        return Remark09;
    }

    public void setRemark09(String Remark09) {
        this.Remark09 = Remark09;
    }
    public String getRemark10() {
        return Remark10;
    }

    public void setRemark10(String Remark10) {
        this.Remark10 = Remark10;
    }
    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }
    public String getMaterialCode8() {
        return MaterialCode8;
    }

    public void setMaterialCode8(String MaterialCode8) {
        this.MaterialCode8 = MaterialCode8;
    }
    public String getMaterialLevel() {
        return MaterialLevel;
    }

    public void setMaterialLevel(String MaterialLevel) {
        this.MaterialLevel = MaterialLevel;
    }
    public Integer getCurrency() {
        return Currency;
    }

    public void setCurrency(Integer Currency) {
        this.Currency = Currency;
    }
    public String getCurrencyName() {
        return CurrencyName;
    }

    public void setCurrencyName(String CurrencyName) {
        this.CurrencyName = CurrencyName;
    }
    public String getProductDemo() {
        return ProductDemo;
    }

    public void setProductDemo(String ProductDemo) {
        this.ProductDemo = ProductDemo;
    }
    public String getContractChangeId() {
        return ContractChangeId;
    }

    public void setContractChangeId(String ContractChangeId) {
        this.ContractChangeId = ContractChangeId;
    }
    public String getContractId() {
        return ContractId;
    }

    public void setContractId(String ContractId) {
        this.ContractId = ContractId;
    }
    public BigDecimal getPriceNoFax() {
        return PriceNoFax;
    }

    public void setPriceNoFax(BigDecimal PriceNoFax) {
        this.PriceNoFax = PriceNoFax;
    }
    public BigDecimal getDriverFeeFax() {
        return DriverFeeFax;
    }

    public void setDriverFeeFax(BigDecimal DriverFeeFax) {
        this.DriverFeeFax = DriverFeeFax;
    }
    public BigDecimal getDriverFee() {
        return DriverFee;
    }

    public void setDriverFee(BigDecimal DriverFee) {
        this.DriverFee = DriverFee;
    }
    public BigDecimal getMatrialPurchaseFee() {
        return MatrialPurchaseFee;
    }

    public void setMatrialPurchaseFee(BigDecimal MatrialPurchaseFee) {
        this.MatrialPurchaseFee = MatrialPurchaseFee;
    }
    public BigDecimal getMatrialFax() {
        return MatrialFax;
    }

    public void setMatrialFax(BigDecimal MatrialFax) {
        this.MatrialFax = MatrialFax;
    }
    public String getPurchaseDemo() {
        return PurchaseDemo;
    }

    public void setPurchaseDemo(String PurchaseDemo) {
        this.PurchaseDemo = PurchaseDemo;
    }
    public Integer getGroupFlag() {
        return GroupFlag;
    }

    public void setGroupFlag(Integer GroupFlag) {
        this.GroupFlag = GroupFlag;
    }
    public String getMassFrameNum() {
        return MassFrameNum;
    }

    public void setMassFrameNum(String MassFrameNum) {
        this.MassFrameNum = MassFrameNum;
    }
    public String getERPMassRecID() {
        return ERPMassRecID;
    }

    public void setERPMassRecID(String ERPMassRecID) {
        this.ERPMassRecID = ERPMassRecID;
    }
    public String getERPMassContractId() {
        return ERPMassContractId;
    }

    public void setERPMassContractId(String ERPMassContractId) {
        this.ERPMassContractId = ERPMassContractId;
    }
    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String ServiceCode) {
        this.ServiceCode = ServiceCode;
    }
    public String getServiceDesc() {
        return ServiceDesc;
    }

    public void setServiceDesc(String ServiceDesc) {
        this.ServiceDesc = ServiceDesc;
    }
    public String getWBSCode() {
        return WBSCode;
    }

    public void setWBSCode(String WBSCode) {
        this.WBSCode = WBSCode;
    }
    public String getWBSName() {
        return WBSName;
    }

    public void setWBSName(String WBSName) {
        this.WBSName = WBSName;
    }
    public Integer getProcessType() {
        return ProcessType;
    }

    public void setProcessType(Integer ProcessType) {
        this.ProcessType = ProcessType;
    }
    public String getChargeManCode() {
        return ChargeManCode;
    }

    public void setChargeManCode(String ChargeManCode) {
        this.ChargeManCode = ChargeManCode;
    }
    public String getChargeManName() {
        return ChargeManName;
    }

    public void setChargeManName(String ChargeManName) {
        this.ChargeManName = ChargeManName;
    }
    public String getTaxCode() {
        return TaxCode;
    }

    public void setTaxCode(String TaxCode) {
        this.TaxCode = TaxCode;
    }
    public String getSourceAccordingID() {
        return SourceAccordingID;
    }

    public void setSourceAccordingID(String SourceAccordingID) {
        this.SourceAccordingID = SourceAccordingID;
    }
    public BigDecimal getzChangeapplymoney() {
        return zChangeapplymoney;
    }

    public void setzChangeapplymoney(BigDecimal zChangeapplymoney) {
        this.zChangeapplymoney = zChangeapplymoney;
    }
    public BigDecimal getzOwnerapprovedmoney() {
        return zOwnerapprovedmoney;
    }

    public void setzOwnerapprovedmoney(BigDecimal zOwnerapprovedmoney) {
        this.zOwnerapprovedmoney = zOwnerapprovedmoney;
    }
    public String getOldItemID() {
        return oldItemID;
    }

    public void setOldItemID(String oldItemID) {
        this.oldItemID = oldItemID;
    }

    @Override
    public String toString() {
        return "CrContractchangebiddetail{" +
        "AccordingID=" + AccordingID +
        ", AccordingSourde=" + AccordingSourde +
        ", AccordingType=" + AccordingType +
        ", ContractCode=" + ContractCode +
        ", OrderID=" + OrderID +
        ", MaterialGroupCode=" + MaterialGroupCode +
        ", MaterialGroupName=" + MaterialGroupName +
        ", MaterialCode=" + MaterialCode +
        ", MaterialName=" + MaterialName +
        ", Norms=" + Norms +
        ", Units=" + Units +
        ", Number=" + Number +
        ", Price=" + Price +
        ", Rate=" + Rate +
        ", DeliveryDate=" + DeliveryDate +
        ", ReferCount=" + ReferCount +
        ", LogicDel=" + LogicDel +
        ", Remark=" + Remark +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", ItemId=" + ItemId +
        ", OrderName=" + OrderName +
        ", WorkCenter=" + WorkCenter +
        ", ProcessShortText=" + ProcessShortText +
        ", FrameCode=" + FrameCode +
        ", RowTotal=" + RowTotal +
        ", TotalAmount=" + TotalAmount +
        ", ERPSource=" + ERPSource +
        ", ERPMode=" + ERPMode +
        ", CompanyID=" + CompanyID +
        ", CardBrand=" + CardBrand +
        ", ProductMaker=" + ProductMaker +
        ", Remark01=" + Remark01 +
        ", Remark02=" + Remark02 +
        ", Remark03=" + Remark03 +
        ", Remark04=" + Remark04 +
        ", Remark05=" + Remark05 +
        ", Remark06=" + Remark06 +
        ", Remark07=" + Remark07 +
        ", Remark08=" + Remark08 +
        ", Remark09=" + Remark09 +
        ", Remark10=" + Remark10 +
        ", Grade=" + Grade +
        ", MaterialCode8=" + MaterialCode8 +
        ", MaterialLevel=" + MaterialLevel +
        ", Currency=" + Currency +
        ", CurrencyName=" + CurrencyName +
        ", ProductDemo=" + ProductDemo +
        ", ContractChangeId=" + ContractChangeId +
        ", ContractId=" + ContractId +
        ", PriceNoFax=" + PriceNoFax +
        ", DriverFeeFax=" + DriverFeeFax +
        ", DriverFee=" + DriverFee +
        ", MatrialPurchaseFee=" + MatrialPurchaseFee +
        ", MatrialFax=" + MatrialFax +
        ", PurchaseDemo=" + PurchaseDemo +
        ", GroupFlag=" + GroupFlag +
        ", MassFrameNum=" + MassFrameNum +
        ", ERPMassRecID=" + ERPMassRecID +
        ", ERPMassContractId=" + ERPMassContractId +
        ", ServiceCode=" + ServiceCode +
        ", ServiceDesc=" + ServiceDesc +
        ", WBSCode=" + WBSCode +
        ", WBSName=" + WBSName +
        ", ProcessType=" + ProcessType +
        ", ChargeManCode=" + ChargeManCode +
        ", ChargeManName=" + ChargeManName +
        ", TaxCode=" + TaxCode +
        ", SourceAccordingID=" + SourceAccordingID +
        ", zChangeapplymoney=" + zChangeapplymoney +
        ", zOwnerapprovedmoney=" + zOwnerapprovedmoney +
        ", oldItemID=" + oldItemID +
        "}";
    }
}
