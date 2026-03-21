package com.pcitc.legalAffairs.controller.litigate.dispute;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeExecuteBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeExecuteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 纠纷执行Controller
 * @author meihongli
 *
 */
@RestController
@RequestMapping("dispute/execute")
public class DisputeExecuteController {

    @Autowired
    private LitigateDisputeExecuteService executeService;

    /**
     * 查询纠纷单据
     * @param query
     * @return
     */
	@PostMapping("queryList")
	public Result queryList(@RequestBody DisputeQueryBo query) {
		IPage<DisputeBo> list = executeService.queryList(query);
		return Result.data(list);
	}
    
    @RequestMapping("save")
    public Result save(@RequestBody DisputeExecuteBo bo) {
        Long id = executeService.save(bo, bo.getFkDisputeId());
        Result result = Result.data(id);
        return result;
    }

    @RequestMapping("update")
    public Result update(@RequestBody DisputeExecuteBo bo) {
        Long id = executeService.update(bo, bo.getFkDisputeId());
        Result result = Result.data(id);
        return result;
    }

    /**
     * 保存执行情况
     * @param bo
     * @return
     */
    @RequestMapping("saveProgress")
    public Result saveProgress(@RequestBody DisputeExecuteBo bo) {
        executeService.save(bo.getProgress(), bo.getFkDisputeId(), bo.getFId());
        Result result = Result.status(true);
        return result;
    }
    
    /**
     * 保存执行情况
     * @param bo
     * @return
     */
    @RequestMapping("updateProgress")
    public Result updateProgress(@RequestBody DisputeExecuteBo bo) {
    	executeService.update(bo.getProgress(), bo.getFkDisputeId(), bo.getFId());
    	Result result = Result.status(true);
    	return result;
    }
    
    @RequestMapping("getInfo")
    public Result getInfo(@RequestParam String disputeId) {
        DisputeExecuteBo bo = executeService.getExecuteInfo(disputeId);
        Result<DisputeExecuteBo> result = Result.data(bo);
        return result;
    }

    @RequestMapping("closeCase")
    public Result closeCase(@RequestParam Long disputeId) {
        executeService.closeCase(disputeId);
        Result result = Result.status(true);
        return result;
    }

}