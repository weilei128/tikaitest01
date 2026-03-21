package com.pcitc.szgt.contract.offeree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.config.MainDataConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.offeree.entity.MdmOffereedata;
import com.pcitc.szgt.contract.offeree.mapper.MdmOffereedataMapper;
import com.pcitc.szgt.contract.offeree.model.InMainDataRespModel;
import com.pcitc.szgt.contract.offeree.model.InRespData;
import com.pcitc.szgt.contract.offeree.model.MainDataRespModel;
import com.pcitc.szgt.contract.offeree.model.RespData;
import com.pcitc.szgt.contract.offeree.service.MdmService;
import com.pcitc.szgt.contract.util.UUIDUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MdmServiceImpl implements MdmService {

    @Autowired
    private MainDataConfig mainDataConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MdmOffereedataMapper mdmOffereedataMapper;

    @Transactional
    public void tranfer(Set<MainDataRespModel> dataSet) {

        System.out.println("mdmtranfer invoke");
        if(CollectionUtils.isEmpty(dataSet)){
            return;
        }
        checkIfExist(dataSet);

        for(MainDataRespModel model:dataSet){
            MdmOffereedata data = new MdmOffereedata();
            data.setOffereeCode(model.getC_001());
            data.setOffereeName(model.getC_002());
            data.setOffereeType(0);
            if("24".equals(model.getC_006())){
                data.setOffereeType(1);
            }

            boolean c_007 = "1".equals(model.getC_007());
            boolean c_008 = "1".equals(model.getC_008());

            if(c_007 && c_008){
                data.setOffereeSort("004001,004002");
            }else if(!c_007 && c_008){
                data.setOffereeSort("004002");
            }else if(c_007){
                data.setOffereeSort("004001");
            }

            data.setCategory(0);
            data.setIsValid("1");
            data.setOrgCode(model.getC_009());
            data.setTaxRegisterCode(model.getC_009());
            data.setBusinessLicenseCode(model.getC_009());
            data.setIDCard(model.getC_010());
            data.setCorporation(model.getC_015());
            data.setOfficeAddress(model.getC_017());
            data.setBankName(model.getBc_001());
            data.setOpenUints(model.getBc_009());
            data.setBankCode(model.getBc_008());
            data.setBankAcount(model.getBc_002());
            data.setSourceType(mainDataConfig.getSysCode());

            if(model.isExist()){
                data.setModifiedDate(LocalDateTime.now());
                UpdateWrapper<MdmOffereedata> updateWrapper = new UpdateWrapper<>();
                updateWrapper.lambda().eq(MdmOffereedata::getOffereeCode, model.getC_001());
                mdmOffereedataMapper.update(data, updateWrapper);
            }else{
                data.setOffereeId(UUIDUtils.getUUID());
                data.setCreatedDate(LocalDateTime.now());
                mdmOffereedataMapper.insert(data);
            }
        }

    }

    private void checkIfExist(Set<MainDataRespModel> datas){
        List<String> codes = datas.stream().map(MainDataRespModel::getC_001).collect(Collectors.toList());
        QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MdmOffereedata::getOffereeCode, codes);

        List<MdmOffereedata> mdmOffereedata = mdmOffereedataMapper.selectList(queryWrapper);
        List<String> existCodes = mdmOffereedata.stream().map(MdmOffereedata::getOffereeCode).collect(Collectors.toList());
        for(MainDataRespModel model:datas){
            if(existCodes.contains(model.getC_001())){
                model.setExist(true);
            }
        }
    }

    private void checkInIfExist(Set<InMainDataRespModel> datas){
        List<String> codes = datas.stream().map(InMainDataRespModel::getC_001).collect(Collectors.toList());
        QueryWrapper<MdmOffereedata> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(MdmOffereedata::getOffereeCode, codes);

        List<MdmOffereedata> mdmOffereedata = mdmOffereedataMapper.selectList(queryWrapper);
        List<String> existCodes = mdmOffereedata.stream().map(MdmOffereedata::getOffereeCode).collect(Collectors.toList());
        for(InMainDataRespModel model:datas){
            if(existCodes.contains(model.getC_001())){
                model.setExist(true);
            }
        }
    }

    public Set<MainDataRespModel> fetchAllData(String strtime, String endtime, Integer pageSize, Integer pageNo){
        int pageno = pageNo;
        RespData respData = fetchMainData(strtime, endtime, pageSize.toString(), pageNo.toString());
        Set<MainDataRespModel> allData = new HashSet<>(respData.getDatalist());

        while(respData.getDatalist().size() > 0){
            respData = fetchMainData(strtime, endtime, pageSize.toString(), String.valueOf(++pageno));
            allData.addAll(respData.getDatalist());
        }

        return allData;
    }

    private RespData fetchMainData(String strtime, String endtime, String pageSize, String pageNo){
//        JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
//        svr.setAddress(mainDataConfig.getUrl());
//
//        svr.setServiceClass(CxWsService.class);
//        CxWsService a = (CxWsService)svr.create();
//        String result = a.revicesForcxWs(mainDataConfig.getModelId(), mainDataConfig.getSysCode(), strtime, endtime, pageSize, pageNo);
//
//        try {
//            RespData dataResult = objectMapper.
//                    readValue(result, RespData.class);
//            return dataResult.getDatalist();
//        } catch (IOException e) {
//            StringWriter trace = new StringWriter();
//            e.printStackTrace(new PrintWriter(trace));
//            throw new BaseException("MdmServiceImpl readValue fail", 500);
//        }

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(mainDataConfig.getUrl());
        Object[] objects = new Object[0];
        String result;
        try{
            objects = client.invoke("RevicesForcxWs", mainDataConfig.getModelId(), mainDataConfig.getSysCode(), strtime, endtime, pageSize, pageNo);
            result = objects[0].toString();
        }catch (Exception e){
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            throw new BaseException("RevicesForcxWs fail", 500);
        }

        try {
            System.out.println("mdmdata:" + result);
            return objectMapper.
                    readValue(result, RespData.class);
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            throw new BaseException("MdmServiceImpl readValue fail", 500);
        }
    }

    @Override
    public Set<InMainDataRespModel> fetchAllInData(String strtime, String endtime, Integer pageSize, Integer pageNo) {

        int pageno = pageNo;

        InRespData respData = fetchInMainData(strtime, endtime, pageSize.toString(), pageNo.toString());
        Set<InMainDataRespModel> allData = new HashSet<>(respData.getDatalist());

        while(respData.getDatalist().size() > 0){
            respData = fetchInMainData(strtime, endtime, pageSize.toString(), String.valueOf(++pageno));
            allData.addAll(respData.getDatalist());
        }

        return allData;
    }

    @Override
    public void transferIn(Set<InMainDataRespModel> dataSet) {
        System.out.println("mdmtranfer invoke");
        if(CollectionUtils.isEmpty(dataSet)){
            return;
        }
        checkInIfExist(dataSet);

        for(InMainDataRespModel model:dataSet){
            MdmOffereedata data = new MdmOffereedata();
            data.setOffereeCode(model.getC_001());
            data.setOffereeName(model.getC_002());
            data.setOffereeType(0);
            data.setCategory(0);
            data.setIsValid("1");
            data.setOrgCode(model.getC_009());
            data.setTaxRegisterCode(model.getC_009());
            data.setBusinessLicenseCode(model.getC_009());
            data.setCorporation(model.getC_013());
            data.setOfficeAddress(model.getC_014());
            if("101".equals(model.getC_008())){
                //公司
                data.setCompanyType("0");
            }else if("102".equals(model.getC_008())){
                //部门
                data.setCompanyType("1");
            }
            data.setSourceType(mainDataConfig.getInSysCode());

            if(model.isExist()){
                data.setModifiedDate(LocalDateTime.now());
                UpdateWrapper<MdmOffereedata> updateWrapper = new UpdateWrapper<>();
                updateWrapper.lambda().eq(MdmOffereedata::getOffereeCode, model.getC_001());
                mdmOffereedataMapper.update(data, updateWrapper);
            }else{
                data.setOffereeId(UUIDUtils.getUUID());
                data.setCreatedDate(LocalDateTime.now());
                mdmOffereedataMapper.insert(data);
            }
        }
    }

    private InRespData fetchInMainData(String strtime, String endtime, String pageSize, String pageNo){

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(mainDataConfig.getUrl());
        Object[] objects = new Object[0];
        String result;
        try{
            objects = client.invoke("RevicesForcxWs", mainDataConfig.getInModelId(), mainDataConfig.getInSysCode(), strtime, endtime, pageSize, pageNo);
            result = objects[0].toString();
        }catch (Exception e){
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            throw new BaseException("RevicesForcxWs fail", 500);
        }

        try {
            System.out.println("mdmdata:" + result);
            return objectMapper.
                    readValue(result, InRespData.class);
        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
            throw new BaseException("MdmServiceImpl readValue fail", 500);
        }
    }
}
