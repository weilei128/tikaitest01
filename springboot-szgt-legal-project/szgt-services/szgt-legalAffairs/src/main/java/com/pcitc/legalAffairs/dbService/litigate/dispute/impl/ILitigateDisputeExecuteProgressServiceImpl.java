package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeExecuteProgressService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeExecuteProgressMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecuteProgress;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeExecuteProgressServiceImpl extends ServiceImpl<LitigateDisputeExecuteProgressMapper, FwLitigateDisputeExecuteProgress> implements ILitigateDisputeExecuteProgressService {



    @Override
    public boolean updateById(FwLitigateDisputeExecuteProgress entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeExecuteProgress entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeExecuteProgress> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeExecuteProgress> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeExecuteProgress::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeExecuteProgress::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeExecuteProgress::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeExecuteProgress::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}
