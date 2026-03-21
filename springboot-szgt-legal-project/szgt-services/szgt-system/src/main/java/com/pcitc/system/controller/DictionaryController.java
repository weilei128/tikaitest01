package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.system.bo.SysDictionaryBo;
import com.pcitc.system.bo.SysDictionaryCategoryBo;
import com.pcitc.system.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @description 字典管理接口
 * @author leigang
 * @date 2020年2月18日 14:44:45
 *
 */


@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    private DictionaryService dictionaryService;

    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @PostMapping("queryDictionary")
    @ApiOperation("查询字典的项目")
    public Result queryDictionary(@RequestParam String dictionaryCategoryId) {
        return Result.data(dictionaryService.queryDictionary(dictionaryCategoryId));
    }

    @PostMapping("deleteDictionary")
    @ApiOperation("删除字典项目")
    public Result deleteDictionary(@RequestParam String dictionaryId) {
        dictionaryService.deleteDictionary(dictionaryId);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("updateDictionary")
    @ApiOperation("更新字典")
    public Result updateDictionary(@RequestBody SysDictionaryBo sysDictionaryBo) {
        dictionaryService.updateDictionary(sysDictionaryBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("addDictionaryCategory")
    @ApiOperation("添加字典分类")
    public Result addDictionaryCategory(@RequestBody SysDictionaryCategoryBo sysDictionaryCategoryBo) {
        dictionaryService.addDictionaryCategory(sysDictionaryCategoryBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("addDictionary")
    @ApiOperation("添加字典项分类")
    public Result addDictionary(@RequestBody SysDictionaryBo sysDictionaryBo) {
        dictionaryService.addDictionary(sysDictionaryBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("queryDictionaryCategory")
    @ApiOperation("查询字典分类")
    public Result queryDictionaryCategory() {
        return Result.data(dictionaryService.queryDictionaryCategory());
    }


}
