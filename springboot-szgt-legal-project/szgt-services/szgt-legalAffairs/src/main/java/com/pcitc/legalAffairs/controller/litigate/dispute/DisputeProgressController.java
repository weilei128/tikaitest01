package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeProgressBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettlingBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeProgressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 纠纷处理进展Controller
 * @author meihongli
 */
@RestController
@RequestMapping("dispute/progress")
public class DisputeProgressController {

    @Autowired
    private LitigateDisputeProgressService progressService;

    @PostMapping("save")
    public Result save(@RequestBody DisputeProgressBo bo) {
        Long id = progressService.save(bo, bo.getFkDisputeId(), bo.getFkDisputeName());
        Result result = Result.data(id);
        return result;
    }

    /**
     * 批量保存进度信息
     * @param bo
     * @return
     */
    @PostMapping("saveBatch")
    public Result save(@RequestBody DisputeSettlingBo bo) {
        progressService.save(bo, bo.getDisputeId(), bo.getDisputeName());
        Result result = Result.status(true);
        return result;
    }

    /**
     * 保存进度和双方律所信息
     * @param bo
     * @return
     */
    @PostMapping("saveAll")
    public Result saveAll(@RequestBody DisputeSettlingBo bo) {
        progressService.saveAll(bo, bo.getDisputeId(), bo.getDisputeName());
        Result result = Result.status(true);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody DisputeProgressBo bo) {
        Long id = progressService.update(bo);
        Result result = Result.data(id);
        return result;
    }

    /**
     * 批量修改进度信息
     * @param bo
     * @return
     */
    @PostMapping("updateBatch")
    public Result update(@RequestBody DisputeSettlingBo bo) {
        progressService.update(bo, bo.getDisputeId(), bo.getDisputeName());
        Result result = Result.status(true);
        return result;
    }

    /**
     * 修改进度和双方律所信息
     * @param bo
     * @return
     */
    @PostMapping("updateAll")
    public Result updateAll(@RequestBody DisputeSettlingBo bo) {
        progressService.updateAll(bo, bo.getDisputeId(), bo.getDisputeName());
        Result result = Result.status(true);
        return result;
    }

    /**
     * 删除进度信息
     * @param progId
     * @return
     */
    @PostMapping("delete")
    public Result delete(@RequestParam Long progId) {
    	progressService.delete(progId);
        Result result = Result.status(true);
        return result;
    }
    
    /**
     * 批量删除进度信息
     * @param progId
     * @return
     */
    @PostMapping("deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> progId) {
    	progressService.delete(progId);
    	Result result = Result.status(true);
    	return result;
    }
    
    /**
     * 查看纠纷处理进度
     * @param disputeId
     * @return
     */
    @PostMapping("listProgress")
    public Result listProgresses(@RequestParam String disputeId) {
        List<DisputeProgressBo> progresses = progressService.getByDisputeId(disputeId, false);
        Result<List<DisputeProgressBo>> data = Result.data(progresses);
        return data;
    }

    /**
     * 查看大事记
     * @param disputeId
     * @return
     */
    @PostMapping("getEvents")
    public Result listEvents(@RequestParam String disputeId) {
        List<DisputeProgressBo> events = progressService.getByDisputeId(disputeId, true);
        Result<List<DisputeProgressBo>> data = Result.data(events);
        return data;
    }

}
