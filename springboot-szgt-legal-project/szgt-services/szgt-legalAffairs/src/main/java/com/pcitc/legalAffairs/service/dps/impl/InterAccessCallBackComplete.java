package com.pcitc.legalAffairs.service.dps.impl;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgLogService;
import com.pcitc.legalAffairs.dbService.Intermediary.impl.IntermediaryOperate;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgLog;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @description 中介机构准入(审批完成接口)
 * @author leigang
 * @date 2020年3月26日 14:21:25
 *
 */
@Component
public class InterAccessCallBackComplete implements DpsComplete<CallBackVo> {


    @Autowired
    private IFwIntermediaryOrgBasicService orgService;
    @Autowired
    private IFwIntermediaryOrgLogService ologService;

    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Iaa.getCategoryCode();
    }

    @Transactional
    @Override
    public void execute(CallBackVo callBackVo) {
        Integer backType = callBackVo.getCallBackType();
        String businessId = callBackVo.getBusinessId();
        FwIntermediaryOrgBasic fwIntermediaryOrgBasic = new FwIntermediaryOrgBasic();
        businessId = Utils.getHandlerStr(businessId);
        fwIntermediaryOrgBasic.setfId(Long.parseLong(businessId));
        if (backType == TypeEnum.ExecuteFinish.getType()) {
            //审批同意
            fwIntermediaryOrgBasic.setfWorkFlowId(TypeEnum.Finish.getType());
            fwIntermediaryOrgBasic.setfAdmitTime(new Date());
            orgService.updateById(fwIntermediaryOrgBasic);

            // 记录准入操作
            FwIntermediaryOrgBasic entity = orgService.getById(businessId);
            FwIntermediaryOrgLog log = new FwIntermediaryOrgLog();
            log.setFkIntermediaryId(entity.getfId());
            log.setfOperate(IntermediaryOperate.ADMIT);
            log.setFkOperatorId(entity.getFkHandleOrgId());
            log.setFkOperatorName(entity.getFkHandleOrgName());
            ologService.save(log);

        } else if (backType == TypeEnum.ExecuteRevert.getType()) {
            //审批退回
            fwIntermediaryOrgBasic.setfWorkFlowId(TypeEnum.Revert.getType());
            orgService.updateById(fwIntermediaryOrgBasic);
        }
    }

    @Override
    public void start(int type, StartVo startVo) {
        String businessId = startVo.getBusinessId();
        businessId = Utils.getHandlerStr(businessId);

        if (type == 0) {
            //验证
            FwIntermediaryOrgBasic service = orgService.getById(businessId);
            if (service == null) {
                throw new BaseException("该单据不存在,请仔细检查业务id是否输错", 500);
            }
            return;
        }

        FwIntermediaryOrgBasic fwIntermediaryOrgBasic = new FwIntermediaryOrgBasic();
        fwIntermediaryOrgBasic.setfId(Long.parseLong(businessId));
        fwIntermediaryOrgBasic.setfWorkFlowId(TypeEnum.ApprovalIng.getType());
        orgService.updateById(fwIntermediaryOrgBasic);
    }

}
