package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOppositeMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOpposite;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOppositeServiceImpl extends ServiceImpl<LitigateDisputeOppositeMapper, FwLitigateDisputeOpposite> implements ILitigateDisputeOppositeService {
	
    @Override
    public boolean updateById(FwLitigateDisputeOpposite entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOpposite entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfCreateId(Long.valueOf(userInfo.getfId()));
    	entity.setfCreateUser(userInfo.getfAccount());
    	entity.setfCreateName(userInfo.getfCname());
    	entity.setfCreateTime(new Date());
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	
    	return super.save(entity);
    }

    @Override
    public LambdaUpdateChainWrapper<FwLitigateDisputeOpposite> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOpposite> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOpposite::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOpposite::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOpposite::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOpposite::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
