package com.pcitc.szgt.contract.make.entity;

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
 * @since 2020-02-20
 */
public class CrContractofferee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractID")
    private String ContractID;

    @TableField("OffereeID")
    private String OffereeID;

    @TableField("OldOffereeID")
    private String OldOffereeID;

    @TableField("LinkID")
    private String LinkID;

    @TableField("BankID")
    private String BankID;

    @TableField("OffereeName")
    private String OffereeName;

    @TableField("Position")
    private String Position;

    @TableField("ContractOffereeType")
    private Integer ContractOffereeType;

    @TableField("OffereeType")
    private Integer OffereeType;

    @TableField("OffereeLinkMan")
    private String OffereeLinkMan;

    @TableField("OffereeLinkPhone")
    private String OffereeLinkPhone;

    @TableField("OffereeCheck")
    private Integer OffereeCheck;

    @TableField("OffereeBelong")
    private Integer OffereeBelong;

    @TableField("OrderID")
    private Integer OrderID;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Flag")
    private Integer Flag;

    @TableField("Email")
    private String Email;

    @TableField("Fax")
    private String Fax;

    @TableField("BankName")
    private String BankName;

    @TableField("OpenUints")
    private String OpenUints;

    @TableField("BankAccount")
    private String BankAccount;

    @TableField("MainUserID")
    private String MainUserID;

    @TableField("OffereeTypeName")
    private String OffereeTypeName;

    @TableField("InnerContractType")
    private Integer InnerContractType;

    @TableField("InnerContractOperator")
    private String InnerContractOperator;

    @TableField("InnerContractOperatorName")
    private String InnerContractOperatorName;

    /**
     * 开票相对人Id
     */
    @TableField("Z_InvoiceOffereeId")
    private String zInvoiceoffereeid;

    /**
     * 开票相对人名称
     */
    @TableField("Z_InvoiceOffereeName")
    private String zInvoiceoffereename;

    /**
     * 开票金额
     */
    @TableField("Z_InvoiceMoney")
    private BigDecimal zInvoicemoney;

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String OffereeID) {
        this.OffereeID = OffereeID;
    }
    public String getOldOffereeID() {
        return OldOffereeID;
    }

    public void setOldOffereeID(String OldOffereeID) {
        this.OldOffereeID = OldOffereeID;
    }
    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String LinkID) {
        this.LinkID = LinkID;
    }
    public String getBankID() {
        return BankID;
    }

    public void setBankID(String BankID) {
        this.BankID = BankID;
    }
    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String OffereeName) {
        this.OffereeName = OffereeName;
    }
    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
    public Integer getContractOffereeType() {
        return ContractOffereeType;
    }

    public void setContractOffereeType(Integer ContractOffereeType) {
        this.ContractOffereeType = ContractOffereeType;
    }
    public Integer getOffereeType() {
        return OffereeType;
    }

    public void setOffereeType(Integer OffereeType) {
        this.OffereeType = OffereeType;
    }
    public String getOffereeLinkMan() {
        return OffereeLinkMan;
    }

    public void setOffereeLinkMan(String OffereeLinkMan) {
        this.OffereeLinkMan = OffereeLinkMan;
    }
    public String getOffereeLinkPhone() {
        return OffereeLinkPhone;
    }

    public void setOffereeLinkPhone(String OffereeLinkPhone) {
        this.OffereeLinkPhone = OffereeLinkPhone;
    }
    public Integer getOffereeCheck() {
        return OffereeCheck;
    }

    public void setOffereeCheck(Integer OffereeCheck) {
        this.OffereeCheck = OffereeCheck;
    }
    public Integer getOffereeBelong() {
        return OffereeBelong;
    }

    public void setOffereeBelong(Integer OffereeBelong) {
        this.OffereeBelong = OffereeBelong;
    }
    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer OrderID) {
        this.OrderID = OrderID;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
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
    public Integer getFlag() {
        return Flag;
    }

    public void setFlag(Integer Flag) {
        this.Flag = Flag;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }
    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }
    public String getOpenUints() {
        return OpenUints;
    }

    public void setOpenUints(String OpenUints) {
        this.OpenUints = OpenUints;
    }
    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String BankAccount) {
        this.BankAccount = BankAccount;
    }
    public String getMainUserID() {
        return MainUserID;
    }

    public void setMainUserID(String MainUserID) {
        this.MainUserID = MainUserID;
    }
    public String getOffereeTypeName() {
        return OffereeTypeName;
    }

    public void setOffereeTypeName(String OffereeTypeName) {
        this.OffereeTypeName = OffereeTypeName;
    }
    public Integer getInnerContractType() {
        return InnerContractType;
    }

    public void setInnerContractType(Integer InnerContractType) {
        this.InnerContractType = InnerContractType;
    }
    public String getInnerContractOperator() {
        return InnerContractOperator;
    }

    public void setInnerContractOperator(String InnerContractOperator) {
        this.InnerContractOperator = InnerContractOperator;
    }
    public String getInnerContractOperatorName() {
        return InnerContractOperatorName;
    }

    public void setInnerContractOperatorName(String InnerContractOperatorName) {
        this.InnerContractOperatorName = InnerContractOperatorName;
    }
    public String getzInvoiceoffereeid() {
        return zInvoiceoffereeid;
    }

    public void setzInvoiceoffereeid(String zInvoiceoffereeid) {
        this.zInvoiceoffereeid = zInvoiceoffereeid;
    }
    public String getzInvoiceoffereename() {
        return zInvoiceoffereename;
    }

    public void setzInvoiceoffereename(String zInvoiceoffereename) {
        this.zInvoiceoffereename = zInvoiceoffereename;
    }
    public BigDecimal getzInvoicemoney() {
        return zInvoicemoney;
    }

    public void setzInvoicemoney(BigDecimal zInvoicemoney) {
        this.zInvoicemoney = zInvoicemoney;
    }

    @Override
    public String toString() {
        return "CrContractofferee{" +
        "ContractID=" + ContractID +
        ", OffereeID=" + OffereeID +
        ", OldOffereeID=" + OldOffereeID +
        ", LinkID=" + LinkID +
        ", BankID=" + BankID +
        ", OffereeName=" + OffereeName +
        ", Position=" + Position +
        ", ContractOffereeType=" + ContractOffereeType +
        ", OffereeType=" + OffereeType +
        ", OffereeLinkMan=" + OffereeLinkMan +
        ", OffereeLinkPhone=" + OffereeLinkPhone +
        ", OffereeCheck=" + OffereeCheck +
        ", OffereeBelong=" + OffereeBelong +
        ", OrderID=" + OrderID +
        ", Oulabel=" + Oulabel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Flag=" + Flag +
        ", Email=" + Email +
        ", Fax=" + Fax +
        ", BankName=" + BankName +
        ", OpenUints=" + OpenUints +
        ", BankAccount=" + BankAccount +
        ", MainUserID=" + MainUserID +
        ", OffereeTypeName=" + OffereeTypeName +
        ", InnerContractType=" + InnerContractType +
        ", InnerContractOperator=" + InnerContractOperator +
        ", InnerContractOperatorName=" + InnerContractOperatorName +
        ", zInvoiceoffereeid=" + zInvoiceoffereeid +
        ", zInvoiceoffereename=" + zInvoiceoffereename +
        ", zInvoicemoney=" + zInvoicemoney +
        "}";
    }
}
