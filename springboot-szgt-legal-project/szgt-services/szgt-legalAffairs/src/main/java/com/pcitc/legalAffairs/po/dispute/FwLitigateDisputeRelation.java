package com.pcitc.legalAffairs.po.dispute;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 */
@TableName("fw_litigate_dispute_relation")
public class FwLitigateDisputeRelation {
	@TableId
    private Long fId;

    private Long fkDisputeId;

    private Long fkRelatedId;


    @TableField(fill = FieldFill.INSERT)
    private String fCreateuser;
    @TableField(fill = FieldFill.INSERT)
    private String fCreatename;
    @TableField(fill = FieldFill.INSERT)
    private Date fCreatetime;
	@TableField(fill = FieldFill.UPDATE)
    private String fUpdateuser;
    @TableField(fill = FieldFill.UPDATE)
    private String fUpdatename;
    @TableField(fill = FieldFill.UPDATE)
    private Date fUpdatetime;

    private Integer fType;

    private Integer fState;

    private Integer fSort;

    @TableLogic
    private Integer fIsdel;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkDisputeId() {
        return fkDisputeId;
    }

    public void setFkDisputeId(Long fkDisputeId) {
        this.fkDisputeId = fkDisputeId;
    }

    public Long getFkRelatedId() {
        return fkRelatedId;
    }

    public void setFkRelatedId(Long fkRelatedId) {
        this.fkRelatedId = fkRelatedId;
    }

	public Integer getfType() {
		return fType;
	}

	public void setfType(Integer fType) {
		this.fType = fType;
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

	public Integer getfIsdel() {
		return fIsdel;
	}

	public void setfIsdel(Integer fIsdel) {
		this.fIsdel = fIsdel;
	}

}