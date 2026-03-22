package com.oo.system.api.feign;

import com.oo.common.core.constant.ServiceNameConstants;
import com.oo.common.core.domain.R;
import com.oo.system.api.domain.SysDictData;
import com.oo.system.api.factory.RemoteDictFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 字典服务
 *
 * @author
 */
@FeignClient(contextId = "remoteDictService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDictFallbackFactory.class)
public interface RemoteDictService {

    String API_PREFIX = "/client";
    String GET_LABEL = API_PREFIX + "/dict/get-label";
    String GET_VALUE = API_PREFIX + "/dict/get-value";
    String INSERT_DICT_DATA = API_PREFIX + "/dict/insert-dict-data";
    String LOADING_DICT_CACHE = API_PREFIX + "/dict/loading-cache";
    String LOADING_TRANSLATE_CACHE = API_PREFIX + "/dict/loading-translate-cache";

    /**
     * 获取字典标签
     * @param dictType
     * @param dictValue
     * @return
     */
    @GetMapping(GET_LABEL)
    R<String> getDictLabel(@RequestParam("dictType") String dictType, @RequestParam("dictValue") String dictValue);

    /**
     * 获取字典键值
     * @param dictType
     * @param dictLabel
     * @return
     */
    @GetMapping(GET_VALUE)
    R<String> getDictValue(@RequestParam("dictType") String dictType, @RequestParam("dictLabel") String dictLabel);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData
     * @return
     */
    @PostMapping(INSERT_DICT_DATA)
    R<Integer> insertDictData(@RequestBody SysDictData dictData);

    /**
     * 加载字典缓存数据
     *
     */
    @GetMapping(LOADING_DICT_CACHE)
    R loadingDictCache();

    /**
     * 加载翻译缓存数据
     *
     */
    @GetMapping(LOADING_TRANSLATE_CACHE)
    R loadingTranslateCache();
}
