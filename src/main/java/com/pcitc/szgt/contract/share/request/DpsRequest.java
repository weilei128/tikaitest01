package com.pcitc.szgt.contract.share.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.ssc.dps.inte.workflow.*;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.make.modelEx.NextApproverVo;
import com.pcitc.szgt.contract.share.model.DpsTaskMessage;
import com.pcitc.szgt.contract.share.model.TaskCountModel;
import com.pcitc.szgt.contract.share.model.TaskCountQueryModel;
import com.pcitc.szgt.contract.share.model.TaskQueryModel;
import com.pcitc.szgt.contract.share.service.IOaTaskService;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DpsRequest {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShareConfig shareConfig;

    @Autowired
    private IOaTaskService oaTask;

    @Value("${DPS.AppId}")
    private String appId;

    /**
     * 模板查询
     *
     * @param categoryCode 流程分类编码
     * @param ownKind      模板类型（0：机构， 1：公共）
     * @param organiseId   组织机构Id,如果模板类型是”0”需传值
     */
    public List<AppWorkflowData> workflow(String categoryCode, Integer ownKind, String organiseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryCode", categoryCode);
        params.put("ownKind", ownKind);
        params.put("organiseId", organiseId);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dps/workflow", params);

        try {
            DataResult<List<AppWorkflowData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppWorkflowData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 路由条件查询
     */
    public List<AppMetasData> metas(String categoryCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryCode", categoryCode);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dps/metas", params);

        try {
            DataResult<List<AppMetasData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppMetasData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 流程发起
     *
     * @param startContext
     */
    public AppCallResult oristart(StartContext startContext) {
        try {
            String s = objectMapper.writeValueAsString(startContext);
            log.info("ori workflow start--------------------");
            log.info(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("http://" + shareConfig + "/dps/start");
        log.info("ori workflow end--------------------");

        String result = restTemplateUtil.postJsonRequest(
                "http://222.84.252.143:23000/workflowrest/dps-service/init/start", startContext, null);
        AppCallResult appCallResult = null;

        log.info("ori request finish");
        log.info(result);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 流程发起
     *
     * @param startContext
     */
    public AppCallResult start(StartContext startContext) {
        try {
            String s = objectMapper.writeValueAsString(startContext);
            log.info("workflow start--------------------");
            log.info(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("http://" + shareConfig + "/dps/start");
        log.info("workflow end--------------------");

        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/dps/start", startContext, null);
        AppCallResult appCallResult = null;

        log.info("request finish");
        log.info(result);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 审批通过
     *
     * @param executeContext
     * @return
     */
    public AppCallResult complete(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/complete", executeContext, null);

        AppCallResult appCallResult = null;

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 审批退回
     *
     * @param executeContext
     * @return
     */
    public AppCallResult revert(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/dps/revert", executeContext, null);

        AppCallResult appCallResult = null;

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 审批历史
     *
     * @return
     */
    public List<AppTaskOpinionData> opinion(String businessId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        // String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/opinion?businessId={businessId}", params);
        //update  byzhouziran 20200722
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/opinion?businessId={businessId}&isIncludeStart=0", params);
        long endTime = System.currentTimeMillis();
        log.info("-----审批历史请求系统接口时间= {} ms", (endTime - startTime));
        try {
            DataResult<List<AppTaskOpinionData>> dataResult =
                    objectMapper.readValue(result, new TypeReference<DataResult<List<AppTaskOpinionData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 业务废弃
     *
     * @param businessId
     * @param flowOrganiseId
     */
    public AppCallResult businessdiscard(String businessId, String flowOrganiseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        params.put("flowOrganiseId", flowOrganiseId);
//        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/businessdiscard/{businessId}", params, null);
        String result = restTemplateUtil.deleteJsonRequest("http://" + shareConfig + "/dps/businessdiscard/" + businessId, params, null);
        try {
            DataResult<AppCallResult> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<AppCallResult>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 协同审查
     */
    public AppCallResult coordinate(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/coordinate", executeContext, null);
        try {
            DataResult<AppCallResult> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<AppCallResult>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 待办列表
     *
     * @return
     */
    public PagedList pagedtodotask(String userId, Integer pageIndex, Integer pageSize, String businessCodeOrName,
                                   String categoryCode, Map<String, Object> extMap) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("current", pageIndex);
        params.put("size", pageSize);
     /* Map<String, Object> extMap = new HashMap<>();
        extMap.put("ext001", "contract");*/
        params.put("extMap", extMap);

        if (!StringUtils.isEmpty(businessCodeOrName)) {
            params.put("businessCodeOrName", businessCodeOrName);
        }
        if (!StringUtils.isEmpty(categoryCode)) {
            params.put("categoryCode", categoryCode);
        }
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/pagedtodotask", params, null);
        try {
            DataResult<PagedList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<PagedList>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 已办列表
     *
     * @return
     */
    public PagedList pageddonetask(String userId, Integer pageIndex, Integer pageSize, String businessCodeOrName, String categoryCode, Map<String, Object> extMap) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("current", pageIndex);
        params.put("size", pageSize);
      /*  Map<String, Object> extMap = new HashMap<>();
        extMap.put("ext001", "contract");*/
        params.put("extMap", extMap);

        if (!StringUtils.isEmpty(businessCodeOrName)) {
            params.put("businessCodeOrName", businessCodeOrName);
        }

        if (!StringUtils.isEmpty(categoryCode)) {
            params.put("categoryCode", categoryCode);
        }
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/pageddonetask", params, null);
        try {
            DataResult<PagedList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<PagedList>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 批量审批
     *
     * @return
     */
    public AppCallResult completebatch(List<ExecuteContext> executeContextList) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/completebatch", executeContextList, null);

        AppCallResult appCallResult = null;
        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 代理审批
     *
     * @param executeContext
     * @return
     */
    public AppCallResult proxy(ExecuteContext executeContext) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/proxy", executeContext, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求代理审批接口=总时间 {} ms,参数:executeContext={}", endTime - startTime, JSONObject.toJSON(executeContext));
        AppCallResult appCallResult = null;
        try {
            DataResult<AppCallResult> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<AppCallResult>>() {
            });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 撤销(由应用系统调用，对已经审批通过的待办进行撤销操作)
     *
     * @return
     */
    public AppCallResult withdraw(String taskId) {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("taskId", taskId);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dps/withdraw", bodyParams, null);

        AppCallResult appCallResult = null;

        try {
            DataResult<AppCallResult> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<AppCallResult>>() {
            });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 退回返回接口(相当于这个流程回调退回之前的状态)
     *
     * @return
     */
    public AppCallResult backrevert(ExecuteContext executeContext) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/backrevert", executeContext, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求退回返回接口(相当于这个流程回调退回之前的状态)接口=总时间 {} ms,参数:taskId={}", endTime - startTime, JSONObject.toJSON(executeContext));
        AppCallResult appCallResult = null;

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 后续审批人
     *
     * @param taskId
     * @return
     */
    public List<ExecutorData> nextexecutor(String taskId) {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("taskId", taskId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dps/nextexecutor", bodyParams, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求后续审批人接口=总时间 {} ms,参数:taskId={}", endTime - startTime, taskId);
        try {
            DataResult<List<ExecutorData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<ExecutorData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 流程分类
     *
     * @return
     */
    public List<AppFlowCategory> flowcategory() {
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/flowcategory", null);

        try {
            DataResult<List<AppFlowCategory>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppFlowCategory>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 待办委托
     *
     * @param executorId 待办所属用户Id
     * @param agentId    委托用户Id
     * @return
     */
    public AppCallResult entrust(String executorId, String agentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("executorId", executorId);
        params.put("agentId", agentId);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/entrust/{executorId}/{agentId}", params);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 退回活动选择列表
     *
     * @param taskId
     * @return
     */
    public List<AppSimpleData> revertselect(String taskId) {

        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("taskId", taskId);
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/revertselect{taskId}", uriParams);

        try {
            DataResult<List<AppSimpleData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppSimpleData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 退回选择
     *
     * @param executeContext
     * @return
     */
    public AppCallResult revertselect(ExecuteContext executeContext) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/revertselect", executeContext, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求退回选择接口=总时间 {} ms,参数:ExecuteContext={}", endTime - startTime, JSONObject.toJSON(executeContext));
        AppCallResult appCallResult = null;
        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            appCallResult = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        return appCallResult;
    }

    /**
     * 催办
     *
     * @param content 内容描述
     * @param idList  待办id列表
     * @return
     */
    public AppCallResult remind(String content, List<String> idList) {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("content", content);
        bodyParams.put("idList", idList);

        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/remind", bodyParams, null);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 催办待办
     *
     * @param businessId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PagedList remindtask(String businessId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("businessId", businessId);
        uriParams.put("pageIndex", pageIndex);
        uriParams.put("pageSize", pageSize);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/remindtask?" +
                "businessId={businessId}&pageIndex={pageIndex}&pageSize={pageSize}", uriParams);

        try {
            DataResult<PagedList> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<PagedList>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 分发
     *
     * @param executeContext
     * @return
     */
    public AppCallResult reactivate(ExecuteContext executeContext) {
        long startTime = System.currentTimeMillis();
        log.info("------分发请求参数=={}", JSON.toJSONString(executeContext));
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/reactivate", executeContext, null);
        log.info("------请求分发接口返回参数=={}", result);
        long endTime = System.currentTimeMillis();
        log.info("------请求分发接口=总时间 {} ms,参数:taskId={}", endTime - startTime);
        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 分发参与者/当前活动参与者
     *
     * @param taskId
     * @return
     */
    public List<AppParticipantData> taskparticipant(String taskId) {

        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("taskId", taskId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/taskparticipant/{taskId}", null, uriParams);
        long endTime = System.currentTimeMillis();
        log.info("------请求分发参与者/当前活动参与者接口=总时间 {} ms,参数:taskId={}", endTime - startTime, taskId);
        try {
            DataResult<List<AppParticipantData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppParticipantData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 活动参与者
     *
     * @param taskId
     * @param activityId
     * @return
     */
    public List<AppParticipantData> activityparticipant(String taskId, String activityId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("taskId", taskId);
        uriParams.put("activityId", activityId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/activityparticipant/{taskId}/{activityId}", null, uriParams);
        long endTime = System.currentTimeMillis();
        log.info("------请求活动参与者接口=总时间 {} ms,参数:taskId={}==activityId={}", endTime - startTime, taskId, activityId);
        try {
            DataResult<List<AppParticipantData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppParticipantData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 业务流程字段
     *
     * @param businessId
     * @return
     */
    public List<AppMetasData> metasByBusinessId(String businessId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("businessId", businessId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/metasByBusinessId/{businessId}", null, uriParams);
        long endTime = System.currentTimeMillis();
        log.info("------请求业务流程字段接口=总时间 {} ms,参数:businessId={}", endTime - startTime, businessId);
        try {
            DataResult<List<AppMetasData>> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<List<AppMetasData>>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 抄送完成
     *
     * @param taskId
     * @return
     */
    public AppCallResult cccomplete(String taskId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("taskId", taskId);
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/cccomplete/{taskId}", null, uriParams);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 抄送
     *
     * @param executeContext
     * @return
     */
    public AppCallResult cc(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/cc", executeContext, null);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 跳出完成
     *
     * @param executeContext
     * @return
     */
    public AppCallResult exitcomplete(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/exitcomplete", executeContext, null);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 流程变更
     *
     * @param taskId
     * @return
     */
    public AppCallResult flowchange(String taskId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("taskId", taskId);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/flowchange/{taskId}", uriParams);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 跳过
     *
     * @param executeContext
     * @return
     */
    public AppCallResult skip(ExecuteContext executeContext) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/skip", executeContext, null);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 选择完成
     *
     * @param executeContext
     * @return
     */
    public AppCallResult selectcomplete(ExecuteContext executeContext) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/selectcomplete", executeContext, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求选择完成接口=总时间 {} ms,参数:ExecuteContext={}", endTime - startTime, JSON.toJSONString(executeContext));
        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 单点登录
     *
     * @param userId
     * @return
     */
    public String sso(String userId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("appId", appId);
        uriParams.put("userId", userId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/ssot/token?appId={appId}&userId={userId}", uriParams);
        long endTime = System.currentTimeMillis();
        log.info("------请求 单点登录接口=总时间 {} ms,参数:userId={}", endTime - startTime, userId);
        try {
            DataResult<String> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<String>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }


    //####### 定制待办接口

    /**
     * 插入待办消息
     *
     * @param taskMessage
     * @return
     */
    public AppCallResult taskMessage(DpsTaskMessage taskMessage) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/taskMessage", taskMessage, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求插入待办消息接口=总时间 {} ms", endTime - startTime);
        try {
            DataResult<AppCallResult> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<AppCallResult>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 消息待办完成
     *
     * @return
     */
    public AppCallResult taskMessageComplete(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/taskMessageComplete/{taskId}", params);
        long endTime = System.currentTimeMillis();
        log.info("------请求消息待办完成接口=总时间 {} ms==参数:taskId={}", endTime - startTime, taskId);
        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 待办分页获取
     *
     * @param todoTaskQueryModel
     * @return
     */
    public PagedList pagedtodotask(TaskQueryModel todoTaskQueryModel) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/pagedtodotaskByCategoryCode", todoTaskQueryModel, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求待办分页获取接口=总时间 {} ms", endTime - startTime);
        try {
            DataResult<PagedList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<PagedList>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 已办分页获取
     *
     * @param todoTaskQueryModel
     * @return
     */
    public PagedList pageddonetask(TaskQueryModel todoTaskQueryModel) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/pageddonetaskByCategoryCode", todoTaskQueryModel, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求已办分页获取接口=总时间 {} ms", endTime - startTime);
        try {
            DataResult<PagedList> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<PagedList>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 待办数量统计
     *
     * @return
     */
    public TaskCountModel todoTaskCount(TaskCountQueryModel taskCountQueryModel) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/todotaskCountByCategoryCode", taskCountQueryModel, null);
        long endTime = System.currentTimeMillis();
        log.info("------请求待办数量统计取接口=总时间 {} ms", endTime - startTime);
        try {
            DataResult<TaskCountModel> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<TaskCountModel>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 已办数量统计
     *
     * @param taskCountQueryModel
     * @return
     */
    public TaskCountModel doneTaskCount(TaskCountQueryModel taskCountQueryModel) {
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/donetaskCountByCategoryCode", taskCountQueryModel, null);

        try {
            DataResult<TaskCountModel> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<TaskCountModel>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 根据业务ID查询待办列表
     *
     * @return
     */
    public PagedList todotaskByBusinessId(String businessId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/todotaskByBusinessId/{businessId}", params);

        try {
            DataResult<PagedList> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<PagedList>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * 待办转移
     *
     * @param taskList     待办id列表
     * @param transferId   指定用户的id
     * @param transferName 指定用户名称
     * @return
     */
    public AppCallResult transferByTask(List<String> taskList, String transferId, String transferName) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskList", taskList);
        params.put("transferId", transferId);
        params.put("transferName", transferName);
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/transferByTask", params, null);

        try {
            DataResult<AppCallResult> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<AppCallResult>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 待办详情
     *
     * @param taskId
     * @return
     */
    public ExecuteTaskData taskdetail(String taskId) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/taskdetail?taskId=" + taskId, null);
        try {
            DataResult<ExecuteTaskData> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<ExecuteTaskData>>() {
                    });
            long endTime = System.currentTimeMillis();
            log.info("-----获取待办详情总时间= {} ms,参数taskId={}", (endTime - startTime), taskId);
            return dataResult.getData();
        } catch (IOException e) {
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * oa待办变已办
     *
     * @param taskId
     * @return
     */
    public boolean oaTaskDone(String taskId) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/oatask/oaTaskDone", params);
        long endTime = System.currentTimeMillis();
        log.info("-----oa待办变已办--请求/oatask/oaTaskDone总时间= {} ms,参数taskId={}", (endTime - startTime), taskId);
        try {
            DataResult<Object> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>() {
            });
            return dataResult.isSuccess();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 删除oa待办
     *
     * @param taskId
     * @return
     */
    public boolean oaTaskDel(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/oatask/oaTaskDel", params);
        try {
            DataResult<Object> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>() {
            });
            return dataResult.isSuccess();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 发送oa待办
     *
     * @param taskId
     * @return
     */
    public boolean sendOaTask(String taskId) {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("taskId", taskId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/oatask/sendOaTask", bodyParams);
        long endTime = System.currentTimeMillis();
        log.info("-----发送oa待办--请求/oatask/sendOaTask总时间= {} ms,参数taskId={}", (endTime - startTime), taskId);
        try {
            DataResult<Object> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>() {
            });
            return dataResult.isSuccess();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

    }

    /**
     * oa待办重发
     *
     * @param taskId
     * @return
     */
    public boolean resendOaTask(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/oatask/resendOaTask", params);
        log.info("----oa待办重发==={}", result);
        try {
            DataResult<Object> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<Object>>() {
            });
            return dataResult.isSuccess();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 后续活动查询审批人(流程发起前可以查询下一级审批人)
     *
     * @param
     * @return
     */
    public List<NextApproverVo> nextActivityApprover(ActivityApproveUserData approver) {
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.postJsonRequest("http://" + shareConfig + "/dps/nextactivityOfMetas/2", approver, null);
        long endTime = System.currentTimeMillis();
        log.info("-----后续活动查询审批人(流程发起前可以查询下一级审批人)--请求/dps/nextactivityOfMetas/2总时间= {} ms,参数ActivityApproveUserData={}", (endTime - startTime), approver.toString());
        try {
            DataResult<List<NextApproverVo>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<NextApproverVo>>>() {
            });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    public boolean deleteOaTask(String taskId) {
        try {
            int count = oaTask.deleteOaTask(taskId);
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    public JSONObject businessData(String businessId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        long startTime = System.currentTimeMillis();
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dps/businessData/{businessId}", params);
        long endTime = System.currentTimeMillis();
        log.info("------查询流程发起后的流程活动审批详情=总时间 {} ms==参数:taskId={}", endTime - startTime, businessId);
        try {
            DataResult<JSONObject> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<JSONObject>>() {
                    });
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }
}
