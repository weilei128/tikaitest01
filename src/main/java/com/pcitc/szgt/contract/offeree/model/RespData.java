package com.pcitc.szgt.contract.offeree.model;

import java.util.List;

public class RespData {
    private String msgErr;
    private String count;
    private List<MainDataRespModel> datalist;

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

    public List<MainDataRespModel> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<MainDataRespModel> datalist) {
        this.datalist = datalist;
    }
}
