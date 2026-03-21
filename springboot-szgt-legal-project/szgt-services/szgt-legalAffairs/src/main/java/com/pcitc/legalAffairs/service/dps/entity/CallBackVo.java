package com.pcitc.legalAffairs.service.dps.entity;

import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppParticipantData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import lombok.Data;

import java.util.Date;
import java.util.List;

/***
 * @description 各个业务回调参数
 * @author leigang
 * @date 2020年3月18日 11:04:07
 *
 */
@Data
public class CallBackVo {

    //回调类型
    private Integer callBackType;
    private Integer category;
    private String taskId;
    private Integer result;
    private String executorId;
    private String businessId;
    private String activityId;
    private String activityName;
    private String userId;
    private String userName;
    private String message;
    private String categoryCode;
    private Date executeDate;
    private List<String> taskIdList;
    private List<String> executorIdList;
    private List<AppParticipantData> participantList;
    private List<AppVariableData> variableList;
    private List<AppMetasData> metasList;

}
