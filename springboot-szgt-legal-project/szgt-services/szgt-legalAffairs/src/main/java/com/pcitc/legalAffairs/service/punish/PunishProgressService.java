package com.pcitc.legalAffairs.service.punish;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.punish.PunishProgressBo;
import com.pcitc.legalAffairs.dbService.punish.IPunishProgressService;
import com.pcitc.legalAffairs.po.punish.FwPunishProgress;

/**
 * 处罚执行相关Service
 * @author HoLiX
 *
 */
@Service
public class PunishProgressService {

	@Autowired
	private IPunishProgressService ipunishProgressService;
	@Autowired
	private PunishFileService fileService;

	public IPunishProgressService getIService() {
		return this.ipunishProgressService;
	}
	
	/**
	 * 保存或编辑
	 * 
	 * @param bo
	 * @return fid
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Long saveOrUpdate(PunishProgressBo bo) {
		if (bo == null) {
			throw new BaseException("参数异常", 400);
		}
		if (bo.getFkPunishId() == null) {
			throw new BaseException("参数异常", 400);
		}
		FwPunishProgress entity = boToEntity(bo);
		if (entity.getfId() == null) {
			ipunishProgressService.save(entity);
		} else {
			ipunishProgressService.updateById(entity);
		}
		fileService.update(bo.getFiles(), bo.getFkPunishId(), entity.getfId(), PunishConst.PROGRESS);
		return entity.getfId();
	}
	
	public void updateBatch(Long punishId, List<PunishProgressBo> bos) {
		if (punishId == null) {
			throw new BaseException("参数异常", 400);
		}
		if (bos == null) {
			return;
		}
		bos = bos.stream().map(e -> {
			e.setFkPunishId(punishId);
			return e;
		}).collect(Collectors.toList());
		List<Long> updates = bos.stream().filter(e -> e.getfId() != null).map(PunishProgressBo::getfId).collect(Collectors.toList());
		ipunishProgressService.lambdaUpdate()
			.eq(FwPunishProgress::getFkPunishId, punishId)
			.notIn(updates != null && updates.size() > 0, FwPunishProgress::getfId, updates)
			.remove();
		bos.forEach(e -> saveOrUpdate(e));
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id) {
		if (id == null) {
			return;
		}
		ipunishProgressService.removeById(id);
	}
	
	/**
	 * 删除处罚中全部处理情况
	 * 
	 * @param id
	 */
	public void deleteByPunishId(Long id) {
		if (id == null) {
			return;
		}
		ipunishProgressService.lambdaUpdate().eq(FwPunishProgress::getFkPunishId, id).remove();
	}
	
	/**
	 * 通过ID获取数据
	 * @param id
	 * @return
	 */
	public PunishProgressBo getById(Serializable id) {
		PunishProgressBo bo = entityToBo(ipunishProgressService.getById(id));
		if (bo != null) {
			bo.setFiles(fileService.getList(bo.getFkPunishId(), bo.getfId(), PunishConst.PROGRESS));
		}
		return bo;
	}
	
	/**
	 * 通过处罚ID获取数据列表
	 * @param punishId
	 * @return
	 */
	public List<PunishProgressBo> getByPunishId(Serializable punishId) {
		return entityToBo(ipunishProgressService.lambdaQuery().eq(FwPunishProgress::getFkPunishId, punishId).orderByDesc(FwPunishProgress::getfCreatetime).list()).stream().map(bo -> {
			bo.setFiles(fileService.getList(bo.getFkPunishId(), bo.getfId(), PunishConst.PROGRESS));
			return bo;
		}).collect(Collectors.toList());
	}

	public static FwPunishProgress boToEntity(PunishProgressBo bo) {
		try {
			if (bo == null) {
				return null;
			}
			FwPunishProgress entity = new FwPunishProgress();
			BeanUtils.copyProperties(bo, entity);
			if (StringUtils.checkValNotNull(bo.getfDate())) {
				entity.setfDate(PunishConst.FORMAT.parse(bo.getfDate()));
			}
			return entity;
		} catch (ParseException e) {
			throw new BaseException("日期格式异常", 400);
		}
	}
	
	public static PunishProgressBo entityToBo(FwPunishProgress entity) {
		if (entity == null) {
			return null;
		}
		PunishProgressBo bo = new PunishProgressBo();
		BeanUtils.copyProperties(entity, bo);
		if (entity.getfDate() != null) {
			bo.setfDate(PunishConst.FORMAT.format(entity.getfDate()));
		}
		return bo;
	}
	
	public static List<PunishProgressBo> entityToBo(List<FwPunishProgress> entity) {
		if (entity == null) {
			return null;
		}
		return entity.stream().map(PunishProgressService::entityToBo).collect(Collectors.toList());
	}
}
