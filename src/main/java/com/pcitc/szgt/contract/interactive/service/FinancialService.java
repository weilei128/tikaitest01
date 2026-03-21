package com.pcitc.szgt.contract.interactive.service;

import com.pcitc.szgt.contract.interactive.entity.CrExecutepayment;
import com.pcitc.szgt.contract.interactive.model.ContractInfo;
import com.pcitc.szgt.contract.interactive.model.FinancialProof;
import com.pcitc.szgt.contract.interactive.model.RespData;

import java.util.List;

public interface FinancialService {

    public void sendDataToFinancial(ContractInfo contractInfo);

    public void receiveFinancialProof(List<FinancialProof> financialProof);

    /**
     * 查询合同的付款信息
     * @return
     */
    public List<CrExecutepayment> query(String contractId);

}
