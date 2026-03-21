package com.pcitc.szgt.contract.perform.model;

import java.time.LocalDate;

public class PayAlertQueryVo {

    /**
     * 合同序号
     */
    private String ruleSerialNum;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 收付款类型
     */
    private String fPayType;

    /**
     * 收款开始时间
     */
    private String startDate;

    /**
     * 收款结束时间
     */
    private String endDate;

    private Integer pageNum;

    private Integer pageSize;

    public String getRuleSerialNum() {
        return ruleSerialNum;
    }

    public void setRuleSerialNum(String ruleSerialNum) {
        this.ruleSerialNum = ruleSerialNum;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getfPayType() {
        return fPayType;
    }

    public void setfPayType(String fPayType) {
        this.fPayType = fPayType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
