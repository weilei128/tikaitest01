package com.pcitc.legalAffairs.service.dps.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.service.dps.config.WfConfig;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/***
 * @description 工作流接口请求
 * @author leigang
 * @date 2020年3月27日 14:26:37
 *
 */
@Service
@Slf4j
public class DpsHttpService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WfConfig wfConfig;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getAuthorization() {
        try {
            ServletRequestAttributes requestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            return request.getHeader("Authorization");
        } catch (Exception e) {

        }
        return null;
    }

    public Result workflow(MultiValueMap<String, String> bodyParams) {
        String result = dpsPostObjectRequest(HttpMethod.POST, wfConfig.getUrl() + "/dps/workflow",
                bodyParams, null, true);
        return JSON.parseObject(result, Result.class);
    }

    public Result start(StartVo startVo) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(startVo);
        String result = dpsObjectRequest(HttpMethod.POST, wfConfig.getUrl() + "/dps/start",
                jsonObject, null, true);
        return JSON.parseObject(result, Result.class);
    }
    
    public Result complete(ExecuteContext executeContext) {
    	JSONObject jsonObject = (JSONObject) JSON.toJSON(executeContext);
        String result = dpsObjectRequest(HttpMethod.POST, wfConfig.getUrl() + "/dps/complete", jsonObject, null, true);
        return JSON.parseObject(result, Result.class);

    }
    
    public Result taskMessage(StartVo executeContext) {
    	JSONObject jsonObject = (JSONObject) JSON.toJSON(executeContext);
        String result = dpsObjectRequest(HttpMethod.POST, wfConfig.getUrl() + "/dps/taskMessage", jsonObject, null, true);
        return JSON.parseObject(result, Result.class);
    }
    
    public Result taskMessageComplete(String taskId) {
    	String url = wfConfig.getUrl() + "/dps/taskMessageComplete/{taskId}";
    	Map<String, String> uriParam = new HashMap<>();
    	uriParam.put("taskId", taskId);
    	String result = dpsObjectRequest(HttpMethod.GET, url, null, (JSONObject) JSON.toJSON(uriParam), true);
    	return JSON.parseObject(result, Result.class);
    }


    private String dpsPostObjectRequest(HttpMethod method, String url,
                                        MultiValueMap<String, String> bodyParams,
                                        JSONObject uriParams, boolean isAuthorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setAuHeader(isAuthorization, headers);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodyParams, headers);
        HttpEntity<String> response;
        if (uriParams == null) {
            response = restTemplate.exchange(url, method, entity, String.class);
        } else {
            response = restTemplate.exchange(url, method, entity, String.class, uriParams);
        }

        return response.getBody();
    }

    private void setAuHeader(boolean isAuthorization, HttpHeaders headers) {
//        headers.set("Authorization", "bearer 5e771b7a-d5fc-4e2b-b3a0-3e7e3dca775b");
        if (isAuthorization) {
            if (!StringUtils.isEmpty(getAuthorization())) {
                headers.set("Authorization", getAuthorization());
            }
        }
    }

    private String dpsObjectRequest(HttpMethod method, String url,
                                    JSONObject bodyParams,
                                    JSONObject uriParams,
                                    boolean isAuthorization) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        setAuHeader(isAuthorization, headers);
        HttpEntity<JSONObject> entity = new HttpEntity<>(bodyParams, headers);
        HttpEntity<String> response;
        if (uriParams == null) {
            response = restTemplate.exchange(url, method, entity, String.class);
        } else {
            response = restTemplate.exchange(url, method, entity, String.class, uriParams);
        }

        return response.getBody();
    }

    private String dpsArrayRequest(HttpMethod method, String url,
                                   JSONArray bodyParams,
                                   JSONObject uriParams,
                                   boolean isAuthorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        setAuHeader(isAuthorization, headers);
        HttpEntity<JSONArray> entity = new HttpEntity<JSONArray>(bodyParams, headers);
        HttpEntity<String> response = null;
        if (uriParams == null) {
            response = restTemplate.exchange(url, method, entity, String.class);
        } else {
            response = restTemplate.exchange(url, method, entity, String.class, uriParams);
        }

        return response.getBody();
    }


}
