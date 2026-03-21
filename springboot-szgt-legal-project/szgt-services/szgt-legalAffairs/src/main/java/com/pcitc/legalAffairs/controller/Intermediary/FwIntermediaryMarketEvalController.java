package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryMarketEvalBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryMarketEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fwIntermediaryMarketEval")
public class FwIntermediaryMarketEvalController {
    @Autowired
    private IFwIntermediaryMarketEvalService marketEvalService;

    /**
     * 根据主键查询市场评价信息
     *
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId) {
        return marketEvalService.queryById(fId);
    }

    /**
     * 根据关联中介机构外键查询市场评价信息列表
     *
     * @param fkIntermediaryId
     * @return
     */
    @PostMapping("queryListByIntermId")
    public Result queryListByIntermId(@RequestParam Long fkIntermediaryId) {
        return marketEvalService.queryListByIntermId(fkIntermediaryId);
    }

    /**
     * 单个保存市场评价信息
     *
     * @param marketEvalBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryMarketEvalBo marketEvalBo) {
        return marketEvalService.save(marketEvalBo);
    }

    /**
     * 根据主键更新市场评价信息
     *
     * @param marketEvalBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryMarketEvalBo marketEvalBo) {
        return marketEvalService.update(marketEvalBo);
    }

    /**
     * 根据主键删除市场评价信息
     *
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long fId) {

        return marketEvalService.deleteById(fId);
    }

    /**
     * 根据主键批量删除市场评价信息
     *
     * @param fIds
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> fIds) {
        return marketEvalService.deleteBatch(fIds);
    }
}
