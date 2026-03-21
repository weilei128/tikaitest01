package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-28
 */
public class ZAmBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("BankID")
    private String BankID;

    @TableField("OrgConfigID")
    private String OrgConfigID;

    @TableField("BankName")
    private String BankName;

    @TableField("AccountName")
    private String AccountName;

    @TableField("BankNO")
    private String BankNO;

    @TableField("BankCode")
    private String BankCode;

    @TableField("CreateDate")
    private LocalDateTime CreateDate;

    @TableField("CreateBy")
    private String CreateBy;

    @TableField("ModifyDate")
    private LocalDateTime ModifyDate;

    @TableField("ModifyBy")
    private String ModifyBy;

    public String getBankID() {
        return BankID;
    }

    public void setBankID(String BankID) {
        this.BankID = BankID;
    }
    public String getOrgConfigID() {
        return OrgConfigID;
    }

    public void setOrgConfigID(String OrgConfigID) {
        this.OrgConfigID = OrgConfigID;
    }
    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }
    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String AccountName) {
        this.AccountName = AccountName;
    }
    public String getBankNO() {
        return BankNO;
    }

    public void setBankNO(String BankNO) {
        this.BankNO = BankNO;
    }
    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDateTime CreateDate) {
        this.CreateDate = CreateDate;
    }
    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }
    public LocalDateTime getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(LocalDateTime ModifyDate) {
        this.ModifyDate = ModifyDate;
    }
    public String getModifyBy() {
        return ModifyBy;
    }

    public void setModifyBy(String ModifyBy) {
        this.ModifyBy = ModifyBy;
    }

    @Override
    public String toString() {
        return "ZAmBank{" +
        "BankID=" + BankID +
        ", OrgConfigID=" + OrgConfigID +
        ", BankName=" + BankName +
        ", AccountName=" + AccountName +
        ", BankNO=" + BankNO +
        ", BankCode=" + BankCode +
        ", CreateDate=" + CreateDate +
        ", CreateBy=" + CreateBy +
        ", ModifyDate=" + ModifyDate +
        ", ModifyBy=" + ModifyBy +
        "}";
    }
}
