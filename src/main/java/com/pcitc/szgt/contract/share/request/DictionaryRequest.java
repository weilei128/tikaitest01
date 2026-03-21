package com.pcitc.szgt.contract.share.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.share.entity.SysDictionary;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.model.DictionaryCategoryTree;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictionaryRequest {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private ShareConfig shareConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 	根据id查询分类信息树
     * @return
     */
    private DictionaryCategoryTree queryDictionaryCategoryTree(Integer categoryId){
        List<Integer> params = new ArrayList<>();
        params.add(categoryId);

        String result = restTemplateUtil.postJsonRequest(
                "http://" + shareConfig + "/dictionary/queryDictionaryCategoryByIds", params, null);

        List<DictionaryCategoryTree> tree = null;
        try {
            DataResult<List<DictionaryCategoryTree>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<DictionaryCategoryTree>>>(){});
            tree = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(!CollectionUtils.isEmpty(tree)){
            return tree.get(0);
        }

        return null;
    }

    /**
     * 按code查树，层序遍历
     * @param code
     * @return
     */
    public List<SysDictionarycategory> queryAllSubCategorys(String code){
        DictionaryCategoryTree roottree = queryDictionaryCategoryTreeByCode(code);
        List<SysDictionarycategory> cats = new ArrayList<>();
        if (roottree == null){
            return cats;
        }

        Queue<DictionaryCategoryTree> treeQueue = new LinkedList<>();
        treeQueue.offer(roottree);
        while(!treeQueue.isEmpty()){
            DictionaryCategoryTree remove = treeQueue.remove();
            SysDictionarycategory dictionarycategory = treeToCategory(remove);
            cats.add(dictionarycategory);

            if(!CollectionUtils.isEmpty(remove.getChildNodeList())){
                for(DictionaryCategoryTree tree:remove.getChildNodeList()){
                    treeQueue.offer(tree);
                }
            }
        }

        return cats;

    }

    /**
     * 按code查树，层序遍历, 转成Map
     * @return
     */
    public Map<Integer, SysDictionarycategory> queryAllSubCategoryToMap(String code){
        List<SysDictionarycategory> hetongDicts = queryAllSubCategorys(code);
        return hetongDicts.stream().collect(Collectors.toMap(SysDictionarycategory::getfId, Function.identity(), (k1, k2) -> k1));
    }

    private SysDictionarycategory treeToCategory(DictionaryCategoryTree tree){
        SysDictionarycategory cat = new SysDictionarycategory();
        BeanUtils.copyProperties(tree, cat);
        return cat;
    }


    /**
     * 按id查询下一级分类
     * @return
     */
    public List<SysDictionarycategory> querySubCategorys(Integer categoryId){
        DictionaryCategoryTree category = queryDictionaryCategoryTree(categoryId);
        List<DictionaryCategoryTree> childNodeList = category.getChildNodeList();

        List<SysDictionarycategory> cats = new ArrayList<>();
        for(DictionaryCategoryTree tree:childNodeList){
            SysDictionarycategory cat = new SysDictionarycategory();
            BeanUtils.copyProperties(tree, cat);
            cats.add(cat);
        }

        return cats;
    }

    /**
     * 按id查询分类信息
     * @param categoryId
     * @return
     */
    public SysDictionarycategory queryCategoryById(Integer categoryId){
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);

        String result = restTemplateUtil.postFormRequest(
                "http://" + shareConfig + "/dictionary/queryDictionaryCategoryById", params, null);

        DictionaryCategoryTree tree = null;
        try {
            DataResult<DictionaryCategoryTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<DictionaryCategoryTree>>(){});
            tree = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        SysDictionarycategory dictionarycategory = new SysDictionarycategory();
        if(tree != null){
            BeanUtils.copyProperties(tree, dictionarycategory);
        }

        return dictionarycategory;
    }

    /**
     * 查询分类下的字典
     * @param dictionaryCategoryId 字典分类id
     * @return
     */
    public List<SysDictionary> queryDictionaryByCid(String dictionaryCategoryId){
        Map<String, Object> params = new HashMap<>();
        params.put("dictionaryCategoryId", dictionaryCategoryId);
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dictionary/queryDictionary", params);

        try {
            DataResult<List<SysDictionary>> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<SysDictionary>>>() {});
            return dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 根据字典id查字典信息
     * @param dictionaryId
     * @return
     */
    public SysDictionary queryDictionary(String dictionaryId){
        Map<String, Object> params = new HashMap<>();
        params.put("dictionaryId", dictionaryId);

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dictionary/queryDictionaryById?dictionaryId={dictionaryId}", params);

        try {
            DataResult<SysDictionary> o = objectMapper.readValue(result, new TypeReference<DataResult<SysDictionary>>() {
            });
            return o.getData();
        }catch (IOException e){
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 根据code查询子分类
     * @return
     */
    public List<SysDictionarycategory> querySubCategoryByCode(String code){
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dictionary/queryDictionaryCategoryChildByCode", params);
        DataResult<List<DictionaryCategoryTree>> dataResult = null;
        try {
            dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<DictionaryCategoryTree>>>() {});
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        List<SysDictionarycategory> cats = new ArrayList<>();
        for(DictionaryCategoryTree tree:dataResult.getData()){
            SysDictionarycategory cat = new SysDictionarycategory();
            BeanUtils.copyProperties(tree, cat);
            cats.add(cat);
        }

        return cats;
    }

    /**
     * 根据code查询分类信息
     * @return
     */
    public SysDictionarycategory queryCategotyByCode(String code){
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dictionary/queryDictionaryCategoryByCode", params);
        DataResult<DictionaryCategoryTree> dataResult = null;
        try {
            dataResult = objectMapper.readValue(result, new TypeReference<DataResult<DictionaryCategoryTree>>() {});
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        SysDictionarycategory cat = new SysDictionarycategory();
        if(dataResult.getData() != null){
            BeanUtils.copyProperties(dataResult.getData(), cat);
        }
        return cat;
    }

    /**
     * 通过code查询子字典
     * @return
     */
    public List<SysDictionarycategory> queryCategoryByCodeUsingLike(String code){
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("type", "2");

        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/dictionary/queryDictionaryCategoryByLikeCode/{code}/{type}", params);
        DataResult<List<DictionaryCategoryTree>> dataResult = null;

        try {
            dataResult = objectMapper.readValue(result, new TypeReference<DataResult<List<DictionaryCategoryTree>>>() {});
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        List<SysDictionarycategory> resList = new ArrayList<>();
        if(CollectionUtils.isEmpty(dataResult.getData())){
            return resList;
        }

        for(DictionaryCategoryTree tree : dataResult.getData()){
            SysDictionarycategory cat = new SysDictionarycategory();
            BeanUtils.copyProperties(tree, cat);
            resList.add(cat);
        }

        return resList;
    }

    /**
     * 根据code查字典分类树
     * @param code
     * @return
     */
    public DictionaryCategoryTree queryDictionaryCategoryTreeByCode(String code){
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        String result = restTemplateUtil.postFormRequest("http://" + shareConfig + "/dictionary/queryDictionaryCategoryChild", params);

        DictionaryCategoryTree tree = null;
        try {
            DataResult<DictionaryCategoryTree> dataResult = objectMapper.readValue(result, new TypeReference<DataResult<DictionaryCategoryTree>>(){});
            tree = dataResult.getData();
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }

        if(tree == null){
            tree = new DictionaryCategoryTree();
        }

        return tree;

    }

}
