package com.pcitc.system.dbService;

import com.pcitc.common.entity.Result;
import com.pcitc.system.bo.SysAttachmentBo;
import com.pcitc.system.bo.SysAttachmentQueryBo;
import com.pcitc.system.po.SysAttachment;
import com.pcitc.szgt.legalAffairs.base.IBaseMapper;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.io.IOException;

public interface SysAttachmentService extends IBaseService<SysAttachment> {
    /**
     * 添加或修改附件
     * @param sysAttachmentBo
     * @return
     */
    public Result saveOrUpdate(SysAttachmentBo sysAttachmentBo) throws Exception;

    /**
     * 根据主键删除附件信息
     * @param fId
     * @return
     */
    public Result deleteById(Integer fId);

    /**
     * 根据主键查询附件信息
     * @param fId
     * @return
     */
    public Result queryById(Integer fId );

    /**
     * 根据条件查询附件信息列表
     * @param sysAttachmentQueryBo
     * @return
     */
    public Result queryForList(SysAttachmentQueryBo sysAttachmentQueryBo);

    /**
     * 根据条件查询附件信息并进行分页处理
     * @param sysAttachmentQueryBo
     * @return
     */
    public Result queryForPage(SysAttachmentQueryBo sysAttachmentQueryBo );
}