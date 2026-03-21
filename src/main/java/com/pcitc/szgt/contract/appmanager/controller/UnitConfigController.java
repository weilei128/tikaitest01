package com.pcitc.szgt.contract.appmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.szgt.contract.appmanager.model.UnitConfigResultVo;
import com.pcitc.szgt.contract.appmanager.model.UnitConfigSaveVo;
import com.pcitc.szgt.contract.appmanager.service.UnitConfigService;
import com.pcitc.szgt.contract.common.DataResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UnitConfigController" , tags = "应用管理-单位配置")
@RestController
@RequestMapping("/appmanage/unitconfig")
public class UnitConfigController {

    @Autowired
    private UnitConfigService unitConfigService;

    @ApiOperation(value = "query" , notes = "根据机构ID查询数据")
    @GetMapping("query")
    public DataResult<UnitConfigResultVo> queryUnitConfigByOuid(@RequestParam String orgId) {
        return unitConfigService.queryUnitConfig(orgId);
    }
    
    @ApiOperation(value = "save" , notes = "单位配置保存方法")
    @PostMapping("save")
    public DataResult<?> saveUnitConfig(@RequestBody UnitConfigSaveVo unitConfigSaveVo) {
        return unitConfigService.saveUnitConfig(unitConfigSaveVo);
    }

    @GetMapping("printatta")
    public DataResult<?> getPrintAtta(@RequestParam String orgId) {
        return unitConfigService.getPrintAtta(orgId);
    }

    @ApiOperation(value = "getWaterMark" , notes = "获取合同水印图片")
    @GetMapping("getWaterMark")
    public DataResult<?>  getWaterMark(@RequestParam String contractId) {
        return unitConfigService.getWaterMark(contractId);
    }

    @ApiOperation(value = "getWaterMark2" , notes = "获取水印")
    @GetMapping("getWaterMark2")
    public DataResult<?>  getWaterMark2(@RequestParam Integer orgId) {
        return DataResult.success(unitConfigService.queryWatermarkByOrgId(orgId));
    }

    @ApiOperation(value = "gethonestduty" , notes = "获取廉洁责任书")
    @GetMapping("gethonestduty")
    public DataResult<?>  getHonestduty(@RequestParam Integer orgId) {
        return DataResult.success(unitConfigService.queryHonestdutyByOrgId(orgId));
    }

    @ApiOperation(value = "getsafeprotocol" , notes = "获取安全协议")
    @GetMapping("getsafeprotocol")
    public DataResult<?>  getSafeProtocol(@RequestParam Integer orgId) {
        return DataResult.success(unitConfigService.querySafeProtocolByOrgId(orgId));
    }

    @ApiOperation(value = "getkeepsecret" , notes = "获取保密承诺函")
    @GetMapping("getkeepsecret")
    public DataResult<?>  getKeepsecret(@RequestParam Integer orgId) {
        return DataResult.success(unitConfigService.queryKeepsecretByOrgId(orgId));
    }

}
