package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.authorize.AuthorizeExerciseMapper;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeExercise;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IAuthorizeExerciseServiceImpl extends ServiceImpl<AuthorizeExerciseMapper, FwAuthorizeExercise> implements IAuthorizeExerciseService {

    @Override
    public boolean updateById(FwAuthorizeExercise entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwAuthorizeExercise entity) {
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
    public LambdaUpdateChainWrapper<FwAuthorizeExercise> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwAuthorizeExercise> lambdaUpdate = super.lambdaUpdate()
			.set(FwAuthorizeExercise::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeExercise::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeExercise::getfUpdateName, userInfo.getfCname())
			.set(FwAuthorizeExercise::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}