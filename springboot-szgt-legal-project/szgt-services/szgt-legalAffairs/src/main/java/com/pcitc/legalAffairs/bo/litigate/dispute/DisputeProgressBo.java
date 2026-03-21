package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeProgressBo {

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
     * 案件阶段
     */
    private Integer fStep;

    /**
     * 案件状态
     */
    private Integer fStatus;
    
    /**
     * 是否已生成案号
     */
    private Integer fHaveCaseCode;
    
    /**
     * 案号
     */
    private Integer fCaseCode;

    /**
     * 案件阶段
     */
    private String fStepName;
    
    /**
     * 案件状态
     */
    private String fStatusName;

    /**
     * 开始时间
     */
    private String fBegindate;

    /**
     * 结束时间
     */
    private String fEnddate;

    /**
     * 是否大事记
     */
    private Byte fIsEvent;

    /**
     * 是否胜诉
     */
    private Byte fIsVictory;

    /**
     * 进展描述
     */
    private String fDescription;

    /**
     * 进展附件ID
     */
    private Long fkProgressAttachmentId;

    /**
     * 进展附件路径
     */
    private String fkProgressAttachmentPath;

    /**
     * 进展附件文件名
     */
    private String fkProgressAttachmentName;

    /**
     * 进展附件扩展名
     */
    private String fkProgressAttachmentExt;

    /**
     * 受理机构
     */
    private String fReceivingAgency;

    /**
     * 受理时间
     */
    private String fReceiveDate;

    /**
     * 案由1
     */
    private Integer fCause1;

    /**
     * 案由2
     */
    private Integer fCause2;

    /**
     * 案由3
     */
    private Integer fCause3;

    /**
     * 案由4
     */
    private Integer fCause4;

    /**
     * 相关附件ID
     */
    private Long fkAttachmentId;

    /**
     * 相关附件路径
     */
    private String fkAttachmentPath;

    /**
     * 相关附件文件名
     */
    private String fkAttachmentName;

    /**
     * 相关附件扩展名
     */
    private String fkAttachmentExt;

    /**
     * 排序
     */
    private Integer fSort;
    
    private List<DisputeFileDictBo> files;

    private List<DisputeFileDictBo> attachments;
    
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