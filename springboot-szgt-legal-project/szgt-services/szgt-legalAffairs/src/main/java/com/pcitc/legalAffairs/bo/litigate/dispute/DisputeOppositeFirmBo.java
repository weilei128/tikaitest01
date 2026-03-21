package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeOppositeFirmBo {
    private Long fId;

    /**
     * 纠纷ID
     */
    private Long fkDisputeId;

    /**
     * 纠纷名称
     */
    private String fkDisputeName;

    /**
     * 代理律所ID
     */
    private Long fkIntermediaryId;

    /**
     * 代理律所名称
     */
    private String fkIntermediaryName;

    /**
     * 代理律所统一社会信用代码
     */
    private String fkIntermediaryUscCode;

    /**
     * 代理律师
     */
    private String fActingLawyer;

    /**
     * 排序
     */
    private Integer fSort;

    private List<Long> steps;
    
    private List<String> stepNames;

    // /**
    //  * 是否删除 1：删除，0：未删除
    //  */
    // private Integer fIsdel;

    // private Long fCreateId;

    // /**
    //  * 创建人账号
    //  */
    // private String fCreateUser;

    // /**
    //  * 创建人姓名
    //  */
    // private String fCreateName;

    // /**
    //  * 创建时间
    //  */
    // private Date fCreateTime;

    // private Long fUpdateId;

    // /**
    //  * 修改人账号
    //  */
    // private String fUpdateUser;

    // /**
    //  * 修改人姓名
    //  */
    // private String fUpdateName;

    // /**
    //  * 修改时间
    //  */
    // private Date fUpdateTime;

}