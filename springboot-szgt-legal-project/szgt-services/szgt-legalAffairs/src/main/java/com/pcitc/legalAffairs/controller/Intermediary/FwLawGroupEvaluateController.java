package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwLawGroupEvaluateQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwLawGroupEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lawGroupEvaluate")
public class FwLawGroupEvaluateController {
    @Autowired
    private IFwLawGroupEvaluateService lawGroupEvaluateService;

    /**
     * 根据主键查询法律团队考评信息
     *
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId) {
        return lawGroupEvaluateService.queryById(fId);
    }

    /**
     * 根据关联法律团队主键查询考评信息列表
     *
     * @param fkLegalTeamId
     * @return
     */
    @PostMapping("queryListByLegalTeamId")
    public Result queryListByLegalTeamId(@RequestParam Long fkLegalTeamId) {
        return lawGroupEvaluateService.queryListByLegalTeamId(fkLegalTeamId);
    }

    /**
     * 多条件分页查询律师考评信息
     * @param groupEvaluateQueryBo
     * @return
     */
    @PostMapping("queryPageByConditions")
    public Result queryPageByConditions(@RequestBody FwLawGroupEvaluateQueryBo groupEvaluateQueryBo) {
        return lawGroupEvaluateService.queryPageByConditions(groupEvaluateQueryBo);
    }

    /**
     * 单个保存律师考评信息
     * @param lawGroupEvaluateBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwLawGroupEvaluateBo lawGroupEvaluateBo){
    return lawGroupEvaluateService.save(lawGroupEvaluateBo);
    }

    /**
     * 根据主键修改律师考评信息
     * @param lawGroupEvaluateBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwLawGroupEvaluateBo lawGroupEvaluateBo){
        return lawGroupEvaluateService.update(lawGroupEvaluateBo);
    }


    /**
     * 根据主键删除律师考评信息
     * @param fId
     * @return
     */
    @PostMapping("delete")
    public Result delete(@RequestParam Long fId){
        return lawGroupEvaluateService.delete(fId);
    }

}
