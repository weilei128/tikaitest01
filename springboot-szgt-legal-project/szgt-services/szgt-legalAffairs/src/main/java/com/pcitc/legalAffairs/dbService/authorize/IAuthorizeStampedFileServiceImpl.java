package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.mapper.authorize.AuthorizeStampedFileMapper;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeStampedFile;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IAuthorizeStampedFileServiceImpl extends ServiceImpl<AuthorizeStampedFileMapper, FwAuthorizeStampedFile> implements IAuthorizeStampedFileService {
	
    @Override
    public boolean updateById(FwAuthorizeStampedFile entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwAuthorizeStampedFile entity) {
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
    public LambdaUpdateChainWrapper<FwAuthorizeStampedFile> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwAuthorizeStampedFile> lambdaUpdate = super.lambdaUpdate()
			.set(FwAuthorizeStampedFile::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeStampedFile::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeStampedFile::getfUpdateName, userInfo.getfCname())
			.set(FwAuthorizeStampedFile::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }}