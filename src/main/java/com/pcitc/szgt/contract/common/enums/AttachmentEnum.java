package com.pcitc.szgt.contract.common.enums;

/**
 * 附件枚举
 */
public enum AttachmentEnum {

    // 相对人
    BUSINESSLICENCE_CERTIFICATE(1, "相关附件", 1,"营业执照三证合一"),
    LEGALENTITY_CERTIFICATE(1, "相关附件", 2, "法定代表人身份证明书"),
    QUALIFICATION_CERTIFICATE(1, "相关附件", 3, "相关资质证"),
    BUSINESSLICENCE_BINARYCODE(1, "相关附件", 4, "营业执照二维码"),
    PERSON_IDCARD(2, "自然人相关附件", 1, "自然人身份证"),

    // 标准文本管理
    STANDARD_TEXT(1, "标准文本", 1, "标准文本"),
    STANDARD_OTHER(1, "标准文本", 2, ""),
    DRAFT_TEXT(2, "起草说明",1, "起草说明"),
    SUGGESTION_TABLE(3, "文本评审",1, "评审意见表"),

    // 单位配置
    GROUP_CORP_WATERMARK(1, "集团签约主体", 1, "合同水印"),
    ASSET_CORP_WATERMARK(2, "资产签约主体", 1, "合同水印"),
    SHARE_CORP_WATERMARK(3, "股份签约主体", 1, "合同水印"),
    ENTRUST_TEMPLATE(4, "授权委托书模板", 1, "授权委托书模板"),
    HONESTDUTY_TEMPLATE(5, "廉洁从业责任书模板", 1, "廉洁从业责任书模板"),
    SAFEPROTOCOL_TEMPLATE(6, "安全协议模板", 1, "安全协议模板"),
    KEEPSECRET_TEMPLATE(7, "保密承诺函", 1, "保密承诺函"),
    PAYMENTCHECK_TEMPLATE(8, "付款审批表模板", 1, ""),

    // 合同准备
    PREPARE_ABOUT_ZYT(1, "相关附件", 1, "中标通知书/议价报告/谈判记录"),
    PREPARE_ABOUT_OFFEREE(1, "相关附件", 2, "相对人法定代表人证明/相对人授权委托书"),
    PREPARE_ABOUT_KEEPSECRET(1, "相关附件", 3, "保密协议"),
    PREPARE_ABOUT_OTHER(1, "相关附件", 4, ""),
    PREPARE_BIG(2, "大附件", 1, ""),
    PREPARE_MASTER(3, "线下主合同附件", 1, ""),

    // 签约依据
    PREPARE_BASIS_INFOR(1, "签约依据信息", 1, "依据文件"),

    // 订立发起
    MAKE_ENCRYPT(1, "合同加密附件", 1, ""),
    MAKE_NORMAL(1, "附件", 2,""),
    MAKE_BIG(1, "大附件", 3,""),

    // 合同签署
    SIGN_SIGNATURE(1, "相关附件", 1, "合同签字页扫描件"),
    SIGN_OTHER(1, "相关附件", 2, ""),

    // 履行选择
    FULFIL_SELECT(1, "附件", 1, ""),

    // 合同转让签署
    TRANSFER_PRO(1, "相关附件", 1, "转让协议"),
    TRANSFER_ATTA(1, "相关附件", 2, "转让附件"),

    // 审批附件
    APPROVE_ATTA(1, "审批附件", 1, "审批附件"),
    TEXT_COMPARE(2, "用于文本比对的附件", 1, "用于文本比对的附件");

    private int type;

    private String typeName;

    private int code;

    private String attaName;

    private AttachmentEnum(int type, String typeName, int code, String attaName){
        this.type = type;
        this.code = code;
        this.typeName = typeName;
        this.attaName = attaName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAttaName() {
        return attaName;
    }

    public void setAttaName(String attaName) {
        this.attaName = attaName;
    }}
