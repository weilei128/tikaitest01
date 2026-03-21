package com.pcitc.szgt.contract.textmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.textmanage.entity.CrContracttextmodel;
import com.pcitc.szgt.contract.textmanage.model.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-02-20
 */
public interface ICrContracttextmodelService extends IService<CrContracttextmodel> {
    /**
     * 文本申请(第一版)
     * @param textApplyVo
     * @return
     */
    public DataResult textApply(TextApplyVo textApplyVo);

    /**
     * 文本编辑(第二版及以后)
     * @param textEditVo
     * @return
     */
    public DataResult textEdit(TextEditVo textEditVo);

    /**
     * 文本废弃
     * @return
     */
    public DataResult textDiscard(TextDiscardVo textDiscardVo);

    /**
     * 文本草稿删除
     * @return
     */
    public DataResult textDraftDel(TextDraftDelVo textDraftDelVo);

    /**
     * 文本查询
     * @param textQueryVo
     * @return
     */
    public DataResult<PageData<TextDetailResultVo>> textQuery(TextQueryVo textQueryVo);

    /**
     * 文本详情
     * @param textId
     * @return
     */
    public DataResult<TextDetailResultVo> textDetail(String textId);

    /**
     * 文本历史版本
     * @return
     */
    public DataResult<List<TextDetailResultVo>> historyList(String textId);
}
