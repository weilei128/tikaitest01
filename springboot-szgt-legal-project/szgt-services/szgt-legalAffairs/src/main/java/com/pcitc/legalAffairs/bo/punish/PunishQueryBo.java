package com.pcitc.legalAffairs.bo.punish;

import java.util.List;

import com.pcitc.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 处罚信息查询条件
 * 
 * @author meihongli
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PunishQueryBo extends BaseEntity {
	
	/**
	 * 处罚事由(fuzzy)
	 */
	private String cause;

	/**
	 * 处罚日期 - 开始时间
	 */
	private String punishDateBegin;

	/**
	 * 处罚日期 - 结束时间
	 */
	private String punishDateEnd;

	/**
	 * 被处罚企业ID
	 */
	private Long companyId;

	/**
	 * 被处罚企业名称(fuzzy)
	 */
	private String companyName;
	
	private List<Integer> stateList;
}
