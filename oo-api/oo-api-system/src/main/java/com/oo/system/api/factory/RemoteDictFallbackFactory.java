package com.oo.system.api.factory;

import com.oo.common.core.domain.R;
import com.oo.system.api.domain.SysDictData;
import com.oo.system.api.feign.RemoteDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 字典服务降级处理
 *
 * @author
 */
@Component
public class RemoteDictFallbackFactory implements FallbackFactory<RemoteDictService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteDictFallbackFactory.class);

    @Override
    public RemoteDictService create(Throwable throwable)
    {
        log.error("字典服务调用失败:{}", throwable.getMessage());
        return new RemoteDictService()
        {
            @Override
            public R<String> getDictLabel(String dictType, String dictValue)
            {
                return R.fail("获取字典失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getDictValue(String dictType, String dictLabel)
            {
                return R.fail("获取字典失败:" + throwable.getMessage());
            }
            @Override
            public R<Integer> insertDictData(@RequestBody SysDictData dictData)
            {
                return R.fail("获取字典失败:" + throwable.getMessage());
            }
            @Override
            public R loadingDictCache()
            {
                return R.fail("获取字典失败:" + throwable.getMessage());
            }
            @Override
            public R loadingTranslateCache()
            {
                return R.fail("获取翻译数据失败:" + throwable.getMessage());
            }
        };
    }
}
