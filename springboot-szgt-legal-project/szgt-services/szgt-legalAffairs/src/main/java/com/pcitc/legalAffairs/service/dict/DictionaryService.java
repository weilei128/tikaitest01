package com.pcitc.legalAffairs.service.dict;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.pcitc.common.entity.Result;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.service.dps.config.WfConfig;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;

@Service("sysDictService")
public class DictionaryService {
	
	public static Map<String, String> idNameMap = new HashMap<>();
	public static Map<String, String> codeNameMap = new HashMap<>();
	
    private static WfConfig wfConfig;
	private static DpsHttpService dpsHttpService;
	
	@Autowired
	public void setWfConfig(WfConfig wfConfig) {
		DictionaryService.wfConfig = wfConfig;
	}
	
	@Autowired
	public void setDpsHttpService(DpsHttpService dpsHttpService) {
		DictionaryService.dpsHttpService = dpsHttpService;
	}
	
	/**
	 * 通过ID查询数据字典值
	 * @param dictionaryId
	 * @return
	 */
	public static String queryDictionaryById(Serializable dictionaryId) {
		if (dictionaryId == null) {
			return null;
		}
		if (idNameMap.get(dictionaryId.toString()) != null) {
			return idNameMap.get(dictionaryId.toString());
		}
		String url = getFullUrl("dictionary/queryDictionaryCategoryById?categoryId={categoryId}");
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		setAuHeader(true, headers);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("categoryId", dictionaryId);
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);

		ResponseEntity<Result> responseEntity = dpsHttpService.restTemplate().exchange(url, HttpMethod.GET, entity, Result.class, paramMap);
		Result result = responseEntity.getBody();
		if (!result.isSuccess()) {
			if ("分类不存在或者已经删除".equals(result.getMsg())) {
				return dictionaryId.toString();
			}
			throw new BaseException(result.getMsg(), result.getCode());
		}
		Map<String, Object> map = (Map<String, Object>) (result.getData());
		String id = map.get("fId").toString();
		String code = map.get("fCode").toString();
		String name = map.get("fCnName").toString();
		idNameMap.put(id, name);
		codeNameMap.put(code, name);
		return name;
	}
	
	/**
	 * 通过ID查询数据字典值
	 * @param dictCode
	 * @return
	 */
	public static String queryDictionaryByCode(String dictCode) {
		if (dictCode == null) {
			return null;
		}
		if (codeNameMap.get(dictCode.toString()) != null) {
			return codeNameMap.get(dictCode.toString());
		}
		String url = getFullUrl("dictionary/queryDictionaryCategoryByCode");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		setAuHeader(true, headers);
		
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("code", dictCode);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(paramMap, headers);
		
		ResponseEntity<Result> responseEntity = dpsHttpService.restTemplate().exchange(url, HttpMethod.POST, entity, Result.class);
		Result result = responseEntity.getBody();
		if (!result.isSuccess()) {
			if ("分类不存在或者已经删除".equals(result.getMsg())) {
				return dictCode.toString();
			}

			throw new BaseException(result.getMsg(), result.getCode());
		}
		Map<String, Object> map = (Map<String, Object>) (result.getData());
		String id = map.get("fId").toString();
		String code = map.get("fCode").toString();
		String name = map.get("fCnName").toString();
		idNameMap.put(id, name);
		codeNameMap.put(code, name);
		return name;
	}
	
	/**
	 * 清除缓存数据
	 */
	public static void clearBuffer() {
		idNameMap.clear();
		codeNameMap.clear();
	}
	
	private static String getFullUrl(String url) {
		String domain = Optional.ofNullable(wfConfig.getUrl()).orElse("");
		if (!domain.endsWith("/")) {
			domain += "/";
		}
		if (url.startsWith("/")) {
			url = url.replaceFirst("\\/", "");
		}
		return domain + url;
	}
	
	private static void setAuHeader(boolean isAuthorization, HttpHeaders headers) {
		if (isAuthorization) {
			if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
				headers.set("Authorization", dpsHttpService.getAuthorization());
			}
		}
	}
}
