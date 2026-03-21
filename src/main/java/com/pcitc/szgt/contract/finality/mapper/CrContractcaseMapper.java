package com.pcitc.szgt.contract.finality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pcitc.szgt.contract.finality.entity.CrContractcase;
import com.pcitc.szgt.contract.finality.model.ContractCaseData;
import com.pcitc.szgt.contract.finality.model.ContractCaseModel;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-04
 */
public interface CrContractcaseMapper extends BaseMapper<CrContractcase> {
    
	public ContractCaseModel queryTotalAndAmount(ContractCaseData caseData);
	
	public Integer queryFulfilContractCount(ContractCaseData caseData);
	
	public Integer queryBackdateCount(ContractCaseData caseData);
	
	public Integer queryNormallyCount(ContractCaseData caseData);
	
}
