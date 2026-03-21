package com.pcitc.legalAffairs.dbService.litigate.dispute.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeRelationService;
import com.pcitc.legalAffairs.mapper.litigate.dispute.LitigateDisputeRelationMapper;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeRelation;
import com.pctic.common.utils.UserUtils;

@Service
public class ILitigateDisputeRelationServiceImpl extends ServiceImpl<LitigateDisputeRelationMapper, FwLitigateDisputeRelation> implements ILitigateDisputeRelationService {

	public List<FwLitigateDisputeRelation> getByDisputeId(Serializable disputeId) {
		return lambdaQuery().eq(FwLitigateDisputeRelation::getFkDisputeId, disputeId).list();
	}
	
	public List<FwLitigateDisputeRelation> getByRelatedId(Serializable disputeId) {
		return lambdaQuery().eq(FwLitigateDisputeRelation::getFkRelatedId, disputeId).list();
	}
	
	
	@Override
	public boolean save(FwLitigateDisputeRelation entity) {
    	SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfCreateuser(userInfo.getfAccount());
    	entity.setfCreatename(userInfo.getfCname());
    	entity.setfCreatetime(new Date());
		return super.save(entity);
	}
	
	@Override
	public boolean saveBatch(Collection<FwLitigateDisputeRelation> entityList) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
		if (entityList != null) {
			entityList.forEach(entity -> {
		    	entity.setfCreateuser(userInfo.getfAccount());
		    	entity.setfCreatename(userInfo.getfCname());
		    	entity.setfCreatetime(new Date());
			});
		}
		return super.saveBatch(entityList);
	}
	
	@Override
	public boolean updateById(FwLitigateDisputeRelation entity) {
		SysUserInfo userInfo = UserUtils.getUserInfo();
    	
    	entity.setfUpdateuser(userInfo.getfAccount());
    	entity.setfUpdatename(userInfo.getfCname());
    	entity.setfUpdatetime(new Date());
		return super.updateById(entity);
	}

}
