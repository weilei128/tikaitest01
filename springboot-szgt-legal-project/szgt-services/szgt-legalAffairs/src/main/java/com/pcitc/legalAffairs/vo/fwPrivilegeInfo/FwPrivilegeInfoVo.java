package com.pcitc.legalAffairs.vo.fwPrivilegeInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 法务查询权限表
 * </p>
 *
 * @author jobob
 * @since 2020-09-29
 */
public class FwPrivilegeInfoVo{

    private Long fId;

    /**
     * 用户ID
     */
    private Long fkUserId;

    /**
     * 用户姓名
     */
    private String fkUserName;

    /**
     * 组织ID
     */
    private Long fkOrgId;

    /**
     * 组织名称
     */
    private String fkOrgName;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    private Integer fIsdel;

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

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }
    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }
    public String getFkUserName() {
        return fkUserName;
    }

    public void setFkUserName(String fkUserName) {
        this.fkUserName = fkUserName;
    }
    public Long getFkOrgId() {
        return fkOrgId;
    }

    public void setFkOrgId(Long fkOrgId) {
        this.fkOrgId = fkOrgId;
    }
    public String getFkOrgName() {
        return fkOrgName;
    }

    public void setFkOrgName(String fkOrgName) {
        this.fkOrgName = fkOrgName;
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

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }

    @Override
    public String toString() {
        return "FwPrivilegeInfo{" +
        "fId=" + fId +
        ", fkUserId=" + fkUserId +
        ", fkUserName=" + fkUserName +
        ", fkOrgId=" + fkOrgId +
        ", fkOrgName=" + fkOrgName +
        ", fSort=" + fSort +
        ", fIsdel=" + fIsdel +
        ", fCreateUser=" + fCreateUser +
        ", fCreateName=" + fCreateName +
        ", fCreateTime=" + fCreateTime +
        ", fUpdateUser=" + fUpdateUser +
        ", fUpdateName=" + fUpdateName +
        ", fUpdateTime=" + fUpdateTime +
        "}";
    }
}
