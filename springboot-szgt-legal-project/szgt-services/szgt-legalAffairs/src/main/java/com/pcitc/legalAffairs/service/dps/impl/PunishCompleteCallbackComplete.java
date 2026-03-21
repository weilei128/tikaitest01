package com.pcitc.legalAffairs.service.dps.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.punish.PunishInfoBo;
import com.pcitc.legalAffairs.service.dps.DpsComplete;
import com.pcitc.legalAffairs.service.dps.en.DpsCategoryEnum;
import com.pcitc.legalAffairs.service.dps.en.TypeEnum;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.legalAffairs.service.dps.http.Utils;
import com.pcitc.legalAffairs.service.punish.PunishService;

@Component
public class PunishCompleteCallbackComplete implements DpsComplete<CallBackVo> {

	@Autowired
	private PunishService punishService;

	@Override
	public String getCategoryCode() {
		return DpsCategoryEnum.DpsCategory_Punish_Settle.getCategoryCode();
	}

	@Override
	public void execute(CallBackVo callBackVo) {
		Integer backType = callBackVo.getCallBackType();
		String businessId = callBackVo.getBusinessId();
		Long businessIdLong = Long.valueOf(Utils.getHandlerStr(businessId));
		// 审批同意
		if (backType == TypeEnum.ExecuteFinish.getType()) {
			punishService.completeApproveConfirm(businessIdLong);
		// 审批退回
		} else if (backType == TypeEnum.ExecuteRevert.getType()) {
			punishService.completeApproveRefuse(businessIdLong);
		}

	}

	@Override
	public void start(int type, StartVo startVo) {
		String businessId = startVo.getBusinessId();
		businessId = Utils.getHandlerStr(businessId);

		if (type == 0) {
			// 验证
			PunishInfoBo service = punishService.getById(businessId);
            if (service == null) {
                throw new BaseException("该单据不存在,请仔细检查业务id是否输错", 500);
            }
            return;
        }

        punishService.completeSubmit(Long.valueOf(businessId));
	}

}
