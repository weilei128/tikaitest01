package com.pcitc.szgt.contract.appmanager.model;

import java.util.List;

public class UnitConfigResultVo {
    /**
     * 单位配置id
     */
    private String OrgConfigID;

    private String GroupCorpName;

    /**
     * 集团签约主体-法定代表人id
     */
    private String GroupCorp;

    /**
     * 资产签约主体-法定代表人名字
     */
    private String AssetCorpName;

    /**
     * 资产签约主体-法定代表人id
     */
    private String AssetCorp;

    /**
     * 股份签约主体法定代表人名字
     */
    private String ShareCorpName;

    /**
     * 股份签约主体法定代表人id
     */
    private String ShareCorp;

    /**
     * 流程分发员名字
     */
    private String FlowDistributerName;

    /**
     * 流程分发员id
     */
    private String FlowDistributer;

    /**
     * 组织机构id
     */
    private String OrgID;

    /**
     * 是否单位配置
     */
    private Integer IsSingingBody;

    /**
     * 合同终结警报天数
     */
    private Integer FinalDay;

    /**
     * 签约主体名称列表
     */
    private List<OrgSignVo> OrgSigns;

    public String getOrgConfigID() {
        return OrgConfigID;
    }

    public void setOrgConfigID(String orgConfigID) {
        OrgConfigID = orgConfigID;
    }

    public String getGroupCorpName() {
        return GroupCorpName;
    }

    public void setGroupCorpName(String groupCorpName) {
        GroupCorpName = groupCorpName;
    }

    public String getAssetCorpName() {
        return AssetCorpName;
    }

    public void setAssetCorpName(String assetCorpName) {
        AssetCorpName = assetCorpName;
    }

    public String getShareCorpName() {
        return ShareCorpName;
    }

    public void setShareCorpName(String shareCorpName) {
        ShareCorpName = shareCorpName;
    }

    public String getFlowDistributerName() {
        return FlowDistributerName;
    }

    public void setFlowDistributerName(String flowDistributerName) {
        FlowDistributerName = flowDistributerName;
    }

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    public Integer getIsSingingBody() {
        return IsSingingBody;
    }

    public void setIsSingingBody(Integer isSingingBody) {
        IsSingingBody = isSingingBody;
    }

    public List<OrgSignVo> getOrgSigns() {
        return OrgSigns;
    }

    public void setOrgSigns(List<OrgSignVo> orgSigns) {
        OrgSigns = orgSigns;
    }

    public String getGroupCorp() {
        return GroupCorp;
    }

    public void setGroupCorp(String groupCorp) {
        GroupCorp = groupCorp;
    }

    public String getAssetCorp() {
        return AssetCorp;
    }

    public void setAssetCorp(String assetCorp) {
        AssetCorp = assetCorp;
    }

    public String getShareCorp() {
        return ShareCorp;
    }

    public void setShareCorp(String shareCorp) {
        ShareCorp = shareCorp;
    }

    public String getFlowDistributer() {
        return FlowDistributer;
    }

    public void setFlowDistributer(String flowDistributer) {
        FlowDistributer = flowDistributer;
    }

    public Integer getFinalDay() {
        return FinalDay;
    }

    public void setFinalDay(Integer finalDay) {
        FinalDay = finalDay;
    }
}
