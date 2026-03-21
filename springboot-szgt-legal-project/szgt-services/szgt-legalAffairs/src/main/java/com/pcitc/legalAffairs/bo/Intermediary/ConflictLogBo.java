package com.pcitc.legalAffairs.bo.Intermediary;

import java.util.Date;

import lombok.Data;

@Data
public class ConflictLogBo {
    private Long fId;

    /**
     * 利益冲突信息ID
     */
    private Long fkConflictId;

    /**
     * 统一社会信用代码
     */
    private String fkUscCode;

    /**
     * 操作人ID
     */
    private Long fkOperatorId;

    /**
     * 操作人姓名
     */
    private String fkOperatorName;

    /**
     * 执行操作 0-启用 1- 禁用
     */
    private Integer fkOperate;

    /**
     * 操作执行时间
     */
    private Date fOperatetime;

    /**
     * 排序
     */
    private Integer fSort;

}