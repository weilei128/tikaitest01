package com.pcitc.szgt.contract.offeree.model;

import java.time.LocalDateTime;

public class MdmOffereedataVo {

    private String OffereeId;

    /**
     * 类别 MDM ERP 默认MDM
     */
    private Integer Category;

    /**
     * 相对人编码
     */
    private String OffereeCode;

    /**
     * 相对人名称
     */
    private String OffereeName;

    /**
     * 相对人归属 0 MDM() 1 ERP
     */
    private String OffereeBelong;

    /**
     * 相对人分类 0供应商 1客户
     */
    private String OffereeSort;

    /**
     * 相对人分类名称
     */
    private String OffereeSortName;

    /**
     * 机构类型 默认企业
     */
    private String CompanyType;

    /**
     * 法定代表人
     */
    private String Corporation;

    /**
     * 组织机构代码
     */
    private String OrgCode;

    /**
     * 税务登记号
     */
    private String TaxRegisterCode;

    /**
     * 注册号
     */
    private String BusinessLicenseCode;

    /**
     * 准入证号
     */
    private String MarketAccessCode;

    /**
     * 证件编号
     */
    private String LawQualCode;

    /**
     * 办公地址
     */
    private String OfficeAddress;

    /**
     * 注册地址
     */
    private String RegisterAddress;

    /**
     * 联系人
     */
    private String LinkManName;

    /**
     * 联系人职位
     */
    private String LinkManPosition;

    /**
     * 联系人电话
     */
    private String LinkManPhone;

    /**
     * 邮箱
     */
    private String Email;

    /**
     * 传真
     */
    private String Fax;

    /**
     * 开户银行代码
     */
    private String BankName;

    /**
     * 银行编号
     */
    private String BankCode;

    /**
     * 银行开户名称
     */
    private String OpenUints;

    /**
     * 银行账号
     */
    private String BankAcount;

    /**
     * 机构类型 0机构 1自然人
     */
    private Integer OffereeType;

    /**
     * 身份证
     */
    private String IDCard;

    /**
     * 是否有效 1是 0否
     */
    private String IsValid;

    /**
     * 创建日期
     */
    private LocalDateTime CreatedDate;

    /**
     * 创建人
     */
    private String CreatedBy;

    /**
     * 修改日期
     */
    private LocalDateTime ModifiedDate;

    /**
     * 修改人
     */
    private String ModifiedBy;

    /**
     * 来源类型 INSYS_INUNIT 内部单位   INSYS_OUTUNIT 外部单位
     */
    private String SourceType;

    public String getOffereeId() {
        return OffereeId;
    }

    public void setOffereeId(String offereeId) {
        OffereeId = offereeId;
    }

    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer category) {
        Category = category;
    }

    public String getOffereeCode() {
        return OffereeCode;
    }

    public void setOffereeCode(String offereeCode) {
        OffereeCode = offereeCode;
    }

    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String offereeName) {
        OffereeName = offereeName;
    }

    public String getOffereeBelong() {
        return OffereeBelong;
    }

    public void setOffereeBelong(String offereeBelong) {
        OffereeBelong = offereeBelong;
    }

    public String getOffereeSort() {
        return OffereeSort;
    }

    public void setOffereeSort(String offereeSort) {
        OffereeSort = offereeSort;
    }

    public String getOffereeSortName() {
        return OffereeSortName;
    }

    public void setOffereeSortName(String offereeSortName) {
        OffereeSortName = offereeSortName;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String companyType) {
        CompanyType = companyType;
    }

    public String getCorporation() {
        return Corporation;
    }

    public void setCorporation(String corporation) {
        Corporation = corporation;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    public String getTaxRegisterCode() {
        return TaxRegisterCode;
    }

    public void setTaxRegisterCode(String taxRegisterCode) {
        TaxRegisterCode = taxRegisterCode;
    }

    public String getBusinessLicenseCode() {
        return BusinessLicenseCode;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        BusinessLicenseCode = businessLicenseCode;
    }

    public String getMarketAccessCode() {
        return MarketAccessCode;
    }

    public void setMarketAccessCode(String marketAccessCode) {
        MarketAccessCode = marketAccessCode;
    }

    public String getLawQualCode() {
        return LawQualCode;
    }

    public void setLawQualCode(String lawQualCode) {
        LawQualCode = lawQualCode;
    }

    public String getOfficeAddress() {
        return OfficeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        OfficeAddress = officeAddress;
    }

    public String getRegisterAddress() {
        return RegisterAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        RegisterAddress = registerAddress;
    }

    public String getLinkManName() {
        return LinkManName;
    }

    public void setLinkManName(String linkManName) {
        LinkManName = linkManName;
    }

    public String getLinkManPosition() {
        return LinkManPosition;
    }

    public void setLinkManPosition(String linkManPosition) {
        LinkManPosition = linkManPosition;
    }

    public String getLinkManPhone() {
        return LinkManPhone;
    }

    public void setLinkManPhone(String linkManPhone) {
        LinkManPhone = linkManPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getOpenUints() {
        return OpenUints;
    }

    public void setOpenUints(String openUints) {
        OpenUints = openUints;
    }

    public String getBankAcount() {
        return BankAcount;
    }

    public void setBankAcount(String bankAcount) {
        BankAcount = bankAcount;
    }

    public Integer getOffereeType() {
        return OffereeType;
    }

    public void setOffereeType(Integer offereeType) {
        OffereeType = offereeType;
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

    public void setIsValid(String isValid) {
        IsValid = isValid;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }
}
