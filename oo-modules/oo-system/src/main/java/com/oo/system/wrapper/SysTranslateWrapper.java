package com.oo.system.wrapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.service.ISysTranslateService;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 翻译视图类
 */
public class SysTranslateWrapper {

    private final ISysTranslateService sysTranslateService = SpringUtils.getBean(ISysTranslateService.class);

    public static SysTranslateWrapper build() { return new SysTranslateWrapper(); }

    public Object entityVO(String fieldId, String category, Object obj) throws NoSuchFieldException, IllegalAccessException {
        if (StringUtils.isEmpty(fieldId)) {
            return obj;
        }
        List<SysTranslate> translateList = sysTranslateService.list(Wrappers.<SysTranslate>query().lambda().eq(SysTranslate::getFieldId, fieldId)
                .eq(StringUtils.isNotEmpty(category), SysTranslate::getCategory, category));
        Class c = obj.getClass();
        Field field = c.getDeclaredField("transList");
        field.setAccessible(true);
        field.set(obj, translateList);
        return obj;
    }
}