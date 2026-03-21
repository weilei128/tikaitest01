package com.pcitc.szgt.contract.make.modelEx;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 合同物料
 * */
public class BidItem {
    public String Id;
    public String materialId;//物料ID
    public String standCode;//原编码
    public String code;//物料/物料组编码
    public String name;//物料/物料组名称
    public String unit;//计量单位
    public String deliveryDate;//交付时间
    public BigDecimal amount;//数量
    public BigDecimal price;//单价
    public BigDecimal rate;//税率
    public BigDecimal subTotal;//小计
    public String orderId;
    public Integer itemKind;
    public String groupName;
    public String contractID;
    public String remark03;//质量标准
    public String remark08;//采购备注
    public String materialLevel;//等级
    public String materialLevelName;//等级
    public Integer Currency;//币种
    public String CurrencyName;//币种

}
