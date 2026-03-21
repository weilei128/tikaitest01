package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOppositeFirmService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOppositeFirmMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOppositeFirm;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOppositeFirmServiceImpl extends ServiceImpl<LitigateDisputeOppositeFirmMapper, FwLitigateDisputeOppositeFirm> implements ILitigateDisputeOppositeFirmService {
	
    @Override
    public boolean updateById(FwLitigateDisputeOppositeFirm entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOppositeFirm entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOppositeFirm> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOppositeFirm> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOppositeFirm::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOppositeFirm::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOppositeFirm::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOppositeFirm::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
