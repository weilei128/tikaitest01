package com.pcitc.legalAffairs.dbService.person.impl;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.person.IPersonResumeService;
import com.pcitc.legalAffairs.mapper.person.PersonResumeMapper;
import com.pcitc.legalAffairs.po.person.FwPersonResume;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IPersonResumeServiceImpl extends ServiceImpl<PersonResumeMapper, FwPersonResume> implements IPersonResumeService {
	
    @Override
    public boolean updateById(FwPersonResume entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwPersonResume entity) {
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
    public LambdaUpdateChainWrapper<FwPersonResume> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwPersonResume> lambdaUpdate = super.lambdaUpdate()
			.set(FwPersonResume::getfUpdateId, userInfo.getfId())
			.set(FwPersonResume::getfUpdateUser, userInfo.getfAccount())
			.set(FwPersonResume::getfUpdateName, userInfo.getfCname())
			.set(FwPersonResume::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}