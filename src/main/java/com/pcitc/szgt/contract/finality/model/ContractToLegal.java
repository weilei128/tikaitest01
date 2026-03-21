package com.pcitc.szgt.contract.finality.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContractToLegal {

    private String ruleSerialNum ;//合同序号

    private String contractNum  ;//合同编号

    private String contractName  ;//合同名称

    private String typename1 ;//合同类别

    private String typename2 ;//

    private String typename3 ;//

    private String typename4 ;//

    private Integer type1 ;//合同类别

    private Integer type2 ;//合同类别

    private Integer type3 ;//合同类别

    private Integer type4 ;//合同类别

    private LocalDate caseDate ;//发案日期

    private BigDecimal totalAmount ;//发案金额

    private String ramark ;//发案原因

    private String offereeNames ;//相对人名称


}
