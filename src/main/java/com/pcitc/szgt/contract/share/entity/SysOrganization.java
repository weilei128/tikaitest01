package com.pcitc.szgt.contract.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 组织机构基础信息表
 * </p>
 *
 * @author jobob
 * @since 2020-02-25
 */
public class SysOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 	数据主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Integer fId;

    /**
     * 组织机构唯一编码
     */
    @TableField("f_Code")
    private String fCode;

    /**
     * 	组织机构全称
     */
    @TableField("f_Name")
    private String fName;

    /**
     * 组织机构简称
     */
    @TableField("f_Short_Name")
    private String fShortName;

    /**
     * 上级机构id
     */
    @TableField("fk_Parent_Id")
    private Integer fkParentId;

    /**
     * 上级组织机构名称
     */
    @TableField("fk_Parent_Name")
    private String fkParentName;

    /**
     * 组织机构地址
     */
    @TableField("f_Address")
    private String fAddress;

    /**
     * 描述
     */
    @TableField("f_Description")
    private String fDescription;

    /**
     * 组织机构层级
     */
    @TableField("f_Level")
    private Integer fLevel;

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
     * 类型：0部门，1公司
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

    //修改人姓名
    @TableField("f_Update_Name")
    private String fUpdateName;
    //修改时间
    @TableField("f_Update_Time")
    private LocalDateTime fUpdateTime;

    //累计损失金额合同案件总数量
    private Integer caseTotal  ;
    //累计合同案件损失金额（万元）
    private BigDecimal caseAmount ; 
    //履行中合同总数量
    private Integer fulfilTotal ;
    //倒签合同数量
    private Integer backdateTotal ;
    //正常签订合同数量
    private Integer normallyTotal ;
    //合同倒签率
    private Double  backdateRate;

    private Integer fIsVirtual;
    
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getfShortName() {
        return fShortName;
    }

    public void setfShortName(String fShortName) {
        this.fShortName = fShortName;
    }
    public Integer getFkParentId() {
        return fkParentId;
    }

    public void setFkParentId(Integer fkParentId) {
        this.fkParentId = fkParentId;
    }
    public String getFkParentName() {
        return fkParentName;
    }

    public void setFkParentName(String fkParentName) {
        this.fkParentName = fkParentName;
    }
    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }
    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }
    public Integer getfLevel() {
        return fLevel;
    }

    public void setfLevel(Integer fLevel) {
        this.fLevel = fLevel;
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

	public Integer getCaseTotal() {
		return caseTotal;
	}

	public void setCaseTotal(Integer caseTotal) {
		this.caseTotal = caseTotal;
	}

	public BigDecimal getCaseAmount() {
		return caseAmount;
	}

	public void setCaseAmount(BigDecimal caseAmount) {
		this.caseAmount = caseAmount;
	}

	public Integer getFulfilTotal() {
		return fulfilTotal;
	}

	public void setFulfilTotal(Integer fulfilTotal) {
		this.fulfilTotal = fulfilTotal;
	}

	public Integer getBackdateTotal() {
		return backdateTotal;
	}

	public void setBackdateTotal(Integer backdateTotal) {
		this.backdateTotal = backdateTotal;
	}

	public Integer getNormallyTotal() {
		return normallyTotal;
	}

	public void setNormallyTotal(Integer normallyTotal) {
		this.normallyTotal = normallyTotal;
	}

	public Double getBackdateRate() {
		return backdateRate;
	}

	public void setBackdateRate(Double backdateRate) {
		this.backdateRate = backdateRate;
	}

    public Integer getfIsVirtual() {
        return fIsVirtual;
    }

    public void setfIsVirtual(Integer fIsVirtual) {
        this.fIsVirtual = fIsVirtual;
    }

    @Override
    public String toString() {
        return "SysOrganization{" +
        "fId=" + fId +
        ", fCode=" + fCode +
        ", fName=" + fName +
        ", fShortName=" + fShortName +
        ", fkParentId=" + fkParentId +
        ", fkParentName=" + fkParentName +
        ", fAddress=" + fAddress +
        ", fDescription=" + fDescription +
        ", fLevel=" + fLevel +
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
