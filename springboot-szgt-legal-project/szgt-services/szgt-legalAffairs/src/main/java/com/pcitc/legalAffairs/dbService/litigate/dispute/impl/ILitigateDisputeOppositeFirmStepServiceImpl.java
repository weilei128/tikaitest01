package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeFirmStepService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOppositeFirmStepMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirmStep;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOppositeFirmStepServiceImpl extends ServiceImpl<LitigateDisputeOppositeFirmStepMapper, FwLitigateDisputeOppositeFirmStep> implements ILitigateDisputeOppositeFirmStepService {
	
	
    @Override
    public boolean updateById(FwLitigateDisputeOppositeFirmStep entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOppositeFirmStep entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOppositeFirmStep> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOppositeFirmStep> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOppositeFirmStep::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOppositeFirmStep::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOppositeFirmStep::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOppositeFirmStep::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }


}
