package com.pcitc.legalAffairs.vo.person;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author 法律人员信息表
 */
public class FwExcelPersonVo {


    /**
     * 姓名
     */
    @Excel(name= "姓名", orderNum= "0")
    private String fName;

    /**
     * 所属组织名称
     */
    @Excel(name= "组织机构", orderNum= "1")
    private String fkOrgName;

    /**
     * 所属组织层级
     */
    @Excel(name= "年龄", orderNum= "2")
    private String age;

    /**
     * 职务
     */
    @Excel(name= "职务", orderNum= "3")
    private String fPosition;

    /**
     * 性别 0-女 1-男
     */
    @Excel(name= "性别", orderNum= "4")
    private String fGender;

    /**
     * 是否专职法律工作人员
     */
    @Excel(name= "是否专职法律人员", orderNum= "5")
    private String fIsFullTimeLegal;

    /**
     * 是否总法律顾问 0-否 1-是
     */
    @Excel(name= "是否总法律顾问", orderNum= "6")
    private String fIsGeneralAdvisor;

    /**
     * 除法律外其他分管业务
     */
    @Excel(name= "专兼职法律顾问", orderNum= "7")
    private String fBussinessExceptLegal;

    /**
     * 是否分管领导 0-否 1-是
     */
    @Excel(name= "是否分管领导", orderNum= "8")
    private String fIsInChargeLeader;

    /**
     * 是否法律从业人员 0否 1是
     */
    @Excel(name= "是否法律从业人员", orderNum= "9")
    private String fIsLegalPractitioner;
    /**
     * 是否为副总法律顾问 0-否 1-是
     */
    @Excel(name= "是否总法律顾问", orderNum= "10")
    private String fIsSubGeneralAdvisor;

    @Excel(name= "是否机构负责人", orderNum= "11")
    private String fIsMainOfLegalAgency;

    /**
     * 类型 0-草稿 1-保存
     */
    private Integer fType;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
    }

    public String getfGender() {
        return fGender;
    }

    public void setfGender(String fGender) {
        this.fGender = fGender;
    }

    public String getfIsFullTimeLegal() {
        return fIsFullTimeLegal;
    }

    public void setfIsFullTimeLegal(String fIsFullTimeLegal) {
        this.fIsFullTimeLegal = fIsFullTimeLegal;
    }

    public String getfIsGeneralAdvisor() {
        return fIsGeneralAdvisor;
    }

    public void setfIsGeneralAdvisor(String fIsGeneralAdvisor) {
        this.fIsGeneralAdvisor = fIsGeneralAdvisor;
    }

    public String getfBussinessExceptLegal() {
        return fBussinessExceptLegal;
    }

    public void setfBussinessExceptLegal(String fBussinessExceptLegal) {
        this.fBussinessExceptLegal = fBussinessExceptLegal;
    }

    public String getfIsInChargeLeader() {
        return fIsInChargeLeader;
    }

    public void setfIsInChargeLeader(String fIsInChargeLeader) {
        this.fIsInChargeLeader = fIsInChargeLeader;
    }

    public String getfIsLegalPractitioner() {
        return fIsLegalPractitioner;
    }

    public void setfIsLegalPractitioner(String fIsLegalPractitioner) {
        this.fIsLegalPractitioner = fIsLegalPractitioner;
    }

    public String getfIsSubGeneralAdvisor() {
        return fIsSubGeneralAdvisor;
    }

    public void setfIsSubGeneralAdvisor(String fIsSubGeneralAdvisor) {
        this.fIsSubGeneralAdvisor = fIsSubGeneralAdvisor;
    }

    public String getfIsMainOfLegalAgency() {
        return fIsMainOfLegalAgency;
    }

    public void setfIsMainOfLegalAgency(String fIsMainOfLegalAgency) {
        this.fIsMainOfLegalAgency = fIsMainOfLegalAgency;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }
}