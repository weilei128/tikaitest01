package com.pcitc.legalAffairs.service.punish;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.legalAffairs.bo.punish.PunishFileBo;
import com.pcitc.legalAffairs.dbService.punish.IPunishFileService;
import com.pcitc.legalAffairs.po.punish.FwPunishFile;

/**
 * 处罚相关附件信息Service
 * @author meihongli
 *
 */
@Service
public class PunishFileService {

	@Autowired
	private IPunishFileService iservice;
	
	public IPunishFileService getIService() {
		return this.iservice;
	}
	
	/**
	 * 更新文件列表
	 * 将对应文件列表更新为新的列表
	 * 
	 * @param bo <b>新的</b>文件列表 {@code bo == null}时不会变更文件列表
	 * @param punishId 处罚ID
	 * @param businessId 业务ID
	 * @param businessCode 业务代码
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void update(List<PunishFileBo> bo, Long punishId, Long businessId, String businessCode) {
		if (bo == null) {
			return;
		}
		iservice.lambdaUpdate()
			.eq(FwPunishFile::getFkPunishId, punishId)
			.eq(FwPunishFile::getFkBusinessId, businessId)
			.eq(FwPunishFile::getfBusinessCode, businessCode)
			.remove();
		List<FwPunishFile> entityList = boToEntity(bo, punishId, businessId, businessCode);
		iservice.saveBatch(entityList);
	}
	
	/**
	 * 增加文件
	 * 在文件列表中增加一个文件
	 * 
	 * @param bo
	 * @return
	 */
	public Long append(PunishFileBo bo) {
		FwPunishFile entity = boToEntity(bo);
		iservice.save(entity);
		return entity.getfId();
	}
	
	/**
	 * 增加文件
	 * 在文件列表中增加多个文件
	 * 
	 * @param bo
	 * @return
	 */
	public void append(List<PunishFileBo> bo) {
		List<FwPunishFile> entity = boToEntity(bo);
		iservice.saveBatch(entity);
	}
	
	/**
	 * 删除文件
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		iservice.removeById(id);
	}
	
	/**
	 * 删除businessID下的附件信息
	 * 
	 * @param punishId
	 * @param businessId
	 * @param businessCode
	 */
	public void deleteByBusiness(Long punishId, Long businessId, String businessCode) {
		iservice.lambdaUpdate()
			.eq(FwPunishFile::getFkPunishId, punishId)
			.eq(FwPunishFile::getFkBusinessId, businessId)
			.eq(FwPunishFile::getfBusinessCode, businessCode)
			.remove();
	}
	
	/**
	 * 删除处罚信息下的全部附件信息
	 * 
	 * @param punishId
	 * @param businessId
	 * @param businessCode
	 */
	public void deleteByPunish(Long punishId) {
		iservice.lambdaUpdate()
			.eq(FwPunishFile::getFkPunishId, punishId)
			.remove();
	}
	
	public List<PunishFileBo> getList(Long punishId, Long businessId, String businessCode) {
		List<FwPunishFile> list = iservice.lambdaQuery()
			.eq(FwPunishFile::getFkPunishId, punishId)
			.eq(FwPunishFile::getFkBusinessId, businessId)
			.eq(FwPunishFile::getfBusinessCode, businessCode)
			.list();
		return entityToBo(list);
	}
	
	public static FwPunishFile boToEntity(PunishFileBo bo) {
		if (bo == null) {
			return null;
		}
		FwPunishFile entity = new FwPunishFile();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}
	
	public static FwPunishFile boToEntity(PunishFileBo bo, Long punishId, Long businessId, String businessCode) {
		if (bo == null) {
			return null;
		}
		bo.setFkPunishId(punishId);
		bo.setFkBusinessId(businessId);
		bo.setfBusinessCode(businessCode);
		FwPunishFile entity = new FwPunishFile();
		BeanUtils.copyProperties(bo, entity);
		return entity;
	}
	
	public static List<FwPunishFile> boToEntity(List<PunishFileBo> bos) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(PunishFileService::boToEntity).collect(Collectors.toList());
	}
	
	public static List<FwPunishFile> boToEntity(List<PunishFileBo> bos, Long punishId, Long businessId, String businessCode) {
		if (bos == null) {
			return null;
		}
		return bos.stream().map(bo -> boToEntity(bo, punishId, businessId, businessCode)).collect(Collectors.toList());
	}

	public static PunishFileBo entityToBo(FwPunishFile entity) {
		if (entity == null) {
			return null;
		}
		PunishFileBo bo = new PunishFileBo();
		BeanUtils.copyProperties(entity, bo);
		return bo;
	}
	
	public static List<PunishFileBo> entityToBo(List<FwPunishFile> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(PunishFileService::entityToBo).collect(Collectors.toList());
	}
}
