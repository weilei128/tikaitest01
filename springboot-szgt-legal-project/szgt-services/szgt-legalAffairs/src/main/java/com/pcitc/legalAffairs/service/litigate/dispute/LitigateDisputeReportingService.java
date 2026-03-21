package com.pcitc.legalAffairs.service.litigate.dispute;

import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.boToEntity;
import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.entityToBo;
import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.oppositeBosToEntityList;
import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.othersideBosToEntityList;
import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.oursideBosToEntityList;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeApproveBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOthersideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOthersideService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOpposite;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOtherside;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOurside;
import com.pcitc.legalAffairs.service.dps.DpsApproveService;
import com.pcitc.legalAffairs.service.dps.DpsQueryService;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pctic.common.utils.UserUtils;

/**
 * 纠纷填报Service
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeReportingService {

	@Autowired
	private ILitigateDisputeService idisputeService;
	@Autowired
	private ILitigateDisputeOursideService idisputeOursideService;
	@Autowired
	private ILitigateDisputeOppositeService idisputeOppositeService;
	@Autowired
	private ILitigateDisputeOthersideService idisputeOthersideService;
	@Autowired
	private LitigateDisputeService disputeService;
	@Autowired
	private LitigateDisputeOursideService oursideService;
	@Autowired
	private LitigateDisputeOppositeService oppositeService;
	@Autowired
	private LitigateDisputeOthersideService othersideService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private DpsApproveService dpsApproveService;
	@Autowired
	private DpsQueryService dpsQueryService;
	@Autowired
	private LitigateDisputeFileDictService fileService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private LitigateDisputeRelationService disputeRelationService;

	/**
	 * 保存诉前争议信息及我方、对方、第三方信息(若有)
	 * @param bo
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Long save(DisputeBo bo) {
		if (bo == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		FwLitigateDispute entity = boToEntity(bo);
		// 生成案号, 前缀为 J
//		if (StringUtils.checkValNull(entity.getfCode())) {
		entity.setfCode(disputeService.codeGenerator("J", entity.getFkReportedOrgId(), bo.getFType(), bo.getFType2(), bo.getFType3(), false));
		// 将原有的S案号改为J案号
//		} else {
//			entity.setfCode(entity.getfCode().replace("S", "J"));
//		}
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		if (entity.getfSaveTime() == null) {
			entity.setfSaveTime(new Date());
		}
		idisputeService.save(entity);
		Long id = entity.getfId();
		String name = entity.getfName();
		if (bo.getOurside() != null) {
			List<FwLitigateDisputeOurside> ourside = oursideBosToEntityList(bo.getOurside(), id, name);
			idisputeOursideService.saveBatch(ourside);
		}
		if (bo.getOpposite() != null) {
			List<FwLitigateDisputeOpposite> opposite = oppositeBosToEntityList(bo.getOpposite(), id, name);
			idisputeOppositeService.saveBatch(opposite);
		}
		if (bo.getOtherside() != null) {
			List<FwLitigateDisputeOtherside> otherside = othersideBosToEntityList(bo.getOtherside(), id, name);
			idisputeOthersideService.saveBatch(otherside);
		}

		if (bo.getRelated() != null) {
			List<Long> relatedIds = bo.getRelated().stream().map(DisputeVo::getId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
			disputeRelationService.update(id, relatedIds);
		}
		fileService.save(bo.getFiles(), id, DisputeAttachEnum.DISPUTE, id);
		return id;
	}

	@Transactional(rollbackFor = Throwable.class)
	public Long update(DisputeBo bo) {
		if (bo == null || bo.getFId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		Long id = bo.getFId();
		String name = bo.getFName();
		FwLitigateDispute entity = boToEntity(bo);
		// 生成案号, 前缀为 J
//		if (StringUtils.checkValNull(entity.getfCode())) {
		entity.setfCode(disputeService.codeGenerator("J", entity.getFkReportedOrgId(), bo.getFType(), bo.getFType2(), bo.getFType3(), false));
		// 将原有的S案号改为J案号
//		} else {
//			entity.setfCode(entity.getfCode().replace("S", "J"));
//		}
		if (entity.getfSaveTime() == null) {
			entity.setfSaveTime(new Date());
		}
//		entity.setfStatus(DisputeStatus.DISPUTE_APPROVING);
		SysUserInfo userInfo = UserUtils.getUserInfo();
		entity.setfUserId(userInfo.getfId());
		idisputeService.updateById(entity);
		// 更新子表
		oursideService.updateBatch(bo.getOurside(), id, name);
		oppositeService.updateBatch(bo.getOpposite(), id, name);
		othersideService.updateBatch(bo.getOtherside(), id, name);
		if (bo.getRelated() != null) {
			List<Long> relatedIds = bo.getRelated().stream().map(DisputeVo::getId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
			disputeRelationService.update(id, relatedIds);
		}

		// 找到对应的审批待办 设为完成
		try {
			String categoryCode = DpsCategoryEnum.DpsCategory_Report.getCategoryCode();
			String businessId = categoryCode + "_" + id;
			List<ExecuteTaskData> toDoList = dpsQueryService.getTodoTaskByBusinessId(businessId);
			if (toDoList == null || toDoList.size() == 0) {
				return id;
			}
			boolean completed = false;
			for (ExecuteTaskData e: toDoList) {
				// 自己的待办
				String userId = e.getExecutorId();
				if (userInfo.getfId().toString().equals(userId)) {
					ExecuteContext complete = new ExecuteContext();
					complete.setAppId("111");
					complete.setExecutorCode(userInfo.getfCode());
					complete.setExecutorId(userId);
					complete.setExecutorName(userInfo.getfCname());
					complete.setOpinion("操作完成");
					complete.setTaskId(e.getTaskId());
					complete.setCategoryCode(categoryCode);
					complete.setBusinessId(businessId);
					dpsApproveService.approveDpsComplete(complete);
					completed = true;
					break;
				}
			}
			if (!completed) {
				// 随便找一条待办先完成再说
				ExecuteTaskData e = toDoList.get(0);
				String userId = e.getExecutorId();
				if (userInfo.getfId().toString().equals(userId)) {
					ExecuteContext complete = new ExecuteContext();
					complete.setAppId("111");
					complete.setExecutorCode(userInfo.getfCode());
					complete.setExecutorId(userInfo.getfId().toString());
					complete.setExecutorName(userInfo.getfCname());
					complete.setOpinion("操作完成");
					complete.setTaskId(e.getTaskId());
					complete.setCategoryCode(categoryCode);
					complete.setBusinessId(businessId);
					dpsApproveService.approveDpsComplete(complete);
					completed = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * 废弃单据
	 * @param bo
	 */
	public void discard(DisputeBo bo) {
		if (bo == null || bo.getFId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		Long id = bo.getFId();
		String cause = bo.getFDiscardDescription();
		if (StringUtils.checkValNull(cause)) {
			throw new BaseException("请填写废弃原因! ", 500);
		}
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		entity.setfIsDiscard(DisputeStatus.DISCARDED);
		entity.setfDiscardDescription(cause);
		idisputeService.updateById(entity);
	}
	
	/**
	 * 审批单据
	 * @param bo
	 */
	public void approve(DisputeApproveBo bo) {
		if (bo == null || bo.getId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		Long id = bo.getId();
		String cause = bo.getCause();
		if (StringUtils.checkValNull(cause)) {
			throw new BaseException("请填写废弃原因! ", 500);
		}
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		entity.setfStatus(DisputeStatus.DISPUTE_SETTLING);
		entity.setfDiscardDescription(cause);
		idisputeService.updateById(entity);
	}
	
	public DisputeBo getbyId(String id) {
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		DisputeBo bo = entityToBo(entity);
		List<DisputeOursideBo> ourside = oursideService.getByDisputeId(id);
		List<DisputeOppositeBo> opposite = oppositeService.getByDisputeId(id);
		List<DisputeOthersideBo> otherside = othersideService.getByDisputeId(id);
		bo.setOurside(ourside);
		bo.setOpposite(opposite);
		bo.setOtherside(otherside);
		return bo;
	}
	
	public IPage<DisputeBo> queryList(DisputeQueryBo bo) {
		IPage<FwLitigateDispute> page = new Page<>(bo.getCurrent(), bo.getSize());
		LambdaQueryWrapper<FwLitigateDispute> wrapper = idisputeService.lambdaQueryWrapper()
				.like(StringUtils.checkValNotNull(bo.getCode()), FwLitigateDispute::getfCode, bo.getCode())
				.like(StringUtils.checkValNotNull(bo.getName()), FwLitigateDispute::getfName, bo.getName())
				.in(FwLitigateDispute::getfStatus, DisputeStatus.LITIGATE, DisputeStatus.DISPUTE_REPORTING, DisputeStatus.DISPUTE_APPROVING, DisputeStatus.DISPUTE_SETTLING, DisputeStatus.DISPUTE_SETTLED_EXECUTING, DisputeStatus.DISPUTE_SETTLED_CLOSED, DisputeStatus.DISCARDED_RETURN)
				.eq(bo.getDiscarded() != null, FwLitigateDispute::getfIsDiscard, bo.getDiscarded())
				.eq(bo.getStatus() != null, FwLitigateDispute::getfStatus, bo.getStatus())
				.and(w -> w.eq(FwLitigateDispute::getfUserId, -1)
					.in(FwLitigateDispute::getFkReportedOrgId, userOrgService.getLoginUserOrg())
					.or(w1 -> w1.eq(FwLitigateDispute::getfUserId, UserUtils.getUserInfo().getfId()))
					.or(privilegeInfoService.privilegedOrgs() != null, w1 -> w1.in(FwLitigateDispute::getFkReportedOrgId, privilegeInfoService.privilegedOrgs()))
				)
				.orderByDesc(FwLitigateDispute::getfSaveTime);
		IPage<FwLitigateDispute> pagedOri = idisputeService.page(page, wrapper);
		IPage<DisputeBo> convert = pagedOri.convert(entity -> {
			DisputeBo queriedBo = entityToBo(entity);
			List<DisputeOppositeBo> opposite = oppositeService.getByDisputeId(queriedBo.getFId().toString());
			String oppositeName = "";
			StringBuilder oppositeNameBuilder = new StringBuilder();
			if (opposite != null) {
				opposite.stream().forEach(e -> {
					oppositeNameBuilder.append(e.getFName());
					oppositeNameBuilder.append(", ");
				});
				if (oppositeNameBuilder.toString().length() >= 1) {
					oppositeName = oppositeNameBuilder.toString().substring(0, oppositeNameBuilder.toString().length() - 2);
				}
				queriedBo.setOppositeName(oppositeName);
			}
			
			return queriedBo;
		});
		return convert;
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public boolean remove(String id) {
		if (id == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		// 删除主表
		idisputeService.removeById(id);
		// 删除子表
		idisputeOursideService.remove(idisputeOursideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOurside::getFkDisputeId, id));
		idisputeOppositeService.remove(idisputeOppositeService.lambdaUpdateWrapper().eq(FwLitigateDisputeOpposite::getFkDisputeId, id));
		idisputeOthersideService.remove(idisputeOthersideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOtherside::getFkDisputeId, id));
		return true;
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public boolean remove(List<String> ids) {
		if (ids == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		idisputeService.removeByIds(ids);
		idisputeOursideService.remove(idisputeOursideService.lambdaUpdateWrapper().in(FwLitigateDisputeOurside::getFkDisputeId, ids));
		idisputeOppositeService.remove(idisputeOppositeService.lambdaUpdateWrapper().in(FwLitigateDisputeOpposite::getFkDisputeId, ids));
		idisputeOthersideService.remove(idisputeOthersideService.lambdaUpdateWrapper().in(FwLitigateDisputeOtherside::getFkDisputeId, ids));
		return true;
	}

}
