package com.pcitc.legalAffairs.service.userorg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.system.bo.SysOrganizationBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pcitc.common.entity.Result;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pctic.common.utils.UserUtils;

/**
 * 查询用户绑定的组织ID
 * @author meihongli
 *
 */
@Service
public class UserOrgService {

	@Value("${dps.url}")
	private String sysUrl;
	@Autowired
	private DpsHttpService dpsHttpService;
	
	/**
	 * 获取组织列表
	 * 
	 * @param showDepartment 是否显示部门
	 * @return
	 */
	public List<Map<String, Object>> getOrgInfo(boolean showDepartment) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganization";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);

		Result result = response.getBody();
		List<Map<String, Object>> resMap = null;
		if (!result.isSuccess()) {
			resMap = new ArrayList<>();
		} else {
			resMap = (List<Map<String, Object>>) result.getData();
		}
		if (showDepartment) {
			return resMap;
		}
		return getCompany(resMap);
	}
	
	private List<Map<String, Object>> getCompany(List<Map<String, Object>> list) {
		if (list == null) {
			return null;
		}
		return list.stream().map(this::getCompany).filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	private Map<String, Object> getCompany(Map<String, Object> map) {
		if (map.get("childNodeList") != null) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("childNodeList");
			if (list.size() > 0) {
				list = list.stream().map(each -> {
					return getCompany(each);
				}).filter(self -> self != null).collect(Collectors.toList());
				map.put("childNodeList", list);
			}
		}
		if (
			map.get("fType") != null
			&& Integer.valueOf("0").equals(Integer.valueOf(map.get("fType").toString())) 
			&& (
				map.get("childNodeList") == null 
				|| ((List<Map<String, Object>>) map.get("childNodeList")).size() == 0
			)
		) {
			return null;
		}
		return map;
	}
	
	/**
	 * 获取组织信息
	 * @param orgId
	 * @return return
	 */
	public Map<String, Object> getOrgInfo(String orgId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganizationById";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("organizationId", orgId);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		Map<String, Object> resMap = null;
		if (!result.isSuccess()) {
			resMap = new HashMap<>();
		} else {
			resMap = (Map<String, Object>) result.getData();
		}
		return resMap;
	}

	public List<Map<String, Object>> getOrgInfos(List<?> orgId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganizationByIds";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("organizationIds", orgId);
		map.put("type", "0");
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		List<Map<String, Object>> resMap = null;
		if (!result.isSuccess()) {
			resMap = new ArrayList<>();
		} else {
			resMap = (List<Map<String, Object>>) result.getData();
		}
		return resMap;
	}


	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUserInfo(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/userInfo/queryUserById";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("userId", userId);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		Map<String, Object> resMap = (Map<String, Object>) result.getData();
		
		return resMap;
	}

	/**
	 * 获取用户绑定的组织ID
	 * @return
	 */
	public List<Long> getLoginUserOrg() {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/userManage/querySelectOrganization";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
//		headers.set("Authorization", "bearer 6bdb848c-9f32-4c88-bd39-ccfe1ed0a14f");

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("userId", String.valueOf(UserUtils.getUserInfo().getfId()));
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		List list = (List) result.getData();
		if (list != null) {
			return (List<Long>) list.stream().map(each -> Long.parseLong(each.toString())).collect(Collectors.toList());
		}
		return null;
	}
	
	/**
	 * 获取用户绑定的组织ID
	 * @return
	 */
	public List<Long> getUserOrg(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/userManage/querySelectOrganization";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
//		headers.set("Authorization", "bearer 6bdb848c-9f32-4c88-bd39-ccfe1ed0a14f");
		
		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("userId", userId);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		List list = (List) result.getData();
		if (list != null) {
			return (List<Long>) list.stream().map(each -> Long.parseLong(each.toString())).collect(Collectors.toList());
		}
		return null;
	}
	
	/**
	 * 获取用户绑定的组织ID及其下全部组织ID
	 * @return
	 */
	public List<Long> getLoginUserChildren() {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganizationByIds";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
//		headers.set("Authorization", "bearer 6bdb848c-9f32-4c88-bd39-ccfe1ed0a14f");
		
		List<Long> orgs = getLoginUserOrg();
		if (orgs == null) {
			return null;
		}

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("organizationIds", orgs.stream().map(each -> (Object) each).collect(Collectors.toList()));
		map.put("type", 0);
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		List<Map<String, Object>> data = (List) result.getData();
		
		
		return getOrgIds(data, orgs);
	}

	public List<Long> getOrgIds(List<Map<String, Object>> data, List<Long> list) {
		data.forEach(each -> getOrgIdsR(each, list));
		if (list == null) {
			return null;
		}
		return list.stream().distinct().collect(Collectors.toList());
	}
	
	private void getOrgIdsR(Map<String, Object> data, List<Long> list) {
		if (data.get("fId") != null) {
			list.add(Long.parseLong(String.valueOf(data.get("fId"))));
		}
		if (data.get("childNodeList") != null) {
			List<Map<String, Object>> l = (List) data.get("childNodeList");
			l.stream().forEach(each -> getOrgIdsR(each, list));
		}
	}

	/**
	 * 获取用户绑定的组织ID及其下全部组织ID
	 * @return
	 */
	public List<SysOrganizationBo> getLoginUserChildrenBo() {
		RestTemplate restTemplate = new RestTemplate();
		String url = sysUrl + "/system/queryOrganizationByIds";
		// token
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

//		headers.set("Authorization", "bearer 6bdb848c-9f32-4c88-bd39-ccfe1ed0a14f");

		List<Long> orgs = getLoginUserOrg();
		if (orgs == null) {
			return null;
		}

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("organizationIds", orgs.stream().map(each -> (Object) each).collect(Collectors.toList()));
		map.put("type", 0);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = response.getBody();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
		}
		List<Map<String, Object>> mapData = (List) result.getData();

		JSONArray data = JSONArray.parseArray(JSONArray.toJSON(mapData).toString());
		List<SysOrganizationBo> boList = new ArrayList<>();
		return getOrgDatas(data, boList);
	}
	private List<SysOrganizationBo> getOrgDatas(JSONArray data, List<SysOrganizationBo> list) {

		for(Object object :data){
			JSONObject obj = (JSONObject) object;
			if (obj.get("fId") != null) {
				SysOrganizationBo bo = JSONObject.toJavaObject(obj, SysOrganizationBo.class);
				list.add(bo);
			}
			if (obj.get("childNodeList") != null) {
				JSONArray jsonArray = JSONArray.parseArray(obj.get("childNodeList").toString());
				getOrgDatas(jsonArray, list);
			}
		}
		if (list == null) return null;
		return list.stream().distinct().collect(Collectors.toList());
	}

	private void getOrgDatasR(Map<String, Object> data, List<Long> list) {
		if (data.get("fId") != null) {
			list.add(Long.parseLong(String.valueOf(data.get("fId"))));
		}
		if (data.get("childNodeList") != null) {
			List<Map<String, Object>> l = (List) data.get("childNodeList");
			l.stream().forEach(each -> getOrgIdsR(each, list));
		}
	}
	
}
