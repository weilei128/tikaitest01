package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryBankInfoBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fwIntermediaryBankInfo")
public class FwIntermediaryBankInfoController {
    @Autowired
    private IFwIntermediaryBankInfoService bankInfoService;

    /**
     * 根据主键查询银行信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return bankInfoService.queryById(fId);
    }

    /**
     * 根据关联中介机构外键查询银行信息
     * @param fkIntermediaryId
     * @return
     */
    @PostMapping("queryListByIntermId")
    public  Result queryListByIntermId(@RequestParam Long fkIntermediaryId){
        return bankInfoService.queryListByIntermId(fkIntermediaryId);
    }

    /**
     * 单个保存银行信息
     * @param bankInfoBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryBankInfoBo bankInfoBo){
        return bankInfoService.save(bankInfoBo);
    }

    /**
     * 根据主键更新银行信息
     * @param bankInfoBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryBankInfoBo bankInfoBo){
        return bankInfoService.update(bankInfoBo);
    }

    /**
     * 根据主键删除银行信息
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long fId){
        return bankInfoService.deleteById(fId);
    }

    /**
     * 根据主键批量删除银行信息
     * @param fIds
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> fIds){
        return bankInfoService.deleteBatch(fIds);
    }
}
