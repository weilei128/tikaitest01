package com.pcitc.szgt.contract.share.model;

public class TaskCountQueryModel {

    private String businessCodeOrName;

    private String categoryCode;

    private String startTime;

    private String endTime;

    private Integer includeMsg;

    private String query;

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
}
