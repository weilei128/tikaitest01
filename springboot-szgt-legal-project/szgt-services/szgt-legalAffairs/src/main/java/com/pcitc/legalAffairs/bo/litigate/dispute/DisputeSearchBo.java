package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.math.BigDecimal;
import java.util.List;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisputeSearchBo extends BaseEntity {

	/**
	 * 填报部门
	 */
	private String orgId;
	/**
	 * 纠纷环节
	 */
	private String status;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 是否结案
	 */
	private Integer settled;
	/**
	 * 是否涉外纠纷
	 */
	private Integer isForeignRelated;
	/**
	 * 案发时间
	 */
	private String begindate;
	/**
	 * 案发时间
	 */
	private String enddate;
	/**
	 * 纠纷类别
	 */
	private Integer type;
	/**
	 * 内外部纠纷
	 */
	private Integer isExternal;
	/**
	 * 处理方式
	 */
	private Integer settleMethod;
	/**
	 * 我方案件当事人
	 */
	private String oursideName;
	/**
	 * 相对方案件当事人
	 */
	private String oppositeName;
	/**
	 * 案件承办单位
	 */
	private String agencyName;
	/**
	 * 受理机构
	 */
	private String receivingAgency;
	/**
	 * 进展阶段
	 */
	private Integer step;
	/**
	 * 案件状态
	 */
	private Integer progressStatus;
	/**
	 * 结案时间
	 */
	private String closeDateBegin;
	/**
	 * 结案时间
	 */
	private String closeDateEnd;
	/**
	 * 涉及金额
	 */
	private BigDecimal relatedAmountMin;
	/**
	 * 涉及金额
	 */
	private BigDecimal relatedAmountMax;
	/**
	 * 预计损失
	 */
	private BigDecimal estimatedLossAmountMin;
	/**
	 * 预计损失
	 */
	private BigDecimal estimatedLossAmountMax;
	/**
	 * 是否债权清收
	 */
	private Integer isDebtCollection;
	/**
	 * 是否涉刑
	 */
	private Integer isCriminalInvolved;
	/**
	 * 挽回损失
	 */
	private BigDecimal lossRecoveredMin;
	/**
	 * 挽回损失
	 */
	private BigDecimal lossRecoveredMax;
	/**
	 * 避免损失
	 */
	private BigDecimal lossAvoidedMin;
	/**
	 * 避免损失
	 */
	private BigDecimal lossAvoidedMax;
	/**
	 * 是否废弃
	 */
	private Integer isDiscarded;
	
	/**
	 * 组织列表
	 */
	private List<Long> orgList;

	/**
	 * 法务查询权限表  集合
	 * */
	private List<Long> privilegeOrgIds;
}
