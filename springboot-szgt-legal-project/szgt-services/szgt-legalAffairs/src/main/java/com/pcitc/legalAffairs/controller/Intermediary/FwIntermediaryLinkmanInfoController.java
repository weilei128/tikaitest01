package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryLinkmanInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryLinkmanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fwIntermediaryLinkmanInfo")
public class FwIntermediaryLinkmanInfoController {
    @Autowired
    private IFwIntermediaryLinkmanInfoService linkmanInfoService;

    /**
     * 根据主键查询中介联系人信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return linkmanInfoService.queryById(fId);
    }

    /**
     * 根据关联中介机构外键查询中介联系人信息
     * @param fkIntermediaryId
     * @return
     */
    @PostMapping("queryListByIntermId")
    public  Result queryListByIntermId(@RequestParam Long fkIntermediaryId){
        return linkmanInfoService.queryListByIntermId(fkIntermediaryId);
    }

    /**
     * 单个保存中介联系人信息
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryLinkmanInfoBo linkmanInfoBo){
        return linkmanInfoService.save(linkmanInfoBo);
    }

    /**
     * 根据主键更新中介联系人信息
     * @param linkmanInfoBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryLinkmanInfoBo linkmanInfoBo){
        return linkmanInfoService.update(linkmanInfoBo);
    }

    /**
     * 根据主键删除中介联系人信息
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long fId){
        return linkmanInfoService.deleteById(fId);
    }

    /**
     * 根据主键批量删除中介联系人信息
     * @param fIds
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> fIds){
        return linkmanInfoService.deleteBatch(fIds);
    }
}
