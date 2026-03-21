package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IntermediaryQueryBo extends BaseEntity {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 统一社会信用代码
	 */
	private String uscCode;
	/**
	 * 中介类型
	 */
	private String type;
	/**
	 * 成立时间
	 */
	private Date establishDateBegin;
	/**
	 * 成立时间
	 */
	private Date establishDateEnd;
	/**
	 * 负责人
	 */
	private String officer;
	/**
	 * 注册地址
	 */
	private String registerAddress;
	/**
	 * 办公地址
	 */
	private String actualAddress;
	/**
	 * 律师人数
	 */
	private Integer lawyerNumMin;
	/**
	 * 律师人数
	 */
	private Integer lawyerNumMax;
	/**
	 * 合伙人人数
	 */
	private Integer partnerNumMin;
	/**
	 * 合伙人人数
	 */
	private Integer partnerNumMax;
	/**
	 * 专业领域编码
	 */
	private String professionCode;
	/**
	 * 常年法律顾问
	 */
	private Integer isAdvisor;
	/**
	 * 专利代理机构
	 */
	private Integer patentAgency;
	/**
	 * 商标代理机构
	 */
	private Integer tmAgency;
	/**
	 * 准入企业
	 */
	private Long handleOrgId;
	/**
	 * 准入时间
	 */
	private Date admitDateBegin;
	/**
	 * 准入时间
	 */
	private Date admitDateEnd;
	/**
	 * 工作流状态
	 */
	private Integer workFlowId;
	/**
	 * 是否启用
	 */
	private Integer state;
	/**
	 * 是否准入
	 */
	private Integer admit;
}
