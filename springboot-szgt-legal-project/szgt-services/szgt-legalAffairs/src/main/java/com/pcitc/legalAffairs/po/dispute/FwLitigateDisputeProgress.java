package com.pcitc.legalAffairs.po.dispute;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 纠纷进展信息表
 */
@TableName(value = "fw_litigate_dispute_progress")
public class FwLitigateDisputeProgress implements Serializable {
	
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
     * 案件阶段
     */
    private Integer fStep;

    /**
     * 案件状态
     */
    private Integer fStatus;
    
    /**
     * 是否已生成案号
     */
    private Integer fHaveCaseCode;
    
    /**
     * 案号
     */
    private Integer fCaseCode;

    /**
     * 开始时间
     */
    private Date fBegindate;

    /**
     * 结束时间
     */
    private Date fEnddate;

    /**
     * 是否大事记
     */
    private Byte fIsEvent;

    /**
     * 是否胜诉
     */
    private Byte fIsVictory;

    /**
     * 进展描述
     */
    private String fDescription;

    /**
     * 进展附件ID
     */
    private Long fkProgressAttachmentId;

    /**
     * 进展附件路径
     */
    private String fkProgressAttachmentPath;

    /**
     * 进展附件文件名
     */
    private String fkProgressAttachmentName;

    /**
     * 进展附件扩展名
     */
    private String fkProgressAttachmentExt;

    /**
     * 受理机构
     */
    private String fReceivingAgency;

    /**
     * 受理时间
     */
    private Date fReceiveDate;

    /**
     * 案由1
     */
    @TableField("f_Cause_1")
    private Integer fCause1;

    /**
     * 案由2
     */
    @TableField("f_Cause_2")
    private Integer fCause2;

    /**
     * 案由3
     */
    @TableField("f_Cause_3")
    private Integer fCause3;

    /**
     * 案由4
     */
    @TableField("f_Cause_4")
    private Integer fCause4;

    /**
     * 相关附件ID
     */
    private Long fkAttachmentId;

    /**
     * 相关附件路径
     */
    private String fkAttachmentPath;

    /**
     * 相关附件文件名
     */
    private String fkAttachmentName;

    /**
     * 相关附件扩展名
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

    public Integer getfStep() {
        return fStep;
    }

    public void setfStep(Integer fStep) {
        this.fStep = fStep;
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    public Date getfBegindate() {
        return fBegindate;
    }

    public void setfBegindate(Date fBegindate) {
        this.fBegindate = fBegindate;
    }

    public Date getfEnddate() {
        return fEnddate;
    }

    public void setfEnddate(Date fEnddate) {
        this.fEnddate = fEnddate;
    }

    public Byte getfIsEvent() {
        return fIsEvent;
    }

    public void setfIsEvent(Byte fIsEvent) {
        this.fIsEvent = fIsEvent;
    }

    public Byte getfIsVictory() {
        return fIsVictory;
    }

    public void setfIsVictory(Byte fIsVictory) {
        this.fIsVictory = fIsVictory;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Long getFkProgressAttachmentId() {
        return fkProgressAttachmentId;
    }

    public void setFkProgressAttachmentId(Long fkProgressAttachmentId) {
        this.fkProgressAttachmentId = fkProgressAttachmentId;
    }

    public String getFkProgressAttachmentPath() {
        return fkProgressAttachmentPath;
    }

    public void setFkProgressAttachmentPath(String fkProgressAttachmentPath) {
        this.fkProgressAttachmentPath = fkProgressAttachmentPath;
    }

    public String getFkProgressAttachmentName() {
        return fkProgressAttachmentName;
    }

    public void setFkProgressAttachmentName(String fkProgressAttachmentName) {
        this.fkProgressAttachmentName = fkProgressAttachmentName;
    }

    public String getFkProgressAttachmentExt() {
        return fkProgressAttachmentExt;
    }

    public void setFkProgressAttachmentExt(String fkProgressAttachmentExt) {
        this.fkProgressAttachmentExt = fkProgressAttachmentExt;
    }

    public String getfReceivingAgency() {
        return fReceivingAgency;
    }

    public void setfReceivingAgency(String fReceivingAgency) {
        this.fReceivingAgency = fReceivingAgency;
    }

    public Date getfReceiveDate() {
        return fReceiveDate;
    }

    public void setfReceiveDate(Date fReceiveDate) {
        this.fReceiveDate = fReceiveDate;
    }

    public Integer getfCause1() {
        return fCause1;
    }

    public void setfCause1(Integer fCause1) {
        this.fCause1 = fCause1;
    }

    public Integer getfCause2() {
        return fCause2;
    }

    public void setfCause2(Integer fCause2) {
        this.fCause2 = fCause2;
    }

    public Integer getfCause3() {
        return fCause3;
    }

    public void setfCause3(Integer fCause3) {
        this.fCause3 = fCause3;
    }

    public Integer getfCause4() {
        return fCause4;
    }

    public void setfCause4(Integer fCause4) {
        this.fCause4 = fCause4;
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

	public Integer getfHaveCaseCode() {
		return fHaveCaseCode;
	}

	public void setfHaveCaseCode(Integer fHaveCaseCode) {
		this.fHaveCaseCode = fHaveCaseCode;
	}

	public Integer getfCaseCode() {
		return fCaseCode;
	}

	public void setfCaseCode(Integer fCaseCode) {
		this.fCaseCode = fCaseCode;
	}
}