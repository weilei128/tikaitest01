package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeApproveBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.service.litigate.dispute.DisputeStatus;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeReportingService;

/**
 * 纠纷填报Controller
 * @author meihongli
 *
 */
@RestController
@RequestMapping("dispute/reporting")
public class DisputeReportingController {

	@Autowired
	private LitigateDisputeReportingService disputeService;
	
	/**
	 * 临时保存纠纷填报
	 * @param bo
	 * @return
	 */
	@PostMapping("saveTempo")
	public Result<Long> saveTempo(@RequestBody DisputeBo bo) {
		bo.setFStatus(DisputeStatus.DISPUTE_REPORTING);
		Long id = disputeService.save(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 保存纠纷填报
	 * @param bo
	 * @return
	 */
	@PostMapping("save")
	public Result<Long> save(@RequestBody DisputeBo bo) {
		// 应由工作流控制状态的变更
//		bo.setFStatus(DisputeStatus.DISPUTE_APPROVING);
		Long id = disputeService.save(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 修改纠纷填报(也用作诉前争议补报为纠纷)临时保存
	 * @param bo
	 * @return
	 */
	@PostMapping("updateTempo")
	public Result<Long> updateTempo(@RequestBody DisputeBo bo) {
		bo.setFStatus(DisputeStatus.DISPUTE_REPORTING);
		Long id = disputeService.update(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 修改纠纷填报(也用作诉前争议补报为纠纷)
	 * @param bo
	 * @return
	 */
	@PostMapping("update")
	public Result<Long> update(@RequestBody DisputeBo bo) {
		// 应由工作流控制状态的变更
//		bo.setFStatus(DisputeStatus.DISPUTE_APPROVING);
		Long id = disputeService.update(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 删除
	 * @param disputeId
	 * @return
	 */
	@PostMapping("remove")
	public Result<?> remove(@RequestParam String disputeId) {
		boolean success = disputeService.remove(disputeId);
		Result<?> result = Result.status(success);
		return result;
	}

	/**
	 * 批量删除
	 * @param disputeId
	 * @return
	 */
	@PostMapping("removeBatch")
	public Result<?> removeBatch(@RequestBody List<String> disputeId) {
		boolean success = disputeService.remove(disputeId);
		Result<?> result = Result.status(success);
		return result;
	}
	
	/**
	 * 废弃
	 * @param bo
	 * @return
	 */
	@PostMapping("discard")
	public Result<?> discard(@RequestBody DisputeBo bo) {
		disputeService.discard(bo);
		Result<?> result = Result.status(true);
		return result;
	}
	
	/**
	 * 审批单据
	 * @param bo
	 * @return
	 */
	@PostMapping("approve")
	public Result<?> approve(@RequestBody DisputeApproveBo bo) {
		disputeService.approve(bo);
		Result<?> result = Result.status(true);
		return result;
	}

	
	@PostMapping("getById")
	public Result<DisputeBo> getById(@RequestParam String disputeId) {
		DisputeBo bo = disputeService.getbyId(disputeId);
		Result<DisputeBo> result = Result.data(bo);
		return result;
	}

	@PostMapping("queryList")
	public Result<IPage<DisputeBo>> queryList(@RequestBody DisputeQueryBo query) {
		IPage<DisputeBo> list = disputeService.queryList(query);
		return Result.data(list);
	}
}
