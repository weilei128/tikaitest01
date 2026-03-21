package com.pcitc.szgt.contract.offeree.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OffereeInfoVo {

    private String OffereeId;

    private Integer OffereeType;

    private String OffereeCode;

    private String OffereeName;

    private Integer OffereeeBelong;

    private Integer CompanyType;

    private String CompanyTypeName;

    private String Corporation;

    private Integer DataSource;

    private String SourceType;

    private String OffereeSort;

    private String CreditCode;

    private String NaturePerson;

    private String IDCard;

    private String Phone;

    private String Address;

    private Integer IsEnable;

    private String CreatedBy;

    private String CreatedByName;

    private LocalDateTime CreatedDate;

    private String ModifiedBy;

    private LocalDateTime ModifiedDate;

    private Integer Status;
    private Integer EndCount;
    private Integer CaseCount;
    private List<OffereeContractVo> Ends;
    private List<OffereeContractVo> Cases;

    public String getOffereeId() {
        return OffereeId;
    }

    public void setOffereeId(String offereeId) {
        OffereeId = offereeId;
    }

    public Integer getOffereeType() {
        return OffereeType;
    }

    public void setOffereeType(Integer offereeType) {
        OffereeType = offereeType;
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

    public Integer getOffereeeBelong() {
        return OffereeeBelong;
    }

    public void setOffereeeBelong(Integer offereeeBelong) {
        OffereeeBelong = offereeeBelong;
    }

    public Integer getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(Integer companyType) {
        CompanyType = companyType;
    }

    public String getCorporation() {
        return Corporation;
    }

    public void setCorporation(String corporation) {
        Corporation = corporation;
    }

    public String getOffereeSort() {
        return OffereeSort;
    }

    public void setOffereeSort(String offereeSort) {
        OffereeSort = offereeSort;
    }

    public String getCreditCode() {
        return CreditCode;
    }

    public void setCreditCode(String creditCode) {
        CreditCode = creditCode;
    }

    public String getNaturePerson() {
        return NaturePerson;
    }

    public void setNaturePerson(String naturePerson) {
        NaturePerson = naturePerson;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(Integer isEnable) {
        IsEnable = isEnable;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public Integer getDataSource() {
        return DataSource;
    }

    public void setDataSource(Integer dataSource) {
        DataSource = dataSource;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public void setEndCount(Integer endCount) {
        EndCount = endCount;
    }

    public void setCaseCount(Integer caseCount) {
        CaseCount = caseCount;
    }

    public Integer getEndCount() {
        return EndCount;
    }

    public Integer getCaseCount() {
        return CaseCount;
    }

    public List<OffereeContractVo> getEnds() {
        return Ends;
    }

    public List<OffereeContractVo> getCases() {
        return Cases;
    }

    public void setEnds(List<OffereeContractVo> ends) {
        Ends = ends;
    }

    public void setCases(List<OffereeContractVo> cases) {
        Cases = cases;
    }

    public String getCreatedByName() {
        return CreatedByName;
    }

    public void setCreatedByName(String createdByName) {
        CreatedByName = createdByName;
    }

    public String getCompanyTypeName() {
        return CompanyTypeName;
    }

    public void setCompanyTypeName(String companyTypeName) {
        CompanyTypeName = companyTypeName;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }
}
