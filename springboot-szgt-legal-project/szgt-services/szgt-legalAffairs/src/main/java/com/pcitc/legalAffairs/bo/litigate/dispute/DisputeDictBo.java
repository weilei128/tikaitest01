package com.pcitc.legalAffairs.bo.litigate.dispute;

import java.util.Date;

import lombok.Data;

@Data
public class DisputeDictBo {
    
    private Long fId;

    /**
     * 文件目录名称
     */
    private String fName;

    /**
     * 父分类ID
     */
    private Long fkParentId;

    /**
     * 父分类名称
     */
    private String fkParentName;

    /**
     * 文件目录备注
     */
    private String fNote;

    /**
     * 文件目录规则
     */
    private String fRule;

    /**
     * 字典分类级别
     */
    private Integer fLevel;

    /**
     * 启用状态：0启用/1未启用
     */
    private Byte fState;

    /**
     * 排序字段
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    private Byte fIsdel;

    /**
     * 创建人id
     */
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

    /**
     * 更新人id
     */
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

}