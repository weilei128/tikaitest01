package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
public class WfMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 待办/已办主键
     */
    @TableId("messageId")
    private String messageId;

    @TableField("taskId")
    private String taskId;

    /**
     * 来自的待办Id	
     */
    @TableField("fromTaskId")
    private String fromTaskId;

    /**
     * 业务id
     */
    @TableField("businessId")
    private String businessId;

    /**
     * 业务名称
     */
    @TableField("businessName")
    private String businessName;

    /**
     * 待办所在的活动Id	
     */
    @TableField("activityId")
    private String activityId;

    /**
     * 待办所在的实例Id
     */
    @TableField("instanceId")
    private String instanceId;

    /**
     * 对应的流程模板Id
     */
    @TableField("workflowId")
    private String workflowId;

    /**
     * 对应的流程模板的版本号
     */
    private String versions;

    /**
     * 流程分类编码	
     */
    @TableField("categoryCode")
    private String categoryCode;

    /**
     * 活动名称
     */
    @TableField("activityName")
    private String activityName;

    /**
     * 活动类型Id 1 WorkflowUser 用户待办  2WorkflowRole 角色用户待办 3Coordinate系统审查待办 4Selective 分发待办 7Sponsor 经办人待办 8Proxy代理人待办 9WorkflowPosition 岗位用户待办 10 CC抄送待办
     */
    @TableField("activityType")
    private String activityType;

    /**
     * 活动类型名称	
     */
    @TableField("activityTypeName")
    private String activityTypeName;

    /**
     * 执行方式  0Sequence顺序 1Paraller 并行 2Vote选举
     */
    @TableField("executeType")
    private Integer executeType;

    /**
     * 审批度（执行方式为选举时）	
     */
    private Integer degree;

    /**
     * 待办类型 0Sequence顺序 1Paraller 并行 2Vote选举	
     */
    @TableField("taskType")
    private Integer taskType;

    /**
     * 执行结果（审批通过，退回等）-1 Forword未审批 2Complete完成 审批通过  3Revert退回 7Skip 跳过 8Coordinate协同审查	
     */
    @TableField("executeResult")
    private Integer executeResult;

    /**
     * 发送人Id	
     */
    @TableField("sendUserId")
    private String sendUserId;

    /**
     * 发送人名称	
     */
    @TableField("sendUserName")
    private String sendUserName;

    /**
     * 执行人Id	
     */
    @TableField("executorId")
    private String executorId;

    /**
     * 执行人名称	
     */
    @TableField("executorName")
    private String executorName;

    /**
     * 代理人Id	
     */
    @TableField("agentId")
    private String agentId;

    /**
     * 代理人名称	
     */
    @TableField("agentName")
    private String agentName;

    /**
     * 处理人Id	
     */
    @TableField("handlerId")
    private String handlerId;

    /**
     * 处理人名称	
     */
    @TableField("handlerName")
    private String handlerName;

    /**
     * 发送时间
     */
    @TableField("sendDate")
    private LocalDateTime sendDate;

    /**
     * 接受时间（第一次打开待办的时间）	
     */
    @TableField("receiveDate")
    private LocalDateTime receiveDate;

    /**
     * 处理时间	
     */
    @TableField("executeDate")
    private LocalDateTime executeDate;

    /**
     * 审批意见
     */
    private String opinion;

    /**
     * 处理人所在部门Id	
     */
    @TableField("departmentId")
    private String departmentId;

    /**
     * 处理人所在部门名称
     */
    @TableField("departmentName")
    private String departmentName;

    @TableField("unitId")
    private String unitId;

    /**
     * 处理人所在单位名称	
     */
    @TableField("unitName")
    private String unitName;

    /**
     * 状态0 未读 1已读 2已处理
     */
    @TableField("messageState")
    private Integer messageState;

    /**
     * 合同代办标识	
     */
    private String ext001;

    /**
     * 合同序号	
     */
    private String ext002;

    /**
     * 合同名称
     */
    private String ext003;

    /**
     * 合同环节
     */
    private String ext004;

    /**
     * 主办部门ID	
     */
    private String ext005;

    /**
     * 主办部门名称	
     */
    private String ext006;

    /**
     * 是否框架合同	
     */
    private String ext007;

    /**
     * 经办人名称	
     */
    private String ext008;

    /**
     * 合同模块id	
     */
    private String ext009;

    /**
     * 标的金额
     */
    private BigDecimal ext010;

    /**
     * 标的金额币种id	
     */
    private String ext011;

    /**
     * 标的金额币种中文	
     */
    private String ext012;

    /**
     * 合同类型1id	
     */
    private String ext013;

    /**
     * 合同类型2id	
     */
    private String ext014;

    /**
     * 合同类型3id	
     */
    private String ext015;

    /**
     * 合同类型4id	
     */
    private String ext016;

    /**
     * 合同类型名称	
     */
    private String ext017;

    /**
     * 合同环节中文名	
     */
    private String ext018;

    /**
     * 合同模块中文名	
     */
    private String ext019;

    /**
     * 合同环节id	
     */
    private String ext020;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    @TableField("modifieDdate")
    private LocalDateTime modifieDdate;

    /**
     * 是否删除
     */
    @TableField("logicDel")
    private Integer logicDel;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getFromTaskId() {
        return fromTaskId;
    }

    public void setFromTaskId(String fromTaskId) {
        this.fromTaskId = fromTaskId;
    }
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }
    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }
    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }
    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
    public Integer getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(Integer executeResult) {
        this.executeResult = executeResult;
    }
    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }
    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
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
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }
    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }
    public LocalDateTime getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDateTime executeDate) {
        this.executeDate = executeDate;
    }
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }
    public String getExt001() {
        return ext001;
    }

    public void setExt001(String ext001) {
        this.ext001 = ext001;
    }
    public String getExt002() {
        return ext002;
    }

    public void setExt002(String ext002) {
        this.ext002 = ext002;
    }
    public String getExt003() {
        return ext003;
    }

    public void setExt003(String ext003) {
        this.ext003 = ext003;
    }
    public String getExt004() {
        return ext004;
    }

    public void setExt004(String ext004) {
        this.ext004 = ext004;
    }
    public String getExt005() {
        return ext005;
    }

    public void setExt005(String ext005) {
        this.ext005 = ext005;
    }
    public String getExt006() {
        return ext006;
    }

    public void setExt006(String ext006) {
        this.ext006 = ext006;
    }
    public String getExt007() {
        return ext007;
    }

    public void setExt007(String ext007) {
        this.ext007 = ext007;
    }
    public String getExt008() {
        return ext008;
    }

    public void setExt008(String ext008) {
        this.ext008 = ext008;
    }
    public String getExt009() {
        return ext009;
    }

    public void setExt009(String ext009) {
        this.ext009 = ext009;
    }
    public BigDecimal getExt010() {
        return ext010;
    }

    public void setExt010(BigDecimal ext010) {
        this.ext010 = ext010;
    }
    public String getExt011() {
        return ext011;
    }

    public void setExt011(String ext011) {
        this.ext011 = ext011;
    }
    public String getExt012() {
        return ext012;
    }

    public void setExt012(String ext012) {
        this.ext012 = ext012;
    }
    public String getExt013() {
        return ext013;
    }

    public void setExt013(String ext013) {
        this.ext013 = ext013;
    }
    public String getExt014() {
        return ext014;
    }

    public void setExt014(String ext014) {
        this.ext014 = ext014;
    }
    public String getExt015() {
        return ext015;
    }

    public void setExt015(String ext015) {
        this.ext015 = ext015;
    }
    public String getExt016() {
        return ext016;
    }

    public void setExt016(String ext016) {
        this.ext016 = ext016;
    }
    public String getExt017() {
        return ext017;
    }

    public void setExt017(String ext017) {
        this.ext017 = ext017;
    }
    public String getExt018() {
        return ext018;
    }

    public void setExt018(String ext018) {
        this.ext018 = ext018;
    }
    public String getExt019() {
        return ext019;
    }

    public void setExt019(String ext019) {
        this.ext019 = ext019;
    }
    public String getExt020() {
        return ext020;
    }

    public void setExt020(String ext020) {
        this.ext020 = ext020;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public LocalDateTime getModifieDdate() {
        return modifieDdate;
    }

    public void setModifieDdate(LocalDateTime modifieDdate) {
        this.modifieDdate = modifieDdate;
    }
    public Integer getLogicDel() {
        return logicDel;
    }

    public void setLogicDel(Integer logicDel) {
        this.logicDel = logicDel;
    }

    @Override
    public String toString() {
        return "WfMessage{" +
        "messageId=" + messageId +
        ", taskId=" + taskId +
        ", fromTaskId=" + fromTaskId +
        ", businessId=" + businessId +
        ", businessName=" + businessName +
        ", activityId=" + activityId +
        ", instanceId=" + instanceId +
        ", workflowId=" + workflowId +
        ", versions=" + versions +
        ", categoryCode=" + categoryCode +
        ", activityName=" + activityName +
        ", activityType=" + activityType +
        ", activityTypeName=" + activityTypeName +
        ", executeType=" + executeType +
        ", degree=" + degree +
        ", taskType=" + taskType +
        ", executeResult=" + executeResult +
        ", sendUserId=" + sendUserId +
        ", sendUserName=" + sendUserName +
        ", executorId=" + executorId +
        ", executorName=" + executorName +
        ", agentId=" + agentId +
        ", agentName=" + agentName +
        ", handlerId=" + handlerId +
        ", handlerName=" + handlerName +
        ", sendDate=" + sendDate +
        ", receiveDate=" + receiveDate +
        ", executeDate=" + executeDate +
        ", opinion=" + opinion +
        ", departmentId=" + departmentId +
        ", departmentName=" + departmentName +
        ", unitId=" + unitId +
        ", unitName=" + unitName +
        ", messageState=" + messageState +
        ", ext001=" + ext001 +
        ", ext002=" + ext002 +
        ", ext003=" + ext003 +
        ", ext004=" + ext004 +
        ", ext005=" + ext005 +
        ", ext006=" + ext006 +
        ", ext007=" + ext007 +
        ", ext008=" + ext008 +
        ", ext009=" + ext009 +
        ", ext010=" + ext010 +
        ", ext011=" + ext011 +
        ", ext012=" + ext012 +
        ", ext013=" + ext013 +
        ", ext014=" + ext014 +
        ", ext015=" + ext015 +
        ", ext016=" + ext016 +
        ", ext017=" + ext017 +
        ", ext018=" + ext018 +
        ", ext019=" + ext019 +
        ", ext020=" + ext020 +
        ", createDate=" + createDate +
        ", modifieDdate=" + modifieDdate +
        ", logicDel=" + logicDel +
        "}";
    }
}
