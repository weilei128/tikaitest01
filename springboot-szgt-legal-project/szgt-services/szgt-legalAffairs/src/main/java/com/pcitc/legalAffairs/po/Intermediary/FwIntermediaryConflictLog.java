package com.pcitc.legalAffairs.po.Intermediary;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 利益冲突操作记录
 */
@TableName("fw_intermediary_conflict_log")
public class FwIntermediaryConflictLog implements Serializable {
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 利益冲突信息ID
     */
    private Long fkConflictId;

    /**
     * 统一社会信用代码
     */
    private String fkUscCode;

    /**
     * 操作人ID
     */
    private Long fkOperatorId;

    /**
     * 操作人姓名
     */
    private String fkOperatorName;

    /**
     * 执行操作 0-启用 1- 禁用
     */
    private Integer fkOperate;

    /**
     * 操作执行时间
     */
    private Date fOperatetime;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableLogic
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

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkConflictId() {
        return fkConflictId;
    }

    public void setFkConflictId(Long fkConflictId) {
        this.fkConflictId = fkConflictId;
    }

    public String getFkUscCode() {
        return fkUscCode;
    }

    public void setFkUscCode(String fkUscCode) {
        this.fkUscCode = fkUscCode;
    }

    public Long getFkOperatorId() {
        return fkOperatorId;
    }

    public void setFkOperatorId(Long fkOperatorId) {
        this.fkOperatorId = fkOperatorId;
    }

    public String getFkOperatorName() {
        return fkOperatorName;
    }

    public void setFkOperatorName(String fkOperatorName) {
        this.fkOperatorName = fkOperatorName;
    }

    public Integer getFkOperate() {
        return fkOperate;
    }

    public void setFkOperate(Integer fkOperate) {
        this.fkOperate = fkOperate;
    }

    public Date getfOperatetime() {
        return fOperatetime;
    }

    public void setfOperatetime(Date fOperatetime) {
        this.fOperatetime = fOperatetime;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public Long getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Long fCreateId) {
        this.fCreateId = fCreateId;
    }

    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser;
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Long getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Long fUpdateId) {
        this.fUpdateId = fUpdateId;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser;
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}