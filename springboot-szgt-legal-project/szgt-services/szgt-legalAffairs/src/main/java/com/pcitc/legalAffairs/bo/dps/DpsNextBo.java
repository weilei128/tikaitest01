package com.pcitc.legalAffairs.bo.dps;

import java.util.List;

import lombok.Data;

@Data
public class DpsNextBo {
	
	private String activityId;
	private String instanceId;
	private String participantId;
	private String userId;
	private String userName;
	/**
	 * 所属组织
	 */
	private List<String> orgName;
	/**
	 * 用户职位
	 */
	private String position;
}
