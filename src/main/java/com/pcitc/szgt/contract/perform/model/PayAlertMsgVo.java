package com.pcitc.szgt.contract.perform.model;

import java.math.BigDecimal;

public class PayAlertMsgVo {
    /**
     * 合同id
     */
    private String fContractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 收付款金额
     */
    private BigDecimal fMoney;

    /**
     * 标的金额
     */
    private BigDecimal objectMoney;

    /**
     * 累计金额
     */
    private BigDecimal totalMoney;

    public String getfContractId() {
        return fContractId;
    }

    public void setfContractId(String fContractId) {
        this.fContractId = fContractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getfMoney() {
        return fMoney;
    }

    public void setfMoney(BigDecimal fMoney) {
        this.fMoney = fMoney;
    }

    public BigDecimal getObjectMoney() {
        return objectMoney;
    }

    public void setObjectMoney(BigDecimal objectMoney) {
        this.objectMoney = objectMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
