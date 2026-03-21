package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 文件目录表
 */
@TableName("fw_litigate_dispute_dict")
public class FwLitigateDisputeDict implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
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
    @TableLogic
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

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Long getFkParentId() {
        return fkParentId;
    }

    public void setFkParentId(Long fkParentId) {
        this.fkParentId = fkParentId;
    }

    public String getFkParentName() {
        return fkParentName;
    }

    public void setFkParentName(String fkParentName) {
        this.fkParentName = fkParentName;
    }

    public String getfNote() {
        return fNote;
    }

    public void setfNote(String fNote) {
        this.fNote = fNote;
    }

    public String getfRule() {
        return fRule;
    }

    public void setfRule(String fRule) {
        this.fRule = fRule;
    }

    public Integer getfLevel() {
        return fLevel;
    }

    public void setfLevel(Integer fLevel) {
        this.fLevel = fLevel;
    }

    public Byte getfState() {
        return fState;
    }

    public void setfState(Byte fState) {
        this.fState = fState;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Byte getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Byte fIsdel) {
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