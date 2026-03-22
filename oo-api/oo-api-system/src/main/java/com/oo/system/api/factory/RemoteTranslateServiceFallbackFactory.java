package com.oo.system.api.factory;

import com.oo.common.core.domain.R;
import com.oo.common.core.web.domain.TranslateSubmitVo;
import com.oo.common.core.web.domain.TranslateVO;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.feign.RemoteTranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class RemoteTranslateServiceFallbackFactory  implements FallbackFactory<RemoteTranslateService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteTranslateServiceFallbackFactory.class);

    @Override
    public RemoteTranslateService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteTranslateService()
        {
            @Override
            public boolean translateSubmit(@RequestBody TranslateSubmitVo translateSubmitVo) {
                return false;
            }

            @Override
            public int translateRemove(@PathVariable String[] Id ) {
                return 0;
            }

            @Override
            public R translateAdd(@RequestBody SysTranslate sysTranslate) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
        };
    }
}
