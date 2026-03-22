package com.oo.datamanagement.api.cache;

import com.oo.common.core.constant.CacheNames;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.redis.service.RedisService;
import com.oo.datamanagement.api.feign.RemoteDataManageService;

/**
 * 数据管理缓存
 */
public class DataManageCache {

    private static RemoteDataManageService remoteDataManageService;

    private static RemoteDataManageService getDataManageClient() {
        if (remoteDataManageService == null) {
            remoteDataManageService = SpringUtils.getBean(RemoteDataManageService.class);
        }
        return remoteDataManageService;
    }

    public static final String ORGANIZATION_CACHE = CacheNames.DATA_MANAGE_CACHE + ":organization";
    public static final String ORGANIZATION_NAME = ORGANIZATION_CACHE + ":name:";
    public static final String PROJECT_CACHE = CacheNames.DATA_MANAGE_CACHE + ":project";
    public static final String PROJECT_NAME = PROJECT_CACHE + ":name:";
    public static final String WELL_CACHE = CacheNames.DATA_MANAGE_CACHE + ":well";
    public static final String WELL_NAME = WELL_CACHE + ":name:";

    /**
     * 获取组织机构名称
     *
     * @param organizationId 组织机构id
     * @return 组织机构名称
     */
    public static String getOrganizationName(String organizationId) {
        if (StringUtils.isEmpty(organizationId)) {
            return null;
        }
        if (SpringUtils.getBean(RedisService.class).hasKey(ORGANIZATION_NAME + organizationId)) {
            return SpringUtils.getBean(RedisService.class).getCacheObject(ORGANIZATION_NAME + organizationId);
        }
        String result = getDataManageClient().getOrganizationName(organizationId).getData();
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        SpringUtils.getBean(RedisService.class).setCacheObject(ORGANIZATION_NAME + organizationId, result);
        return result;
    }

    /**
     * 获取区块/井场名称
     *
     * @param projectId 区块/井场id
     * @return 区块/井场名称
     */
    public static String getProjectName(String projectId) {
        if (StringUtils.isEmpty(projectId)) {
            return null;
        }
        if (SpringUtils.getBean(RedisService.class).hasKey(PROJECT_NAME + projectId)) {
            return SpringUtils.getBean(RedisService.class).getCacheObject(PROJECT_NAME + projectId);
        }
        String result = getDataManageClient().getProjectName(projectId).getData();
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        SpringUtils.getBean(RedisService.class).setCacheObject(PROJECT_NAME + projectId, result);
        return result;
    }

    /**
     * 获取井名称
     *
     * @param wellId 井id
     * @return 井名称
     */
    public static String getWellName(String wellId) {
        if (StringUtils.isEmpty(wellId)) {
            return null;
        }
        if (SpringUtils.getBean(RedisService.class).hasKey(WELL_NAME + wellId)) {
            return SpringUtils.getBean(RedisService.class).getCacheObject(WELL_NAME + wellId);
        }
        String result = getDataManageClient().getWellName(wellId).getData();
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        SpringUtils.getBean(RedisService.class).setCacheObject(WELL_NAME + wellId, result);
        return result;
    }
}
