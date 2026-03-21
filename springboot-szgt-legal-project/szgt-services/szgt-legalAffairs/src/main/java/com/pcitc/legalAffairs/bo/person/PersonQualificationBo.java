package com.pcitc.legalAffairs.bo.person;

import java.util.Date;

import lombok.Data;

@Data
public class PersonQualificationBo {
    /**
     * 主键
     */
    private Long fId;

    /**
     * 关联person_info表f_IDID
     */
    private Long fkPersonId;

    /**
     * person_info表f_name
     */
    private String fkPersonName;

    /**
     * 资质/证书名称
     */
    private String fName;

    /**
     * 证书编号
     */
    private String fCode;

    /**
     * 颁证日期
     */
    private String fAwardedDate;

    /**
     * 颁证机构
     */
    private String fAwardAgency;

    /**
     * 最近年检日期
     */
    private String fYearlyCheckDate;

    /**
     * 所属国家/地区
     */
    private String fNationality;

    /**
     * 备案/年检周期
     */
    private String fCheckRound;

    /**
     * 资质文件主键ID
     */
    private Long fkFileId;

    /**
     * 资质文件名
     */
    private String fkFileName;

    /**
     * 资质文件服务器路径
     */
    private String fkFileServerPath;

    /**
     * 资质文件扩展名
     */
    private String fkFileExt;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    private Integer fIsdel;

    private Integer fCreateId;

    /**
     * 创建人账号
     */
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    private String fCreateName;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    private Integer fUpdateId;

    /**
     * 修改人账号
     */
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    private String fUpdateName;

    /**
     * 修改时间
     */
    private Date fUpdateTime;


}
