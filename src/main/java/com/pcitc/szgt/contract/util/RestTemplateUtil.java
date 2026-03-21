package com.pcitc.szgt.contract.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络请求工具
 */

@Component
public class RestTemplateUtil {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get 请求
     * @param url
     * @param uriParams
     * @return
     */
    public String getRequest(String url, Map<String, Object> uriParams, String paramToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        if(StringUtils.isEmpty(paramToken)){
            paramToken = getBearerToken();
        }

        if(!StringUtils.isEmpty(paramToken)){
            headers.set("Authorization", paramToken);
        }

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        HttpEntity<String> response = null;
        if(uriParams == null){
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } else {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, uriParams);
        }

        return response.getBody();
    }

    public String getRequest(String url, Map<String, Object> uriParams){
        return getRequest(url, uriParams, null);
    }

    /**
     * json请求
     * @param url
     * @param bodyParams  body参数  Map、List、JavaBean、String(json格式)
     * @param uriParams   uri参数 Map
     * @return
     */
    public String postJsonRequest(String url, Object bodyParams, Map<String, Object> uriParams, String paramToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(StringUtils.isEmpty(paramToken)){
            paramToken = getBearerToken();
        }

        if(!StringUtils.isEmpty(paramToken)){
            headers.set("Authorization", paramToken);
        }

        HttpEntity<Object> entity = new HttpEntity<>(bodyParams, headers);
        HttpEntity<String> response = null;
        if(uriParams == null){
            response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } else {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, uriParams);
        }

        return response.getBody();
    }

    public String postJsonRequest(String url, Object bodyParams, Map<String, Object> uriParams) {
        return postJsonRequest(url, bodyParams, uriParams, null);
    }

    /**
     * 表单请求
     * @param url
     * @param bodyParams
     * @return
     */
    public String postFormRequest(String url, Map<String, Object> bodyParams, String paramToken){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if(StringUtils.isEmpty(paramToken)){
            paramToken = getBearerToken();
        }

        if(!StringUtils.isEmpty(paramToken)){
            headers.set("Authorization", paramToken);
        }

        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        if(!CollectionUtils.isEmpty(bodyParams)){
            params.setAll(bodyParams);
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        HttpEntity<String> response = null;

        response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public String postFormRequest(String url, Map<String, Object> bodyParams){
        return postFormRequest(url, bodyParams, null);
    }

    /**
     * 	下载文件
     * @param url
     * @return
     */
    public byte[] BinaryRequest(String url){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<Resource>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
        return response.getBody();
    }

    /**
     *	获取当前token
     * @return
     */
    private String getBearerToken(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Object type = request.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE);
            Object value = request.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE);
            return type + " " + value;
//            return request.getHeader("Authorization");
        }
        return null;
    }

    public static String getToken(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Object value = request.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE);
            return value + "";
        }
        return null;
    }

    /**
     * json请求
     * @param url
     * @param bodyParams  body参数  Map、List、JavaBean、String(json格式)
     * @param uriParams   uri参数 Map
     * @return
     */
    public String deleteJsonRequest(String url, Object bodyParams, Map<String, Object> uriParams, String paramToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(StringUtils.isEmpty(paramToken)){
            paramToken = getBearerToken();
        }

        if(!StringUtils.isEmpty(paramToken)){
            headers.set("Authorization", paramToken);
        }

        HttpEntity<Object> entity = new HttpEntity<>(bodyParams, headers);
        HttpEntity<String> response = null;
        if(uriParams == null){
            response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        } else {
            response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, uriParams);
        }

        return response.getBody();
    }

    public String deleteJsonRequest(String url, Object bodyParams, Map<String, Object> uriParams) {
        return deleteJsonRequest(url, bodyParams, uriParams, null);
    }    
}