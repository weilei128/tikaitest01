package com.pcitc.szgt.contract.interactive.model;

/**
 * 接收财务凭证数据
 */
public class FinancialProofVo {

    private String jsondata ;

    private String currTime;

    private String sign;

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FinancialProofVo{");
        sb.append("jsondata='").append(jsondata).append('\'');
        sb.append(", currTime='").append(currTime).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
