package com.pcitc.legalAffairs.dbService.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireElectionR;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IFwIntermediaryInfoHireElectionRService extends IBaseService<FwIntermediaryInfoHireElectionR> {

	public Result saveOne(FwIntermediaryInfoHireElectionR entity);
	public Result saveEntityList(List<FwIntermediaryInfoHireElectionR> entities);
	public Result deleteOne(Long id);
	public Result deleteList(List<Long> ids);
	public Result updateOne(FwIntermediaryInfoHireElectionR entity);
	public Result updateList(List<FwIntermediaryInfoHireElectionR> entities);
	public Result getByHire(Long hireId);
	public Result getByIntermediary(Long intermediaryId);
	public Result deleteByHire(Long hireId);
	public Result deleteByIntermediary(Long intermediaryId);
	public Result deleteByHireAndIntermediary(Long hireId, Long intermediaryId);
	
}
