package com.oo.system.api.factory;

import com.oo.common.core.domain.DictDataSelect;
import com.oo.common.core.web.domain.SysMenu;
import com.oo.system.api.feign.RemoteDictDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RemoteDictDataServiceFallbackFactory implements FallbackFactory<RemoteDictDataService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteDictDataServiceFallbackFactory.class);

    @Override
    public RemoteDictDataService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteDictDataService()
        {
            @Override
            public String dictDataSelectValue(@RequestBody DictDataSelect dictDataSelect)
            {
                return null;
            }

            @Override
            public String dictDataSelectLabel(@RequestBody DictDataSelect dictDataSelect)
            {
                return null;
            }
        };

    }



}
