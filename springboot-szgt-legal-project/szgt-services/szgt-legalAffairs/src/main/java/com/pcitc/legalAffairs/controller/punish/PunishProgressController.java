package com.pcitc.legalAffairs.controller.punish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.IdBo;
import com.pcitc.legalAffairs.bo.punish.PunishProgressBo;
import com.pcitc.legalAffairs.service.punish.PunishProgressService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("punish/progress")
public class PunishProgressController {

	@Autowired
	private PunishProgressService punishProgressService;
	
	@ApiOperation("保存或编辑")
	@PostMapping("save")
	public Result<Long> saveOrUpdate(@RequestBody PunishProgressBo bo) {
		Long id = punishProgressService.saveOrUpdate(bo);
		return Result.data(id);
	}
	
	@ApiOperation("删除")
	@PostMapping(path="delete")
	public Result<?> delete(@RequestBody IdBo id) {
		punishProgressService.delete(id.getId());
		return Result.status(true);
	}
	
	@ApiOperation("根据处罚ID删除")
	@PostMapping("deleteByPunishId")
	public Result<?> saveOrUpdate(@RequestBody IdBo id) {
		punishProgressService.deleteByPunishId(id.getId());
		return Result.status(true);
	}
	
	@ApiOperation("根据ID获取数据")
	@GetMapping("getById/{id}")
	public Result<PunishProgressBo> getById(@PathVariable String id) {
		PunishProgressBo progress = punishProgressService.getById(id);
		return Result.data(progress);
	}
	
	@ApiOperation("根据处罚ID获取数据列表")
	@GetMapping("getByPunishId/{id}")
	public Result<List<PunishProgressBo>> getByPunishId(@PathVariable String id) {
		List<PunishProgressBo> progress = punishProgressService.getByPunishId(id);
		return Result.data(progress);
	}

	
}
