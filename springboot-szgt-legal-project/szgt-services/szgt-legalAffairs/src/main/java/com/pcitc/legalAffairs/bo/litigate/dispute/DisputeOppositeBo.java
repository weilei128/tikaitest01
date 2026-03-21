package com.pcitc.legalAffairs.bo.litigate.dispute;

import lombok.Data;

@Data
public class DisputeOppositeBo {
	
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
     * 名称
     */
    private String fName;

    /**
     * 地址
     */
    private String fAddress;

    /**
     * 属性
     */
    private Long fStatus;

    /**
     * 属性
     */
    private String fStatusName;
    
    /**
     * 属性描述
     */
    private String fStatusDescription;

    /**
     * 联系人
     */
    private String fContact;

    /**
     * 联系电话
     */
    private String fPhone;

    /**
     * Email
     */
    private String fEmail;

    /**
     * 法律地位
     */
    private String fLegalStatus;
    
    /**
     * 法律地位
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
