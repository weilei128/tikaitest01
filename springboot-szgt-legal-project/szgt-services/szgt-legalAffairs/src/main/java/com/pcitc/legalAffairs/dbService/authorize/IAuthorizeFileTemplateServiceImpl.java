package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.authorize.AuthorizeFileTemplateMapper;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeFileTemplate;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IAuthorizeFileTemplateServiceImpl extends ServiceImpl<AuthorizeFileTemplateMapper, FwAuthorizeFileTemplate> implements IAuthorizeFileTemplateService {

    @Override
    public boolean updateById(FwAuthorizeFileTemplate entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwAuthorizeFileTemplate entity) {
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
    public LambdaUpdateChainWrapper<FwAuthorizeFileTemplate> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwAuthorizeFileTemplate> lambdaUpdate = super.lambdaUpdate()
			.set(FwAuthorizeFileTemplate::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeFileTemplate::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeFileTemplate::getfUpdateName, userInfo.getfCname())
			.set(FwAuthorizeFileTemplate::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}