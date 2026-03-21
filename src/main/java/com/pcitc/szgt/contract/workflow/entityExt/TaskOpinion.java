package com.pcitc.szgt.contract.workflow.entityExt;

import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;

import java.util.Date;

public class TaskOpinion {
    public String opinionId;
    public String appId;
    public String taskId;
    public Integer taskType;
    public String executeId;
    public String businessId;
    public String businessCode;
    public String businessName;
    public Integer executeResult;
    public String executeResultName;
    public String remark;
    public String opinion;
    public String executorId;
    public String executorName;
    public String executorDeptId;
    public String executorDeptName;
    public String executorOrgId;
    public String executorOrgName;
    public Date executeDate;
    public Integer dataState;
    public String activityName;
    public String categoryCode;
    public AttachmentResultVo attachmentResultVo;
    //2021-05-21 新增加授权人
    public String certigier ;
}
