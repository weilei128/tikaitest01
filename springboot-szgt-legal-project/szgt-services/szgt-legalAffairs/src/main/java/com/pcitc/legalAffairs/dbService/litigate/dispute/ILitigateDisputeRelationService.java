package com.pcitc.legalAffairs.dbService.litigate.dispute;

import java.io.Serializable;
import java.util.List;

import com.pcitc.legalAffairs.po.dispute.FwLitigateDisputeRelation;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface ILitigateDisputeRelationService extends IBaseService<FwLitigateDisputeRelation> {
	public List<FwLitigateDisputeRelation> getByDisputeId(Serializable disputeId);
	
	public List<FwLitigateDisputeRelation> getByRelatedId(Serializable disputeId);

}
