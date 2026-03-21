package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.system.bo.SysAttachmentDirectoryBo;
import com.pcitc.system.bo.SysAttachmentDirectoryQueryBo;
import com.pcitc.system.dbService.SysAttachmentDirectoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sysAttachmentDirectory")
public class SysAttachmentDirectoryController {
    /**
     * 系统附件目录信息服务层
     */
    @Autowired
    private SysAttachmentDirectoryService sysAttachmentDirectoryService;

    /**
     * 根据主键查讯系统附件目录
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId) {
        return sysAttachmentDirectoryService.queryById(fId);
    }

    /**
     * 根据分类主键查讯系统附件目录并分页
     * @param sysAttachmentDirectoryQueryBo
     * @return
     */
    @PostMapping("queryPageByParentName")
    public Result queryPageByParentName(@RequestBody SysAttachmentDirectoryQueryBo sysAttachmentDirectoryQueryBo) {
        return sysAttachmentDirectoryService.queryPageByParentName(sysAttachmentDirectoryQueryBo);
    }

    /**
     * 添加系统附件目录信息
     * @param sysAttachmentDirectoryBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysAttachmentDirectoryBo sysAttachmentDirectoryBo) {
        return sysAttachmentDirectoryService.save(sysAttachmentDirectoryBo);
    }

    /**
     * 根据主键批量删除系统附件目录信息
     * @param ids
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        return sysAttachmentDirectoryService.deleteBatch(ids);
    }

    /**
     * 根据主键更新系统附件目录信息
     * @param sysAttachmentDirectoryBo
     * @return
     */
    @PostMapping("updateById")
    public Result updateById(@RequestBody SysAttachmentDirectoryBo sysAttachmentDirectoryBo) {
        return sysAttachmentDirectoryService.updateById(sysAttachmentDirectoryBo);
    }
}
