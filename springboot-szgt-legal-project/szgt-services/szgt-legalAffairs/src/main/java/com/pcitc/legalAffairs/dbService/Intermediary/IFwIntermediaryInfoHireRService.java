package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireR;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IFwIntermediaryInfoHireRService extends IBaseService<FwIntermediaryInfoHireR> {

	public Result saveOne(FwIntermediaryInfoHireR entity);
	public Result saveEntityList(List<FwIntermediaryInfoHireR> entities);
	public Result deleteOne(Long id);
	public Result deleteList(List<Long> ids);
	public Result updateOne(FwIntermediaryInfoHireR entity);
	public Result updateList(List<FwIntermediaryInfoHireR> entities);
	public Result getByHire(Long hireId);
	public Result getByIntermediary(Long intermediaryId);
	public Result deleteByHire(Long hireId);
	public Result deleteByIntermediary(Long intermediaryId);
	public Result deleteByHireAndIntermediary(Long hireId, Long intermediaryId);
	
}
