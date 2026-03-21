package com.pcitc.szgt.contract.make.entity;

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
 * @since 2020-02-20
 */
public class CrContractaccord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractID")
    private String ContractID;

    /**
     * 签约依据ID
     */
    @TableField("AccordingID")
    private String AccordingID;

    @TableField("Kind")
    private Integer Kind;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("RefContractID")
    private String RefContractID;

    @TableField("OrderID")
    private String OrderID;

    @TableField("LogicDel")
    private Integer LogicDel;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Status")
    private Integer Status;

    @TableField("OldOrderID")
    private String OldOrderID;

    @TableField("IsERPMassAccord")
    private Integer IsERPMassAccord;

    @TableField("IsUseTax")
    private Integer IsUseTax;

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getAccordingID() {
        return AccordingID;
    }

    public void setAccordingID(String AccordingID) {
        this.AccordingID = AccordingID;
    }
    public Integer getKind() {
        return Kind;
    }

    public void setKind(Integer Kind) {
        this.Kind = Kind;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public String getRefContractID() {
        return RefContractID;
    }

    public void setRefContractID(String RefContractID) {
        this.RefContractID = RefContractID;
    }
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
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
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getOldOrderID() {
        return OldOrderID;
    }

    public void setOldOrderID(String OldOrderID) {
        this.OldOrderID = OldOrderID;
    }
    public Integer getIsERPMassAccord() {
        return IsERPMassAccord;
    }

    public void setIsERPMassAccord(Integer IsERPMassAccord) {
        this.IsERPMassAccord = IsERPMassAccord;
    }
    public Integer getIsUseTax() {
        return IsUseTax;
    }

    public void setIsUseTax(Integer IsUseTax) {
        this.IsUseTax = IsUseTax;
    }

    @Override
    public String toString() {
        return "CrContractaccord{" +
        "ContractID=" + ContractID +
        ", AccordingID=" + AccordingID +
        ", Kind=" + Kind +
        ", Oulabel=" + Oulabel +
        ", RefContractID=" + RefContractID +
        ", OrderID=" + OrderID +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Status=" + Status +
        ", OldOrderID=" + OldOrderID +
        ", IsERPMassAccord=" + IsERPMassAccord +
        ", IsUseTax=" + IsUseTax +
        "}";
    }
}
