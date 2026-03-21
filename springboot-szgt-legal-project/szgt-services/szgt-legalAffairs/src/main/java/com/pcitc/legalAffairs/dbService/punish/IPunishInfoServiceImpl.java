package com.pcitc.legalAffairs.dbService.punish;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.punish.PunishInfoMapper;
import com.pcitc.legalAffairs.po.punish.FwPunishInfo;
import com.pctic.common.utils.UserUtils;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IPunishInfoServiceImpl extends ServiceImpl<PunishInfoMapper, FwPunishInfo> implements IPunishInfoService {
	
	@Override
	public boolean save(FwPunishInfo entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfCreateuser(userInfo.getfAccount());
    	entity.setfCreatename(userInfo.getfCname());
    	entity.setfCreatetime(new Date());
		return super.save(entity);
	}
	
	@Override
	public boolean saveBatch(Collection<FwPunishInfo> entityList) {
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
	public boolean updateById(FwPunishInfo entity) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateuser(userInfo.getfAccount());
    	entity.setfUpdatename(userInfo.getfCname());
    	entity.setfUpdatetime(new Date());
		return super.updateById(entity);
	}
}
