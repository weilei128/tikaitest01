package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 纠纷文件归档表(纠纷文件与目录关联表)
 */
@TableName("fw_litigate_dispute_file_dict")
public class FwLitigateDisputeFileDict implements Serializable {
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 目录ID
     */
    
    private Long fkDictId;

    /**
     * 目录名称
     */
    private String fkDictName;

    /**
     * 文件ID
     */
    private Long fkFileId;

    /**
     * 文件名
     */
    private String fkFileName;

    /**
     * 文件路径
     */
    private String fkFilePath;

    /**
     * 文件扩展名
     */
    private String fkFileExt;

    /**
     * 纠纷ID
     */
    private Long fkDisputeId;

    /**
     * 纠纷名
     */
    private String fkDisputeName;

    private Integer fSort;

    private Integer fIsdel;

    private Long fCreateId;

    private String fCreateUser;

    private String fCreateName;

    private Date fCreateTime;

    private Long fUpdateId;

    private String fUpdateUser;

    private String fUpdateName;

    private Date fUpdateTime;
    
    private String fFileType;
    
    private Long fBusinessId;

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkDictId() {
        return fkDictId;
    }

    public void setFkDictId(Long fkDictId) {
        this.fkDictId = fkDictId;
    }

    public String getFkDictName() {
        return fkDictName;
    }

    public void setFkDictName(String fkDictName) {
        this.fkDictName = fkDictName;
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

    public String getFkFilePath() {
        return fkFilePath;
    }

    public void setFkFilePath(String fkFilePath) {
        this.fkFilePath = fkFilePath;
    }

    public String getFkFileExt() {
        return fkFileExt;
    }

    public void setFkFileExt(String fkFileExt) {
        this.fkFileExt = fkFileExt;
    }

    public Long getFkDisputeId() {
        return fkDisputeId;
    }

    public void setFkDisputeId(Long fkDisputeId) {
        this.fkDisputeId = fkDisputeId;
    }

    public String getFkDisputeName() {
        return fkDisputeName;
    }

    public void setFkDisputeName(String fkDisputeName) {
        this.fkDisputeName = fkDisputeName;
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

	public String getfFileType() {
		return fFileType;
	}

	public void setfFileType(String fFileType) {
		this.fFileType = fFileType;
	}

	public Long getfBusinessId() {
		return fBusinessId;
	}

	public void setfBusinessId(Long fBusinessId) {
		this.fBusinessId = fBusinessId;
	}
    
}