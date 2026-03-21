package com.pcitc.szgt.contract.common.enums.unitconfig;

/**
 * 单位配置附件枚举
 */
public enum UnitConfigAttachmentEnum {

    GROUP_CORP_WATERMARK(1, "集团签约主体-合同水印"),
    ASSET_CORP_WATERMARK(2, "资产签约主体-合同水印"),
    SHARE_CORP_WATERMARK(3, "股份签约主体-合同水印"),
    ENTRUST_TEMPLATE(4, "授权委托书模板"),
    HONESTDUTY_TEMPLATE(5, "廉洁从业责任书模板"),
    SAFEPROTOCOL_TEMPLATE(6, "安全协议模板"),
    KEEPSECRET_TEMPLATE(7, "保密承诺函"),
    PAYMENTCHECK_TEMPLATE(8, "付款审批表模板");

    private int code;
    private String text;

    private UnitConfigAttachmentEnum(int code, String text){
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
