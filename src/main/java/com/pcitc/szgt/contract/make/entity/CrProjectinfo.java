package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public class CrProjectinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ProjectID")
    private String ProjectID;

    /**
     * 用户单位OUID
     */
    @TableField("UserOrgID")
    private String UserOrgID;

    /**
     * 使用范围
     */
    @TableField("OrgID")
    private String OrgID;

    /**
     * 项目编号
     */
    @TableField("ProjectCode")
    private String ProjectCode;

    /**
     * 项目名称
     */
    @TableField("ProjectName")
    private String ProjectName;

    /**
     * 建设单位
     */
    @TableField("ExecOrgan")
    private String ExecOrgan;

    /**
     * 立项年度
     */
    @TableField("AtYear")
    private Integer AtYear;

    /**
     * 项目总投资金额
     */
    @TableField("InvestAmount")
    private BigDecimal InvestAmount;

    /**
     * 币种
     */
    @TableField("Currency")
    private Integer Currency;

    /**
     * 汇率
     */
    @TableField("Rate")
    private BigDecimal Rate;

    /**
     * 人民币
     */
    @TableField("Amount")
    private BigDecimal Amount;

    /**
     * 是否有效 0否 1 是
     */
    @TableField("IsValid")
    private Integer IsValid;

    /**
     * 状态
     */
    @TableField("Status")
    private Integer Status;

    /**
     * 是否删除 0否 1是
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 备注
     */
    @TableField("Remark")
    private String Remark;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 修改时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 企业标识
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 负责人
     */
    @TableField("Responsibleperson")
    private String Responsibleperson;

    /**
     * 负责人编码
     */
    @TableField("Responsiblepersoncode")
    private String Responsiblepersoncode;

    /**
     * 公司代码
     */
    @TableField("Companycode")
    private String Companycode;

    /**
     * 工厂
     */
    @TableField("Factory")
    private String Factory;

    /**
     * 数据来源 ERP 手工
     */
    private Integer source;

    /**
     * 扩展字段1
     */
    @TableField("REMARK1")
    private String remark1;

    /**
     * 扩展字段2
     */
    @TableField("REMARK2")
    private String remark2;

    /**
     * 扩展字段3
     */
    @TableField("REMARK3")
    private String remark3;

    /**
     * 扩展字段4
     */
    @TableField("REMARK4")
    private String remark4;

    /**
     * 扩展字段5
     */
    @TableField("REMARK5")
    private String remark5;

    @TableField("BeLongTo")
    private Integer BeLongTo;

    @TableField("GroupFlag")
    private Integer GroupFlag;

    @TableField("ReferAccordingId")
    private String ReferAccordingId;

    @TableField("ProjectObject")
    private String ProjectObject;

    @TableField("ProjectObjectMoney")
    private BigDecimal ProjectObjectMoney;

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getUserOrgID() {
        return UserOrgID;
    }

    public void setUserOrgID(String UserOrgID) {
        this.UserOrgID = UserOrgID;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getProjectCode() {
        return ProjectCode;
    }

    public void setProjectCode(String ProjectCode) {
        this.ProjectCode = ProjectCode;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public String getExecOrgan() {
        return ExecOrgan;
    }

    public void setExecOrgan(String ExecOrgan) {
        this.ExecOrgan = ExecOrgan;
    }
    public Integer getAtYear() {
        return AtYear;
    }

    public void setAtYear(Integer AtYear) {
        this.AtYear = AtYear;
    }
    public BigDecimal getInvestAmount() {
        return InvestAmount;
    }

    public void setInvestAmount(BigDecimal InvestAmount) {
        this.InvestAmount = InvestAmount;
    }
    public Integer getCurrency() {
        return Currency;
    }

    public void setCurrency(Integer Currency) {
        this.Currency = Currency;
    }
    public BigDecimal getRate() {
        return Rate;
    }

    public void setRate(BigDecimal Rate) {
        this.Rate = Rate;
    }
    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }
    public Integer getIsValid() {
        return IsValid;
    }

    public void setIsValid(Integer IsValid) {
        this.IsValid = IsValid;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
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
    public String getResponsibleperson() {
        return Responsibleperson;
    }

    public void setResponsibleperson(String Responsibleperson) {
        this.Responsibleperson = Responsibleperson;
    }
    public String getResponsiblepersoncode() {
        return Responsiblepersoncode;
    }

    public void setResponsiblepersoncode(String Responsiblepersoncode) {
        this.Responsiblepersoncode = Responsiblepersoncode;
    }
    public String getCompanycode() {
        return Companycode;
    }

    public void setCompanycode(String Companycode) {
        this.Companycode = Companycode;
    }
    public String getFactory() {
        return Factory;
    }

    public void setFactory(String Factory) {
        this.Factory = Factory;
    }
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }
    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }
    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }
    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }
    public Integer getBeLongTo() {
        return BeLongTo;
    }

    public void setBeLongTo(Integer BeLongTo) {
        this.BeLongTo = BeLongTo;
    }
    public Integer getGroupFlag() {
        return GroupFlag;
    }

    public void setGroupFlag(Integer GroupFlag) {
        this.GroupFlag = GroupFlag;
    }
    public String getReferAccordingId() {
        return ReferAccordingId;
    }

    public void setReferAccordingId(String ReferAccordingId) {
        this.ReferAccordingId = ReferAccordingId;
    }
    public String getProjectObject() {
        return ProjectObject;
    }

    public void setProjectObject(String ProjectObject) {
        this.ProjectObject = ProjectObject;
    }
    public BigDecimal getProjectObjectMoney() {
        return ProjectObjectMoney;
    }

    public void setProjectObjectMoney(BigDecimal ProjectObjectMoney) {
        this.ProjectObjectMoney = ProjectObjectMoney;
    }

    @Override
    public String toString() {
        return "CrProjectinfo{" +
        "ProjectID=" + ProjectID +
        ", UserOrgID=" + UserOrgID +
        ", OrgID=" + OrgID +
        ", ProjectCode=" + ProjectCode +
        ", ProjectName=" + ProjectName +
        ", ExecOrgan=" + ExecOrgan +
        ", AtYear=" + AtYear +
        ", InvestAmount=" + InvestAmount +
        ", Currency=" + Currency +
        ", Rate=" + Rate +
        ", Amount=" + Amount +
        ", IsValid=" + IsValid +
        ", Status=" + Status +
        ", LogicDel=" + LogicDel +
        ", Remark=" + Remark +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", Responsibleperson=" + Responsibleperson +
        ", Responsiblepersoncode=" + Responsiblepersoncode +
        ", Companycode=" + Companycode +
        ", Factory=" + Factory +
        ", source=" + source +
        ", remark1=" + remark1 +
        ", remark2=" + remark2 +
        ", remark3=" + remark3 +
        ", remark4=" + remark4 +
        ", remark5=" + remark5 +
        ", BeLongTo=" + BeLongTo +
        ", GroupFlag=" + GroupFlag +
        ", ReferAccordingId=" + ReferAccordingId +
        ", ProjectObject=" + ProjectObject +
        ", ProjectObjectMoney=" + ProjectObjectMoney +
        "}";
    }
}
