package com.pcitc.szgt.contract.common.enums.textmodel;

/**
 * 标准文本状态枚举
 */
public enum TextStatusEnum {

    DRAFT(1, "草稿"),
    ENABEL(5, "已启用");

    private int status;
    private String text;

    private TextStatusEnum(int status, String text){
        this.status = status;
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }}
