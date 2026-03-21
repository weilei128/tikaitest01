package com.pcitc.szgt.contract.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author jobob
 * @since 2020-02-27
 */
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Integer fId;

    /**
     * 字典键值
     */
    @TableField("f_Key")
    private String fKey;

    /**
     * 字典项名称
     */
    @TableField("f_Name")
    private String fName;

    /**
     * 字典项中文名
     */
    @TableField("f_Cn_Name")
    private String fCnName;

    /**
     * 字典项附带内容
     */
    @TableField("f_Content")
    private String fContent;

    /**
     * 字典分类ID
     */
    @TableField("fk_Dictionary_Category_ID")
    private Integer fkDictionaryCategoryId;

    /**
     * 字典分类名称
     */
    @TableField("fk_Dictionary_Category_Name")
    private String fkDictionaryCategoryName;

    /**
     * 快速分类字段
     */
    @TableField("f_Category")
    private String fCategory;

    /**
     * 启用状态：0启用/1未启用
     */
    @TableField("f_State")
    private Integer fState;

    /**
     * 排序字段
     */
    @TableField("f_Sort")
    private Integer fSort;

    /**
     * 类型：0普通字典，1待定
     */
    @TableField("f_Type")
    private Integer fType;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableField("f_IsDel")
    private Integer fIsdel;

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

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
    public String getfKey() {
        return fKey;
    }

    public void setfKey(String fKey) {
        this.fKey = fKey;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getfCnName() {
        return fCnName;
    }

    public void setfCnName(String fCnName) {
        this.fCnName = fCnName;
    }
    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }
    public Integer getFkDictionaryCategoryId() {
        return fkDictionaryCategoryId;
    }

    public void setFkDictionaryCategoryId(Integer fkDictionaryCategoryId) {
        this.fkDictionaryCategoryId = fkDictionaryCategoryId;
    }
    public String getFkDictionaryCategoryName() {
        return fkDictionaryCategoryName;
    }

    public void setFkDictionaryCategoryName(String fkDictionaryCategoryName) {
        this.fkDictionaryCategoryName = fkDictionaryCategoryName;
    }
    public String getfCategory() {
        return fCategory;
    }

    public void setfCategory(String fCategory) {
        this.fCategory = fCategory;
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
        return "SysDictionary{" +
        "fId=" + fId +
        ", fKey=" + fKey +
        ", fName=" + fName +
        ", fCnName=" + fCnName +
        ", fContent=" + fContent +
        ", fkDictionaryCategoryId=" + fkDictionaryCategoryId +
        ", fkDictionaryCategoryName=" + fkDictionaryCategoryName +
        ", fCategory=" + fCategory +
        ", fState=" + fState +
        ", fSort=" + fSort +
        ", fType=" + fType +
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
