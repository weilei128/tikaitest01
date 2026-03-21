package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryBankInfoBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryBankInfo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IFwIntermediaryBankInfoService extends IBaseService<FwIntermediaryBankInfo> {
    /**
     * 根据主键查询银行信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联中介机构外键查询银行信息
     * @param fkIntermediaryId
     * @return
     */
    public  Result queryListByIntermId(Long fkIntermediaryId);

    /**
     * 单个保存银行信息
     * @param bankInfoBo
     * @return
     */
    public Result save(FwIntermediaryBankInfoBo bankInfoBo);

    /**
     * 根据主键更新银行信息
     * @param bankInfoBo
     * @return
     */
    public Result update(FwIntermediaryBankInfoBo bankInfoBo);

    /**
     * 根据主键删除银行信息
     * @param fId
     * @return
     */
    public Result deleteById(Long fId);

    /**
     * 根据主键批量删除银行信息
     * @param fIds
     * @return
     */
    public Result deleteBatch(List<Long> fIds);
}
