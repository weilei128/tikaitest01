package com.pcitc.legalAffairs.controller.Intermediary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictLogBo;
import com.pcitc.legalAffairs.bo.Intermediary.ConflictQueryBo;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryConflictLogService;
import com.pcitc.legalAffairs.dbService.Intermediary.IFwIntermediaryInterestConflictService;

/**
 * 利益冲突名单
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("conflict")
public class FwIntermediaryInterestConflictController {

	@Autowired
	private IFwIntermediaryInterestConflictService conflictService;
	@Autowired
	private IFwIntermediaryConflictLogService clogService;
	
	/**
	 * 列表查询
	 * @param bo
	 * @return
	 */
	@PostMapping("page")
	public Result pageData(@RequestBody ConflictQueryBo bo) {
		return conflictService.pageData(bo);
	}
	
	/**
	 * 禁用中介
	 * @param uscCode
	 * @return
	 */
	@PostMapping("ban")
	public Result ban(@RequestBody ConflictLogBo bo) {
		return conflictService.ban(bo);
	}
	
	/**
	 * 启用中介
	 * @param uscCode
	 * @return
	 */
	@PostMapping("unban")
	public Result unban(@RequestBody ConflictLogBo bo) {
		return conflictService.unban(bo);
	}
	
	/**
	 * 删除中介
	 * @param uscCode
	 * @return
	 */
	@PostMapping("delete")
	public Result delete(@RequestParam String uscCode) {
		return conflictService.delete(uscCode);
	}
	
	/**
	 * 检查可用性
	 * @param uscCode
	 * @return
	 */
	@PostMapping("checkIsBanned")
	public Result checkIsBanned(@RequestParam String uscCode) {
		boolean banned = conflictService.isBanned(uscCode);
		return Result.data(banned);
	}
	
	/**
	 * 查看操作日志
	 * @param conflictId
	 * @return
	 */
	@PostMapping("getOperateLog")
	public Result getOperateLog(@RequestParam String conflictId) {
		return clogService.getByConflictIdAndGetResult(conflictId);
	}
}
