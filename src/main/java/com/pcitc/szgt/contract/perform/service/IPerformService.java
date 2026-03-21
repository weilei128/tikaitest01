package com.pcitc.szgt.contract.perform.service;

import java.util.List;
import java.util.Map;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.make.entity.CrContractrununit;
import com.pcitc.szgt.contract.perform.entityEx.ContractChange;
import com.pcitc.szgt.contract.perform.entityEx.ContractEnd;
import com.pcitc.szgt.contract.perform.entityEx.ContractQuery;
import com.pcitc.szgt.contract.perform.entityEx.ContractSeal;
import com.pcitc.szgt.contract.perform.entityEx.ContractTrans;
import com.pcitc.szgt.contract.perform.model.*;

/**
 * <p>合同履行</p>
 * @author ziran.zhou
 * @since 2020-03-02
 */

public interface IPerformService {
    /**
     * 	合同是否继续履行
     */
    boolean isPerformContract(String contractId);

    /**
     * 	合同转交
     */
    boolean contractForward(String contractId, String performUser, String receivePayUser, String finalityUser);

    /**
     * 	合同转交状态
     * @return
     */
    public CrContractrununit contractForwardStatus(String contractId);

    /**
     * 	是否启用变更流程
     */
    boolean isExistChangeFlow(String contractId);

    /**
     * 	合同是否变更中
     */
    boolean contractIsChange(String contractId);

    /**
     * 	合同变更
     */
    Integer contractChange(ContractChange contractChangeModel, boolean isSubmit);
    /**
     * 	合同变更查询
     */
    DataResult<?>  getContractChange(String ContractID);

    /**
     * 	获取变更环节合同文本模板列表
     */
    DataResult<?> getChangeTextModel(String contractId);

    /**
     * 	变更合同文本生成
     */
    DataResult<?> createChangeContractText(String contractTextId, String changeId, String textId, Integer textType);

    /**
     *	获取变更合同文本
     */
    DataResult<?> getChangeContractText(String changeId, boolean edit);

    /**
     * 	获取最新的变更文本，用于变更保存后再次打开可以查看
     */
    DataResult<?> getNewChangeContractText(String changeId);

    /**
     *	获取变更合同历史合同文本
     */
    DataResult<?> getChangeHistoryContractText(String changeId);

    /**
     * 	获取变更记录
     */
    DataResult<?> getChangeList(String contractId, Integer pageNum, Integer pageSize);

    /**
     * 	获取所有变更记录
     */
    DataResult<?> getAllChangeList(String contractId, Integer pageNum, Integer pageSize);

    /**
     * 	查看变更信息
     */
    DataResult<?> getChange(String changeCntractId);

    /**
     * 	获取变更信息，标的明细
     */
    DataResult<?> getChangeBid(String changeCntractId, Integer pageNum, Integer pageSize);

    /**
     * 	合同变更签署
     */
    boolean contractChangeSign(String changeContractId, String ourSignatoryName, String ourSignatory,
                               String opponentSignatory, String ourSealedTime, Integer effective,
                               String effectiveDate, String effectiveElements, String taskId, boolean isSumbit);

    /**
     * 	获取合同变更签署信息
     */
    DataResult<?> getContractChangeSign(String changeContractId);

    /**
     * 	判断是否存在转让流程
     */
    boolean isExistTransFlow(String contractId);

    /**
     * 	判断合同是否正在转让审批中
     */
    boolean isContractTrans(String contractId);

    /**
     * 	合同转让
     */
    Integer contractTrans(ContractTrans contractTrans, boolean isSumbit);

    /**
     *	获取合同转让详细信息
     */
    DataResult<?> getContractTrans(String transId);

    /**
     * 	获取合同转让记录
     */
    DataResult<?> getContractTrasnList(String contractId, Integer pageNum, Integer pageSize);

    /**
     * 	获取合同所有转让记录
     */
    DataResult<?> getContractAllTrasnList(String contractId, Integer pageNum, Integer pageSize);


    /**
     * 	合同转让签署
     */
    boolean contractTransSign(String transContractId, String ourSignatory,
                              String opponentSignatory, String ourSealedTime, Integer effective,
                              String effectiveDate, String effectiveElements, String taskId, boolean isSumbit);

    /**
     * 	获取合同转让签署信息
     */
    DataResult<?> getContractTransSign(String transContractId);

    /**
     * 	根据合同获取相对人及我方签约主体
     */
    DataResult<?> getContractOffAndMysig(String contractId);

    /**
     * 	判断是否存在终止流程
     */
    boolean isExistContractEnd(String contractId);

    /**
     *	合同终止
     */
    Integer contractEnd(ContractEnd contrctEnd);

    /**
     * 	获取合同终止详细信息
     */
    DataResult<?> getContractEnd(String endId);

    /**
     * 	获取合同终止签署信息
     */
    DataResult<?> getContractEndSign(String endId);

    /**
     * 	获取合同终止记录
     */
    DataResult<?> getContractEndList(String contractId, Integer pageSize, Integer pageNum);

    /**
     * 	取消合同终止
     */
    boolean cancelContractEnd(String endId);

    /**
     * 	合同终止签署
     */
    boolean contractEndSign(String endContractId, String ourSignatory,
                            String opponentSignatory, String ourSealedTime, Integer effective,
                            String effectiveDate, String effectiveElements, String taskId, boolean isSumbit);

    /**
     * 	合同订立备案
     */
    boolean contractSeal(List<ContractSeal> crcontactSealList);

    /**
     * 	合同订立备案详情
     * @param infoId
     * @return
     */
    DataResult<?> getContractSeal(String infoId);

    /**
     * 	合同订立备案查询
     */
    DataResult<?> getSealContract(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                               String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                               String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum);

    /**
     * 	合同变更备案查询
     */
    DataResult<?> getSealContractChange(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                     String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                     String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum);

    /**
     * 	合同变更备案
     */
    boolean contractChangeSeal(List<ContractSeal> contractChangeSealList);

    /**
     * 	获取合同变更备案信息
     */
    DataResult<?> getContractChangeSeal(String changeId);

    /**
     *	获取转让备案详细信息
     */
    DataResult<?> getContractTransSeal(String transId);

    /**
     * 	获取终止备案详细信息
     */
    DataResult<?> getContractEndSeal(String endId);

    /**
     * 	合同转让备案查询
     */
    DataResult<?> getSealContractTransfer(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                       String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                       String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum);

    /**
     * 	合同转让备案
     */
    boolean contractTransferSeal(List<ContractSeal> contractTransferSealList);

    /**
     * 	合同终止备案查询
     */
    DataResult<?> getSealContractEnd(String ruleserialNum, String contractName, String contractNum, Integer mainDept, String mainOrgUserId,
                                  String type1, String type2, String type3, String type4, String isMakeSureMoney, String minContractMoney,
                                  String maxContractMoney, String isSeal, Integer pageSize, Integer pageNum);

    /**
     * 	合同终止备案
     */
    boolean contractEndSeal(List<ContractSeal> contractTransferSealList);

    /**
     * 	合同运行简表查询
     */
    DataResult<?> queryContractOverTable(ContractQuery contractQuery);

    /**
     * 	合同查询统计
     */
    DataResult<?> queryContract(ContractQuery contractQuery,Integer ifPerform);

    /**
     * 	合同查询统计导出
     */
    List<ExportContractVo> queryContractExport(ContractQuery contractQuery);

    /**
     * 	合同履行操作权限
     */
    DataResult<?> performOperate(String contractId);

    /**
     * 	添加合同警报信息
     * @param addVo
     */
    boolean savePayAlert(PayAlertAddVo addVo);

    /**
     * 	关闭合同警报
     * @param fId
     * @return
     */
    public boolean closePayAlert(Integer fId, Integer state);

    /**
     * 	查询合同付款警告
     * @return
     */
    public PageData<PayAlertListVo> queryPayAlert(PayAlertQueryVo queryVo);

    /**
     * 	查询收付款合同警告列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageData<PayAlertMsgVo> queryPayAlertMsg(Integer pageNum, Integer pageSize);

    /**
     * 	查询合同终结警告列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageData<FinalAlertMsgVo> queryFinalAlertMsg(Integer pageNum, Integer pageSize);

    /**
     * 	合同报警信息数量
     * @return
     */
    public Map<String, Long> queryAlertCnt();
}
