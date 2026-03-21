package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSearchBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeServiceImpl extends ServiceImpl<LitigateDisputeMapper, FwLitigateDispute> implements ILitigateDisputeService {

	@Override
	public IPage<DisputeVo> searchPage(Page<DisputeVo> page, DisputeSearchBo bo) {
    	if (bo == null) {
    		bo = new DisputeSearchBo();
    	}
        return baseMapper.searchData(page, bo);
    }
	
    @Override
    public boolean updateById(FwLitigateDispute entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateId(Long.valueOf(userInfo.getfId()));
    	entity.setfUpdateUser(userInfo.getfAccount());
    	entity.setfUpdateName(userInfo.getfCname());
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }
    
    @Override
    public boolean updateByIdApproval(FwLitigateDispute entity) {
    	entity.setfUpdateTime(new Date());
    	return super.updateById(entity);
    }

    @Override
    public boolean save(FwLitigateDispute entity) {
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
    public LambdaUpdateChainWrapper<FwLitigateDispute> lambdaUpdate() {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	LambdaUpdateChainWrapper<FwLitigateDispute> lambdaUpdate = super.lambdaUpdate()
			.set(FwLitigateDispute::getfUpdateId, userInfo.getfId())
			.set(FwLitigateDispute::getfUpdateUser, userInfo.getfAccount())
			.set(FwLitigateDispute::getfUpdateName, userInfo.getfCname())
			.set(FwLitigateDispute::getfUpdateTime, new Date());
    	return lambdaUpdate;
    }


}
