package com.pcitc.legalAffairs.controller.Organization;

import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoBo;
import com.pcitc.legalAffairs.bo.Organization.OrganizationBasicInfoQueryBo;
import com.pcitc.legalAffairs.dbService.OrganizationBasicInfo.IOrganizationBasicInfoService;
import com.pcitc.legalAffairs.easypoi.utils.OfficeExportUtil;
import com.pcitc.legalAffairs.vo.person.FwExcelOrganVo;
import com.pcitc.legalAffairs.vo.person.FwExcelPersonVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pcitc.common.entity.Result;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("organizationBasicInfo")
public class OrganizationBasicInfoController {
    @Autowired
    private IOrganizationBasicInfoService organizationBasicInfoService;

    @PostMapping("queryOrganizationBasicInfo")
    public Result queryOrganizationBasicInfo(@RequestParam Integer id) {
        return organizationBasicInfoService.queryOrganizationBasicInfo(id);
    }

    /**
     * 法律机构管理分页查询
     * @param organizationBasicInfoQueryBo
     * @return
     */
    @PostMapping("queryOrganizationBasicInfoManagePage")
    public Result queryOrganizationBasicInfoManagePage(@RequestBody OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo){
        return organizationBasicInfoService.queryOrganizationBasicInfoManagePage(organizationBasicInfoQueryBo);
    }

    /**
     * 法律机构信息综合查询
     * @param organizationBasicInfoQueryBo
     * @return
     */
    @PostMapping("queryOrganizationBasicInfoGlobalPage")
    public Result queryOrganizationBasicInfoGlobalPage(@RequestBody OrganizationBasicInfoQueryBo organizationBasicInfoQueryBo){
        return organizationBasicInfoService.queryOrganizationBasicInfoGlobalPage(organizationBasicInfoQueryBo);
    }
    @PostMapping("deleteOrganizationBasicInfo")
    public Result deleteOrganizationBasicInfo(@RequestParam Integer id) {
        return organizationBasicInfoService.deleteOrganizationBasicInfo(id);
    }

    @PostMapping("updateOrganizationBasicInfo")
    public Result updateOrganizationBasicInfo(@RequestBody OrganizationBasicInfoBo organizationBasicInfoBo) {
        return organizationBasicInfoService.updateOrganizationBasicInfo(organizationBasicInfoBo);
    }
    @PostMapping("saveOrganizationBasicInfo")
    public Result saveOrganizationBasicInfo(@RequestBody OrganizationBasicInfoBo organizationBasicInfoBo) {
        return organizationBasicInfoService.saveOrganizationBasicInfo(organizationBasicInfoBo);
    }

    /**
     * 法律机构管理分页查询
     * @param organizationBasicInfoQueryBo
     * @return
     */
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<FwExcelOrganVo> voList = organizationBasicInfoService.exportExcel();
        String fileName = "法律机构导出";
        Workbook workbook = OfficeExportUtil.getWorkbook("法律机构导出", "Sheet 1", FwExcelOrganVo.class, voList);
        OfficeExportUtil.exportExcel(workbook, "法律机构导出", response);
    }
}
