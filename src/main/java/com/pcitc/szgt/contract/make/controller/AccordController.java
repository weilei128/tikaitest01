package com.pcitc.szgt.contract.make.controller;


import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.modelEx.Accord;
import com.pcitc.szgt.contract.make.service.ICrContractaccordoaotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 签约依据管理
 * </p>
 *
 * @author ziranzhou
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/make/accord")
public class AccordController {

    @Autowired
    private ICrContractaccordoaotherService iCrContractaccordoaotherService;

    /*
     * 签约依据新增
     * */
    @RequestMapping(value = "/addAccord", method = RequestMethod.POST)
    public DataResult addAccord(@RequestBody Accord accord) {
        return DataResult.success(iCrContractaccordoaotherService.addAccord(accord));
    }

    /*
     * 签约依据新增
     * */
    @RequestMapping(value = "/delAccord", method = RequestMethod.POST)
    public DataResult delAccord(@RequestParam(required = true) String Id) {
        return DataResult.success(iCrContractaccordoaotherService.delAccord(Id));
    }

    /*
     * 签约依据查看
     * */
    @RequestMapping(value = "/getAccordById", method = RequestMethod.GET)
    public DataResult getAccordById(@RequestParam(required = true) String Id) {
        return iCrContractaccordoaotherService.getAccordById(Id);
    }

    /*
     * 签约依据修改
     * */
    @RequestMapping(value = "/updteAccord", method = RequestMethod.POST)
    public DataResult updateAccord(@RequestBody Accord accord) {
        return DataResult.success(iCrContractaccordoaotherService.updateAccord(accord));
    }

    /*
     * 签约依据查询
     * */
    @RequestMapping(value = "/queryAccord", method = RequestMethod.POST)
    public DataResult queryAccord(@RequestParam(required = false) String accordCode, @RequestParam(required = false) String accordName,
                                  @RequestParam(required = false) Integer accordSource, @RequestParam(required = false) Integer accordType,
                                  @RequestParam(required = false) String createdBy, @RequestParam(required = false) String orgID,
                                  @RequestParam(required = false) Integer useCount, @RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer isValid,
                                  String userName) {
        return  iCrContractaccordoaotherService.queryAccord(accordCode, accordName, accordSource, accordType,
                createdBy, userName, orgID, useCount, pageNum, pageSize, isValid);
    }
}
