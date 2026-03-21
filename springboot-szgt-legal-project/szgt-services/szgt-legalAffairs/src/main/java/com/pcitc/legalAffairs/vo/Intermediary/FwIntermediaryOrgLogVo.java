package com.pcitc.legalAffairs.vo.Intermediary;

import java.util.Date;

import lombok.Data;

@Data
public class FwIntermediaryOrgLogVo {
    private Long fId;

    /**
     * 中介ID
     */
    private Long fkIntermediaryId;

    /**
     * 操作人ID
     */
    private Long fkOperatorId;

    /**
     * 操作人名称
     */
    private String fkOperatorName;

    /**
     * 操作时间
     */
    private Date fOperateTime;

    /**
     * 执行操作 0-准入 1-聘用 2-解除准入 3-启用 4-禁用
     */
    private Integer fOperate;
}