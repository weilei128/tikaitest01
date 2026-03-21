package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideFirmService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOursideFirmMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOursideFirm;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOursideFirmServiceImpl extends ServiceImpl<LitigateDisputeOursideFirmMapper, FwLitigateDisputeOursideFirm> implements ILitigateDisputeOursideFirmService {
	
    @Override
    public boolean updateById(FwLitigateDisputeOursideFirm entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOursideFirm entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOursideFirm> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOursideFirm> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOursideFirm::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOursideFirm::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOursideFirm::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOursideFirm::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }


}
