package com.pcitc.szgt.contract.appmanager.controller;

import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseQueryVo;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseResultVo;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseSetVo;
import com.pcitc.szgt.contract.appmanager.service.AMQueryLicenseService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appmanage/amquerylicense")
public class AMQueryLicenseController {
    /*查询授权
     * */
    @Autowired
    private AMQueryLicenseService amQueryLicenseService;

    /*
     * 查询管理授权
     * */
    @PostMapping("/setAMQueryLicense")
    public DataResult setAMQueryLicense(@RequestBody AmQueryLicenseSetVo amQueryLicenseSetVo) {
        return DataResult.success(amQueryLicenseService.setAMQueryLicense(amQueryLicenseSetVo));
    }

    /*
     * 查询管理数据
     * */
    @GetMapping(value = "/queryAMQueryLicense")
    public DataResult<PageData<AmQueryLicenseResultVo>> queryAMQueryLicense(AmQueryLicenseQueryVo amQueryLicenseQueryVo) {
        return amQueryLicenseService.queryAMQueryLicense(amQueryLicenseQueryVo);
    }

    /*
     * 删除用户查询授权
     * */
    @PostMapping("/delAMQueryLicense")
    public DataResult delAMQueryLicense(@RequestParam String userId) {
        return DataResult.success(amQueryLicenseService.delAMQueryLicense(userId));
    }

    /*
     * 查看用户查询授权
     * */
    @GetMapping("/getAMQueryLicenseById")
    public DataResult<List<AmQueryLicenseResultVo>> getAMQueryLicenseById(@RequestParam String userId) {
        return amQueryLicenseService.getAMQueryLicenseById(userId);
    }
}
