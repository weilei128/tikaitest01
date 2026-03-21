package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.system.bo.SysAttachmentBo;
import com.pcitc.system.bo.SysAttachmentQueryBo;
import com.pcitc.system.dbService.SysAttachmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("sysAttachment")
public class SysAttachmentController {
    @Autowired
    private SysAttachmentService sysAttachmentService;

    @PostMapping("saveOrUpdate")
    @ApiOperation("添加或修改附件")
    public Result saveOrUpdate(@RequestBody SysAttachmentBo sysAttachmentBo) throws Exception {
        return sysAttachmentService.saveOrUpdate(sysAttachmentBo);
    }

    @PostMapping("deleteById")
    @ApiOperation("根据主键删除附件信息")
    public Result deleteById(@RequestParam Integer fId) {
        return sysAttachmentService.deleteById(fId);
    }

    @PostMapping("queryById")
    @ApiOperation("根据主键查询附件信息")
    public Result queryById(@RequestParam Integer fId) {
        return sysAttachmentService.queryById(fId);
    }

    @PostMapping("queryForList")
    @ApiOperation("根据条件查询附件信息列表")
    public Result queryForList(@RequestBody SysAttachmentQueryBo sysAttachmentQueryBo) {
        return sysAttachmentService.queryForList(sysAttachmentQueryBo);
    }

    @PostMapping("queryForPage")
    @ApiOperation("根据条件查询附件信息并进行分页处理")
    public Result queryForPage(@RequestBody SysAttachmentQueryBo sysAttachmentQueryBo) {
        return sysAttachmentService.queryForPage(sysAttachmentQueryBo);
    }
}
