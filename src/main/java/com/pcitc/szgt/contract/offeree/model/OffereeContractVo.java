package com.pcitc.szgt.contract.offeree.model;

/*
 * 异常情况合同
 * */
public class OffereeContractVo {

    public String contractId;
    public String contractName;
    public String ruleserialNum;
    public String createDate;
    public String createUserId;
    public String createUserName;
    public String createOrgId;
    public String createOrgName;
    public String offereePerformRemark;//相对人异常履约情况说明

    public String getContractId() {
        return contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public String getRuleserialNum() {
        return ruleserialNum;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setRuleserialNum(String ruleserialNum) {
        this.ruleserialNum = ruleserialNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId;
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }
    public String getOffereePerformRemark() {
        return offereePerformRemark;
    }

    public void setOffereePerformRemark(String offereePerformRemark) {
        this.offereePerformRemark = offereePerformRemark;
    }

}
