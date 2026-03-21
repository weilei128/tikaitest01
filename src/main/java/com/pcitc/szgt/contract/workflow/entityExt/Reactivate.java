package com.pcitc.szgt.contract.workflow.entityExt;

import java.util.List;

import com.pcitc.ssc.dps.inte.workflow.AppVariableData;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Reactivate" , description = "前端分发传过来的参数值")
public class Reactivate {
	
    public String  contractId;   //合同ID
    public String  categoryCode; //业务分类
    public String  taskId;       //执行的待办Id
    public String  opinion;      //审批意见
    public Integer activityType; //获取分发过来的类型参数
    public String  type ;        //审批分发的类型
    public List<Participant> participantJson;//分发的参与者的JSON字符串
    public List<AppVariableData>  variableList ; //用于记录用户的审批历史，是审批记录还是分发记录
}
