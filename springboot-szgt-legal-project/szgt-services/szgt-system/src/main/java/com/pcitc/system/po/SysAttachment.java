package com.pcitc.system.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "sys_attachment")
public class SysAttachment {

    @TableId
    private Integer fId;

    private String fBusinessType;

    private String fBusinessId;

    private String fFileId;

    private String fFileName;

    private String fFileExt;

    private String fRelativePath;

    private String fServerPath;
    /**
     * 关联文件目录ID
     */
    private Long fkDirectoryId;
    /**
     * 关联文件目录名称
     */
    private String fkDirectoryName;

    private Integer fState;

    private Integer fSort;

    private Integer fType;

    private Integer fIsdel;

    private String fCreateUser;

    private String fCreateName;

    private Date fCreateTime;

    private String fUpdateUser;

    private String fUpdateName;

    private Date fUpdateTime;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfBusinessType() {
        return fBusinessType;
    }

    public void setfBusinessType(String fBusinessType) {
        this.fBusinessType = fBusinessType == null ? null : fBusinessType.trim();
    }

    public String getfBusinessId() {
        return fBusinessId;
    }

    public void setfBusinessId(String fBusinessId) {
        this.fBusinessId = fBusinessId == null ? null : fBusinessId.trim();
    }

    public String getfFileId() {
        return fFileId;
    }

    public void setfFileId(String fFileId) {
        this.fFileId = fFileId == null ? null : fFileId.trim();
    }

    public String getfFileName() {
        return fFileName;
    }

    public void setfFileName(String fFileName) {
        this.fFileName = fFileName == null ? null : fFileName.trim();
    }

    public String getfFileExt() {
        return fFileExt;
    }

    public void setfFileExt(String fFileExt) {
        this.fFileExt = fFileExt == null ? null : fFileExt.trim();
    }

    public String getfRelativePath() {
        return fRelativePath;
    }

    public void setfRelativePath(String fRelativePath) {
        this.fRelativePath = fRelativePath == null ? null : fRelativePath.trim();
    }

    public String getfServerPath() {
        return fServerPath;
    }

    public void setfServerPath(String fServerPath) {
        this.fServerPath = fServerPath == null ? null : fServerPath.trim();
    }

    public Long getFkDirectoryId() {
        return fkDirectoryId;
    }

    public void setFkDirectoryId(Long fkDirectoryId) {
        this.fkDirectoryId = fkDirectoryId;
    }

    public String getFkDirectoryName() {
        return fkDirectoryName;
    }

    public void setFkDirectoryName(String fkDirectoryName) {
        this.fkDirectoryName = fkDirectoryName;
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
        this.fCreateUser = fCreateUser == null ? null : fCreateUser.trim();
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName == null ? null : fCreateName.trim();
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser == null ? null : fUpdateUser.trim();
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName == null ? null : fUpdateName.trim();
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}