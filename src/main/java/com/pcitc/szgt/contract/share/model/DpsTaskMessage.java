package com.pcitc.szgt.contract.share.model;

import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;

/**
 * 插入待办消息参数
 */
public class DpsTaskMessage {

    private String businessId;

    private String businessName;

    private String creatorCode;

    private String categoryCode;

    private String executorCode;

    private String executorId;

    private String executorName;

    private Boolean sendToOa;

    private AppExtendsData extendsData;

    public AppExtendsData getExtendsData() {
        return extendsData;
    }

    public void setExtendsData(AppExtendsData extendsData) {
        this.extendsData = extendsData;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getExecutorCode() {
        return executorCode;
    }

    public void setExecutorCode(String executorCode) {
        this.executorCode = executorCode;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCreatorCode() {
        return creatorCode;
    }

    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
    }

    public Boolean getSendToOa() {
        return sendToOa;
    }

    public void setSendToOa(Boolean sendToOa) {
        this.sendToOa = sendToOa;
    }
}
