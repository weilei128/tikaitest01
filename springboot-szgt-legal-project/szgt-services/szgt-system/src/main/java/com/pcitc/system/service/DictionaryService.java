package com.pcitc.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.pcitc.common.exception.BaseException;
import com.pcitc.system.bo.SysDictionaryBo;
import com.pcitc.system.bo.SysDictionaryCategoryBo;
import com.pcitc.system.dbService.SysDictionaryCategoryService;
import com.pcitc.system.dbService.SysDictionaryService;
import com.pcitc.system.po.SysDictionary;
import com.pcitc.system.po.SysDictionaryCategory;
import com.pcitc.system.po.SysMenu;
import com.pcitc.system.vo.SysDictionaryCategoryVo;
import com.pcitc.system.vo.SysDictionaryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @description 数据字典管理服务
 * @author leigang
 * @date 2020年2月18日 14:45:37
 *
 */
@Service
public class DictionaryService {

    private SysDictionaryCategoryService sysDictionaryCategoryService;

    private SysDictionaryService sysDictionaryService;

    @Autowired
    public void setSysDictionaryService(SysDictionaryService sysDictionaryService) {
        this.sysDictionaryService = sysDictionaryService;
    }

    @Autowired
    public void setSysDictionaryCategoryService(SysDictionaryCategoryService sysDictionaryCategoryService) {
        this.sysDictionaryCategoryService = sysDictionaryCategoryService;
    }

    /**
     * 查询字典
     *
     * @param dictionaryCategoryId 字典分类id
     * @return 返回字典
     */
    public List<SysDictionaryVo> queryDictionary(String dictionaryCategoryId) {
        LambdaQueryWrapper<SysDictionary> queryWrapper = sysDictionaryService.lambdaQueryWrapper();
        queryWrapper.eq(SysDictionary::getFkDictionaryCategoryId, dictionaryCategoryId);
        List<SysDictionary> sysDictionaryList = sysDictionaryService.list(queryWrapper);
        List<SysDictionaryVo> sysDictionaryVoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysDictionaryList)) {
            return sysDictionaryVoList;
        }
        sysDictionaryList.forEach(sysDictionary -> {
            SysDictionaryVo sysDictionaryVo = new SysDictionaryVo();
            BeanUtils.copyProperties(sysDictionary, sysDictionaryVo);
            sysDictionaryVoList.add(sysDictionaryVo);
        });
        return sysDictionaryVoList;
    }

    public void deleteDictionary(String dictionaryId) {
        SysDictionary sysDictionary = sysDictionaryService.getById(dictionaryId);
        if (sysDictionary == null) {
            throw new BaseException("字典项不存在,或者已经删除", 500);
        }
        sysDictionaryService.removeById(dictionaryId);
    }

    public void updateDictionary(SysDictionaryBo sysDictionarBo) {
        if (sysDictionarBo == null) {
            throw new BaseException("参数不存在", 500);
        }
        Integer id = sysDictionarBo.getfId();

        if ( sysDictionaryService.getById(id) == null) {
            throw new BaseException("字典项不存在,或者已经删除", 500);
        }
        SysDictionary sysDictionary = new SysDictionary();
        BeanUtils.copyProperties(sysDictionarBo,sysDictionary);
        sysDictionaryService.updateById(sysDictionary);

    }

    public void addDictionaryCategory(SysDictionaryCategoryBo sysDictionaryCategoryBo) {
        if (sysDictionaryCategoryBo == null) {
            throw new BaseException("参数为空", 500);
        }
        SysDictionaryCategory sysDictionaryCategory = new SysDictionaryCategory();
        BeanUtils.copyProperties(sysDictionaryCategoryBo, sysDictionaryCategory);
        sysDictionaryCategoryService.save(sysDictionaryCategory);
    }

    /***
     * 查询数据字典分类
     * @return
     */
    public List<SysDictionaryCategoryVo> queryDictionaryCategory() {
        List<SysDictionaryCategory> categoryList = sysDictionaryCategoryService.list();
        List<SysDictionaryCategoryVo> sysDictionaryCategoryVoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(categoryList)) {
            return sysDictionaryCategoryVoList;
        }
        categoryList.forEach(sysDictionaryCategory -> {
            SysDictionaryCategoryVo sysDictionaryCategoryVo = new SysDictionaryCategoryVo();
            BeanUtils.copyProperties(sysDictionaryCategory, sysDictionaryCategoryVo);
            sysDictionaryCategoryVoList.add(sysDictionaryCategoryVo);
        });

        List<SysDictionaryCategoryVo> sysOrganizationList =
                sysDictionaryCategoryVoList.stream().
                        filter(sysDictionaryCategoryVo -> sysDictionaryCategoryVo.getFkParentId() == null
                                || sysDictionaryCategoryVo.getFkParentId() == 0)
                        .collect(Collectors.toList());

        loop(sysDictionaryCategoryVoList, sysOrganizationList);

        return sysOrganizationList;

    }

    private void loop(List<SysDictionaryCategoryVo> listVo, List<SysDictionaryCategoryVo> sysOrganizationList) {
        sysOrganizationList.forEach(sysDictionaryCategoryVo -> {
            Integer id = sysDictionaryCategoryVo.getfId();
            List<SysDictionaryCategoryVo> listChild = listVo
                    .stream()
                    .filter(organizationVo ->
                            id.equals(organizationVo.getFkParentId()))
                    .collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(listChild)) {
                sysDictionaryCategoryVo.setChildNodeList(listChild);
                loop(listVo, listChild);
            }
        });
    }

    public void addDictionary(SysDictionaryBo sysDictionaryBo){
        if (sysDictionaryBo == null) {
            throw new BaseException("缺少参数", 500);
        }
        SysDictionary sysDictionary = new SysDictionary();
        BeanUtils.copyProperties(sysDictionaryBo, sysDictionary);
        sysDictionaryService.save(sysDictionary);
    }

}
