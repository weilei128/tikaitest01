package com.pcitc.szgt.contract.agencyquery.service.impl;

import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractinfo;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractinfoMapper;
import com.pcitc.szgt.contract.agencyquery.entity.ContractQueryDTO;
import com.pcitc.szgt.contract.agencyquery.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 臧传军
 * @date 2021-01-20 09:12:47
 **/
@Service
public class ContractServiceimpl implements ContractService {

    @Autowired
    private CrContractinfoMapper crContractinfoMapper;

    @Autowired
    private CrContractbasicMapper crContractbasicMapper;

    @Override
    public ContractQueryDTO selectById(String contractId) {
       CrContractinfo info= crContractinfoMapper.selectById(contractId);
       CrContractbasic base=crContractbasicMapper.selectById(contractId);
        ContractQueryDTO dto=new ContractQueryDTO();
        if(info!=null){
            dto.setInnerOperator(info.getInnerOperator());
        }
        if(base!=null){
            dto.setContractId(base.getContractID());
            dto.setContractName(base.getContractName());
            dto.setContractNum(base.getContractNum());
            dto.setCreatedDate(base.getCreatedDate());
            dto.setMainDeptName(base.getMainDeptName());
            dto.setMainOrgName(base.getMainOrgName());
        }
        return dto;
    }
}
