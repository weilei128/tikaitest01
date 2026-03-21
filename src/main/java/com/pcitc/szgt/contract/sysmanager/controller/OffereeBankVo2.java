package com.pcitc.szgt.contract.sysmanager.controller;

import java.util.Date;

public class OffereeBankVo2 {

    private String BankID;

    private String OffereeID;

    private String BankUK;

    private String BankCode;

    private String BankName;

    private String BankAcount;

    private String CreatedBy;

    private Date CreatedDate;

    private String ModifiedBy;

    private Date ModifiedDate;

    public String getBankID() {
        return BankID;
    }

    public void setBankID(String bankID) {
        BankID = bankID;
    }

    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String offereeID) {
        OffereeID = offereeID;
    }

    public String getBankUK() {
        return BankUK;
    }

    public void setBankUK(String bankUK) {
        BankUK = bankUK;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankAcount() {
        return BankAcount;
    }

    public void setBankAcount(String bankAcount) {
        BankAcount = bankAcount;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OffereeBankVo2{");
        sb.append("BankID='").append(BankID).append('\'');
        sb.append(", OffereeID='").append(OffereeID).append('\'');
        sb.append(", BankUK='").append(BankUK).append('\'');
        sb.append(", BankCode='").append(BankCode).append('\'');
        sb.append(", BankName='").append(BankName).append('\'');
        sb.append(", BankAcount='").append(BankAcount).append('\'');
        sb.append(", CreatedBy='").append(CreatedBy).append('\'');
        sb.append(", CreatedDate=").append(CreatedDate);
        sb.append(", ModifiedBy='").append(ModifiedBy).append('\'');
        sb.append(", ModifiedDate=").append(ModifiedDate);
        sb.append('}');
        return sb.toString();
    }
}
