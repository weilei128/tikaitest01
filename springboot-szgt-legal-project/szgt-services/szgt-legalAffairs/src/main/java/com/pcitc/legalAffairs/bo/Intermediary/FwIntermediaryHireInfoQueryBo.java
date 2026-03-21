package com.pcitc.legalAffairs.bo.Intermediary;

public class FwIntermediaryHireInfoQueryBo {
    /**
     * 聘用类型:0,常年法律顾问聘用;1,专项业务聘用
     */
    private Short fHireType;
    /**
     * 聘用方式名称
     */
    private String fHireWayName;
    /**
     * 工作流状态ID
     */
    private Integer fWorkFlowId;
    /**
     * 聘用方式编码
     */
    private String fHireWayCode;
    /**
     * 预计服务费用(人民币)
     */
    private String fPreServiceCost;
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

    private int pageIndex;

    private int pageSize;

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

	public Integer getfWorkFlowId() {
		return fWorkFlowId;
	}

	public void setfWorkFlowId(Integer fWorkFlowId) {
		this.fWorkFlowId = fWorkFlowId;
	}
}
