package com.oo.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.oo.common.core.exception.ServiceException;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.bean.BeanUtils;
import com.oo.common.core.web.domain.TranslateVO;
import com.oo.common.redis.service.RedisService;
import com.oo.common.security.utils.DictUtils;
import com.oo.common.security.utils.TranslateUtils;
import com.oo.system.api.domain.SysDictData;
import org.springframework.stereotype.Service;
import com.oo.system.mapper.SysTranslateMapper;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.service.ISysTranslateService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * 多语言配置Service业务层处理
 * 
 * @author oo
 * @date 2023-08-09
 */
@Service
public class SysTranslateServiceImpl extends MppServiceImpl<SysTranslateMapper, SysTranslate> implements ISysTranslateService
{

    /**
     * 项目启动时，初始化翻译字典到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingTranslateCache();
    }

    /**
     * 设置翻译字典缓存
     *
     * @param key 参数键
     * @param transDatas 字典数据列表
     */
    public static void setTranslateCache(String key, List<SysTranslate> transDatas)
    {
        SpringUtils.getBean(RedisService.class).setCacheObject(TranslateUtils.getCacheKey(key), transDatas);
    }

    /**
     * 删除指定翻译字典缓存
     *
     * @param key 字典键
     */
    public void removeTranslateCache(String key)
    {
        SpringUtils.getBean(RedisService.class).deleteObject(TranslateUtils.getCacheKey(key));
    }

    /**
     * 清空翻译字典缓存
     */
    @Override
    public void clearTranslateCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(TranslateUtils.SYS_TRANSLATE_KEY + "*");
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * 查询多语言配置列表
     * 
     * @param sysTranslate 多语言配置
     * @return 多语言配置
     */
    @Override
    public List<SysTranslate> selectSysTranslateList(SysTranslate sysTranslate)
    {
        return baseMapper.selectSysTranslateList(sysTranslate);
    }

    /**
     * 保存
     *
     * @param fieldId 字段id
     * @param voList 翻译数组
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submit(String fieldId, List<TranslateVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return true;
        }
        remove(Wrappers.<SysTranslate>update().lambda().eq(SysTranslate::getFieldId, fieldId));
        boolean result = true;
        for (TranslateVO vo : voList) {
            SysTranslate sysTranslate = new SysTranslate();
            BeanUtils.copyProperties(vo, sysTranslate);
            if (sysTranslate.getFieldId() == null) {
                sysTranslate.setFieldId(Long.valueOf(fieldId));
            }
            result = result && saveOrUpdateByMultiId(sysTranslate);
        }
        if (result && StringUtils.isNotEmpty(voList.get(0).getCategory())) { //更新缓存
            resetTransCacheByCategory(voList.get(0).getCategory());
//            removeTranslateCache(category);
//            SysTranslate queryItem = new SysTranslate();
//            queryItem.setCategory(category);
//            Map<String, List<SysTranslate>> translateMap = baseMapper.selectSysTranslateList(queryItem).stream().filter(a -> StringUtils.isNotBlank(a.getCategory())).collect(Collectors.groupingBy(SysTranslate::getCategory));
//            for (Map.Entry<String, List<SysTranslate>> entry : translateMap.entrySet())
//            {
//                setTranslateCache(entry.getKey(), new ArrayList<>(entry.getValue()));
//            }
        }
        return result;
    }

    /**
     * 删除
     *
     * @param fieldIds 字段ids（同一category的）
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(List<String> fieldIds) {
        List<SysTranslate> dataList = list(Wrappers.<SysTranslate>query().lambda().in(SysTranslate::getFieldId, fieldIds));
        if (dataList == null || dataList.isEmpty()) {
            return true;
        }
        boolean result = true;
        for (SysTranslate item : dataList) {
            result = result && deleteByMultiId(item);
//            if (result && StringUtils.isNotEmpty(item.getCategory())) {
//                removeTranslateCache(item.getCategory());
//            }
        }
        resetTransCacheByCategory(dataList.get(0).getCategory());
        return result;
    }

    /**
     * 加载翻译字典缓存数据
     */
    @Override
    public void loadingTranslateCache()
    {
        SysTranslate translate = new SysTranslate();
        Map<String, List<SysTranslate>> translateMap = baseMapper.selectSysTranslateList(translate).stream().filter(a -> StringUtils.isNotBlank(a.getCategory())).collect(Collectors.groupingBy(SysTranslate::getCategory));
        for (Map.Entry<String, List<SysTranslate>> entry : translateMap.entrySet())
        {
            setTranslateCache(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    /**
     * 重置所有翻译缓存数据
     */
    @Override
    public void resetTranslateCache()
    {
        clearTranslateCache();
        loadingTranslateCache();
    }

    /**
     * 重置某个类的翻译缓存数据
     */
    @Override
    public void resetTransCacheByCategory(String category)
    {
        removeTranslateCache(category);
        List<SysTranslate> translateList = list(Wrappers.<SysTranslate>query().lambda().eq(SysTranslate::getCategory, category));
        setTranslateCache(category, translateList);
    }
}
