package com.pcitc.legalAffairs.po.authorize;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 
 */
@TableName("fw_authorize_file_template")
public class FwAuthorizeFileTemplate implements Serializable {

    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 模板名称
     */
    private String fName;

    /**
     * 模板类别
     */
    private String fTemplateType;

    /**
     * 授权性质
     */
    private String fAuthorizeProperty;

    /**
     * 授权类别
     */
    private String fAuthorizeType;

    /**
     * 是否启用
     */
    private Byte fIsAvailable;

    /**
     * 模板文件ID
     */
    private Long fkFileId;

    /**
     * 模板文件名
     */
    private String fkFileName;

    /**
     * 模板文件扩展名
     */
    private String fkFileExt;

    /**
     * 模板路径
     */
    private String fkFilePath;

    /**
     * 发布人ID
     */
    private Long fkPublisherId;

    /**
     * 发布人姓名
     */
    private String fkPublisherName;

    /**
     * 发布时间
     */
    private Date fPublishDate;

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfTemplateType() {
        return fTemplateType;
    }

    public void setfTemplateType(String fTemplateType) {
        this.fTemplateType = fTemplateType;
    }

    public String getfAuthorizeProperty() {
        return fAuthorizeProperty;
    }

    public void setfAuthorizeProperty(String fAuthorizeProperty) {
        this.fAuthorizeProperty = fAuthorizeProperty;
    }

    public String getfAuthorizeType() {
        return fAuthorizeType;
    }

    public void setfAuthorizeType(String fAuthorizeType) {
        this.fAuthorizeType = fAuthorizeType;
    }

    public Byte getfIsAvailable() {
        return fIsAvailable;
    }

    public void setfIsAvailable(Byte fIsAvailable) {
        this.fIsAvailable = fIsAvailable;
    }

    public Long getFkFileId() {
        return fkFileId;
    }

    public void setFkFileId(Long fkFileId) {
        this.fkFileId = fkFileId;
    }

    public String getFkFileName() {
        return fkFileName;
    }

    public void setFkFileName(String fkFileName) {
        this.fkFileName = fkFileName;
    }

    public String getFkFileExt() {
        return fkFileExt;
    }

    public void setFkFileExt(String fkFileExt) {
        this.fkFileExt = fkFileExt;
    }

    public String getFkFilePath() {
        return fkFilePath;
    }

    public void setFkFilePath(String fkFilePath) {
        this.fkFilePath = fkFilePath;
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

    public Long getFkPublisherId() {
        return this.fkPublisherId;
    }

    public void setFkPublisherId(Long fkPublisherId) {
        this.fkPublisherId = fkPublisherId;
    }

    public String getFkPublisherName() {
        return this.fkPublisherName;
    }

    public void setFkPublisherName(String fkPublisherName) {
        this.fkPublisherName = fkPublisherName;
    }

    public Date getfPublishDate() {
        return this.fPublishDate;
    }

    public void setfPublishDate(Date fPublishDate) {
        this.fPublishDate = fPublishDate;
    }
}