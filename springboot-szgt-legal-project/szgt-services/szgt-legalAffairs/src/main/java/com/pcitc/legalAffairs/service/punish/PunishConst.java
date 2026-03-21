package com.pcitc.legalAffairs.service.punish;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 处罚信息相关常量
 * 
 * @author meihongli
 */
public class PunishConst {

	/**
	 * 日期格式
	 */
	public static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 状态 - 草稿 - 0
	 */
	public static final Integer DRAFT = 0;
	/**
	 * 状态 - 审批中 - 1
	 */
	public static final Integer APPROVING = 1;
	/**
	 * 状态 - 审批通过 - 2
	 */
	public static final Integer APPROVED = 2;
	/**
	 * 状态 - 审批拒绝 - 3
	 */
	public static final Integer REFUSED = 3;
	/**
	 * 状态 - 作废 - 4
	 */
	public static final Integer DISCARDED = 4;
	
	/**
	 * 状态 - 处理完毕审批中 - 5
	 */
	public static final Integer COMPLETE_APPROVING = 5;
	
	/**
	 * 状态 - 处理完毕审批完成 - 6
	 */
	public static final Integer COMPLETE = 6;
	
	/**
	 * 状态 - 处理完毕审批拒绝 - 7
	 */
	public static final Integer COMPLETE_REFUSED = 7;

	/**
	 * businessCode: 处罚处理情况 - fawu_punish_progress
	 */
	public static final String PROGRESS = "fawu_punish_progress";
	
	/**
	 * businessCode: 行政处罚决定书 - fawu_punish_file
	 */
	public static final String PUNISH_FILE = "fawu_punish_file";
}
