package com.oo.system.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.oo.common.core.domain.DictDataSelect;
import com.oo.system.service.ISysDictDataService;
import com.oo.system.service.ISysDictTypeService;
import com.oo.system.wrapper.SysTranslateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.common.security.utils.SecurityUtils;
import com.oo.system.api.domain.SysDictData;

/**
 * 数据字典信息
 *
 * @author
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
//    @RequiresPermissions("system:dict:query")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam Long dictCode, @RequestParam String dictType) throws NoSuchFieldException, IllegalAccessException {
        SysDictData dictData = dictDataService.selectDictDataById(dictCode);
        return AjaxResult.success(SysTranslateWrapper.build().entityVO(dictCode.toString(), dictType, dictData));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }


    @Log(title = "字典数据通过type和明文查id", businessType = BusinessType.OTHER)
    @PostMapping("/dictDataSelectValue")
    public String dictDataSelectValue(@RequestBody DictDataSelect dictDataSelect)
    {
        return dictDataService.dictDataSelectValue(dictDataSelect.getDictType(),dictDataSelect.getDictLabel());
    }

    @Log(title = "字典数据通过type和ID查明文", businessType = BusinessType.OTHER)
    @PostMapping("/dictDataSelectLabel")
    public String dictDataSelectLabel(@RequestBody DictDataSelect dictDataSelect)
    {
        return dictDataService.selectDictLabel(dictDataSelect.getDictType(),dictDataSelect.getDictValue());
    }
}
