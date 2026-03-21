package com.pcitc.legalAffairs.service.dps.impl;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.litigate.dispute.ILitigateDisputeService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.service.dps.DpsApproveService;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.service.litigate.dispute.DisputeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @description 诉求争议录入
 * @author leigang
 * @date 2020年3月26日 14:21:25
 *
 */
@Component
public class CdnCallBackComplete implements DpsComplete<CallBackVo> {

    @Autowired
    private ILitigateDisputeService disputeService;
    @Autowired
    private DpsApproveService approveService;


    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Cdn.getCategoryCode();
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
            //审批通过 进入纠纷填报
            fwLitigateDispute.setfStatus(DisputeStatus.DISPUTE_REPORTING);
            // 抹除userid
            fwLitigateDispute.setfUserId(-1);
//            approveService.approveDpsStart(disputeService.getById(businessId), DpsCategoryEnum.DpsCategory_Report, null);
            disputeService.updateByIdApproval(fwLitigateDispute);
        } else if (callBackType == TypeEnum.ExecuteRevert.getType()) {
            //审批退回
            fwLitigateDispute.setfWorkFlowId(TypeEnum.Revert.getType());
            fwLitigateDispute.setfStatus(DisputeStatus.PRE_LITIGATE_RETURN);
            disputeService.updateByIdApproval(fwLitigateDispute);
        }
    }

    @Transactional
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
        fwLitigateDispute.setfStatus(DisputeStatus.PRE_LITIGATE_APPROVING);
        fwLitigateDispute.setfWorkFlowId(TypeEnum.ApprovalIng.getType());
        disputeService.updateByIdApproval(fwLitigateDispute);
    }


}
