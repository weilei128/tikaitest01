package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeSettledService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeSettledMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeSettled;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeSettledServiceImpl extends ServiceImpl<LitigateDisputeSettledMapper, FwLitigateDisputeSettled> implements ILitigateDisputeSettledService {

    @Override
    public boolean updateById(FwLitigateDisputeSettled entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeSettled entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeSettled> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeSettled> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeSettled::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeSettled::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeSettled::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeSettled::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
