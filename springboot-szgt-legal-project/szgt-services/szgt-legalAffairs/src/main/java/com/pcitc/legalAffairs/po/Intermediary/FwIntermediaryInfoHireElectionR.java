package com.pcitc.legalAffairs.po.Intermediary;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 
 */
@TableName("fw_intermediary_info_hire_election_r")
public class FwIntermediaryInfoHireElectionR implements Serializable {

    @TableId(value = "f_ID",type = IdType.AUTO)
    private Long fId;

    private Long fkHireId;

    private String fkHireWayName;

    private Long fkIntermediaryId;

    private String fkIntermediaryName;

    private Long fkAttachmentId;
    
    private String fkAttachmentName;
    
    private String fkAttachmentPath;
    
    private String fkAttachmentExt;
    
    /**
     * 排序字段
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    private Boolean fIsdel;

    /**
     * 创建人账号
     */
    private String fCreateuser;

    /**
     * 创建人姓名
     */
    private String fCreatename;

    /**
     * 创建时间
     */
    private Date fCreatetime;

    /**
     * 修改人账号
     */
    private String fUpdateuser;

    /**
     * 修改人姓名
     */
    private String fUpdatename;

    /**
     * 修改时间
     */
    private Date fUpdatetime;

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkHireId() {
        return fkHireId;
    }

    public void setFkHireId(Long fkHireId) {
        this.fkHireId = fkHireId;
    }

    public String getFkHireWayName() {
        return fkHireWayName;
    }

    public void setFkHireWayName(String fkHireWayName) {
        this.fkHireWayName = fkHireWayName;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public String getFkIntermediaryName() {
        return fkIntermediaryName;
    }

    public void setFkIntermediaryName(String fkIntermediaryName) {
        this.fkIntermediaryName = fkIntermediaryName;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Boolean getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Boolean fIsdel) {
        this.fIsdel = fIsdel;
    }

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }

	public Long getFkAttachmentId() {
		return fkAttachmentId;
	}

	public void setFkAttachmentId(Long fkAttachmentId) {
		this.fkAttachmentId = fkAttachmentId;
	}

	public String getFkAttachmentName() {
		return fkAttachmentName;
	}

	public void setFkAttachmentName(String fkAttachmentName) {
		this.fkAttachmentName = fkAttachmentName;
	}

	public String getFkAttachmentPath() {
		return fkAttachmentPath;
	}

	public void setFkAttachmentPath(String fkAttachmentPath) {
		this.fkAttachmentPath = fkAttachmentPath;
	}

	public String getFkAttachmentExt() {
		return fkAttachmentExt;
	}

	public void setFkAttachmentExt(String fkAttachmentExt) {
		this.fkAttachmentExt = fkAttachmentExt;
	}
}