package com.pcitc.system.vo;


import java.util.Date;

public class SysAttachmentVo {
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
        this.fBusinessType = fBusinessType;
    }

    public String getfBusinessId() {
        return fBusinessId;
    }

    public void setfBusinessId(String fBusinessId) {
        this.fBusinessId = fBusinessId;
    }

    public String getfFileId() {
        return fFileId;
    }

    public void setfFileId(String fFileId) {
        this.fFileId = fFileId;
    }

    public String getfFileName() {
        return fFileName;
    }

    public void setfFileName(String fFileName) {
        this.fFileName = fFileName;
    }

    public String getfFileExt() {
        return fFileExt;
    }

    public void setfFileExt(String fFileExt) {
        this.fFileExt = fFileExt;
    }

    public String getfRelativePath() {
        return fRelativePath;
    }

    public void setfRelativePath(String fRelativePath) {
        this.fRelativePath = fRelativePath;
    }

    public String getfServerPath() {
        return fServerPath;
    }

    public void setfServerPath(String fServerPath) {
        this.fServerPath = fServerPath;
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
