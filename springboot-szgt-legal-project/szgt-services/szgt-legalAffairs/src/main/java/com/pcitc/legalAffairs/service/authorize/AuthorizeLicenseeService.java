package com.pcitc.legalAffairs.service.authorize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeLicenseeBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeLicenseeService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeLicensee;

/**
 * 授权申请 - 被授权人Service
 * 因该功能数据全部整合进授权申请主表, 本Service废弃
 * 
 * @author meihongli
 *
 */
@Deprecated
@Service
public class AuthorizeLicenseeService {

	@Autowired
	private IAuthorizeLicenseeService ilicenseeService;
	
	public void save(AuthorizeLicenseeBo bo, Long authorizeId) {
		List<FwAuthorizeLicensee> list = ilicenseeService.lambdaQuery().eq(FwAuthorizeLicensee::getFkAuthorizeId, authorizeId).list();
		if (list != null && list.size() > 0) {
			throw new BaseException("已存在其他被授权人", 400);
		}
		FwAuthorizeLicensee entity = boToEntity(bo, authorizeId);
		ilicenseeService.save(entity);
	}
	
	public void save(List<AuthorizeLicenseeBo> bo, Long authorizeId) {
		if (bo != null && bo.size() > 1) {
			throw new BaseException("被授权人过多", 400);
		}
		List<FwAuthorizeLicensee> list = ilicenseeService.lambdaQuery().eq(FwAuthorizeLicensee::getFkAuthorizeId, authorizeId).list();
		if (list != null && list.size() > 0) {
			throw new BaseException("已存在其他被授权人", 400);
		}
		List<FwAuthorizeLicensee> entity = boToEntity(bo, authorizeId);
		ilicenseeService.saveBatch(entity);
	}
	
	public void delete(Long id) {
		ilicenseeService.removeById(id);
	}
	
	public void delete(List<Long> id) {
		ilicenseeService.removeByIds(id);
	}
	
	public void deleteByAuthorizeId(Long id) {
		ilicenseeService.lambdaUpdate().eq(FwAuthorizeLicensee::getFkAuthorizeId, id).remove();
	}
	
	public void deleteByAuthorizeId(List<Long> id) {
		ilicenseeService.lambdaUpdate().in(FwAuthorizeLicensee::getFkAuthorizeId, id).remove();
	}
	
	public void update(AuthorizeLicenseeBo bo) {
		FwAuthorizeLicensee entity = boToEntity(bo);
		ilicenseeService.updateById(entity);
	}
	
	public void update(List<AuthorizeLicenseeBo> bo, Long authorizeId) {
		if (bo == null) {
			bo = new ArrayList<>();
		}
		List<AuthorizeLicenseeBo> toSave = bo.stream().filter(each -> each.getFId() == null).collect(Collectors.toList());
		
		List<AuthorizeLicenseeBo> toUpdate = bo.stream().filter(each -> each.getFId() != null).collect(Collectors.toList());
		toUpdate.stream().forEach(each -> update(each));
		
		List<Long> list = toUpdate.stream().map(AuthorizeLicenseeBo::getFId).collect(Collectors.toList());
		
		ilicenseeService.lambdaUpdate().notIn(FwAuthorizeLicensee::getfId, list).remove();
		
		save(toSave, authorizeId);
	}
	
	public List<AuthorizeLicenseeBo> getByAuthorizeId(String id) {
		List<FwAuthorizeLicensee> list = ilicenseeService.lambdaQuery().eq(FwAuthorizeLicensee::getFkAuthorizeId, id).list();
		return entityToBo(list);
	}
	
	public static FwAuthorizeLicensee boToEntity(AuthorizeLicenseeBo bo, Long authorizeId) {
		FwAuthorizeLicensee entity = new FwAuthorizeLicensee();
		BeanUtils.copyProperties(bo, entity);
		if (authorizeId != null) {
			entity.setFkAuthorizeId(authorizeId);
		}
		return entity;
	}
	
	public static FwAuthorizeLicensee boToEntity(AuthorizeLicenseeBo bo) {
		return boToEntity(bo, null);
	}
	
	public static List<FwAuthorizeLicensee> boToEntity(List<AuthorizeLicenseeBo> bo, Long authorizeId) {
		if (bo == null) {
			return null;
		}
		return bo.stream().map(each -> boToEntity(each, authorizeId)).collect(Collectors.toList());
	}
	
	public static List<FwAuthorizeLicensee> boToEntity(List<AuthorizeLicenseeBo> bo) {
		return boToEntity(bo);
	}
	
	public static AuthorizeLicenseeBo entityToBo(FwAuthorizeLicensee entity) {
		AuthorizeLicenseeBo bo = new AuthorizeLicenseeBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}
	
	public static List<AuthorizeLicenseeBo> entityToBo(List<FwAuthorizeLicensee> entity) {
		if (entity == null) {
			return null;
		}
		return entity.stream().map(each -> entityToBo(each)).collect(Collectors.toList());
	}
}
