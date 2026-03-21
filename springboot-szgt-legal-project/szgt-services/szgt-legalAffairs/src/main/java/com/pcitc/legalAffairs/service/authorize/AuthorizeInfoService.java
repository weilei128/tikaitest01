package com.pcitc.legalAffairs.service.authorize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pcitc.legalAffairs.dbService.fwPrivilegeInfo.IPrivilegeInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeInfoBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeInfoService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.legalAffairs.vo.authorize.AuthorizeInfoVo;
import com.pctic.common.utils.UserUtils;

/**
 * 授权申请Service
 * 
 * @author meihongli
 *
 */
@Service
public class AuthorizeInfoService {

	private static final DateFormat YEAR = new SimpleDateFormat("yy");
	// private static final DateFormat YEAR4DIGITS = new SimpleDateFormat("yy");

	@Autowired
	private IAuthorizeInfoService iauthInfoService;
//	@Autowired
//	private AuthorizeLicenseeService licenseeService;
	@Autowired
	private AuthorizeExerciseService exerciseService;
	@Autowired
	private AuthorizeFileService fileService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private IPrivilegeInfoService fwPrivilegeInfoService;
	/**
	 * 临时保存
	 * @param bo
	 * @return
	 */
	public Long saveTemp(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.TEMP);
		// 生成流水号
		entity.setfSerialNo(generateSerialNo());
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		iauthInfoService.save(entity);
		Long authorizeId = entity.getfId();
//		if (bo.getLicensee() != null) {
//			licenseeService.save(bo.getLicensee(), authorizeId);
//		}
		return authorizeId;
	}
	
	/**
	 * 保存
	 * @param bo
	 * @return
	 */
	public Long save(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVING);
		// 生成流水号
		entity.setfSerialNo(generateSerialNo());
		entity.setfApplyTime(new Date());
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		iauthInfoService.save(entity);
		Long authorizeId = entity.getfId();
		
//		if (bo.getLicensee() != null) {
//			licenseeService.save(bo.getLicensee(), authorizeId);
//		}
		return authorizeId;
	}
	
	/**
	 * 编辑并暂存
	 * @param bo
	 * @return
	 */
	public Long updateTemp(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.TEMP);
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		Long authorizeId = entity.getfId();
		iauthInfoService.updateById(entity);
		
//		licenseeService.update(bo.getLicensee(), authorizeId);
		return authorizeId;
	}
	
	/**
	 * 编辑并保存
	 * @param bo
	 * @return
	 */
	public Long update(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVING);
		entity.setfApplyTime(new Date());
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		Long authorizeId = entity.getfId();
		iauthInfoService.updateById(entity);
		
//		licenseeService.update(bo.getLicensee(), authorizeId);
		return authorizeId;
	}
	
	/**
	 * 编辑并暂存, 不处理被授权人信息
	 * @param bo
	 * @return
	 */
	public Long updateTempNoLicensee(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.TEMP);
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		iauthInfoService.updateById(entity);
		Long authorizeId = entity.getfId();
		
		return authorizeId;
	}
	
	/**
	 * 编辑并保存, 不处理被授权人信息
	 * @param bo
	 * @return
	 */
	public Long updateNoLicensee(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = boToEntity(bo);
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVING);
		entity.setfUserId(UserUtils.getUserInfo().getfId());
		entity.setfApplyTime(new Date());
		iauthInfoService.updateById(entity);
		Long authorizeId = entity.getfId();
		
		return authorizeId;
	}
	
	/**
	 * 申请审批通过
	 * @param id
	 */
	public void approvePass(Long id) {
		FwAuthorizeInfo entity = iauthInfoService.getById(id);
		if (entity == null) {
			throw new BaseException("找不到该单据!", 400);
		}
		entity.setfWorkFlowId(TypeEnum.Finish.getType());
		if (entity.getfUseStamp() == 1) {
			entity.setfStatus(AuthorizeConsts.AuthorizeStatus.EXERCISING);
		} else {
			entity.setfStatus(AuthorizeConsts.AuthorizeStatus.AUTHORIZATION_HANDLING);
		}
		// 生成授权书编号
		entity.setfAuthorizationNo(generateAuthorizationNo(entity));
		iauthInfoService.updateByIdApproval(entity);
	}
	
	/**
	 * 申请审批拒绝
	 * @param id
	 */
	public void approveRefuse(Long id) {
		FwAuthorizeInfo entity = iauthInfoService.getById(id);
		if (entity == null) {
			throw new BaseException("找不到该单据!", 400);
		}
		entity.setfWorkFlowId(TypeEnum.Revert.getType());
		entity.setfStatus(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVE_REFUSED);
		iauthInfoService.updateByIdApproval(entity);
	}
	
	/**
	 * 废弃
	 * @param id
	 */
	public void discard(Long id) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
		iauthInfoService.lambdaUpdate()
			.eq(FwAuthorizeInfo::getfId, id)
			.set(FwAuthorizeInfo::getfIsDiscard, AuthorizeConsts.AuthorizeDiscard.DISCARDED)
			.set(FwAuthorizeInfo::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeInfo::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeInfo::getfUpdateTime, new Date())
			.update();
	}
	
	/**
	 * 行权完成
	 * @param id
	 * @param exercised 是否已行权
	 */
	public void exerciseFinish(Long id, boolean exercised) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
		LambdaUpdateChainWrapper<FwAuthorizeInfo> wrapper = iauthInfoService.lambdaUpdate()
			.eq(FwAuthorizeInfo::getfId, id);
		if (exercised) {
			wrapper.set(FwAuthorizeInfo::getfStatus, AuthorizeConsts.AuthorizeStatus.EXERCISED)
				.set(FwAuthorizeInfo::getfUpdateId, userInfo.getfId())
				.set(FwAuthorizeInfo::getfUpdateUser, userInfo.getfAccount())
				.set(FwAuthorizeInfo::getfUpdateTime, new Date())
				.update();
		} else {
			wrapper.set(FwAuthorizeInfo::getfStatus, AuthorizeConsts.AuthorizeStatus.NOT_EXERCISED)
				.set(FwAuthorizeInfo::getfUpdateId, userInfo.getfId())
				.set(FwAuthorizeInfo::getfUpdateUser, userInfo.getfAccount())
				.set(FwAuthorizeInfo::getfUpdateTime, new Date())
				.update();
		}
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id) {
		iauthInfoService.removeById(id);
//		licenseeService.deleteByAuthorizeId(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(List<Long> ids) {
		iauthInfoService.removeByIds(ids);
//		licenseeService.deleteByAuthorizeId(ids);
	}
	
	public AuthorizeInfoBo getById(String id) {
		FwAuthorizeInfo entity = iauthInfoService.getById(id);
		AuthorizeInfoBo bo = entityToBo(entity);
//		bo.setLicensee(licenseeService.getByAuthorizeId(id));
		bo.setStampedFile(fileService.findByAuthorizeId(id));
		bo.setExercise(exerciseService.getOneByAuthorizeId(id));
		return bo;
	}
	
	/**
	 * 列表查询
	 * @param bo status - 状态; size - 每页条目数; current - 页码;
	 * @return
	 */
	public IPage<AuthorizeInfoBo> pageAuthorizeInfo(AuthorizeQueryBo bo) {
		IPage<FwAuthorizeInfo> page = new Page<>(bo.getCurrent(), bo.getSize());
		Integer userId = UserUtils.getUserInfo().getfId();
		IPage<FwAuthorizeInfo> result = iauthInfoService.lambdaQuery()
				.in(bo.getStatus() != null, FwAuthorizeInfo::getfStatus, bo.getStatus())
				// 授权申请暂存
				.eq(
					bo.getStatus().contains(AuthorizeConsts.AuthorizeStatus.TEMP) || bo.getStatus().contains(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVING), 
					FwAuthorizeInfo::getfUserId, 
					userId
				)
				// 授权书办理
				.eq(
					bo.getStatus().contains(AuthorizeConsts.AuthorizeStatus.AUTHORIZATION_HANDLING), 
					FwAuthorizeInfo::getfAuthorizationPrintPersonId,
					userId
				)
				// 行权录入
				.eq(
					bo.getStatus().contains(AuthorizeConsts.AuthorizeStatus.EXERCISING), 
					FwAuthorizeInfo::getfExerciseReporterId, 
					userId
				)
				.orderByDesc(FwAuthorizeInfo::getfUpdateTime)
				.page(page);
		IPage<AuthorizeInfoBo> convert = result.convert(entity -> entityToBo(entity));
		return convert;
	}
	
	/**
	 * 
	 * @param bo
	 * @return
	 */
	public IPage<AuthorizeInfoVo> authorizeQuery(AuthorizeQueryBo bo) {
		Page<AuthorizeInfoVo> page = new Page<>(bo.getCurrent(), bo.getSize());
		bo.setOrgList(userOrgService.getLoginUserChildren());
		//当前登录人 法务查询权限表 组织机构id   2020年9月29日
//		List<Long> longs = fwPrivilegeInfoService.queryOrgIdByUserId(Long.valueOf(UserUtils.getUserInfo().getfId()));
//		if(null!=longs){
//			bo.setPrivilegeOrgIds(longs);
//		}
		IPage<AuthorizeInfoVo> result = iauthInfoService.getPageByConditions(page, bo);
		return result;
	}
	
	/**
	 * 生成流水号
	 * @return
	 */
	public String generateSerialNo() {
		String year = YEAR.format(new Date());
		LambdaQueryWrapper<FwAuthorizeInfo> wrapper = iauthInfoService.lambdaQueryWrapper()
			.likeRight(FwAuthorizeInfo::getfSerialNo, year)
			.orderByDesc(FwAuthorizeInfo::getfSerialNo)
			.last("LIMIT 1");
		FwAuthorizeInfo last = iauthInfoService.getOne(wrapper);
		int serial = 1;
		if (last != null) {
			String c = last.getfSerialNo().substring(last.getfSerialNo().length() - 6, last.getfSerialNo().length());
			serial = Integer.valueOf(c) + 1;
		}
		String serialNo = year + String.format("%08d", serial);
		return serialNo;
	}

	/**
	 * 生成授权书编号
	 * @param entity
	 * @return
	 */
	public String generateAuthorizationNo(FwAuthorizeInfo entity) {
		String orgName = entity.getFkAuthorizerOrgName();
		String authorizeType = entity.getfType();
		String word = "授字";
		int yearInt = LocalDate.now().getYear();
		String year = String.valueOf(yearInt);
		StringBuffer serialNo = new StringBuffer()
			.append(orgName).append("-")
			.append(authorizeType).append("-")
			.append(word).append("-")
			.append(year).append("-");
		LambdaQueryWrapper<FwAuthorizeInfo> wrapper = iauthInfoService.lambdaQueryWrapper()
			.likeRight(FwAuthorizeInfo::getfAuthorizationNo, serialNo.toString())
			.orderByDesc(FwAuthorizeInfo::getfAuthorizationNo)
			.last("LIMIT 1");
		FwAuthorizeInfo last = iauthInfoService.getOne(wrapper);
		int serial = 1;
		if (last != null) {
			String c = last.getfSerialNo().substring(last.getfSerialNo().length() - 6, last.getfSerialNo().length());
			serial = Integer.valueOf(c) + 1;
		}
		serialNo.append(String.format("%04d", serial)).append("号");
		return serialNo.toString();
	}

	public static FwAuthorizeInfo boToEntity(AuthorizeInfoBo bo) {
		FwAuthorizeInfo entity = new FwAuthorizeInfo();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}
	
	public static AuthorizeInfoBo entityToBo(FwAuthorizeInfo entity) {
		AuthorizeInfoBo bo = new AuthorizeInfoBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}
	
	public static List<AuthorizeInfoBo> entitiesToBoList(List<FwAuthorizeInfo> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(entity -> entityToBo(entity)).collect(Collectors.toList());
	}
}
