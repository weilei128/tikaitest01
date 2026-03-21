package com.pcitc.legalAffairs.controller.dps;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.legalAffairs.service.dps.DpsManagerService;
import com.pcitc.legalAffairs.service.dps.entity.CallBackVo;
import com.pcitc.legalAffairs.service.dps.entity.StartVo;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @description 工作流完成接口
 * @author leigang
 * @date 2020年3月26日 15:56:46
 *
 */
@RequestMapping("common/dps")
@RestController
public class DpsController {

    private DpsManagerService dpsManager;

    @Autowired
    public void setDpsManager(DpsManagerService dpsManager) {
        this.dpsManager = dpsManager;
    }

    @ApiOperation(value = "工作流启动")
    @PostMapping("start")
    public Result start(@RequestBody StartVo startVo) {
        dpsManager.start(startVo);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "工作流审批回调")
    @PostMapping("approvalCallBack")
    public Result approvalCallBack(@RequestBody CallBackVo callBackVo) {
        dpsManager.execute(callBackVo);
        return Result.data(true);
    }
    
}
