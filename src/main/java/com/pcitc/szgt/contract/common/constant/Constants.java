package com.pcitc.szgt.contract.common.constant;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final int SUCCESS = 200;
    public static final int FAILCODE = 500;
    public static final int NOTCODE = 404;

    public static final String UPDATE_SUCCESS_STRING = "修改成功";
    public static final String DELETE_SUCCESS_STRING = "删除成功";

    public static final Integer HandWorkAccord = 0;//手工依据
    public static final Integer HandWorkMaterial = 1;//手工物料
    public static final Integer ERPOrder = 2;//ERP订单

    //领导-秘书映射
    public static final Map<String, String> secretaryMap = new HashMap<>();

    static{
//        secretaryMap.put("de435454646", "10000158");
        secretaryMap.put("10000077", "10000265");
        secretaryMap.put("10000146", "10000488");
    }

    public static final String SEND_MESSAGE_AGENT = "您有待办信息未处理，请及时查看！";
    public static final String SEND_MESSAGE = "【合同系统】请及时处理：";

    //独立审批流程
    public static final String ROUTE_TYPE_APPROVE = "app" ;
    public static final String ROUTE_APPROVE_YES  = "appYes" ;
    //独立分发流程
    public static final String ROUTE_TYPE_DISPENSE = "dis" ;
    public static final String ROUTE_DISPENSE_YES = "disYes" ;
}
