package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryOperateBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryQueryBo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryOrgBasicVo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;


public interface IFwIntermediaryOrgBasicService extends IBaseService<FwIntermediaryOrgBasic> {
    /**
     * 根据主键查询中介机构信息
     * @param fId
     * @return
     */
    public Result queryById(Long fId);

    /**
     * 根据关联聘用信息主键查询中介机构信息列表
     * @param fkHireInfoId
     * @return
     */
    public Result queryListHireInfoId(Long fkHireInfoId);
    /**
     * 根据关联聘用信息主键查询中介机构信息列表
     * @param fkHireInfoId
     * @return
     */
    public Result queryListElectionInfoId(Long fkHireInfoId);
    /**
     * 根据主键查询中介机构所有信息
     * @param fId
     * @return
     */
    public Result queryAllById(Long fId);

    /**
     * 多条件分页查询中介机构所有信息
     * @param orgBasicQueryBo
     * @return
     */
    public  Result queryPageByConditions(FwIntermediaryOrgBasicQueryBo orgBasicQueryBo);

    /**
     * 单个保存中介机构基本信息
     * @param orgBasicBo
     * @return
     */
    public Result save(FwIntermediaryOrgBasicBo orgBasicBo);

    /**
     * 单个保存中介机构基本信息并返回主键
     * @param orgBasicBo
     * @return
     */
    public Result saveReturnId(FwIntermediaryOrgBasicBo orgBasicBo);
    /**
     * 根据主键更新中介机构基本信息
     * @param orgBasicBo
     * @return
     */
    public Result update(FwIntermediaryOrgBasicBo orgBasicBo);

    /**
     * 根据主键删除中介机构所有信息
     * @param fId
     * @return
     */
    public Result deleteById(Long fId);

    public Result<List<FwIntermediaryOrgBasicVo>> queryByHireId(Long hireId);

	Result<List<FwIntermediaryOrgBasicVo>> queryElectionsByHireId(Long hireId);
	
    /**
     * 启用
     * @param bo
     */
    public void enable(IntermediaryOperateBo bo);

    /**
     * 禁用
     * 
     * @param bo
     */
    public void disable(IntermediaryOperateBo bo);

    /**
     * 解除准入
     * 
     * @param bo
     */
    public void cancelAdmit(IntermediaryOperateBo bo);
    
    /**
     * 通过统一社会信用代码解除准入
     * 
     * @param bo
     */
    public void cancelAdmitByUscCode(IntermediaryOperateBo bo);
    
    /**
     * 分页查询中介资源库
     * @param bo
     * @return
     */
    public IPage<FwIntermediaryOrgBasicVo> pageIntermediary(IntermediaryQueryBo bo);
}
