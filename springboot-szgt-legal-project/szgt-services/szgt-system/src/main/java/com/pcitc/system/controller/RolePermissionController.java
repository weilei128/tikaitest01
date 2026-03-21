package com.pcitc.system.controller;

import com.pcitc.system.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @description 角色权限关联接口
 * @author leigang
 * @date 2020年2月19日 16:27:46
 *
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {

    private RolePermissionService rolePermissionService;

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

}
