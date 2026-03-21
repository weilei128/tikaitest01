package com.pcitc.legalAffairs.controller.Intermediary;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireInfoQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.HireInfoQuery;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireInfoService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInfoHireElectionRService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInfoHireRService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireElectionR;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryInfoHireR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("intermediaryHireInfo")
public class FwIntermediaryHireInfoController {
    @Autowired
    private IFwIntermediaryHireInfoService  hireInfoService;
    @Autowired
    private IFwIntermediaryInfoHireRService hireRService;
    @Autowired
    private IFwIntermediaryInfoHireElectionRService electionRService;
    @Autowired
    private IFwIntermediaryOrgBasicService orgService;
    /**
     * 单个保存中介机构聘用信息
     * @param hireInfoBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryHireInfoBo hireInfoBo){
        return  hireInfoService.save(hireInfoBo);
    }

    /**
     * 单个修改中介机构聘用信息
     * @param hireInfoBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryHireInfoBo hireInfoBo){
        return hireInfoService.update(hireInfoBo);
    }

    /**
     * 根据主键查询中介机构聘用信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return hireInfoService.queryById(fId);
    }

    /**
     * 根据主键查询中介机构聘用信息(包含法律团队信息和中介机构信息）
     * @param fId
     * @return
     */
    @PostMapping("queryAllById")
    public Result queryAllById(@RequestParam Long fId){
        return hireInfoService.queryAllById(fId);
    }

    /**
     * 多条件分页查询中介机构聘用信息
     * @param hireInfoQueryBo
     * @return
     */
    @PostMapping("queryPageByConditins")
    public Result queryPageByConditins(@RequestBody FwIntermediaryHireInfoQueryBo hireInfoQueryBo){
        return  hireInfoService.queryPageByConditins(hireInfoQueryBo);
    }

    /**
     * 根据主键删除中介机构聘用信息
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result delete(@RequestParam Long fId){
        return hireInfoService.delete(fId);
    }
    /**
     * 根据主键删除中介机构聘用信息(包括聘用法律团队信息）
     * @param fId
     * @return
     */
    @PostMapping("deleteAllById")
    public Result deleteAllById(@RequestParam Long fId){
        return  hireInfoService.deleteAllById(fId);
    }

    /**
     * 批量保存中介聘用信息
     * @param entities
     * @return
     */
    @PostMapping("saveIntermediaryHireInfo")
    public Result saveIntermediaryHire(@RequestBody List<FwIntermediaryInfoHireR> entities) {
        return hireRService.saveEntityList(entities);
    }

    /**
     * 批量删除中介聘用信息
     * @param ids
     * @return
     */
    @PostMapping("deleteIntermediaryHireInfo")
    public Result deleteIntermediaryHire(@RequestBody List<Long> ids) {
        return hireRService.deleteList(ids);
    }

    /**
     * 由聘用信息获取受聘中介
     * @param hireId
     * @return
     */
    @PostMapping("getIntermediaryByHireId")
    public Result getIntermediaryByHireId(@RequestParam Long hireId) {
        return orgService.queryByHireId(hireId);
    }
    
    /**
     * 批量删除中介聘用信息
     * @param ids
     * @return
     */
    @PostMapping("deleteIntermediaryHireInfoByHireAndIntermediary")
    public Result deleteIntermediaryHireByHireAndIntermediary(@RequestParam(required = false) Long hireId, @RequestParam(required = false) Long intermediaryId) {
        return hireRService.deleteByHireAndIntermediary(hireId, intermediaryId);
    }
    
    /**
     * 批量保存中介候选信息
     * @param entities
     * @return
     */
    @PostMapping("saveIntermediaryElectionInfo")
    public Result saveIntermediaryElection(@RequestBody List<FwIntermediaryInfoHireElectionR> entities) {
    	return electionRService.saveEntityList(entities);
    }
    
    /**
     * 批量删除中介聘用信息
     * @param ids
     * @return
     */
    @PostMapping("deleteIntermediaryElectionInfo")
    public Result deleteIntermediaryElection(@RequestBody List<Long> ids) {
    	return electionRService.deleteList(ids);
    }
    
    /**
     * 由聘用信息获取受聘中介
     * @param hireId
     * @return
     */
    @PostMapping("getIntermediaryByElectionId")
    public Result getIntermediaryByElectionId(@RequestParam Long hireId) {
    	return orgService.queryElectionsByHireId(hireId);
    }
    
    /**
     * 批量删除中介聘用信息
     * @param ids
     * @return
     */
    @PostMapping("deleteIntermediaryElectionInfoByHireAndIntermediary")
    public Result deleteIntermediaryElectionByHireAndIntermediary(@RequestParam(required = false) Long hireId, @RequestParam(required = false) Long intermediaryId) {
    	return electionRService.deleteByHireAndIntermediary(hireId, intermediaryId);
    }
    
    /**
     * 查询中介聘用信息
     * @param ids
     * @return
     */
    @PostMapping("queryHireInfo")
    public Result queryHireInfo(@RequestBody HireInfoQuery bo) {
    	return Result.data(hireInfoService.queryHireInfo(bo));
    }

}
