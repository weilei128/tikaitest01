package com.pcitc.legalAffairs.bo.authorize;

import lombok.Data;

@Data
public class AuthorizeExerciseBo {
	
    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;

    /**
     * 行权状态
     */
    private Integer fStatus;

    /**
     * 行权完成日期
     */
    private String fCompleteDate;

    /**
     * 行权说明/未行权说明
     */
    private String fDescription;

    /**
     * 授权状态
     */
    private Integer fAuthorizeStatus;

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
     * 原件寄回
     */
    private Integer fReturnOriginal;

    /**
     * 收件人
     */
    private String fRecipient;

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
