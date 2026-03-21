package com.pcitc.legalAffairs.bo.person;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PersonQulificationSummaryBo {

	/**
	 * 组织层级
	 */
	private Integer orgLevel;
	/**
	 * 专职法律人员数
	 */
	private Integer personNumber;
	/**
	 * 持证人员数
	 */
	private Integer qulificationNumber;
	/**
	 * 持证率
	 */
	private BigDecimal qulificationRate;
}
