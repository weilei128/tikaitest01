package com.oo.system.controller;

import java.util.Arrays;
import java.util.List;

import com.oo.common.core.domain.R;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.system.domain.SysBussnessWdp;
import com.oo.system.service.ISysBussnessWdpService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 业务域与WDP权限关系映射（通过最后一级做映射）Controller
 *
 * @author oo
 * @date 2023-07-21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/bussnesswdp")
@Api(value = "sys-bussnesswdp-controller", tags = "")
public class SysBussnessWdpController extends BaseController
{
    private final ISysBussnessWdpService sysBussnessWdpService;

    /**
     * 列表
     */
    @Log(title = "查询业务域", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public TableDataInfo list(SysBussnessWdp sysBussnessWdp)
    {
        startPage();
        List<SysBussnessWdp> list = sysBussnessWdpService.selectSysBussnessWdpList(sysBussnessWdp);
        return getDataTable(list);
    }

    /**
     * 导出
     *//*
    @RequiresPermissions("project:wdp:export")
    @Log(title = "业务域与WDP权限关系映射（通过最后一级做映射）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "export", notes = "传入sysBussnessWdp")
    public void export(HttpServletResponse response, SysBussnessWdp sysBussnessWdp)
    {
        List<SysBussnessWdp> list = sysBussnessWdpService.selectSysBussnessWdpList(sysBussnessWdp);
        ExcelUtil<SysBussnessWdp> util = new ExcelUtil<SysBussnessWdp>(SysBussnessWdp.class);
        util.exportExcel(response, list, "业务域与WDP权限关系映射（通过最后一级做映射）数据");
    }*/

    /**
     * 详细信息
     */
 /*   @RequiresPermissions("project:wdp:query")
    @GetMapping(value = "/{id}")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "getInfo", notes = "传入id")
    public R<SysBussnessWdp> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(sysBussnessWdpService.getById(id));
    }*/

    /**
     * 新增
     */
    @Log(title = "添加", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody List<SysBussnessWdp> sysBussnessWdp)
    {
        for(SysBussnessWdp item:sysBussnessWdp){
            sysBussnessWdpService.deleteSysBussnessWdpById(item.getWdpId());
        }
        int a = sysBussnessWdpService.insertSysBussnessWdp(sysBussnessWdp);
        if(a > 0){
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    /**
     * 修改
     */
/*    @RequiresPermissions("project:wdp:edit")
    @Log(title = "业务域与WDP权限关系映射（通过最后一级做映射）", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "edit", notes = "传入sysBussnessWdp")
    public R edit(@RequestBody SysBussnessWdp sysBussnessWdp)
    {
        return R.status(sysBussnessWdpService.updateById(sysBussnessWdp));
    }*/

    /**
     * 删除
     */
/*    @RequiresPermissions("project:wdp:remove")
    @Log(title = "业务域与WDP权限关系映射（通过最后一级做映射）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "remove", notes = "传入ids")
    public R remove(@PathVariable Long[] ids)
    {
        return R.status(sysBussnessWdpService.removeByIds(Arrays.asList(ids)));
    }*/
}
