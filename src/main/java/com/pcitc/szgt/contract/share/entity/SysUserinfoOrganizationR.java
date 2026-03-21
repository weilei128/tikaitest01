package com.pcitc.szgt.contract.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户组织机构关系表
 * </p>
 *
 * @author jobob
 * @since 2020-04-30
 */
public class SysUserinfoOrganizationR implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 用户id
     */
    @TableField("fk_User_ID")
    private Integer fkUserId;

    /**
     * 用户编码
     */
    @TableField("fk_User_Code")
    private String fkUserCode;

    /**
     * 组织机构id
     */
    @TableField("fk_Organization_ID")
    private Integer fkOrganizationId;

    /**
     * 组织机构编码
     */
    @TableField("fk_Organization_Code")
    private String fkOrganizationCode;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableField("f_IsDel")
    private Integer fIsdel;

    /**
     * 状态
     */
    @TableField("f_State")
    private Integer fState;

    /**
     * 排序
     */
    @TableField("f_Sort")
    private Integer fSort;

    /**
     * 类型
     */
    @TableField("f_Type")
    private Integer fType;

    /**
     * 创建人账号
     */
    @TableField("f_Create_User")
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    @TableField("f_Create_Name")
    private String fCreateName;

    /**
     * 创建时间
     */
    @TableField("f_Create_Time")
    private LocalDateTime fCreateTime;

    /**
     * 修改人账号
     */
    @TableField("f_Update_User")
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    @TableField("f_Update_Name")
    private String fUpdateName;

    /**
     * 修改时间
     */
    @TableField("f_Update_Time")
    private LocalDateTime fUpdateTime;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }
    public Integer getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }
    public String getFkUserCode() {
        return fkUserCode;
    }

    public void setFkUserCode(String fkUserCode) {
        this.fkUserCode = fkUserCode;
    }
    public Integer getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(Integer fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }
    public String getFkOrganizationCode() {
        return fkOrganizationCode;
    }

    public void setFkOrganizationCode(String fkOrganizationCode) {
        this.fkOrganizationCode = fkOrganizationCode;
    }
    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }
    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }
    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }
    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
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
    public LocalDateTime getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(LocalDateTime fCreateTime) {
        this.fCreateTime = fCreateTime;
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
    public LocalDateTime getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(LocalDateTime fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    @Override
    public String toString() {
        return "SysUserinfoOrganizationR{" +
        "fId=" + fId +
        ", fkUserId=" + fkUserId +
        ", fkUserCode=" + fkUserCode +
        ", fkOrganizationId=" + fkOrganizationId +
        ", fkOrganizationCode=" + fkOrganizationCode +
        ", fIsdel=" + fIsdel +
        ", fState=" + fState +
        ", fSort=" + fSort +
        ", fType=" + fType +
        ", fCreateUser=" + fCreateUser +
        ", fCreateName=" + fCreateName +
        ", fCreateTime=" + fCreateTime +
        ", fUpdateUser=" + fUpdateUser +
        ", fUpdateName=" + fUpdateName +
        ", fUpdateTime=" + fUpdateTime +
        "}";
    }
}
