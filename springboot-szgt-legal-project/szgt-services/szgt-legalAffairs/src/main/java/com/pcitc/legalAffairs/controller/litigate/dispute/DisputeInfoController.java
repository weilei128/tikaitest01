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
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeQueryBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeSearchBo;
import com.pcitc.legalAffairs.service.litigate.dispute.DisputeStatus;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeService;
import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;

/**
 * 诉前争议Controller
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("dispute/info")
public class DisputeInfoController {

	@Autowired
	private LitigateDisputeService disputeService;
	
	@PostMapping("save")
	public Result save(@RequestBody DisputeBo bo) {
		bo.setFStatus(DisputeStatus.PRE_LITIGATE_SETTLING);
		Long id = disputeService.save(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 保存诉前争议并进入诉讼
	 * @param bo
	 * @return
	 */
	@PostMapping("saveAndLitigate")
	public Result saveNLitigate(@RequestBody DisputeBo bo) {
		// 20200411
		// 由工作流控制进入诉讼状态
		bo.setFStatus(DisputeStatus.PRE_LITIGATE_APPROVING);
		Long id = disputeService.save(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	@PostMapping("remove")
	public Result remove(@RequestParam String disputeId) {
		boolean success = disputeService.remove(disputeId);
		Result result = Result.status(success);
		return result;
	}

	@PostMapping("removeBatch")
	public Result removeBatch(@RequestBody List<String> disputeId) {
		boolean success = disputeService.remove(disputeId);
		Result result = Result.status(success);
		return result;
	}
	
	@PostMapping("update")
	public Result update(@RequestBody DisputeBo bo) {
		Long id = disputeService.update(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 修改诉前争议信息并进入诉讼
	 * @param bo
	 * @return
	 */
	@PostMapping("updateAndLitigate")
	public Result updateNLitigate(@RequestBody DisputeBo bo) {
		// 20200411
		// 由工作流控制进入诉讼状态
		bo.setFStatus(DisputeStatus.PRE_LITIGATE_APPROVING);
		Long id = disputeService.update(bo);
		Result<Long> result = Result.data(id);
		return result;
	}
	
	/**
	 * 废弃
	 * @param bo
	 * @return
	 */
	@PostMapping("discard")
	public Result discard(@RequestBody DisputeBo bo) {
		disputeService.discard(bo);
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 办结
	 * @param bo
	 * @return
	 */
	@PostMapping("finish")
	public Result finish(@RequestBody DisputeBo bo) {
		disputeService.finish(bo);
		Result result = Result.status(true);
		return result;
	}
	
	@PostMapping("getById")
	public Result getById(@RequestParam String disputeId) {
		DisputeBo bo = disputeService.getbyId(disputeId);
		Result<DisputeBo> result = Result.data(bo);
		return result;
	}

	@PostMapping("queryList")
	public Result queryList(@RequestBody DisputeQueryBo query) {
		IPage<DisputeBo> list = disputeService.queryList(query);
		return Result.data(list);
	}

	@PostMapping("searchPage")
	public Result searchPage(@RequestBody(required = false) DisputeSearchBo query) {
		IPage<DisputeVo> list = disputeService.searchList(query);
		return Result.data(list);
	}

}
