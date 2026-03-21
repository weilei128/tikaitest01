package com.pcitc.legalAffairs.dbService.Intermediary.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictQueryBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideFirmBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryConflictLogService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInterestConflictService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.mapper.Intermediary.FwIntermediaryInterestConflictMapper;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInterestConflict;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeLawFirmService;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeService;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.Intermediary.ConflictDetailVo;
import com.pcitc.legalAffairs.vo.Intermediary.ConflictVo;

@Service
public class FwIntermediaryInterestConflictServiceImpl extends ServiceImpl<FwIntermediaryInterestConflictMapper, FwIntermediaryInterestConflict> implements IFwIntermediaryInterestConflictService {

	@Autowired
	private LitigateDisputeLawFirmService disputeOppositeService;
	@Autowired
	private LitigateDisputeService disputeService;
	@Autowired
	private IFwIntermediaryConflictLogService clogService;
	@Autowired
	private IFwIntermediaryOrgBasicService intermediaryService;
	

	@Override
	public void save(String uscCode, String name, String type) {
		List<FwIntermediaryInterestConflict> list = lambdaQuery().eq(FwIntermediaryInterestConflict::getfUscCode, uscCode).list();
		// 已存在类似数据
		if (list != null && list.size() > 0) {
			return;
		}
		FwIntermediaryInterestConflict entity = new FwIntermediaryInterestConflict();
		entity.setfUscCode(uscCode);
		entity.setfName(name);
		entity.setfType(type);
		
		save(entity);
	}

	@Override
	public void saveList(List<FwIntermediaryInterestConflict> entities) {
		entities.stream().forEach(entity -> save(entity.getfUscCode(), entity.getfName(), entity.getfType()));
	}

	@Override
	public boolean isBanned(String uscCode) {
		List<FwIntermediaryInterestConflict> list = lambdaQuery().eq(FwIntermediaryInterestConflict::getfUscCode, uscCode).eq(FwIntermediaryInterestConflict::getfStatus, 1).list();
		return (list != null && list.size() > 0);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Result ban(ConflictLogBo bo) {
		lambdaUpdate().eq(FwIntermediaryInterestConflict::getfUscCode, bo.getFkUscCode()).set(FwIntermediaryInterestConflict::getfStatus, 1).update();
		bo.setFkOperate(1);
		clogService.save(bo);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Result unban(ConflictLogBo bo) {
		lambdaUpdate().eq(FwIntermediaryInterestConflict::getfUscCode, bo.getFkUscCode()).set(FwIntermediaryInterestConflict::getfStatus, 0).update();
		bo.setFkOperate(0);
		clogService.save(bo);
		return Result.success(ResultCode.SUCCESS);
	}

	@Override
	public Result pageData(ConflictQueryBo bo) {
		Page<FwIntermediaryInterestConflict> data = new Page<>(bo.getCurrent(), bo.getSize());
		IPage<FwIntermediaryInterestConflict> page = lambdaQuery()
			.like(bo.getName() != null, FwIntermediaryInterestConflict::getfName, bo.getName())
			.like(bo.getUscCode() != null, FwIntermediaryInterestConflict::getfUscCode, bo.getUscCode())
			.eq(bo.getType() != null, FwIntermediaryInterestConflict::getfType, bo.getType())
			.eq(bo.getStatus() != null, FwIntermediaryInterestConflict::getfStatus, bo.getStatus())
			.page(data);
		IPage<ConflictVo> convert = page.convert(entity -> {
			ConflictVo vo = new ConflictVo();
			BeanUtils.copyProperties(entity, vo);
			// 判定是否准入
			List<FwIntermediaryOrgBasic> list = intermediaryService.lambdaQuery()
				.eq(FwIntermediaryOrgBasic::getfSocialCreditCode, entity.getfUscCode())
				.eq(FwIntermediaryOrgBasic::getfIsValid, 1)
				.eq(FwIntermediaryOrgBasic::getfWorkFlowId, TypeEnum.Finish.getType())
				.list();
			if (list == null || list.size() == 0) {
				vo.setAdmit(0);
			} else {
				vo.setAdmit(1);
			}
			
			vo.setCases(getConflictDetail(entity.getfUscCode()));
			return vo;
		});
		return Result.data(convert);
	}
	
	public List<ConflictDetailVo> getConflictDetail(String uscCode) {
		List<DisputeOppositeFirmBo> oppositeLawFirmByUscCode = disputeOppositeService.getOppositeLawFirmByUscCode(uscCode);
		if (oppositeLawFirmByUscCode == null) {
			return null;
		}
		List<ConflictDetailVo> collect = oppositeLawFirmByUscCode.stream().map(firm -> {
			ConflictDetailVo vo = new ConflictDetailVo();
			
			DisputeBo dispute = disputeService.getbyId(firm.getFkDisputeId() + "");
			vo.setFkDisputeName(dispute.getFName());
			vo.setManager(dispute.getFkReportedPersonName());
			if (dispute.getOurside() != null) {
				String ourSide = dispute.getOurside()
					.stream()
					.map(DisputeOursideBo::getFLitigantName)
					.collect(Collectors.joining(", "));
				vo.setOurSide(ourSide);
			}
			if (dispute.getOpposite() != null) {
				String opposite = dispute.getOpposite()
					.stream()
					.map(DisputeOppositeBo::getFName)
					.collect(Collectors.joining(", "));
				vo.setOpposite(opposite);
			}
			if (dispute.getOurFirm() != null) {
				String firm1 = dispute.getOurFirm()
					.stream()
					.map(DisputeOursideFirmBo::getFkIntermediaryName)
					.collect(Collectors.joining(", "));
				vo.setFirm(firm1.toString());
			}
			return vo;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public Result delete(String uscCode) {
		lambdaUpdate()
			.eq(FwIntermediaryInterestConflict::getfUscCode, uscCode)
			.remove();
		return Result.success(ResultCode.SUCCESS);
	}
	
	@Override
	public void removeNoCase() {
//		lambdaUpdate()
//			.notInSql(FwIntermediaryInterestConflict::getfUscCode, "select fk_Intermediary_USC_Code from fw_litigate_dispute_opposite_firm where f_isDel = 0")
//			.remove();
	}
}
