package com.pcitc.szgt.contract.offeree.controller;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.offeree.entity.MdmOffereedata;
import com.pcitc.szgt.contract.offeree.model.*;
import com.pcitc.szgt.contract.offeree.service.MainDataService;
import com.pcitc.szgt.contract.offeree.service.OffereeService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offereemanage")
@Slf4j
public class OffereeController {

    @Autowired
    private OffereeService offereeService;

    @Autowired
    private MainDataService mainDataService;

    @PostMapping("add")
    public DataResult add(@RequestBody OffereeVo offereeVo) {
        return offereeService.add(offereeVo);
    }

    @GetMapping("detail")
    public DataResult<OffereeResultVo> queryDetail(@RequestParam String offereeId) {
        return offereeService.queryDetail(offereeId);
    }

    @ApiOperation(value = "query" , notes = "维护、创建相对人列表")
    @GetMapping("query")
    public DataResult<PageData<OffereeInfoVo>> queryOfferee(OffereeQueryVo offereeQueryVo) {
        return offereeService.queryOfferee(offereeQueryVo);
    }

    @PostMapping("delete")
    public DataResult deleteOfferee(@RequestParam String offereeId) {
        offereeService.deleteOfferee(offereeId);
        return DataResult.success(null);
    }

    @PostMapping("update")
    public DataResult updateOfferee(@RequestBody OffereeVo offereeVo) {
        offereeService.updateOfferee(offereeVo);
        return DataResult.success(null);
    }

    @GetMapping("mdm/query")
    public DataResult<PageData<MdmOffereedataVo>> queryMdm(MdmQueryVo mdmQueryVo) {
        return offereeService.queryMdm(mdmQueryVo);
    }

    @PostMapping("addMDMOfferee")
    public DataResult addMDMOfferee(@RequestParam String mdmOffereeId) {
        return DataResult.success(offereeService.addMDMOfferee(mdmOffereeId));
    }

    /*
     * 获取相对人联系人信息
     * */
    @GetMapping("getOffereeLinkMan")
    public DataResult getOffereeLinkMan(String offereeId) {
        return offereeService.getOffereeLinkMan(offereeId);
    }

    /*
     * 获取相对人银行信息
     * */
    @GetMapping("getoffereeBank")
    public DataResult getoffereeBank(String offereeId) {
        return offereeService.getoffereeBank(offereeId);
    }

    /**
     * 手动同步外部单位
     * @return
     */
    @GetMapping("manualMDMsyn")
    public DataResult manualMDMsyn(String start){
        mainDataService.fetchMainDataManual(start);
        return DataResult.success(null);
    }

    /**
     * 手动同步内部单位
     * @param start
     * @return
     */
    @GetMapping("manualMDMsynin")
    public DataResult manualMDMsynIn(String start){
        mainDataService.fetchInMainDataManual(start);
        return DataResult.success(null);
    }
}
