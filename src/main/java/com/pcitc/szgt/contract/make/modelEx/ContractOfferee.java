package com.pcitc.szgt.contract.make.modelEx;

import com.pcitc.szgt.contract.offeree.model.OffereeContractVo;
import lombok.Data;

import java.util.List;

/*
 * 合同相对人
 * */
@Data
public class ContractOfferee {
    public String contractId;
    public String offereeId;
    public String offereeName;
    public String offereelinkman;
    public String linkmanposition;
    public String phone;
    public String email;
    public String fax;
    public String corporation;//法人代表

    public String bankname;//开户行
    public String Bankuk;//开户行单位
    public String bankacount;//银行账号
    public String offereeSort;//相对人分类
    public List<OffereeContractVo> ends;//异常履约情况
    public Integer endsCount;//异常履约情况数量
    public List<OffereeContractVo> cases;//发案情况
    public Integer casesCount;//发案情况数量
}
