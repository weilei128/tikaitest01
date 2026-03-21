package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeRelationDropBo {

	private Long disputeId;
	
	private List<Long> relatedIds;
}
