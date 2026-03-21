package com.pcitc.legalAffairs.vo.Intermediary;

import java.util.Date;

import lombok.Data;

@Data
public class IntermediaryHireVo {
	private Long hireId;
	private String hireName;
	private Long intermediaryId;
	private String intermediaryName;
	private String lawyerName;
	private Integer hireType;
	private String hireWay;
	private String serviceCost;
	private String orgName;
	private String personName;
	private Date date; 
}
