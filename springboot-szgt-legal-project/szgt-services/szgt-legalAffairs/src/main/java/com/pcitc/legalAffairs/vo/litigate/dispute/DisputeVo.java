package com.pcitc.legalAffairs.vo.litigate.dispute;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DisputeVo {

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 填报部门名称
	 */
	private String orgName;
	/**
	 * 案发时间
	 */
	private String date;
	/**
	 * 处理方式
	 */
	private Long settleMethod;
	/**
	 * 是否办结 0-否 1-是
	 */
	private Integer closed;
	/**
	 * 内外部纠纷
	 */
	private Long isExternal;
	/**
	 * 纠纷性质
	 */
	private Long isMajor;
	/**
	 * 纠纷类型
	 */
	private String type;
	private String typeName;
	/**
	 * 纠纷类型
	 */
	private String type2;
	/**
	 * 纠纷类型
	 */
	private String type3;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 案由
	 */
	private String cause;
	/**
	 * 涉案金额
	 */
	private BigDecimal relatedAmount;
	/**
	 * 我方当事人
	 */
	private String ourside;
	/**
	 * 对方当事人
	 */
	private String opposite;

	/***
	 * 工作流状态id(1.诉求争议录入审批完成 2.诉求争议录入审批退回 3.纠纷填报审批完成 4.纠纷填报审批退回)
	 */
	private Integer fWorkFlowId;
	
	private Integer status;
	
	/**
	 * 关联单统计
	 */
	private Integer relationCount;
	
	/**
	 * 案号统计
	 */
	private Integer caseCodeCount;
}
