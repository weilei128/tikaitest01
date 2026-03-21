package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-20
 */
public class CrContractrununit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("RunUnitID")
    private String RunUnitID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("OrgID")
    private Integer OrgID;

    //履行经办人
    @TableField("UserID")
    private String UserID;

    @TableField("Oulabel")
    private Integer Oulabel;

    //执行转交的用户
    @TableField("PayUserId")
    private String PayUserId;

    @TableField("PayUserOrg")
    private Integer PayUserOrg;

    //终结办理人
    @TableField("FinalityUserId")
    private String FinalityUserId;

    @TableField("FinalityUserOrg")
    private Integer FinalityUserOrg;

    @TableField("FrameOrg")
    private Integer FrameOrg;

    @TableField("IsFrameContract")
    private Integer IsFrameContract;

    public String getRunUnitID() {
        return RunUnitID;
    }

    public void setRunUnitID(String RunUnitID) {
        this.RunUnitID = RunUnitID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer OrgID) {
        this.OrgID = OrgID;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public String getPayUserId() {
        return PayUserId;
    }

    public void setPayUserId(String PayUserId) {
        this.PayUserId = PayUserId;
    }
    public Integer getPayUserOrg() {
        return PayUserOrg;
    }

    public void setPayUserOrg(Integer PayUserOrg) {
        this.PayUserOrg = PayUserOrg;
    }
    public String getFinalityUserId() {
        return FinalityUserId;
    }

    public void setFinalityUserId(String FinalityUserId) {
        this.FinalityUserId = FinalityUserId;
    }
    public Integer getFinalityUserOrg() {
        return FinalityUserOrg;
    }

    public void setFinalityUserOrg(Integer FinalityUserOrg) {
        this.FinalityUserOrg = FinalityUserOrg;
    }
    public Integer getFrameOrg() {
        return FrameOrg;
    }

    public void setFrameOrg(Integer FrameOrg) {
        this.FrameOrg = FrameOrg;
    }
    public Integer getIsFrameContract() {
        return IsFrameContract;
    }

    public void setIsFrameContract(Integer IsFrameContract) {
        this.IsFrameContract = IsFrameContract;
    }

    @Override
    public String toString() {
        return "CrContractrununit{" +
        "RunUnitID=" + RunUnitID +
        ", ContractID=" + ContractID +
        ", OrgID=" + OrgID +
        ", UserID=" + UserID +
        ", Oulabel=" + Oulabel +
        ", PayUserId=" + PayUserId +
        ", PayUserOrg=" + PayUserOrg +
        ", FinalityUserId=" + FinalityUserId +
        ", FinalityUserOrg=" + FinalityUserOrg +
        ", FrameOrg=" + FrameOrg +
        ", IsFrameContract=" + IsFrameContract +
        "}";
    }
}
