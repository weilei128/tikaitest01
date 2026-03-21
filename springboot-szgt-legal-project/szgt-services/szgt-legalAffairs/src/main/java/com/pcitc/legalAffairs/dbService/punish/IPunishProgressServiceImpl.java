package com.pcitc.legalAffairs.dbService.punish;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.punish.PunishProgressMapper;
import com.pcitc.legalAffairs.po.punish.FwPunishProgress;
import com.pctic.common.utils.UserUtils;

@Service
public class IPunishProgressServiceImpl extends ServiceImpl<PunishProgressMapper, FwPunishProgress> implements IPunishProgressService {
	@Override
	public boolean save(FwPunishProgress entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfCreateuser(userInfo.getfAccount());
    	entity.setfCreatename(userInfo.getfCname());
    	entity.setfCreatetime(new Date());
		return super.save(entity);
	}
	
	@Override
	public boolean saveBatch(Collection<FwPunishProgress> entityList) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
		if (entityList != null) {
			entityList.forEach(entity -> {
		    	entity.setfCreateuser(userInfo.getfAccount());
		    	entity.setfCreatename(userInfo.getfCname());
		    	entity.setfCreatetime(new Date());
			});
		}
		return super.saveBatch(entityList);
	}
	
	@Override
	public boolean updateById(FwPunishProgress entity) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateuser(userInfo.getfAccount());
    	entity.setfUpdatename(userInfo.getfCname());
    	entity.setfUpdatetime(new Date());
		return super.updateById(entity);
	}

}
