package com.pcitc.legalAffairs.service.litigate.dispute;

/**
 * 纠纷状态
 * 纠纷环节
 * @author meihongli
 *
 */
public class DisputeStatus {

	/**
	 * 诉前争议 - 在办 0
	 */
	public static final Byte PRE_LITIGATE_SETTLING = 0;
	/**
	 * 诉前争议 - 办结 1
	 */
	public static final Byte PRE_LITIGATE_SETTLED = 1;
	/**
	 * 诉前争议 - 审批 2
	 */
	public static final Byte PRE_LITIGATE_APPROVING = 2;
	/**
	 * 诉前争议 - 进入诉讼 3
	 */
	public static final Byte LITIGATE = 3;
	/**
	 * 纠纷填报 4
	 */
	public static final Byte DISPUTE_REPORTING = 4;
	/**
	 * 纠纷填报审查审批 5
	 */
	public static final Byte DISPUTE_APPROVING = 5;
	/**
	 * 纠纷办理 6
	 */
	public static final Byte DISPUTE_SETTLING = 6;
	/**
	 * 纠纷结案 - 执行 7
	 */
	public static final Byte DISPUTE_SETTLED_EXECUTING = 7;
	/**
	 * 纠纷结案 - 关闭 8
	 */
	public static final Byte DISPUTE_SETTLED_CLOSED = 8;

	/**
	 * 诉前争议单据退回 9
	 */
	public static final Byte PRE_LITIGATE_RETURN = 9;
	
	/**
	 * 纠纷单据退回 10
	 */
	public static final Byte DISCARDED_RETURN = 10;
	
	/**
	 * 单据废弃
	 */
	public static final Byte DISCARDED = 1;


}
