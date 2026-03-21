package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryLinkmanInfoBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryLinkmanInfo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IFwIntermediaryLinkmanInfoService extends IBaseService<FwIntermediaryLinkmanInfo> {
    /**
     * 根据主键查询中介联系人信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联中介机构外键查询中介联系人信息
     * @param fkIntermediaryId
     * @return
     */
    public  Result queryListByIntermId(Long fkIntermediaryId);

    /**
     * 单个保存中介联系人信息
     * @param linkmanInfoBo
     * @return
     */

    public Result save(FwIntermediaryLinkmanInfoBo linkmanInfoBo);

    /**
     * 根据主键更新中介联系人信息
     * @param linkmanInfoBo
     * @return
     */
    public Result update(FwIntermediaryLinkmanInfoBo linkmanInfoBo);

    /**
     * 根据主键删除中介联系人信息
     * @param fId
     * @return
     */
    public Result deleteById(Long fId);

    /**
     * 根据主键批量删除中介联系人信息
     * @param fIds
     * @return
     */
    public Result deleteBatch(List<Long> fIds);
}
