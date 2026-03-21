package com.pcitc.szgt.contract.interactive.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 财务凭证 model
 */
public class FinancialProof {
    //公司代码
    private String bukrs;

    //支付申请号
    private String zhtzfh;

    //会计凭证编号
    private String belnr;

    //会计年度
    private Integer gjahr;

    //合同编号
    private String zhtbh;

    //合同流水号
    private String zcmisod;

    //供应商编码
    private String lifnr;

    //凭证过账日期
    private LocalDateTime budat;

    //凭证过账金额
    private BigDecimal dmbtr;

    //被冲销凭证号
    private String stblg;

    //撤回凭证会计年度
    private Integer stjah;

    //冲销日期
    private LocalDateTime budat1;

    //冲销金额
    private BigDecimal dmbtr1;

    //币种
    private String waers;

    //单据编号
    private String zhtdjh;

    //结算方式
    private String zhtjsfs;

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

    public String getZhtbh() {
        return zhtbh;
    }

    public void setZhtbh(String zhtbh) {
        this.zhtbh = zhtbh;
    }

    public String getZcmisod() {
        return zcmisod;
    }

    public void setZcmisod(String zcmisod) {
        this.zcmisod = zcmisod;
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
        final StringBuilder sb = new StringBuilder("FinancialProof{");
        sb.append("bukrs='").append(bukrs).append('\'');
        sb.append(", zhtzfh='").append(zhtzfh).append('\'');
        sb.append(", belnr='").append(belnr).append('\'');
        sb.append(", gjahr=").append(gjahr);
        sb.append(", zhtbh='").append(zhtbh).append('\'');
        sb.append(", zcmisod='").append(zcmisod).append('\'');
        sb.append(", lifnr='").append(lifnr).append('\'');
        sb.append(", budat=").append(budat);
        sb.append(", dmbtr=").append(dmbtr);
        sb.append(", stblg='").append(stblg).append('\'');
        sb.append(", stjah=").append(stjah);
        sb.append(", budat1=").append(budat1);
        sb.append(", dmbtr1=").append(dmbtr1);
        sb.append(", waers='").append(waers).append('\'');
        sb.append(", zhtdjh='").append(zhtdjh).append('\'');
        sb.append(", zhtjsfs='").append(zhtjsfs).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
