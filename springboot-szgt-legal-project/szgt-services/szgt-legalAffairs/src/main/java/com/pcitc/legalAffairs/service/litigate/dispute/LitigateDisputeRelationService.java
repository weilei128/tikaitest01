package com.pcitc.legalAffairs.service.litigate.dispute;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeRelationService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeRelation;

@Service
public class LitigateDisputeRelationService {

	@Autowired
	private ILitigateDisputeRelationService iservice;
	
	public ILitigateDisputeRelationService getIService() {
		return this.iservice;
	}
	
	/**
	 * 变更关联关系
	 * @param disputeId
	 * @param relatedIds
	 */
	public void update(Long disputeId, List<Long> relatedIds) {
		if (relatedIds == null) {
			return;
		}
		dropRelation(disputeId);
		appendRelation(disputeId, relatedIds);
	}
	
	/**
	 * 删除关联关系
	 * @param disputeId
	 */
	public void dropRelation(Long disputeId) {
		iservice.lambdaUpdate().eq(FwLitigateDisputeRelation::getFkDisputeId, disputeId).remove();
	}
	
	/**
	 * 删除关联关系
	 * @param disputeId
	 */
	public void dropRelation(Long disputeId, List<Long> relatedIds) {
		iservice.lambdaUpdate()
			.eq(FwLitigateDisputeRelation::getFkDisputeId, disputeId)
			.in(FwLitigateDisputeRelation::getFkRelatedId, relatedIds)
			.remove();
	}
	
	/**
	 * 追加关联关系
	 * @param disputeId
	 * @param relatedIds
	 */
	public void appendRelation(Long disputeId, List<Long> relatedIds) {
		if (relatedIds == null) {
			return;
		}

		List<FwLitigateDisputeRelation> entities = relatedIds.stream().distinct().map(relatedId -> {
			FwLitigateDisputeRelation entity = new FwLitigateDisputeRelation();
			entity.setFkDisputeId(disputeId);
			entity.setFkRelatedId(relatedId);
			return entity;
		}).collect(Collectors.toList());
		iservice.saveBatch(entities);
	}
	
	/**
	 * 获取关联单ID列表
	 * @param disputeId
	 * @return
	 */
	public List<Long> getRelatedIds(Serializable disputeId) {
		List<FwLitigateDisputeRelation> list = iservice.getByDisputeId(disputeId);
		return list.stream().map(FwLitigateDisputeRelation::getFkRelatedId).collect(Collectors.toList());
	}
}
