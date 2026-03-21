package com.pcitc.legalAffairs.dbService.person.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.person.IPersonHonorService;
import com.pcitc.legalAffairs.mapper.person.PersonHonorMapper;
import com.pcitc.legalAffairs.po.person.FwPersonHonor;
import com.pctic.common.utils.UserUtils;

@Service
public class IPersonHonorServiceImpl extends ServiceImpl<PersonHonorMapper, FwPersonHonor> implements IPersonHonorService {

    @Override
    public boolean updateById(FwPersonHonor entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwPersonHonor entity) {
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
    public LambdaUpdateChainWrapper<FwPersonHonor> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwPersonHonor> lambdaUpdate = super.lambdaUpdate()
			.set(FwPersonHonor::getfUpdateId, userInfo.getfId())
			.set(FwPersonHonor::getfUpdateUser, userInfo.getfAccount())
			.set(FwPersonHonor::getfUpdateName, userInfo.getfCname())
			.set(FwPersonHonor::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}