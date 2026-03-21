package com.pcitc.szgt.contract.agencyquery.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.share.ShareConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.perform.service.IPerformService;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.agencyquery.entity.ContractQueryDTO;
import com.pcitc.szgt.contract.agencyquery.entity.WorkflowLogDTO;
import com.pcitc.szgt.contract.agencyquery.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * oa代办维护
 * @author 臧传军
 * @date 2021-01-19 13:35:03
 **/
@RestController
@Slf4j
public class AgencyqueryController {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShareConfig shareConfig;

    @Autowired
    private IPerformService performService;

    @Autowired
    private ContractService contractService;

    /**
     * 代办查询
     * @param fBusinessName
     * @param fExecutorName
     * @param fBusinessId
     * @param fTaskId
     * @param fExecutorCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/agencyquery")
    public DataResult<?> workflowloglist(String fBusinessName,String fExecutorName,String fBusinessId,String fTaskId,
    		String fExecutorCode,@RequestParam(required = true,defaultValue = "1") Integer pageNum,
    		@RequestParam(required = true,defaultValue ="10") Integer pageSize){
    	
        Map<String, Object> params = new HashMap<>();
        if(StringUtils.isNotEmpty(fBusinessId)){params.put("fBusinessId",fBusinessId);}
        if(StringUtils.isNotEmpty(fBusinessName)){params.put("fBusinessName",fBusinessName);}
        if(StringUtils.isNotEmpty(fTaskId)){params.put("fTaskId",fTaskId);}
        if(StringUtils.isNotEmpty(fExecutorCode)){params.put("fExecutorCode",fExecutorCode);}
        if(StringUtils.isNotEmpty(fExecutorName)){params.put("fExecutorName",fExecutorName);}
        params.put("pageSize",pageSize);
        params.put("pageNum",pageNum);
        params.put("fCategoryCode","szgt_contract");
        String result = restTemplateUtil.getRequest("http://" + shareConfig + "/oatask/query?"+queryToParams(params), null);
        log.info("------Result=={}",result);
        try {
            DataResult<WorkflowLogDTO> dataResult = objectMapper.
                    readValue(result, new TypeReference<DataResult<WorkflowLogDTO>>() {
                    });
            WorkflowLogDTO dto=dataResult.getData();
            List<Object> obj= (List<Object>) dto.getRecords().stream().map(c->{
                LinkedHashMap list= (LinkedHashMap) c;
                String contractId=String.valueOf(list.get("fBusinessId"));
                ContractQueryDTO info = contractService.selectById(contractId);
                if(info!=null){
                    //合同ID
                    list.put("ContractId",info.getContractId());
                    //合同编号
                    list.put("ContractNum",info.getContractNum());
                    //合同名称
                    list.put("ContractName",info.getContractName());
                    //合同对方经办人
                    list.put("InnerOperator",info.getInnerOperator());
                    //主办部门
                    list.put("MainDeptName",info.getMainDeptName());
                    //主办单位
                    list.put("MainOrgName",info.getMainOrgName());
                    //创建时间
                    list.put("CreatedDate",info.getCreatedDate());
                }
                return list;
            }).collect(Collectors.toList());
            dto.setRecords(obj);
            dataResult.setData(dto);
            return dataResult;
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            log.error(trace.toString());
            throw new BaseException("对象解析失败", 500);
        }
    }

    /**
     * 	拼接参数
     * @param params
     * @return
     */
    private String queryToParams(Map<String, Object> params){
        StringBuilder sb=new StringBuilder();
        params.forEach((k,v)->{
            sb.append(k+"="+v);
            sb.append("&");
        });
        return sb.toString().substring(0,sb.toString().length()-1);
    }

}
