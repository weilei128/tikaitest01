package com.pcitc.szgt.contract.offeree.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OffereeLicenseVo {

    private String businesslicenseid;

    private String offereeid;

    private String businesslicensecode;

    private String registeraddr;

    private String officeaddr;

    private String postcode;

    private String createdby;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createddate;

    private String modifiedby;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifieddate;

    private String registeredcapital;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date registerdate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reportdate;

    private Boolean isFix;

    public String getBusinesslicenseid() {
        return businesslicenseid;
    }

    public void setBusinesslicenseid(String businesslicenseid) {
        this.businesslicenseid = businesslicenseid == null ? null : businesslicenseid.trim();
    }

    public String getOffereeid() {
        return offereeid;
    }

    public void setOffereeid(String offereeid) {
        this.offereeid = offereeid == null ? null : offereeid.trim();
    }

    public String getBusinesslicensecode() {
        return businesslicensecode;
    }

    public void setBusinesslicensecode(String businesslicensecode) {
        this.businesslicensecode = businesslicensecode == null ? null : businesslicensecode.trim();
    }

    public String getRegisteraddr() {
        return registeraddr;
    }

    public void setRegisteraddr(String registeraddr) {
        this.registeraddr = registeraddr == null ? null : registeraddr.trim();
    }

    public String getOfficeaddr() {
        return officeaddr;
    }

    public void setOfficeaddr(String officeaddr) {
        this.officeaddr = officeaddr == null ? null : officeaddr.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby == null ? null : modifiedby.trim();
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getRegisteredcapital() {
        return registeredcapital;
    }

    public void setRegisteredcapital(String registeredcapital) {
        this.registeredcapital = registeredcapital == null ? null : registeredcapital.trim();
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public Boolean getFix() {
        return isFix;
    }

    public void setFix(Boolean fix) {
        isFix = fix;
    }
}
