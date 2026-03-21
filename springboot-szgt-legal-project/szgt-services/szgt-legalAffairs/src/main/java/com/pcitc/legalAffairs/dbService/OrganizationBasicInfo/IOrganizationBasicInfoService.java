package com.pcitc.legalAffairs.dbService.OrganizationBasicInfo;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoQueryBo;
import com.pcitc.legalAffairs.po.Organization.OrganizationBasicInfo;
import com.pcitc.legalAffairs.vo.person.FwExcelOrganVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IOrganizationBasicInfoService extends IBaseService<OrganizationBasicInfo> {
    public Result queryOrganizationBasicInfo(Integer id);

    /**
     * 根据查询条件分页查询法律机构基本信息
     * @param organizationBasicInfoQueryBo
     * @return
     */
    public Result queryOrganizationBasicInfoGlobalPage(OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo);
    public Result queryOrganizationBasicInfoManagePage(OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo);
    public Result deleteOrganizationBasicInfo(Integer id);
    public Result updateOrganizationBasicInfo(OrganizationBasicInfoBo organizationBasicInfoBo);
    public Result saveOrganizationBasicInfo( OrganizationBasicInfoBo organizationBasicInfoBo);
    public List<FwExcelOrganVo> exportExcel();

}
