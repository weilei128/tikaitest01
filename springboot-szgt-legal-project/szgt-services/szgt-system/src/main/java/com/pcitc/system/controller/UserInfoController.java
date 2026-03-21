package com.pcitc.system.controller;

import com.pcitc.common.entity.BaseEntity;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.system.bo.SysRoleBo;
import com.pcitc.system.bo.SysUserInfoBo;
import com.pcitc.system.service.RoleService;
import com.pcitc.system.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @description 用户信息
 * @author leigang
 * @date 2020年2月19日 11:26:36
 *
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("queryUserInfoList")
    public Result queryUserInfoList(@RequestBody BaseEntity baseEntity) {
        return Result.data(userInfoService.queryUserInfoList(baseEntity));
    }

    @ApiOperation("添加用户信息")
    @PostMapping("addUserInfo")
    public Result addUserInfo(@RequestBody SysUserInfoBo sysUserInfoBo) {
        userInfoService.addUserInfo(sysUserInfoBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("updateUser")
    public Result updateUser(@RequestBody SysUserInfoBo sysUserInfoBo) {
        userInfoService.updateUser(sysUserInfoBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("更改用户密码")
    @PostMapping("updatePsw")
    public Result updatePsw(@RequestParam String userId, @RequestParam String psw) {
        userInfoService.updatePsw(userId, psw);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("批量删除")
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> batchIds) {
        userInfoService.deleteBatch(batchIds);
        return Result.success(ResultCode.SUCCESS);
    }


}
