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
 * @since 2020-03-06
 */
public class FfOffereeappraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("PerformCommentID")
    private String PerformCommentID;

    @TableField("OffereeID")
    private String OffereeID;

    @TableField("OldPerformCommentID")
    private String OldPerformCommentID;

    @TableField("OldOffereeID")
    private String OldOffereeID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("Appraiser")
    private String Appraiser;

    @TableField("AppraiseDate")
    private LocalDateTime AppraiseDate;

    @TableField("AppraiseContent")
    private String AppraiseContent;

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

    public String getPerformCommentID() {
        return PerformCommentID;
    }

    public void setPerformCommentID(String PerformCommentID) {
        this.PerformCommentID = PerformCommentID;
    }
    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String OffereeID) {
        this.OffereeID = OffereeID;
    }
    public String getOldPerformCommentID() {
        return OldPerformCommentID;
    }

    public void setOldPerformCommentID(String OldPerformCommentID) {
        this.OldPerformCommentID = OldPerformCommentID;
    }
    public String getOldOffereeID() {
        return OldOffereeID;
    }

    public void setOldOffereeID(String OldOffereeID) {
        this.OldOffereeID = OldOffereeID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getAppraiser() {
        return Appraiser;
    }

    public void setAppraiser(String Appraiser) {
        this.Appraiser = Appraiser;
    }
    public LocalDateTime getAppraiseDate() {
        return AppraiseDate;
    }

    public void setAppraiseDate(LocalDateTime AppraiseDate) {
        this.AppraiseDate = AppraiseDate;
    }
    public String getAppraiseContent() {
        return AppraiseContent;
    }

    public void setAppraiseContent(String AppraiseContent) {
        this.AppraiseContent = AppraiseContent;
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
        return "FfOffereeappraise{" +
        "PerformCommentID=" + PerformCommentID +
        ", OffereeID=" + OffereeID +
        ", OldPerformCommentID=" + OldPerformCommentID +
        ", OldOffereeID=" + OldOffereeID +
        ", ContractID=" + ContractID +
        ", Appraiser=" + Appraiser +
        ", AppraiseDate=" + AppraiseDate +
        ", AppraiseContent=" + AppraiseContent +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
