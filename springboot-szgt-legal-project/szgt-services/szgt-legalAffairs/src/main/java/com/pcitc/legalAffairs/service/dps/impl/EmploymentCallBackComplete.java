package com.pcitc.legalAffairs.service.dps.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryHireInfoService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgBasicService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryOrgLogService;
import com.pcitc.legalAffairs.dbService.Intermediary.impl.IntermediaryOperate;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryHireInfo;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgBasic;
import com.pcitc.legalAffairs.po.Intermediary.FwIntermediaryOrgLog;
import com.pcitc.legalAffairs.po.dispute.FwLitigateDispute;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.vo.Intermediary.FwIntermediaryOrgBasicVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @description 中介机构聘用(审批完成接口)
 * @author leigang
 * @date 2020年3月26日 14:21:25
 *
 */
@Component
public class EmploymentCallBackComplete implements DpsComplete<CallBackVo> {


    @Autowired
    private IFwIntermediaryHireInfoService hireInfoService;
    @Autowired
    private IFwIntermediaryOrgLogService ologService;
    @Autowired
    private IFwIntermediaryOrgBasicService orgService;


    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Iae.getCategoryCode();
    }

    @Transactional
    @Override
    public void execute(CallBackVo callBackVo) {
        Integer backType = callBackVo.getCallBackType();
        String businessId = callBackVo.getBusinessId();
        FwIntermediaryHireInfo hireInfo = new FwIntermediaryHireInfo();
        businessId = Utils.getHandlerStr(businessId);
        hireInfo.setfId(Long.parseLong(businessId));
        if (backType == TypeEnum.ExecuteFinish.getType()) {
            //审批同意
            hireInfo.setfWorkFlowId(TypeEnum.Finish.getType());
            hireInfoService.updateById(hireInfo);

            // 记录聘用操作
            FwIntermediaryHireInfo hire = hireInfoService.getById(businessId);
            List<FwIntermediaryOrgBasicVo> orgs = orgService.queryByHireId(Long.parseLong(businessId)).getData();
            if (orgs != null) {
                List<FwIntermediaryOrgLog> ologList = orgs.stream().map(org -> {
                	
                	FwIntermediaryOrgBasic basic = new FwIntermediaryOrgBasic();
                	basic.setfId(org.getfId());
                	basic.setfHiredTimes(org.getfHiredTimes() + 1);
                	orgService.updateById(basic);
                	
                    FwIntermediaryOrgLog olog = new FwIntermediaryOrgLog();
                    olog.setFkIntermediaryId(org.getfId());
                    olog.setfOperate(IntermediaryOperate.HIRE);
                    olog.setFkOperatorName(hire.getfHandler());
                    return olog;
                }).collect(Collectors.toList());
                ologService.saveBatch(ologList);
            }
        } else if (backType == TypeEnum.ExecuteRevert.getType()) {
            //审批退回
            hireInfo.setfWorkFlowId(TypeEnum.Revert.getType());
            hireInfoService.updateById(hireInfo);
        }
    }

    @Override
    public void start(int type,StartVo startVo) {
        String businessId = startVo.getBusinessId();
        businessId = Utils.getHandlerStr(businessId);

        if (type == 0) {
            //验证
            FwIntermediaryHireInfo service = hireInfoService.getById(businessId);
            if (service == null) {
                throw new BaseException("该单据不存在,请仔细检查业务id是否输错", 500);
            }
            return;
        }

        FwIntermediaryHireInfo hireInfo = new FwIntermediaryHireInfo();
        hireInfo.setfId(Long.parseLong(businessId));
        hireInfo.setfWorkFlowId(TypeEnum.ApprovalIng.getType());
        hireInfoService.updateById(hireInfo);
    }


}
