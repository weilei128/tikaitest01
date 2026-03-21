package com.pcitc.szgt.contract.perform.entity;

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
 * @since 2020-03-02
 */
public class CrContractchangeofferee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ChangeOffereeId")
    private String ChangeOffereeId;

    @TableField("ContractChangeID")
    private String ContractChangeID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("ContractChangeDetailID")
    private String ContractChangeDetailID;

    @TableField("OrginalOffereeId")
    private String OrginalOffereeId;

    @TableField("OrginalOffereeCode")
    private String OrginalOffereeCode;

    @TableField("OrginalOffereeName")
    private String OrginalOffereeName;

    @TableField("NewOffereeId")
    private String NewOffereeId;

    @TableField("NewOffereeCode")
    private String NewOffereeCode;

    @TableField("NewOffereeName")
    private String NewOffereeName;

    @TableField("Remark")
    private String Remark;

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

    public String getChangeOffereeId() {
        return ChangeOffereeId;
    }

    public void setChangeOffereeId(String ChangeOffereeId) {
        this.ChangeOffereeId = ChangeOffereeId;
    }
    public String getContractChangeID() {
        return ContractChangeID;
    }

    public void setContractChangeID(String ContractChangeID) {
        this.ContractChangeID = ContractChangeID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getContractChangeDetailID() {
        return ContractChangeDetailID;
    }

    public void setContractChangeDetailID(String ContractChangeDetailID) {
        this.ContractChangeDetailID = ContractChangeDetailID;
    }
    public String getOrginalOffereeId() {
        return OrginalOffereeId;
    }

    public void setOrginalOffereeId(String OrginalOffereeId) {
        this.OrginalOffereeId = OrginalOffereeId;
    }
    public String getOrginalOffereeCode() {
        return OrginalOffereeCode;
    }

    public void setOrginalOffereeCode(String OrginalOffereeCode) {
        this.OrginalOffereeCode = OrginalOffereeCode;
    }
    public String getOrginalOffereeName() {
        return OrginalOffereeName;
    }

    public void setOrginalOffereeName(String OrginalOffereeName) {
        this.OrginalOffereeName = OrginalOffereeName;
    }
    public String getNewOffereeId() {
        return NewOffereeId;
    }

    public void setNewOffereeId(String NewOffereeId) {
        this.NewOffereeId = NewOffereeId;
    }
    public String getNewOffereeCode() {
        return NewOffereeCode;
    }

    public void setNewOffereeCode(String NewOffereeCode) {
        this.NewOffereeCode = NewOffereeCode;
    }
    public String getNewOffereeName() {
        return NewOffereeName;
    }

    public void setNewOffereeName(String NewOffereeName) {
        this.NewOffereeName = NewOffereeName;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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
        return "CrContractchangeofferee{" +
        "ChangeOffereeId=" + ChangeOffereeId +
        ", ContractChangeID=" + ContractChangeID +
        ", ContractID=" + ContractID +
        ", ContractChangeDetailID=" + ContractChangeDetailID +
        ", OrginalOffereeId=" + OrginalOffereeId +
        ", OrginalOffereeCode=" + OrginalOffereeCode +
        ", OrginalOffereeName=" + OrginalOffereeName +
        ", NewOffereeId=" + NewOffereeId +
        ", NewOffereeCode=" + NewOffereeCode +
        ", NewOffereeName=" + NewOffereeName +
        ", Remark=" + Remark +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
