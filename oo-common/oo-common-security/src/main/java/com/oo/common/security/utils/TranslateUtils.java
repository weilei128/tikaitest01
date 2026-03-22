package com.oo.common.security.utils;

import com.oo.common.core.utils.ServletUtils;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.redis.service.RedisService;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.feign.RemoteDictService;
import org.apache.poi.util.SystemOutLogger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 翻译工具类
 */
public class TranslateUtils {

    private static RemoteDictService remoteDictService;

    private static RemoteDictService getDictClient() {
        if (remoteDictService == null) {
            remoteDictService = SpringUtils.getBean(RemoteDictService.class);
        }
        return remoteDictService;
    }

    /**
     * 翻译字典管理 cache key
     */
    public static final String SYS_TRANSLATE_KEY = "oo-gjgs-dc:sys_translate:";

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return SYS_TRANSLATE_KEY + configKey;
    }

    /**
     * 获取翻译字典缓存
     *
     * @param key 参数键
     * @return dictDatas 翻译字典数据列表
     */
    public static List<SysTranslate> getTranslateCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            return StringUtils.cast(cacheObj);
        }
//        getDictClient().loadingTranslateCache();
//        Object cache = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
//        if (StringUtils.isNotNull(cache))
//        {
//            return StringUtils.cast(cache);
//        }
        return null;
    }

    /**
     * 获取对应语言的翻译字典缓存
     *
     * @param key 参数键
     * @return dictDatas 翻译字典数据列表
     */
    public static List<SysTranslate> getLangTranslateCache(String key, String lang)
    {
        if (StringUtils.isEmpty(lang)) {
            HttpServletRequest httpServletRequest = ServletUtils.getRequest();
            if (StringUtils.isNotNull(httpServletRequest)) {
                lang = httpServletRequest.getHeader("Accept-Language");
            }
            if (StringUtils.isEmpty(lang)) {
                lang = "zh-cn";
            }
        }
        Object cacheObj = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<SysTranslate> dataList = StringUtils.cast(cacheObj);
            if (!dataList.isEmpty()) {
                String finalLang = lang;
                return dataList.stream().filter(a -> a.getLang().equals(finalLang)).collect(Collectors.toList());
            }
            return dataList;
        }
        return null;
    }

    /**
     * 获取翻译内容
     *
     * @param category 分类
     * @param fieldId 字段id
     * @param lang 语言类型标识
     * @return 翻译内容
     */
    public static String getTranslateContent(String category, String fieldId, String lang)
    {
        if (StringUtils.isEmpty(lang)) {
            HttpServletRequest httpServletRequest = ServletUtils.getRequest();
            if (StringUtils.isNotNull(httpServletRequest)) {
                lang = httpServletRequest.getHeader("Accept-Language");
            }
            if (StringUtils.isEmpty(lang)) {
                lang = "zh-cn";
            }
        }
        if (StringUtils.isEmpty(category)) {
            return null;
        }
        List<SysTranslate> datas = getTranslateCache(category);

        if (StringUtils.isNotNull(datas))
        {
            for (SysTranslate item : datas)
            {
                if (fieldId.equals(item.getFieldId().toString()) && lang.equals(item.getLang()))
                {
                    return item.getContent();
                }
            }
        }
        return null;
    }
}
