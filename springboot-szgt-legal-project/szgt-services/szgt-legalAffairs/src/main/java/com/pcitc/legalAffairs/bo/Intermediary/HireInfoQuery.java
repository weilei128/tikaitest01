package com.pcitc.legalAffairs.bo.Intermediary;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HireInfoQuery extends BaseEntity {
	private String hireName;
	private String intermediaryName;
	private Integer hireType;
	private String hireWay;
	private String lawyerName;
}
