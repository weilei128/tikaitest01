package com.pcitc.legalAffairs.service.dps.entity;

import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import lombok.Data;

import java.util.List;

/***
 * @description 启动工作流流程
 * @author leigang
 * @date 2020年3月27日 09:53:53
 *
 */
@Data
public class StartVo {

    private String appId;
    private String categoryCode;
    private String ownKind;
    private String organiseId;
    private String businessId;
    private String businessCode;
    private String businessName;
    private String organiseName;
    private String workflowId;
    private List<AppMetasData> metasList;
    private List<AppVariableData> variableList;
    private AppExtendsData extendsData;

    private String userId;
    private String userCode;
    private String userName;
    
    private String executorId;
    private String executorCode;
    private String executorName;
}
