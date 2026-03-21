package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 签约依据
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
public class CrContractaccordoaother implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AccordingID")
    private String AccordingID;

    @TableField("ACode")
    private String ACode;

    @TableField("UserOrgID")
    private String UserOrgID;

    @TableField("OrgID")
    private String OrgID;

    @TableField("AccordingName")
    private String AccordingName;

    /**
     * 依据类型
     */
    @TableField("AccordingType")
    private Integer AccordingType;

    /**
     * 依据类型名称
     */
    @TableField("AccordingTypeName")
    private String AccordingTypeName;

    /**
     * 依据来源
     */
    @TableField("AccordingSource")
    private Integer AccordingSource;

    /**
     * 依据来源名称
     */
    @TableField("AccordingSourceName")
    private String AccordingSourceName;

    @TableField("IsValid")
    private Integer IsValid;

    @TableField("Status")
    private Integer Status;

    @TableField("ReferCount")
    private Integer ReferCount;

    @TableField("Remark")
    private String Remark;

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

    @TableField("PlanFactoryCode")
    private String PlanFactoryCode;

    @TableField("ProfitCenter")
    private String ProfitCenter;

    @TableField("FunctionPosition")
    private String FunctionPosition;

    @TableField("ERPModule")
    private Integer ERPModule;

    @TableField("MainPersonCode")
    private String MainPersonCode;

    @TableField("MainPersonName")
    private String MainPersonName;

    @TableField("CompanyCode")
    private String CompanyCode;

    @TableField("Remark01")
    private String Remark01;

    @TableField("Remark02")
    private String Remark02;

    @TableField("Remark03")
    private String Remark03;

    @TableField("Remark04")
    private String Remark04;

    @TableField("Remark05")
    private String Remark05;

    @TableField("BasicStartDate")
    private LocalDateTime BasicStartDate;

    @TableField("SystemCondition")
    private String SystemCondition;

    @TableField("VersionNumber")
    private String VersionNumber;

    @TableField("PlanPersonGroup")
    private String PlanPersonGroup;

    @TableField("WorkType")
    private String WorkType;

    @TableField("Remark06")
    private String Remark06;

    @TableField("Remark07")
    private String Remark07;

    @TableField("Remark08")
    private String Remark08;

    @TableField("Remark09")
    private String Remark09;

    @TableField("Remark10")
    private String Remark10;

    @TableField("UseCount")
    private Integer UseCount;

    @TableField("IWMSFlag")
    private Integer IWMSFlag;

    @TableField("IWMSContractID")
    private String IWMSContractID;

    /**
     * ERP大集中过来的数据
     */
    @TableField("GroupFlag")
    private Integer GroupFlag;

    /**
     * WBS编号
     */
    @TableField("WBSCode")
    private String WBSCode;

    /**
     * WBS名称
     */
    @TableField("WBSName")
    private String WBSName;

    @TableField("ItemUnit")
    private String ItemUnit;

    @TableField("BudgetAmount")
    private BigDecimal BudgetAmount;

    @TableField("OffereeCode")
    private String OffereeCode;

    @TableField("OffereeName")
    private String OffereeName;

    @TableField("BidAmount")
    private BigDecimal BidAmount;

    @TableField("BidAmountRate")
    private String BidAmountRate;

    @TableField("BidDate")
    private LocalDateTime BidDate;

    @TableField("BidFileCode")
    private String BidFileCode;

    /**
     * 中标人的投标文件
     */
    @TableField("BidFileUrl")
    private String BidFileUrl;

    @TableField("BudgetAmountRate")
    private String BudgetAmountRate;

    @TableField("ProjectCode")
    private String ProjectCode;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("RegisteredDate")
    private LocalDateTime RegisteredDate;

    @TableField("TenderCode")
    private String TenderCode;

    @TableField("TenderName")
    private String TenderName;

    @TableField("IsFrameTender")
    private Boolean IsFrameTender;

    /**
     * 招标文件
     */
    @TableField("TenderFileUrl")
    private String TenderFileUrl;

    /**
     * 中标人的投标文件
     */
    @TableField("BidManTenderFileUrl")
    private String BidManTenderFileUrl;

    /**
     * 招标文件标准编码
     */
    @TableField("TenderFileCode")
    private String TenderFileCode;

    public String getAccordingID() {
        return AccordingID;
    }

    public void setAccordingID(String AccordingID) {
        this.AccordingID = AccordingID;
    }
    public String getACode() {
        return ACode;
    }

    public void setACode(String ACode) {
        this.ACode = ACode;
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
    public String getAccordingName() {
        return AccordingName;
    }

    public void setAccordingName(String AccordingName) {
        this.AccordingName = AccordingName;
    }
    public Integer getAccordingType() {
        return AccordingType;
    }

    public void setAccordingType(Integer AccordingType) {
        this.AccordingType = AccordingType;
    }
    public String getAccordingTypeName() {
        return AccordingTypeName;
    }

    public void setAccordingTypeName(String AccordingTypeName) {
        this.AccordingTypeName = AccordingTypeName;
    }
    public Integer getAccordingSource() {
        return AccordingSource;
    }

    public void setAccordingSource(Integer AccordingSource) {
        this.AccordingSource = AccordingSource;
    }
    public String getAccordingSourceName() {
        return AccordingSourceName;
    }

    public void setAccordingSourceName(String AccordingSourceName) {
        this.AccordingSourceName = AccordingSourceName;
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
    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer ReferCount) {
        this.ReferCount = ReferCount;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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
    public String getPlanFactoryCode() {
        return PlanFactoryCode;
    }

    public void setPlanFactoryCode(String PlanFactoryCode) {
        this.PlanFactoryCode = PlanFactoryCode;
    }
    public String getProfitCenter() {
        return ProfitCenter;
    }

    public void setProfitCenter(String ProfitCenter) {
        this.ProfitCenter = ProfitCenter;
    }
    public String getFunctionPosition() {
        return FunctionPosition;
    }

    public void setFunctionPosition(String FunctionPosition) {
        this.FunctionPosition = FunctionPosition;
    }
    public Integer getERPModule() {
        return ERPModule;
    }

    public void setERPModule(Integer ERPModule) {
        this.ERPModule = ERPModule;
    }
    public String getMainPersonCode() {
        return MainPersonCode;
    }

    public void setMainPersonCode(String MainPersonCode) {
        this.MainPersonCode = MainPersonCode;
    }
    public String getMainPersonName() {
        return MainPersonName;
    }

    public void setMainPersonName(String MainPersonName) {
        this.MainPersonName = MainPersonName;
    }
    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }
    public String getRemark01() {
        return Remark01;
    }

    public void setRemark01(String Remark01) {
        this.Remark01 = Remark01;
    }
    public String getRemark02() {
        return Remark02;
    }

    public void setRemark02(String Remark02) {
        this.Remark02 = Remark02;
    }
    public String getRemark03() {
        return Remark03;
    }

    public void setRemark03(String Remark03) {
        this.Remark03 = Remark03;
    }
    public String getRemark04() {
        return Remark04;
    }

    public void setRemark04(String Remark04) {
        this.Remark04 = Remark04;
    }
    public String getRemark05() {
        return Remark05;
    }

    public void setRemark05(String Remark05) {
        this.Remark05 = Remark05;
    }
    public LocalDateTime getBasicStartDate() {
        return BasicStartDate;
    }

    public void setBasicStartDate(LocalDateTime BasicStartDate) {
        this.BasicStartDate = BasicStartDate;
    }
    public String getSystemCondition() {
        return SystemCondition;
    }

    public void setSystemCondition(String SystemCondition) {
        this.SystemCondition = SystemCondition;
    }
    public String getVersionNumber() {
        return VersionNumber;
    }

    public void setVersionNumber(String VersionNumber) {
        this.VersionNumber = VersionNumber;
    }
    public String getPlanPersonGroup() {
        return PlanPersonGroup;
    }

    public void setPlanPersonGroup(String PlanPersonGroup) {
        this.PlanPersonGroup = PlanPersonGroup;
    }
    public String getWorkType() {
        return WorkType;
    }

    public void setWorkType(String WorkType) {
        this.WorkType = WorkType;
    }
    public String getRemark06() {
        return Remark06;
    }

    public void setRemark06(String Remark06) {
        this.Remark06 = Remark06;
    }
    public String getRemark07() {
        return Remark07;
    }

    public void setRemark07(String Remark07) {
        this.Remark07 = Remark07;
    }
    public String getRemark08() {
        return Remark08;
    }

    public void setRemark08(String Remark08) {
        this.Remark08 = Remark08;
    }
    public String getRemark09() {
        return Remark09;
    }

    public void setRemark09(String Remark09) {
        this.Remark09 = Remark09;
    }
    public String getRemark10() {
        return Remark10;
    }

    public void setRemark10(String Remark10) {
        this.Remark10 = Remark10;
    }
    public Integer getUseCount() {
        return UseCount;
    }

    public void setUseCount(Integer UseCount) {
        this.UseCount = UseCount;
    }
    public Integer getIWMSFlag() {
        return IWMSFlag;
    }

    public void setIWMSFlag(Integer IWMSFlag) {
        this.IWMSFlag = IWMSFlag;
    }
    public String getIWMSContractID() {
        return IWMSContractID;
    }

    public void setIWMSContractID(String IWMSContractID) {
        this.IWMSContractID = IWMSContractID;
    }
    public Integer getGroupFlag() {
        return GroupFlag;
    }

    public void setGroupFlag(Integer GroupFlag) {
        this.GroupFlag = GroupFlag;
    }
    public String getWBSCode() {
        return WBSCode;
    }

    public void setWBSCode(String WBSCode) {
        this.WBSCode = WBSCode;
    }
    public String getWBSName() {
        return WBSName;
    }

    public void setWBSName(String WBSName) {
        this.WBSName = WBSName;
    }
    public String getItemUnit() {
        return ItemUnit;
    }

    public void setItemUnit(String ItemUnit) {
        this.ItemUnit = ItemUnit;
    }
    public BigDecimal getBudgetAmount() {
        return BudgetAmount;
    }

    public void setBudgetAmount(BigDecimal BudgetAmount) {
        this.BudgetAmount = BudgetAmount;
    }
    public String getOffereeCode() {
        return OffereeCode;
    }

    public void setOffereeCode(String OffereeCode) {
        this.OffereeCode = OffereeCode;
    }
    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String OffereeName) {
        this.OffereeName = OffereeName;
    }
    public BigDecimal getBidAmount() {
        return BidAmount;
    }

    public void setBidAmount(BigDecimal BidAmount) {
        this.BidAmount = BidAmount;
    }
    public String getBidAmountRate() {
        return BidAmountRate;
    }

    public void setBidAmountRate(String BidAmountRate) {
        this.BidAmountRate = BidAmountRate;
    }
    public LocalDateTime getBidDate() {
        return BidDate;
    }

    public void setBidDate(LocalDateTime BidDate) {
        this.BidDate = BidDate;
    }
    public String getBidFileCode() {
        return BidFileCode;
    }

    public void setBidFileCode(String BidFileCode) {
        this.BidFileCode = BidFileCode;
    }
    public String getBidFileUrl() {
        return BidFileUrl;
    }

    public void setBidFileUrl(String BidFileUrl) {
        this.BidFileUrl = BidFileUrl;
    }
    public String getBudgetAmountRate() {
        return BudgetAmountRate;
    }

    public void setBudgetAmountRate(String BudgetAmountRate) {
        this.BudgetAmountRate = BudgetAmountRate;
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
    public LocalDateTime getRegisteredDate() {
        return RegisteredDate;
    }

    public void setRegisteredDate(LocalDateTime RegisteredDate) {
        this.RegisteredDate = RegisteredDate;
    }
    public String getTenderCode() {
        return TenderCode;
    }

    public void setTenderCode(String TenderCode) {
        this.TenderCode = TenderCode;
    }
    public String getTenderName() {
        return TenderName;
    }

    public void setTenderName(String TenderName) {
        this.TenderName = TenderName;
    }
    public Boolean getIsFrameTender() {
        return IsFrameTender;
    }

    public void setIsFrameTender(Boolean IsFrameTender) {
        this.IsFrameTender = IsFrameTender;
    }
    public String getTenderFileUrl() {
        return TenderFileUrl;
    }

    public void setTenderFileUrl(String TenderFileUrl) {
        this.TenderFileUrl = TenderFileUrl;
    }
    public String getBidManTenderFileUrl() {
        return BidManTenderFileUrl;
    }

    public void setBidManTenderFileUrl(String BidManTenderFileUrl) {
        this.BidManTenderFileUrl = BidManTenderFileUrl;
    }
    public String getTenderFileCode() {
        return TenderFileCode;
    }

    public void setTenderFileCode(String TenderFileCode) {
        this.TenderFileCode = TenderFileCode;
    }

    @Override
    public String toString() {
        return "CrContractaccordoaother{" +
        "AccordingID=" + AccordingID +
        ", ACode=" + ACode +
        ", UserOrgID=" + UserOrgID +
        ", OrgID=" + OrgID +
        ", AccordingName=" + AccordingName +
        ", AccordingType=" + AccordingType +
        ", AccordingTypeName=" + AccordingTypeName +
        ", AccordingSource=" + AccordingSource +
        ", AccordingSourceName=" + AccordingSourceName +
        ", IsValid=" + IsValid +
        ", Status=" + Status +
        ", ReferCount=" + ReferCount +
        ", Remark=" + Remark +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", PlanFactoryCode=" + PlanFactoryCode +
        ", ProfitCenter=" + ProfitCenter +
        ", FunctionPosition=" + FunctionPosition +
        ", ERPModule=" + ERPModule +
        ", MainPersonCode=" + MainPersonCode +
        ", MainPersonName=" + MainPersonName +
        ", CompanyCode=" + CompanyCode +
        ", Remark01=" + Remark01 +
        ", Remark02=" + Remark02 +
        ", Remark03=" + Remark03 +
        ", Remark04=" + Remark04 +
        ", Remark05=" + Remark05 +
        ", BasicStartDate=" + BasicStartDate +
        ", SystemCondition=" + SystemCondition +
        ", VersionNumber=" + VersionNumber +
        ", PlanPersonGroup=" + PlanPersonGroup +
        ", WorkType=" + WorkType +
        ", Remark06=" + Remark06 +
        ", Remark07=" + Remark07 +
        ", Remark08=" + Remark08 +
        ", Remark09=" + Remark09 +
        ", Remark10=" + Remark10 +
        ", UseCount=" + UseCount +
        ", IWMSFlag=" + IWMSFlag +
        ", IWMSContractID=" + IWMSContractID +
        ", GroupFlag=" + GroupFlag +
        ", WBSCode=" + WBSCode +
        ", WBSName=" + WBSName +
        ", ItemUnit=" + ItemUnit +
        ", BudgetAmount=" + BudgetAmount +
        ", OffereeCode=" + OffereeCode +
        ", OffereeName=" + OffereeName +
        ", BidAmount=" + BidAmount +
        ", BidAmountRate=" + BidAmountRate +
        ", BidDate=" + BidDate +
        ", BidFileCode=" + BidFileCode +
        ", BidFileUrl=" + BidFileUrl +
        ", BudgetAmountRate=" + BudgetAmountRate +
        ", ProjectCode=" + ProjectCode +
        ", ProjectName=" + ProjectName +
        ", RegisteredDate=" + RegisteredDate +
        ", TenderCode=" + TenderCode +
        ", TenderName=" + TenderName +
        ", IsFrameTender=" + IsFrameTender +
        ", TenderFileUrl=" + TenderFileUrl +
        ", BidManTenderFileUrl=" + BidManTenderFileUrl +
        ", TenderFileCode=" + TenderFileCode +
        "}";
    }
}
