package com.pcitc.legalAffairs.controller.Intermediary;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicBo;
import com.pcitc.legalAffairs.bo.Intermediary.FwIntermediaryOrgBasicQueryBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryOperateBo;
import com.pcitc.legalAffairs.bo.Intermediary.IntermediaryQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgLogService;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryOrgBasicVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fwIntermediaryOrgBasic")
public class FwIntermediaryOrgBasicController {
    @Autowired
    private IFwIntermediaryOrgBasicService orgBasicService;
    @Autowired
    private IFwIntermediaryOrgLogService ologService;

    /**
     * 根据主键查询中介机构基本信息
     * @param fId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam Long fId){
        return orgBasicService.queryById(fId);
    }

    /**
     * 根据关联聘用信息主键查询中介机构信息列表(聘用时添加中介机构自动关联）
     * @param fkHireInfoId
     * @return
     */
    @PostMapping("queryListHireInfoId")
    public Result queryListHireInfoId(@RequestParam Long fkHireInfoId){
        return orgBasicService.queryListHireInfoId(fkHireInfoId);
    }

    /**
     * 根据主键查询中介机构所有信息
     * @param fId
     * @return
     */
    @PostMapping("queryAllById")
    public Result queryAllById(@RequestParam Long fId){
        return orgBasicService.queryAllById(fId);
    }

    /**
     * 多条件分页查询中介机构所有信息
     * @param orgBasicQueryBo
     * @return
     */
    @PostMapping("queryPageByConditions")
    public  Result queryPageByConditions(@RequestBody FwIntermediaryOrgBasicQueryBo orgBasicQueryBo){
        return orgBasicService.queryPageByConditions(orgBasicQueryBo);
    }

    /**
     * 单个保存中介机构基本信息
     * @param orgBasicBo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FwIntermediaryOrgBasicBo orgBasicBo){
        return orgBasicService.save(orgBasicBo);
    }

    /**
     * 单个保存中介机构基本信息并返回主键
     * @param orgBasicBo
     * @return
     */
    @PostMapping("saveReturnId")
    public Result saveReturnId(@RequestBody FwIntermediaryOrgBasicBo orgBasicBo){
        return orgBasicService.saveReturnId(orgBasicBo);
    }

    /**
     * 根据主键更新中介机构基本信息
     * @param orgBasicBo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody FwIntermediaryOrgBasicBo orgBasicBo){
        return orgBasicService.update(orgBasicBo);
    }

    /**
     * 根据主键删除中介机构所有信息
     * @param fId
     * @return
     */
    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long fId){
        return orgBasicService.deleteById(fId);
    }

    /**
     * 启用
     * @param bo
     * @return
     */
    @PostMapping("enable")
    public Result enable(@RequestBody IntermediaryOperateBo bo) {
        orgBasicService.enable(bo);
        return Result.status(true);
    }

    /**
     * 禁用
     * 
     * @param bo
     * @return
     */
    @PostMapping("disable")
    public Result disable(@RequestBody IntermediaryOperateBo bo) {
        orgBasicService.disable(bo);
        return Result.status(true);
    }

    /**
     * 解除准入
     * 
     * @param bo
     * @return
     */
    @PostMapping("cancelAdmit")
    public Result cancelAdmit(@RequestBody IntermediaryOperateBo bo) {
        orgBasicService.cancelAdmit(bo);
        return Result.status(true);
    }
    
    /**
     * 通过统一社会信用代码解除准入
     * 
     * @param bo
     * @return
     */
    @PostMapping("cancelAdmitByUscCode")
    public Result cancelAdmitByUscCode(@RequestBody IntermediaryOperateBo bo) {
    	orgBasicService.cancelAdmitByUscCode(bo);
    	return Result.status(true);
    }
    
    @PostMapping("getOperateLog")
    public Result getOperateLog(@RequestParam String id) {
    	return ologService.getOperateLog(id);
    }
    
    @PostMapping("getIntermediaryRepo")
    public Result getIntermediaryRepo(@RequestBody IntermediaryQueryBo bo) {
    	IPage<FwIntermediaryOrgBasicVo> page = orgBasicService.pageIntermediary(bo);
    	return Result.data(page);
    }
}
