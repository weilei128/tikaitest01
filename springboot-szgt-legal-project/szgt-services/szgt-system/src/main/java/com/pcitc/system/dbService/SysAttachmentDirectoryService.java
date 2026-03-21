package com.pcitc.system.dbService;

import com.pcitc.common.entity.Result;
import com.pcitc.system.bo.SysAttachmentDirectoryBo;
import com.pcitc.system.bo.SysAttachmentDirectoryQueryBo;
import com.pcitc.system.po.SysAttachmentDirectory;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface SysAttachmentDirectoryService extends IBaseService<SysAttachmentDirectory> {
    /**
     * 根据主键查讯系统附件目录
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据分类名称查讯系统附件目录并分页
     * @param sysAttachmentDirectoryQueryBo
     * @return
     */
    public Result queryPageByParentName(SysAttachmentDirectoryQueryBo sysAttachmentDirectoryQueryBo);

    /**
     * 添加系统附件目录信息
     * @param sysAttachmentDirectoryBo
     * @return
     */
    public Result save(SysAttachmentDirectoryBo sysAttachmentDirectoryBo);

    /**
     * 根据主键批量删除系统附件目录信息
     * @param ids
     * @return
     */
    public Result deleteBatch(List<Long> ids);

    /**
     * 根据主键更新系统附件目录信息
     * @param sysAttachmentDirectoryBo
     * @return
     */
    public Result updateById(SysAttachmentDirectoryBo sysAttachmentDirectoryBo);
}
