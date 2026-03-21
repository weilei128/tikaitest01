package com.pcitc.legalAffairs.dbService.authorize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.mapper.authorize.AuthorizeInfoMapper;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.vo.authorize.AuthorizeInfoVo;
import com.pctic.common.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IAuthorizeInfoServiceImpl extends ServiceImpl<AuthorizeInfoMapper, FwAuthorizeInfo>
        implements IAuthorizeInfoService {

    @Override
    public IPage<AuthorizeInfoVo> getPageByConditions(Page<AuthorizeInfoVo> page, AuthorizeQueryBo bo) {
        if (bo == null) {
    		bo = new AuthorizeQueryBo();
    	}
        return baseMapper.getPageByConditions(page, bo);
    }
    
    @Override
    public boolean updateById(FwAuthorizeInfo entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean updateByIdApproval(FwAuthorizeInfo entity) {    	
    	entity.setfUpdateTime(new Date());

    	return super.updateById(entity);
    }
    
    @Override
    public boolean save(FwAuthorizeInfo entity) {
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
    public LambdaUpdateChainWrapper<FwAuthorizeInfo> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwAuthorizeInfo> lambdaUpdate = super.lambdaUpdate()
			.set(FwAuthorizeInfo::getfUpdateId, userInfo.getfId())
			.set(FwAuthorizeInfo::getfUpdateUser, userInfo.getfAccount())
			.set(FwAuthorizeInfo::getfUpdateName, userInfo.getfCname())
			.set(FwAuthorizeInfo::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }
}