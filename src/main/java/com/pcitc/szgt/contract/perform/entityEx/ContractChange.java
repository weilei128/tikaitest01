package com.pcitc.szgt.contract.perform.entityEx;


import java.util.List;

/*
 * 合同变更实体
 * */
public class ContractChange {
    public String taskId;//待办id
    public String contractId;//合同ID
    public String changeId;//变更ID
    public String changeCode;//转让编号
    public Integer proposer;//变更方 我方 、对方、双方、其他
    public String changeReason;//变更原因
    public Integer[] changeType;//变更事项 标的金额、履行期限、主体名称、标的明细、其他
    public String changeAmount;//变更后金额（含税合同金额）
    public String changeTaxAmount;//税额
    public String changeNoTaxAmount;//不含税额
    public Integer isMakeSure;//履行期限是否确定
    public String performStart;//履行期限开始时间
    public String PerformEnd;//履行期限结束时间
    public String term;
    public Integer mySignBodyCode;//签约主体
    public String mySignBodyName;//签约主体
    public String offereeId;//相对人新
    public String offereeName;//相对人名称新
    public String oldOffereeId;
    public String oldOffereeName;
    public String other;//其他
    /*
     * 变更物料
     * */
    public List<ContractChangeBid> changeBidList;
}
