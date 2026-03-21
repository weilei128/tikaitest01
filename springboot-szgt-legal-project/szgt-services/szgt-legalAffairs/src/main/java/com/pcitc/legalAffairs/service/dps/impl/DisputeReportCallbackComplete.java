package com.pcitc.legalAffairs.service.dps.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;

@Component
public class DisputeReportCallbackComplete implements DpsComplete<CallBackVo> {

    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Report.getCategoryCode();
    }

    @Transactional
    @Override
    public void execute(CallBackVo callBackVo) {
    }

    @Transactional
    @Override
    public void start(int type, StartVo startVo) {
    }

}
