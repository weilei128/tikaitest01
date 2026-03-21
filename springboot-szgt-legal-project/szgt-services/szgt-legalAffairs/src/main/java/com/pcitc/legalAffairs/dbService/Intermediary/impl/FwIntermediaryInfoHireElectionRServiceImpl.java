package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInfoHireElectionRService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryInfoHireElectionRMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireElectionR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;

@Service
public class FwIntermediaryInfoHireElectionRServiceImpl extends ServiceImpl<FwIntermediaryInfoHireElectionRMapper, FwIntermediaryInfoHireElectionR> implements IFwIntermediaryInfoHireElectionRService {

	@Autowired
	private IFwIntermediaryOrgBasicService intermediaryService;
	
	@Override
	public Result saveOne(FwIntermediaryInfoHireElectionR entity) {
		FwIntermediaryOrgBasic intermediary = intermediaryService.getById(entity.getFkIntermediaryId());
		if (intermediary == null) {
			return Result.fail(ResultCode.FAILURE);
		}
		save(entity);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result saveEntityList(List<FwIntermediaryInfoHireElectionR> entities) {
		for (FwIntermediaryInfoHireElectionR entity : entities) {
			FwIntermediaryOrgBasic intermediary = intermediaryService.getById(entity.getFkIntermediaryId());
			if (intermediary == null) {
				return Result.fail(ResultCode.FAILURE);
			}
			
			lambdaUpdate().eq(FwIntermediaryInfoHireElectionR::getFkHireId, entity.getFkHireId()).eq(FwIntermediaryInfoHireElectionR::getFkIntermediaryId, entity.getFkIntermediaryId()).remove();
			save(entity);

		}
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteOne(Long id) {
		removeById(id);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteList(List<Long> ids) {
		removeByIds(ids);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result updateOne(FwIntermediaryInfoHireElectionR entity) {
		updateById(entity);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result getByHire(Long hireId) {
		List<FwIntermediaryInfoHireElectionR> list = lambdaQuery().eq(FwIntermediaryInfoHireElectionR::getFkHireId, hireId).list();
		return Result.data(list);
	}

	@Override
	public Result getByIntermediary(Long intermediaryId) {
		List<FwIntermediaryInfoHireElectionR> list = lambdaQuery().eq(FwIntermediaryInfoHireElectionR::getFkIntermediaryId, intermediaryId).list();
		return Result.data(list);
	}

	@Override
	public Result updateList(List<FwIntermediaryInfoHireElectionR> entities) {
		updateBatchById(entities);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByHire(Long hireId) {
		lambdaUpdate()
			.eq(FwIntermediaryInfoHireElectionR::getFkHireId, hireId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByIntermediary(Long intermediaryId) {
		lambdaUpdate()
			.eq(FwIntermediaryInfoHireElectionR::getFkIntermediaryId, intermediaryId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByHireAndIntermediary(Long hireId, Long intermediaryId) {
		if (hireId == null && intermediaryId == null) {
			throw new BaseException(ResultCode.PARAM_MISS);
		}
		lambdaUpdate()
			.eq(hireId != null, FwIntermediaryInfoHireElectionR::getFkHireId, hireId)
			.eq(intermediaryId != null, FwIntermediaryInfoHireElectionR::getFkIntermediaryId, intermediaryId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

}
