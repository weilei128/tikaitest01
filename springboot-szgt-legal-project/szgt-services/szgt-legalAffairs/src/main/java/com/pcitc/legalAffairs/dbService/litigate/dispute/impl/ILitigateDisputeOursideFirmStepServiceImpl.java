package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideFirmStepService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOursideFirmStepMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirmStep;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOursideFirmStepServiceImpl extends ServiceImpl<LitigateDisputeOursideFirmStepMapper, FwLitigateDisputeOursideFirmStep> implements ILitigateDisputeOursideFirmStepService {
	
    @Override
    public boolean updateById(FwLitigateDisputeOursideFirmStep entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOursideFirmStep entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOursideFirmStep> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOursideFirmStep> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOursideFirmStep::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOursideFirmStep::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOursideFirmStep::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOursideFirmStep::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
