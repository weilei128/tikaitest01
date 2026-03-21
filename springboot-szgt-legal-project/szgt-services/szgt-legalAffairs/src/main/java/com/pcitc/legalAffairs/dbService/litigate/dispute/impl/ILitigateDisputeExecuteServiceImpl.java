package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeExecuteService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeExecuteMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeExecute;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeExecuteServiceImpl extends ServiceImpl<LitigateDisputeExecuteMapper, FwLitigateDisputeExecute> implements ILitigateDisputeExecuteService {

    @Override
    public boolean updateById(FwLitigateDisputeExecute entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeExecute entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeExecute> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeExecute> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeExecute::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeExecute::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeExecute::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeExecute::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}
