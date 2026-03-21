package com.pcitc.szgt.contract.interactive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.config.FinancialConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.interactive.entity.CrExecutepayment;
import com.pcitc.szgt.contract.interactive.entity.FinancialLog;
import com.pcitc.szgt.contract.interactive.mapper.CrExecutepaymentMapper;
import com.pcitc.szgt.contract.interactive.mapper.FinancialLogMapper;
import com.pcitc.szgt.contract.interactive.model.ContractInfo;
import com.pcitc.szgt.contract.interactive.model.FinancialProof;
import com.pcitc.szgt.contract.interactive.model.RespData;
import com.pcitc.szgt.contract.interactive.service.FinancialService;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    private FinancialConfig financialConfig;

    @Autowired
    private CrExecutepaymentMapper executepaymentMapper;

    @Autowired
    private CrContractbasicMapper contractbasicMapper;

    @Autowired
    private FinancialLogMapper financialLogMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 合同->财务 发送合同数据信息
     * @param contractInfo
     * @return
     */
    @Override
    public void sendDataToFinancial(ContractInfo contractInfo) {

        if(!financialConfig.check()){
            return;
        }

        FinancialLog financialLog = new FinancialLog();
        financialLog.setContractCode(contractInfo.getContractCode());
        financialLog.setContractId(contractInfo.getContractId());
        financialLog.setContractName(contractInfo.getContractName());
        financialLog.setSyskey(financialConfig.getKey());
        financialLog.setSyscode(financialConfig.getSysCode());

        log.info("sendDataToFinancial...start");
        log.info("contractInfo:" + contractInfo);

        //准备数据
        List<ContractInfo> dataList = new ArrayList<>();
        dataList.add(contractInfo);
        String jsondata = null;
        try {
            jsondata = objectMapper.writeValueAsString(dataList);
        } catch (JsonProcessingException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error("json data 解析失败");
            log.error(trace.toString());
            financialLog.setException(trace.toString());
            financialLog.setCreatetime(LocalDateTime.now());
            financialLog.setStatus(0);
            financialLogMapper.insert(financialLog);
            return;
        }

        String yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH").format(new Date());
        String text = financialConfig.getSysCode() + financialConfig.getKey() + yyyyMMddHH + jsondata;
        String ciphertText = DigestUtils.md5DigestAsHex(text.getBytes());

        financialLog.setTime(yyyyMMddHH);
        financialLog.setJsondata(jsondata);
        financialLog.setMd5(ciphertText);

        // 开始请求
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(financialConfig.getUrl());
        Object[] objects = new Object[0];
        String result;

        try{
            objects = client.invoke("SyncContractInfo", financialConfig.getSysCode(), jsondata, ciphertText);
            result = objects[0].toString();
//            result = mockFinancial(financialConfig.getSysCode(), jsondata, ciphertText);

        }catch (Exception e){
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error("sendDataToFinancial 请求失败");
            log.error(trace.toString());
            financialLog.setException(trace.toString());
            financialLog.setCreatetime(LocalDateTime.now());
            financialLog.setStatus(0);
            financialLogMapper.insert(financialLog);
            return;
        };

        financialLog.setResult(result);

        //获取结果
        RespData respData = null;
        try {
            respData = objectMapper.readValue(result, RespData.class);
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error("sendDataToFinancial result 解析失败");
            log.error(trace.toString());
            financialLog.setException(trace.toString());
            financialLog.setCreatetime(LocalDateTime.now());
            financialLog.setStatus(2);
            financialLogMapper.insert(financialLog);
            return;
        }

        financialLog.setCreatetime(LocalDateTime.now());
        financialLog.setFalg(respData.getFalg());
        if("success".equals(respData.getFalg())){
            financialLog.setStatus(1);
        }else{
            financialLog.setStatus(0);
        }

        financialLogMapper.insert(financialLog);

        log.info("sendDataToFinancial result:" + result);
    }

    /**
     * 接收财务凭证
     * @param financialProofs
     */
    @Override
    @Transactional
    public void receiveFinancialProof(List<FinancialProof> financialProofs) {

        log.info("datas:" + financialProofs);

        List<String> seqIds = financialProofs.stream().map(FinancialProof::getZcmisod).collect(Collectors.toList());

        QueryWrapper<CrContractbasic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(CrContractbasic::getRuleSerialNum, seqIds);

        List<CrContractbasic> crContractbasics = contractbasicMapper.selectList(queryWrapper);
        Map<String, String> dataMap = crContractbasics.stream().collect(Collectors.toMap(CrContractbasic::getRuleSerialNum, CrContractbasic::getContractID, (k1, k2) -> k2));

        for(FinancialProof financialProof:financialProofs){
            CrExecutepayment crExecutepayment = new CrExecutepayment();
            BeanUtils.copyProperties(financialProof, crExecutepayment);
            crExecutepayment.setContractID(financialProof.getZcmisod());

            QueryWrapper<CrExecutepayment> queryExist = new QueryWrapper<>();
            queryExist.lambda().eq(CrExecutepayment::getZhtzfh, financialProof.getZhtzfh());
            Integer integer = executepaymentMapper.selectCount(queryExist);
            if(integer > 0){
                crExecutepayment.setModifiedTime(LocalDateTime.now());
                executepaymentMapper.update(crExecutepayment, queryExist);
            }else{
                crExecutepayment.setCreateTime(LocalDateTime.now());
                crExecutepayment.setPayID(UUIDUtils.getUUID());
                executepaymentMapper.insert(crExecutepayment);
            }
        }

    }

    /**
     * 查询合同的付款信息
     * @return
     */
    public List<CrExecutepayment> query(String contractId){

        QueryWrapper<CrExecutepayment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CrExecutepayment::getContractID, contractId);

        return executepaymentMapper.selectList(queryWrapper);
    }

    /**
     * 模拟财务接口
     * @return
     */
    public String mockFinancial(String sysCode, String jsonData, String md5){
        String yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH").format(new Date());
        String text = sysCode + "GXTZFSSC" + yyyyMMddHH + jsonData;
        String ciphertText = DigestUtils.md5DigestAsHex(text.getBytes());
        RespData respData = new RespData();

        if(!ciphertText.equals(md5)){
            respData.setFalg("fail");
            respData.setId(UUIDUtils.getUUID());
            respData.setMess("密文验证失败");
            try {
                return objectMapper.writeValueAsString(respData);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        List<ContractInfo> list = null;
        try {
            list = objectMapper.readValue(jsonData, new TypeReference<List<ContractInfo>>(){});
        } catch (IOException e) {
            respData.setFalg("fail");
            respData.setId(UUIDUtils.getUUID());
            respData.setMess("json解析失败");
            try {
                return objectMapper.writeValueAsString(respData);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }

        respData.setFalg("success");
        respData.setId(UUIDUtils.getUUID());
        respData.setMess("成功");
        try {
            return objectMapper.writeValueAsString(respData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
