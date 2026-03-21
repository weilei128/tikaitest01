package com.pcitc.legalAffairs.bo.authorize;

import java.util.List;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizeQueryBo extends BaseEntity {

	/**
	 * 授权人姓名
	 */
	private String authorizerName;
	/**
	 * 被授权人姓名
	 */
	private String licenseeName;
	/**
	 * 经办人姓名
	 */
	private String managerName;
	/**
	 * 授权类型
	 */
	private String authorizeType;
	/**
	 * 申请时间 - 开始
	 */
	private String applyDateBegin;
	/**
	 * 申请时间 - 结束
	 */
	private String applyDateEnd;
	/**
	 * 授权期限
	 */
	private String authorizeLimit;
	/**
	 * 授权状态
	 */
	private List<Integer> status;
	/**
	 * 查询范围
	 */
	private Long managerOrgId;
	/**
	 * 行权报告录入
	 */
	private Byte hasExercise;
	/**
	 * 是否废弃
	 */
	private Byte isDiscard;
	
	/**
	 * 工作流状态
	 */
	private Integer workFlowId;
	
	private List<Long> orgList;

	/**
	 * 法务查询权限表  集合
	 * */
	private List<Long> privilegeOrgIds;
}
