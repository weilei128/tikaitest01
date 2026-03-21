package com.pcitc.szgt.contract.common.enums.offeree;

/**
 * 相对人类型枚举
 */
public enum OffereeTypeEnum {

    Org(0, "机构"),
    Person(1, "自然人");

    private int type;

    private String text;

    private OffereeTypeEnum(int type, String text){
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
