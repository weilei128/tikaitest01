package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 * @author jobob
 * @since 2020-02-28
 */
@ApiModel(value = "AmUnitconfiguration" , description = "单位配置对象类")
public class AmUnitconfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("OrgConfigID")
    private String OrgConfigID;

    @TableField("OrgID")
    private Integer OrgID;

    @TableField("GroupWaterMarkID")
    private String GroupWaterMarkID;

    @TableField("GroupWaterMarkPath")
    private String GroupWaterMarkPath;

    @TableField("GroupCorp")
    private String GroupCorp;

    @TableField("GroupCorpName")
    private String GroupCorpName;

    @TableField("ShareWaterMarkID")
    private String ShareWaterMarkID;

    @TableField("ShareWaterMarkPath")
    private String ShareWaterMarkPath;

    @TableField("ShareCorp")
    private String ShareCorp;

    @TableField("ShareCorpName")
    private String ShareCorpName;

    @TableField("AssetWaterMarkID")
    private String AssetWaterMarkID;

    @TableField("AssetWaterMarkPath")
    private String AssetWaterMarkPath;

    @TableField("AssetCorp")
    private String AssetCorp;

    @TableField("AssetCorpName")
    private String AssetCorpName;

    @TableField("FlowDistributer")
    private String FlowDistributer;

    @TableField("HonestDutyID")
    private String HonestDutyID;

    @TableField("HonestDutyPath")
    private String HonestDutyPath;

    @TableField("EntrustID")
    private String EntrustID;

    @TableField("EntrustPath")
    private String EntrustPath;

    @TableField("CheckListID")
    private String CheckListID;

    @TableField("CheckListPath")
    private String CheckListPath;

    @TableField("EffectiveDate")
    private LocalDateTime EffectiveDate;

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

    @TableField("SafeProtocolID")
    private String SafeProtocolID;

    @TableField("PerformPaymentID")
    private String PerformPaymentID;

    @TableField("GroupElectronicSealID")
    private String GroupElectronicSealID;

    @TableField("GroupElectronicSealNum")
    private String GroupElectronicSealNum;

    @TableField("StockElectronicSealID")
    private String StockElectronicSealID;

    @TableField("StockElectronicSealNum")
    private String StockElectronicSealNum;

    @TableField("AssetElectronicSealID")
    private String AssetElectronicSealID;

    @TableField("AssetElectronicSealNum")
    private String AssetElectronicSealNum;

    @TableField("OffereeCheckUserID")
    private String OffereeCheckUserID;

    @TableField("OffereeCheckUserName")
    private String OffereeCheckUserName;

    @TableField("WasteCheckUserID")
    private String WasteCheckUserID;

    @TableField("WasteCheckUserName")
    private String WasteCheckUserName;

    @TableField("PerformDateIsEnabled")
    private Integer PerformDateIsEnabled;

    @TableField("ReminderDays")
    private Integer ReminderDays;

    @TableField("DisableDays")
    private Integer DisableDays;

    @TableField("IsSignatureAndSeal")
    private Integer IsSignatureAndSeal;

    @TableField("SignDateIsEnabled")
    private Integer SignDateIsEnabled;

    @TableField("SignRemindDays")
    private Integer SignRemindDays;

    @TableField("SignDisableDays")
    private Integer SignDisableDays;

    @TableField("FinalityIsEnabled")
    private Integer FinalityIsEnabled;

    @TableField("FinalityDisableDays")
    private Integer FinalityDisableDays;

    @TableField("HasDOMSecurity")
    private Integer HasDOMSecurity;

    @TableField("IsSingingBody")
    private Integer IsSingingBody;

    @TableField("FinalDay")
    private Integer FinalDay;

    public String getOrgConfigID() {
        return OrgConfigID;
    }

    public void setOrgConfigID(String OrgConfigID) {
        this.OrgConfigID = OrgConfigID;
    }
    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer OrgID) {
        this.OrgID = OrgID;
    }
    public String getGroupWaterMarkID() {
        return GroupWaterMarkID;
    }

    public void setGroupWaterMarkID(String GroupWaterMarkID) {
        this.GroupWaterMarkID = GroupWaterMarkID;
    }
    public String getGroupWaterMarkPath() {
        return GroupWaterMarkPath;
    }

    public void setGroupWaterMarkPath(String GroupWaterMarkPath) {
        this.GroupWaterMarkPath = GroupWaterMarkPath;
    }
    public String getGroupCorp() {
        return GroupCorp;
    }

    public void setGroupCorp(String GroupCorp) {
        this.GroupCorp = GroupCorp;
    }
    public String getGroupCorpName() {
        return GroupCorpName;
    }

    public void setGroupCorpName(String GroupCorpName) {
        this.GroupCorpName = GroupCorpName;
    }
    public String getShareWaterMarkID() {
        return ShareWaterMarkID;
    }

    public void setShareWaterMarkID(String ShareWaterMarkID) {
        this.ShareWaterMarkID = ShareWaterMarkID;
    }
    public String getShareWaterMarkPath() {
        return ShareWaterMarkPath;
    }

    public void setShareWaterMarkPath(String ShareWaterMarkPath) {
        this.ShareWaterMarkPath = ShareWaterMarkPath;
    }
    public String getShareCorp() {
        return ShareCorp;
    }

    public void setShareCorp(String ShareCorp) {
        this.ShareCorp = ShareCorp;
    }
    public String getShareCorpName() {
        return ShareCorpName;
    }

    public void setShareCorpName(String ShareCorpName) {
        this.ShareCorpName = ShareCorpName;
    }
    public String getAssetWaterMarkID() {
        return AssetWaterMarkID;
    }

    public void setAssetWaterMarkID(String AssetWaterMarkID) {
        this.AssetWaterMarkID = AssetWaterMarkID;
    }
    public String getAssetWaterMarkPath() {
        return AssetWaterMarkPath;
    }

    public void setAssetWaterMarkPath(String AssetWaterMarkPath) {
        this.AssetWaterMarkPath = AssetWaterMarkPath;
    }
    public String getAssetCorp() {
        return AssetCorp;
    }

    public void setAssetCorp(String AssetCorp) {
        this.AssetCorp = AssetCorp;
    }
    public String getAssetCorpName() {
        return AssetCorpName;
    }

    public void setAssetCorpName(String AssetCorpName) {
        this.AssetCorpName = AssetCorpName;
    }
    public String getFlowDistributer() {
        return FlowDistributer;
    }

    public void setFlowDistributer(String FlowDistributer) {
        this.FlowDistributer = FlowDistributer;
    }
    public String getHonestDutyID() {
        return HonestDutyID;
    }

    public void setHonestDutyID(String HonestDutyID) {
        this.HonestDutyID = HonestDutyID;
    }
    public String getHonestDutyPath() {
        return HonestDutyPath;
    }

    public void setHonestDutyPath(String HonestDutyPath) {
        this.HonestDutyPath = HonestDutyPath;
    }
    public String getEntrustID() {
        return EntrustID;
    }

    public void setEntrustID(String EntrustID) {
        this.EntrustID = EntrustID;
    }
    public String getEntrustPath() {
        return EntrustPath;
    }

    public void setEntrustPath(String EntrustPath) {
        this.EntrustPath = EntrustPath;
    }
    public String getCheckListID() {
        return CheckListID;
    }

    public void setCheckListID(String CheckListID) {
        this.CheckListID = CheckListID;
    }
    public String getCheckListPath() {
        return CheckListPath;
    }

    public void setCheckListPath(String CheckListPath) {
        this.CheckListPath = CheckListPath;
    }
    public LocalDateTime getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(LocalDateTime EffectiveDate) {
        this.EffectiveDate = EffectiveDate;
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
    public String getSafeProtocolID() {
        return SafeProtocolID;
    }

    public void setSafeProtocolID(String SafeProtocolID) {
        this.SafeProtocolID = SafeProtocolID;
    }
    public String getPerformPaymentID() {
        return PerformPaymentID;
    }

    public void setPerformPaymentID(String PerformPaymentID) {
        this.PerformPaymentID = PerformPaymentID;
    }
    public String getGroupElectronicSealID() {
        return GroupElectronicSealID;
    }

    public void setGroupElectronicSealID(String GroupElectronicSealID) {
        this.GroupElectronicSealID = GroupElectronicSealID;
    }
    public String getGroupElectronicSealNum() {
        return GroupElectronicSealNum;
    }

    public void setGroupElectronicSealNum(String GroupElectronicSealNum) {
        this.GroupElectronicSealNum = GroupElectronicSealNum;
    }
    public String getStockElectronicSealID() {
        return StockElectronicSealID;
    }

    public void setStockElectronicSealID(String StockElectronicSealID) {
        this.StockElectronicSealID = StockElectronicSealID;
    }
    public String getStockElectronicSealNum() {
        return StockElectronicSealNum;
    }

    public void setStockElectronicSealNum(String StockElectronicSealNum) {
        this.StockElectronicSealNum = StockElectronicSealNum;
    }
    public String getAssetElectronicSealID() {
        return AssetElectronicSealID;
    }

    public void setAssetElectronicSealID(String AssetElectronicSealID) {
        this.AssetElectronicSealID = AssetElectronicSealID;
    }
    public String getAssetElectronicSealNum() {
        return AssetElectronicSealNum;
    }

    public void setAssetElectronicSealNum(String AssetElectronicSealNum) {
        this.AssetElectronicSealNum = AssetElectronicSealNum;
    }
    public String getOffereeCheckUserID() {
        return OffereeCheckUserID;
    }

    public void setOffereeCheckUserID(String OffereeCheckUserID) {
        this.OffereeCheckUserID = OffereeCheckUserID;
    }
    public String getOffereeCheckUserName() {
        return OffereeCheckUserName;
    }

    public void setOffereeCheckUserName(String OffereeCheckUserName) {
        this.OffereeCheckUserName = OffereeCheckUserName;
    }
    public String getWasteCheckUserID() {
        return WasteCheckUserID;
    }

    public void setWasteCheckUserID(String WasteCheckUserID) {
        this.WasteCheckUserID = WasteCheckUserID;
    }
    public String getWasteCheckUserName() {
        return WasteCheckUserName;
    }

    public void setWasteCheckUserName(String WasteCheckUserName) {
        this.WasteCheckUserName = WasteCheckUserName;
    }
    public Integer getPerformDateIsEnabled() {
        return PerformDateIsEnabled;
    }

    public void setPerformDateIsEnabled(Integer PerformDateIsEnabled) {
        this.PerformDateIsEnabled = PerformDateIsEnabled;
    }
    public Integer getReminderDays() {
        return ReminderDays;
    }

    public void setReminderDays(Integer ReminderDays) {
        this.ReminderDays = ReminderDays;
    }
    public Integer getDisableDays() {
        return DisableDays;
    }

    public void setDisableDays(Integer DisableDays) {
        this.DisableDays = DisableDays;
    }
    public Integer getIsSignatureAndSeal() {
        return IsSignatureAndSeal;
    }

    public void setIsSignatureAndSeal(Integer IsSignatureAndSeal) {
        this.IsSignatureAndSeal = IsSignatureAndSeal;
    }
    public Integer getSignDateIsEnabled() {
        return SignDateIsEnabled;
    }

    public void setSignDateIsEnabled(Integer SignDateIsEnabled) {
        this.SignDateIsEnabled = SignDateIsEnabled;
    }
    public Integer getSignRemindDays() {
        return SignRemindDays;
    }

    public void setSignRemindDays(Integer SignRemindDays) {
        this.SignRemindDays = SignRemindDays;
    }
    public Integer getSignDisableDays() {
        return SignDisableDays;
    }

    public void setSignDisableDays(Integer SignDisableDays) {
        this.SignDisableDays = SignDisableDays;
    }
    public Integer getFinalityIsEnabled() {
        return FinalityIsEnabled;
    }

    public void setFinalityIsEnabled(Integer FinalityIsEnabled) {
        this.FinalityIsEnabled = FinalityIsEnabled;
    }
    public Integer getFinalityDisableDays() {
        return FinalityDisableDays;
    }

    public void setFinalityDisableDays(Integer FinalityDisableDays) {
        this.FinalityDisableDays = FinalityDisableDays;
    }
    public Integer getHasDOMSecurity() {
        return HasDOMSecurity;
    }

    public void setHasDOMSecurity(Integer HasDOMSecurity) {
        this.HasDOMSecurity = HasDOMSecurity;
    }

    public Integer getIsSingingBody() {
        return IsSingingBody;
    }

    public void setIsSingingBody(Integer isSingingBody) {
        IsSingingBody = isSingingBody;
    }

    public Integer getFinalDay() {
        return FinalDay;
    }

    public void setFinalDay(Integer finalDay) {
        FinalDay = finalDay;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AmUnitconfiguration{");
        sb.append("OrgConfigID='").append(OrgConfigID).append('\'');
        sb.append(", OrgID=").append(OrgID);
        sb.append(", GroupWaterMarkID='").append(GroupWaterMarkID).append('\'');
        sb.append(", GroupWaterMarkPath='").append(GroupWaterMarkPath).append('\'');
        sb.append(", GroupCorp='").append(GroupCorp).append('\'');
        sb.append(", GroupCorpName='").append(GroupCorpName).append('\'');
        sb.append(", ShareWaterMarkID='").append(ShareWaterMarkID).append('\'');
        sb.append(", ShareWaterMarkPath='").append(ShareWaterMarkPath).append('\'');
        sb.append(", ShareCorp='").append(ShareCorp).append('\'');
        sb.append(", ShareCorpName='").append(ShareCorpName).append('\'');
        sb.append(", AssetWaterMarkID='").append(AssetWaterMarkID).append('\'');
        sb.append(", AssetWaterMarkPath='").append(AssetWaterMarkPath).append('\'');
        sb.append(", AssetCorp='").append(AssetCorp).append('\'');
        sb.append(", AssetCorpName='").append(AssetCorpName).append('\'');
        sb.append(", FlowDistributer='").append(FlowDistributer).append('\'');
        sb.append(", HonestDutyID='").append(HonestDutyID).append('\'');
        sb.append(", HonestDutyPath='").append(HonestDutyPath).append('\'');
        sb.append(", EntrustID='").append(EntrustID).append('\'');
        sb.append(", EntrustPath='").append(EntrustPath).append('\'');
        sb.append(", CheckListID='").append(CheckListID).append('\'');
        sb.append(", CheckListPath='").append(CheckListPath).append('\'');
        sb.append(", EffectiveDate=").append(EffectiveDate);
        sb.append(", CreatedBy='").append(CreatedBy).append('\'');
        sb.append(", CreatedDate=").append(CreatedDate);
        sb.append(", ModifiedBy='").append(ModifiedBy).append('\'');
        sb.append(", ModifiedDate=").append(ModifiedDate);
        sb.append(", Oulabel=").append(Oulabel);
        sb.append(", SafeProtocolID='").append(SafeProtocolID).append('\'');
        sb.append(", PerformPaymentID='").append(PerformPaymentID).append('\'');
        sb.append(", GroupElectronicSealID='").append(GroupElectronicSealID).append('\'');
        sb.append(", GroupElectronicSealNum='").append(GroupElectronicSealNum).append('\'');
        sb.append(", StockElectronicSealID='").append(StockElectronicSealID).append('\'');
        sb.append(", StockElectronicSealNum='").append(StockElectronicSealNum).append('\'');
        sb.append(", AssetElectronicSealID='").append(AssetElectronicSealID).append('\'');
        sb.append(", AssetElectronicSealNum='").append(AssetElectronicSealNum).append('\'');
        sb.append(", OffereeCheckUserID='").append(OffereeCheckUserID).append('\'');
        sb.append(", OffereeCheckUserName='").append(OffereeCheckUserName).append('\'');
        sb.append(", WasteCheckUserID='").append(WasteCheckUserID).append('\'');
        sb.append(", WasteCheckUserName='").append(WasteCheckUserName).append('\'');
        sb.append(", PerformDateIsEnabled=").append(PerformDateIsEnabled);
        sb.append(", ReminderDays=").append(ReminderDays);
        sb.append(", DisableDays=").append(DisableDays);
        sb.append(", IsSignatureAndSeal=").append(IsSignatureAndSeal);
        sb.append(", SignDateIsEnabled=").append(SignDateIsEnabled);
        sb.append(", SignRemindDays=").append(SignRemindDays);
        sb.append(", SignDisableDays=").append(SignDisableDays);
        sb.append(", FinalityIsEnabled=").append(FinalityIsEnabled);
        sb.append(", FinalityDisableDays=").append(FinalityDisableDays);
        sb.append(", HasDOMSecurity=").append(HasDOMSecurity);
        sb.append(", IsSingingBody=").append(IsSingingBody);
        sb.append('}');
        return sb.toString();
    }
}
