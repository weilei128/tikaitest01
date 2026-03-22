package com.oo.common.security.aspect;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.oo.common.core.annotation.DictField;
import com.oo.common.core.utils.SpringUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.annotation.DataTranslate;
import com.oo.common.core.web.domain.TranslateVO;
import com.oo.common.security.utils.DictUtils;
import com.oo.common.security.utils.TranslateUtils;
import com.oo.system.api.domain.SysDictData;
import com.oo.system.api.domain.SysTranslate;
import com.oo.system.api.feign.RemoteDictService;
import com.oo.system.api.feign.RemoteTranslateService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

import cn.hutool.core.bean.BeanUtil;


/**
 * 翻译字典映射方法
 */
@Component
public class ReflectUtil {

    private final RemoteDictService remoteDictService = SpringUtils.getBean(RemoteDictService.class);
    private final RemoteTranslateService remoteTranslateService = SpringUtils.getBean(RemoteTranslateService.class);

    /**
     * 多语言翻译查询
     *
     * @param result
     * @param aClass
     */
    public void translateDTO(Object result, Class<?> aClass, String lang)  {
        // 获取这个类下的所有字段
        final Field[] declaredFields = getAllFields(aClass);
        for (Field field : declaredFields) {

            // 获取类上的@DataTranslate注解
            final DataTranslate dataTranslate = field.getAnnotation(DataTranslate.class);
            // 如果没有此注解则跳过
            if (Objects.isNull(dataTranslate)) continue;

            // 获取声明的翻译字段参数信息
            final String fieldId = dataTranslate.fieldId();
            String category = dataTranslate.category();

            if ("dict".equals(category)) {
                category = BeanUtil.getFieldValue(result, "dictType").toString();
            }

            // 取出目标对象对应字段的值
            final Object fieldValue = BeanUtil.getFieldValue(result, field.getName());
            final Object fieldIdValue = BeanUtil.getFieldValue(result, fieldId);
            // 如果没有值则跳过
            if (Objects.isNull(fieldId)) continue;

            // 调用Redis资源开始翻译
            final String translateName = TranslateUtils.getTranslateContent(category, fieldIdValue.toString(), lang);
            // 赋值翻译字段
            BeanUtil.setFieldValue(result, field.getName(), StringUtils.isEmpty(translateName) ? fieldValue : translateName);
        }
    }


    /**
     * 提交表单数据自动更新字典
     *
     * @param list
     * @param aClass
     * @param lang
     */
    public void submitDictData(List<Object> list, Class<?> aClass, String lang) {
        final Field[] declaredFields = getAllFields(aClass);
        for (Field field : declaredFields) {
            // 获取类上的@DictField注解
            final DictField dictField = field.getAnnotation(DictField.class);
            // 如果没有此注解则跳过
            if (Objects.isNull(dictField)) continue;
            // 获取字典类型
            final String dictType = dictField.dictType();
            List<SysDictData> dictDataList = DictUtils.getDictCache(dictType);
            for (Object item : list) {
                // 取出目标对象对应字段的值
                Object fieldValue = BeanUtil.getFieldValue(item, field.getName());
                if (fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) {
                    continue;
                }
                boolean isHave = false;
                // 判断字典值是否存在 没有的新建
                List<SysTranslate> translateList = TranslateUtils.getLangTranslateCache(dictType, lang);
                if (translateList != null && !translateList.isEmpty()) { //查询翻译数据 当前语言有无匹配数据
                    isHave = translateList.stream().anyMatch(e -> e.getContent().equals(fieldValue.toString()));
                }
                if (!isHave && dictDataList != null && !dictDataList.isEmpty()) { //翻译里没有对应语言的数据 查字典的默认值
                    SysDictData currDictData = dictDataList.stream().filter(e -> e.getDictLabel().equals(fieldValue.toString())).findFirst().orElse(null);
                    if (currDictData != null) { //如果默认值和选择的相同 新增所选值为当前语言的翻译数据
                        isHave = true;
                        SysTranslate translate = new SysTranslate();
                        translate.setFieldId(currDictData.getDictCode());
                        translate.setLang(lang);
                        translate.setCategory(dictType);
                        translate.setContent(fieldValue.toString());
                        remoteTranslateService.translateAdd(translate);
                    }
                }
                if (!isHave) { //新增
                    SysDictData dictData = new SysDictData();
                    dictData.setDictLabel(fieldValue.toString());
                    dictData.setDictType(dictType);
                    dictData.setIsDefault("N");
                    dictData.setStatus("0");
                    long maxSort = dictDataList == null ? 0 : dictDataList.stream().map(SysDictData::getDictSort).max(Comparator.comparing(e -> e)).orElse(0L);
                    dictData.setDictSort(maxSort + 1);
                    String dictValue = String.valueOf(IdWorker.getId());
                    dictData.setDictValue(dictValue);
                    TranslateVO translateVO = new TranslateVO();
                    translateVO.setLang(lang);
                    translateVO.setCategory(dictType);
                    translateVO.setContent(fieldValue.toString());
                    dictData.setTransList(Collections.singletonList(translateVO));
                    Integer row = remoteDictService.insertDictData(dictData).getData();
                    if (row != null && row > 0) {
                        // 赋值保存的字典字段
                        BeanUtil.setFieldValue(item, field.getName(), dictValue);
                    }
                }
                else {
                    // 将label转为value赋值字段
                    BeanUtil.setFieldValue(item, field.getName(), DictUtils.getDictValue(dictType, fieldValue.toString()));
                }
            }
        }
    }

    /**
     * 获取所有字段
     *
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }
}
