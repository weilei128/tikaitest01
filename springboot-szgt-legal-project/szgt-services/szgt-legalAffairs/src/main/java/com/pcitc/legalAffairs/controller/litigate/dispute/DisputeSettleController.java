package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettleChangeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSettledBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeSettleMethodChangeService;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeSettleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 纠纷办理Controller
 * @author meihongli
 */
@RestController
@RequestMapping("dispute/settle")
public class DisputeSettleController {

    @Autowired
    private LitigateDisputeSettleMethodChangeService settleMethodChangeService;
    @Autowired
    private LitigateDisputeSettleService settleService;

    /**
     * 变更纠纷处理方式
     * @param bo
     * @return
     */
    @RequestMapping("changeSettleMethod")
    public Result changeSettleMethod(@RequestBody DisputeSettleChangeBo bo) {
        Long disputeId = bo.getFkDisputeId();
        Long id = settleMethodChangeService.changeSettleMethod(disputeId, bo);
        Result result = Result.data(id);
		return result;
    }

    /**
     * 查看纠纷处理方式变更记录
     * @param disputeId
     * @return
     */
    @RequestMapping("listSettleMethodChanges")
    public Result listSettleMethodChange(@RequestParam String disputeId) {
        List<DisputeSettleChangeBo> changes = settleMethodChangeService.getByDisputeId(disputeId);
        Result<List<DisputeSettleChangeBo>> result = Result.data(changes);
        return result;
    }

    /**
     * 结案并关闭
     * @param bo
     * @return
     */
    @RequestMapping("settleAndClose")
    public Result settledAndClose(@RequestBody DisputeSettledBo bo) {
        Long id = settleService.settleAndCloseCase(bo.getFkDisputeId(), bo.getFkDisputeName(), bo);
        return Result.data(id);
    }

    /**
     * 结案并执行
     * @param bo
     * @return
     */
    @RequestMapping("settleAndExecute")
    public Result settledAndExecute(@RequestBody DisputeSettledBo bo) {
        Long id = settleService.settleAndExecute(bo.getFkDisputeId(), bo.getFkDisputeName(), bo);
        return Result.data(id);
    }
    
    /**
     * 查询纠纷单据
     * @param query
     * @return
     */
	@PostMapping("queryList")
	public Result queryList(@RequestBody DisputeQueryBo query) {
		IPage<DisputeBo> list = settleService.queryList(query);
		return Result.data(list);
	}
	
	/**
	 * 查询结案信息
	 * @param disputeId
	 * @return
	 */
	@PostMapping("getSettledInfo")
	public Result getSettleInfo(@RequestParam String disputeId) {
		DisputeSettledBo settleInfo = settleService.getSettleInfo(disputeId);
		return Result.data(settleInfo);
	}

}