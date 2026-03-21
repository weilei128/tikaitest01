package com.pcitc.szgt.contract.interactive.model;

import java.math.BigDecimal;

public class ContractInfo {
    private String contractId;
    //编号
    private String contractCode;
    //名称
    private String contractName;
    //金额
    private BigDecimal contractAmount;
    //币种编号
    private String contractCurreC;
    //币种名称
    private String contractCurreN;
    //合同类型
    private String contractType;
    //合同类型名称
    private String contractTypeN;
    //公司编号
    private String companyCode;
    //公司名称
    private String companyName;
    //部门编号
    private String deaprCode;
    //部门名称
    private String deparName;
    //经办人编号
    private String handlUserCode;
    //经办人名称
    private String handlUserName;
    //订单编号
    private String orderCode;
    //订单类型
    private String orderType;
    //合同开始日期 yyyy-mm-dd hh24:mi:ss
    private String contrBeginDate;
    //合同结束日期 yyyy-mm-dd hh24:mi:ss
    private String contrEndDate;
    //合同签约日期 yyyy-mm-dd hh24:mi:ss
    private String contractDate;
    //合同主体
    private String contractParty;
    //是否框架合同
    private String contrIsFrame;
    //合同文本地址
    private String contractUrl;
    //合同相对人编号1
    private String contrUserCode1;
    //合同相对人编号2
    private String contrUserCode2;
    //合同相对人编号3
    private String contrUserCode3;
    //合同相对人编号4
    private String contrUserCode4;
    //合同相对人编号5
    private String contrUserCode5;
    //合同相对人名称1
    private String contrUserName1;
    //合同相对人名称2
    private String contrUserName2;
    //合同相对人名称3
    private String contrUserName3;
    //合同相对人名称4
    private String contrUserName4;
    //合同相对人名称5
    private String contrUserName5;
    //预留文本1
    private String otherChar1;
    //预留文本2
    private String otherChar2;
    //预留文本3
    private String otherChar3;
    //预留文本4
    private String otherChar4;
    //预留文本5
    private String otherChar5;
    //预留金额1
    private BigDecimal otherNum1;
    //预留金额2
    private BigDecimal otherNum2;
    //预留金额3
    private BigDecimal otherNum3;
    //预留金额4
    private BigDecimal otherNum4;
    //预留金额5
    private BigDecimal otherNum5;
    //合同状态代码
    private String contractState;
    //合同状态名称
    private String contractStateN;

    public ContractInfo(){
        contractAmount = new BigDecimal(0);
        otherNum1 = new BigDecimal(0);
        otherNum2 = new BigDecimal(0);
        otherNum3 = new BigDecimal(0);
        otherNum4 = new BigDecimal(0);
        otherNum5 = new BigDecimal(0);
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getContractCurreC() {
        return contractCurreC;
    }

    public void setContractCurreC(String contractCurreC) {
        this.contractCurreC = contractCurreC;
    }

    public String getContractCurreN() {
        return contractCurreN;
    }

    public void setContractCurreN(String contractCurreN) {
        this.contractCurreN = contractCurreN;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeaprCode() {
        return deaprCode;
    }

    public void setDeaprCode(String deaprCode) {
        this.deaprCode = deaprCode;
    }

    public String getDeparName() {
        return deparName;
    }

    public void setDeparName(String deparName) {
        this.deparName = deparName;
    }

    public String getHandlUserCode() {
        return handlUserCode;
    }

    public void setHandlUserCode(String handlUserCode) {
        this.handlUserCode = handlUserCode;
    }

    public String getHandlUserName() {
        return handlUserName;
    }

    public void setHandlUserName(String handlUserName) {
        this.handlUserName = handlUserName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getContrBeginDate() {
        return contrBeginDate;
    }

    public void setContrBeginDate(String contrBeginDate) {
        this.contrBeginDate = contrBeginDate;
    }

    public String getContrEndDate() {
        return contrEndDate;
    }

    public void setContrEndDate(String contrEndDate) {
        this.contrEndDate = contrEndDate;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractParty() {
        return contractParty;
    }

    public void setContractParty(String contractParty) {
        this.contractParty = contractParty;
    }

    public String getContrIsFrame() {
        return contrIsFrame;
    }

    public void setContrIsFrame(String contrIsFrame) {
        this.contrIsFrame = contrIsFrame;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getContrUserCode1() {
        return contrUserCode1;
    }

    public void setContrUserCode1(String contrUserCode1) {
        this.contrUserCode1 = contrUserCode1;
    }

    public String getContrUserCode2() {
        return contrUserCode2;
    }

    public void setContrUserCode2(String contrUserCode2) {
        this.contrUserCode2 = contrUserCode2;
    }

    public String getContrUserCode3() {
        return contrUserCode3;
    }

    public void setContrUserCode3(String contrUserCode3) {
        this.contrUserCode3 = contrUserCode3;
    }

    public String getContrUserCode4() {
        return contrUserCode4;
    }

    public void setContrUserCode4(String contrUserCode4) {
        this.contrUserCode4 = contrUserCode4;
    }

    public String getContrUserCode5() {
        return contrUserCode5;
    }

    public void setContrUserCode5(String contrUserCode5) {
        this.contrUserCode5 = contrUserCode5;
    }

    public String getContrUserName1() {
        return contrUserName1;
    }

    public void setContrUserName1(String contrUserName1) {
        this.contrUserName1 = contrUserName1;
    }

    public String getContrUserName2() {
        return contrUserName2;
    }

    public void setContrUserName2(String contrUserName2) {
        this.contrUserName2 = contrUserName2;
    }

    public String getContrUserName3() {
        return contrUserName3;
    }

    public void setContrUserName3(String contrUserName3) {
        this.contrUserName3 = contrUserName3;
    }

    public String getContrUserName4() {
        return contrUserName4;
    }

    public void setContrUserName4(String contrUserName4) {
        this.contrUserName4 = contrUserName4;
    }

    public String getContrUserName5() {
        return contrUserName5;
    }

    public void setContrUserName5(String contrUserName5) {
        this.contrUserName5 = contrUserName5;
    }

    public String getOtherChar1() {
        return otherChar1;
    }

    public void setOtherChar1(String otherChar1) {
        this.otherChar1 = otherChar1;
    }

    public String getOtherChar2() {
        return otherChar2;
    }

    public void setOtherChar2(String otherChar2) {
        this.otherChar2 = otherChar2;
    }

    public String getOtherChar3() {
        return otherChar3;
    }

    public void setOtherChar3(String otherChar3) {
        this.otherChar3 = otherChar3;
    }

    public String getOtherChar4() {
        return otherChar4;
    }

    public void setOtherChar4(String otherChar4) {
        this.otherChar4 = otherChar4;
    }

    public String getOtherChar5() {
        return otherChar5;
    }

    public void setOtherChar5(String otherChar5) {
        this.otherChar5 = otherChar5;
    }

    public BigDecimal getOtherNum1() {
        return otherNum1;
    }

    public void setOtherNum1(BigDecimal otherNum1) {
        this.otherNum1 = otherNum1;
    }

    public BigDecimal getOtherNum2() {
        return otherNum2;
    }

    public void setOtherNum2(BigDecimal otherNum2) {
        this.otherNum2 = otherNum2;
    }

    public BigDecimal getOtherNum3() {
        return otherNum3;
    }

    public void setOtherNum3(BigDecimal otherNum3) {
        this.otherNum3 = otherNum3;
    }

    public BigDecimal getOtherNum4() {
        return otherNum4;
    }

    public void setOtherNum4(BigDecimal otherNum4) {
        this.otherNum4 = otherNum4;
    }

    public BigDecimal getOtherNum5() {
        return otherNum5;
    }

    public void setOtherNum5(BigDecimal otherNum5) {
        this.otherNum5 = otherNum5;
    }

    public String getContractTypeN() {
        return contractTypeN;
    }

    public void setContractTypeN(String contractTypeN) {
        this.contractTypeN = contractTypeN;
    }

    public String getContractState() {
        return contractState;
    }

    public void setContractState(String contractState) {
        this.contractState = contractState;
    }

    public String getContractStateN() {
        return contractStateN;
    }

    public void setContractStateN(String contractStateN) {
        this.contractStateN = contractStateN;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContractInfo{");
        sb.append("contractId='").append(contractId).append('\'');
        sb.append(", contractCode='").append(contractCode).append('\'');
        sb.append(", contractName='").append(contractName).append('\'');
        sb.append(", contractAmount=").append(contractAmount);
        sb.append(", contractCurreC='").append(contractCurreC).append('\'');
        sb.append(", contractCurreN='").append(contractCurreN).append('\'');
        sb.append(", contractType='").append(contractType).append('\'');
        sb.append(", contractTypeN='").append(contractTypeN).append('\'');
        sb.append(", companyCode='").append(companyCode).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", deaprCode='").append(deaprCode).append('\'');
        sb.append(", deparName='").append(deparName).append('\'');
        sb.append(", handlUserCode='").append(handlUserCode).append('\'');
        sb.append(", handlUserName='").append(handlUserName).append('\'');
        sb.append(", orderCode='").append(orderCode).append('\'');
        sb.append(", orderType='").append(orderType).append('\'');
        sb.append(", contrBeginDate='").append(contrBeginDate).append('\'');
        sb.append(", contrEndDate='").append(contrEndDate).append('\'');
        sb.append(", contractDate='").append(contractDate).append('\'');
        sb.append(", contractParty='").append(contractParty).append('\'');
        sb.append(", contrIsFrame='").append(contrIsFrame).append('\'');
        sb.append(", contractUrl='").append(contractUrl).append('\'');
        sb.append(", contrUserCode1='").append(contrUserCode1).append('\'');
        sb.append(", contrUserCode2='").append(contrUserCode2).append('\'');
        sb.append(", contrUserCode3='").append(contrUserCode3).append('\'');
        sb.append(", contrUserCode4='").append(contrUserCode4).append('\'');
        sb.append(", contrUserCode5='").append(contrUserCode5).append('\'');
        sb.append(", contrUserName1='").append(contrUserName1).append('\'');
        sb.append(", contrUserName2='").append(contrUserName2).append('\'');
        sb.append(", contrUserName3='").append(contrUserName3).append('\'');
        sb.append(", contrUserName4='").append(contrUserName4).append('\'');
        sb.append(", contrUserName5='").append(contrUserName5).append('\'');
        sb.append(", otherChar1='").append(otherChar1).append('\'');
        sb.append(", otherChar2='").append(otherChar2).append('\'');
        sb.append(", otherChar3='").append(otherChar3).append('\'');
        sb.append(", otherChar4='").append(otherChar4).append('\'');
        sb.append(", otherChar5='").append(otherChar5).append('\'');
        sb.append(", otherNum1=").append(otherNum1);
        sb.append(", otherNum2=").append(otherNum2);
        sb.append(", otherNum3=").append(otherNum3);
        sb.append(", otherNum4=").append(otherNum4);
        sb.append(", otherNum5=").append(otherNum5);
        sb.append(", contractState='").append(contractState).append('\'');
        sb.append(", contractStateN='").append(contractStateN).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
