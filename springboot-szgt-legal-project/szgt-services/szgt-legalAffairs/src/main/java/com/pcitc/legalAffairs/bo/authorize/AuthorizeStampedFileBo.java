package com.pcitc.legalAffairs.bo.authorize;

import lombok.Data;

@Data
public class AuthorizeStampedFileBo {

    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;

    /**
     * 上传人ID
     */
    private Long fkUploadPersonId;

    /**
     * 上传人姓名
     */
    private String fkUploadPersonName;

    /**
     * 已盖章文件ID
     */
    private Long fkFileId;

    /**
     * 已盖章文件名
     */
    private String fkFileName;

    /**
     * 已盖章文件扩展名
     */
    private String fkFileExt;

    /**
     * 已盖章文件路径
     */
    private String fkFilePath;

    /**
     * 状态 0-暂存 1-正式保存
     */
    private Byte fStatus;

    /**
     * 排序
     */
    private Integer fSort;

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