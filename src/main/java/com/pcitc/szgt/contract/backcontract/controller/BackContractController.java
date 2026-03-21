package com.pcitc.szgt.contract.backcontract.controller;

import com.pcitc.szgt.contract.backcontract.entity.ContractQuery;
import com.pcitc.szgt.contract.backcontract.service.BackContractService;
import com.pcitc.szgt.contract.common.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 	倒签合同
 * @author 臧传军
 * @date 2021-01-20 17:11:18
 **/
@RestController
public class BackContractController {

    @Autowired
    private BackContractService backContractService;

    /**
     * 	倒签合同列表
     * @return
     */
    @GetMapping("/backcontract")
    public DataResult backcontract(ContractQuery params){
        return backContractService.list(params);
    }
}
