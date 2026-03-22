package com.oo.system.api.feign;


import com.oo.common.core.constant.ServiceNameConstants;
import com.oo.common.core.domain.R;
import com.oo.common.core.web.domain.TranslateSubmitVo;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.factory.RemoteTranslateServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 字典数据查询
 */
@FeignClient(contextId = "remoteTranslateService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteTranslateServiceFallbackFactory.class)
public interface RemoteTranslateService {


    /**
     *
     * @return 菜单多语言保存
     */
    @PostMapping("/translate/submit")
    public boolean translateSubmit(@RequestBody TranslateSubmitVo translateSubmitVo);

    /**
     *
     * @return 菜单多语言删除
     */
    @PostMapping("/translate/remove")
    public int translateRemove(@RequestBody String[] Id );

    /**
     * 添加某个类型的多语言
     *
     * @return 菜单多语言
     */
    @PostMapping("/translate/add")
    public R translateAdd(@RequestBody SysTranslate sysTranslate);
}
