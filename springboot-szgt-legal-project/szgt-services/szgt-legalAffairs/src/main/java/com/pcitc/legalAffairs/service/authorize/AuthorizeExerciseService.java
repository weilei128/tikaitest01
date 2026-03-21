package com.pcitc.legalAffairs.service.authorize;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeExerciseBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeExerciseQueryBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeExerciseService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeExercise;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行权录入Service
 * 
 * @author meihongli
 *
 */
@Service
public class AuthorizeExerciseService {

	@Autowired
	private IAuthorizeExerciseService iauthExerciseService;
	@Autowired
	private AuthorizeInfoService authorizeInfoService;

	/**
	 * 保存行权录入信息
	 * @param bo
	 */
	public Long save(AuthorizeExerciseBo bo) {
		if (bo == null || bo.getFkAuthorizeId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		if (authorizeInfoService.getById(bo.getFkAuthorizeId().toString()) == null) {
			throw new BaseException("找不到授权! ", 500);
		}
		if (getOneByAuthorizeId(bo.getFkAuthorizeId().toString()) != null) {
			throw new BaseException("行权报告已存在! ", 500);
		}
		FwAuthorizeExercise entity = boToEntity(bo);
		iauthExerciseService.save(entity);
		authorizeInfoService.exerciseFinish(bo.getFkAuthorizeId(), bo.getFStatus() == 1);
		return entity.getfId();
	}

	/**
	 * 编辑行权录入信息
	 * @param bo
	 */
	public Long update(AuthorizeExerciseBo bo) {
		if (bo == null || bo.getFId() == null || bo.getFkAuthorizeId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		if (authorizeInfoService.getById(bo.getFkAuthorizeId().toString()) == null) {
			throw new BaseException("找不到授权! ", 500);
		}
		FwAuthorizeExercise entity = boToEntity(bo);
		iauthExerciseService.updateById(entity);
		authorizeInfoService.exerciseFinish(bo.getFkAuthorizeId(), bo.getFStatus() == 1);
		return entity.getfId();
	}

	/**
	 * 暂存行权录入信息
	 * @param bo
	 */
	public Long saveTemp(AuthorizeExerciseBo bo) {
		if (bo == null || bo.getFkAuthorizeId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		if (authorizeInfoService.getById(bo.getFkAuthorizeId().toString()) == null) {
			throw new BaseException("找不到授权! ", 500);
		}
		if (getOneByAuthorizeId(bo.getFkAuthorizeId().toString()) != null) {
			throw new BaseException("行权报告已存在! ", 500);
		}
		FwAuthorizeExercise entity = boToEntity(bo);
		iauthExerciseService.save(entity);
		return entity.getfId();
	}
	
	/**
	 * 编辑暂存行权录入信息
	 * @param bo
	 */
	public Long updateTemp(AuthorizeExerciseBo bo) {
		if (bo == null || bo.getFId() == null || bo.getFkAuthorizeId() == null) {
			throw new BaseException("请求参数异常! ", 500);
		}
		if (authorizeInfoService.getById(bo.getFkAuthorizeId().toString()) == null) {
			throw new BaseException("找不到授权! ", 500);
		}
		FwAuthorizeExercise entity = boToEntity(bo);
		iauthExerciseService.updateById(entity);
		return entity.getfId();
	}
	
	/**
	 * 删除行权录入信息
	 * @param bo
	 */
	public void delete(Long id) {
		FwAuthorizeExercise entity = iauthExerciseService.getById(id);
		if (entity == null) {
			throw new BaseException("数据不存在或已被删除! ", 500);
		}
		iauthExerciseService.removeById(id);
	}

	/**
	 * 删除行权录入信息
	 * @param bo
	 */
	public void delete(List<Long> id) {
		iauthExerciseService.removeByIds(id);
	}

	/**
	 * 根据授权查找行权信息
	 * @param authorizeId
	 * @return
	 */
	public AuthorizeExerciseBo getOneByAuthorizeId(String authorizeId) {
		FwAuthorizeExercise entity = iauthExerciseService.getOne(iauthExerciseService.lambdaQueryWrapper().eq(FwAuthorizeExercise::getFkAuthorizeId, authorizeId));
		return entityToBo(entity);
	}
	
	/**
	 * 查询行权信息列表
	 * @param bo
	 * @return
	 */
	public IPage<AuthorizeExerciseBo> listExerciseInfo(AuthorizeExerciseQueryBo bo) {
		IPage<FwAuthorizeExercise> page = new Page<>(bo.getCurrent(), bo.getSize());
		IPage<FwAuthorizeExercise> result = iauthExerciseService.page(page, iauthExerciseService.lambdaQueryWrapper());
		return result.convert(entity -> entityToBo(entity));
	}

	public static AuthorizeExerciseBo entityToBo(FwAuthorizeExercise entity) {
		if (entity == null) {
			return null;
		}
		AuthorizeExerciseBo bo = new AuthorizeExerciseBo();
		BeanUtils.copyProperties(entity, bo);
		if (entity.getfCompleteDate() != null) {
			bo.setFCompleteDate(AuthorizeConsts.YMD.format(entity.getfCompleteDate()));
		}
		return bo;
	}

	public static FwAuthorizeExercise boToEntity(AuthorizeExerciseBo bo, Long authorizeId) {
		try {
			if (bo == null) {
				return null;
			}
			FwAuthorizeExercise entity = new FwAuthorizeExercise();
			BeanUtils.copyProperties(bo, entity);
			if (bo.getFCompleteDate() != null) {
				entity.setfCompleteDate(AuthorizeConsts.YMD.parse(bo.getFCompleteDate()));
			}
			if (authorizeId != null) {
				entity.setFkAuthorizeId(authorizeId);
			}
			return entity;
		} catch (ParseException e) {
			throw new BaseException("时间格式异常! ", 500);
		}
	}

	public static FwAuthorizeExercise boToEntity(AuthorizeExerciseBo bo) {
		return boToEntity(bo, null);
	}
	
	public static List<AuthorizeExerciseBo> entitiesToBoList(List<FwAuthorizeExercise> bos) {
		return bos.stream().map(bo -> entityToBo(bo)).collect(Collectors.toList());
	}
}
