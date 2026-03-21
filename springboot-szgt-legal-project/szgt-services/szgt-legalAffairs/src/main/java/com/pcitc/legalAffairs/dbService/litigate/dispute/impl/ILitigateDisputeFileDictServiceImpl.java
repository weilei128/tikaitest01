package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeFileDictService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeFileDictMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeFileDict;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeFileDictServiceImpl extends ServiceImpl<LitigateDisputeFileDictMapper, FwLitigateDisputeFileDict> implements ILitigateDisputeFileDictService {

    @Override
    public boolean updateById(FwLitigateDisputeFileDict entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDisputeFileDict entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDisputeFileDict> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDisputeFileDict> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDisputeFileDict::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDisputeFileDict::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDisputeFileDict::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDisputeFileDict::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }


}
