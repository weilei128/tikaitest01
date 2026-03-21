package com.pcitc.szgt.contract.agencyquery.entity;

import java.time.LocalDateTime;

/**
 * @author 臧传军
 * @date 2021-01-19 16:12:45
 **/
public class ContractQueryDTO{
    /**
     * 合同ID
     */
    private String contractId;
    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同对方经办人
     */
    private String InnerOperator;

    /**
     * 主办部门
     */
    private String mainDeptName;

    /**
     * 主办单位
     */
    private String mainOrgName;

    /**
     * 创建时间
     */
    private LocalDateTime createdDate;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getInnerOperator() {
        return InnerOperator;
    }

    public void setInnerOperator(String innerOperator) {
        InnerOperator = innerOperator;
    }

    public String getMainDeptName() {
        return mainDeptName;
    }

    public void setMainDeptName(String mainDeptName) {
        this.mainDeptName = mainDeptName;
    }

    public String getMainOrgName() {
        return mainOrgName;
    }

    public void setMainOrgName(String mainOrgName) {
        this.mainOrgName = mainOrgName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
