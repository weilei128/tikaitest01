package com.pcitc.szgt.contract.perform.entityEx;

import java.math.BigDecimal;

/**
 *	 查询
 */
public class ContractQuery {
    public String ruleserialNum;//合同序号
    public String contractName;//合同名称
    public String contractNum;//合同编号
    public String mainOrgId ; //主机构ID
    public Integer type1;//合同类型1
    public Integer type2;//合同类型2
    public Integer type3;//合同类型3
    public Integer type4;//合同类型4
    public String projectId;//所属项目
    public String offereeId;//相对人
    public String offereeName;//相对人名称
    public Integer mainDeptId;//主办单位
    public Integer mainOrgUserId;//经办人
    public Integer moneySource1;//资金来源1
    public Integer moneySource2;//资金来源2
    public Integer selectWay1;//选商方式1
    public Integer selectWay2;//选商方式2
    public Integer moneyFlow;//资金流向
    public BigDecimal beginContractCurrenyAmount;//标的金额
    public BigDecimal endContractCurrenyAmount;//标的金额
    public Integer curreny;//币种
    public BigDecimal beginContractAmount;//标的金额（人民币)
    public BigDecimal endContractAmount;//标的金额（人民币)
    public String beginMySiginDate;//签订日期
    public String endMySiginDate;
    public String beginCreateTime;//合同创建日期
    public String endCreateTime;
    public Integer propertyModel;//合同模块
    public Integer section;//合同环节
    public Integer pageNum;
    public Integer pageSize;
}
