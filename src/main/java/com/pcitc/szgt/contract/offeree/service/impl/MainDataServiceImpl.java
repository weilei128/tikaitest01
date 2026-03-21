package com.pcitc.szgt.contract.offeree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pcitc.szgt.contract.config.MainDataConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.offeree.entity.MdmTime;
import com.pcitc.szgt.contract.offeree.mapper.MdmTimeMapper;
import com.pcitc.szgt.contract.offeree.model.InMainDataRespModel;
import com.pcitc.szgt.contract.offeree.model.MainDataRespModel;
import com.pcitc.szgt.contract.offeree.service.MainDataService;
import com.pcitc.szgt.contract.offeree.service.MdmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class MainDataServiceImpl implements MainDataService {

    @Autowired
    private MdmService mdmService;

    @Autowired
    private MainDataConfig mainDataConfig;

    @Autowired
    private MdmTimeMapper mdmTimeMapper;

    @Transactional
    public Map<String, String> updateLastTime(String sysCode){

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String end = dateTimeFormatter.format(ldt);
        String start = "";

        QueryWrapper<MdmTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MdmTime::getSysCode, sysCode);
        queryWrapper.last("for update");

        MdmTime mdmTime = mdmTimeMapper.selectOne(queryWrapper);
        if(mdmTime == null){
            start = "1970-01-01 00:00:00";

            MdmTime newMdmTime = new MdmTime();
            newMdmTime.setLasttime(ldt);
            newMdmTime.setSysCode(sysCode);
            mdmTimeMapper.insert(newMdmTime);
        } else {
            LocalDateTime lasttime = mdmTime.getLasttime();
            if(lasttime == null){
                start = "1970-01-01 00:00:00";

                mdmTime.setLasttime(ldt);
                mdmTimeMapper.updateById(mdmTime);
            }else{
                start = dateTimeFormatter.format(lasttime);

                mdmTime.setLasttime(ldt);
                mdmTimeMapper.updateById(mdmTime);
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        return map;

    }

    /**
     * 获取外部单位
     */
    @Override
    public void fetchMainData() {

        if(!mainDataConfig.check()){
            return;
        }

        log.error("fetchMainData revoke");

        Map<String, String> timeMap = updateLastTime(mainDataConfig.getSysCode());
        String start = timeMap.get("start");
        String end = timeMap.get("end");

        System.out.println("start:" + start);
        System.out.println("end:" + end);

        Set<MainDataRespModel> mainDataRespModels = mdmService.fetchAllData(start, end, 100, 1);
        mdmService.tranfer(mainDataRespModels);
    }

    /**
     * 手动拉取外部单位
     */
    @Override
    public void fetchMainDataManual(String start) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = simpleDateFormat.format(new Date());

        Set<MainDataRespModel> mainDataRespModels = mdmService.fetchAllData(start, endtime, 100, 1);
        mdmService.tranfer(mainDataRespModels);

    }

    /**
     * 拉取内部单位
     */
    @Override
    public void fetchInMainData() {

        if(!mainDataConfig.check()){
            return;
        }

        log.error("fetchMainData revoke");

        Map<String, String> timeMap = updateLastTime(mainDataConfig.getInSysCode());
        String start = timeMap.get("start");
        String end = timeMap.get("end");

        System.out.println("start:" + start);
        System.out.println("end:" + end);

        Set<InMainDataRespModel> mainDataRespModels = mdmService.fetchAllInData(start, end, 100, 1);
        mdmService.transferIn(mainDataRespModels);
    }

    /**
     * 手动拉取内部单位
     */
    @Override
    public void fetchInMainDataManual(String start) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = simpleDateFormat.format(new Date());

        Set<InMainDataRespModel> mainDataRespModels = mdmService.fetchAllInData(start, endtime, 100, 1);
        mdmService.transferIn(mainDataRespModels);
    }
}
