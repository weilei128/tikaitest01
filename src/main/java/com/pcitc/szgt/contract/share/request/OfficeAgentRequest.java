package com.pcitc.szgt.contract.share.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pcitc.szgt.contract.make.service.IMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.constant.Constants;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.make.entity.CrOfficeAgents;
import com.pcitc.szgt.contract.make.entity.CrOfficeAgentsList;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.TaskQueryModel;
import com.pcitc.szgt.contract.share.model.UserRole;
import com.pcitc.szgt.contract.share.model.UserRoleList;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.workflow.service.IWorkFlowService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OfficeAgentRequest {

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ShareConfig shareConfig;
    @Autowired
    private UserInfoRequest userInfoRequest ;
    @Autowired
    private IMakeService makeService;
    /**
     * 	工作流
     */
    @Autowired
    private IWorkFlowService workFlowService;
    /**
     *	办公代理添加
     * @param taskId
     * @return
     */
    public DataResult<?> createOfficeAgents(CrOfficeAgents agents){
    	String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/officeAgent", agents,null);
        try {
            DataResult<?> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<?>>(){});
            return dataResult;
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }
    
    /**
     *	办公代理列表
     * @param taskId
     * @return
     */
    public CrOfficeAgentsList queryOfficeAgentsList(Map<String,Object> mapparam){

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/officeAgent?page={page}&size={size}&operationId={operationId}", mapparam);
        try {
            DataResult<CrOfficeAgentsList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<CrOfficeAgentsList>>(){});
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }
    
    /**
     *	办公代理状态修改
     * @param taskId
     * @return
     */
    public boolean updateAgentStatusByAgentId(Integer agentId,Integer status){
        Map<String, Object> params = new HashMap<>();
        params.put("id", agentId);
        params.put("agentStatus", status);
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/officeAgent", params, null);
        try {
            DataResult<Object> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>(){});
            return dataResult.isSuccess();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }
    /**
     * 	合同ID
     * @param contractID
     */
    public void sendMessageForTransactor(String contractID,String section) {
        DataResult<?> result = workFlowService.getContractByBusinessId(contractID);

        DataResult<?> crcontractinfo=makeService.getContractById(contractID);//客户要求修改了合同模板
        String Msg=Constants.SEND_MESSAGE_AGENT;
        String mainDeptName="";
        if(crcontractinfo.getData()!=null){
            JSONObject jsonObject = (JSONObject) crcontractinfo.getData() ;
            if(jsonObject.get("mainDeptName").toString().endsWith("部")||jsonObject.get("mainDeptName").toString().equals("")){
                mainDeptName=jsonObject.get("mainDeptName").toString();
            }else{
                mainDeptName= jsonObject.get("mainDeptName").toString()+"部";
            }
            List<Integer> filterUserId = new ArrayList<>();
            filterUserId.add(Integer.valueOf(jsonObject.get("mainUserId")+""));
            List<SysUserinfo> sysUserInfo = userInfoRequest.queryByIdBatch(filterUserId.toArray(new Integer[0]));

            Msg=Constants.SEND_MESSAGE+"《"+jsonObject.get("contractName")+"》，发起人："+mainDeptName+ sysUserInfo.get(0).getfCname();
        }
log.info("根据合同查询当前办理人："+result.getData());
        if(result.getData()!=null) {
        	List<Integer> userIdList = new ArrayList<>();
            JSONObject json = (JSONObject) result.getData() ;
            if(!json.isEmpty()) {
            	userIdList.add(Integer.parseInt((String) json.get("currentUserId")));   
            }
            sendMsg(userIdList,Msg,section);
        }
    }
    /**
     * 	手机短信发送接口
     * @param  phones 电话号码集合
     * @param  msg    短信文案
     * @param  batchName 信息签名
     */
    public DataResult<?> sendMsg(List<Integer> userIdList,String msg,String batchName) {
    	List<Integer> filterUserId = new ArrayList<>();
    	for(Integer userId : userIdList) {
    		UserRoleList role = userInfoRequest.queryUserRoleByUserId(userId);
    		if(role!=null) {
	    		List<UserRole> roles = role.getUserSelectRoleInfoList().stream()
	    				.filter(i->i.getRoleCode().equals("sendCode"))
	    				.collect(Collectors.toList());
	    	    if(roles !=null && roles.size()>0) {
	    	    	filterUserId.add(userId);
	    	    }
    		}
    	}
    	log.info("当前用户角色包含短信的用户："+filterUserId);
    	DataResult<Object> dataResult = null ;
    	if(filterUserId!=null && filterUserId.size()>0) {
	        List<SysUserinfo> sysUserInfo = userInfoRequest.queryByIdBatch(filterUserId.toArray(new Integer[0]));
	        List<String> phoneList = sysUserInfo.stream().filter(i->!Strings.isNullOrEmpty(i.getfPhoneNum()))
	        		.map(SysUserinfo::getfPhoneNum).collect(Collectors.toList());
            log.info("当前用户手机号："+phoneList);
	        Map<String, Object> params = new HashMap<>();
	        params.put("phoneList", phoneList);
	        params.put("msg", msg);
	        params.put("batchName", batchName);
	        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/sms/send", params, null);
	        log.info("---短信发送回调数据={}",result);
	        try {
	           dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>(){});
	        } catch (IOException e) {
	            StringWriter trace = new StringWriter();
	            e.printStackTrace(new PrintWriter(trace));
	            log.error(trace.toString());
	            throw new BaseException("对象解析失败", 500);
	        }
    	}
    	return dataResult;
    }
}
