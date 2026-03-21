package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

public class FwIntermediaryBankInfoBo {
    /**
     * 中介机构银行信息主键
     */
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 开户银行名称
     */
    private String fDepositBank;
    /**
     * 银行账号
     */
    private String fBankAccount;
    /**
     * 开户名称
     */
    private String fAccountName;
    /**
     * 银行编号
     */
    private String fBankCode;

    private String fCreateuser;

    private String fCreatename;

    private Date fCreatetime;

    private String fUpdateuser;

    private String fUpdatename;

    private Date fUpdatetime;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public String getfDepositBank() {
        return fDepositBank;
    }

    public void setfDepositBank(String fDepositBank) {
        this.fDepositBank = fDepositBank;
    }

    public String getfBankAccount() {
        return fBankAccount;
    }

    public void setfBankAccount(String fBankAccount) {
        this.fBankAccount = fBankAccount;
    }

    public String getfAccountName() {
        return fAccountName;
    }

    public void setfAccountName(String fAccountName) {
        this.fAccountName = fAccountName;
    }

    public String getfBankCode() {
        return fBankCode;
    }

    public void setfBankCode(String fBankCode) {
        this.fBankCode = fBankCode;
    }

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }
}
