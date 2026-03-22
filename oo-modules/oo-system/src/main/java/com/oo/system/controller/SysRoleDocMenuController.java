package com.oo.system.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import com.oo.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.system.domain.SysRoleDocMenu;
import com.oo.system.service.ISysRoleDocMenuService;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.utils.poi.ExcelUtil;
import com.oo.common.core.web.page.TableDataInfo;

/**
 * 文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型Controller
 * 
 * @author oo
 * @date 2023-07-21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/roledocmenu")
@Api(value = "sys-roledocmenu-controller", tags = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型接口")
public class SysRoleDocMenuController extends BaseController
{
    private final ISysRoleDocMenuService sysRoleDocMenuService;

    /**
     * 列表
     */
      @RequiresPermissions("system:menu:list")
      @GetMapping("/list")
      @ApiOperationSupport(order = 1)
      @ApiOperation(value = "list", notes = "传入sysRoleDocMenu")
      public TableDataInfo list(SysRoleDocMenu sysRoleDocMenu)
      {
          startPage();
          List<SysRoleDocMenu> list = sysRoleDocMenuService.selectSysRoleDocMenuList(sysRoleDocMenu);
          return getDataTable(list);
      }

//    /**
//     * 导出
//     */
//    @RequiresPermissions("system:menu:export")
//    @Log(title = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ApiOperationSupport(order = 2)
//    @ApiOperation(value = "export", notes = "传入sysRoleDocMenu")
//    public void export(HttpServletResponse response, SysRoleDocMenu sysRoleDocMenu)
//    {
//        List<SysRoleDocMenu> list = sysRoleDocMenuService.selectSysRoleDocMenuList(sysRoleDocMenu);
//        ExcelUtil<SysRoleDocMenu> util = new ExcelUtil<SysRoleDocMenu>(SysRoleDocMenu.class);
//        util.exportExcel(response, list, "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型数据");
//    }
//
//    /**
//     * 详细信息
//     */
//    @RequiresPermissions("system:menu:query")
//    @GetMapping(value = "/{roleId}")
//    @ApiOperationSupport(order = 3)
//    @ApiOperation(value = "getInfo", notes = "传入roleId")
//    public R<SysRoleDocMenu> getInfo(@PathVariable("roleId") Long roleId)
//    {
//        return R.ok(sysRoleDocMenuService.getById(roleId));
//    }
//
//    /**
//     * 新增
//     */
//    @RequiresPermissions("system:menu:add")
//    @Log(title = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型", businessType = BusinessType.INSERT)
//    @PostMapping
//    @ApiOperationSupport(order = 4)
//    @ApiOperation(value = "add", notes = "传入sysRoleDocMenu")
//    public R add(@RequestBody SysRoleDocMenu sysRoleDocMenu)
//    {
//        return R.status(sysRoleDocMenuService.save(sysRoleDocMenu));
//    }
//
//    /**
//     * 修改
//     */
//    @RequiresPermissions("system:menu:edit")
//    @Log(title = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperationSupport(order = 5)
//    @ApiOperation(value = "edit", notes = "传入sysRoleDocMenu")
//    public R edit(@RequestBody SysRoleDocMenu sysRoleDocMenu)
//    {
//        return R.status(sysRoleDocMenuService.updateById(sysRoleDocMenu));
//    }
//
//    /**
//     * 删除
//     */
//    @RequiresPermissions("system:menu:remove")
//    @Log(title = "文件类型配置权限-配置业务域的文件权限，可通过映射关系找到wdp的文件类型", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{roleIds}")
//    @ApiOperationSupport(order = 6)
//    @ApiOperation(value = "remove", notes = "传入roleIds")
//    public R remove(@PathVariable Long[] roleIds)
//    {
//        return R.status(sysRoleDocMenuService.removeByIds(Arrays.asList(roleIds)));
//    }
}
