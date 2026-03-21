package com.pcitc.legalAffairs.dbService.OrganizationStaff;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Organization.OrganizationStaffBo;
import com.pcitc.legalAffairs.po.Organization.OrganizationStaff;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IOrganizationStaffService extends IBaseService<OrganizationStaff> {
    public Result queryOrganizationStaff(Integer id);

    /**
     * 根据法律机构信息id查询法律人员列表
     * @param lawFirmID
     * @return
     */
    public Result queryOrganizationStaffList(Integer lawFirmID);
    public Result queryOrganizationStaffPage(Integer pageIndex,Integer pageSize,Integer lawFirmID);
    public Result deleteOrganizationStaff(Integer id);

    /**
     * 根据主键批量删除
     * @param ids
     * @return
     */
    public Result deleteOrganizationStaffBatch(List<Integer> ids);
    public Result updateOrganizationStaff(OrganizationStaffBo organizationStaffBo);
    public Result saveOrganizationStaff(OrganizationStaffBo organizationStaffBo);
}
