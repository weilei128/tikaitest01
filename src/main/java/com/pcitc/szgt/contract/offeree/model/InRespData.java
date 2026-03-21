package com.pcitc.szgt.contract.offeree.model;

import java.util.List;

public class InRespData {
    private String msgErr;
    private String count;
    private List<InMainDataRespModel> datalist;

    public String getMsgErr() {
        return msgErr;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<InMainDataRespModel> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<InMainDataRespModel> datalist) {
        this.datalist = datalist;
    }
}
