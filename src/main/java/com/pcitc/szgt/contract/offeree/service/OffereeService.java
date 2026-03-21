package com.pcitc.szgt.contract.offeree.service;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.offeree.entity.MdmOffereedata;
import com.pcitc.szgt.contract.offeree.model.*;

import java.util.List;

/**
 * @Author lihe
 * @Desc 相对人管理service
 * @Date 2019/04/12
 */

public interface OffereeService {

    /**
     * 添加相对人
     *
     * @param offereeVo
     * @return
     */
    DataResult add(OffereeVo offereeVo);

    /**
     * 查询相对人详情
     *
     * @param offereeId
     * @return
     */
    DataResult<OffereeResultVo> queryDetail(String offereeId);

    /**
     * 查询相对人信息
     *
     * @return
     */
    DataResult<PageData<OffereeInfoVo>> queryOfferee(OffereeQueryVo offereeQueryVo);

    /**
     * 删除相对人信息
     *
     * @param offereeId
     */
    void deleteOfferee(String offereeId);


    /**
     * 更新相对人信息
     *
     * @param offereeVo
     */
    void updateOfferee(OffereeVo offereeVo);


    /**
     * 相对人引用列表查询
     *
     * @return
     */
    DataResult<PageData<MdmOffereedataVo>> queryMdm(MdmQueryVo mdmQueryVo);

    /*
     * 相对人引用
     * */
    String addMDMOfferee(String mdmOffereeId);

    /*
     * 获取联系人信息
     * */
    DataResult getOffereeLinkMan(String offereeId);

    /*
     * 获取银行信息
     * */
    DataResult getoffereeBank(String offereeId);

}
