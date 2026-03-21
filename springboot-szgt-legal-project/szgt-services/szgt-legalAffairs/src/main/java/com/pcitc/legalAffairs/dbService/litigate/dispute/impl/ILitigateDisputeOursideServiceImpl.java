package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeOursideService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeOursideMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeOurside;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeOursideServiceImpl extends ServiceImpl<LitigateDisputeOursideMapper, FwLitigateDisputeOurside> implements ILitigateDisputeOursideService {

    @Override
    public boolean updateById(FwLitigateDisputeOurside entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeOurside entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeOurside> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeOurside> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeOurside::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeOurside::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeOurside::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeOurside::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
