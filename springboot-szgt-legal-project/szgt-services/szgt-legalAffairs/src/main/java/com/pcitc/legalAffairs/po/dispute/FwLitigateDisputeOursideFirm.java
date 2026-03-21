package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 我方代理律所信息表
 */
@TableName(value = "fw_litigate_dispute_ourside_firm")
public class FwLitigateDisputeOursideFirm implements Serializable {
	
	@TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 纠纷ID
     */
    private Long fkDisputeId;

    /**
     * 纠纷名称
     */
    private String fkDisputeName;

    /**
     * 代理律所ID
     */
    private Long fkIntermediaryId;

    /**
     * 代理律所名称
     */
    private String fkIntermediaryName;

    /**
     * 代理律所统一社会信用代码
     */
    private String fkIntermediaryUscCode;

    /**
     * 代理律师
     */
    private String fActingLawyer;

    /**
     * 合同编号
     */
    private String fContractNo;

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

    public String getFkIntermediaryUscCode() {
        return fkIntermediaryUscCode;
    }

    public void setFkIntermediaryUscCode(String fkIntermediaryUscCode) {
        this.fkIntermediaryUscCode = fkIntermediaryUscCode;
    }

    public String getfActingLawyer() {
        return fActingLawyer;
    }

    public void setfActingLawyer(String fActingLawyer) {
        this.fActingLawyer = fActingLawyer;
    }

    public String getfContractNo() {
        return fContractNo;
    }

    public void setfContractNo(String fContractNo) {
        this.fContractNo = fContractNo;
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