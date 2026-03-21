package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeFileDictBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeFileDictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dispute/archive")
public class DisputeArchiveController {

    @Autowired
    private LitigateDisputeFileDictService archiveService;

    @PostMapping("save")
    public Result save(@RequestBody DisputeFileDictBo bo) {
        Long id = archiveService.save(bo);
        return Result.data(id);
    }

    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<DisputeFileDictBo> bo) {
        archiveService.save(bo);
        return Result.status(true);
    }
    
    @PostMapping("update")
    public Result update(@RequestBody DisputeFileDictBo bo) {
        Long id = archiveService.update(bo);
        return Result.data(id);
    }

    @PostMapping("delete")
    public Result delete(@RequestBody List<Long> bo) {
        archiveService.delete(bo);
        return Result.status(true);
    }
    
    @PostMapping("deleteByFileId")
    public Result deleteByFileId(@RequestBody List<Long> fileIds) {
    	archiveService.deleteByFileId(fileIds);
        return Result.status(true);
    }

    @PostMapping("deleteByDisputeId")
    public Result deleteByDisputeId(@RequestBody List<Long> fileIds) {
    	archiveService.deleteByDisputeId(fileIds);
    	return Result.status(true);
    }
    
    @PostMapping("getResult")
    public Result getResult(@RequestBody DisputeFileDictBo bo) {
        List<DisputeFileDictBo> res = archiveService.getByConditions(bo);
        return Result.data(res);
    }

    @PostMapping("getById")
    public Result getById(@RequestParam String id) {
        DisputeFileDictBo res = archiveService.getById(id);
        return Result.data(res);
    }

    @PostMapping("archive")
    public Result archive(@RequestParam Long disputeId) {
        archiveService.archiveDispute(disputeId);
        return Result.status(true);
    }

}