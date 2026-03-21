package com.pcitc.szgt.contract.agencyquery.service;

import com.pcitc.szgt.contract.agencyquery.entity.ContractQueryDTO;

/**
 * @author 臧传军
 * @date 2021-01-20 09:08:57
 **/
public interface ContractService {
    ContractQueryDTO selectById(String contractId);
}
