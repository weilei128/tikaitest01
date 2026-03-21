package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryMarketEvalBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryMarketEval;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IFwIntermediaryMarketEvalService extends IBaseService<FwIntermediaryMarketEval> {
    /**
     * 根据主键查询市场评价信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联中介机构外键查询市场评价信息列表
     * @param fkIntermediaryId
     * @return
     */
    public  Result queryListByIntermId(Long fkIntermediaryId);

    /**
     * 单个保存市场评价信息
     * @param marketEvalBo
     * @return
     */
    public Result save(FwIntermediaryMarketEvalBo marketEvalBo);

    /**
     * 根据主键更新市场评价信息
     * @param marketEvalBo
     * @return
     */
    public Result update(FwIntermediaryMarketEvalBo marketEvalBo);

    /**
     * 根据主键删除市场评价信息
     * @param fId
     * @return
     */
    public Result deleteById( Long fId);

    /**
     * 根据主键批量删除市场评价信息
     * @param fIds
     * @return
     */
    public Result deleteBatch(List<Long> fIds);
}
