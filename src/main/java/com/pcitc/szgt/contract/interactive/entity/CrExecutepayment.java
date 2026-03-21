package com.pcitc.szgt.contract.interactive.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-06-16
 */
public class CrExecutepayment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 付款ID
     */
    @TableId("PayID")
    private String PayID;

    /**
     * 合同ID guid
     */
    @TableField("ContractID")
    private String ContractID;

    /**
     * 付款类型
     */
    @TableField("Ptype")
    private String Ptype;

    /**
     * 公司代码
     */
    @TableField("BUKRS")
    private String bukrs;

    /**
     * 支付申请号（判断唯一，不存在Insert 存在update）
     */
    @TableField("ZHTZFH")
    private String zhtzfh;

    /**
     * 会计凭证编号
     */
    @TableField("BELNR")
    private String belnr;

    /**
     * 会计年度
     */
    @TableField("GJAHR")
    private Integer gjahr;

    /**
     * 供应商编码
     */
    @TableField("LIFNR")
    private String lifnr;

    /**
     * 凭证过帐日期
     */
    @TableField("BUDAT")
    private LocalDateTime budat;

    /**
     * 凭证过账金额
     */
    @TableField("DMBTR")
    private BigDecimal dmbtr;

    /**
     * 被冲销凭证号
     */
    @TableField("STBLG")
    private String stblg;

    /**
     * 撤回凭证会计年度
     */
    @TableField("STJAH")
    private Integer stjah;

    /**
     * 冲销日期
     */
    @TableField("BUDAT1")
    private LocalDateTime budat1;

    @TableField("DMBTR1")
    private BigDecimal dmbtr1;

    /**
     * 币种(中文)
     */
    @TableField("WAERS")
    private String waers;

    /**
     * 创建日期
     */
    @TableField("CreateTime")
    private LocalDateTime CreateTime;

    /**
     * 修改日期
     */
    @TableField("ModifiedTime")
    private LocalDateTime ModifiedTime;

    /**
     * 单据编号
     */
    @TableField("ZHTDJH")
    private String zhtdjh;

    /**
     * 结算方式
     */
    @TableField("ZHTJSFS")
    private String zhtjsfs;

    public String getPayID() {
        return PayID;
    }

    public void setPayID(String PayID) {
        this.PayID = PayID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getPtype() {
        return Ptype;
    }

    public void setPtype(String Ptype) {
        this.Ptype = Ptype;
    }
    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }
    public String getZhtzfh() {
        return zhtzfh;
    }

    public void setZhtzfh(String zhtzfh) {
        this.zhtzfh = zhtzfh;
    }
    public String getBelnr() {
        return belnr;
    }

    public void setBelnr(String belnr) {
        this.belnr = belnr;
    }
    public Integer getGjahr() {
        return gjahr;
    }

    public void setGjahr(Integer gjahr) {
        this.gjahr = gjahr;
    }
    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }
    public LocalDateTime getBudat() {
        return budat;
    }

    public void setBudat(LocalDateTime budat) {
        this.budat = budat;
    }
    public BigDecimal getDmbtr() {
        return dmbtr;
    }

    public void setDmbtr(BigDecimal dmbtr) {
        this.dmbtr = dmbtr;
    }
    public String getStblg() {
        return stblg;
    }

    public void setStblg(String stblg) {
        this.stblg = stblg;
    }
    public Integer getStjah() {
        return stjah;
    }

    public void setStjah(Integer stjah) {
        this.stjah = stjah;
    }
    public LocalDateTime getBudat1() {
        return budat1;
    }

    public void setBudat1(LocalDateTime budat1) {
        this.budat1 = budat1;
    }
    public BigDecimal getDmbtr1() {
        return dmbtr1;
    }

    public void setDmbtr1(BigDecimal dmbtr1) {
        this.dmbtr1 = dmbtr1;
    }
    public String getWaers() {
        return waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }
    public LocalDateTime getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(LocalDateTime CreateTime) {
        this.CreateTime = CreateTime;
    }
    public LocalDateTime getModifiedTime() {
        return ModifiedTime;
    }

    public void setModifiedTime(LocalDateTime ModifiedTime) {
        this.ModifiedTime = ModifiedTime;
    }
    public String getZhtdjh() {
        return zhtdjh;
    }

    public void setZhtdjh(String zhtdjh) {
        this.zhtdjh = zhtdjh;
    }
    public String getZhtjsfs() {
        return zhtjsfs;
    }

    public void setZhtjsfs(String zhtjsfs) {
        this.zhtjsfs = zhtjsfs;
    }

    @Override
    public String toString() {
        return "CrExecutepayment{" +
        "PayID=" + PayID +
        ", ContractID=" + ContractID +
        ", Ptype=" + Ptype +
        ", bukrs=" + bukrs +
        ", zhtzfh=" + zhtzfh +
        ", belnr=" + belnr +
        ", gjahr=" + gjahr +
        ", lifnr=" + lifnr +
        ", budat=" + budat +
        ", dmbtr=" + dmbtr +
        ", stblg=" + stblg +
        ", stjah=" + stjah +
        ", budat1=" + budat1 +
        ", dmbtr1=" + dmbtr1 +
        ", waers=" + waers +
        ", CreateTime=" + CreateTime +
        ", ModifiedTime=" + ModifiedTime +
        ", zhtdjh=" + zhtdjh +
        ", zhtjsfs=" + zhtjsfs +
        "}";
    }
}
