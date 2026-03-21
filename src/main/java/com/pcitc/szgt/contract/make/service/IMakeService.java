package com.pcitc.szgt.contract.make.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.make.entity.CrOfficeAgentsList;
import com.pcitc.szgt.contract.make.modelEx.BidItem;
import com.pcitc.szgt.contract.make.modelEx.ContractOfferee;
import com.pcitc.szgt.contract.make.modelEx.OffereeSaveVo;
import com.pcitc.szgt.contract.make.modelEx.PrepareContract;
import com.pcitc.szgt.contract.make.modelEx.StdTextUseRateVo;

/**
 * <p>合同起草订立</p>
 *
 * @since 2020-02-20
 */

public interface IMakeService {
    /**
     * 	根据当前用户获取主办部门
     */
    DataResult<?> getMainDept();

    /**
     *	 获取签约主体
     */
    DataResult<?> getMySignDept(Integer deptId);

    /**
     * 	合同准备
     */
    boolean contractSave(PrepareContract prepareContract, boolean submit);
    /**
     *	修改合同履行信息，订立保存/送审
     */
    boolean updatePerContractInfo(String contractId, Integer needPrintCount, Integer perFormIsConfirm,
                                  String perFormStartDate, String perFormEndDate, Integer issueSolveMode,
                                  String settleDeadline, String taskId, String perFormNotConfirmRemark,
                                  boolean isSubmit, String changeRemark, Integer type,String mySignBodyName,Integer textType,Integer moneyFlow);
    /**
     * 	合同补录功能开发
     */
    boolean replenishContract(PrepareContract prepareContract, boolean submit);
    /**
     *	合同补录功能-订立页面 保存/提交 -不走审批流程
     */
    boolean updateReplenishContract(String contractId, Integer needPrintCount, Integer perFormIsConfirm,
                                  	String perFormStartDate, String perFormEndDate, Integer issueSolveMode,
                                  	String settleDeadline, String taskId, String perFormNotConfirmRemark, 
                                  	boolean isSubmit, String changeRemark, Integer type);
    /**
     * 	合同补录功能列表
     */
    DataResult<?> getReplenishContractList(String ruleserialNum, String contractName, 
    									String isFrameContract, Integer pageNum, 
    									Integer pageSize) ;
    /**
     * 	合同相对人保存
     */
    boolean contractOffereeSave(OffereeSaveVo offereeSaveVo);

    /**
     * 	合同相对人修改
     */
    boolean contractOffereeUpdate(ContractOfferee contractOfferee);

    /**
     *	合同相对人明细查看
     */
    DataResult<?> contractOffereeInfo(String contractId, String offereeId);

    /**
     *	合同相对人删除
     */
    boolean contractOffereeDel(String contractId, String offereeId);

    /**
     * 	根据合同ID获取合同相对人列表
     */
    DataResult<?> getContractOffereList(String contractId);

    /**
     * 	物料数据
     */
    DataResult<?> queryMaterial(String groupCode, String materialCode, String materialName, Integer pageNum, Integer pageSize);

    /**
     * 	物料转换成标的库
     */
    List<BidItem> materialToBidItem(String contractId, String[] materialIds);

    /**
     * 	插入合同标的
     */
    boolean addContractMaterial(List<BidItem> bidItemList);

    /**
     *	合同物料标的明细删除
     */
    boolean delContractMaterial(String contractId, String Id);

    /**
     * 	获取合同标的明细
     */
    DataResult<?> getContractMaterial(String contractId, Integer pageNum, Integer pageSize);

    /**
     * 	获取合同信息 crcontractbasic crcontractinfo
     */
    DataResult<?> getContractById(String contractId);
    
    /**
     * 	合同废弃
     */
    boolean discardContract(String contractId, String taskId, String discardReason);

    /**
     * 	合同删除
     */
    boolean delContract(String contractId, String taskId);
    /**
     * 	获取合同文本模板列表
     */
    DataResult<?> getTextModel(String contractId);
    /**
     *	合同文本生成
     */
    DataResult<?> createContractText(String contractTextId, String contractId, String textId, Integer textType);

    /**
     *	获取合同文本
     */
    DataResult<?> getContractText(String contractId, boolean edit);

    /**
     * 	获取所有合同文本
     * @param contractId
     * @return
     */
    DataResult<?> getAllContractText(@RequestParam String contractId);

    /**
     * 	获取最新的合同文本
     */
    DataResult<?> getNewContractText(String contractId);

    /**
     *	获取历史合同文本
     */
    DataResult<?> getHistoryContractText(String contractId);

    /**
     * 	前往纸质打印
     */
    DataResult<?> contractToSign(String contractId);

    /**
     * 	合同打印完成
     */
    boolean contractPrintCompelete(String contractId, String taskId);

    /**
     * 	合同签署列表
     */
    DataResult<?> getContractSignList(String ruleserilNum, String contractName, String isFrameContract, Integer pageNum, Integer pageSize);

    /**
     *	合同签署提交/保存
     */
    boolean contractSign(String contractId, String mySignPerson, String otherSignPerson2,String useSignetApprover,
                         String mySignDate, Integer perFormIsConfirm, String perFormStartDate,String perFormEndDate, 
                         String apporveUser, String signAddr,String eEffectiveDate, Integer importantDocType, 
                         String importantDoc, String taskId, boolean isSubmit, String perFormNotConfirmRemark);

    /**
     * 	获取合同签署信息
     */
    DataResult<?> getContractSign(String contractId);

    /**
     * 	合同准备草稿箱列表
     */
    DataResult<?> queryPreparContract(Integer pageSize, Integer pageNum);

    /**
     * 	个人已办合同查询
     */
    DataResult<?> queryContractByUserId(String contractName, String ruleserialNum, Integer type1,
                                     String createDateBegin, String createDateEnd, Integer pageSize, Integer pageNum);

    /**
     * 	合同拷贝
     */
    boolean copyContract(String contractId);

    /**
     * 	框架合同列表
     */
    DataResult<?> getFramContractList(String ruleserialNum, String contractName, Integer pageSize, Integer pageNum);

    /**
     * 	主合同列表
     *
     * @param ruleserialNum
     * @param contractName
     * @param pageSize
     * @param pageNum
     * @return
     */
    DataResult<?> getMasterContractList(String ruleserialNum, String contractName, Integer pageSize, Integer pageNum);

    /**
     * 	签约依据合同关联查询
     */
    DataResult<?> queryContractAccord(Integer mainDept, String ruleserialNum, String contractName, String contractNum, String accordCode, String accordName,
                                   String accordType, String isEabled, Integer pageSize, Integer pageNum);

    /**
     * 	项目合同关联查询
     */
    DataResult<?> queryContractProject(Integer mainDept, String ruleserialNum, String contractName, String contractNum,
                                    String projectName, Integer atYear, Integer pageSize, Integer pageNum);

    /**
     * 	标准文本使用率
     * @param ruleSerialNum
     * @param contractNum
     * @param contractName
     * @param mainDeptID
     * @return
     */
    PageData<StdTextUseRateVo> queryStdTextUseRate(String ruleSerialNum, String contractNum, String contractName, String mainDeptID, Integer pageNum, Integer pageSize);

    /**
     *	 单位标准文本使用率
     *  @return
     */
    Map<String, String> queryUnitStdTextUseRate();

    void backrevertContract(String contractId, Integer needPrintCount,
                            Integer perFormIsConfirm, String perFormStartDate,
                            String perFormEndDate, Integer issueSolveMode,
                            String settleDeadline, String perFormNotConfirmRemark,
                            boolean isSubmit, String changeRemark,
                            Integer type,
                            String taskId, String opinion);
    /**
     * 	创建办公代理方法
     * @param proxyUserId
     * @param startTime
     * @param endTime
     */
    DataResult<?>  createOfficeAgents(String proxyUserId,String startTime,String endTime);
    /**
     * 	个人工作助理--办公代理列表
     */
    CrOfficeAgentsList queryOfficeAgentsList(Integer pageSize, Integer pageNum);
    /**
     * 	根据参数修改状态
     * @param agentId
     * @return
     */
    boolean updateAgentStatusByAgentId(Integer agentId,Integer status);
    /**
     * 	合同代办转交
     * @param serialNum  合同序号
     * @param primitiveUser 原办理人
     * @param transferUser  新办理人
     * @return
     */
    DataResult<?> contractChangeDispose(String serialNum,String primitiveUser,String transferUser) ;
    
}
