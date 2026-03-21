package com.pcitc.szgt.contract.attachment.service;

import com.pcitc.szgt.contract.attachment.model.vo.AttachmentQueryVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentUploadVo;
import com.pcitc.szgt.contract.common.DataResult;

import java.util.List;

public interface AttachmentService {

    /**
     * 附件上传
     * @param attachmentUploadVo
     * @return
     */
    public DataResult<AttachmentResultVo> uploadFile(AttachmentUploadVo attachmentUploadVo);

    /**
     * 附件上传并返回附件详情
     * @param attachmentUploadVo
     * @return
     */
    public DataResult<AttachmentResultVo> uploadFileReturnDetail(AttachmentUploadVo attachmentUploadVo);

    /**
     * 获取指定propertyId下未删除附件
     * @param queryVo
     * @return
     */
    public DataResult<List<AttachmentResultVo>> queryMultiAttachment(AttachmentQueryVo queryVo);

    /**
     * 获取指定propertyId下所有的附件
     * @return
     */
    public DataResult<List<AttachmentResultVo>> queryAllAttachment(AttachmentQueryVo queryVo);

    /**
     * 获取指定propertyId下的第一个附件
     * @param queryVo
     * @return
     */
    public DataResult<List<AttachmentResultVo>> queryOneAttachment(AttachmentQueryVo queryVo);

    /**
     * 根据附件id查询
     * @param attachmentId
     * @return
     */
    public AttachmentResultVo selectById(String attachmentId);

    /**
     * 删除附件
     * @param attachmentId
     * @return
     */
    DataResult deleteAttachment(String attachmentId);

    /**
     * 附件排序
     * @param attIds
     * @return
     */
    DataResult orderAttachment(List<String> attIds);
}
