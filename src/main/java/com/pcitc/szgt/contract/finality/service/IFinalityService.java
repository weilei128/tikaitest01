package com.pcitc.szgt.contract.finality.service;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.finality.model.ContractCaseData;
import com.pcitc.szgt.contract.finality.model.ContractFromLegal;
import com.pcitc.szgt.contract.finality.model.ContractToLegal;
import com.pcitc.szgt.contract.perform.entityEx.ContractQuery;

import javax.print.DocFlavor;
import javax.xml.crypto.Data;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * 合同终结管理
 * author ziran.zhou
 * 2020-03-04
 * */
public interface IFinalityService {

    /*
     * 待（已）发案合同列表
     * */
    DataResult<?> queryContractIncidence(String ruleserialNum, String contractName,
                                      String type1, String beginCreateTime, String endCreateTime, String dataType, Integer pageSize, Integer pageNum);

    /*
     * 发案新增
     * */
    boolean contractIncidence(String contractId, String caseTime, BigDecimal amout, String reason);

    /*
     * 发案新增
     * */
    DataResult<?> getContractIncidence(String contractId);
    /*
     * 合同发案从合同系统推送至法务系统
     * */
    DataResult<?> contractFromLegal(ContractFromLegal contractFromLegal);

    /*
     * 发案取消，履行回转
     * */
    boolean cancelContractIncidence(String caseId, String remark);

    /*
     * 合同履行完毕
     * */
    boolean conractComplete(String contractId, String taskId);

    /*
     * 终结合同查询
     * */
    DataResult<?> queryContractFinality(ContractQuery contractQuery);

    /*
     * 合同履行回转
     * */
    boolean contractRotation(String contractId, String messageId, String taskId);

    /**
     *	合同终结提交/保存
     */
    boolean contractTreatment(String contractId, String endId, Integer isNormal, String endDescripition, String description,
                              Integer performApprise, String performAppriseText, String appraiseContent, String taskId, boolean isSubmit);

    /*
     * 获取合同终结记录
     * */
    DataResult<?> getcontractTreatment(String endId);

    /*
     * 电子归档合同查询（待归档）
     * */
    DataResult<?> queryContractElectFiling(ContractQuery contractQuery);

    /*
     * 废弃合同查询
     * */
    DataResult<?> queryDiscardContract(ContractQuery contractQuery);
    
    /**
     * 合同案件损失金额
     * @return
     */
    DataResult<?> queryContractCaseLoseSum(ContractCaseData caseData);
    
    /**
     * 合同倒签率
     * @return
     */
    DataResult<?> queryContractBackdateRate(ContractCaseData caseData);
}
