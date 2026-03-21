package com.pcitc.szgt.contract.perform.entityEx;


/*
 * 合同转让
 * */
public class ContractTrans {
    public String transId;
    public String transferNo;
    public String contractId;
    public Integer transferType;//转让类型
    public Integer applicant;//申请方
    public String transferReason;//转让原因
    public String offereeId;//转让相对人ID ","
    public String offereeName;//转让相对人名称
    public Integer mySignBodyCode;//签约主体id
    public String mySignBodyName;//签约主体名称
    public String oldoffereeId;//原相对人ID
    public String oldoffereeName;//原相对人名称
    public String oldmySignBodyCode;//原签约主体id
    public String oldmySignBodyName;//原签约主体名称
}
