package com.pcitc.legalAffairs.controller.litigate.dispute;

import java.util.List;
import java.util.stream.Collectors;

import com.pcitc.legalAffairs.vo.litigate.dispute.DisputeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeBo;
import com.pcitc.legalAffairs.bo.litigate.dispute.DisputeRelationDropBo;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeRelationService;
import com.pcitc.legalAffairs.service.litigate.dispute.LitigateDisputeService;

import io.swagger.annotations.ApiOperation;

/**
 * 纠纷关联单Controller
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("dispute/relation")
public class DisputeRelationController {

	@Autowired
	private LitigateDisputeService disputeService;
	@Autowired
	private LitigateDisputeRelationService relationService;
	
	@ApiOperation("更新关联单列表")
	@PostMapping("update")
	public Result<?> update(@RequestBody DisputeBo bo) {
		if (bo == null || bo.getRelated() == null) {
			return Result.data(null);
		}
		Long disputeId = bo.getFId();
		List<Long> relatedIds = bo.getRelated().stream().map(DisputeVo::getId).collect(Collectors.toList());
		relationService.update(disputeId, relatedIds);
		return Result.data(null);
	}
	
	@ApiOperation("追加关联单")
	@PostMapping("append")
	public Result<?> append(@RequestBody DisputeBo bo) {
		if (bo == null || bo.getRelated() == null) {
			return Result.data(null);
		}
		Long disputeId = bo.getFId();
		List<Long> relatedIds = bo.getRelated().stream().map(DisputeVo::getId).collect(Collectors.toList());
		relationService.appendRelation(disputeId, relatedIds);
		return Result.data(null);
	}

	@ApiOperation("删除关联关系")
	@PostMapping("delete")
	public Result<?> delete(@RequestParam Long disputeId) {
		relationService.dropRelation(disputeId);
		return Result.status(true);
	}
	
	@ApiOperation("删除关联关系")
	@PostMapping("deleteBatch")
	public Result<?> deleteBatch(@RequestBody DisputeRelationDropBo data) {
		relationService.dropRelation(data.getDisputeId(), data.getRelatedIds());
		return Result.status(true);
	}

	@ApiOperation("获取关联单列表")
	@GetMapping("getList/{disputeId}")
	public Result<List<DisputeVo>> getList(@PathVariable Long disputeId) {
		List<DisputeVo> relatedDisputes = disputeService.getRelatedDisputes(disputeId);
		return Result.data(relatedDisputes);
	}
}
