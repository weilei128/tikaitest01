package com.pcitc.legalAffairs.service.litigate.dispute;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideFirmBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInterestConflictService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeFirmService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeFirmStepService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideFirmService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideFirmStepService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirm;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirmStep;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirm;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirmStep;

/**
 * 纠纷办理: 代理律所Service
 * 
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeLawFirmService {

	@Autowired
	private ILitigateDisputeOursideFirmService idisputeOursideFirmService;
	@Autowired
	private ILitigateDisputeOppositeFirmService idisputeOppositeFirmService;
	@Autowired
	private ILitigateDisputeOursideFirmStepService idisputeOursideFirmStepService;
	@Autowired
	private ILitigateDisputeOppositeFirmStepService idisputeOppositeFirmStepService;
	@Autowired
	private IFwIntermediaryInterestConflictService conflictService;

	/**
	 * 保存我方代理律所信息
	 * 
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Long saveOursideFirm(DisputeOursideFirmBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOursideFirm entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
		List<Long> steps = bo.getSteps();
		idisputeOursideFirmService.save(entity);
		Long firmId = entity.getfId();
		List<FwLitigateDisputeOursideFirmStep> stepEntities = DisputePojoConverter.oursideFirmStepsToEntityList(steps,
				firmId, disputeId);
		if (stepEntities != null) {
			idisputeOursideFirmStepService.saveBatch(stepEntities);
		}

		return firmId;
	}

	/**
	 * 批量保存我方代理律所信息
	 * 
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void saveOursideFirm(List<DisputeOursideFirmBo> bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return;
		}
		bo.stream().forEach(e -> {
			saveOursideFirm(e, disputeId, disputeName);
		});
	}

	/**
	 * 批量编辑我方代理律所信息
	 * 
	 * @param bos
	 * @param disputeId
	 * @param disputeName
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void updateOursideFirm(List<DisputeOursideFirmBo> bos, Long disputeId, String disputeName) {
		// 删除进展信息
		idisputeOursideFirmStepService.remove(idisputeOursideFirmStepService.lambdaUpdateWrapper()
				.eq(FwLitigateDisputeOursideFirmStep::getFkDisputeId, disputeId));
		// 新添加的项目
		List<DisputeOursideFirmBo> newItems = bos.stream().filter(bo -> bo.getFId() == null)
				.collect(Collectors.toList());
		// 应编辑的项目
		List<DisputeOursideFirmBo> existed = bos.stream()
				.filter(bo -> bo.getFId() != null).collect(Collectors.toList());
		// 应避免删除的项目ID
		List<Long> ids = existed.stream().map(DisputeOursideFirmBo::getFId).collect(Collectors.toList());
		// 编辑项目
		if (existed != null) {
			existed.stream().forEach(bo -> {
				updateOursideFirm(bo, disputeId, disputeName);
			});
		}
		// 删除项目
		idisputeOursideFirmService.remove(idisputeOursideFirmService.lambdaUpdateWrapper()
				.eq(FwLitigateDisputeOursideFirm::getFkDisputeId, disputeId)
				.notIn(ids != null && ids.size() > 0, FwLitigateDisputeOursideFirm::getfId, ids));
		// 保存新项目
		saveOursideFirm(newItems, disputeId, disputeName);
	}

	/**
	 * 编辑我方代理律所信息
	 * 
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void updateOursideFirm(DisputeOursideFirmBo bo, Long disputeId, String disputeName) {
		// 删除进展信息
		idisputeOursideFirmStepService.remove(idisputeOursideFirmStepService.lambdaUpdateWrapper()
				.eq(FwLitigateDisputeOursideFirmStep::getFkDisputeId, disputeId)
				.eq(FwLitigateDisputeOursideFirmStep::getFkLawFirmId, bo.getFId()));
		FwLitigateDisputeOursideFirm entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
		idisputeOursideFirmService.update(entity, idisputeOursideFirmService.lambdaUpdateWrapper()
				.eq(FwLitigateDisputeOursideFirm::getfId, bo.getFId())
				.eq(FwLitigateDisputeOursideFirm::getFkDisputeId, disputeId));
		List<Long> steps = bo.getSteps();
		List<FwLitigateDisputeOursideFirmStep> stepEntityList = DisputePojoConverter.oursideFirmStepsToEntityList(steps,
				bo.getFId(), disputeId);
		if (stepEntityList != null) {
			idisputeOursideFirmStepService.saveBatch(stepEntityList);
		}
	}

	/**
	 * 删除我方代理律所信息
	 * 
	 * @param id
	 */
	public void deleteOursideFirm(String id) {
		idisputeOursideFirmService.removeById(id);
		idisputeOursideFirmStepService.remove(idisputeOursideFirmStepService.lambdaUpdateWrapper()
				.eq(FwLitigateDisputeOursideFirmStep::getFkLawFirmId, id));
	}

	/**
	 * 删除我方代理律所信息
	 * 
	 * @param ids
	 */
	public void deleteOursideFirm(List<String> ids) {
		idisputeOursideFirmService.removeByIds(ids);
		idisputeOursideFirmStepService.remove(idisputeOursideFirmStepService.lambdaUpdateWrapper()
			.in(FwLitigateDisputeOursideFirmStep::getFkLawFirmId, ids));
	}

	/**
	 * 获取纠纷单据中我方律所信息
	 * @param disputeId
	 * @return
	 */
	public List<DisputeOursideFirmBo> getOursideLawFirms(Long disputeId) {
		List<FwLitigateDisputeOursideFirm> entities = idisputeOursideFirmService.list(idisputeOursideFirmService.lambdaQueryWrapper().eq(FwLitigateDisputeOursideFirm::getFkDisputeId, disputeId));
		List<FwLitigateDisputeOursideFirmStep> steps = idisputeOursideFirmStepService.list(idisputeOursideFirmStepService.lambdaQueryWrapper()
			.eq(FwLitigateDisputeOursideFirmStep::getFkDisputeId, disputeId));
		return DisputePojoConverter.oursideFirmEntitiesToBoList(entities, steps);
	}

	/**
	 * 保存对方代理律所信息
	 * 
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Long saveOppositeFirm(DisputeOppositeFirmBo bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return null;
		}
		FwLitigateDisputeOppositeFirm entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
		List<Long> steps = bo.getSteps();
		idisputeOppositeFirmService.save(entity);
		Long firmId = entity.getfId();
		List<FwLitigateDisputeOppositeFirmStep> stepEntities = DisputePojoConverter.oppositeFirmStepsToEntityList(steps, firmId, disputeId);
		if (stepEntities != null) {
			idisputeOppositeFirmStepService.saveBatch(stepEntities);
		}
		// 保存利益冲突信息
		conflictService.save(entity.getFkIntermediaryUscCode(), entity.getFkIntermediaryName(), null);
		return firmId;
	}
	
	/**
	 * 批量保存对方代理律所信息
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void saveOppositeFirm(List<DisputeOppositeFirmBo> bo, Long disputeId, String disputeName) {
		if (bo == null) {
			return;
		}
		bo.stream().forEach(e -> {
			saveOppositeFirm(e, disputeId, disputeName);
		});
	}
	
	/**
	 * 批量编辑我方代理律所信息
	 * @param bos
	 * @param disputeId
	 * @param disputeName
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void updateOppositeFirm(List<DisputeOppositeFirmBo> bos, Long disputeId, String disputeName) {
		// 删除进展信息
		idisputeOppositeFirmStepService.remove(idisputeOppositeFirmStepService.lambdaUpdateWrapper()
			.eq(FwLitigateDisputeOppositeFirmStep::getFkDisputeId, disputeId));
		// 新添加的项目
		List<DisputeOppositeFirmBo> newItems = bos.stream().filter(bo -> bo.getFId() == null).collect(Collectors.toList());
		// 应编辑的项目
		List<DisputeOppositeFirmBo> existed = bos.stream().filter(bo -> bo.getFId() != null).collect(Collectors.toList());
		// 应避免删除的项目ID
		List<Long> ids = existed.stream().map(DisputeOppositeFirmBo::getFId).collect(Collectors.toList());
		// 编辑项目
		if (existed != null) {
			existed.stream().forEach(bo -> {
				updateOppositeFirm(bo, disputeId, disputeName);
			});
		}
		// 删除项目
		idisputeOppositeFirmService.remove(idisputeOppositeFirmService.lambdaUpdateWrapper()
			.eq(FwLitigateDisputeOppositeFirm::getFkDisputeId, disputeId)
			.notIn(ids != null && ids.size() > 0, FwLitigateDisputeOppositeFirm::getfId, ids));
		// 保存新项目
		saveOppositeFirm(newItems, disputeId, disputeName);
		conflictService.removeNoCase();
	}

	/**
	 * 编辑对方代理律所信息
	 * @param bo
	 * @param disputeId
	 * @param disputeName
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void updateOppositeFirm(DisputeOppositeFirmBo bo, Long disputeId, String disputeName) {
		// 删除进展信息
		idisputeOppositeFirmStepService.remove(idisputeOppositeFirmStepService.lambdaUpdateWrapper()
			.eq(FwLitigateDisputeOppositeFirmStep::getFkDisputeId, disputeId)
			.eq(FwLitigateDisputeOppositeFirmStep::getFkLawFirmId, bo.getFId()));
		FwLitigateDisputeOppositeFirm entity = DisputePojoConverter.boToEntity(bo, disputeId, disputeName);
		idisputeOppositeFirmService.update(entity, idisputeOppositeFirmService.lambdaUpdateWrapper()
			.eq(FwLitigateDisputeOppositeFirm::getfId, bo.getFId())
			.eq(FwLitigateDisputeOppositeFirm::getFkDisputeId, disputeId));
		List<Long> steps = bo.getSteps();
		List<FwLitigateDisputeOppositeFirmStep> stepEntityList = DisputePojoConverter.oppositeFirmStepsToEntityList(steps, bo.getFId(), disputeId);
		if (stepEntityList != null) {
			idisputeOppositeFirmStepService.saveBatch(stepEntityList);
		}
		conflictService.save(entity.getFkIntermediaryUscCode(), entity.getFkIntermediaryName(), null);
		conflictService.removeNoCase();
	}

	/**
	 * 删除对方代理律所信息
	 * 
	 * @param id
	 */
	public void deleteOppositeFirm(String id) {
		idisputeOppositeFirmService.removeById(id);
		idisputeOppositeFirmStepService.remove(idisputeOppositeFirmStepService.lambdaUpdateWrapper().eq(FwLitigateDisputeOppositeFirmStep::getFkLawFirmId, id));
		conflictService.removeNoCase();
	}

	/**
	 * 删除对方代理律所信息
	 * 
	 * @param ids
	 */
	public void deleteOppositeFirm(List<String> ids) {
		idisputeOppositeFirmService.removeByIds(ids);
		idisputeOppositeFirmStepService.remove(idisputeOppositeFirmStepService.lambdaUpdateWrapper().in(FwLitigateDisputeOppositeFirmStep::getFkLawFirmId, ids));
		conflictService.removeNoCase();
	}

	/**
	 * 获取纠纷单据中对方律所信息
	 * @param disputeId
	 * @return
	 */
	public List<DisputeOppositeFirmBo> getOppositeLawFirms(Long disputeId) {
		List<FwLitigateDisputeOppositeFirm> entities = idisputeOppositeFirmService.list(idisputeOppositeFirmService.lambdaQueryWrapper().eq(FwLitigateDisputeOppositeFirm::getFkDisputeId, disputeId));
		List<FwLitigateDisputeOppositeFirmStep> steps = idisputeOppositeFirmStepService.list(idisputeOppositeFirmStepService.lambdaQueryWrapper()
			.eq(FwLitigateDisputeOppositeFirmStep::getFkDisputeId, disputeId));
		return DisputePojoConverter.oppositeFirmEntitiesToBoList(entities, steps);
	}

	public List<DisputeOppositeFirmBo> getOppositeLawFirmByUscCode(String uscCode) {
		List<FwLitigateDisputeOppositeFirm> list = idisputeOppositeFirmService.lambdaQuery()
			.eq(FwLitigateDisputeOppositeFirm::getFkIntermediaryUscCode, uscCode)
			.select(FwLitigateDisputeOppositeFirm::getFkDisputeId, FwLitigateDisputeOppositeFirm::getFkDisputeName)
			.list();
		return DisputePojoConverter.oppositeFirmEntitiesToBoList(list, null);
	}
}
