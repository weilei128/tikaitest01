package com.pcitc.legalAffairs.dbService.Intermediary;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.HireInfoQuery;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.vo.Intermediary.IntermediaryHireVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IFwIntermediaryHireInfoService extends IBaseService<FwIntermediaryHireInfo> {
    /**
     * 单个保存中介机构聘用信息
     * @param hireInfoBo
     * @return
     */
    public Result save(FwIntermediaryHireInfoBo hireInfoBo);

    /**
     * 单个修改中介机构聘用信息
     * @param hireInfoBo
     * @return
     */
    public Result update(FwIntermediaryHireInfoBo hireInfoBo);

    /**
     * 根据主键查询中介机构聘用信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据主键查询中介机构聘用信息(包含法律团队信息和中介机构信息）
     * @param fId
     * @return
     */
    public Result queryAllById(Long fId);

    /**
     * 多条件分页查询中介机构聘用信息
     * @param hireInfoQueryBo
     * @return
     */
    public Result queryPageByConditins(FwIntermediaryHireInfoQueryBo hireInfoQueryBo);

    /**
     * 根据主键删除中介机构聘用信息
     * @param fId
     * @return
     */
    public Result delete(Long fId);

    /**
     * 根据主键删除中介机构聘用信息(包括聘用法律团队信息）
     * @param fId
     * @return
     */
    public Result deleteAllById(Long fId);
    
    /**
     * 查询中介聘用信息
     * @param page
     * @param bo
     * @return
     */
	public IPage<IntermediaryHireVo> queryHireInfo(HireInfoQuery bo);

}
