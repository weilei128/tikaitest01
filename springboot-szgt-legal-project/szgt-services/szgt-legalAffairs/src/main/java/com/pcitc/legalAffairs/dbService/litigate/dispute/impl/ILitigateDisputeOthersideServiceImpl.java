package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOthersideService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOthersideMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOtherside;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOthersideServiceImpl extends ServiceImpl<LitigateDisputeOthersideMapper, FwLitigateDisputeOtherside> implements ILitigateDisputeOthersideService {
	
    @Override
    public boolean updateById(FwLitigateDisputeOtherside entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOtherside entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOtherside> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOtherside> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOtherside::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOtherside::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOtherside::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOtherside::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }


}
