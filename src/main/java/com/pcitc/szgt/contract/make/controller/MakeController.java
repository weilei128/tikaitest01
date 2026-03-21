package com.pcitc.szgt.contract.make.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.make.modelEx.BidItem;
import com.pcitc.szgt.contract.make.modelEx.ContractOfferee;
import com.pcitc.szgt.contract.make.modelEx.OffereeSaveVo;
import com.pcitc.szgt.contract.make.modelEx.PrepareContract;
import com.pcitc.szgt.contract.make.modelEx.StdTextUseRateVo;
import com.pcitc.szgt.contract.make.service.IMakeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @since 2020-02-20
 */
@Api(value = "/make/prepare" , tags = "合同起草订立管理")
@RestController
@RequestMapping("/make/prepare")
public class MakeController {
    /**
     * 	合同准备
     *  20190423
     */
    @Autowired
    private IMakeService makeService;


    @ApiOperation(value="getMainDept" , notes = "根据当前用户获取主办部门")
    @GetMapping(value = "/getMainDept")
    public DataResult<?> getMainDept() {
        return makeService.getMainDept();
    }

    @ApiOperation(value="getMySinBody",notes = "获取签约主体")
    @GetMapping(value = "/getMySinBody")
    public DataResult<?> getMySinBody(Integer detpId) {
        return makeService.getMySignDept(detpId);
    }


    @ApiOperation(value="contractSave" , notes = "合同保存-订立准备")
    @PostMapping(value = "/contractSave")
    public DataResult<?> contractSave(@RequestBody PrepareContract prepareContract, @RequestParam(required = true) boolean submit) {
        return DataResult.success(makeService.contractSave(prepareContract, submit));
    }
    
    @ApiOperation(value = "updatePerContractInfo",notes = "合同订立页面 保存/提交送审")
    @PostMapping(value = "/updatePerContractInfo")
    public DataResult<?> updatePerContractInfo(@RequestParam(required = true) String contractId, @RequestParam(required = false) Integer needPrintCount,
                                            @RequestParam(required = false) Integer perFormIsConfirm, @RequestParam(required = false) String perFormStartDate,
                                            @RequestParam(required = false) String perFormEndDate, @RequestParam(required = false) Integer issueSolveMode,
                                            @RequestParam(required = false) String settleDeadline, @RequestParam(required = false) String taskId,
                                            @RequestParam(required = false) String perFormNotConfirmRemark, @RequestParam(required = true) boolean isSubmit,
                                            @RequestParam(required = false) String changeRemark, @RequestParam(required = false) Integer type,
                                               @RequestParam(required = false) String mySignBodyName, @RequestParam(required = false) Integer textType,
                                               @RequestParam(required = false) Integer moneyFlow) {
        return DataResult.success(makeService.updatePerContractInfo(contractId, needPrintCount, perFormIsConfirm,
                perFormStartDate, perFormEndDate, issueSolveMode, settleDeadline, taskId, perFormNotConfirmRemark, isSubmit, changeRemark, type,mySignBodyName,textType,moneyFlow));
    }
    
    @ApiOperation(value="replenishContract",notes ="合同补录功能-订立准备方法")
    @PostMapping(value = "/replenishContract")
    public DataResult<?> replenishContract(@RequestBody PrepareContract prepareContract, @RequestParam(required = true) boolean submit){
        return DataResult.success(makeService.replenishContract(prepareContract, submit));
    }

    @ApiOperation(value="updateReplenishContract" , notes ="合同补录功能-订立页面 保存/提交 -不走审批流程")
    @PostMapping(value = "/updateReplenishContract")
    public DataResult<?> updateReplenishContract(@RequestParam(required = true) String contractId, @RequestParam(required = false) Integer needPrintCount,
                                            @RequestParam(required = false) Integer perFormIsConfirm, @RequestParam(required = false) String perFormStartDate,
                                            @RequestParam(required = false) String perFormEndDate, @RequestParam(required = false) Integer issueSolveMode,
                                            @RequestParam(required = false) String settleDeadline, @RequestParam(required = false) String taskId,
                                            @RequestParam(required = false) String perFormNotConfirmRemark, @RequestParam(required = true) boolean isSubmit,
                                            @RequestParam(required = false) String changeRemark, @RequestParam(required = false) Integer type) {
        return DataResult.success(makeService.updateReplenishContract(contractId, needPrintCount, perFormIsConfirm,
                perFormStartDate, perFormEndDate, issueSolveMode, settleDeadline, taskId, perFormNotConfirmRemark, isSubmit, changeRemark, type));
    }    

    @ApiOperation(value = "replenishList" , notes = "合同补录功能列表")
    @GetMapping(value = "/replenishList")
    public DataResult<?> getReplenishList(@RequestParam(required = false) String ruleserialNum, 
    									  @RequestParam(required = false) String contractName,
							              @RequestParam(required = false) String isFrameContract, 
							              @RequestParam(required = false) Integer pageNum, 
							              @RequestParam(required = true)  Integer pageSize){
    	
        return makeService.getReplenishContractList(ruleserialNum, contractName, isFrameContract, pageNum, pageSize);
    }


    @ApiOperation(value = "contractOffereeSave", notes = "合同相对人保存")
    @PostMapping(value = "/contractOffereeSave")
    public DataResult<Boolean> contractOffereeSave(@RequestBody OffereeSaveVo offereeSaveVo) {
        return DataResult.success(makeService.contractOffereeSave(offereeSaveVo));
    }

    @ApiOperation(value = "contractOffereeUpdate", notes = "合同相对人编辑")
    @PostMapping(value = "/contractOffereeUpdate")
    public DataResult<Boolean> contractOffereeUpdate(@RequestBody ContractOfferee contractOfferee) {
        return DataResult.success(makeService.contractOffereeUpdate(contractOfferee));
    }

    @ApiOperation(value="contractOffereeInfo", notes = "合同相对人明细查看")
    @GetMapping(value = "/contractOffereeInfo")
    public DataResult<?> contractOffereeInfo(@RequestParam(required = true) String contractId, @RequestParam(required = true) String offereeId) {
        return makeService.contractOffereeInfo(contractId, offereeId);
    }

    @ApiOperation(value = "contractOffereeDel", notes = "合同相对人删除")
    @PostMapping(value = "/contractOffereeDel")
    public DataResult<?> contractOffereeDel(@RequestParam(required = true) String contractId, @RequestParam(required = true) String offereeId) {
        return DataResult.success(makeService.contractOffereeDel(contractId, offereeId));
    }

    @ApiOperation(value="getContractOffereList" , notes = "获取合同相对人列表")
    @GetMapping(value = "/getContractOffereList")
    public DataResult<?> getContractOffereList(@RequestParam(required = true) String contractId) {
        return makeService.getContractOffereList(contractId);
    }

    @ApiOperation(value = "queryMaterial" , notes = "物料查询")
    @GetMapping(value = "/queryMaterial")
    public DataResult<?> queryMaterial(@RequestParam(required = false) String groupCode, @RequestParam(required = false) String materialCode,
                                       @RequestParam(required = false) String materialName,@RequestParam(required = true) Integer pageNum, 
                                       @RequestParam(required = true)  Integer pageSize) {
        return makeService.queryMaterial(groupCode, materialCode, materialName, pageNum, pageSize);
    }

    @ApiOperation(value="materialToBidItem" , notes = "物料转换成标的库")
    @GetMapping(value = "/materialToBidItem")
    public DataResult<?> materialToBidItem(@RequestParam(required = true) String contractId, @RequestParam(required = true) String[] materialIds) {
        return DataResult.success(makeService.materialToBidItem(contractId, materialIds));
    }

    @ApiOperation(value = "addContractMaterial", notes = "插入合同标的")
    @PostMapping(value = "/addContractMaterial")
    public DataResult<?> addContractMaterial(@RequestBody List<BidItem> bidItemList) {
        return DataResult.success(makeService.addContractMaterial(bidItemList));
    }

    @ApiOperation(value="delContractMaterial", notes = "合同物料标的明细删除")
    @PostMapping(value = "/delContractMaterial")
    public DataResult<?> delContractMaterial(@RequestParam(required = true) String contractId, @RequestParam(required = true) String Id) {
        return DataResult.success(makeService.delContractMaterial(contractId, Id));
    }

    @ApiOperation(value = "getContractMaterial" , notes = "获取合同标的列表")
    @GetMapping(value = "/getContractMaterial")
    public DataResult<?> getContractMaterial(@RequestParam(required = true) String contractId, 
								    		 @RequestParam(required = true) Integer pageNum, 
								    		 @RequestParam(required = true) Integer pageSize) {
        return makeService.getContractMaterial(contractId, pageNum, pageSize);
    }

    @ApiOperation(value = "getContractById" ,notes = "获取合同信息 crcontractbasic crcontractinfo")
    @GetMapping(value = "/getContractById")
    public DataResult<?> getContractById(@RequestParam(required = true) String contractId) {
        return makeService.getContractById(contractId);
    }

    @ApiOperation(value="discardContract" , notes = "合同废弃")
    @PostMapping(value = "/discardContract")
    public DataResult<?> discardContract(@RequestParam(required = true) String contractId,
    									 @RequestParam(required = true) String taskId, 
    									 @RequestParam(required = true) String discardReason) {
    	
        return DataResult.success(makeService.discardContract(contractId, taskId, discardReason));
    }

    @ApiOperation(value = "delContract" ,notes = "合同删除（只能删除删除准备状态的合同）")
    @PostMapping(value = "/delContract")
    public DataResult<?> delContract(@RequestParam(required = true) String contractId, 
    								 @RequestParam(required = true) String taskId) {
    	
        return DataResult.success(makeService.delContract(contractId, taskId));
    }

    @ApiOperation(value = "getTextModel", notes = "获取合同文本模板列表")
    @GetMapping(value = "/getTextModel")
    public DataResult<?> getTextModel(@RequestParam(required = true) String contractId) {
        return DataResult.success(makeService.getTextModel(contractId));
    }

    @ApiOperation(value="createContractText" , notes = "合同文本生成（订立环节）")
    @PostMapping(value = "/createContractText")
    public DataResult<?> createContractText(@RequestParam(required = true) String contractTextId, 
    										@RequestParam(required = true) String contractId, 
    										@RequestParam(required = false) String textId,
    										@RequestParam(required = true) Integer textType) {
        return makeService.createContractText(contractTextId, contractId, textId, textType);
    }

    @ApiOperation(value="getContractText" , notes = "获取合同文本")
    @GetMapping(value = "/getContractText")
    public DataResult<?> getContractText(@RequestParam(required = true) String contractId, 
    									 @RequestParam(required = true) boolean edit) {
    	
        return makeService.getContractText(contractId, edit);
    }

    /**
     * 	获取历史合同文本
     * @param contractId
     * @return
     */
    @ApiOperation(value = "getAllContractText" ,notes = "获取合同文本")
    @GetMapping("/getAllContractText")
    public DataResult<?> getAllContractText(@RequestParam String contractId){
        return makeService.getAllContractText(contractId);
    }

    @ApiOperation(value = "getNewContractText" , notes ="获取最新合同文本")
    @GetMapping(value = "/getNewContractText")
    public DataResult<?> getNewContractText(@RequestParam(required = true) String contractId) {
        return makeService.getNewContractText(contractId);
    }

    @ApiOperation(value = "getHistoryContractText" , notes ="获取合同文本历史版本")
    @GetMapping(value = "/getHistoryContractText")
    public DataResult<?> getHistoryContractText(@RequestParam(required = true) String contractId) {
        return makeService.getHistoryContractText(contractId);
    }

    @ApiOperation(value = "contractToSign" , notes ="前往纸质打印")
    @PostMapping(value = "/contractToSign")
    public DataResult<?> contractToSign(@RequestParam(required = true) String contractId) {
        return makeService.contractToSign(contractId);
    }

    @ApiOperation(value = "contractPrintCompelete" , notes ="打印完成")
    @PostMapping(value = "/contractPrintCompelete")
    public DataResult<?> contractPrintCompelete(@RequestParam(required = true) String contractId, @RequestParam(required = true) String taskId) {
        return DataResult.success(makeService.contractPrintCompelete(contractId, taskId));
    }

    @ApiOperation(value = "getContractSignList" , notes ="合同签署列表")
    @GetMapping(value = "/getContractSignList")
    public DataResult<?> getContractSignList(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                          	 @RequestParam(required = false) String isFrameContract, @RequestParam(required = false) Integer pageNum, 
                                          	 @RequestParam(required = true) Integer pageSize) {
        return makeService.getContractSignList(ruleserialNum, contractName, isFrameContract, pageNum, pageSize);
    }

    @ApiOperation(value = "contractSign" , notes ="合同签署 保存/提交")
    @PostMapping(value = "/contractSign")
    public DataResult<?> contractSign(@RequestParam(required = true) String contractId, @RequestParam(required = true) String mySignPerson,
                                     @RequestParam(required = false) String otherSignPerson2,@RequestParam(required = false) String useSignetApprover,
                                     @RequestParam(required = false) String mySignDate, @RequestParam(required = false) Integer perFormIsConfirm,
                                     @RequestParam(required = false) String perFormStartDate, @RequestParam(required = false) String perFormEndDate,
                                     @RequestParam(required = false) String apporveUser, @RequestParam(required = false) String signAddr,
                                     @RequestParam(required = false) String eEffectiveDate, @RequestParam(required = false) Integer importantDocType,
                                     @RequestParam(required = false) String importantDoc, @RequestParam(required = false) String taskId, 
                                     @RequestParam(required = true) boolean isSubmit, @RequestParam(required = false) String perFormNotConfirmRemark) {
    	
        return DataResult.success(makeService.contractSign(contractId, mySignPerson, otherSignPerson2,useSignetApprover, mySignDate, perFormIsConfirm,
													       perFormStartDate, perFormEndDate, apporveUser, signAddr, eEffectiveDate, importantDocType, 
													       importantDoc, taskId, isSubmit, perFormNotConfirmRemark));
    }

    @ApiOperation(value = "getContractSign" , notes ="合同签署信息查看")
    @GetMapping(value = "/getContractSign")
    public DataResult<?> getContractSign(@RequestParam(required = true) String contractId) {
        return makeService.getContractSign(contractId);
    }

    @ApiOperation(value = "queryPreparContract" , notes ="合同准备草稿箱列表")
    @GetMapping(value = "/queryPreparContract")
    public DataResult<?> queryPreparContract(@RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return makeService.queryPreparContract(pageNum, pageSize);
    }

    @ApiOperation(value="queryContractByUserId", notes =  "个人已办合同列表")
    @GetMapping(value = "/queryContractByUserId")
    public DataResult<?> queryContractByUserId(@RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum, 
    										   @RequestParam(required = false) Integer type1,@RequestParam(required = false) String createDateBegin, 
    										   @RequestParam(required = false) String createDateEnd, @RequestParam(required = true) Integer pageSize,
    										   @RequestParam(required = true) Integer pageNum) {
        return makeService.queryContractByUserId(contractName, ruleserialNum, type1, createDateBegin, createDateEnd, pageSize, pageNum);
    }

    @ApiOperation(value="copyContract" , notes = "合同拷贝")
    @ApiImplicitParam(name = "contractId" , value = "合同ID")
    @PostMapping(value = "/copyContract")
    public DataResult<?> copyContract(@RequestParam(required = true) String contractId) {
        return DataResult.success(makeService.copyContract(contractId));
    }

    @ApiOperation(value="getFramContractList" , notes = "框架合同列表")
    @GetMapping(value = "/getFramContractList")
    public DataResult<?> getFramContractList(@RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                                             @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return makeService.getFramContractList(ruleserialNum, contractName, pageSize, pageNum);
    }

    @ApiOperation(value="getMasterContractList" , notes = "主合同列表")
    @GetMapping(value = "/getMasterContractList")
    public DataResult<?> getMasterContractList(@RequestParam(required = false) String contractName, @RequestParam(required = false) String ruleserialNum,
                                            @RequestParam(required = true) Integer pageSize,
                                            @RequestParam(required = true) Integer pageNum) {
        return makeService.getMasterContractList(ruleserialNum, contractName, pageSize, pageNum);
    }


    @ApiOperation(value="queryContractAccord" , notes = "签约依据关联合同查询")
    @GetMapping(value = "/queryContractAccord")
    public DataResult<?> queryContractAccord(@RequestParam(required = false) Integer mainDept, @RequestParam(required = false) String ruleserialNum,
                                          @RequestParam(required = false) String contractName, @RequestParam(required = false) String contractNum,
                                          @RequestParam(required = false) String accordCode, @RequestParam(required = false) String accordName,
                                          @RequestParam(required = false) String accordType, @RequestParam(required = false) String isEabled,
                                          @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return makeService.queryContractAccord(mainDept, ruleserialNum, contractName, contractNum, accordCode, accordName,
                accordType, isEabled, pageSize, pageNum);
    }


    @ApiOperation(value="queryContractProject" , notes = "项目关联合同查询")
    @GetMapping(value = "/queryContractProject")
    public DataResult<?> queryContractProject(@RequestParam(required = false) Integer mainDept, @RequestParam(required = false) String ruleserialNum,
                                              @RequestParam(required = false) String contractName, @RequestParam(required = false) String contractNum,
                                              @RequestParam(required = false) String projectName, @RequestParam(required = false) Integer atYear,
                                              @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
    	
        return makeService.queryContractProject(mainDept, ruleserialNum, contractName, contractNum,projectName, atYear, pageSize, pageNum);
    }

    /**
     * @param ruleSerialNum
     * @param contractNum
     * @param contractName
     * @param mainDeptID
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value= "queryStdTextUseRate" , notes = "所有部门标准文本使用率分页查询")
    @GetMapping(value = "/queryStdTextUseRate")
    public DataResult<PageData<StdTextUseRateVo>> queryStdTextUseRate(String ruleSerialNum,String contractNum,String contractName,String mainDeptID,
                                                                      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(required = false, defaultValue = "20") Integer pageSize){
        return DataResult.success(makeService.queryStdTextUseRate(ruleSerialNum, contractNum, contractName, mainDeptID, pageNum, pageSize));
    }

    
    @ApiOperation(value= "queryUnitStdTextUseRate" , notes = "当前用户所在单位的标准文本使用率")
    @GetMapping(value = "/queryUnitStdTextUseRate")
    public DataResult<Map<String, String>> queryUnitStdTextUseRate(){
        return DataResult.success(makeService.queryUnitStdTextUseRate());
    }
    
    @ApiOperation(value = "officeAgents" , notes = "个人工作助理--办公代理")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "proxyUserId",value = "代理人ID",required = true,dataType = "字符串"),
        @ApiImplicitParam(name = "startTime",value = "开始时间",required = true,dataType = "字符串"),
        @ApiImplicitParam(name = "endTime",value = "结束时间",required = true,dataType = "字符串")})
	@PostMapping(value = "/officeAgents")
	public DataResult<?> officeAgents(@RequestParam(required = true) String proxyUserId, 
								   @RequestParam(required = true) String startTime,
								   @RequestParam(required = true) String endTime) {
		return makeService.createOfficeAgents(proxyUserId, startTime, endTime);
	}

    @ApiOperation(value = "agentList" , notes = "个人工作助理--办公代理列表")
    @GetMapping(value="/agentList")
    public DataResult<?> queryOfficeAgentsList(@RequestParam(required = true) Integer pageSize, 
    										   @RequestParam(required = true) Integer pageNum){
    	return DataResult.success(makeService.queryOfficeAgentsList(pageSize,pageNum));
    }
    
    @ApiOperation(value = "cancelAgent" , notes = "个人工作助理--办公代理取消")
    @GetMapping(value="/cancelAgent/{agentId}/{status}")
    public DataResult<?> cancelAgent(@PathVariable(required = true) Integer agentId,
    								 @PathVariable(required = true) Integer status){
    	return DataResult.success(makeService.updateAgentStatusByAgentId(agentId,status));
    }
    
    @ApiOperation(value="contractChange" , notes = "应用管理-待办转交")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "serialNum",value = "合同序号",required = true,dataType = "字符串"),
        @ApiImplicitParam(name = "primitiveUser",value = "原办理人",required = true,dataType = "字符串"),
        @ApiImplicitParam(name = "transferUser",value = "新办理人",required = true,dataType = "字符串")})
	@PostMapping(value = "/contractChange")
	public DataResult<?> contractChange(@RequestParam(required = true) String serialNum, 
									 	@RequestParam(required = true) String primitiveUser,
									 	@RequestParam(required = true) String transferUser) {
		return makeService.contractChangeDispose(serialNum, primitiveUser, transferUser);
	}
}

