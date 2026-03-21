package com.pcitc.szgt.contract.common.enums;

public enum FinancialEnum {

    FULFIL("3", "合同履行"),
    CHANGE("4", "合同变更"),
    TRANSFER("5", "合同转让"),
    TERMINATE("7", "合同终止"),
    FINASH("8", "合同终结"),
    DISCARD("2","合同作废");

    private String code;
    private String name;

    FinancialEnum(String code, String name){
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
