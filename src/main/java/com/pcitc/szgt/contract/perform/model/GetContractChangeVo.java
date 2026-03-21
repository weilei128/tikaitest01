package com.pcitc.szgt.contract.perform.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GetContractChangeVo {
    /**
     * 合同id
     */
    private String contractID;

    /**
     * 合同变更id
     */
    private String contractChangeID;

    /**
     * 变更单编码
     */
    private String ccNo;

    /**
     * 变更申请方
     */
    private String proposer;

    /**
     * 变更原因
     */
    private String changeReason;

    /**
     * 变更事项
     */
    private int changeType;

    /**
     * 变更后含税金额
     */
    private String changeAmount;

    /**
     * 变更后不含税金额
     */
    private String changeNoTaxAmount;

    /**
     * 变更后税额
     */
    private String changeTaxAmount;




}
