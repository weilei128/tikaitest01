package com.pcitc.legalAffairs.dbService.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireLegalTeamBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireLegalTeam;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

import java.util.List;

public interface IFwIntermediaryHireLegalTeamService extends IBaseService<FwIntermediaryHireLegalTeam> {
    /**
     * 单个保存聘用法律团队信息
     * @param hireLegalTeamBo
     * @return
     */
    public Result save(FwIntermediaryHireLegalTeamBo hireLegalTeamBo);

    /**
     * 根据主键更新聘用法律团队信息
     * @param hireLegalTeamBo
     * @return
     */
    public Result update(FwIntermediaryHireLegalTeamBo hireLegalTeamBo);

    /**
     * 根据主键查询聘用法律团队信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /***
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    public Result queryListByOrgBasicId(Long fkOrgBasicId, Integer type);
    
    /***
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    public Result queryListByHireId(Long hireId, Integer type);
    
    /***
     * 根据聘用信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    public Result queryListByOrgBasicIdAndHireId(Long fkOrgBasicId, Long hireId, Integer type);

    /**
     * 根据主键删除聘用法律团队信息
     * @param fId
     * @return
     */
    public Result deleteById(Long fId);

    /**
     * 根据主键批量删除聘用法律团队信息
     * @param fIds
     * @return
     */
    public  Result deleteBatch(List<Long> fIds);
}
