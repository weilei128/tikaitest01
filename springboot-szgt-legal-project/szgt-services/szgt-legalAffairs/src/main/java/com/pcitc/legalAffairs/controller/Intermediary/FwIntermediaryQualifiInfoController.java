package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryQualifiInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryQualifiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fwIntermediaryQualifiInfo")
public class FwIntermediaryQualifiInfoController  {
    @Autowired
    private IFwIntermediaryQualifiInfoService qualifiInfoService;
    /**
     * 根据资质信息主键查询资质信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return qualifiInfoService.queryById(fId);
    }

    /**
     * 根据关联中介机构外键查询资质信息列表
     * @param fkIntermediaryId
     * @return
     */
    @PostMapping("queryListByIntermId")
    public  Result queryListByIntermId(@RequestParam Long fkIntermediaryId){
        return qualifiInfoService.queryListByIntermId(fkIntermediaryId);
    }

    /**
     * 单个保存中介机构资质信息
     * @param qualifiInfoBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryQualifiInfoBo qualifiInfoBo){
        return qualifiInfoService.save(qualifiInfoBo);
    }

    /**
     * 根据主键更新中介机构资质信息
     * @param qualifiInfoBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryQualifiInfoBo qualifiInfoBo){
        return qualifiInfoService.update(qualifiInfoBo);
    }

    /**
     * 根据主键删除中介机构资质信息
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long fId){
        return qualifiInfoService.deleteById(fId);
    }

    /**
     * 根据主键批量删除中介机构资质信息
     * @param fIds
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> fIds){
        return qualifiInfoService.deleteBatch(fIds);
    }
}
