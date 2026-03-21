package com.pcitc.system.bo;

public class SysAttachmentQueryBo {
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
    private int pageIndex;
    private int pageSize;

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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
