package com.oo.system.controller;

import com.oo.common.core.domain.R;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.service.ISysDocMenuWdpService;
import com.oo.system.wrapper.SysTranslateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docmenuwdp")
public class SysDocMenuWdpController extends BaseController {


    @Autowired(required = false)
    private ISysDocMenuWdpService sysDocMenuWdpService;

  /*  @Log(title = "查询业务域", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysDocMenuWdp sysDocMenuWdp)
    {
        startPage();
        List<SysDocMenuWdp> list = sysDocMenuWdpService.selectSysDocMenuWdpList(sysDocMenuWdp);
        return getDataTable(list);

    }*/

    /**
     * 列表
     */
    @Log(title = "根据文件类型名称查询", businessType = BusinessType.OTHER)
    @PostMapping("/getNameList")
    public R<List<TreeSelect>> getNameList(String wdpName)
    {
        return R.ok(sysDocMenuWdpService.selectSysDocMenuWdpNameList(wdpName));
    }

/*    *//**
     * 加载对应角色部门列表树
     *//*
    @PostMapping("/roleDeptTreeselect")
    public AjaxResult roleDeptTreeselect()
    {
        List<SysDocMenuWdp> depts = sysDocMenuWdpService.selectSysDocMenuWdpList(new SysDocMenuWdp());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("depts", sysDocMenuWdpService.buildDeptTreeSelect(depts));
        return ajax;
    }*/

    /**
     * 新增
     */
    @Log(title = "添加", businessType = BusinessType.OTHER)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysDocMenuWdp sysDocMenuWdp)
    {
        int a = sysDocMenuWdpService.insertDocMenuWdp(sysDocMenuWdp);
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
    public AjaxResult edit(@Validated @RequestBody SysDocMenuWdp sysDocMenuWdp)
    {
        int a = sysDocMenuWdpService.updateDocMenuWdp(sysDocMenuWdp);
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
        return toAjax(sysDocMenuWdpService.deleteDocMenuWdpByIds(id));
    }

    /**
     * 查询详情
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) throws NoSuchFieldException, IllegalAccessException {
        SysDocMenuWdp sysDocMenuWdp = sysDocMenuWdpService.getInfo(id);
        return AjaxResult.success(SysTranslateWrapper.build().entityVO(id.toString(), "docMenuWdp", sysDocMenuWdp));
    }
}
