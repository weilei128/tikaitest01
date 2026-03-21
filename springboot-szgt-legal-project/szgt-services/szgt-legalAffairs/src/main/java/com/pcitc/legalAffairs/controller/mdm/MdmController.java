package com.pcitc.legalAffairs.controller.mdm;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.service.mdm.MdmService;
import com.pcitc.legalAffairs.service.mdm.entity.MdmBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @description mdm接口的包装
 * @author leigang
 * @date 2020年4月17日 14:14:02
 *
 */
@RequestMapping("/common/mdm")
@RestController
public class MdmController {

    @Autowired
    private MdmService mdmService;

    @PostMapping("getMdmData")
    public Result getMdmData(@RequestBody MdmBo mdmBo) {
        return Result.data(mdmService.getMdmData(mdmBo.getMethod(), mdmBo.getObjectParams()));
    }

}
