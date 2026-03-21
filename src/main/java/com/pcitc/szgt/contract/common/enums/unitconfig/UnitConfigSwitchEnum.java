package com.pcitc.szgt.contract.common.enums.unitconfig;

/**
 * 单位配置开关枚举
 */
public enum UnitConfigSwitchEnum {
    CONTRACTPREPARECHECK(1, "审查审批开关配置-合同准备审查审批"),
    CONTRACTENDCHECK(2, "审查审批开关配置-合同终结审查审批"),
    PERFORMPAYMENTCHECK(3, "审查审批开关配置-履行结算审查审批");

    private int code;
    private String text;

    private UnitConfigSwitchEnum(int code, String text){
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
