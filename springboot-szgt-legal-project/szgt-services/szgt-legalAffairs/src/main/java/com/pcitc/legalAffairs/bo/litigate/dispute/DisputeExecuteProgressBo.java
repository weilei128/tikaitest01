package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.List;

import lombok.Data;

/**
 * @author 
 * 纠纷执行进度表
 */
@Data
public class DisputeExecuteProgressBo {
	
    private Long fId;

    private Long fkDisputeId;

    private Long fkExecuteId;

    /**
     * 时间
     */
    private String fDate;

    /**
     * 执行情况
     */
    private String fProgress;

    /**
     * 附件ID
     */
    private Long fkAttachmentId;

    /**
     * 附件路径
     */
    private String fkAttachmentPath;

    /**
     * 附件文件名
     */
    private String fkAttachmentName;

    /**
     * 附件扩展名
     */
    private String fkAttachmentExt;

    /**
     * 排序
     */
    private Integer fSort;
    
    private List<DisputeFileDictBo> files;

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