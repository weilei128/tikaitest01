package com.pcitc.legalAffairs.service.authorize;

import java.util.List;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeInfoBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeStampedFileBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeInfoService;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeStampedFileService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeStampedFile;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 授权书相关Service
 * 
 * @author meihongli
 */
@Service
public class AuthorizeFileService {

	@Autowired
	private IAuthorizeInfoService iauthorizeService;
	@Autowired
	private IAuthorizeStampedFileService istampedFileService;
	@Autowired
	private AuthorizeInfoService authorizeService;

	/**
	 * 临时保存已盖章授权文件信息
	 * @param bo
	 */
	public void saveTemp(AuthorizeStampedFileBo bo) {
		Long authorizeId = bo.getFkAuthorizeId();
		if (authorizeId == null) {
			throw new BaseException("参数异常", 400);
		}
		if (findByAuthorizeId(authorizeId.toString()) != null) {
			throw new BaseException("授权文件信息已存在", 400);
		}
		FwAuthorizeStampedFile entity = boToEntity(bo);
		entity.setfStatus((byte) 0);
		istampedFileService.save(entity);
	}

	/**
	 * 保存已盖章授权文件信息并进入行权环节
	 * @param bo
	 */
	public void saveAndConfirm(AuthorizeStampedFileBo bo) {
		Long authorizeId = bo.getFkAuthorizeId();
		if (authorizeId == null) {
			throw new BaseException("参数异常", 400);
		}
		if (findByAuthorizeId(authorizeId.toString()) != null) {
			throw new BaseException("授权文件信息已存在", 400);
		}

		FwAuthorizeStampedFile entity = boToEntity(bo);
		entity.setfStatus((byte) 1);
		istampedFileService.save(entity);
		// 更改主表中的标记
		iauthorizeService.lambdaUpdate()
			.eq(FwAuthorizeInfo::getfId, bo.getFkAuthorizeId())
			.set(FwAuthorizeInfo::getfStatus, AuthorizeConsts.AuthorizeStatus.EXERCISING)
			.update();
	}


	/**
	 * 编辑已盖章授权文件并保存
	 * @param bo
	 */
	public void updateTemp(AuthorizeStampedFileBo bo) {
		if (bo.getFId() == null) {
			saveTemp(bo);
			return;
		}
		FwAuthorizeStampedFile entity = boToEntity(bo);
		entity.setfStatus((byte) 0);
		istampedFileService.updateById(entity);
	}


	/**
	 * 编辑已盖章授权文件信息并进入行权环节
	 * @param bo
	 */
	public void updateAndConfirm(AuthorizeStampedFileBo bo) {
		if (bo.getFId() == null) {
			saveAndConfirm(bo);
			return;
		}
		FwAuthorizeStampedFile entity = boToEntity(bo);
		entity.setfStatus((byte) 1);
		istampedFileService.save(entity);
		// 更改主表中的标记
		iauthorizeService.lambdaUpdate()
			.eq(FwAuthorizeInfo::getfId, bo.getFkAuthorizeId())
			.set(FwAuthorizeInfo::getfStatus, AuthorizeConsts.AuthorizeStatus.EXERCISING)
			.update();
	}

	public void deleteById(Long id) {
		istampedFileService.removeById(id);
	}

	public void deleteById(List<Long> id) {
		istampedFileService.removeByIds(id);
	}

	public void deleteByAuthorizeId(Long authorizeId) {
		istampedFileService.lambdaUpdate()
			.eq(FwAuthorizeStampedFile::getFkAuthorizeId, authorizeId)
			.remove();
	}

	public AuthorizeStampedFileBo findById(String id) {
		FwAuthorizeStampedFile entity = istampedFileService.getById(id);
		return entityToBo(entity);
	}

	public AuthorizeStampedFileBo findByAuthorizeId(String authorizeId) {
		FwAuthorizeStampedFile entity = istampedFileService.getOne(istampedFileService.lambdaQueryWrapper()
			.eq(FwAuthorizeStampedFile::getFkAuthorizeId, authorizeId));
		return entityToBo(entity);
	}

	/**
	 * 废弃授权申请单
	 * @param authorizeId
	 */
	public void discard(Long authorizeId) {
		authorizeService.discard(authorizeId);
	}

	/**
	 * 授权书打印人移交
	 * @param bo
	 */
	public void printPersonTransfer(AuthorizeInfoBo bo) {
		Long id = bo.getFId();
		if (id == null) {
			throw new BaseException("参数异常", 400);
		}
		iauthorizeService.lambdaUpdate()
			.eq(FwAuthorizeInfo::getfId, id)
			.set(FwAuthorizeInfo::getfAuthorizationPrintPersonId, bo.getFAuthorizationPrintPersonId())
			.set(FwAuthorizeInfo::getfAuthorizationPrintPersonName, bo.getFAuthorizationPrintPersonName())
			.update();
	}

	public static FwAuthorizeStampedFile boToEntity(AuthorizeStampedFileBo bo) {
		if (bo == null) {
			return null;
		}
		FwAuthorizeStampedFile entity = new FwAuthorizeStampedFile();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}

	public static AuthorizeStampedFileBo entityToBo(FwAuthorizeStampedFile entity) {
		if (entity == null) {
			return null;
		}
		AuthorizeStampedFileBo bo = new AuthorizeStampedFileBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}
}