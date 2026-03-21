package com.pcitc.szgt.contract.offeree.model;

import java.util.List;

public class OffereeResultVo {
    private OffereeInfoVo offereeInfo;

    private List<OffereeBankVo> offereeBankList;

//    private OffereeLicenseVo offereeLicense;

    private List<OffereeLinkmanVo> offereeLinkmanList;

    public OffereeInfoVo getOffereeInfo() {
        return offereeInfo;
    }

    public void setOffereeInfo(OffereeInfoVo offereeInfo) {
        this.offereeInfo = offereeInfo;
    }

    public List<OffereeBankVo> getOffereeBankList() {
        return offereeBankList;
    }

    public void setOffereeBankList(List<OffereeBankVo> offereeBankList) {
        this.offereeBankList = offereeBankList;
    }

//    public OffereeLicenseVo getOffereeLicense() {
//        return offereeLicense;
//    }
//
//    public void setOffereeLicense(OffereeLicenseVo offereeLicense) {
//        this.offereeLicense = offereeLicense;
//    }

    public List<OffereeLinkmanVo> getOffereeLinkmanList() {
        return offereeLinkmanList;
    }

    public void setOffereeLinkmanList(List<OffereeLinkmanVo> offereeLinkmanList) {
        this.offereeLinkmanList = offereeLinkmanList;
    }
}
