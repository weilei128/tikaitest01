package com.pcitc.system.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "sys_userinfo")
public class SysUserInfo {
    @TableId
    private Integer fId;

    private String fCode;

    private String fCname;

    private String fEname;

    private String fAccount;

    private String fPassword;

    private Integer fIsLock;

    private String fSex;

    private Date fBirthday;

    private Integer fAge;

    private String fPhoneNum;

    private String fLandLine;

    private String fEmail;

    private Date fJobTime;

    private String fIdCard;

    private String fMarital;

    private Integer fHeight;

    private String fHometown;

    private String fNation;

    private String fEducation;

    private String fGraduatedSchool;

    private String fProfession;

    private String fPoliticalstatus;

    private String fAddress;

    private String fDescription;

    private Integer fState;

    private Integer fSort;

    private Integer fType;

    private Integer fIsdel;

    private String fCreateUser;

    private String fCreateName;

    private Date fCreateTime;

    private String fUpdateUser;

    private String fUpdateName;

    private Date fUpdateTime;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getfCname() {
        return fCname;
    }

    public void setfCname(String fCname) {
        this.fCname = fCname == null ? null : fCname.trim();
    }

    public String getfEname() {
        return fEname;
    }

    public void setfEname(String fEname) {
        this.fEname = fEname == null ? null : fEname.trim();
    }

    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount == null ? null : fAccount.trim();
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword == null ? null : fPassword.trim();
    }

    public Integer getfIsLock() {
        return fIsLock;
    }

    public void setfIsLock(Integer fIsLock) {
        this.fIsLock = fIsLock;
    }

    public String getfSex() {
        return fSex;
    }

    public void setfSex(String fSex) {
        this.fSex = fSex == null ? null : fSex.trim();
    }

    public Date getfBirthday() {
        return fBirthday;
    }

    public void setfBirthday(Date fBirthday) {
        this.fBirthday = fBirthday;
    }

    public Integer getfAge() {
        return fAge;
    }

    public void setfAge(Integer fAge) {
        this.fAge = fAge;
    }

    public String getfPhoneNum() {
        return fPhoneNum;
    }

    public void setfPhoneNum(String fPhoneNum) {
        this.fPhoneNum = fPhoneNum == null ? null : fPhoneNum.trim();
    }

    public String getfLandLine() {
        return fLandLine;
    }

    public void setfLandLine(String fLandLine) {
        this.fLandLine = fLandLine == null ? null : fLandLine.trim();
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail == null ? null : fEmail.trim();
    }

    public Date getfJobTime() {
        return fJobTime;
    }

    public void setfJobTime(Date fJobTime) {
        this.fJobTime = fJobTime;
    }

    public String getfIdCard() {
        return fIdCard;
    }

    public void setfIdCard(String fIdCard) {
        this.fIdCard = fIdCard == null ? null : fIdCard.trim();
    }

    public String getfMarital() {
        return fMarital;
    }

    public void setfMarital(String fMarital) {
        this.fMarital = fMarital == null ? null : fMarital.trim();
    }

    public Integer getfHeight() {
        return fHeight;
    }

    public void setfHeight(Integer fHeight) {
        this.fHeight = fHeight;
    }

    public String getfHometown() {
        return fHometown;
    }

    public void setfHometown(String fHometown) {
        this.fHometown = fHometown == null ? null : fHometown.trim();
    }

    public String getfNation() {
        return fNation;
    }

    public void setfNation(String fNation) {
        this.fNation = fNation == null ? null : fNation.trim();
    }

    public String getfEducation() {
        return fEducation;
    }

    public void setfEducation(String fEducation) {
        this.fEducation = fEducation == null ? null : fEducation.trim();
    }

    public String getfGraduatedSchool() {
        return fGraduatedSchool;
    }

    public void setfGraduatedSchool(String fGraduatedSchool) {
        this.fGraduatedSchool = fGraduatedSchool == null ? null : fGraduatedSchool.trim();
    }

    public String getfProfession() {
        return fProfession;
    }

    public void setfProfession(String fProfession) {
        this.fProfession = fProfession == null ? null : fProfession.trim();
    }

    public String getfPoliticalstatus() {
        return fPoliticalstatus;
    }

    public void setfPoliticalstatus(String fPoliticalstatus) {
        this.fPoliticalstatus = fPoliticalstatus == null ? null : fPoliticalstatus.trim();
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress == null ? null : fAddress.trim();
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser == null ? null : fCreateUser.trim();
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName == null ? null : fCreateName.trim();
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser == null ? null : fUpdateUser.trim();
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName == null ? null : fUpdateName.trim();
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}