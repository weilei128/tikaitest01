package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryQualifiInfoBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryQualifiInfo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IFwIntermediaryQualifiInfoService extends IBaseService<FwIntermediaryQualifiInfo> {
    /**
     * 根据资质信息主键查询资质信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联中介机构外键查询资质信息列表
     * @param fkIntermediaryId
     * @return
     */
    public  Result queryListByIntermId( Long fkIntermediaryId);

    /**
     * 单个保存中介机构资质信息
     * @param qualifiInfoBo
     * @return
     */
    public Result save( FwIntermediaryQualifiInfoBo qualifiInfoBo);

    /**
     * 根据主键更新中介机构资质信息
     * @param qualifiInfoBo
     * @return
     */
    public Result update(FwIntermediaryQualifiInfoBo qualifiInfoBo);

    /**
     * 根据主键删除中介机构资质信息
     * @param fId
     * @return
     */
    public Result deleteById(Long fId);

    /**
     * 根据主键批量删除中介机构资质信息
     * @param fIds
     * @return
     */
    public Result deleteBatch(List<Long> fIds);
}
