package com.pcitc.szgt.contract.perform.controller;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.pageoffice.controller.EditAndSaveController;
import com.pcitc.szgt.contract.perform.entityEx.*;
import com.pcitc.szgt.contract.perform.model.*;
import com.pcitc.szgt.contract.perform.service.IPerformService;

import com.pcitc.szgt.contract.util.DateUtil;
import com.pcitc.szgt.contract.util.OfficeExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.hssf.record.chart.DatRecord;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ziranzhou
 * @since 2020-03-02
 */
@Api(value = "PerformController",tags = "合同履行")
@RestController
@RequestMapping("/perform")
public class PerformController {
    @Autowired
    private IPerformService performService;

    @ApiOperation(value = "isPerformContract",notes = "合同是否可以变更、终止、发案、转交、转让操作")
    @GetMapping(value = "/isPerformContract")
    public DataResult<?> isPerformContract(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.isPerformContract(contractId));
    }


    @ApiOperation(value = "contractForward",notes = "合同履行转交")
    @PostMapping(value = "/contractForward")
    public DataResult<?> contractForward(@RequestParam(required = true) String contractId, @RequestParam(required = true) String performUser,
                                      @RequestParam(required = false) String receivePayUser, @RequestParam(required = true) String finalityUser) {
        return DataResult.success(performService.contractForward(contractId, performUser, receivePayUser, finalityUser));
    }

    @ApiOperation(value = "contractForwardStatus",notes = "合同履行转交状态")
    @GetMapping("contractForwardStatus")
    public DataResult<?> contractForwardStatus(@RequestParam String contractId){
        return DataResult.success(performService.contractForwardStatus(contractId));
    }

    @ApiOperation(value = "isExistChangeFlow",notes = "判断合同所在企业/单位是否启用变更流程")
    @GetMapping(value = "/isExistChangeFlow")
    public DataResult<?> isExistChangeFlow(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.isExistChangeFlow(contractId));
    }

    /*
     * 合同是否变更中
     * */
    @RequestMapping(value = "/contractIsChange", method = RequestMethod.GET)
    public DataResult<?> contractIsChange(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.contractIsChange(contractId));
    }

    /*
     * 获取合同变更文本模板列表
     * */
    @RequestMapping(value = "/getChangeTextModel", method = RequestMethod.GET)
    public DataResult<?> getChangeTextModel(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.getChangeTextModel(contractId));
    }

    /*
     * 变更合同文本生成
     * */
    @RequestMapping(value = "/createChangeContractText", method = RequestMethod.POST)
    public DataResult<?> createChangeContractText(@RequestParam(required = true) String contractTextId, @RequestParam(required = true) String changeId, @RequestParam(required = false) String textId,
                                               @RequestParam(required = true) Integer textType) {
        return DataResult.success(performService.createChangeContractText(contractTextId, changeId, textId, textType));
    }

    /*
     * 获取变更合同文本
     * */
    @RequestMapping(value = "/getChangeContractText", method = RequestMethod.GET)
    public DataResult<?> getChangeContractText(@RequestParam(required = true) String changeId, @RequestParam(required = true) boolean edit) {
        return DataResult.success(performService.getChangeContractText(changeId, edit));
    }

    /*
     * 获取最新的变更文本，用于变更保存后再次打开可以查看
     * */
    @RequestMapping(value = "/getNewChangeContractText", method = RequestMethod.GET)
    public DataResult<?> getNewChangeContractText(@RequestParam(required = true) String changeId) {
        return DataResult.success(performService.getNewChangeContractText(changeId));
    }

    /*
     * 获取变更合同文本历史版本
     * */
    @RequestMapping(value = "/getChangeHistoryContractText", method = RequestMethod.GET)
    public DataResult<?> getChangeHistoryContractText(@RequestParam(required = true) String changeId) {
        return DataResult.success(performService.getChangeHistoryContractText(changeId));
    }


    /**
     * 	合同变更保存/提交 0 合同正在变更中  1保存/提交成功
     */
    @RequestMapping(value = "/contractChange", method = RequestMethod.POST)
    public DataResult<?> contractChange(@RequestBody ContractChange contractChangeModel, @RequestParam(required = true) boolean isSubmit) {
        return DataResult.success(performService.contractChange(contractChangeModel, isSubmit));
    }
    /**
     * 	查询合同变更信息BY changeId（待续）
     */
    @RequestMapping(value = "/getContractChange", method = RequestMethod.POST)
    public DataResult<?> getContractChange(String ContractID) {
        return DataResult.success(performService.getContractChange(ContractID));
    }

    /**
     * 	获取变更记录
     */
    @RequestMapping(value = "/getChangeList", method = RequestMethod.GET)
    public DataResult<?> getChangeList(@RequestParam(required = true) String contractId, @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return performService.getChangeList(contractId, pageNum, pageSize);
    }

    /**
     * 	获取所有变更记录（包括所有状态的）
     */
    @RequestMapping(value = "/getAllChangeList", method = RequestMethod.GET)
    public DataResult<?> getAllChangeList(@RequestParam(required = true) String contractId, @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return performService.getAllChangeList(contractId, pageNum, pageSize);
    }

    /**
     * 	获取变更明细记录
     */
    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public DataResult<?> getChange(@RequestParam(required = true) String changeConractId) {
        return performService.getChange(changeConractId);
    }

    /**
     * 	获取变更标的明细记录
     */
    @RequestMapping(value = "/getChangeBid", method = RequestMethod.GET)
    public DataResult<?> getChangeBid(@RequestParam(required = true) String changeConractId, @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return performService.getChangeBid(changeConractId, pageNum, pageSize);
    }

    @ApiOperation(value = "contractChangeSign" ,notes = "合同变更签署")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "changeContractId", value = "合同变更主键", required = true),
            @ApiImplicitParam(name = "ourSignatoryName", value = "我方签约人姓名"),
            @ApiImplicitParam(name = "ourSignatory", value = "我方签约人id"),
            @ApiImplicitParam(name = "opponentSignatory", value = "对方签约人"),
            @ApiImplicitParam(name = "ourSealedTime", value = "签订日期"),
            @ApiImplicitParam(name = "effective", value = "生效情况"),
            @ApiImplicitParam(name = "effectiveDate", value = "变更生效日期"),
            @ApiImplicitParam(name = "effectiveElements", value = "变更生效要件"),
            @ApiImplicitParam(name = "isSumbit", value = "是否提交 保存/提交"),
    })
    @PostMapping(value = "/contractChangeSign")
    public DataResult<?> contractChangeSign(@RequestParam(required = true) String changeContractId, @RequestParam(required = true) String ourSignatoryName,
                                         @RequestParam(required = true) String ourSignatory, @RequestParam(required = true) String opponentSignatory,
                                         @RequestParam(required = true) String ourSealedTime, @RequestParam(required = true) Integer effective,
                                         @RequestParam(required = false) String effectiveDate, @RequestParam(required = false) String effectiveElements,
                                         @RequestParam(required = false) String taskId, @RequestParam(required = true) boolean isSumbit) {
        return DataResult.success(performService.contractChangeSign(changeContractId, ourSignatoryName, ourSignatory,
                opponentSignatory, ourSealedTime, effective, effectiveDate, effectiveElements, taskId, isSumbit));
    }

    /**
     * 	获取合同变更转让签署
     */
    @RequestMapping(value = "/getContractChangeSign", method = RequestMethod.GET)
    public DataResult<?> getContractChangeSign(@RequestParam(required = true) String changeContractId) {
        return performService.getContractChangeSign(changeContractId);
    }


    /**
     * 	判断合同所在企业/单位是否启用转让流程
     */
    @RequestMapping(value = "/isExistTransFlow", method = RequestMethod.GET)
    public DataResult<?> isExistTransFlow(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.isExistTransFlow(contractId));
    }


    @ApiOperation(value = "isContractTrans" , notes = "判断合同是否正在转让/已保存，true禁止保存")
    @GetMapping(value = "/isContractTrans")
    public DataResult<?> isContractTrans(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.isContractTrans(contractId));
    }


    @ApiOperation(value = "contractTrans" , notes = "合同转让保存/提交 0 合同正在变更中  1保存/提交成功")
    @PostMapping(value = "/contractTrans")
    public DataResult<?> contractTrans(@RequestBody ContractTrans contractTrans, @RequestParam(required = true) boolean isSubmit) {
        return DataResult.success(performService.contractTrans(contractTrans, isSubmit));
    }

    /*
     * 获取转让详细信息
     * */
    @RequestMapping(value = "/getContractTrans", method = RequestMethod.GET)
    public DataResult<?> getContractTrans(@RequestParam(required = true) String transId) {
        return performService.getContractTrans(transId);
    }

    /*
     * 获取合同转让记录列表
     * */
    @RequestMapping(value = "/getContractTrasnList", method = RequestMethod.GET)
    public DataResult<?> getContractTrasnList(@RequestParam(required = true) String contractId,
                                           @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return performService.getContractTrasnList(contractId, pageNum, pageSize);
    }

    /*
     * 获取合同所有转让记录列表
     * */
    @RequestMapping(value = "/getContractAllTrasnList", method = RequestMethod.GET)
    public DataResult<?> getContractAllTrasnList(@RequestParam(required = true) String contractId,
                                              @RequestParam(required = true) Integer pageNum, @RequestParam(required = true) Integer pageSize) {
        return performService.getContractAllTrasnList(contractId, pageNum, pageSize);
    }

    /*
     * 合同转让签署
     * */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transContractId", value = "合同转让主键"),
            @ApiImplicitParam(name = "ourSignatory", value = "我方签约人id"),
            @ApiImplicitParam(name = "opponentSignatory", value = "对方签约人"),
            @ApiImplicitParam(name = "ourSealedTime", value = "签订日期"),
            @ApiImplicitParam(name = "effective", value = "生效情况"),
            @ApiImplicitParam(name = "effectiveDate", value = "转让生效日期"),
            @ApiImplicitParam(name = "effectiveElements", value = "转让生效要件"),
            @ApiImplicitParam(name = "isSumbit", value = "是否提交  保存/提交")
    })
    @RequestMapping(value = "/contractTransSign", method = RequestMethod.POST)
    public DataResult<?> contractTransSign(@RequestParam(required = true) String transContractId,
                                        @RequestParam(required = true) String ourSignatory, @RequestParam(required = true) String opponentSignatory,
                                        @RequestParam(required = true) String ourSealedTime, @RequestParam(required = true) Integer effective,
                                        @RequestParam(required = false) String effectiveDate, @RequestParam(required = false) String effectiveElements,
                                        @RequestParam(required = false) String taskId, boolean isSumbit) {
        return DataResult.success(performService.contractTransSign(transContractId, ourSignatory,
                opponentSignatory, ourSealedTime, effective, effectiveDate, effectiveElements, taskId, isSumbit));
    }

    /*
     * 合同转让签署信息查看
     * */
    @RequestMapping(value = "/getContractTransSign", method = RequestMethod.GET)
    public DataResult<?> getContractTransSign(@RequestParam(required = true) String transContractId) {
        return performService.getContractTransSign(transContractId);
    }

    /*
     * 获取合同原相对人及签约主体
     * */
    @RequestMapping(value = "/getContractOffAndMysig", method = RequestMethod.GET)
    public DataResult<?> getContractOffAndMysig(@RequestParam(required = true) String contractId) {
        return performService.getContractOffAndMysig(contractId);
    }

    /*
     * 判断是否存在合同终止流程
     * */
    @RequestMapping(value = "/isExistContractEnd", method = RequestMethod.GET)
    public DataResult<?> isExistContractEnd(@RequestParam(required = true) String contractId) {
        return DataResult.success(performService.isExistContractEnd(contractId));
    }

    /*
     * 合同终止
     * */
    @RequestMapping(value = "/contractEnd", method = RequestMethod.POST)
    public DataResult<?> contractEnd(@RequestBody ContractEnd contractEnd) {
        return DataResult.success(performService.contractEnd(contractEnd));
    }

    /*
     * 获取合同终止详细信息
     * */
    @RequestMapping(value = "/getContractEnd", method = RequestMethod.GET)
    public DataResult<?> getContractEnd(@RequestParam(required = true) String endId) {
        return performService.getContractEnd(endId);
    }

    /*
     * 获取合同终止记录
     * */
    @RequestMapping(value = "/getContractEndList", method = RequestMethod.GET)
    public DataResult<?> getContractEndList(@RequestParam(required = true) String contractId,
                                         @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return performService.getContractEndList(contractId, pageSize, pageNum);
    }


    /*
     * 获取合同终止签署信息
     * */
    @RequestMapping(value = "/getContractEndSign", method = RequestMethod.GET)
    public DataResult<?> getContractEndSign(@RequestParam(required = true) String endId) {
        return performService.getContractEndSign(endId);
    }

    /*
     * 取消合同终止
     * */
    @RequestMapping(value = "/cancelContractEnd", method = RequestMethod.POST)
    public DataResult<?> cancelContractEnd(@RequestParam(required = true) String endId) {
        return DataResult.success(performService.cancelContractEnd(endId));
    }

    /*
     * 合同终止签署
     * */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endContractId", value = "合同终止主键"),
            @ApiImplicitParam(name = "ourSignatory", value = "我方签约人id"),
            @ApiImplicitParam(name = "opponentSignatory", value = "对方签约人"),
            @ApiImplicitParam(name = "ourSealedTime", value = "签订日期"),
            @ApiImplicitParam(name = "effective", value = "生效情况"),
            @ApiImplicitParam(name = "effectiveDate", value = "终止生效日期"),
            @ApiImplicitParam(name = "effectiveElements", value = "终止生效要件"),
            @ApiImplicitParam(name = "isSumbit", value = "是否提交  保存/提交")
    })
    @RequestMapping(value = "/ContractEndSign", method = RequestMethod.POST)
    public DataResult<?> ContractEndSign(@RequestParam(required = true) String endContractId,
                                      @RequestParam(required = true) String ourSignatory, @RequestParam(required = true) String opponentSignatory,
                                      @RequestParam(required = true) String ourSealedTime, @RequestParam(required = true) Integer effective,
                                      @RequestParam(required = false) String effectiveDate, @RequestParam(required = false) String effectiveElements,
                                      @RequestParam(required = false) String taskId, boolean isSumbit) {
        return DataResult.success(performService.contractEndSign(endContractId, ourSignatory,
                opponentSignatory, ourSealedTime, effective, effectiveDate, effectiveElements, taskId, isSumbit));
    }

    /*
     * 合同订立备案查询
     * */
    @RequestMapping(value = "/getSealContract", method = RequestMethod.GET)
    public DataResult<?> getSealContract(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                      @RequestParam(required = false) String contractNum, @RequestParam(required = false) Integer mainDept,
                                      @RequestParam(required = false) String mainOrgUserId, @RequestParam(required = false) String type1,
                                      @RequestParam(required = false) String type2, @RequestParam(required = false) String type3,
                                      @RequestParam(required = false) String type4, @RequestParam(required = false) String isMakeSureMoney,
                                      @RequestParam(required = false) String minContractMoney,
                                      @RequestParam(required = false) String maxContractMoney, @RequestParam(required = false) String isSeal,
                                      @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return performService.getSealContract(ruleserialNum, contractName, contractNum, mainDept, mainOrgUserId, type1, type2, type3, type4, isMakeSureMoney,
                minContractMoney, maxContractMoney, isSeal, pageSize, pageNum);
    }

    /**
     * 合同订立备案详情
     *
     * @param infoId
     * @return
     */
    @GetMapping("/getContractSeal")
    public DataResult<?> getContractSeal(String infoId) {
        return performService.getContractSeal(infoId);
    }

    /*
     * 合同订立备案
     * */
    @RequestMapping(value = "/contractSeal", method = RequestMethod.POST)
    public DataResult<?> contractSeal(@RequestBody ContractSeals contractSeals) {
        return DataResult.success(performService.contractSeal(contractSeals.contractSeals));
    }

    /*
     * 合同变更备案查询
     * */
    @RequestMapping(value = "/getSealContractChange", method = RequestMethod.GET)
    public DataResult<?> getSealContractChange(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                            @RequestParam(required = false) String contractNum, @RequestParam(required = false) Integer mainDept,
                                            @RequestParam(required = false) String mainOrgUserId, @RequestParam(required = false) String type1,
                                            @RequestParam(required = false) String type2, @RequestParam(required = false) String type3,
                                            @RequestParam(required = false) String type4, String isMakeSureMoney,
                                            @RequestParam(required = false) String minContractMoney, @RequestParam(required = false) String maxContractMoney,
                                            @RequestParam(required = false) String isSeal, @RequestParam(required = true) Integer pageSize,
                                            @RequestParam(required = true) Integer pageNum) {
        return performService.getSealContractChange(ruleserialNum, contractName, contractNum, mainDept, mainOrgUserId, type1, type2, type3, type4, isMakeSureMoney,
                minContractMoney, maxContractMoney, isSeal, pageSize, pageNum);
    }

    /*
     * 合同变更备案
     * */
    @RequestMapping(value = "/contractChangeSeal", method = RequestMethod.POST)
    public DataResult<?> contractChangeSeal(@RequestBody ContractSeals contractSeals) {
        return DataResult.success(performService.contractChangeSeal(contractSeals.contractSeals));
    }

    /*
     * 获取合同变更备案详细信息
     * */
    @RequestMapping(value = "/getContractChangeSeal", method = RequestMethod.GET)
    public DataResult<?> getContractChangeSeal(@RequestParam(required = true) String changeId) {
        return performService.getContractChangeSeal(changeId);
    }

    /*
     * 获取合同转让备案详细信息
     * */
    @RequestMapping(value = "/getContractTransSeal", method = RequestMethod.GET)
    public DataResult<?> getContractTransSeal(@RequestParam(required = true) String transId) {
        return performService.getContractTransSeal(transId);
    }

    /*
     * 获取合同终止备案详细信息
     * */
    @RequestMapping(value = "/getContractEndSeal", method = RequestMethod.GET)
    public DataResult<?> getContractEndSeal(@RequestParam(required = true) String endId) {
        return performService.getContractEndSeal(endId);
    }

    /*
     * 合同转交备案查询
     * */
    @RequestMapping(value = "/getSealContractTransfer", method = RequestMethod.GET)
    public DataResult<?> getSealContractTransfer(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                              @RequestParam(required = false) String contractNum, @RequestParam(required = false) Integer mainDept,
                                              @RequestParam(required = false) String mainOrgUserId, @RequestParam(required = false) String type1,
                                              @RequestParam(required = false) String type2, @RequestParam(required = false) String type3,
                                              @RequestParam(required = false) String type4, @RequestParam(required = false) String isMakeSureMoney,
                                              @RequestParam(required = false) String minContractMoney,
                                              @RequestParam(required = false) String maxContractMoney, @RequestParam(required = false) String isSeal,
                                              @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return performService.getSealContractTransfer(ruleserialNum, contractName, contractNum, mainDept, mainOrgUserId, type1, type2, type3, type4, isMakeSureMoney,
                minContractMoney, maxContractMoney, isSeal, pageSize, pageNum);
    }

    /*
     * 合同转让备案
     * */
    @RequestMapping(value = "/contractTransferSeal", method = RequestMethod.POST)
    public DataResult<?> contractTransferSeal(@RequestBody ContractSeals contractSeals) {
        return DataResult.success(performService.contractTransferSeal(contractSeals.contractSeals));
    }

    /*
     * 合同终止备案查询
     * */
    @RequestMapping(value = "/getSealContractEnd", method = RequestMethod.GET)
    public DataResult<?> getSealContractEnd(@RequestParam(required = false) String ruleserialNum, @RequestParam(required = false) String contractName,
                                         @RequestParam(required = false) String contractNum, @RequestParam(required = false) Integer mainDept,
                                         @RequestParam(required = false) String mainOrgUserId, @RequestParam(required = false) String type1,
                                         @RequestParam(required = false) String type2, @RequestParam(required = false) String type3,
                                         @RequestParam(required = false) String type4, @RequestParam(required = false) String isMakeSureMoney,
                                         @RequestParam(required = false) String minContractMoney, @RequestParam(required = false) String maxContractMoney,
                                         @RequestParam(required = false) String isSeal, @RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
        return performService.getSealContractEnd(ruleserialNum, contractName, contractNum, mainDept, mainOrgUserId, type1, type2, type3, type4, isMakeSureMoney,
                minContractMoney, maxContractMoney, isSeal, pageSize, pageNum);
    }

    /**
     * 	合同终止备案
     */
    @RequestMapping(value = "/contractEndSeal", method = RequestMethod.POST)
    public DataResult<?> contractEndSeal(@RequestBody ContractSeals contractSeals) {
        return DataResult.success(performService.contractEndSeal(contractSeals.contractSeals));
    }

    /**
     * 	合同运行简表
     */
    @RequestMapping(value = "/queryContractOverTable", method = RequestMethod.POST)
    public DataResult<?> queryContractOverTable(@RequestBody ContractQuery contractQuery) {
        return performService.queryContractOverTable(contractQuery);
    }

    /**
     * 	合同查询
     */
    @RequestMapping(value = "/queryContract", method = RequestMethod.POST)
    public DataResult<?> queryContract(@RequestBody ContractQuery contractQuery,@RequestParam(required = false) Integer ifPerform) {
        return performService.queryContract(contractQuery,0);
    }

    /**
     * 	合同查询（合同履行预警查询时，找这个接口）
     */
    @RequestMapping(value = "/queryPerformContract", method = RequestMethod.POST)
    public DataResult<?> queryPerformContract(@RequestBody ContractQuery contractQuery,@RequestParam(required = false) Integer ifPerform) {
        return performService.queryContract(contractQuery,30);
    }

    /*
     *合同履行选择权限
     * */
    @RequestMapping(value = "/performOperate", method = RequestMethod.GET)
    public DataResult<?> performOperate(@RequestParam(required = true) String contractId) {
        return performService.performOperate(contractId);
    }

    /**
     * 保存合同收付款警告
     * @return
     */
    @PostMapping("savePayAlert")
    public DataResult<?> savePayAlert(@Validated @RequestBody PayAlertAddVo addVo){
        boolean b = performService.savePayAlert(addVo);
        if(b){
            return DataResult.success(null);
        } else {
            return DataResult.fail(null, 500, "操作失败");
        }
    }

    /**
     * 开启/关闭合同收付款警告
     * @param fId
     * @return
     */
    @PostMapping("enablePayAlert")
    @Validated
    public DataResult<?> closePayAlert(@RequestParam Integer fId, @RequestParam @Min(0) @Max(1) Integer state){
        boolean b = performService.closePayAlert(fId, state);
        if(b){
            return DataResult.success(null);
        } else {
            return DataResult.fail(null, 500, "操作失败");
        }
    }

    /**
     * 查询合同付款警告
     * @return
     */
    @GetMapping("queryPayAlert")
    public DataResult<PageData<PayAlertListVo>> queryPayAlert(PayAlertQueryVo queryVo){
        return DataResult.success(performService.queryPayAlert(queryVo));
    }

    /**
     * 查询收付款合同警告列表
     * @return
     */
    @GetMapping("queryPayAlertMsg")
    public DataResult<PageData<PayAlertMsgVo>> queryPayAlertMsg(Integer pageNum, Integer pageSize){
        return DataResult.success(performService.queryPayAlertMsg(pageNum, pageSize));
    }


    @ApiOperation(value = "查询合同终结警告列表")
    @GetMapping("queryFinalAlertMsg")
    public DataResult<PageData<FinalAlertMsgVo>> queryFinalAlertMsg(Integer pageNum, Integer pageSize){
        return DataResult.success(performService.queryFinalAlertMsg(pageNum, pageSize));
    }

    /**
     * 	查询合同警报数量
     * @return
     */
    @GetMapping("queryAlertCnt")
    public DataResult<Map<String, Long>> queryAlertCnt(){
        return DataResult.success(performService.queryAlertCnt());
    }

    @PostMapping("export")
    public void exportContract(@RequestBody ContractQuery contractQuery, HttpServletResponse response){
        /*查询数据*/
        List<ExportContractVo> exportContractVos= performService.queryContractExport(contractQuery);

        /*调用Workbook导出到excel*/
        Workbook workbook = OfficeExportUtil.getWorkbook("合同查询导出表", "Sheet 1", ExportContractVo.class, exportContractVos);
        OfficeExportUtil.exportExcel(workbook, "合同查询导出表"+ DateUtil.getDateFormatStr(new Date(), DateUtil.FMT_DATETIME), response);
    }

}
