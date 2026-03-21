package com.pcitc.szgt.contract.common.enums.offeree;

/**
 * 相对人状态枚举
 */
public enum OffereeStatus {

    Complete(0, "完成"),
    Draft(1, "草稿");

    private int type;

    private String text;

    private OffereeStatus(int type, String text){
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
