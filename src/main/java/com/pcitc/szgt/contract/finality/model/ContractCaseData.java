package com.pcitc.szgt.contract.finality.model;

import lombok.Data;

@Data
public class ContractCaseData {

	private String parentCode ;
	
	private int ftype ;
	
	private String startTime  ;
	
	private String endTime  ;
	
	private int orgId ;
	
	private int pageNum ;
	
	private int pageSize ;
}
