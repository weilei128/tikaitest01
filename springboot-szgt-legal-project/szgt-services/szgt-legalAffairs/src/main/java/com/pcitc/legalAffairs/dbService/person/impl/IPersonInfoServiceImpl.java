package com.pcitc.legalAffairs.dbService.person.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.bo.person.PersonQulificationSummaryBo;
import com.pcitc.legalAffairs.dbService.person.IPersonInfoService;
import com.pcitc.legalAffairs.mapper.person.PersonInfoMapper;
import com.pcitc.legalAffairs.po.person.FwPersonInfo;
import com.pctic.common.utils.UserUtils;

@Service
public class IPersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, FwPersonInfo> implements IPersonInfoService {

	@Override
	public List<PersonQulificationSummaryBo> qulificationSummary() {
		return baseMapper.qulificationSummary();
	}
	
    @Override
    public boolean updateById(FwPersonInfo entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwPersonInfo entity) {
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
    public LambdaUpdateChainWrapper<FwPersonInfo> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwPersonInfo> lambdaUpdate = super.lambdaUpdate()
			.set(FwPersonInfo::getfUpdateId, userInfo.getfId())
			.set(FwPersonInfo::getfUpdateUser, userInfo.getfAccount())
			.set(FwPersonInfo::getfUpdateName, userInfo.getfCname())
			.set(FwPersonInfo::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }

	
}