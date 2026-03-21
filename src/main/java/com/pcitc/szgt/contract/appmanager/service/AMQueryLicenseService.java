package com.pcitc.szgt.contract.appmanager.service;

import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseQueryVo;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseResultVo;
import com.pcitc.szgt.contract.appmanager.model.AmQueryLicenseSetVo;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;

import java.util.List;

public interface AMQueryLicenseService {
    /*
     * 设置查询授权管理
     * */
    boolean setAMQueryLicense(AmQueryLicenseSetVo amQueryLicenseSetVo);

    /*
     *查询授权
     * */
    DataResult<PageData<AmQueryLicenseResultVo>> queryAMQueryLicense(AmQueryLicenseQueryVo queryVo);

    /*
     * 根据用户删除查询授权
     * */
    boolean delAMQueryLicense(String userId);

    /*
     * 根据ID获取用户查询授权数据
     * */
    DataResult<List<AmQueryLicenseResultVo>> getAMQueryLicenseById(String userId);
}
