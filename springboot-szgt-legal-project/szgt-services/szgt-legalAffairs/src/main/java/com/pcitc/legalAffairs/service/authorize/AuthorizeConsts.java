package com.pcitc.legalAffairs.service.authorize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 授权功能使用的常量
 * 
 *
 */
public class AuthorizeConsts {

    /**
     * 时间转换格式
     * @author meihongli
     */
    public static final DateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * 事项授权单状态
     * @author meihongli
     *
     */
    public static final class AuthorizeStatus {
    	
    	/**
    	 * 0 - 暂存
    	 */
    	public static final Integer TEMP = 0;
    	/**
    	 * 1 - 申请审批中
    	 */
    	public static final Integer APPLICATION_APPROVING = 1;
    	/**
    	 * 2 - 授权书办理中
    	 */
    	public static final Integer AUTHORIZATION_HANDLING = 2;
    	/**
    	 * 3 - 授权申请被拒绝
    	 */
    	public static final Integer APPLICATION_APPROVE_REFUSED = 3;
    	/**
    	 * 4 - 正在行权
    	 */
    	public static final Integer EXERCISING = 4;
    	/**
    	 * 5 - 行权完成: 已行权
    	 */
    	public static final Integer EXERCISED = 5;
    	/**
    	 * 5 - 行权完成: 未行权
    	 */
    	public static final Integer NOT_EXERCISED = 5;
    }
    
    /**
     * 事项授权单废弃状态
     * @author meihongli
     *
     */
    public static class AuthorizeDiscard {
    	/**
    	 * 0 - 未废弃
    	 */
    	public static final int NOT_DISCARDED = 0;
    	/**
    	 * 1 - 已废弃
    	 */
    	public static final int DISCARDED = 1;
    }
}
