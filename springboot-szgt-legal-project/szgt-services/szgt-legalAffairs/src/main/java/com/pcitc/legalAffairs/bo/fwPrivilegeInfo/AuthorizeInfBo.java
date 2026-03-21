package com.pcitc.legalAffairs.bo.fwPrivilegeInfo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeInfBo {

    /**
     * ID
     */
    private Long id;
    /**
     * 流水序号
     */
    private String serialNo;
    /**
     * 授权书编号
     */
    private String authorizationNo;
    /**
     * 工作流状态
     */
    private Integer workFlowId;
    /**
     * 授权人属性
     */
    private String authorizerProperty;
    /**
     * 授权人姓名
     */
    private String authorizerName;
    /**
     * 申请人姓名
     */
    private String managerName;
    /**
     * 被授权人姓名
     */
    private String licenseeName;
    /**
     * 授权类型
     */
    private String authorizeType;
    /**
     * 授权期限
     */
    private String authorizeLimit;
    /**
     * 申请时间
     */
    private Date applyDate;

    /**
     * 工作流状态id
     */
    private Integer fWorkFlowId;

}