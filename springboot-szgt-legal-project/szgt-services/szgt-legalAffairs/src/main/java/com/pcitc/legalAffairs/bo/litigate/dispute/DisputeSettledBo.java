package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class DisputeSettledBo {

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
     * 办理结果
     */
    private String fResult;

    /**
     * 管理建议
     */
    private String fAdvice;

    /**
     * 结案时间
     */
    private String fDate;

    /**
     * 挽回损失(万)
     */
    private BigDecimal fLossRecovered;

    /**
     * 挽回损失币种
     */
    private Integer fLossRecoveredCurrency;

    /**
     * 避免损失(万)
     */
    private BigDecimal fLossAvoided;

    /**
     * 避免损失币种
     */
    private Integer fLossAvoidedCurrency;

    /**
     * 生效判决书ID
     */
    private Long fkAttachmentId;

    /**
     * 生效判决书路径
     */
    private String fkAttachmentPath;

    /**
     * 生效判决书文件名
     */
    private String fkAttachmentName;

    /**
     * 生效判决书扩展名
     */
    private String fkAttachmentExt;

    /**
     * 排序
     */
    private Integer fSort;

    private List<DisputeFileDictBo> files;

    /**
     *  败诉金额
     */
    private BigDecimal flossLosingAmount;

    /**
     * 败诉金额币种
     */
    private Integer flossLosingAmountCurrency;

    /**
     * 是否删除 1：删除，0：未删除
     */
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