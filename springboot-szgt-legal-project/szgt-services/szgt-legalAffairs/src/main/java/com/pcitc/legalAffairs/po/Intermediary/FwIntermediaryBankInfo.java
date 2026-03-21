package com.pcitc.legalAffairs.po.Intermediary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

/**
 * 中介机构银行信息
 */
@TableName("fw_intermediary_bank_info")
public class FwIntermediaryBankInfo extends BasePojo {
    /**
     * 中介机构银行信息主键
     */
    @TableId(value = "f_ID",type = IdType.AUTO)
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
}
