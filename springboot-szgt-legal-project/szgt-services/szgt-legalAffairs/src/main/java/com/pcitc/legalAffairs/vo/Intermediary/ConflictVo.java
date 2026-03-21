package com.pcitc.legalAffairs.vo.Intermediary;

import java.util.Date;
import java.util.List;

import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeOppositeFirmBo;

import lombok.Data;

@Data
public class ConflictVo {

    private Long fId;

    /**
     * 中介名称
     */
    private String fName;

    /**
     * 统一社会信用代码
     */
    private String fUscCode;

    /**
     * 中介类型
     */
    private String fType;

    /**
     * 状态 0-启用 1-禁用
     */
    private Byte fStatus;

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

    /**
     * 涉及案件
     */
    private List<ConflictDetailVo> cases;
    
    /**
     * 是否准入 0-否 1-是
     */
    private Integer admit;
}
