package com.pcitc.szgt.contract.common.enums.offeree;

/**
 * 相对人附件枚举
 */
public enum OffereeAttachmentEnum {

    BUSINESSLICENCE_CERTIFICATE(1, "相关附件-营业执照三证合一"),
    LEGALENTITY_CERTIFICATE(2, "相关附件-法定代表人身份证明书"),
    QUALIFICATION_CERTIFICATE(3, "相关资质证"),
    BUSINESSLICENCE_BINARYCODE(4, "营业执照二维码");

    private int code;

    private String text;

    private OffereeAttachmentEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }}
