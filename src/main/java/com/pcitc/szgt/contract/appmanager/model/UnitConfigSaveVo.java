package com.pcitc.szgt.contract.appmanager.model;

import java.util.List;

public class UnitConfigSaveVo {

    /**
     * 单位配置id
     */
    private String OrgConfigID;

    /**
     * 集团签约主体-法定代表人id
     */
    private String GroupCorp;

    /**
     * 资产签约主体-法定代表人id
     */
    private String AssetCorp;

    /**
     * 股份签约主体法定代表人id
     */
    private String ShareCorp;

    /**
     * 流程分发员
     */
    private String FlowDistributer;

    /**
     * 组织机构id
     */
    private Integer OrgID;

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

    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer orgID) {
        OrgID = orgID;
    }

    public Integer getIsSingingBody() {
        return IsSingingBody;
    }

    public void setIsSingingBody(Integer isSingingBody) {
        IsSingingBody = isSingingBody == null ? 0 : isSingingBody;
    }

    public List<OrgSignVo> getOrgSigns() {
        return OrgSigns;
    }

    public void setOrgSigns(List<OrgSignVo> orgSigns) {
        OrgSigns = orgSigns;
    }

    public Integer getFinalDay() {
        return FinalDay;
    }

    public void setFinalDay(Integer finalDay) {
        FinalDay = finalDay;
    }
}
