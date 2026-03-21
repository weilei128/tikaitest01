package com.pcitc.szgt.contract.backcontract.entity;

/**
 * @author 臧传军
 * @date 2021-01-20 17:25:47
 **/
public class ContractQuery {

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 合同序号
     */
    private String ruleSerialNum;

    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 经办部门ID
     */
    private Integer mainDeptID;

    public Integer getMainDeptID() {
        return mainDeptID;
    }

    public void setMainDeptID(Integer mainDeptID) {
        this.mainDeptID = mainDeptID;
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

    public String getRuleSerialNum() {
        return ruleSerialNum;
    }

    public void setRuleSerialNum(String ruleSerialNum) {
        this.ruleSerialNum = ruleSerialNum;
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
}
