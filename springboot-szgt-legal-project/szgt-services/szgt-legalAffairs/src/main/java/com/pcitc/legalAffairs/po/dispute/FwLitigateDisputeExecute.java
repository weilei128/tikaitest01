package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 纠纷执行信息表
 * 
 */
@TableName(value = "fw_litigate_dispute_execute")
public class FwLitigateDisputeExecute implements Serializable {
	
	@TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    private Long fkDisputeId;

    private String fkDisputeName;

    /**
     * 申请执行人
     */
    private String fExecutor;

    /**
     * 被申请执行人
     */
    private String fExecuted;

    /**
     * 被追加执行人
     */
    private String fAddedExecuted;

    /**
     * 执行机构
     */
    private String fExecuteAgency;

    /**
     * 执行标的
     */
    private String fExecutionTarget;

    /**
     * 执行文件ID
     */
    private Long fkAttachmentId;

    /**
     * 执行文件路径
     */
    private String fkAttachmentPath;

    /**
     * 执行文件文件名
     */
    private String fkAttachmentName;

    /**
     * 执行文件扩展名
     */
    private String fkAttachmentExt;

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

    public String getfExecutor() {
        return fExecutor;
    }

    public void setfExecutor(String fExecutor) {
        this.fExecutor = fExecutor;
    }

    public String getfExecuted() {
        return fExecuted;
    }

    public void setfExecuted(String fExecuted) {
        this.fExecuted = fExecuted;
    }

    public String getfAddedExecuted() {
        return fAddedExecuted;
    }

    public void setfAddedExecuted(String fAddedExecuted) {
        this.fAddedExecuted = fAddedExecuted;
    }

    public String getfExecuteAgency() {
        return fExecuteAgency;
    }

    public void setfExecuteAgency(String fExecuteAgency) {
        this.fExecuteAgency = fExecuteAgency;
    }

    public String getfExecutionTarget() {
        return fExecutionTarget;
    }

    public void setfExecutionTarget(String fExecutionTarget) {
        this.fExecutionTarget = fExecutionTarget;
    }

    public Long getFkAttachmentId() {
        return fkAttachmentId;
    }

    public void setFkAttachmentId(Long fkAttachmentId) {
        this.fkAttachmentId = fkAttachmentId;
    }

    public String getFkAttachmentPath() {
        return fkAttachmentPath;
    }

    public void setFkAttachmentPath(String fkAttachmentPath) {
        this.fkAttachmentPath = fkAttachmentPath;
    }

    public String getFkAttachmentName() {
        return fkAttachmentName;
    }

    public void setFkAttachmentName(String fkAttachmentName) {
        this.fkAttachmentName = fkAttachmentName;
    }

    public String getFkAttachmentExt() {
        return fkAttachmentExt;
    }

    public void setFkAttachmentExt(String fkAttachmentExt) {
        this.fkAttachmentExt = fkAttachmentExt;
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