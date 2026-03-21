package com.pcitc.szgt.contract.make.modelEx;

import java.util.List;

public class OffereeSaveVo {
    private String contractId;

    private List<String> offereeIds;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public List<String> getOffereeIds() {
        return offereeIds;
    }

    public void setOffereeIds(List<String> offereeIds) {
        this.offereeIds = offereeIds;
    }
}
