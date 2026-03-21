package com.pcitc.system.controller;

import com.pcitc.common.entity.BaseEntity;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.system.bo.SysRoleBo;
import com.pcitc.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @description 角色管理
 * @author leigang
 * @date 2020年2月19日 11:26:36
 *
 */
@RestController
@RequestMapping("role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("分页查询角色列表")
    @PostMapping("queryRoleList")
    public Result queryRoleList(@RequestBody BaseEntity baseEntity) {
        return Result.data(roleService.queryRoleList(baseEntity));
    }

    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public Result addRole(@RequestBody SysRoleBo sysRoleBo) {
        roleService.addRole(sysRoleBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("更新角色信息")
    @PostMapping("updateRole")
    public Result updateRole(@RequestBody SysRoleBo sysRoleBo) {
        roleService.updateRole(sysRoleBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("批量删除")
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> batchIds) {
        roleService.deleteBatch(batchIds);
        return Result.success(ResultCode.SUCCESS);
    }


}
