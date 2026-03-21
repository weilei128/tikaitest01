package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.system.bo.SysOrganizationBo;
import com.pcitc.system.po.SysOrganization;
import com.pcitc.system.service.SystemService;
import com.pcitc.system.vo.SysOrganizationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/***
 * @description 系统管理接口
 * @author leigang
 * @date 2020年2月17日 11:00:27
 *
 */

@RestController
@RequestMapping("system")
public class SystemController {

    private SystemService systemService;

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @PostMapping("addNote")
    public Result addNote(@RequestBody SysOrganizationBo sysOrganizationBo) {
        systemService.addNote(sysOrganizationBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("updateNote")
    public Result updateNote(@RequestBody SysOrganizationBo sysOrganizationBo) {
        systemService.updateNote(sysOrganizationBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("deleteNote")
    public Result deleteNote(@RequestParam Integer noteId) {
        systemService.deleteNote(noteId);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("queryOrganization")
    public Result queryOrganization() {
        return Result.data(systemService.queryOrganization());
    }
}
