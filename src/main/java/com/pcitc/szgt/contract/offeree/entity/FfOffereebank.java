package com.pcitc.szgt.contract.offeree.entity;

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
 * @since 2020-02-18
 */
public class FfOffereebank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行信息主键,GUID
     */
    @TableId("BankID")
    private String BankID;

    /**
     * 相对人ID
     */
    @TableField("OffereeID")
    private String OffereeID;

    /**
     * 开户银行
     */
    @TableField("BankUK")
    private String BankUK;

    /**
     * 银行编号
     */
    @TableField("BankCode")
    private String BankCode;

    /**
     * 开户名称
     */
    @TableField("BankName")
    private String BankName;

    /**
     * 银行账号
     */
    @TableField("BankAcount")
    private String BankAcount;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 修改时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 企业标识
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 是否删除 0否 1 是
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 排序字段
     */
    @TableField("OrderNumber")
    private Integer OrderNumber;

    /**
     * 预留字段1
     */
    @TableField("Remark01")
    private String Remark01;

    /**
     * 预留字段2
     */
    @TableField("Remark02")
    private String Remark02;

    /**
     * 预留字段3
     */
    @TableField("Remark03")
    private String Remark03;

    /**
     * 预留字段4
     */
    @TableField("Remark04")
    private String Remark04;

    /**
     * 预留字段5
     */
    @TableField("Remark05")
    private String Remark05;

    public String getBankID() {
        return BankID;
    }

    public void setBankID(String BankID) {
        this.BankID = BankID;
    }
    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String OffereeID) {
        this.OffereeID = OffereeID;
    }
    public String getBankUK() {
        return BankUK;
    }

    public void setBankUK(String BankUK) {
        this.BankUK = BankUK;
    }
    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }
    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }
    public String getBankAcount() {
        return BankAcount;
    }

    public void setBankAcount(String BankAcount) {
        this.BankAcount = BankAcount;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer OrderNumber) {
        this.OrderNumber = OrderNumber;
    }
    public String getRemark01() {
        return Remark01;
    }

    public void setRemark01(String Remark01) {
        this.Remark01 = Remark01;
    }
    public String getRemark02() {
        return Remark02;
    }

    public void setRemark02(String Remark02) {
        this.Remark02 = Remark02;
    }
    public String getRemark03() {
        return Remark03;
    }

    public void setRemark03(String Remark03) {
        this.Remark03 = Remark03;
    }
    public String getRemark04() {
        return Remark04;
    }

    public void setRemark04(String Remark04) {
        this.Remark04 = Remark04;
    }
    public String getRemark05() {
        return Remark05;
    }

    public void setRemark05(String Remark05) {
        this.Remark05 = Remark05;
    }

    @Override
    public String toString() {
        return "FfOffereebank{" +
        "BankID=" + BankID +
        ", OffereeID=" + OffereeID +
        ", BankUK=" + BankUK +
        ", BankCode=" + BankCode +
        ", BankName=" + BankName +
        ", BankAcount=" + BankAcount +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", LogicDel=" + LogicDel +
        ", OrderNumber=" + OrderNumber +
        ", Remark01=" + Remark01 +
        ", Remark02=" + Remark02 +
        ", Remark03=" + Remark03 +
        ", Remark04=" + Remark04 +
        ", Remark05=" + Remark05 +
        "}";
    }
}
