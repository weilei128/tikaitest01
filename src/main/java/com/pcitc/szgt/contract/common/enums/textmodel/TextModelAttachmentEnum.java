package com.pcitc.szgt.contract.common.enums.textmodel;

/**
 * 标准文本管理附件枚举
 */
public enum TextModelAttachmentEnum {

    STANDARD_TEXT(1, "文本及附件-标准文本"),
    SUGGESTION_TABLE(2, "文本评审-评审意见表");

    private int code;
    private String text;

    private TextModelAttachmentEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
