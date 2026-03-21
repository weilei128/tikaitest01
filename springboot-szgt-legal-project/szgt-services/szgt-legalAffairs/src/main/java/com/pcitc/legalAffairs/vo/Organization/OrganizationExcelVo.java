package com.pcitc.legalAffairs.vo.Organization;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationExcelVo {
    /**
     * 法律机构信息主键
     */
    private Integer fId;
    /**
     * 所属单位
     */
    private String fAffiliatedUnit;
    /**
     * 组织节点
     */
    private String fOrgNode;
    /**
     * 机构ID
     */
    private String fkOrgId ;
    /**
     * 机构名称
     */
    private String fkOrgName;
    /**
     * 机构类型编码
     */
    private String fOrgTypeCode;
    /**
     * 机构类型名称
     */
    private String fOrgTypeName;
    /**
     * 海外机构名称
     */
    private String fOverseaOrgName;
    /**
     * 通讯地址
     */
    private String fPostalAddress;
    /**
     * 邮政编码
     */
    private String fPostCode;
    /**
     * 区号
     */
    private String fAreaCode;
    /**
     * 单位传真
     */
    private String fUnitFax;
    /**
     * 机构职责
     */
    private String fOrgDuties;
    /**
     * 文号
     */
    private String fReferenceNumber;
    /**
     * 文件名称
     */
    private String fFileName;
    /**
     * 文件存储路径
     */
    private String fFileUrl;
    /**
     * 设置状态：0.未设置，1已设置,2草稿，
     */
    private Integer fSetStatus;


}
