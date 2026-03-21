package com.pcitc.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.pcitc.common.exception.CustomException;
import com.pcitc.system.bo.RpBo;
import com.pcitc.system.dbService.SysMenuService;
import com.pcitc.system.dbService.SysPermissionsService;
import com.pcitc.system.dbService.SysRoleService;
import com.pcitc.system.po.SysMenu;
import com.pcitc.system.po.SysPermissions;
import com.pcitc.system.po.SysRole;
import com.pcitc.system.vo.MenuInfoVo;
import com.pctic.common.utils.UUIDUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/***
 * @description
 * @author leigang
 * @date 2020年2月19日 16:28:37
 *
 */
@Service
public class RolePermissionService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysPermissionsService sysPermissionsService;


    public List<MenuInfoVo> queryMenuByRoleId(String roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        if (sysRole == null) {
            throw new CustomException("角色不存在或者已经删除", 500);
        }
        LambdaQueryWrapper<SysPermissions> queryWrapper = sysPermissionsService.lambdaQueryWrapper();
        queryWrapper.eq(SysPermissions::getFkRoleId, roleId);
        List<SysPermissions> permissionsList = sysPermissionsService.list(queryWrapper);
        return permissionsList.stream().map(sysPermissions ->
                filterInfo(sysPermissions.getFkContentId(), sysPermissions.getFkContentName()))
                .collect(Collectors.toList());
    }

    private MenuInfoVo filterInfo(Integer menuId, String menuName) {
        return new MenuInfoVo(menuId, menuName);
    }

    /***
     * 对相关角色添加指定的菜单权限或者功能权限
     * @param rpBo
     */
    public void addRoleAuth(RpBo rpBo) {
        if (rpBo == null) {
            throw new CustomException();
        }
        List<String> addMenuIds = rpBo.getAddMenuIds();
        List<String> deleteMenuIds = rpBo.getDeleteMenuIds();
        String roleId = rpBo.getRoleId();
        SysRole sysRole = sysRoleService.getById(roleId);
        if (sysRole == null) {
            throw new CustomException("角色不存在或者已经删除", 500);
        }
        List<SysMenu> listByIds = (List<SysMenu>) sysMenuService.listByIds(addMenuIds);
        List<SysPermissions> sysPermissionsList = new ArrayList<>();
        listByIds.forEach(sysMenu -> {
            SysPermissions sysPermissions = new SysPermissions();
            sysPermissions.setFkContentId(sysMenu.getfId());
            sysPermissions.setFkContentName(sysMenu.getfName());
            sysPermissions.setFkRoleId(Integer.parseInt(roleId));
            sysPermissions.setFkRoleName(sysRole.getfName());
            sysPermissions.setfCode(UUIDUtils.getUUID());
            sysPermissionsList.add(sysPermissions);
        });
        sysPermissionsService.saveBatch(sysPermissionsList);

        //删除原来的权限
        if (CollectionUtils.isEmpty(deleteMenuIds)) {
            return;
        }
        LambdaQueryWrapper<SysPermissions> queryWrapper =
                sysPermissionsService.lambdaQueryWrapper();
        queryWrapper.in(SysPermissions::getFkContentId, deleteMenuIds);
        sysPermissionsService.remove(queryWrapper);
    }

}
