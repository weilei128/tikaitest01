package com.pcitc.szgt.contract.perform.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayAlertListVo {

    private Integer fId;

    /**
     * 合同id
     */
    private String fContractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同阶段
     */
    private Integer propertyModel;

    /**
     * 合同序号
     */
    private String ruleSerialNum;

    /**
     * 收付款类型编码
     */
    private String fPayType;

    /**
     * 收付款类型名称
     */
    private String payTypeName;

    /**
     * 收付款时间
     */
    private LocalDate fDate;

    /**
     * 预警天数
     */
    private Integer fDay;

    /**
     * 收付款金额
     */
    private BigDecimal fMoney;

    /**
     * 描述
     */
    private String fDesc;

    /**
     * 0 正常  1 关闭
     */
    private Integer fState;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

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

    public String getRuleSerialNum() {
        return ruleSerialNum;
    }

    public void setRuleSerialNum(String ruleSerialNum) {
        this.ruleSerialNum = ruleSerialNum;
    }

    public String getfPayType() {
        return fPayType;
    }

    public void setfPayType(String fPayType) {
        this.fPayType = fPayType;
    }

    public LocalDate getfDate() {
        return fDate;
    }

    public void setfDate(LocalDate fDate) {
        this.fDate = fDate;
    }

    public Integer getfDay() {
        return fDay;
    }

    public void setfDay(Integer fDay) {
        this.fDay = fDay;
    }

    public BigDecimal getfMoney() {
        return fMoney;
    }

    public void setfMoney(BigDecimal fMoney) {
        this.fMoney = fMoney;
    }

    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public Integer getPropertyModel() {
        return propertyModel;
    }

    public void setPropertyModel(Integer propertyModel) {
        this.propertyModel = propertyModel;
    }
}
