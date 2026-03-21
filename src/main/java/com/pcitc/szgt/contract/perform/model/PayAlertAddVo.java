package com.pcitc.szgt.contract.perform.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PayAlertAddVo {

    private Integer fId;

    /**
     * 合同id
     */
    @NotBlank
    private String fContractId;

    /**
     * 收付款类型编码
     */
    @NotBlank
    private String fPayType;

    /**
     * 收付款时间
     */
    @NotNull
    private LocalDate fDate;

    /**
     * 预警天数
     */
    @NotNull
    @Min(0)
    private Integer fDay;

    /**
     * 收付款金额
     */
    @NotNull
    private BigDecimal fMoney;

    /**
     * 描述
     */
    private String fDesc;

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
}
