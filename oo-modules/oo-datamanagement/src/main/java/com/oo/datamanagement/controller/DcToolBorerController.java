package com.oo.datamanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.controller.BaseController;
import com.oo.common.core.web.domain.AjaxResult;
import com.oo.datamanagement.domain.DcToolBorer;
import com.oo.datamanagement.mapper.DcToolBorerMapper;
import com.oo.datamanagement.service.IDcToolBorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 钻机库
 *
 * @author
 */
@RestController
@RequestMapping("/dctoolborer")
public class DcToolBorerController  extends BaseController {
    @Autowired
    private DcToolBorerMapper dcToolBorerMapper;
    @Autowired
    private IDcToolBorerService iDcToolBorerService;


    /**
     * 根据id获取钻机库详细信息
     */
    @GetMapping(value = "/selectDcToolBorer")
    public AjaxResult getInfo(@RequestParam String Id)
    {
        if(StringUtils.isEmpty(Id)){
            return AjaxResult.error("Id不能为空");
        }
        return AjaxResult.success(dcToolBorerMapper.selectById(Id));
    }
    /**
     * 获取钻机库表格（没有查询条件）
     */
    @GetMapping(value = "/selectDcToolBorerQuery")
    public AjaxResult selectDcMdWellQuery(String name,String type,String factory)
    {
        startPage();
        QueryWrapper<DcToolBorer> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.isNotEmpty(type)){
            queryWrapper.eq("type",type);
        }
        if(StringUtils.isNotEmpty(factory)){
            queryWrapper.like("factory",factory);
        }
        return AjaxResult.success(dcToolBorerMapper.selectList(queryWrapper));
    }
    /**
     * 根据id删除钻机库详细信息
     */
    @DeleteMapping(value = "/delete")
    public AjaxResult deleteDcMdWell(@RequestParam String Ids)
    {
        String[] Id = Ids.split(",");
        List<String> list = new ArrayList(Arrays.asList(Id));
        for (String a : list) {
            if (a == null || StringUtils.isEmpty(a)) {
                return AjaxResult.error("id不能为空");
            }
        }
        return AjaxResult.success(dcToolBorerMapper.deleteBatchIds(list));
    }
    /**
     * 根据id更新、新增钻机库详细信息
     */
    @PostMapping(value = "/saveOrUpdateDcToolBorer")
    public AjaxResult saveOrUpdateDcMdWell(@RequestBody DcToolBorer dcToolBorer)
    {
//        if(dcToolBorer==null||StringUtils.isEmpty(dcToolBorer.getId())){
//            return AjaxResult.error("不能为空");
//        }
        return AjaxResult.success(iDcToolBorerService.saveOrUpdate(dcToolBorer));
    }




}
