package com.pcitc.szgt.contract.perform.model;


import cn.afterturn.easypoi.excel.annotation.Excel;


public class ExportContractVo {
    // 线索序号
    @Excel(name = "合同序号", orderNum = "0" ,width=12)
    public String ruleserialNum;//合同序号

    @Excel(name = "合同编号", orderNum = "1",width=25)
    public String contractNum;//合同编号

    @Excel(name = "合同名称", orderNum = "2" ,width=30)
    public String contractName;//合同名称

    @Excel(name = "合同类别", orderNum = "3",width=30)
    public String typeName;//合同类型

    @Excel(name = "所属项目", orderNum = "4")
    public String projectName;//所属项目

    @Excel(name = "相对人姓名", orderNum = "5",width=30)
    public String offereeName;//相对人姓名

    @Excel(name = "主办单位", orderNum = "6",width=30)
    public String mainDeptName;//主办单位

    @Excel(name = "计划金额", orderNum = "7")
    public String planMoney;//计划金额

    @Excel(name = "含税金额", orderNum = "8")
    public String contractObjectAmount;//含税金额

    @Excel(name = "不含税金额", orderNum = "9")
    public String contractNoTaxAmount;//不含税金额

    @Excel(name = "税额", orderNum = "10")
    public String contractTaxAmount;//税额

    @Excel(name = "经办人", orderNum = "11")
    public String mainOrgUserName;//经办人

    @Excel(name = "资金流向", orderNum = "12")
    public String moneyFlowName;//资金流向

    @Excel(name = "资金来源", orderNum = "13")
    public String moneySource;//资金来源  改

    @Excel(name = "内部合同", orderNum = "14")
    public String isInnerContract;//是否内部合同  改

    @Excel(name = "我方签约代表", orderNum = "15")
    public String mySignPersonName;//我方签约代表姓名

    @Excel(name = "创建日期", orderNum = "16",width=15)
    public String createdDate;//合同创建日期

    @Excel(name = "履行完成时间", orderNum = "17")
    public String finalityDate;//履行完成时间

    @Excel(name = "签订时间", orderNum = "18")
    public String mySignDate;//签订时间

    @Excel(name = "签署备案时间", orderNum = "19")
    public String mySealDate;//签署备案时间

    @Excel(name = "合同变更次数", orderNum = "20")
    public String changeCount;//合同变更次数

    @Excel(name = "是否中止", orderNum = "21")
    public String isTerminate;//是否中止

    @Excel(name = "是否转让", orderNum = "22")
    public String isTransfer;//是否转让

    @Excel(name = "合同阶段", orderNum = "23")
    public String propertyModel;//合同阶段

    @Excel(name = "合同环节", orderNum = "24",width=15)
    public String section;//合同环节

    @Excel(name = "是否终结", orderNum = "25")
    public String isFinality;//合同是否终结

    public String getRuleserialNum() {
        return ruleserialNum;
    }

    public void setRuleserialNum(String ruleserialNum) {
        this.ruleserialNum = ruleserialNum;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOffereeName() {
        return offereeName;
    }

    public void setOffereeName(String offereeName) {
        this.offereeName = offereeName;
    }

    public String getMainDeptName() {
        return mainDeptName;
    }

    public void setMainDeptName(String mainDeptName) {
        this.mainDeptName = mainDeptName;
    }

    public String getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(String planMoney) {
        this.planMoney = planMoney;
    }

    public String getContractObjectAmount() {
        return contractObjectAmount;
    }

    public void setContractObjectAmount(String contractObjectAmount) {
        this.contractObjectAmount = contractObjectAmount;
    }

    public String getContractNoTaxAmount() {
        return contractNoTaxAmount;
    }

    public void setContractNoTaxAmount(String contractNoTaxAmount) {
        this.contractNoTaxAmount = contractNoTaxAmount;
    }

    public String getContractTaxAmount() {
        return contractTaxAmount;
    }

    public void setContractTaxAmount(String contractTaxAmount) {
        this.contractTaxAmount = contractTaxAmount;
    }

    public String getMainOrgUserName() {
        return mainOrgUserName;
    }

    public void setMainOrgUserName(String mainOrgUserName) {
        this.mainOrgUserName = mainOrgUserName;
    }

    public String getMoneyFlowName() {
        return moneyFlowName;
    }

    public void setMoneyFlowName(String moneyFlowName) {
        this.moneyFlowName = moneyFlowName;
    }

    public String getMoneySource() {
        return moneySource;
    }

    public void setMoneySource(String moneySource) {
        this.moneySource = moneySource;
    }

    public String getIsInnerContract() {
        return isInnerContract;
    }

    public void setIsInnerContract(String isInnerContract) {
        this.isInnerContract = isInnerContract;
    }

    public String getMySignPersonName() {
        return mySignPersonName;
    }

    public void setMySignPersonName(String mySignPersonName) {
        this.mySignPersonName = mySignPersonName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFinalityDate() {
        return finalityDate;
    }

    public void setFinalityDate(String finalityDate) {
        this.finalityDate = finalityDate;
    }

    public String getMySignDate() {
        return mySignDate;
    }

    public void setMySignDate(String mySignDate) {
        this.mySignDate = mySignDate;
    }

    public String getMySealDate() {
        return mySealDate;
    }

    public void setMySealDate(String mySealDate) {
        this.mySealDate = mySealDate;
    }

    public String getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(String changeCount) {
        this.changeCount = changeCount;
    }

    public String getIsTerminate() {
        return isTerminate;
    }

    public void setIsTerminate(String isTerminate) {
        this.isTerminate = isTerminate;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getPropertyModel() {
        return propertyModel;
    }

    public void setPropertyModel(String propertyModel) {
        this.propertyModel = propertyModel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getIsFinality() {
        return isFinality;
    }

    public void setIsFinality(String isFinality) {
        this.isFinality = isFinality;
    }
}
