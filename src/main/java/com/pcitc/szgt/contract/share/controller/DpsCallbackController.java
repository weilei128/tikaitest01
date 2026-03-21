package com.pcitc.szgt.contract.share.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.vars.ExecuteResult;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.common.enums.ContractEnum;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.entity.WfMessage;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.make.mapper.WfMessageMapper;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.DpsCallbackVo;
import com.pcitc.szgt.contract.share.model.DpsTaskMessage;
import com.pcitc.szgt.contract.share.request.DpsRequest;
import com.pcitc.szgt.contract.share.request.OfficeAgentRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("dpscallback")
public class DpsCallbackController {
    @Autowired
    private IWorkFlowService workFlowService;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private CrContractbasicMapper crContractbasicMapper;

    @Autowired
    private CrContractinfoMapper crContractinfoMapper;

    @Autowired
    private WfMessageMapper wfMessageMapper;

    @Autowired
    private DpsRequest dpsRequest;

    @Autowired
    private DpsCallbackController thisController;

    @Autowired
    private OfficeAgentRequest agentRequest ;

    private Logger logger = LoggerFactory.getLogger(DpsCallbackController.class);

    @PostMapping("cba")
    public void cba(@RequestBody DpsCallbackVo dpsCallbackVo) {
        System.out.println("dpsCallbackVo:" + dpsCallbackVo);
    }

    /**
     * 	合同订立审批结束
     */
    @PostMapping("makeApprove")
    public DataResult<?> makeApprove(@RequestBody DpsCallbackVo dpsCallbackVo) {
        boolean isSuccess = false;
        String json = JSON.toJSONString(dpsCallbackVo);
        logger.info("/dpscallback/treatmentApprove" + json);
        //审批同意
        if (dpsCallbackVo.getCallBackType() == 1) {
            isSuccess = workFlowService.makeApprove(dpsCallbackVo.getBusinessId());
        } else if(dpsCallbackVo.getCallBackType() == 10){
            //给秘书发送待办
            thisController.sendTaskToMishu(dpsCallbackVo.getTaskIdList(), dpsCallbackVo.getExecutorIdList(),
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 7){
            //待办处理
            completeMishuTask(dpsCallbackVo.getTaskId());
        } else if(dpsCallbackVo.getCallBackType() == 11){
            //退回返回
            //给秘书发送待办
            List<String> taskIds = new ArrayList<>();
            List<String> executorIdList = new ArrayList<>();
            taskIds.add(dpsCallbackVo.getTaskId());
            executorIdList.add(dpsCallbackVo.getUserId());

            thisController.sendTaskToMishu(taskIds, executorIdList,
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 6){
            CrContractbasic crContractbasic = crContractbasicMapper.selectById(dpsCallbackVo.getBusinessId());
            agentRequest.sendMessageForTransactor(crContractbasic.getContractID(),ContractEnum.enumSectionMap.get(crContractbasic.getSection().toString()));
        }
        return DataResult.success(isSuccess);
    }

    /**
     * 完成秘书待办
     */
    private void completeMishuTask(String taskId){
        if(StringUtils.isEmpty(taskId)){
            return;
        }
        List<WfMessage> wfMessages = wfMessageMapper.selectList(new QueryWrapper<WfMessage>().lambda().
                eq(WfMessage::getTaskId, taskId).
                eq(WfMessage::getExecuteResult, 100).
                eq(WfMessage::getLogicDel, 0).
                eq(WfMessage::getMessageState, ContractEnum.EnumMessageSate.UnRead));

        if(!CollectionUtils.isEmpty(wfMessages)){
            AppCallResult appCallResult = dpsRequest.taskMessageComplete(wfMessages.get(0).getExt003());
            if(appCallResult.getResult()){
                wfMessages.get(0).setMessageState(Integer.parseInt(ContractEnum.EnumMessageSate.Deal.getCode()));
                wfMessageMapper.updateById(wfMessages.get(0));
            }
        }
    }

    /**
     * 	发送秘书待办
     * @param taskIdList
     * @param executorIdList
     */
    @Transactional
    public void sendTaskToMishu(List<String> taskIdList, List<String> executorIdList, String businessId, String categoryCode){
        if(CollectionUtils.isEmpty(taskIdList)){
            return;
        }

        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrContractbasic::getContractID, businessId).last("for update");
        CrContractbasic crContractBasic = crContractbasicMapper.selectOne(queryWrapper);
        CrContractinfo crContractInfo = crContractinfoMapper.selectById(businessId);

        Integer[] integers = executorIdList.stream().map(Integer::parseInt).toArray(Integer[]::new);
        List<SysUserinfo> sysUserinfos = userInfoRequest.queryByIdBatch(integers);
        Map<Integer, SysUserinfo> users = sysUserinfos.stream().collect(Collectors.toMap(SysUserinfo::getfId, Function.identity(), (k1, k2) -> k1));

        for(int i = 0;i<taskIdList.size();i++){
            SysUserinfo userInfo = users.get(Integer.parseInt(executorIdList.get(i)));
            if(userInfo == null){
                continue;
            }

            if(Constants.secretaryMap.containsKey(userInfo.getfCode())){
                //秘书用户编码
                String msCode = Constants.secretaryMap.get(userInfo.getfCode());
                //秘书用户
                SysUserinfo msUser = userInfoRequest.queryByCode(msCode);
                //如要发送OA,系统管理接收必填字段项
                DpsTaskMessage taskMessage = new DpsTaskMessage();
                taskMessage.setBusinessId(businessId);
                taskMessage.setCategoryCode(categoryCode);
                taskMessage.setExecutorId(msUser.getfId().toString());
                taskMessage.setExecutorName(msUser.getfCname());
                taskMessage.setExecutorCode(msUser.getfCode());
                taskMessage.setBusinessName(crContractBasic.getContractName());

                SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(crContractBasic.getMainOrgUserID()));
                if(sysUserinfo != null){
                    taskMessage.setCreatorCode(sysUserinfo.getfCode());
                }

                AppExtendsData appExtendsData = workFlowService.setExtendsData(crContractBasic.getContractID(), crContractBasic, crContractInfo,
                        ContractEnum.EnumModule.Make.getCode(), "", "");
                taskMessage.setExtendsData(appExtendsData);
                //秘书发送消息代办并发送给OA系统，此处固定为true，必须发送。
                taskMessage.setSendToOa(true);
                
                AppCallResult appCallResult = dpsRequest.taskMessage(taskMessage);

                WfMessage wfMessage = new WfMessage();
                wfMessage.setMessageId(UUID.randomUUID().toString());
                wfMessage.setTaskId(taskIdList.get(i));
                wfMessage.setBusinessId(appExtendsData.getBusinessId());//业务数据ID
                wfMessage.setBusinessName(appExtendsData.getExt003());//业务名称
                wfMessage.setCategoryCode(categoryCode);//流程分类编码
                //设置固定秘书执行结果 100
                wfMessage.setExecuteResult(100);//执行结果（审批通过，退回等）-1 Forword未审批 2Complete完成 审批通过  3Revert退回 7Skip 跳过 8Coordinate协同审查
                wfMessage.setExt001(appExtendsData.getExt001());//合同代办标识
                wfMessage.setExt003(appCallResult.getId());
                wfMessage.setExt020(appExtendsData.getExt020());
                wfMessage.setMessageState(Integer.parseInt(ContractEnum.EnumMessageSate.UnRead.getCode()));
                wfMessage.setCreateDate(LocalDateTime.now());
                wfMessage.setLogicDel(Integer.parseInt(ContractEnum.EnumIsLogicDel.Valid.getCode()));
                wfMessageMapper.insert(wfMessage);
            }

        }
    }

    /**
     *	合同变更审批结束
     */
    @PostMapping("changeApprove")
    public DataResult<?> changeApprove(@RequestBody DpsCallbackVo dpsCallbackVo) {
        boolean isSuccess = false;
        String json = JSON.toJSONString(dpsCallbackVo);
        logger.info("/dpscallback/treatmentApprove" + json);
        if (dpsCallbackVo.getCallBackType() == 1) {
            isSuccess = workFlowService.changeApprove(dpsCallbackVo.getBusinessId());
        } else if(dpsCallbackVo.getCallBackType() == 10){
            //给秘书发送待办
            thisController.sendTaskToMishu(dpsCallbackVo.getTaskIdList(), dpsCallbackVo.getExecutorIdList(),
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 7){
            //待办处理
            completeMishuTask(dpsCallbackVo.getTaskId());
        } else if(dpsCallbackVo.getCallBackType() == 11){
            //退回返回
            //给秘书发送待办
            List<String> taskIds = new ArrayList<>();
            List<String> executorIdList = new ArrayList<>();
            taskIds.add(dpsCallbackVo.getTaskId());
            executorIdList.add(dpsCallbackVo.getUserId());

            thisController.sendTaskToMishu(taskIds, executorIdList,
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        }
        return DataResult.success(isSuccess);
    }

    /*
     * 合同转让审批结束
     * */
    @PostMapping("transApprove")
    public DataResult<?> transApprove(@RequestBody DpsCallbackVo dpsCallbackVo) {
        boolean isSuccess = false;
        String json = JSON.toJSONString(dpsCallbackVo);
        logger.info("/dpscallback/treatmentApprove" + json);
        if (dpsCallbackVo.getCallBackType() == 1) {
            isSuccess = workFlowService.transApprove(dpsCallbackVo.getBusinessId());
        } else if(dpsCallbackVo.getCallBackType() == 10){
            //给秘书发送待办
            thisController.sendTaskToMishu(dpsCallbackVo.getTaskIdList(), dpsCallbackVo.getExecutorIdList(),
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 7){
            //待办处理
            completeMishuTask(dpsCallbackVo.getTaskId());
        } else if(dpsCallbackVo.getCallBackType() == 11){
            //退回返回
            //给秘书发送待办
            List<String> taskIds = new ArrayList<>();
            List<String> executorIdList = new ArrayList<>();
            taskIds.add(dpsCallbackVo.getTaskId());
            executorIdList.add(dpsCallbackVo.getUserId());

            thisController.sendTaskToMishu(taskIds, executorIdList,
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        }
        return DataResult.success(isSuccess);
    }

    /**
     * 	合同终止审批结束
     */
    @PostMapping("endApprove")
    public DataResult<?> endApprove(@RequestBody DpsCallbackVo dpsCallbackVo) {
        boolean isSuccess = false;
        String json = JSON.toJSONString(dpsCallbackVo);
        logger.info("/dpscallback/treatmentApprove" + json);
        if (dpsCallbackVo.getCallBackType() == 1) {
            isSuccess = workFlowService.endApprove(dpsCallbackVo.getBusinessId());
        } else if(dpsCallbackVo.getCallBackType() == 10){
            //给秘书发送待办
            thisController.sendTaskToMishu(dpsCallbackVo.getTaskIdList(), dpsCallbackVo.getExecutorIdList(),
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 7){
            //待办处理
            completeMishuTask(dpsCallbackVo.getTaskId());
        } else if(dpsCallbackVo.getCallBackType() == 11){
            //退回返回
            //给秘书发送待办
            List<String> taskIds = new ArrayList<>();
            List<String> executorIdList = new ArrayList<>();
            taskIds.add(dpsCallbackVo.getTaskId());
            executorIdList.add(dpsCallbackVo.getUserId());

            thisController.sendTaskToMishu(taskIds, executorIdList,dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        }
        return DataResult.success(isSuccess);
    }

    /**
     * 合同终结审批结束
     */
    @PostMapping("treatmentApprove")
    public DataResult<?> treatmentApprove(@RequestBody DpsCallbackVo dpsCallbackVo) {
        boolean isSuccess = false;
        String json = JSON.toJSONString(dpsCallbackVo);
        logger.info("/dpscallback/treatmentApprove" + json);
        if (dpsCallbackVo.getCallBackType() == 1) {
            isSuccess = workFlowService.treatmentApprove(dpsCallbackVo.getBusinessId());
        } else if(dpsCallbackVo.getCallBackType() == 10){
            //给秘书发送待办
            thisController.sendTaskToMishu(dpsCallbackVo.getTaskIdList(), dpsCallbackVo.getExecutorIdList(),
                    dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        } else if(dpsCallbackVo.getCallBackType() == 7){
            //待办处理
            completeMishuTask(dpsCallbackVo.getTaskId());
        } else if(dpsCallbackVo.getCallBackType() == 11){
            //退回返回
            //给秘书发送待办
            List<String> taskIds = new ArrayList<>();
            List<String> executorIdList = new ArrayList<>();
            taskIds.add(dpsCallbackVo.getTaskId());
            executorIdList.add(dpsCallbackVo.getUserId());

            thisController.sendTaskToMishu(taskIds, executorIdList,dpsCallbackVo.getBusinessId(), dpsCallbackVo.getCategoryCode());
        }
        return DataResult.success(isSuccess);
    }

}
