package com.pcitc.legalAffairs.service.punish;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.punish.PunishFileBo;
import com.pcitc.legalAffairs.bo.punish.PunishInfoBo;
import com.pcitc.legalAffairs.bo.punish.PunishProgressBo;
import com.pcitc.legalAffairs.bo.punish.PunishQueryBo;
import com.pcitc.legalAffairs.dbService.punish.IPunishInfoService;
import com.pcitc.legalAffairs.po.punish.FwPunishInfo;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处罚信息相关Service
 * 
 * @author meihongli
 */
@Service
public class PunishService {


	@Autowired
	private IPunishInfoService ipunishInfoService;
	@Autowired
	private PunishProgressService progressService;
	@Autowired
	private PunishFileService fileService;
	@Autowired
	private UserOrgService userOrgService;
	
	public IPunishInfoService getIService() {
		return this.ipunishInfoService;
	}

	/** 
	 * 新增或编辑 执行新增还是编辑操作取决于fId字段有没有值
	 * 
	 * @param bo bo
	 * @return 主键ID(fId)
	 */
	public Long saveOrUpdate(PunishInfoBo bo) {
		if (bo == null) {
			throw new BaseException("参数不存在", 400);
		}
		FwPunishInfo entity = boToEntity(bo);
		// 数据置于草稿状态
		entity.setfState(PunishConst.DRAFT);
		// 新增
		if (entity.getfId() == null) {
			ipunishInfoService.save(entity);
		// 编辑
		} else {
			ipunishInfoService.updateById(entity);
		}
		// 编辑文件列表
		fileService.update(bo.getFiles(), entity.getfId(), entity.getfId(), PunishConst.PUNISH_FILE);
		progressService.updateBatch(entity.getfId(), bo.getProgress());
		return entity.getfId();
	}

	/**
	 * 送审
	 * @param id
	 * @return id
	 */
	public Long submit(Long id) {
		ipunishInfoService.lambdaUpdate()
			.eq(FwPunishInfo::getfId, id)
			.set(FwPunishInfo::getfWorkFlowId, TypeEnum.ApprovalIng.getType())
			.set(FwPunishInfo::getfState, PunishConst.APPROVING)
			.update();
		return id;
	}

	/**
	 * 审批通过
	 * @param id
	 * @return id
	 */
	public Long approveConfirm(Long id) {
		ipunishInfoService.lambdaUpdate()
			.eq(FwPunishInfo::getfId, id)
			.set(FwPunishInfo::getfWorkFlowId, TypeEnum.Finish.getType())
			.set(FwPunishInfo::getfState, PunishConst.APPROVED)
			.update();
		return id;
	}
	
	/**
	 * 审批拒绝
	 * @param id
	 * @return id
	 */
	public Long approveRefuse(Long id) {
		ipunishInfoService.lambdaUpdate()
			.eq(FwPunishInfo::getfId, id)
			.set(FwPunishInfo::getfWorkFlowId, TypeEnum.Revert.getType())
			.set(FwPunishInfo::getfState, PunishConst.REFUSED)
			.update();
		return id;
	}

	/**
	 * 执行完成送审
	 * @param id
	 * @return id
	 */
	public Long completeSubmit(Long id) {
		ipunishInfoService.lambdaUpdate()
		.eq(FwPunishInfo::getfId, id)
		.set(FwPunishInfo::getfWorkFlowId, TypeEnum.ApprovalIng.getType())
		.set(FwPunishInfo::getfState, PunishConst.COMPLETE_APPROVING)
		.update();
		return id;
	}
	
	/**
	 * 执行完成审批通过
	 * @param id
	 * @return id
	 */
	public Long completeApproveConfirm(Long id) {
		ipunishInfoService.lambdaUpdate()
		.eq(FwPunishInfo::getfId, id)
		.set(FwPunishInfo::getfWorkFlowId, TypeEnum.Finish.getType())
		.set(FwPunishInfo::getfState, PunishConst.COMPLETE)
		.update();
		return id;
	}
	
	/**
	 * 执行完成审批拒绝
	 * @param id
	 * @return id
	 */
	public Long completeApproveRefuse(Long id) {
		ipunishInfoService.lambdaUpdate()
		.eq(FwPunishInfo::getfId, id)
		.set(FwPunishInfo::getfWorkFlowId, TypeEnum.Revert.getType())
		.set(FwPunishInfo::getfState, PunishConst.COMPLETE_REFUSED)
		.update();
		return id;
	}
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(Long id) {
		ipunishInfoService.removeById(id);
	}

	/**
	 * 批量删除数据
	 * @param ids
	 */
	public void delete(List<Long> ids) {
		ipunishInfoService.removeByIds(ids);
	}

	/**
	 * 废弃数据
	 * @param id
	 */
	public void discard(Long id) {
		ipunishInfoService.lambdaUpdate()
			.eq(FwPunishInfo::getfId, id)
			.set(FwPunishInfo::getfState, PunishConst.DISCARDED)
			.update();
	}

	public PunishInfoBo getById(Serializable id) {
		FwPunishInfo entity = ipunishInfoService.getById(id);
		PunishInfoBo bo = entityToBo(entity);
		if (bo == null) {
			return null;
		}
		List<PunishProgressBo> progress = progressService.getByPunishId(id);
		bo.setProgress(progress);
		List<PunishFileBo> files = fileService.getList(entity.getfId(), entity.getfId(), PunishConst.PUNISH_FILE);
		bo.setFiles(files);
		return bo;
	}

	/**
	 * 查询分页列表
	 * @param bo
	 * @return
	 */
	public IPage<PunishInfoBo> page(PunishQueryBo bo) {
		IPage<FwPunishInfo> page = new Page<>(bo.getCurrent(), bo.getSize());
		List<Long> orgList = userOrgService.getLoginUserChildren();
		IPage<FwPunishInfo> result = ipunishInfoService.lambdaQuery()
			// 处罚事由
			.like(StringUtils.checkValNotNull(bo.getCause()), FwPunishInfo::getfCause, bo.getCause())
			// 处罚日期
			.ge(StringUtils.checkValNotNull(bo.getPunishDateBegin()), FwPunishInfo::getfPunishDate, bo.getPunishDateBegin())
			.le(StringUtils.checkValNotNull(bo.getPunishDateEnd()), FwPunishInfo::getfPunishDate, bo.getPunishDateEnd())
			// 被罚企业ID
			.eq(bo.getCompanyId() != null, FwPunishInfo::getfCompanyId, bo.getCompanyId())
			// 被罚企业名称
			.like(StringUtils.checkValNotNull(bo.getCompanyName()), FwPunishInfo::getfCompanyName, bo.getCompanyName())
			// 状态
			.in(bo.getStateList() != null && bo.getStateList().size() > 0, FwPunishInfo::getfState, bo.getStateList())
			// 登录用户只能查询自身及下属企业的状况
//			.in(FwPunishInfo::getfCompanyId, orgList)
			.in(FwPunishInfo::getfReportOrgId, orgList)
			.page(page);
			
		return result.convert(PunishService::entityToBo);
	}

	private static FwPunishInfo boToEntity(PunishInfoBo bo) {
		if (bo == null) {
			return null;
		}
		FwPunishInfo entity = new FwPunishInfo();
		BeanUtils.copyProperties(bo, entity);
		try {
			if (StringUtils.checkValNotNull(bo.getfPunishDateString())) {
				entity.setfPunishDate(PunishConst.FORMAT.parse(bo.getfPunishDateString()));
			}
			if (StringUtils.checkValNotNull(bo.getfReportDateString())) {
				entity.setfReportDate(PunishConst.FORMAT.parse(bo.getfReportDateString()));
			}
		} catch (ParseException e) {
			throw new BaseException("日期格式异常", 400);
		}
		
		return entity;
	}

	private static PunishInfoBo entityToBo(FwPunishInfo entity) {
		if (entity == null) {
			return null;
		}
		PunishInfoBo bo = new PunishInfoBo();
		BeanUtils.copyProperties(entity, bo);
		Optional.ofNullable(entity.getfPunishDate()).ifPresent(e -> {
			bo.setfPunishDateString(PunishConst.FORMAT.format(e));
		});
		Optional.ofNullable(entity.getfReportDate()).ifPresent(e -> {
			bo.setfReportDateString(PunishConst.FORMAT.format(e));
		});
		return bo;
	}

}
