package com.oo.system.api.cache;

import com.oo.common.core.constant.CacheNames;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.redis.service.RedisService;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.feign.RemoteMenuService;

import java.util.List;

/**
 * 系统管理缓存
 */
public class SystemCache {

    private static RemoteMenuService remoteMenuService;

    private static RemoteMenuService getMenuClient() {
        if (remoteMenuService == null) {
            remoteMenuService = SpringUtils.getBean(RemoteMenuService.class);
        }
        return remoteMenuService;
    }

    public static final String WDP_CACHE = CacheNames.SYSTEM_CACHE + ":wdp";
    public static final String WDP_NAME = WDP_CACHE + ":name:";
    public static final String BUSINESS_DOC_CACHE = CacheNames.SYSTEM_CACHE + ":businessDoc";
    public static final String BUSINESS_DOC_NAME = BUSINESS_DOC_CACHE + ":name:";

    /**
     * 获取wdp名称
     *
     * @param wdpId wdpId
     * @return wdp名称
     */
    public static String getWdpName(String wdpId) {
        if (StringUtils.isEmpty(wdpId)) {
            return null;
        }
        return getMenuClient().getWdpName(wdpId).getData();
    }

    /**
     * 获取业务域文件夹名称
     *
     * @param businessId 业务域id
     * @return 业务域文件名称
     */
    public static String getBusinessDocName(String businessId) {
        if (StringUtils.isEmpty(businessId)) {
            return null;
        }
        return getMenuClient().getBusinessDocName(businessId).getData();
    }
}
