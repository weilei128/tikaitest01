package com.pcitc.legalAffairs.service.dps.impl;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeInfoService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeConsts;
import com.pcitc.legalAffairs.service.authorize.AuthorizeInfoService;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @description 事项授权申请(审批完成退回接口)
 * @author leigang
 * @date 2020年3月26日 14:21:25
 *
 */
@Component
@Slf4j
public class AppCallBackComplete implements DpsComplete<CallBackVo> {

    private AuthorizeInfoService authorizeInfoService;
    private IAuthorizeInfoService iauthorizeInfoService;

    @Autowired
    public void setAuthorizeInfoService(AuthorizeInfoService authorizeInfoService) {
        this.authorizeInfoService = authorizeInfoService;
    }

    @Autowired
    public void setIAuthorizeInfoService(IAuthorizeInfoService iauthorizeInfoService) {
        this.iauthorizeInfoService = iauthorizeInfoService;
    }

    @Override
    public String getCategoryCode() {
        return DpsCategoryEnum.DpsCategory_Map.getCategoryCode();
    }

    @Transactional
    @Override
    public void execute(CallBackVo callBackVo) {
        Integer backType = callBackVo.getCallBackType();
        String businessId = callBackVo.getBusinessId();
        FwAuthorizeInfo fwAuthorizeInfo = new FwAuthorizeInfo();
        businessId = Utils.getHandlerStr(businessId);
        fwAuthorizeInfo.setfId(Long.parseLong(businessId));
        if (backType == TypeEnum.ExecuteFinish.getType()) {
            //审批同意
            authorizeInfoService.approvePass(Long.parseLong(businessId));
        } else if (backType == TypeEnum.ExecuteRevert.getType()) {
            //审批退回
            authorizeInfoService.approveRefuse(Long.parseLong(businessId));
        }
    }

    @Override
    public void start(int type, StartVo startVo) {
        String businessId = startVo.getBusinessId();
        businessId = Utils.getHandlerStr(businessId);
        if (type == 0) {
            //验证
            FwAuthorizeInfo service = iauthorizeInfoService.getById(businessId);
            if (service == null) {
                throw new BaseException("该单据不存在,请仔细检查业务id是否输错", 500);
            }
            return;
        }
        FwAuthorizeInfo fwAuthorizeInfo = new FwAuthorizeInfo();
        fwAuthorizeInfo.setfId(Long.parseLong(businessId));
        fwAuthorizeInfo.setfWorkFlowId(TypeEnum.ApprovalIng.getType());
        fwAuthorizeInfo.setfStatus(AuthorizeConsts.AuthorizeStatus.APPLICATION_APPROVING);
        iauthorizeInfoService.updateByIdApproval(fwAuthorizeInfo);
    }


}
