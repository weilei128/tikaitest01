package com.oo.common.security.utils;

import java.util.Collection;
import java.util.List;
import com.oo.common.core.constant.Constants;
import com.oo.common.core.domain.R;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.redis.service.RedisService;
import com.oo.system.api.domain.SysDictData;

import com.oo.system.api.feign.RemoteDictService;

/**
 * 字典工具类
 * 
 * @author ruoyi
 */
public class DictUtils
{
    private static RemoteDictService remoteDictService;

    private static RemoteDictService getDictClient() {
        if (remoteDictService == null) {
            remoteDictService = SpringUtils.getBean(RemoteDictService.class);
        }
        return remoteDictService;
    }

    /**
     * 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisService.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            return StringUtils.cast(cacheObj);
        }
//        getDictClient().loadingDictCache();
//        Object cache = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
//        if (StringUtils.isNotNull(cache))
//        {
//            return StringUtils.cast(cache);
//        }
        return null;
    }

    /**
     * 删除指定字典缓存
     * 
     * @param key 字典键
     */
    public static void removeDictCache(String key)
    {
        SpringUtils.getBean(RedisService.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.SYS_DICT_KEY + configKey;
    }

    /**
     * 获取字典标签
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return
     */
    public static String getDictLabel(String dictType, String dictValue)
    {
        if (StringUtils.isEmpty(dictType) || StringUtils.isEmpty(dictValue)) {
            return null;
        }
        R<String> result = getDictClient().getDictLabel(dictType, dictValue);
        return result.getData();
    }

    /**
     * 获取字典键值
     * @param dictType 字典类型
     * @param dictLabel 字典标签
     * @return
     */
    public static String getDictValue(String dictType, String dictLabel)
    {
        if (StringUtils.isEmpty(dictType) || StringUtils.isEmpty(dictLabel)) {
            return null;
        }
        R<String> result = getDictClient().getDictValue(dictType, dictLabel);
        return result.getData();
    }
}
