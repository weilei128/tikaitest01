package com.oo.system.service;

import java.util.List;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.oo.common.core.web.domain.TranslateVO;
import com.oo.system.api.domain.SysTranslate;

/**
 * 多语言配置Service接口
 * 
 * @author oo
 * @date 2023-08-09
 */
public interface ISysTranslateService extends IMppService<SysTranslate>
{

    /**
     * 查询多语言配置列表
     * 
     * @param sysTranslate 多语言配置
     * @return 多语言配置集合
     */
    List<SysTranslate> selectSysTranslateList(SysTranslate sysTranslate);

    /**
     * 保存
     *
     * @param fieldId 字段id
     * @param voList 翻译数组
     * @return
     */
    boolean submit(String fieldId, List<TranslateVO> voList);

    /**
     * 删除
     *
     * @param fieldIds 字段ids（同一category的）
     * @return
     */
    boolean remove(List<String> fieldIds);

    /**
     * 加载翻译字典缓存数据
     */
    void loadingTranslateCache();

    /**
     * 清空翻译字典缓存数据
     */
    void clearTranslateCache();

    /**
     * 重置翻译字典缓存数据
     */
    void resetTranslateCache();

    /**
     * 重置某个类的翻译缓存数据
     */
    void resetTransCacheByCategory(String category);
}
