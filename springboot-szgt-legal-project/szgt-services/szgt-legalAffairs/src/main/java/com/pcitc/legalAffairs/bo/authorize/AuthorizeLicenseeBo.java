package com.pcitc.legalAffairs.bo.authorize;

import lombok.Data;

@Data
public class AuthorizeLicenseeBo {
    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;

    /**
     * 被授权人类型
     */
    private String fType;

    /**
     * 被授权人名称
     */
    private String fName;

    /**
     * 统一社会信用代码
     */
    private String fUscCode;

    /**
     * 法定代表人/负责人
     */
    private String fLegalRepresentative;

    /**
     * 所属单位ID
     */
    private Byte fkOrgId;

    /**
     * 所属单位名称
     */
    private String fkOrgName;

    /**
     * 身份证号
     */
    private String fIdCardNo;

    /**
     * 联系方式
     */
    private String fContact;

    /**
     * 职务
     */
    private String fPosition;

    /**
     * 授权事项
     */
    private String fAuthorizeMatters;

    /**
     * 授权期限
     */
    private String fAuthorizeLimit;

    /**
     * 授权依据文件ID
     */
    private Long fAccordanceFileId;

    /**
     * 授权依据文件路径
     */
    private String fAccordanceFilePath;

    /**
     * 授权依据文件名
     */
    private String fAccordanceFileName;

    /**
     * 授权依据文件扩展名
     */
    private String fAccordanceFileExt;

    /**
     * 附件ID
     */
    private Long fAdditionalFileId;

    /**
     * 附件路径
     */
    private String fAdditionalFilePath;

    /**
     * 附件文件名
     */
    private String fAdditionalFileName;

    /**
     * 附件扩展名
     */
    private String fAdditionalFileExt;

    /**
     * 注意事项
     */
    private String fCaution;

    /**
     * 备注
     */
    private String fRemark;

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
