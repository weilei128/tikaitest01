package com.pcitc.szgt.contract.offeree.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OffereeLinkmanVo {

    private String LinkID;

    private String OffereeID;

    private String LinkManName;

    private String LinkManPosition;

    private String Phone;

    private String Fax;

    private String Email;

    private String CreatedBy;

    private Date CreatedDate;

    private String ModifiedBy;

    private Date ModifiedDate;

    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String linkID) {
        LinkID = linkID;
    }

    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String offereeID) {
        OffereeID = offereeID;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
}
