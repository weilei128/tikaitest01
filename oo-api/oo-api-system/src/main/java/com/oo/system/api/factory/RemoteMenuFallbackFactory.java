package com.oo.system.api.factory;

import com.oo.common.core.domain.R;
import com.oo.system.api.domain.SysDocMenuBusiness;
import com.oo.system.api.domain.SysDocMenuWdp;
import com.oo.system.api.domain.TreeSelect;
import com.oo.system.api.feign.RemoteMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class RemoteMenuFallbackFactory implements FallbackFactory<RemoteMenuService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteMenuFallbackFactory.class);

    @Override
    public RemoteMenuService create(Throwable throwable)
    {
        log.error("菜单服务调用失败:{}", throwable.getMessage());
        return new RemoteMenuService()
        {
            @Override
            public R<List<TreeSelect>> selectWdpTreeList(String wdpName) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<SysDocMenuWdp>> selectWdpList() {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<TreeSelect>> selectBusinessTreeList(String businessName) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<SysDocMenuBusiness>> selectBusinessList() {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getWdpName(@RequestParam("wdpId") String wdpId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<String> getBusinessDocName(@RequestParam("businessId") String businessId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<Long>> selectChildIds(@RequestParam("businessId") Long businessId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
            @Override
            public R<List<Long>> selectWdpIdsByBusinessId(@RequestParam("businessId") Long businessId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }
        };
    }
}
