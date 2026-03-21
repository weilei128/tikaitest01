package com.pcitc.szgt.contract.workflow.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.contract.workflow.entityExt.ApproveEx;
import com.pcitc.szgt.contract.workflow.entityExt.Reactivate;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <p> 工作流服务</p>
 * @author ziranzhou
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    private IWorkFlowService workFlowService;
    /**
     * 	用户接口
     */
    @Autowired
    private UserInfoRequest userInfoRequest;

    /**
     * 	合同订立审批结束
     */
    @PostMapping(value = "/makeApprove")
    public DataResult<?> makeApprove(@RequestParam(required = true) String contractId) {
        return DataResult.success(workFlowService.makeApprove(contractId));
    }

    /**
     * 	合同变更审批结束
     */
    @PostMapping(value = "/changeApprove")
    public DataResult<?> changeApprove(@RequestParam(required = true) String changeContractId) {
        return DataResult.success(workFlowService.changeApprove(changeContractId));
    }

    /**
     * 	合同转让审批结束
     */
    @PostMapping(value = "/transApprove")
    public DataResult<?> transApprove(@RequestParam(required = true) String transContractId) {
        return DataResult.success(workFlowService.transApprove(transContractId));
    }

    /**
     * 	合同终止审批结束
     */
    @PostMapping(value = "/endApprove")
    public DataResult<?> endApprove(@RequestParam(required = true) String endId) {
        return DataResult.success(workFlowService.endApprove(endId));
    }

    /**
     * 	合同终结审批结束
     */
    @PostMapping(value = "/treatmentApprove")
    public DataResult<?> treatmentApprove(@RequestParam(required = true) String endId) {
        return DataResult.success(workFlowService.treatmentApprove(endId));
    }

    /**
     * 	合同审批通过
     */
    @PostMapping(value = "/complete")
    public DataResult<?> complete(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId,
                               @RequestParam(required = true) String opinion, @RequestParam(required = true) String categoryCode,
                               @RequestParam() String variableList) {
        return DataResult.success(workFlowService.complete(taskId, contractId, opinion, categoryCode,variableList));
    }

    /**
     * 	合同审批退回
     */
    @PostMapping(value = "/revert")
    public DataResult<?> revert(@RequestBody ApproveEx approveEx) {
        return DataResult.success(workFlowService.revert(approveEx));
    }

    /**
     * 	退回提交至退回人
     */
    @PostMapping(value = "/backrevert")
    public DataResult<?> backrevert(@RequestParam(required = true) String businessId, @RequestParam(required = true) String taskId,
                                 @RequestParam(required = true) String opinion) {
        return DataResult.success(workFlowService.backrevert(businessId, taskId, opinion));
    }

    /**
     * 	合同审批历史
     */
    @GetMapping(value = "/opinion")
    public DataResult<?> opinion(@RequestParam(required = true) String businessId) {
        return DataResult.success(workFlowService.opinion(businessId));
    }

    /**
     * 	获取最新退回意见
     */
    @GetMapping(value = "/revertOpinion")
    public DataResult<?> revertOpinion(@RequestParam(required = true) String businessId) {
        return DataResult.success(workFlowService.revertOpinion(businessId));
    }

    /**
     * 	合同协同审批
     */
    @PostMapping(value = "/coordinate")
    public DataResult<?> coordinate(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId,
                                 @RequestParam(required = true) String opinion, @RequestParam(required = true) String coUserId,
                                 @RequestParam(required = true) Integer coordinateType, @RequestParam(required = true) String categoryCode) {
        return DataResult.success(workFlowService.coordinate(taskId, contractId, opinion, coUserId, coordinateType, categoryCode));
    }

    /**
     * 	合同待办列表
     */
    @GetMapping(value = "/taskToDo")
    public PagedList taskToDo(@RequestParam(required = false) String businessCodeOrName, @RequestParam(required = false) String workFlowId,
                              @RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                              @RequestParam(required = false) String isFrameContract, @RequestParam(required = true) Integer pageSize,
                              @RequestParam(required = true) Integer pageNum) {
        return workFlowService.taskToDo(businessCodeOrName, workFlowId, contractName, ruleserialNum, isFrameContract,
                pageSize, pageNum, false,0);
    }

    /**
     * 	合同待办列表(移动端）
     */
    @GetMapping(value = "/taskToDoMobile")
    public PagedList taskToDoMobile(@RequestParam(required = false) String businessCodeOrName, @RequestParam(required = false) String workFlowId,
                                    @RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                                    @RequestParam(required = false) String isFrameContract, @RequestParam(required = true) Integer pageSize,
                                    @RequestParam(required = true) Integer pageNum) {
        return workFlowService.taskToDo(businessCodeOrName, workFlowId, contractName, ruleserialNum, isFrameContract,
                pageSize, pageNum, true,0);
    }

    /**
     * 	合同已办列表
     */
    @GetMapping(value = "/taskDone")
    public PagedList taskDone(@RequestParam(required = false) String businessCodeOrName, @RequestParam(required = false) String workFlowId,
                              @RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                              @RequestParam(required = false) String isFrameContract,@RequestParam(required = false) String offereeName, @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return workFlowService.taskDone(businessCodeOrName, workFlowId, contractName, ruleserialNum, isFrameContract,offereeName,
                pageSize, pageNum);
    }

    /**
     * 	合同其他模块查询列表
     */
    @GetMapping(value = "/taskToDoOther")
    public PagedList taskDone(@RequestParam(required = false) String businessCodeOrName, @RequestParam(required = false) String workFlowId,
                              @RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                              @RequestParam(required = false) String isFrameContract, @RequestParam(required = true) String ext004,
                              @RequestParam(required = true) String ext020,@RequestParam(required = false) String ext010, 
                              @RequestParam(required = false) Integer operator,
                              @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return workFlowService.taskToDoOther(businessCodeOrName, workFlowId, contractName, ruleserialNum, isFrameContract,
                ext004, ext020, ext010, operator, pageSize, pageNum);
    }

    @ApiOperation(value = "/proxy" ,notes = "代理审批")
    @PostMapping(value = "/proxy")
    public DataResult<?> proxy(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId,
                            @RequestParam(required = true) String opinion, @RequestParam(required = true) String categoryCode) {
        return DataResult.success(workFlowService.proxy(taskId, contractId, opinion, categoryCode));
    }

    @ApiOperation(value = "/nextexecutor" ,notes = "后续审批人")
    @GetMapping(value = "/nextexecutor")
    public DataResult<?> nextexecutor(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId) {
        return DataResult.success(workFlowService.nextexecutor(taskId, contractId));
    }

    @ApiOperation(value = "/reactivate" ,notes = "分发")
    @PostMapping(value = "/reactivate")
    public DataResult<?> reactivate(@RequestBody Reactivate reactivate) {
        return DataResult.success(workFlowService.reactivate(reactivate));
    }

    @ApiOperation(value = "/taskparticipant" , notes = "分发参与者/当前活动参与者")
    @GetMapping(value = "/taskparticipant")
    public DataResult<?> taskparticipant(@RequestParam(required = true) String taskId) {
        return DataResult.success(workFlowService.taskparticipant(taskId));
    }

    /**
     * 	抄送
     */
    @PostMapping(value = "/CC")
    public DataResult<?> CC(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId, @RequestParam(required = true) String opinion, @RequestParam(required = true) String categoryCode) {
        return DataResult.success(workFlowService.cc(taskId, contractId, opinion, categoryCode));
    }

    /**
     * 	抄送完成
     */
    @PostMapping(value = "/cccomplete")
    public DataResult<?> cccomplete(@RequestParam(required = true) String taskId) {
        return DataResult.success(workFlowService.cccomplete(taskId));
    }

    /**
     * 	流程变更
     */
    @PostMapping(value = "/flowchange")
    public DataResult<?> flowchange(@RequestParam(required = true) String taskId) {
        return DataResult.success(workFlowService.flowchange(taskId));
    }

    /**
     * 	跳过
     */
    @PostMapping(value = "/skip")
    public DataResult<?> skip(@RequestParam(required = true) String taskId, @RequestParam(required = true) String contractId, @RequestParam(required = true) String opinion, @RequestParam(required = true) String categoryCode) {
        return DataResult.success(workFlowService.skip(taskId, contractId, opinion, categoryCode));
    }

    /**
     *	流程分发员
     */
    @GetMapping(value = "/flowChangeUser")
    public DataResult<?> flowChangeUser(@RequestParam(required = true) String businessId,
                                     @RequestParam(required = true) String categoryCode) {
        return workFlowService.flowChangeUser(categoryCode, businessId);
    }

    /**
     *	通过组织机构获取流程分发员
     */
    @GetMapping(value = "/getFlowChangeUser")
    public DataResult<?> getFlowChangeUser(@RequestParam(required = true) Integer deptId) {
        return workFlowService.flowChangeUser(deptId);
    }

    /**
     * 	临时消息关闭
     */
    @PostMapping(value = "/taskComplete")
    public DataResult<?> taskComplete(@RequestParam(required = true) String taskId) {
        return workFlowService.taskComplete(taskId);
    }


    @ApiOperation(value = "根据页面上的businessId,返回当前办理人")
    @ApiImplicitParam(name = "businessId" ,value = "对应合同ID")
    @GetMapping(value = "/getContractByBusinessId")
    public DataResult<?> getContractByBusinessId(@RequestParam(required = true) String businessId) {
        return workFlowService.getContractByBusinessId(businessId);
    }

    @ApiOperation(value = "插入临时消息")
    @PostMapping(value = "/saveTempMessage")
    public DataResult<Boolean> saveTempMessage(@RequestBody AppExtendsData appExtendsData, @RequestParam(required = true) String categoryCode,
                                      String userId) {
        SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(userId));
        return DataResult.success(workFlowService.saveTempMessage(appExtendsData, categoryCode, sysUserinfo));
    }

    @ApiOperation(value = "临时消息关闭(新)")
    @PostMapping(value = "/completeMessage")
    public DataResult<Boolean> completeMessage(@RequestParam(required = true) String taskId) {
        return DataResult.success(workFlowService.completeMessage(taskId));
    }
    
    @ApiOperation(value = "后续活动查询审批人")
    @GetMapping(value = "/queryNextApprover")
    public DataResult<?> queryNextActivityApprover(@RequestParam(required = true) String taskId,
    		@RequestParam(required = true) String categoryCode) {
        return DataResult.success(workFlowService.getNextActivityApprover(taskId,categoryCode));
    }
    /**
     * 	跳过
     */
    @ApiOperation(value = "后续活动查询审批人")
    @GetMapping(value = "/businessData/{businessId}")
    public  DataResult<?> businessData(@PathVariable String businessId) {
        return DataResult.success( workFlowService.businessData(businessId));
    }
}
