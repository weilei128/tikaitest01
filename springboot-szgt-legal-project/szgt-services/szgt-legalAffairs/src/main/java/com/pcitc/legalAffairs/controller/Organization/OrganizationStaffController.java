package com.pcitc.legalAffairs.controller.Organization;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Organization.OrganizationStaffBo;
import com.pcitc.legalAffairs.dbService.OrganizationStaff.IOrganizationStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organizationStaff")
public class OrganizationStaffController {
    @Autowired
    private IOrganizationStaffService organizationStaffService;
    @PostMapping("queryOrganizationStaff")
    public Result queryOrganizationStaff(@RequestParam Integer id){
        return organizationStaffService.queryOrganizationStaff(id);
    }
    @PostMapping("queryOrganizationStaffList")
    public Result queryOrganizationStaffList(@RequestParam Integer lawFirmID){
        return organizationStaffService.queryOrganizationStaffList(lawFirmID);
    }
    @PostMapping("queryOrganizationStaffPage")
    public Result queryOrganizationStaffPage(@RequestParam Integer pageIndex,@RequestParam Integer pageSize,@RequestParam Integer lawFirmID){
        return organizationStaffService.queryOrganizationStaffPage(pageIndex,pageSize,lawFirmID);
    }
    @PostMapping("deleteOrganizationStaffBatch")
    public Result deleteOrganizationStaffBatch(@RequestBody List<Integer> ids) {
        return organizationStaffService.deleteOrganizationStaffBatch(ids);
    }
    @PostMapping("deleteOrganizationStaff")
    public Result deleteOrganizationStaff(@RequestParam Integer id){
        return organizationStaffService.deleteOrganizationStaff(id);
    }
    @PostMapping("updateOrganizationStaff")
    public Result updateOrganizationStaff(@RequestBody OrganizationStaffBo organizationStaffBo){
        return organizationStaffService.updateOrganizationStaff(organizationStaffBo);
    }
    @PostMapping("saveOrganizationStaff")
    public Result saveOrganizationStaff(@RequestBody OrganizationStaffBo organizationStaffBo){
        return organizationStaffService.saveOrganizationStaff(organizationStaffBo);
    }
}
