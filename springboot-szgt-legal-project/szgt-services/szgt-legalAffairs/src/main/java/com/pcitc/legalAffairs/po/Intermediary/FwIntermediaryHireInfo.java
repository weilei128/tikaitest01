package com.pcitc.legalAffairs.po.Intermediary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

import java.util.Date;

@TableName("fw_intermediary_hire_info")
public class FwIntermediaryHireInfo extends BasePojo {
    /**
     * 中介机构聘用信息主键
     */
    @TableId(value = "f_ID",type = IdType.AUTO)
    private Long fId;
    /**
     * 聘用类型:0,常年法律顾问聘用;1,专项业务聘用
     */
    private Short fHireType;
    private Integer fUserId;
    /**
     * 工作流状态id
     */
    private Integer fWorkFlowId;

    /**
     * 聘用方式名称
     */
    private String fHireWayName;
    /**
     * 聘用方式编码
     */
    private String fHireWayCode;
    /**
     * 预计服务费用(人民币)
     */
    private String fPreServiceCost;
    /**
     * 聘用时间
     */
    private Date fHireDateBegin;
    private Date fHireDateEnd;
    /**
     * 聘用说明
     */
    private String fHireState;
    /**
     * 服务范围
     */
    private String fServiceScope;
    /**
     * 专项业务名称
     */
    private String fSpeBusinessName;
    /**
     * 专项业务类型
     */
    private String fSpeBusinessType;
    /**
     * 联系人
     */
    private String fLinkman;
    /**
     * 专项业务基本情况
     */
    private String fSpeBusinessState;
    /**
     * 具体内容和需求
     */
    private String fDetailRequire;
    /**
     * 附件文件名称
     */
    private String fkAttachFileName;
    /**
     * 关联附件信息主键
     */
    private Long fkAttachId;
    /**
     * 主办部门ID
     */
    private Long fMainDepartId;
    /**
     * 主办部门
     */
    private String fMainDepart;
    /**
     * 单位属性
     */
    private String fProperty;
    /**
     * 经办人
     */
    private String fHandler;
    

    public Integer getfWorkFlowId() {
        return fWorkFlowId;
    }

    public void setfWorkFlowId(Integer fWorkFlowId) {
        this.fWorkFlowId = fWorkFlowId;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Short getfHireType() {
        return fHireType;
    }

    public void setfHireType(Short fHireType) {
        this.fHireType = fHireType;
    }

    public String getfHireWayName() {
        return fHireWayName;
    }

    public void setfHireWayName(String fHireWayName) {
        this.fHireWayName = fHireWayName;
    }

    public String getfHireWayCode() {
        return fHireWayCode;
    }

    public void setfHireWayCode(String fHireWayCode) {
        this.fHireWayCode = fHireWayCode;
    }

    public String getfPreServiceCost() {
        return fPreServiceCost;
    }

    public void setfPreServiceCost(String fPreServiceCost) {
        this.fPreServiceCost = fPreServiceCost;
    }

    public String getfHireState() {
        return fHireState;
    }

    public void setfHireState(String fHireState) {
        this.fHireState = fHireState;
    }

    public String getfServiceScope() {
        return fServiceScope;
    }

    public void setfServiceScope(String fServiceScope) {
        this.fServiceScope = fServiceScope;
    }

    public String getfSpeBusinessName() {
        return fSpeBusinessName;
    }

    public void setfSpeBusinessName(String fSpeBusinessName) {
        this.fSpeBusinessName = fSpeBusinessName;
    }

    public String getfSpeBusinessType() {
        return fSpeBusinessType;
    }

    public void setfSpeBusinessType(String fSpeBusinessType) {
        this.fSpeBusinessType = fSpeBusinessType;
    }

    public String getfLinkman() {
        return fLinkman;
    }

    public void setfLinkman(String fLinkman) {
        this.fLinkman = fLinkman;
    }

    public String getfSpeBusinessState() {
        return fSpeBusinessState;
    }

    public void setfSpeBusinessState(String fSpeBusinessState) {
        this.fSpeBusinessState = fSpeBusinessState;
    }

    public String getfDetailRequire() {
        return fDetailRequire;
    }

    public void setfDetailRequire(String fDetailRequire) {
        this.fDetailRequire = fDetailRequire;
    }

    public String getFkAttachFileName() {
        return fkAttachFileName;
    }

    public void setFkAttachFileName(String fkAttachFileName) {
        this.fkAttachFileName = fkAttachFileName;
    }

    public Long getFkAttachId() {
        return fkAttachId;
    }

    public void setFkAttachId(Long fkAttachId) {
        this.fkAttachId = fkAttachId;
    }

    public String getfMainDepart() {
        return fMainDepart;
    }

    public void setfMainDepart(String fMainDepart) {
        this.fMainDepart = fMainDepart;
    }

    public String getfProperty() {
        return fProperty;
    }

    public void setfProperty(String fProperty) {
        this.fProperty = fProperty;
    }

    public String getfHandler() {
        return fHandler;
    }

    public void setfHandler(String fHandler) {
        this.fHandler = fHandler;
    }

	public Date getfHireDateBegin() {
		return fHireDateBegin;
	}

	public void setfHireDateBegin(Date fHireDateBegin) {
		this.fHireDateBegin = fHireDateBegin;
	}

	public Date getfHireDateEnd() {
		return fHireDateEnd;
	}

	public void setfHireDateEnd(Date fHireDateEnd) {
		this.fHireDateEnd = fHireDateEnd;
	}

	public Integer getfUserId() {
		return fUserId;
	}

	public void setfUserId(Integer fUserId) {
		this.fUserId = fUserId;
	}

	public Long getfMainDepartId() {
		return fMainDepartId;
	}

	public void setfMainDepartId(Long fMainDepartId) {
		this.fMainDepartId = fMainDepartId;
	}
}
