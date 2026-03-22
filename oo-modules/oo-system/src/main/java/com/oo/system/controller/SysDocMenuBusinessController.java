package com.oo.system.controller;

import com.oo.common.core.domain.R;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.service.ISysDocMenuBusinessService;
import com.oo.system.wrapper.SysTranslateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docmenubusiness")
public class SysDocMenuBusinessController extends BaseController{

    @Autowired(required = false)
    private ISysDocMenuBusinessService docMenuBusinessService;

 /*   @Log(title = "查询业务域", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysDocMenuBusiness sysDocMenuBusiness)
    {
        startPage();
        List<SysDocMenuBusiness> list = docMenuBusinessService.selectDocMenuBusinessList(sysDocMenuBusiness);
        return getDataTable(list);

    }*/

    /**
     * 列表
     */
    @Log(title = "根据文件类型名称查询", businessType = BusinessType.OTHER)
    @PostMapping("/getNameList")
    public R<List<TreeSelect>> getNameList(String business_name)
    {
        return R.ok(docMenuBusinessService.selectDocMenuBusinessNameList(business_name));
    }

    /**
     * 新增
     */
    @Log(title = "添加", businessType = BusinessType.OTHER)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysDocMenuBusiness sysDocMenuBusiness)
    {
        int a = docMenuBusinessService.insertDocMenuBusiness(sysDocMenuBusiness);
        if(a > 0){
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    /**
     * 修改
     */
    @Log(title = "修改", businessType = BusinessType.OTHER)
    @PutMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysDocMenuBusiness sysDocMenuBusiness)
    {
        int a = docMenuBusinessService.updateDocMenuBusiness(sysDocMenuBusiness);
        if(a > 0){
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    /**
     * 删除
     */
    @Log(title = "删除", businessType = BusinessType.OTHER)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long[] id)
    {
        return toAjax(docMenuBusinessService.deleteDocMenuBusinessByIds(id));
    }

    /**
     * 根据部门编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) throws NoSuchFieldException, IllegalAccessException {
        SysDocMenuBusiness sysDocMenuBusiness = docMenuBusinessService.getInfo(id);
        return AjaxResult.success(SysTranslateWrapper.build().entityVO(id.toString(), "docMenuBusiness", sysDocMenuBusiness));
    }
}
