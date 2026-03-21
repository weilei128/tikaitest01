package com.pcitc.legalAffairs.bo.authorize;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorizeFileTemplateBo {
    private Long fId;

    /**
     * 模板名称
     */
    private String fName;

    /**
     * 模板类别
     */
    private String fTemplateType;

    /**
     * 授权性质
     */
    private String fAuthorizeProperty;

    /**
     * 授权类别
     */
    private String fAuthorizeType;

    /**
     * 是否启用
     */
    private Byte fIsAvailable;

    /**
     * 模板文件ID
     */
    private Long fkFileId;

    /**
     * 模板文件名
     */
    private String fkFileName;

    /**
     * 模板文件扩展名
     */
    private String fkFileExt;

    /**
     * 模板路径
     */
    private String fkFilePath;
    
    /**
     * 发布人ID
     */
    private Long fkPublisherId;

    /**
     * 发布人姓名
     */
    private String fkPublisherName;

    /**
     * 发布时间
     */
    private Date fPublishDate;

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