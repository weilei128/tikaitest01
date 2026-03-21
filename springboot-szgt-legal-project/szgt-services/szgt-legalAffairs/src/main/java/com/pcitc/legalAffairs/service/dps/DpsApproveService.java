package com.pcitc.legalAffairs.service.dps;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.dps.DpsQueryBo;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.dbService.task.ITaskInfoService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.task.TaskInfo;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;

/**
 * 工作流接口封装
 * @author HoLiX
 *
 */
@Service
public class DpsApproveService {

	@Autowired
	private ILitigateDisputeService disputeService;
	@Autowired
	private DpsManagerService dps;
	@Autowired
	private DpsHttpService dpsHttpService;
	@Autowired
	private DpsQueryService dpsQueryService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private ITaskInfoService taskInfoService;
	
	public AppCallResult approveDpsStart(FwLitigateDispute dispute, DpsCategoryEnum dpsCategory) {
		StartVo startVo = new StartVo();
		String businessId = dpsCategory.getCategoryCode() + "_" + dispute.getfId();
		String categoryCode = dpsCategory.getCategoryCode();
		startVo.setBusinessId(businessId);
		startVo.setCategoryCode(categoryCode);
		startVo.setBusinessName(dispute.getfName());
		startVo.setOrganiseId("1");
		startVo.setOwnKind("0");
		AppExtendsData extendsData = new AppExtendsData();
		extendsData.setBusinessId(businessId);
		extendsData.setExt001("fawu_dispute");
		startVo.setExtendsData(extendsData);
		Result start = dps.start(startVo);
		if (start.isSuccess()) {
			JSONObject data = (JSONObject) JSON.toJSON(start.getData());
			AppCallResult result = JSON.toJavaObject(data, AppCallResult.class);
			
			return result;
		}
		throw new BaseException(start.getMsg(), start.getCode());
	}
	
	public AppCallResult taskDpsStart(FwLitigateDispute dispute, DpsCategoryEnum dpsCategory) {
		StartVo executeContext = new StartVo();
		Integer userid = dispute.getfUserId();
		Map<String, Object> user = userOrgService.getUserInfo(userid.toString());
		SysUserInfo userInfo = JSON.toJavaObject((JSON) JSON.toJSON(user), SysUserInfo.class);
		String businessId = dpsCategory.getCategoryCode() + "_" + dispute.getfId();
		String categoryCode = dpsCategory.getCategoryCode();
		executeContext.setBusinessId(businessId);
		executeContext.setBusinessName(dispute.getfName());
		executeContext.setCategoryCode(categoryCode);
		executeContext.setExecutorId(userInfo.getfId().toString());
		executeContext.setExecutorName(userInfo.getfCname());
		AppExtendsData extendsData = new AppExtendsData();
		extendsData.setBusinessId(businessId);
		extendsData.setExt001("fawu_dispute");
		extendsData.setExt003(dispute.getfName());
		executeContext.setExtendsData(extendsData);
		Result start = dpsHttpService.taskMessage(executeContext);
		if (start.isSuccess()) {
			JSONObject data = (JSONObject) JSON.toJSON(start.getData());
			AppCallResult result = JSON.toJavaObject(data, AppCallResult.class);
			
			TaskInfo taskInfo = new TaskInfo();
			taskInfo.setTaskId(result.getId());
			taskInfo.setCategoryCode(categoryCode);
			taskInfo.setBusinessId(businessId);
			taskInfo.setPureId(dispute.getfId().toString());
			taskInfo.setfState(0);
			taskInfoService.save(taskInfo);
			
			return result;
		}
		throw new BaseException(start.getMsg(), start.getCode());
	}
	
	public AppCallResult approveDpsComplete(ExecuteContext entity) {
		String businessId = entity.getBusinessId();
		String categoryCode = businessId.substring(0, businessId.lastIndexOf("_"));
		Result result = dpsHttpService.complete(entity);
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		Object data = result.getData();
		String taskId = entity.getTaskId();
		if (DpsCategoryEnum.DpsCategory_Cdn.getCategoryCode().equals(categoryCode)) {
			return startReport(taskId, businessId);
		}
		if (DpsCategoryEnum.DpsCategory_Dispute.getCategoryCode().equals(categoryCode)) {
			return startProgress(taskId, businessId);
		}
		return null;
	}
	
	public Result<?> taskMessageComplete(String businessId) {
		List<String> toDoTask = dpsQueryService.getTaskMessageByBusinessId(businessId);
		if (toDoTask != null && toDoTask.size() > 0) {
			toDoTask.forEach(e -> {
				dpsHttpService.taskMessageComplete(e);
			});
			taskInfoService.lambdaUpdate()
				.in(TaskInfo::getTaskId, toDoTask)
				.set(TaskInfo::getfState, 1)
				.update();
		}
		return Result.status(true);
	}
	
	/**
	 * 开始纠纷填报流程
	 */
	private AppCallResult startReport(String taskId, String businessId) {
		System.out.println("纠纷填报流程判定");
		if (!isCompleted(taskId, businessId)) {
			return null;
		}
		System.out.println("纠纷填报流程");
		Long pureId = Long.parseLong(Utils.getHandlerStr(businessId));
		FwLitigateDispute dispute = disputeService.getById(pureId);
		System.out.println("流程开始");
		AppCallResult result = approveDpsStart(dispute, DpsCategoryEnum.DpsCategory_Report);
		return result;
	}
	
	private boolean isCompleted(String taskId, String businessId) {
		DpsQueryBo query = new DpsQueryBo();
		query.setBusinessId(businessId);
		query.setTaskId(taskId);
		List<?> nextExecutor = dpsQueryService.postNextExecutor(query);
		// 没有下一经办人, 则认为审批已经结束
		return (nextExecutor == null || nextExecutor.size() == 0);
	}
	
	private AppCallResult startProgress(String taskId, String businessId) {
		System.out.println("纠纷办理流程判定");
		if (!isCompleted(taskId, businessId)) {
			return null;
		}
		System.out.println("纠纷办理流程");
		Long pureId = Long.parseLong(Utils.getHandlerStr(businessId));
		FwLitigateDispute dispute = disputeService.getById(pureId);
		System.out.println("流程开始");
		AppCallResult result = taskDpsStart(dispute, DpsCategoryEnum.DpsCategory_Settle);
		return result;
	}
}
