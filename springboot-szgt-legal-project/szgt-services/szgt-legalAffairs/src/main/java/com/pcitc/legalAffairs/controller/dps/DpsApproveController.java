package com.pcitc.legalAffairs.controller.dps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.service.dps.DpsApproveService;
import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("dps")
public class DpsApproveController {

	@Autowired
	private DpsApproveService dpsApproveService;
	
	/**
	 * 
	 * @param entity
	 * @param categoryCode
	 * @param businessId
	 * @return
	 */
	@ApiOperation("审批完成")
	@PostMapping("complete")
    public Result approvalComplete(@RequestBody ExecuteContext entity) {
    	AppCallResult approveDpsComplete = dpsApproveService.approveDpsComplete(entity);
    	return Result.data(approveDpsComplete);
    }

}
