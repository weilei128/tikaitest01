package com.pcitc.legalAffairs.bo.litigate.dispute;

import lombok.Data;

@Data
public class DisputeOursideBo {
    private Long fId;

    /**
     * 争议信息ID
     */
    private Long fkDisputeId;

    /**
     * 争议名称
     */
    private String fkDisputeName;

    /**
     * 我方案件当事人属性
     */
    private String fLitigantStatus;
    
    /**
     * 我方案件当事人属性
     */
    private String fLitigantStatusName;

    /**
     * 我方案件当事人
     */
    private String fLitigantName;

    /**
     * 我方案件当事人ID
     */
    private Long fkLitigantId;

    /**
     * 承办单位ID
     */
    private Long fkOrgId;

    /**
     * 承办单位名称
     */
    private String fkOrgName;

    /**
     * 联系人
     */
    private String fContact;

    /**
     * 联系电话
     */
    private String fPhone;

    /**
     * 电子邮件
     */
    private String fEmail;

    /**
     * 我方法律地位
     */
    private String fLegalStatus;
    
    /**
     * 我方法律地位
     */
    private String fLegalStatusName;

    /**
     * 排序
     */
    private Integer fSort;

//    /**
//     * 是否删除 1：删除，0：未删除
//     */
//    private Integer fIsdel;
//
//    private Long fCreateId;
//
//    /**
//     * 创建人账号
//     */
//    private String fCreateUser;
//
//    /**
//     * 创建人姓名
//     */
//    private String fCreateName;
//
//    /**
//     * 创建时间
//     */
//    private Date fCreateTime;
//
//    private Long fUpdateId;
//
//    /**
//     * 修改人账号
//     */
//    private String fUpdateUser;
//
//    /**
//     * 修改人姓名
//     */
//    private String fUpdateName;
//
//    /**
//     * 修改时间
//     */
//    private Date fUpdateTime;

}
