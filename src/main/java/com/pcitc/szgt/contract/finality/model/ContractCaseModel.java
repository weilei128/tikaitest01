package com.pcitc.szgt.contract.finality.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ContractCaseModel" , description = "合同案件类")
public class ContractCaseModel {

	@ApiModelProperty(value = "caseTotal" ,notes = "累计损失金额合同案件总数量")
	public Integer caseTotal ; 

	@ApiModelProperty(value="caseAmount" , notes = "累计合同案件损失金额(万元)")
	public BigDecimal caseAmount ;
	
	@ApiModelProperty(value="fulfilTotal" ,notes = "履行中合同总数量")
	private Integer fulfilTotal ;
	
	@ApiModelProperty(value="backdateTotal" ,notes = "倒签合同数量")
	private Integer backdateTotal ;
	
	@ApiModelProperty(value="normallyTotal",notes = "正常签订合同数量")
	private Integer normallyTotal ;
	
	@ApiModelProperty(value="backdateRate" , notes = "合同倒签率")
	private Double backdateRate ;
	
}
