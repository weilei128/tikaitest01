package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.system.bo.SysMenuBo;
import com.pcitc.system.bo.SysMenuGroupBo;
import com.pcitc.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @description 菜单管理
 * @author leigang
 * @date 2020年2月19日 09:39:27
 *
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    private MenuService menService;

    @Autowired
    public void setMenService(MenuService menService) {
        this.menService = menService;
    }


    @PostMapping("addNote")
    public Result addNote(@RequestBody SysMenuBo sysMenuBo) {
        menService.addMenuNote(sysMenuBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("addMenuGroupNote")
    public Result addMenuGroupNote(@RequestBody SysMenuGroupBo sysMenuGroupBo) {
        menService.addMenuGroupNote(sysMenuGroupBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("updateNote")
    public Result updateNote(@RequestBody SysMenuBo sysMenuBo) {
        menService.updateMenuNote(sysMenuBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("deleteNote")
    public Result deleteNote(@RequestParam Integer noteId) {
        menService.deleteMenuNote(noteId);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("queryMenu")
    public Result queryMenu() {
        return Result.data(menService.queryMenuTree());
    }


}
