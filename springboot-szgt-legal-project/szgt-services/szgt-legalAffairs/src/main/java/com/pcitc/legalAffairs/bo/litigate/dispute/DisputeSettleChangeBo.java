package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

@Data
public class DisputeSettleChangeBo {

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
     * 原处理方式
     */
    private Long fOriSettleMethod;

    /**
     * 新处理方式
     */
    private Long fSettleMethod;

    /**
     * 申请原因
     */
    private String fCause;

    /**
     * 申请时间
     */
    private String fTime;

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
    
    private List<DisputeFileDictBo> files;

    // /**
    //  * 排序
    //  */
    // private Integer fSort;

    // /**
    //  * 是否删除 1：删除，0：未删除
    //  */
    // @TableLogic
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