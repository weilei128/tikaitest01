package com.pcitc.szgt.contract.common.enums;

//收/付款类型
public enum PayTypeEnum {

    OneTime("0", "一次性付款"),
    Advance("1", "预付款"),
    Process("2", "进度款"),
    Assurance("3", "质保金");

    private String code;
    private String name;

    PayTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
