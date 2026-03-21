package com.pcitc.legalAffairs.dbService.OrganizationWrokReward;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Organization.OrganizationWrokRewardBo;
import com.pcitc.legalAffairs.po.Organization.OrganizationWrokReward;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IOrganizationWrokRewardService extends IBaseService<OrganizationWrokReward> {
    public Result queryOrganizationWrokReward(Integer id);
    /**
     * 根据法律机构信息id查询获奖信息
     * @param lawFirmID
     * @return
     */
    public Result queryOrganizationWrokRewardList(Integer lawFirmID);

    /**
     * f分页查询获奖信息
     * @param lawFirmID
     * @return
     */
    public Result queryOrganizationWrokRewardPage(Integer pageIndex,Integer pageSize,Integer lawFirmID);
    public Result deleteOrganizationWrokReward(Integer id);
    public Result updateOrganizationWrokReward(OrganizationWrokRewardBo organizationWrokRewardBo);
    public Result saveOrganizationWrokReward(OrganizationWrokRewardBo organizationWrokRewardBo);
}
