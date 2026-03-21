package com.pcitc.legalAffairs.bo.person;

import lombok.Data;

@Data
public class PersonResumeBo {

    private Long fId;

    private Long fkPersonId;

    private String fkPersonName;

    /**
     * 公司名称
     */
    private String fCompanyName;

    /**
     * 在职时间 - 开始
     */
    private String fBegindate;

    /**
     * 在职时间 - 结束
     */
    private String fEnddate;

    /**
     * 职位
     */
    private String fPosition;

    /**
     * 职责
     */
    private String fDuty;

    /**
     * 业绩描述
     */
    private String fPerformance;

    /**
     * 排序
     */
    private Integer fSort;

//     /**
//      * 是否删除 1：删除，0：未删除
//      */
//     private Integer fIsdel;

//     private Long fCreateId;

//     /**
//      * 创建人账号
//      */
//     private String fCreateUser;

//     /**
//      * 创建人姓名
//      */
//     private String fCreateName;

//     /**
//      * 创建时间
//      */
//     private Date fCreateTime;

//     private Long fUpdateId;

//     /**
//      * 修改人账号
//      */
//     private String fUpdateUser;

//     /**
//      * 修改人姓名
//      */
//     private String fUpdateName;

//     /**
//      * 修改时间
//      */
//     private Date fUpdateTime;
}