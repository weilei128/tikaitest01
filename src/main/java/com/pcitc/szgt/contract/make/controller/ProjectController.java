package com.pcitc.szgt.contract.make.controller;


import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.entity.CrProjectinfo;
import com.pcitc.szgt.contract.make.modelEx.ProjectInfo;
import com.pcitc.szgt.contract.make.service.ICrProjectinfoService;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>项目管理</p>
 * @author ziran.zhou
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/make/project")
public class ProjectController {
    @Autowired
    private ICrProjectinfoService iCrProjectinfoService;

    /**
     * 	项目新增
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public DataResult addAccord(@RequestBody ProjectInfo projectInfo) {
        return DataResult.success(iCrProjectinfoService.addProject(projectInfo));
    }

    /**
     * 	项目修改
     */
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public DataResult updateProject(@RequestBody ProjectInfo projectInfo) {
        return DataResult.success(iCrProjectinfoService.updateProject(projectInfo));
    }

    /**
     * 	项目删除
     */
    @RequestMapping(value = "/delProject", method = RequestMethod.POST)
    public DataResult delProject(@RequestParam(required = true) String projectId) {
        return DataResult.success(iCrProjectinfoService.delProject(projectId));
    }

    
    @ApiOperation(value="根据ID获取项目详细信息")
    @ApiImplicitParam(name = "projectId",value = "项目ID",required = true,dataType = "字符串")
    @RequestMapping(value = "/getProjectInfoById", method = RequestMethod.GET)
    public DataResult getProjectInfoById(@RequestParam(required = true) String projectId) {
        return iCrProjectinfoService.getProjectInfoById(projectId);
    }

    /**
     * 	项目查询
     */
    @RequestMapping(value = "/selectProjectInfo", method = RequestMethod.GET)
    public DataResult selectProjectInfo(@RequestParam(required = false) String projectName, @RequestParam(required = false) String atYear,
                                        @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize,
                                        @RequestParam(required = false) Integer isValid) {
        return iCrProjectinfoService.selectProjectInfo(projectName, atYear, pageNum, pageSize, isValid);
    }
}
