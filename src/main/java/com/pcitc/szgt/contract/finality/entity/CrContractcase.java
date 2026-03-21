package com.pcitc.szgt.contract.finality.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-03-04
 */
public class CrContractcase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CaseID")
    private String CaseID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("TotalAmount")
    private BigDecimal TotalAmount;

    @TableField("Ramark")
    private String Ramark;

    @TableField("CaseDate")
    private LocalDate CaseDate;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("LogicDel")
    private Integer LogicDel;

    @TableField("ProcessResult")
    private String ProcessResult;

    public String getCaseID() {
        return CaseID;
    }

    public void setCaseID(String CaseID) {
        this.CaseID = CaseID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal TotalAmount) {
        this.TotalAmount = TotalAmount;
    }
    public String getRamark() {
        return Ramark;
    }

    public void setRamark(String Ramark) {
        this.Ramark = Ramark;
    }
    public LocalDate getCaseDate() {
        return CaseDate;
    }

    public void setCaseDate(LocalDate CaseDate) {
        this.CaseDate = CaseDate;
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
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public String getProcessResult() {
        return ProcessResult;
    }

    public void setProcessResult(String ProcessResult) {
        this.ProcessResult = ProcessResult;
    }

    @Override
    public String toString() {
        return "CrContractcase{" +
        "CaseID=" + CaseID +
        ", ContractID=" + ContractID +
        ", TotalAmount=" + TotalAmount +
        ", Ramark=" + Ramark +
        ", CaseDate=" + CaseDate +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", LogicDel=" + LogicDel +
        ", ProcessResult=" + ProcessResult +
        "}";
    }
}
