package com.pcitc.legalAffairs.service.dps.impl;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.service.litigate.dispute.DisputeStatus;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @description 纠纷填报(审批完成接口)
 * @author leigang
 * @date 2020年3月26日 14:21:25
 *
 */
@Component
public class DisputeCallBackComplete implements DpsComplete<CallBackVo> {

    @Autowired
    private ILitigateDisputeService disputeService;


    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Dispute.getCategoryCode();
    }

    @Transactional
    @Override
    public void execute(CallBackVo callBackVo) {
        Integer callBackType = callBackVo.getCallBackType();
        String businessId = callBackVo.getBusinessId();
        FwLitigateDispute fwLitigateDispute = new FwLitigateDispute();
        businessId = Utils.getHandlerStr(businessId);
        fwLitigateDispute.setfId(Long.parseLong(businessId));
        if (callBackType == TypeEnum.ExecuteFinish.getType()) {
            //审批同意
            fwLitigateDispute.setfWorkFlowId(TypeEnum.Finish.getType());
            //进入纠纷办理
            fwLitigateDispute.setfStatus(DisputeStatus.DISPUTE_SETTLING);
            fwLitigateDispute.setfUpdateTime(new Date());
            disputeService.updateByIdApproval(fwLitigateDispute);
        } else if (callBackType == TypeEnum.ExecuteRevert.getType()) {
            //审批退回
            fwLitigateDispute.setfWorkFlowId(TypeEnum.Revert.getType());
            fwLitigateDispute.setfStatus(DisputeStatus.DISCARDED_RETURN);
            fwLitigateDispute.setfUpdateTime(new Date());
            disputeService.updateByIdApproval(fwLitigateDispute);
        }
    }

    @Override
    public void start(int type, StartVo startVo) {
        String businessId = startVo.getBusinessId();
        businessId = Utils.getHandlerStr(businessId);

        if (type == 0) {
            //验证
            FwLitigateDispute service = disputeService.getById(businessId);
            if (service == null) {
                throw new BaseException("该单据不存在,请仔细检查业务id是否输错", 500);
            }
            return;
        }

        FwLitigateDispute fwLitigateDispute = new FwLitigateDispute();
        fwLitigateDispute.setfId(Long.parseLong(businessId));
        fwLitigateDispute.setfStatus(DisputeStatus.DISPUTE_APPROVING);
        fwLitigateDispute.setfWorkFlowId(TypeEnum.ApprovalIng.getType());
        fwLitigateDispute.setfUpdateTime(new Date());
        fwLitigateDispute.setfSaveTime(new Date());
        disputeService.updateByIdApproval(fwLitigateDispute);
    }


}
