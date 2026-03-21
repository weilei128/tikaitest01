package com.pcitc.legalAffairs.bo.person;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author 
 * 法律人员工作获奖信息表
 */
@Data
public class PersonHonorBo implements Serializable {
    /**
     * 主键
     */
    private Long fId;

    /**
     * 关联person_info.f_ID
     */
    private Long fkPersonId;

    /**
     * person_info.f_Name
     */
    private String fkPersonName;

    /**
     * 奖项名称
     */
    private String fName;

    /**
     * 奖项级别
     */
    private Long fHonorRank;

    /**
     * 获奖日期
     */
    private String fAwardDate;

    /**
     * 颁证机构
     */
    private String fAwardAgency;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    private Integer fIsdel;

    private Long fCreateId;

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

    private Long fUpdateId;

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

    private static final long serialVersionUID = 1L;

}