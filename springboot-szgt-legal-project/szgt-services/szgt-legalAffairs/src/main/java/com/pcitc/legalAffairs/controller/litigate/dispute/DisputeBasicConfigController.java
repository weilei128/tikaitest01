package com.pcitc.legalAffairs.controller.litigate.dispute;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBasicConfigBo;
import com.pcitc.legalAffairs.service.litigate.dispute.DisputeBasicConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @description 纠纷管理基础配置
 * @author leigang
 * @date 2020年4月3日 14:28:37
 *
 */
@RestController
@RequestMapping("disputeBasicConfig")
public class DisputeBasicConfigController {

    @Autowired
    private DisputeBasicConfigService disputeBasicConfigService;

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody DisputeBasicConfigBo disputeBasicConfigBo) {
        disputeBasicConfigService.saveOrUpdate(disputeBasicConfigBo);
        return Result.success(ResultCode.SUCCESS);
    }

    @PostMapping("queryState")
    public Result queryState(@RequestBody DisputeBasicConfigBo disputeBasicConfigBo) {
        return Result.data(disputeBasicConfigService.queryState(disputeBasicConfigBo));
    }

}
