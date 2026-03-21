package com.pcitc.legalAffairs.controller.Organization;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Organization.OrganizationWrokRewardBo;
import com.pcitc.legalAffairs.dbService.OrganizationWrokReward.IOrganizationWrokRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organizationWrokReward")
public class OrganizationWrokRewardController {
    @Autowired
    private IOrganizationWrokRewardService organizationWrokRewardService;
    @PostMapping("queryOrganizationWrokReward")
    public Result queryOrganizationWrokReward(@RequestParam Integer id){
        return organizationWrokRewardService.queryOrganizationWrokReward(id);
    }
    @PostMapping("queryOrganizationWrokRewardList")
    public Result queryOrganizationWrokRewardList(@RequestParam Integer lawFirmID){
        return organizationWrokRewardService.queryOrganizationWrokRewardList(lawFirmID);
    }
    @PostMapping("queryOrganizationWrokRewardPage")
    public Result queryOrganizationWrokRewardPage(@RequestParam Integer pageIndex,@RequestParam Integer pageSize,@RequestParam Integer lawFirmID){
        return organizationWrokRewardService.queryOrganizationWrokRewardPage(pageIndex,pageSize,lawFirmID);
    }
    @PostMapping("deleteOrganizationWrokReward")
    public Result deleteOrganizationWrokReward(@RequestParam Integer id){
        return organizationWrokRewardService.deleteOrganizationWrokReward(id);
    }
    @PostMapping("updateOrganizationWrokReward")
    public Result updateOrganizationWrokReward(@RequestBody OrganizationWrokRewardBo organizationWrokRewardBo){
        return organizationWrokRewardService.updateOrganizationWrokReward(organizationWrokRewardBo);
    }
    @PostMapping("saveOrganizationWrokReward")
    public Result saveOrganizationWrokReward(@RequestBody OrganizationWrokRewardBo organizationWrokRewardBo){
        return organizationWrokRewardService.saveOrganizationWrokReward(organizationWrokRewardBo);
    }
}
