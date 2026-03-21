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
 * @since 2020-02-27
 */
public class CrContractprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("PrintID")
    private String PrintID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("Printer")
    private String Printer;

    @TableField("PrintDateTime")
    private LocalDateTime PrintDateTime;

    @TableField("PrintCount")
    private Integer PrintCount;

    @TableField("TextGuid")
    private String TextGuid;

    @TableField("IsPrintComplete")
    private Integer IsPrintComplete;

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

    @TableField("Oulabel")
    private Integer Oulabel;

    public String getPrintID() {
        return PrintID;
    }

    public void setPrintID(String PrintID) {
        this.PrintID = PrintID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getPrinter() {
        return Printer;
    }

    public void setPrinter(String Printer) {
        this.Printer = Printer;
    }
    public LocalDateTime getPrintDateTime() {
        return PrintDateTime;
    }

    public void setPrintDateTime(LocalDateTime PrintDateTime) {
        this.PrintDateTime = PrintDateTime;
    }
    public Integer getPrintCount() {
        return PrintCount;
    }

    public void setPrintCount(Integer PrintCount) {
        this.PrintCount = PrintCount;
    }
    public String getTextGuid() {
        return TextGuid;
    }

    public void setTextGuid(String TextGuid) {
        this.TextGuid = TextGuid;
    }
    public Integer getIsPrintComplete() {
        return IsPrintComplete;
    }

    public void setIsPrintComplete(Integer IsPrintComplete) {
        this.IsPrintComplete = IsPrintComplete;
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
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    @Override
    public String toString() {
        return "CrContractprint{" +
        "PrintID=" + PrintID +
        ", ContractID=" + ContractID +
        ", Printer=" + Printer +
        ", PrintDateTime=" + PrintDateTime +
        ", PrintCount=" + PrintCount +
        ", TextGuid=" + TextGuid +
        ", IsPrintComplete=" + IsPrintComplete +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
