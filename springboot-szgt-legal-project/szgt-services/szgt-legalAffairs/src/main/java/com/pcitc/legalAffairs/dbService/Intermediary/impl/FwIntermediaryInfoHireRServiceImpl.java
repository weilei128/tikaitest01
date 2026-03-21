package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInfoHireRService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInterestConflictService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryInfoHireRMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;

@Service
public class FwIntermediaryInfoHireRServiceImpl extends ServiceImpl<FwIntermediaryInfoHireRMapper, FwIntermediaryInfoHireR> implements IFwIntermediaryInfoHireRService {

	@Autowired
	private IFwIntermediaryInterestConflictService conflictService;
	@Autowired
	private IFwIntermediaryOrgBasicService intermediaryService;
	
	@Override
	public Result saveOne(FwIntermediaryInfoHireR entity) {
		FwIntermediaryOrgBasic intermediary = intermediaryService.getById(entity.getFkIntermediaryId());
		if (intermediary == null) {
			return Result.fail(ResultCode.FAILURE);
		}
		if (conflictService.isBanned(intermediary.getfSocialCreditCode())) {
			return Result.fail(400, "该中介机构因利益冲突被禁用");
		}
		save(entity);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Result saveEntityList(List<FwIntermediaryInfoHireR> entities) {
		for (FwIntermediaryInfoHireR entity : entities) {
			FwIntermediaryOrgBasic intermediary = intermediaryService.getById(entity.getFkIntermediaryId());
			if (intermediary == null) {
				return Result.fail(ResultCode.FAILURE);
			}
			if (conflictService.isBanned(intermediary.getfSocialCreditCode())) {
				return Result.fail(400, String.format("%s因利益冲突被禁用", intermediary.getfOrgName()));
			}
			lambdaUpdate().eq(FwIntermediaryInfoHireR::getFkHireId, entity.getFkHireId()).eq(FwIntermediaryInfoHireR::getFkIntermediaryId, entity.getFkIntermediaryId()).remove();
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
	public Result updateOne(FwIntermediaryInfoHireR entity) {
		updateById(entity);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result getByHire(Long hireId) {
		List<FwIntermediaryInfoHireR> list = lambdaQuery().eq(FwIntermediaryInfoHireR::getFkHireId, hireId).list();
		return Result.data(list);
	}

	@Override
	public Result getByIntermediary(Long intermediaryId) {
		List<FwIntermediaryInfoHireR> list = lambdaQuery().eq(FwIntermediaryInfoHireR::getFkIntermediaryId, intermediaryId).list();
		return Result.data(list);
	}

	@Override
	public Result updateList(List<FwIntermediaryInfoHireR> entities) {
		updateBatchById(entities);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByHire(Long hireId) {
		lambdaUpdate()
			.eq(FwIntermediaryInfoHireR::getFkHireId, hireId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByIntermediary(Long intermediaryId) {
		lambdaUpdate()
			.eq(FwIntermediaryInfoHireR::getFkIntermediaryId, intermediaryId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result deleteByHireAndIntermediary(Long hireId, Long intermediaryId) {
		if (hireId == null && intermediaryId == null) {
			throw new BaseException(ResultCode.PARAM_MISS);
		}
		lambdaUpdate()
			.eq(hireId != null, FwIntermediaryInfoHireR::getFkHireId, hireId)
			.eq(intermediaryId != null, FwIntermediaryInfoHireR::getFkIntermediaryId, intermediaryId)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}

}
