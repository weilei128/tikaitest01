package com.pcitc.szgt.contract.interactive.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.interactive.entity.CrExecutepayment;
import com.pcitc.szgt.contract.interactive.model.FinancialProof;
import com.pcitc.szgt.contract.interactive.model.FinancialProofVo;
import com.pcitc.szgt.contract.interactive.service.FinancialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("iac/financial")
@Slf4j
public class FinancialInteractiveController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FinancialService financialService;

    @Value("${secretKey}")
    private String secretKey;

    /**
     *  接收财务凭证
     * @param financialProof
     */
    @PostMapping("proof")
    public DataResult<?> receiveProof(@RequestBody FinancialProofVo financialProof) {

        log.info("receiveProof invoke>>>>>");
        log.info("currTime:" + financialProof.getCurrTime());
        log.info("data:" + financialProof.getJsondata());
        log.info("sign:" + financialProof.getSign());


        String data = financialProof.getJsondata();
        String md5 = DigestUtils.md5DigestAsHex((data + financialProof.getCurrTime() + secretKey).getBytes());
        log.info("compute sign:" + md5);
        if(!md5.equals(financialProof.getSign())){
            throw new BaseException("数据信息不合法", 500);
        }

        List<FinancialProof> l = null;
        try {
            l = objectMapper.readValue(data, new TypeReference<List<FinancialProof>>() {
            });
        } catch (IOException e) {
            throw new BaseException("json 解析失败", 500);
        }

        financialService.receiveFinancialProof(l);

        return DataResult.success(null);

    }

    /**
     * 财务凭证查询
     * @return
     */
    @GetMapping("proof")
    public DataResult<List<CrExecutepayment>> queryProof(@RequestParam String contractId){
        return DataResult.success(financialService.query(contractId));
    }

}
