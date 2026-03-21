package com.pcitc.szgt.contract.share.model;

public class TaskQueryModel {

    private String businessCodeOrName;

    private String categoryCode;

    private Integer current;

    private Integer size;

    private String startTime;

    private String endTime;

    private Integer includeMsg;

    private String query;
    private String groupBy;
    private String taskType;
    //2021-04-09 新增字段
    private Integer userId ;
    
    public String getBusinessCodeOrName() {
        return businessCodeOrName;
    }

    public void setBusinessCodeOrName(String businessCodeOrName) {
        this.businessCodeOrName = businessCodeOrName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getIncludeMsg() {
        return includeMsg;
    }

    public void setIncludeMsg(Integer includeMsg) {
        this.includeMsg = includeMsg;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    
}
