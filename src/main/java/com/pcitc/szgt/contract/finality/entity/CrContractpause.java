package com.pcitc.szgt.contract.finality.entity;

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
 * @since 2020-03-04
 */
public class CrContractpause implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractPauseID")
    private String ContractPauseID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("PauseRemark")
    private String PauseRemark;

    @TableField("PauseStart")
    private LocalDateTime PauseStart;

    @TableField("PauseEnd")
    private LocalDateTime PauseEnd;

    @TableField("PauseReason")
    private Integer PauseReason;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("Kind")
    private Integer Kind;

    public String getContractPauseID() {
        return ContractPauseID;
    }

    public void setContractPauseID(String ContractPauseID) {
        this.ContractPauseID = ContractPauseID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getPauseRemark() {
        return PauseRemark;
    }

    public void setPauseRemark(String PauseRemark) {
        this.PauseRemark = PauseRemark;
    }
    public LocalDateTime getPauseStart() {
        return PauseStart;
    }

    public void setPauseStart(LocalDateTime PauseStart) {
        this.PauseStart = PauseStart;
    }
    public LocalDateTime getPauseEnd() {
        return PauseEnd;
    }

    public void setPauseEnd(LocalDateTime PauseEnd) {
        this.PauseEnd = PauseEnd;
    }
    public Integer getPauseReason() {
        return PauseReason;
    }

    public void setPauseReason(Integer PauseReason) {
        this.PauseReason = PauseReason;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public Integer getKind() {
        return Kind;
    }

    public void setKind(Integer Kind) {
        this.Kind = Kind;
    }

    @Override
    public String toString() {
        return "CrContractpause{" +
        "ContractPauseID=" + ContractPauseID +
        ", ContractID=" + ContractID +
        ", PauseRemark=" + PauseRemark +
        ", PauseStart=" + PauseStart +
        ", PauseEnd=" + PauseEnd +
        ", PauseReason=" + PauseReason +
        ", CreatedBy=" + CreatedBy +
        ", ModifiedBy=" + ModifiedBy +
        ", Oulabel=" + Oulabel +
        ", Kind=" + Kind +
        "}";
    }
}
