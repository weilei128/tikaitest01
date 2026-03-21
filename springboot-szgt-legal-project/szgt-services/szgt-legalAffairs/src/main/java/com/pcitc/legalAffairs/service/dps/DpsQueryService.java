package com.pcitc.legalAffairs.service.dps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.dps.DpsNextBo;
import com.pcitc.legalAffairs.bo.dps.DpsQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireInfoService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeInfoService;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.dbService.task.ITaskInfoService;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.po.task.TaskInfo;
import com.pcitc.legalAffairs.service.dps.config.WfConfig;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;

@Service
public class DpsQueryService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WfConfig wfConfig;
	@Autowired
	private DpsHttpService dpsHttpService;
	@Autowired
	private ILitigateDisputeService idisputeService;
	@Autowired
	private IFwIntermediaryOrgBasicService iintermediaryService;
    @Autowired
    private IFwIntermediaryHireInfoService ihireService;
    @Autowired
    private IAuthorizeInfoService iauthorizeService;
    @Autowired
    private UserOrgService userOrgService;
    @Autowired
    private ITaskInfoService taskInfoService;


	public List<DpsNextBo> getNextExecutor(DpsQueryBo bo) {
		List nextExecutor = postNextExecutor(bo);
		List<DpsNextBo> exec = new ArrayList();
		// 没有待办人 取填报人
		if (nextExecutor == null || nextExecutor.size() == 0) {
			String businessId = bo.getBusinessId();
			String id = Utils.getHandlerStr(businessId);
			String userId = null;
			// 诉前争议
			if (businessId.startsWith(DpsCategoryEnum.DpsCategory_Cdn.getCategoryCode())) {
				FwLitigateDispute oo = idisputeService.getById(id);
				userId = oo.getfUserId().toString();
			// 纠纷填报
			} else if (businessId.startsWith(DpsCategoryEnum.DpsCategory_Dispute.getCategoryCode())) {
				FwLitigateDispute oo = idisputeService.getById(id);
				userId = oo.getfUserId().toString();
			// 中介机构准入
			} else if (businessId.startsWith(DpsCategoryEnum.DpsCategory_Iaa.getCategoryCode())) {
				FwIntermediaryOrgBasic oo = iintermediaryService.getById(id);
				userId = oo.getfUserId().toString();
			// 中介机构聘用
			} else if (businessId.startsWith(DpsCategoryEnum.DpsCategory_Iae.getCategoryCode())) {
				FwIntermediaryHireInfo oo = ihireService.getById(id);
				userId = oo.getfUserId().toString();
			// 事项授权申请
			} else if (businessId.startsWith(DpsCategoryEnum.DpsCategory_Map.getCategoryCode())) {
				FwAuthorizeInfo oo = iauthorizeService.getById(id);
				userId = oo.getfUserId().toString();
			}
			DpsNextBo next = new DpsNextBo();
			next.setUserId(userId);
			exec.add(next);
		// 有待办人 取待办人
		} else {
			for (Object each: nextExecutor) {
				Map<String, Object> e = (Map<String, Object>) each;
				DpsNextBo next = new DpsNextBo();
				next.setUserId(e.get("userId").toString());
				exec.add(next);
			}
		}
		
		for (DpsNextBo e: exec) {
			String userId = e.getUserId();
			List<Long> orgIds = userOrgService.getUserOrg(userId);
			Map<String, Object> userInfo = userOrgService.getUserInfo(userId);
			e.setUserName(userInfo.get("fCname") == null? null: userInfo.get("fCname").toString());
			e.setPosition(userInfo.get("fPosition") == null? null: userInfo.get("fPosition").toString());
			
			List<String> orgNames = new ArrayList<>();
			if (orgIds != null) {
				for (Long orgId: orgIds) {
					Map<String, Object> orgInfo = userOrgService.getOrgInfo(orgId.toString());
					orgNames.add(orgInfo.get("fName") == null? null: orgInfo.get("fName").toString());
				}
			}
			e.setOrgName(orgNames);
		}
		return exec;
	}
	
	public List postNextExecutor(DpsQueryBo bo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setAuHeader(true, headers);
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("taskId", bo.getTaskId());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodyParams, headers);
        String url = wfConfig.getUrl() + "/dps/nextexecutor";
        ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
        return (List) response.getBody().getData();

	}

	public List<ExecuteTaskData> getTodoTaskByBusinessId(String businessId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		setAuHeader(true, headers);
		MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodyParams, headers);
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("businessId", businessId);
		String url = wfConfig.getUrl() + "/dps/todotaskByBusinessId/{businessId}";
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.GET, entity, Result.class, pathVariables);
		Map<String, Object> map = (Map<String, Object>) response.getBody().getData();
		
		List list = (List) map.get("executeTaskList");
		
		if (list == null) {
			return null;
		}
		List<ExecuteTaskData> collect = (List<ExecuteTaskData>) list.stream().map(e -> {
			ExecuteTaskData executeTaskData = JSON.toJavaObject((JSON) JSON.toJSON(e), ExecuteTaskData.class);
			return executeTaskData;
		}).collect(Collectors.toList());
		return collect;
	}

	
	public List<String> getTaskMessageByBusinessId(String businessId) {
		List<TaskInfo> list = taskInfoService.lambdaQuery().eq(TaskInfo::getBusinessId, businessId)
				.eq(TaskInfo::getfState, 0)
				.list();
		if (list == null) {
			return null;
		}
		return list.stream().map(TaskInfo::getTaskId).collect(Collectors.toList());
	}
	
	private void setAuHeader(boolean isAuthorization, HttpHeaders headers) {
		if (isAuthorization) {
			if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
				headers.set("Authorization", dpsHttpService.getAuthorization());
			}
		}
	}

}