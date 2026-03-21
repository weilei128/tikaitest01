package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.authorize.AuthorizeLicenseeMapper;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeLicensee;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IAuthorizeLicenseeServiceImpl extends ServiceImpl<AuthorizeLicenseeMapper, FwAuthorizeLicensee> implements IAuthorizeLicenseeService {

    
    @Override
    public boolean updateById(FwAuthorizeLicensee entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwAuthorizeLicensee entity) {
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
    public LambdaUpdateChainWrapper<FwAuthorizeLicensee> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwAuthorizeLicensee> lambdaUpdate = super.lambdaUpdate()
			.set(FwAuthorizeLicensee::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeLicensee::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeLicensee::getfUpdateName, userInfo.getfCname())
			.set(FwAuthorizeLicensee::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}