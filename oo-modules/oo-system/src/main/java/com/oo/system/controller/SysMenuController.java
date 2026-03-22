package com.oo.system.controller;

import java.util.List;

import com.oo.common.core.web.domain.SysMenu;
import com.oo.system.service.ISysMenuService;
import com.oo.system.wrapper.SysTranslateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.oo.common.core.constant.UserConstants;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.common.log.annotation.Log;
import com.oo.common.log.enums.BusinessType;
import com.oo.common.security.annotation.RequiresPermissions;
import com.oo.common.security.utils.SecurityUtils;

/**
 * 菜单信息
 *
 * @author
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return AjaxResult.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @RequiresPermissions("system:menu:query")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) throws NoSuchFieldException, IllegalAccessException {
        SysMenu menu = menuService.selectMenuById(menuId);
        return AjaxResult.success(SysTranslateWrapper.build().entityVO(menuId.toString(), "menu", menu));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        menus.stream().forEach((m) ->m.setType(0));
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 获取菜单下拉树列表
     */
    @PostMapping("/treeselects")
    public AjaxResult treeselects(@RequestBody List<SysMenu> menus)
    {
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /*
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(userId);
        menus.stream().forEach((m) ->m.setType(0));

        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuIds}")
    public AjaxResult remove(@PathVariable Long[] menuIds)
    {
        menuService.deleteChildByMenuId(menuIds);
        menuService.deleteMenuExistRole(menuIds);
        menuService.deleteBatchMenuById(menuIds);
        return AjaxResult.success();
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters(@RequestParam(value="site", required = false) String site)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> system_menus = menuService.selectMenuTreeByUserId(userId,"0", site);
//        List<SysMenu> front_menus = menuService.selectMenuTreeByUserId(userId,"1");
//        AjaxResult ajax = AjaxResult.success();
//        ajax.put("system-menus",menuService.buildMenus(system_menus));
//        ajax.put("front-menus",menuService.buildMenus(front_menus));
       // return ajax;
        return AjaxResult.success(menuService.buildMenus(system_menus));
    }

    /**
     * 获取前端路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getWebRouters")
    public AjaxResult getWebRouters(@RequestParam(value = "site", required = false) String site) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> system_menus = menuService.selectMenuTreeByUserId(userId, "1", site);
        return AjaxResult.success(menuService.buildMenus(system_menus));
    }
}
