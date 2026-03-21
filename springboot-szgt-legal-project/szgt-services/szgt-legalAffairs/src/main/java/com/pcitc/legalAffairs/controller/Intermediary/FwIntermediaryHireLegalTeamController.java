package com.pcitc.legalAffairs.controller.Intermediary;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryHireLegalTeamBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireLegalTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hireLegalTeam")
public class FwIntermediaryHireLegalTeamController {
    @Autowired
    private IFwIntermediaryHireLegalTeamService hireLegalTeamService;

    /**
     * 单个保存聘用法律团队信息
     * @param hireLegalTeamBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryHireLegalTeamBo hireLegalTeamBo){
        return hireLegalTeamService.save(hireLegalTeamBo);
    }

    /**
     * 根据主键更新聘用法律团队信息
     * @param hireLegalTeamBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryHireLegalTeamBo hireLegalTeamBo){
        return hireLegalTeamService.update(hireLegalTeamBo);
    }

    /**
     * 根据主键查询聘用法律团队信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return hireLegalTeamService.queryById(fId);
    }

    /**
     * 根据中介机构信息外键查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @PostMapping("queryListByOrgBasicId")
    public Result queryListByOrgBasicId(@RequestParam Long fkOrgBasicId, @RequestParam(required = false) Integer fType){
        return hireLegalTeamService.queryListByOrgBasicId(fkOrgBasicId, fType);
    }
    
    /**
     * 根据中介机构信息和聘用信息查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @PostMapping("queryListByOrgBasicIdAndHireId")
    public Result queryListByOrgBasicIdAndHireId(@RequestParam Long fkOrgBasicId, @RequestParam Long fkHireId, @RequestParam(required = false) Integer fType){
    	return hireLegalTeamService.queryListByOrgBasicIdAndHireId(fkOrgBasicId, fkHireId, fType);
    }
    
    /**
     * 根据中介机构信息和聘用信息查询聘用法律团队信息列表
     * @param fkOrgBasicId
     * @return
     */
    @PostMapping("queryListByHireId")
    public Result queryListByHireId(@RequestParam Long fkHireId, @RequestParam(required = false) Integer fType){
    	return hireLegalTeamService.queryListByHireId(fkHireId, fType);
    }

    /**
     * 根据主键删除聘用法律团队信息
     * @param fId
     * @return
     */
    @PostMapping("delete")
    public Result delete(@RequestParam Long fId){
        return hireLegalTeamService.deleteById(fId);
    }

    /**
     * 根据主键批量删除聘用法律团队信息
     * @param fIds
     * @return
     */
    @PostMapping("deleteBatch")
    public  Result deleteBatch(@RequestBody List<Long> fIds){
        return hireLegalTeamService.deleteBatch(fIds);
    }
}
