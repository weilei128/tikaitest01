package com.oo.system.controller;

import java.util.Arrays;
import com.oo.common.core.domain.R;
import com.oo.common.core.web.domain.TranslateSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.service.ISysTranslateService;
import com.oo.common.core.web.controller.BaseController;

/**
 * 多语言配置Controller
 *
 * @author oo
 * @date 2023-08-09
 */
@AllArgsConstructor
@RestController
@RequestMapping("/translate")
@Api(value = "多语言配置", tags = "多语言配置接口")
public class SysTranslateController extends BaseController
{
    private final ISysTranslateService sysTranslateService;

//    /**
//     * 列表
//     */
//    @RequiresPermissions("system:translate:list")
//    @GetMapping("/list")
//    @ApiOperationSupport(order = 1)
//    @ApiOperation(value = "查询多语言配置列表", notes = "传入sysTranslate")
//    public TableDataInfo list(SysTranslate sysTranslate)
//    {
//        startPage();
//        List<SysTranslate> list = sysTranslateService.selectSysTranslateList(sysTranslate);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出
//     */
//    @RequiresPermissions("system:translate:export")
//    @Log(title = "多语言配置", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ApiOperationSupport(order = 2)
//    @ApiOperation(value = "导出多语言配置列表", notes = "传入sysTranslate")
//    public void export(HttpServletResponse response, SysTranslate sysTranslate)
//    {
//        List<SysTranslate> list = sysTranslateService.selectSysTranslateList(sysTranslate);
//        ExcelUtil<SysTranslate> util = new ExcelUtil<SysTranslate>(SysTranslate.class);
//        util.exportExcel(response, list, "多语言配置数据");
//    }
//
//    /**
//     * 详细信息
//     */
//    @RequiresPermissions("system:translate:query")
//    @GetMapping(value = "/{fieldId}")
//    @ApiOperationSupport(order = 3)
//    @ApiOperation(value = "查询多语言配置详情", notes = "传入fieldId")
//    public R<SysTranslate> getInfo(@PathVariable("fieldId") Long fieldId)
//    {
//        return R.ok(sysTranslateService.getById(fieldId));
//    }

    /**
     * 新增
     */
    @RequiresPermissions("system:translate:add")
    @Log(title = "多语言配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增多语言配置", notes = "传入sysTranslate")
    public R add(@RequestBody SysTranslate sysTranslate)
    {
        if (sysTranslateService.save(sysTranslate)) {
            sysTranslateService.resetTransCacheByCategory(sysTranslate.getCategory());
            return R.ok();
        }
        return R.fail();
    }
//
//    /**
//     * 修改
//     */
//    @RequiresPermissions("system:translate:edit")
//    @Log(title = "多语言配置", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperationSupport(order = 5)
//    @ApiOperation(value = "修改多语言配置", notes = "传入sysTranslate")
//    public R edit(@RequestBody SysTranslate sysTranslate)
//    {
//        return R.status(sysTranslateService.updateById(sysTranslate));
//    }

//    /**
//     * 删除
//     */
//    @RequiresPermissions("system:translate:remove")
//    @Log(title = "多语言配置", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{fieldIds}")
//    @ApiOperationSupport(order = 6)
//    @ApiOperation(value = "删除多语言配置", notes = "传入fieldIds")
//    public R remove(@PathVariable String[] fieldIds)
//    {
//        return R.status(sysTranslateService.remove(Arrays.asList(fieldIds)));
//    }

    /**
     * 菜单多语言保存（feign用）
     */
    @Log(title = "菜单多语言保存", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "菜单多语言保存", notes = "传入sysTranslate")
    public boolean submit(@RequestBody TranslateSubmitVo translateSubmitVo)
    {
        return sysTranslateService.submit(translateSubmitVo.getFieldId(),translateSubmitVo.getVoList());
    }
    /**
     * 菜单多语言删除（feign用）
     */
    @Log(title = "菜单多语言删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "菜单多语言删除", notes = "传入fieldIds")
    public int remove1(@RequestBody String[] Id  )
    {
        return sysTranslateService.remove(Arrays.asList(Id)) ? 1 : 0;
    }

}
