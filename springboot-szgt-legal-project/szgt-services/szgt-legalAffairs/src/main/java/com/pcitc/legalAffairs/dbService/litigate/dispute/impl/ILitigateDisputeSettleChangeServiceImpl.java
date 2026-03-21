package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeSettleChangeService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeSettleChangeMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettleChange;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeSettleChangeServiceImpl extends ServiceImpl<LitigateDisputeSettleChangeMapper, FwLitigateDisputeSettleChange> implements ILitigateDisputeSettleChangeService {
	
    @Override
    public boolean updateById(FwLitigateDisputeSettleChange entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeSettleChange entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeSettleChange> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeSettleChange> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeSettleChange::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeSettleChange::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeSettleChange::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeSettleChange::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}
