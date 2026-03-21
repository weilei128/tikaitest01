package com.pcitc.system.bo;

import org.springframework.web.multipart.MultipartFile;

public class SysAttachmentBo {
    /**
     * 传入文件
     */
   private MultipartFile file;
    /**
     * 业务类型字典
     */
    private String fBusinessType;
    /**
     * 业务id
     */
    private String fBusinessId;

    /**
     * 关联文件目录ID
     */
    private Long fkDirectoryId;
    /**
     * 关联文件目录名称
     */
    private String fkDirectoryName;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
}
