package com.oo.system.api.feign;


import com.oo.common.core.constant.ServiceNameConstants;
import com.oo.common.core.domain.DictDataSelect;
import com.oo.system.api.factory.RemoteDictDataServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典数据查询
 */
@FeignClient(contextId = "remoteDictDataService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDictDataServiceFallbackFactory.class)
public interface RemoteDictDataService {

    /**
     *
     * @return 字典数据通过dict_type和dict_label查dict_sort（id）
     */
    @PostMapping("/dict/data/dictDataSelectValue")
    public String dictDataSelectValue(@RequestBody DictDataSelect dictDataSelect);
    /**
     *
     * @return 字典数据通过dict_type和dict_sort查dict_label（明文）
     */
    @PostMapping("/dict/data/dictDataSelectLabel")
//    public String dictDataSelectLabel(@PathVariable("dictType") String dictType,@PathVariable("dictValue") String dictValue);
    public String dictDataSelectLabel(@RequestBody DictDataSelect dictDataSelect);
}
