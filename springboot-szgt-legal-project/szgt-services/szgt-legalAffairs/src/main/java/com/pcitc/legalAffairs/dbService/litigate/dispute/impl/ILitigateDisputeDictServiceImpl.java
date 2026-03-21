package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeDictService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeDictMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeDict;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeDictServiceImpl extends ServiceImpl<LitigateDisputeDictMapper, FwLitigateDisputeDict> implements ILitigateDisputeDictService {

    @Override
    public boolean updateById(FwLitigateDisputeDict entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeDict entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeDict> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeDict> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeDict::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeDict::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeDict::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeDict::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}
