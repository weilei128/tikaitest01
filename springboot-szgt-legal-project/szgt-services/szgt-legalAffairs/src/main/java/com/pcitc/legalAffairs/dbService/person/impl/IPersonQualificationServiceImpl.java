package com.pcitc.legalAffairs.dbService.person.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.person.IPersonQualificationService;
import com.pcitc.legalAffairs.mapper.person.PersonQualificationMapper;
import com.pcitc.legalAffairs.po.person.FwPersonQualification;
import com.pctic.common.utils.UserUtils;

@Service
public class IPersonQualificationServiceImpl extends ServiceImpl<PersonQualificationMapper, FwPersonQualification> implements IPersonQualificationService {
	
    @Override
    public boolean updateById(FwPersonQualification entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(userInfo.getfId());
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwPersonQualification entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfCreateId(userInfo.getfId());
    	entity.setfCreateUser(userInfo.getfAccount());
    	entity.setfCreateName(userInfo.getfCname());
    	entity.setfCreateTime(new Date());
    	entity.setfUpdateId(userInfo.getfId());
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	
    	return super.save(entity);
    }

    @Override
    public LambdaUpdateChainWrapper<FwPersonQualification> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwPersonQualification> lambdaUpdate = super.lambdaUpdate()
			.set(FwPersonQualification::getfUpdateId, userInfo.getfId())
			.set(FwPersonQualification::getfUpdateUser, userInfo.getfAccount())
			.set(FwPersonQualification::getfUpdateName, userInfo.getfCname())
			.set(FwPersonQualification::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

}