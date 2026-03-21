package com.pcitc.szgt.contract.common.model;

import java.time.LocalDate;
import java.util.List;

public class SimpleUserInfo {

    /**
     * 主键
     */
    private Integer fId;

    /**
     * 中文名
     */
    private String fCname;

    /**
     * 登录账户名称
     */
    private String fAccount;

    /**
     * 是否被锁定：0未锁定/1已锁定
     */
    private Integer fIsLock;

    /**
     * 性别：M男/W女
     */
    private String fSex;

    /**
     * 生日
     */
    private LocalDate fBirthday;

    /**
     * 年龄
     */
    private Integer fAge;

    /**
     * 手机号
     */
    private String fPhoneNum;

    /**
     * 邮箱
     */
    private String fEmail;

    /**
     * 用户描述
     */
    private String fDescription;

    /**
     * 启用状态：0启用/1未启用
     */
    private Integer fState;

    /**
     * 所在组织机构
     */
    private List<SimpleOrgInfo> orgs;

    /**
     * 所在单位id
     */
    private Integer unitId;

    /**
     * 所在单位名称
     */
    private String unitName;

    /**
     * 用户是否有审批权限
     * @return
     */
    private String fRole ;
    
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfCname() {
        return fCname;
    }

    public void setfCname(String fCname) {
        this.fCname = fCname;
    }

    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount;
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
        this.fSex = fSex;
    }

    public LocalDate getfBirthday() {
        return fBirthday;
    }

    public void setfBirthday(LocalDate fBirthday) {
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
        this.fPhoneNum = fPhoneNum;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public List<SimpleOrgInfo> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<SimpleOrgInfo> orgs) {
        this.orgs = orgs;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

	public String getfRole() {
		return fRole;
	}

	public void setfRole(String fRole) {
		this.fRole = fRole;
	}

    
}
