package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateQueryBo;
import com.pcitc.legalAffairs.po.Intermediary.FwLawGroupEvaluate;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IFwLawGroupEvaluateService extends IBaseService<FwLawGroupEvaluate> {
    /**
     * 根据主键查询法律团队考评信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联法律团队主键查询考评信息列表
     * @param fkLegalTeamId
     * @return
     */
    public Result queryListByLegalTeamId(Long fkLegalTeamId);

    /**
     * 多条件分页查询律师考评信息
     * @param groupEvaluateQueryBo
     * @return
     */
    public Result queryPageByConditions(FwLawGroupEvaluateQueryBo groupEvaluateQueryBo);

    /**
     * 单个保存律师考评信息
     * @param lawGroupEvaluateBo
     * @return
     */
    public Result save(FwLawGroupEvaluateBo lawGroupEvaluateBo);

    /**
     * 根据主键修改律师考评信息
     * @param lawGroupEvaluateBo
     * @return
     */
    public Result update(FwLawGroupEvaluateBo lawGroupEvaluateBo);

    /**
     * 根据主键删除律师考评信息
     * @param fId
     * @return
     */
    public Result delete(Long fId);
}
