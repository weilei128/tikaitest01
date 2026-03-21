package com.pcitc.legalAffairs.po.authorize;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 已盖章授权书
 */
@TableName("fw_authorize_stamped_file")
public class FwAuthorizeStampedFile implements Serializable {

    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 授权ID
     */
    private Long fkAuthorizeId;

    /**
     * 上传人ID
     */
    private Long fkUploadPersonId;

    /**
     * 上传人姓名
     */
    private String fkUploadPersonName;

    /**
     * 已盖章文件ID
     */
    private Long fkFileId;

    /**
     * 已盖章文件名
     */
    private String fkFileName;

    /**
     * 已盖章文件扩展名
     */
    private String fkFileExt;

    /**
     * 已盖章文件路径
     */
    private String fkFilePath;

    /**
     * 状态 0-暂存 1-正式保存
     */
    private Byte fStatus;

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

    public Long getFkAuthorizeId() {
        return fkAuthorizeId;
    }

    public void setFkAuthorizeId(Long fkAuthorizeId) {
        this.fkAuthorizeId = fkAuthorizeId;
    }

    public Long getFkUploadPersonId() {
        return fkUploadPersonId;
    }

    public void setFkUploadPersonId(Long fkUploadPersonId) {
        this.fkUploadPersonId = fkUploadPersonId;
    }

    public String getFkUploadPersonName() {
        return fkUploadPersonName;
    }

    public void setFkUploadPersonName(String fkUploadPersonName) {
        this.fkUploadPersonName = fkUploadPersonName;
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

    public Byte getfStatus() {
        return fStatus;
    }

    public void setfStatus(Byte fStatus) {
        this.fStatus = fStatus;
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