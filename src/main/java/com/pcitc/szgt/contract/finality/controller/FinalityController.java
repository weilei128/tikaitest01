package com.pcitc.szgt.contract.finality.controller;

import java.math.BigDecimal;

import com.pcitc.szgt.contract.finality.model.ContractFromLegal;
import com.pcitc.szgt.contract.finality.model.ContractToLegal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.finality.model.ContractCaseData;
import com.pcitc.szgt.contract.finality.service.IFinalityService;
import com.pcitc.szgt.contract.perform.entityEx.ContractQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "finality" , tags = "合同终结管理")
@RestController
@RequestMapping("/finality")
public class FinalityController {
	
    @Autowired
    private IFinalityService finalityService;


    @ApiOperation(value = "queryContractIncidence" , notes = "待发案/已发案合同查询")
    @GetMapping(value = "/queryContractIncidence")
    public DataResult<?> queryContractIncidence(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                             @RequestParam(required = false) String type1, @RequestParam(required = false) String beginCreateTime,
                                             @RequestParam(required = false) String endCreateTime, @RequestParam(required = true) String dataType,
                                             @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return finalityService.queryContractIncidence(ruleserialNum, contractName, type1, beginCreateTime,
                endCreateTime, dataType, pageSize, pageNum);
    }


    @ApiOperation(value = "contractIncidence" , notes = "合同发案")
    @PostMapping(value = "/contractIncidence")
    public DataResult<?> contractIncidence(@RequestParam(required = true) String contractId, @RequestParam(required = true) String caseTime,
                                           @RequestParam(required = true) BigDecimal amout, @RequestParam(required = true) String reason) {
        return DataResult.success(finalityService.contractIncidence(contractId, caseTime, amout, reason));
    }


    @ApiOperation(value = "contractFromLegal" , notes = "合同发案从合同系统推送至法务系统")
    @PostMapping(value = "/contractFromLegal")
    public DataResult<?> contractFromLegal(@RequestBody ContractFromLegal contractFromLegal) {
        return finalityService.contractFromLegal(contractFromLegal);
    }


    @ApiOperation(value = "getContractIncidence" , notes = "发案合同明细查看")
    @ApiImplicitParams({@ApiImplicitParam(name = "contractId", value = "合同ID")})
    @GetMapping(value = "/getContractIncidence")
    public DataResult<?> getContractIncidence(@RequestParam(required = true) String contractId) {
        return finalityService.getContractIncidence(contractId);
    }

    @ApiOperation(value = "cancelContractIncidence" , notes = "发案合同取消")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "发案ID"),
            @ApiImplicitParam(name = "reamrk", value = "纠纷处理结果")
    })
    @PostMapping(value = "/cancelContractIncidence")
    public DataResult<?> cancelContractIncidence(@RequestParam(required = true) String caseId, 
    											 @RequestParam(required = true) String reamrk) {
        return DataResult.success(finalityService.cancelContractIncidence(caseId, reamrk));
    }

    @ApiOperation(value = "conractComplete" , notes = "合同履行完毕")
    @PostMapping(value = "/conractComplete")
    public DataResult<?> conractComplete(@RequestParam(required = true) String contractId, @RequestParam(required = true) String taskId) {
        return DataResult.success(finalityService.conractComplete(contractId, taskId));
    }

    
    @ApiOperation(value = "contractRotation" , notes = "履行回转")
    @PostMapping(value = "/contractRotation")
    public DataResult<?> contractRotation(@RequestParam(required = true) String contractId,
                                          @RequestParam(required = false) String messageId, 
                                          @RequestParam(required = false) String taskId) {
        return DataResult.success(finalityService.contractRotation(contractId, messageId, taskId));
    }


    @ApiOperation(value = "/contractTreatment" ,notes = "合同终结提交/保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同ID"),
            @ApiImplicitParam(name = "endId", value = "终结记录ID"),
            @ApiImplicitParam(name = "isNormal", value = "合同终结类型 正常 异常"),
            @ApiImplicitParam(name = "endDescripition", value = "合同完结情况说明"),
            @ApiImplicitParam(name = "description", value = "相对人异常履约情况说明"),
            @ApiImplicitParam(name = "performApprise", value = "履行评价ID"),
            @ApiImplicitParam(name = "performAppriseText", value = "履行评价中文"),
            @ApiImplicitParam(name = "appraiseContent", value = "相对人评价"),
            @ApiImplicitParam(name = "isSumbit", value = "是否提交 保存/提交")
    })
    @PostMapping(value = "/contractTreatment")
    public DataResult<?> contractTreatment(@RequestParam(required = true) String contractId, @RequestParam(required = false) String endId,
                                           @RequestParam(required = false) Integer isNormal, @RequestParam(required = false) String endDescripition,
                                           @RequestParam(required = false) String description, @RequestParam(required = false) Integer performApprise,
                                           @RequestParam(required = false) String performAppriseText, @RequestParam(required = false) String appraiseContent,
                                           @RequestParam(required = false) String taskId, @RequestParam(required = true) boolean isSubmit) {
        return DataResult.success(finalityService.contractTreatment(contractId, endId, isNormal, endDescripition, description, performApprise,
                performAppriseText, appraiseContent, taskId, isSubmit));
    }

    @ApiOperation(value = "getcontractTreatment" , notes = "合同终结明细查看")
    @ApiImplicitParams({@ApiImplicitParam(name = "endId", value = "终结记录ID")})
    @GetMapping(value = "/getcontractTreatment")
    public DataResult<?> getcontractTreatment(@RequestParam(required = true) String endId) {
        return finalityService.getcontractTreatment(endId);
    }

    @ApiOperation(value = "queryContractFinality" , notes = "终结合同查询")
    @PostMapping(value = "/queryContractFinality")
    public DataResult<?> queryContractFinality(@RequestBody ContractQuery contractQuery) {
        return finalityService.queryContractFinality(contractQuery);
    }


    @ApiOperation(value = "queryContractElectFiling" , notes = "电子归档合同查询")
    @PostMapping(value = "/queryContractElectFiling")
    public DataResult<?> queryContractElectFiling(@RequestBody ContractQuery contractQuery) {
        return finalityService.queryContractElectFiling(contractQuery);
    }

    @ApiOperation(value = "queryDiscardContract" , notes = "废弃合同查询")
    @PostMapping(value = "/queryDiscardContract")
    public DataResult<?> queryDiscardContract(@RequestBody ContractQuery contractQuery) {
        return finalityService.queryDiscardContract(contractQuery);
    }
   
    @ApiOperation(value = "queryContractCaseLoseSum" , notes = "合同案件损失金额")
    @PostMapping(value = "/queryContractCaseLoseSum")
    public DataResult<?> queryContractCaseLoseSum(@RequestBody ContractCaseData caseData){
    	return finalityService.queryContractCaseLoseSum(caseData);
    }
    
    @ApiOperation(value = "queryContractBackdateRate" , notes = "合同倒签率")
    @PostMapping(value = "/queryContractBackdateRate")
    public DataResult<?> queryContractBackdateRate(@RequestBody ContractCaseData caseData){
    	return finalityService.queryContractBackdateRate(caseData);
    }

}
