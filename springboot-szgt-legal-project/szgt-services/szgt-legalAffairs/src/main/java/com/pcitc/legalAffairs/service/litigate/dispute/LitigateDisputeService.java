package com.pcitc.legalAffairs.service.litigate.dispute;

import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.entity.Result;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeFileDictBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOthersideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOursideFirmBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSearchBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettleChangeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettledBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOthersideService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOpposite;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOtherside;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOurside;
import com.pcitc.legalAffairs.service.dict.DictionaryService;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import com.pctic.common.utils.DateUtils;
import com.pctic.common.utils.UserUtils;

import static com.pcitc.legalAffairs.service.litigate.dispute.DisputePojoConverter.*;
import static com.pcitc.legalAffairs.service.litigate.dispute.DisputeStatus.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 诉前争议Service
 * 
 * @author meihongli
 *
 */
@Service
public class LitigateDisputeService {

	@Value("${dps.url}")
	private String sysUrl;
	@Autowired
	private DpsHttpService dpsHttpService;
	@Autowired
	private ILitigateDisputeService idisputeService;
	@Autowired
	private ILitigateDisputeOursideService idisputeOursideService;
	@Autowired
	private ILitigateDisputeOppositeService idisputeOppositeService;
	@Autowired
	private ILitigateDisputeOthersideService idisputeOthersideService;
	@Autowired
	private LitigateDisputeOursideService oursideService;
	@Autowired
	private LitigateDisputeOppositeService oppositeService;
	@Autowired
	private LitigateDisputeOthersideService othersideService;
	@Autowired
	private LitigateDisputeLawFirmService lawFirmService;
	@Autowired
	private LitigateDisputeProgressService progressService;
	@Autowired
	private LitigateDisputeSettleMethodChangeService settleMethodService;
	@Autowired
	private LitigateDisputeSettleService settleService;
	@Autowired
	private LitigateDisputeExecuteService executeService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private LitigateDisputeFileDictService fileService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private LitigateDisputeRelationService relationService;

	/**
	 * 保存诉前争议信息及我方、对方、第三方信息(若有)
	 * 
	 * @param bo
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Long save(DisputeBo bo) {
		if (bo == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		FwLitigateDispute entity = boToEntity(bo);
		entity.setfCode(codeGenerator("S", entity.getFkReportedOrgId(), bo.getFType(), bo.getFType2(), bo.getFType3(), false));
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		entity.setfSaveTime(new Date());
		entity.setfArchived(1);
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
			relationService.update(id, relatedIds);
		}

		fileService.save(bo.getFiles(), id, DisputeAttachEnum.DISPUTE, id);
		return id;
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
		idisputeOursideService
				.remove(idisputeOursideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOurside::getFkDisputeId, id));
		idisputeOppositeService.remove(
				idisputeOppositeService.lambdaUpdateWrapper().eq(FwLitigateDisputeOpposite::getFkDisputeId, id));
		idisputeOthersideService.remove(
				idisputeOthersideService.lambdaUpdateWrapper().eq(FwLitigateDisputeOtherside::getFkDisputeId, id));
		return true;
	}

	@Transactional(rollbackFor = Throwable.class)
	public boolean remove(List<String> ids) {
		if (ids == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		idisputeService.removeByIds(ids);
		idisputeOursideService
				.remove(idisputeOursideService.lambdaUpdateWrapper().in(FwLitigateDisputeOurside::getFkDisputeId, ids));
		idisputeOppositeService.remove(
				idisputeOppositeService.lambdaUpdateWrapper().in(FwLitigateDisputeOpposite::getFkDisputeId, ids));
		idisputeOthersideService.remove(
				idisputeOthersideService.lambdaUpdateWrapper().in(FwLitigateDisputeOtherside::getFkDisputeId, ids));
		return true;
	}

	@Transactional(rollbackFor = Throwable.class)
	public Long update(DisputeBo bo) {
		if (bo == null || bo.getFId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		Long id = bo.getFId();
		String name = bo.getFName();
		FwLitigateDispute entity = boToEntity(bo);
		idisputeService.updateById(entity);
		// 更新子表
		oursideService.updateBatch(bo.getOurside(), id, name);
		oppositeService.updateBatch(bo.getOpposite(), id, name);
		othersideService.updateBatch(bo.getOtherside(), id, name);
		if (bo.getRelated() != null) {
			List<Long> relatedIds = bo.getRelated().stream().map(DisputeVo::getId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
			relationService.update(id, relatedIds);
		}

		fileService.save(bo.getFiles(), id, DisputeAttachEnum.DISPUTE, id);

		return id;
	}

	public DisputeBo getbyId(String id) {
		DisputeBo bo = getByIdWithoutRelated(id);
		if (bo == null) {
			return null;
		}
		List<DisputeVo> related = getRelatedDisputes(id);
		
		bo.setRelated(related);
		return bo;
	}
	
	/**
	 * 无关联单的getById
	 * @param id
	 * @return
	 */
	public DisputeBo getByIdWithoutRelated(String id) {
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		DisputeBo bo = entityToBo(entity);
		if (bo == null) {
			return null;
		}
		List<DisputeOursideBo> ourside = oursideService.getByDisputeId(id);
		List<DisputeOppositeBo> opposite = oppositeService.getByDisputeId(id);
		List<DisputeOthersideBo> otherside = othersideService.getByDisputeId(id);
		List<DisputeOursideFirmBo> oursideFirms = lawFirmService.getOursideLawFirms(Long.valueOf(id));
		List<DisputeOppositeFirmBo> oppositeFirms = lawFirmService.getOppositeLawFirms(Long.valueOf(id));
		List<DisputeSettleChangeBo> settleChange = settleMethodService.getByDisputeId(id);
		List<DisputeProgressBo> progress = progressService.getByDisputeId(id, false);
		DisputeSettledBo settleInfo = settleService.getSettleInfo(id);
		DisputeExecuteBo exec = executeService.getExecuteInfo(id);
		List<DisputeFileDictBo> files = fileService.getFilesByType(Long.valueOf(id), DisputeAttachEnum.DISPUTE, Long.valueOf(id));
		
		bo.setOurside(ourside);
		bo.setOpposite(opposite);
		bo.setOtherside(otherside);
		bo.setOurFirm(oursideFirms);
		bo.setOppositeFirm(oppositeFirms);
		bo.setSettleChanges(settleChange);
		bo.setProgresses(progress);
		bo.setSettleInfo(settleInfo);
		bo.setExecuteInfo(exec);
		bo.setFiles(files);
		
		return bo;
	}
	
	public List<DisputeVo> getRelatedDisputes(Serializable id) {
		return relationService.getRelatedIds(id)
			.stream()
			// 关联单不能再取下级关联单 防止出现循环关联的单据
			.map(relatedId -> getByIdWithoutRelated(relatedId.toString()))
			.map(DisputePojoConverter::disputeBoToVo)
			.collect(Collectors.toList());
	}
	
	public IPage<DisputeBo> queryList(DisputeQueryBo bo) {
		IPage<FwLitigateDispute> page = new Page<>(bo.getCurrent(), bo.getSize());
		LambdaQueryWrapper<FwLitigateDispute> wrapper = idisputeService.lambdaQueryWrapper()
				.like(StringUtils.checkValNotNull(bo.getCode()), FwLitigateDispute::getfCode, bo.getCode())
				.like(StringUtils.checkValNotNull(bo.getName()), FwLitigateDispute::getfName, bo.getName())
				.in(FwLitigateDispute::getfStatus, PRE_LITIGATE_SETTLING, PRE_LITIGATE_SETTLED, PRE_LITIGATE_APPROVING, LITIGATE, PRE_LITIGATE_RETURN)
				.eq(bo.getDiscarded() != null, FwLitigateDispute::getfIsDiscard, bo.getDiscarded())
				.eq(bo.getStatus() != null, FwLitigateDispute::getfStatus, bo.getStatus())
				.and(w -> w
						.eq(FwLitigateDispute::getfUserId, UserUtils.getUserInfo().getfId())
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
	 * 办结诉前争议单据
	 * <br/><br/>
	 * <b>注意: </b>已进入诉讼和纠纷填报单据不适用该方法
	 * @param bo
	 */
	public void finish(DisputeBo bo) {
		if (bo == null || bo.getFId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		Long id = bo.getFId();
		String cause = bo.getFSettleDescription();
		String settleDateString = bo.getFSettleDate();
		if (StringUtils.checkValNull(cause)) {
			throw new BaseException("请填写办结信息! ", 500);
		}
		if (StringUtils.checkValNull(settleDateString)) {
			throw new BaseException("请填写办结时间! ", 500);
		}
		FwLitigateDispute entity = idisputeService.getById(id);
		if (entity == null) {
			throw new BaseException("单据不存在或已删除! ", 500);
		}
		// 诉前办结
		entity.setfStatus(DisputeStatus.PRE_LITIGATE_SETTLED);
		entity.setfSettleDescription(cause);
		try {
			entity.setfSettleDate(DateUtils.stringToDate(settleDateString, FORMAT));
		} catch (ParseException e) {
			throw new BaseException("时间格式异常! ", 500);
		}
		idisputeService.updateById(entity);
	}
	
	/**
	 * 纠纷查询
	 * @param bo
	 * @return
	 */
	public IPage<DisputeVo> searchList(DisputeSearchBo bo) {
		Page<DisputeVo> page = new Page<>(bo.getCurrent(), bo.getSize());
		bo.setOrgList(userOrgService.getLoginUserChildren());
		//当前登录人 法务查询权限表 组织机构id   2020年9月29日
		bo.setPrivilegeOrgIds(privilegeInfoService.privilegedOrgs());
		return idisputeService.searchPage(page, bo).convert(vo -> {
			vo.setTypeName(DictionaryService.queryDictionaryById(vo.getType()));
			return vo;
		});
	}
	
	public String codeGenerator(String prefix, Long orgid, String type, String type2, String type3, boolean includeType) {
		DateFormat format = new SimpleDateFormat("yy");
		String year = format.format(new Date());
		StringBuilder b = new StringBuilder();
		String orgcode = getOrgCode(orgid);
		b.append("-").append(orgcode).append("-").append(year).append("-");
		if (includeType) {
			b.append(type).append(type2 == null? "": type2).append(type3 == null? "": type3).append("-");
		}
		FwLitigateDispute last = idisputeService.getOne(idisputeService.lambdaQueryWrapper()
			.like(FwLitigateDispute::getfCode, b.toString())
			.last("order by substring(f_Code, 2) desc limit 1"));
		Integer count = 1;
		if (last != null && last.getfCode() != null) {
			String c = last.getfCode().substring(last.getfCode().length() - 4, last.getfCode().length());
			count = Integer.valueOf(c) + 1;
		}
		StringBuilder b0 = new StringBuilder(prefix);
		b0.append(b).append(String.format("%04d", count));
		return b0.toString();
	}
	
	public String getOrgCode(Long orgid) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganizationById";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		headers.set("Authorization", "bearer 6bdb848c-9f32-4c88-bd39-ccfe1ed0a14f");

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("organizationId", orgid);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}

		Map res = (Map) result.getData();
		if (Integer.parseInt(res.get("fType").toString()) != 0) {
			return res.get("fCode").toString();
		}
		
		String code = res.get("fCode").toString();
		Integer type = 0;
		do {
			if (res.get("fkParentId") == null || "".equals(res.get("fkParentId").toString()) || "0".equals(res.get("fkParentId").toString())) {
				break;
			}
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
			map1.add("organizationId", res.get("fkParentId"));
			HttpEntity<MultiValueMap<String, Object>> entity1 = new HttpEntity<>(map1, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, entity1, Result.class);
			result = response.getBody();
			if (!result.isSuccess()) {
				throw new BaseException(result.getMsg(), result.getCode());
			}

			res = (Map) result.getData();
			code = res.get("fCode").toString();
			type = Integer.parseInt(res.get("fType").toString());
		} while (type == 0);
		return code;
	}
	
}
