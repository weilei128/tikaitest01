package com.pcitc.szgt.contract.backcontract.service;

import com.pcitc.szgt.contract.backcontract.entity.ContractQuery;
import com.pcitc.szgt.contract.common.DataResult;

/**
 * @author 臧传军
 * @date 2021-01-20 17:18:05
 **/
public interface BackContractService {

    DataResult list(ContractQuery contractQuery);

}
