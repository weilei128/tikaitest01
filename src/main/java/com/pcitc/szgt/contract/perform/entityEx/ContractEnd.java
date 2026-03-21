package com.pcitc.szgt.contract.perform.entityEx;

import java.time.LocalDateTime;

/*
 * 合同终止
 * */
public class ContractEnd {
    public String endId;
    public String endCode;
    public String contractId;
    public String contractName;
    public Integer endReason;//终止原因
    public String endTime;//终止日期
    public String endRemark;//终止说明
    public Integer isNormal;
}
