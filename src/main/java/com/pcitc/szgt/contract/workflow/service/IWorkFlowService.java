package com.pcitc.szgt.contract.workflow.service;

import com.alibaba.fastjson.JSONObject;
import com.pcitc.ssc.dps.inte.workflow.*;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.entity.WfMessage;
import com.pcitc.szgt.contract.make.modelEx.NextApproverVo;
import com.pcitc.szgt.contract.make.modelEx.UserInfoListVo;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.DpsCallbackVo;
import com.pcitc.szgt.contract.workflow.entityExt.ApproveEx;
import com.pcitc.szgt.contract.workflow.entityExt.Executor;
import com.pcitc.szgt.contract.workflow.entityExt.Reactivate;
import com.pcitc.szgt.contract.workflow.entityExt.TaskOpinion;

import java.util.List;

/**
 * <p>工作流服务</p>
 * @author ziranzhou
 * @since 2020-02-25
 */
public interface IWorkFlowService {
	/**
	 * 设置工作流扩展字段
	 */
	AppExtendsData setExtendsData(String businessId, CrContractbasic crContractbasic, CrContractinfo crContractinfo,
			String moduleId, String moduleName, String section);

	/**
	 * 合同订立审批结束
	 */
	boolean makeApprove(String contractId);

	/**
	 * 合同变更审批结束
	 */
	boolean changeApprove(String changeContractId);

	/**
	 * 合同转让变更审批结束
	 */
	boolean transApprove(String transId);

	/**
	 *	合同终止审批结束
	 */
	boolean endApprove(String endId);

	/**
	 *	合同终结审批结束
	 */
	boolean treatmentApprove(String endId);

	/**
	 * 审批通过
	 * @param taskId  任务ID
	 * @param contractId  合同ID
	 * @param opinion  
	 * @param categoryCode  分类CODE
	 * @param variableList
	 * @return
	 */
	AppCallResult complete(String taskId, String contractId, String opinion,String categoryCode,String variableList);
	
    /**
     * 	后续活动查询审批人(流程发起前可以查询下一级审批人)
     */
	UserInfoListVo getNextActivityApprover(String taskId,String categoryCode) ;

	/**
	 * 	审批退回
	 */
	AppCallResult revert(ApproveEx approveEx);

	/**
	 * 	审批退回至退回人
	 */
	AppCallResult backrevert(String businessId, String taskId, String opinion);

	/**
	 * 	合同审批历史
	 */
	List<TaskOpinion> opinion(String businessId);

	/**
	 * 	获取退回意见
	 */
	TaskOpinion revertOpinion(String businessId);

	/**
	 *	协同审批
	 */
	AppCallResult coordinate(String taskId, String contractId, String opinion, String coUserId, Integer coordinateType,
			String categoryCode);

	/**
	 * 	个人助理待办列表
	 */
	PagedList taskToDo(String businessCodeOrName, String workFlowId, String contractName, String ruleserialNum,
			String isFrameContract, Integer pageSize, Integer pageNum, boolean isMobile,Integer userId);

	/**
	 * 	合同其他模块查询列表
	 */
	PagedList taskToDoOther(String businessCodeOrName, String workFlowId, String contractName, String ruleserialNum,
			String isFrameContract, String ext004, String ext020, String ext010, Integer operrator, Integer pageSize,
			Integer pageNum);

	/**
	 * 	合同已办列表
	 */
	PagedList taskDone(String businessCodeOrName, String workFlowId, String contractName, String ruleserialNum,
			String isFrameContract,String offereeName, Integer pageSize, Integer pageNum);

	/**
	 *	代理审批
	 */
	AppCallResult proxy(String taskId, String contractId, String opinion, String categoryCode);

	/**
	 *	后续审批人
	 */
	List<Executor> nextexecutor(String taskId, String contractId);

	/**
	 * 	分发
	 */
	AppCallResult reactivate(Reactivate reactivate);

	/**
	 * 	分发参与者/当前活动参与者
	 */
	List<AppParticipantData> taskparticipant(String taskId);

	/**
	 * 	抄送
	 */
	AppCallResult cc(String taskId, String contractId, String opinion, String categoryCode);

	/**
	 * 	抄送完成
	 */
	AppCallResult cccomplete(String taskId);

	/**
	 *	流程变更
	 */
	AppCallResult flowchange(String taskId);

	/**
	 *	审批跳过
	 */
	AppCallResult skip(String taskId, String contractId, String opinion, String categoryCode);

	/*
	 * 流程分发员
	 */
	DataResult<?> flowChangeUser(String categoryCode, String businessId);

	/*
	 * 根据部门获取流程分发员
	 */
	DataResult<?> flowChangeUser(Integer deptId);

	/*
	 * 任务完结
	 */
	DataResult<?> taskComplete(String taskId);

	/**
	 * 	根据合同ID,返回当前办理人
	 */
	DataResult<?> getContractByBusinessId(String businessId);

	/*
	 * 插入消息表
	 */
	boolean addMessage(WfMessage wfMessage, AppExtendsData appExtendsData, DpsCallbackVo dpsCallbackVo);

	/**
	 * 	插入临时消息
	 */
	boolean saveTempMessage(AppExtendsData appExtendsData, String categoryCode, SysUserinfo executor);

	/**
	 * 	插入临时消息 
	 */
	boolean saveTempMessageNew(AppExtendsData appExtendsData, String categoryCode, SysUserinfo executor, String taskId,
			Integer executeResult, String opinion);

	/*
	 * 临时消息处理完毕
	 */
	boolean completeMessage(String taskId);

	JSONObject businessData(String businessId);


	List<AppMetasData> setAppMetasData(List<AppMetasData> metas, CrContractbasic crContractbasic, CrContractinfo crContractinfo, Integer activeType, List<Integer> userId, String routeType);
}
