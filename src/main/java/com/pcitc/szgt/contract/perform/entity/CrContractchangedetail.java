package com.pcitc.szgt.contract.perform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-02
 */
public class CrContractchangedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ContractChangeDetailID")
    private String ContractChangeDetailID;

    @TableField("ContractChangeID")
    private String ContractChangeID;

    @TableField("ChangeType")
    private Integer ChangeType;

    @TableField("ChangeValue1")
    private String ChangeValue1;

    @TableField("ChangeValue2")
    private String ChangeValue2;

    @TableField("ChangeValue3")
    private String ChangeValue3;

    @TableField("ChangeValue4")
    private String ChangeValue4;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("MySignBodyCode")
    private Integer MySignBodyCode;

    @TableField("MySignBodyName")
    private String MySignBodyName;

    @TableField("CompanyType")
    private Integer CompanyType;

    public String getContractChangeDetailID() {
        return ContractChangeDetailID;
    }

    public void setContractChangeDetailID(String ContractChangeDetailID) {
        this.ContractChangeDetailID = ContractChangeDetailID;
    }
    public String getContractChangeID() {
        return ContractChangeID;
    }

    public void setContractChangeID(String ContractChangeID) {
        this.ContractChangeID = ContractChangeID;
    }
    public Integer getChangeType() {
        return ChangeType;
    }

    public void setChangeType(Integer ChangeType) {
        this.ChangeType = ChangeType;
    }
    public String getChangeValue1() {
        return ChangeValue1;
    }

    public void setChangeValue1(String ChangeValue1) {
        this.ChangeValue1 = ChangeValue1;
    }
    public String getChangeValue2() {
        return ChangeValue2;
    }

    public void setChangeValue2(String ChangeValue2) {
        this.ChangeValue2 = ChangeValue2;
    }
    public String getChangeValue3() {
        return ChangeValue3;
    }

    public void setChangeValue3(String ChangeValue3) {
        this.ChangeValue3 = ChangeValue3;
    }
    public String getChangeValue4() {
        return ChangeValue4;
    }

    public void setChangeValue4(String ChangeValue4) {
        this.ChangeValue4 = ChangeValue4;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public Integer getMySignBodyCode() {
        return MySignBodyCode;
    }

    public void setMySignBodyCode(Integer MySignBodyCode) {
        this.MySignBodyCode = MySignBodyCode;
    }
    public String getMySignBodyName() {
        return MySignBodyName;
    }

    public void setMySignBodyName(String MySignBodyName) {
        this.MySignBodyName = MySignBodyName;
    }
    public Integer getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(Integer CompanyType) {
        this.CompanyType = CompanyType;
    }

    @Override
    public String toString() {
        return "CrContractchangedetail{" +
        "ContractChangeDetailID=" + ContractChangeDetailID +
        ", ContractChangeID=" + ContractChangeID +
        ", ChangeType=" + ChangeType +
        ", ChangeValue1=" + ChangeValue1 +
        ", ChangeValue2=" + ChangeValue2 +
        ", ChangeValue3=" + ChangeValue3 +
        ", ChangeValue4=" + ChangeValue4 +
        ", Oulabel=" + Oulabel +
        ", MySignBodyCode=" + MySignBodyCode +
        ", MySignBodyName=" + MySignBodyName +
        ", CompanyType=" + CompanyType +
        "}";
    }
}
