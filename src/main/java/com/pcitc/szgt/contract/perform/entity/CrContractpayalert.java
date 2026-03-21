package com.pcitc.szgt.contract.perform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-11-18
 */
public class CrContractpayalert implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "f_id", type = IdType.AUTO)
    private Integer fId;

    /**
     * 合同id
     */
    private String fContractId;

    /**
     * 收付款类型编码
     */
    private String fPayType;

    /**
     * 收付款时间
     */
    private LocalDate fDate;

    /**
     * 实际截止时间
     */
    private LocalDate fActualDate;

    /**
     * 预警天数
     */
    private Integer fDay;

    /**
     * 收付款金额
     */
    private BigDecimal fMoney;

    /**
     * 描述
     */
    private String fDesc;

    /**
     * 创建人
     */
    private Integer fCreateUser;

    /**
     * 创建时间
     */
    private LocalDateTime fCreateTime;

    /**
     * 修改人
     */
    private Integer fModifyUser;

    /**
     * 修改时间
     */
    private LocalDateTime fModifyTime;

    /**
     * 0 正常  1 关闭
     */
    private Integer fState;

    /**
     * 0 未删除  null  删除
     */
    private Integer fLogicdel;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
    public String getfContractId() {
        return fContractId;
    }

    public void setfContractId(String fContractId) {
        this.fContractId = fContractId;
    }
    public String getfPayType() {
        return fPayType;
    }

    public void setfPayType(String fPayType) {
        this.fPayType = fPayType;
    }

    public LocalDate getfDate() {
        return fDate;
    }

    public void setfDate(LocalDate fDate) {
        this.fDate = fDate;
    }

    public LocalDate getfActualDate() {
        return fActualDate;
    }

    public void setfActualDate(LocalDate fActualDate) {
        this.fActualDate = fActualDate;
    }

    public Integer getfDay() {
        return fDay;
    }

    public void setfDay(Integer fDay) {
        this.fDay = fDay;
    }
    public BigDecimal getfMoney() {
        return fMoney;
    }

    public void setfMoney(BigDecimal fMoney) {
        this.fMoney = fMoney;
    }
    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }
    public Integer getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(Integer fCreateUser) {
        this.fCreateUser = fCreateUser;
    }

    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(LocalDateTime fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Integer getfModifyUser() {
        return fModifyUser;
    }

    public void setfModifyUser(Integer fModifyUser) {
        this.fModifyUser = fModifyUser;
    }

    public LocalDateTime getfModifyTime() {
        return fModifyTime;
    }

    public void setfModifyTime(LocalDateTime fModifyTime) {
        this.fModifyTime = fModifyTime;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }
    public Integer getfLogicdel() {
        return fLogicdel;
    }

    public void setfLogicdel(Integer fLogicdel) {
        this.fLogicdel = fLogicdel;
    }

    @Override
    public String toString() {
        return "CrContractpayalert{" +
        "fId=" + fId +
        ", fContractId=" + fContractId +
        ", fPayType=" + fPayType +
        ", fDate=" + fDate +
        ", fDay=" + fDay +
        ", fMoney=" + fMoney +
        ", fDesc=" + fDesc +
        ", fCreateUser=" + fCreateUser +
        ", fCreateTime=" + fCreateTime +
        ", fModifyUser=" + fModifyUser +
        ", fModifyTime=" + fModifyTime +
        ", fState=" + fState +
        ", fLogicdel=" + fLogicdel +
        "}";
    }
}
