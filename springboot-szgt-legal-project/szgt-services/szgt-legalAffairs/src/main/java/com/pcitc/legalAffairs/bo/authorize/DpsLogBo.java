package com.pcitc.legalAffairs.bo.authorize;

import java.util.Date;

import lombok.Data;

@Data
public class DpsLogBo {
	
	private String appId;
	private String businessId;
	private String businessName;
	private Integer dataState;
	private Date executeDate;
	private String executeId;
	private String executeResult;
	private String executeResultName;
	private Long executorId;
	private String executorName;
	private String opinion;
	private String opinionId;
	private String taskId;
	private Long taskType;
	
}
