package com.pcitc.szgt.contract.perform.model;

import java.time.LocalDateTime;

public class FinalAlertMsgVo {
    /**
     * 合同id
     */
    private String fContractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 履行期限
     */
    private LocalDateTime perFormEndDate;

    public String getfContractId() {
        return fContractId;
    }

    public void setfContractId(String fContractId) {
        this.fContractId = fContractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public LocalDateTime getPerFormEndDate() {
        return perFormEndDate;
    }

    public void setPerFormEndDate(LocalDateTime perFormEndDate) {
        this.perFormEndDate = perFormEndDate;
    }
}
