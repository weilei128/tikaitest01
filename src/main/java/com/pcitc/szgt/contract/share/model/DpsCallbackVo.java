package com.pcitc.szgt.contract.share.model;

import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import com.pcitc.ssc.dps.inte.workflow.MetasData;
import com.pcitc.ssc.dps.inte.workflow.ParticipantData;

import java.util.List;

/**
 * 	工作流返回VO 
 *
 */
public class DpsCallbackVo {
    private String activityId;

    private String activityName;

    private String businessId;

    /**
     * 1 审批完成 2审批退回 3 选择退回 4 活动/参与者完成 5 撤销 
     * 6 流程发起 7 待办处理结果 8 活动激活前 9 活动完成后 10 发送待办
     */
    private Integer callBackType;

    private Integer category;

    private String categoryCode;

    private String executeDate;

    private String executorId;

    private List<String> executorIdList;

    private String messgae;

    private List<MetasData> metasList;

    private List<ParticipantData> participantList;

    private Integer result;

    private String taskId;

    private List<String> taskIdList;

    private String userId;

    private String userName;

    private List<AppVariableData> variableList;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public List<String> getExecutorIdList() {
        return executorIdList;
    }

    public void setExecutorIdList(List<String> executorIdList) {
        this.executorIdList = executorIdList;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public List<MetasData> getMetasList() {
        return metasList;
    }

    public void setMetasList(List<MetasData> metasList) {
        this.metasList = metasList;
    }

    public List<ParticipantData> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<ParticipantData> participantList) {
        this.participantList = participantList;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<String> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AppVariableData> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<AppVariableData> variableList) {
        this.variableList = variableList;
    }

    public Integer getCallBackType() {
        return callBackType;
    }

    public void setCallBackType(Integer callBackType) {
        this.callBackType = callBackType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DpsCallbackVo{");
        sb.append("activityId='").append(activityId).append('\'');
        sb.append(", activityName='").append(activityName).append('\'');
        sb.append(", businessId='").append(businessId).append('\'');
        sb.append(", callBackType=").append(callBackType);
        sb.append(", category=").append(category);
        sb.append(", categoryCode='").append(categoryCode).append('\'');
        sb.append(", executeDate='").append(executeDate).append('\'');
        sb.append(", executorId='").append(executorId).append('\'');
        sb.append(", executorIdList=").append(executorIdList);
        sb.append(", messgae='").append(messgae).append('\'');
        sb.append(", metasList=").append(metasList);
        sb.append(", participantList=").append(participantList);
        sb.append(", result=").append(result);
        sb.append(", taskId='").append(taskId).append('\'');
        sb.append(", taskIdList=").append(taskIdList);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", variableList=").append(variableList);
        sb.append('}');
        return sb.toString();
    }
}
