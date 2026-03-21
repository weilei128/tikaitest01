package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeProgressService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeProgressMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeProgress;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeProgressServiceImpl extends ServiceImpl<LitigateDisputeProgressMapper, FwLitigateDisputeProgress> implements ILitigateDisputeProgressService {

    @Override
    public boolean updateById(FwLitigateDisputeProgress entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeProgress entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeProgress> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeProgress> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeProgress::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeProgress::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeProgress::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeProgress::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
