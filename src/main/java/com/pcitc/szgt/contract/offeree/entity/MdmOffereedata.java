package com.pcitc.szgt.contract.offeree.entity;

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
public class MdmOffereedata implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("OffereeId")
    private String OffereeId;

    /**
     * 类别 MDM ERP 默认MDM
     */
    @TableField("Category")
    private Integer Category;

    /**
     * 相对人编码
     */
    @TableField("OffereeCode")
    private String OffereeCode;

    /**
     * 相对人名称
     */
    @TableField("OffereeName")
    private String OffereeName;

    /**
     * 相对人归属 0 MDM() 1 ERP 
     */
    @TableField("OffereeBelong")
    private String OffereeBelong;

    /**
     * 相对人分类 0供应商 1客户
     */
    @TableField("OffereeSort")
    private String OffereeSort;

    /**
     * 机构类型 默认企业 
     */
    @TableField("CompanyType")
    private String CompanyType;

    /**
     * 法定代表人
     */
    @TableField("Corporation")
    private String Corporation;

    /**
     * 组织机构代码
     */
    @TableField("OrgCode")
    private String OrgCode;

    /**
     * 税务登记号
     */
    @TableField("TaxRegisterCode")
    private String TaxRegisterCode;

    /**
     * 注册号
     */
    @TableField("BusinessLicenseCode")
    private String BusinessLicenseCode;

    /**
     * 准入证号
     */
    @TableField("MarketAccessCode")
    private String MarketAccessCode;

    /**
     * 证件编号
     */
    @TableField("LawQualCode")
    private String LawQualCode;

    /**
     * 办公地址
     */
    @TableField("OfficeAddress")
    private String OfficeAddress;

    /**
     * 注册地址
     */
    @TableField("RegisterAddress")
    private String RegisterAddress;

    /**
     * 联系人
     */
    @TableField("LinkManName")
    private String LinkManName;

    /**
     * 联系人职位
     */
    @TableField("LinkManPosition")
    private String LinkManPosition;

    /**
     * 联系人电话
     */
    @TableField("LinkManPhone")
    private String LinkManPhone;

    /**
     * 邮箱
     */
    @TableField("Email")
    private String Email;

    /**
     * 传真
     */
    @TableField("Fax")
    private String Fax;

    /**
     * 开户银行代码
     */
    @TableField("BankName")
    private String BankName;

    /**
     * 银行编号
     */
    @TableField("BankCode")
    private String BankCode;

    /**
     * 银行开户名称
     */
    @TableField("OpenUints")
    private String OpenUints;

    /**
     * 银行账号
     */
    @TableField("BankAcount")
    private String BankAcount;

    /**
     * 机构类型 0机构 1自然人
     */
    @TableField("OffereeType")
    private Integer OffereeType;

    /**
     * 身份证
     */
    @TableField("IDCard")
    private String IDCard;

    /**
     * 是否有效 1是 0否
     */
    @TableField("IsValid")
    private String IsValid;

    /**
     * 创建日期
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 修改日期
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 来源类型 INSYS_INUNIT 内部单位   INSYS_OUTUNIT 外部单位
     */
    @TableField("SourceType")
    private String SourceType;

    public String getOffereeId() {
        return OffereeId;
    }

    public void setOffereeId(String OffereeId) {
        this.OffereeId = OffereeId;
    }
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }
    public String getOffereeCode() {
        return OffereeCode;
    }

    public void setOffereeCode(String OffereeCode) {
        this.OffereeCode = OffereeCode;
    }
    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String OffereeName) {
        this.OffereeName = OffereeName;
    }
    public String getOffereeBelong() {
        return OffereeBelong;
    }

    public void setOffereeBelong(String OffereeBelong) {
        this.OffereeBelong = OffereeBelong;
    }
    public String getOffereeSort() {
        return OffereeSort;
    }

    public void setOffereeSort(String OffereeSort) {
        this.OffereeSort = OffereeSort;
    }
    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String CompanyType) {
        this.CompanyType = CompanyType;
    }
    public String getCorporation() {
        return Corporation;
    }

    public void setCorporation(String Corporation) {
        this.Corporation = Corporation;
    }
    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
    }
    public String getTaxRegisterCode() {
        return TaxRegisterCode;
    }

    public void setTaxRegisterCode(String TaxRegisterCode) {
        this.TaxRegisterCode = TaxRegisterCode;
    }
    public String getBusinessLicenseCode() {
        return BusinessLicenseCode;
    }

    public void setBusinessLicenseCode(String BusinessLicenseCode) {
        this.BusinessLicenseCode = BusinessLicenseCode;
    }
    public String getMarketAccessCode() {
        return MarketAccessCode;
    }

    public void setMarketAccessCode(String MarketAccessCode) {
        this.MarketAccessCode = MarketAccessCode;
    }
    public String getLawQualCode() {
        return LawQualCode;
    }

    public void setLawQualCode(String LawQualCode) {
        this.LawQualCode = LawQualCode;
    }
    public String getOfficeAddress() {
        return OfficeAddress;
    }

    public void setOfficeAddress(String OfficeAddress) {
        this.OfficeAddress = OfficeAddress;
    }
    public String getRegisterAddress() {
        return RegisterAddress;
    }

    public void setRegisterAddress(String RegisterAddress) {
        this.RegisterAddress = RegisterAddress;
    }
    public String getLinkManName() {
        return LinkManName;
    }

    public void setLinkManName(String LinkManName) {
        this.LinkManName = LinkManName;
    }
    public String getLinkManPosition() {
        return LinkManPosition;
    }

    public void setLinkManPosition(String LinkManPosition) {
        this.LinkManPosition = LinkManPosition;
    }
    public String getLinkManPhone() {
        return LinkManPhone;
    }

    public void setLinkManPhone(String LinkManPhone) {
        this.LinkManPhone = LinkManPhone;
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
    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }
    public String getOpenUints() {
        return OpenUints;
    }

    public void setOpenUints(String OpenUints) {
        this.OpenUints = OpenUints;
    }
    public String getBankAcount() {
        return BankAcount;
    }

    public void setBankAcount(String BankAcount) {
        this.BankAcount = BankAcount;
    }
    public Integer getOffereeType() {
        return OffereeType;
    }

    public void setOffereeType(Integer OffereeType) {
        this.OffereeType = OffereeType;
    }
    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }
    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String IsValid) {
        this.IsValid = IsValid;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    @Override
    public String toString() {
        return "MdmOffereedata{" +
        "OffereeId=" + OffereeId +
        ", Category=" + Category +
        ", OffereeCode=" + OffereeCode +
        ", OffereeName=" + OffereeName +
        ", OffereeBelong=" + OffereeBelong +
        ", OffereeSort=" + OffereeSort +
        ", CompanyType=" + CompanyType +
        ", Corporation=" + Corporation +
        ", OrgCode=" + OrgCode +
        ", TaxRegisterCode=" + TaxRegisterCode +
        ", BusinessLicenseCode=" + BusinessLicenseCode +
        ", MarketAccessCode=" + MarketAccessCode +
        ", LawQualCode=" + LawQualCode +
        ", OfficeAddress=" + OfficeAddress +
        ", RegisterAddress=" + RegisterAddress +
        ", LinkManName=" + LinkManName +
        ", LinkManPosition=" + LinkManPosition +
        ", LinkManPhone=" + LinkManPhone +
        ", Email=" + Email +
        ", Fax=" + Fax +
        ", BankName=" + BankName +
        ", BankCode=" + BankCode +
        ", OpenUints=" + OpenUints +
        ", BankAcount=" + BankAcount +
        ", OffereeType=" + OffereeType +
        ", IDCard=" + IDCard +
        ", IsValid=" + IsValid +
        ", CreatedDate=" + CreatedDate +
        ", CreatedBy=" + CreatedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", ModifiedBy=" + ModifiedBy +
        "}";
    }
}
