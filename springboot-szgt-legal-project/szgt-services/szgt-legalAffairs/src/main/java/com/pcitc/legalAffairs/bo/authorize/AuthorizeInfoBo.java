package com.pcitc.legalAffairs.bo.authorize;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorizeInfoBo {
    private Long fId;

    /**
     * 流水号
     */
    private String fSerialNo;

    /**
     * 授权书序号
     */
    private String fAuthorizationNo;
    
    /**
     * 工作流状态
     */
    private Integer fWorkFlowId;

    /**
     * 经办人ID
     */
    private Long fManagerId;

    /**
     * 经办人姓名
     */
    private String fManagerName;

    /**
     * 经办人所属部门ID
     */
    private Long fkManagerOrgId;

    /**
     * 经办人所属部门名称
     */
    private String fkManagerOrgName;

    /**
     * 经办人电子邮箱
     */
    private String fManagerEmail;

    /**
     * 经办人联系方式
     */
    private String fManagerContact;

    /**
     * 授权类型
     */
    private String fType;
    
    /**
     * 使用模板ID
     */
    private Long fkTemplateId;
    
    /**
     * 模板路径
     */
    private String fTemplatePath;
    
    /**
     * 模板文件名
     */
    private String fTemplateName;
    
    /**
     * 模板扩展名
     */
    private String fTemplateExt;

    /**
     * 模板类型
     */
    private String fTemplateType;

    /**
     * 授权人ID
     */
    private Long fkAuthorizerId;

    /**
     * 授权人姓名
     */
    private String fkAuthorizerName;

    /**
     * 授权人所属机构
     */
    private Long fkAuthorizerOrgId;

    /**
     * 授权人所属机构类型
     */
    private String fkAuthorizerOrgType;

    /**
     * 授权人所属机构名称
     */
    private String fkAuthorizerOrgName;

    /**
     * 法定代表/负责人
     */
    private String fkLegalRepresentative;

    /**
     * 被授权人类型
     */
    private String fLicenseeType;

    /**
     * 被授权人ID
     */
    private Long fkLicenseeId;

    /**
     * 被授权人名称
     */
    private String fLicenseeName;

    /**
     * 被授权人统一社会信用代码
     */
    private String fUscCode;

    /**
     * 被授权人法定代表人/负责人
     */
    private String fLicenseeLegalRepresentative;

    /**
     * 被授权人所属单位ID
     */
    private Long fkLicenseeOrgId;

    /**
     * 被授权人所属单位名称
     */
    private String fkLicenseeOrgName;

    /**
     * 被授权人身份证号
     */
    private String fLicenseeIdCardNo;

    /**
     * 被授权人联系方式
     */
    private String fLicenseeContact;

    /**
     * 被授权人职务
     */
    private String fLicenseePosition;

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
     * 是否用印
     */
    private Byte fUseStamp;

    /**
     * 用印类型(用,分隔)
     */
    private String fUseStampType;

    /**
     * 是否涉密
     */
    private Byte fIsSecret;

    /**
     * 发往单位
     */
    private String fSendTo;

    /**
     * 事由
     */
    private String fCause;

    /**
     * 授权书打印人ID
     */
    private Long fAuthorizationPrintPersonId;

    /**
     * 授权书打印人姓名
     */
    private String fAuthorizationPrintPersonName;

    /**
     * 行权报告填报人ID
     */
    private Long fExerciseReporterId;

    /**
     * 行权报告填报人姓名
     */
    private String fExerciseReporterName;

    /**
     * 取件方式
     */
    private String fPickupMethod;

    /**
     * 事项授权状态
     */
    private Integer fStatus;

    /**
     * 是否废弃
     */
    private Integer fIsDiscard;

    /**
     * 申请时间
     */
    private Date fApplyTime;

    /**
     * 排序
     */
    private Integer fSort;
    
    /**
     * 盖章授权书文件信息
     */
    private AuthorizeStampedFileBo stampedFile;
    
    /**
     * 行权信息
     */
    private AuthorizeExerciseBo exercise;
    
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
