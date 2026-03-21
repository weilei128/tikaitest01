package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeDictBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeDictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dispute/dict")
public class DisputeDictController {

    @Autowired
    private LitigateDisputeDictService dictService;

    @PostMapping("save")
    private Result save(@RequestBody DisputeDictBo bo) {
        Long id = dictService.save(bo);
        return Result.data(id);
    }

    @PostMapping("update")
    private Result update(@RequestBody DisputeDictBo bo) {
        Long id = dictService.update(bo);
        return Result.data(id);
    }

    @PostMapping("delete")
    private Result delete(@RequestBody List<Long> bo) {
        dictService.delete(bo);
        return Result.status(true);
    }

    @PostMapping("getAll")
    private Result getAll() {
        List<DisputeDictBo> list = dictService.getAll();
        return Result.data(list);
    }

    @PostMapping("getById")
    private Result getById(@RequestParam String id) {
        DisputeDictBo bo = dictService.getById(id);
        return Result.data(bo);
    }
}