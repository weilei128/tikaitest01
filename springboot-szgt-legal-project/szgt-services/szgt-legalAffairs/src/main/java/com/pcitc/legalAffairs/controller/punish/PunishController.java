package com.pcitc.legalAffairs.controller.punish;

import java.util.List;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.punish.PunishInfoBo;
import com.pcitc.legalAffairs.bo.punish.PunishQueryBo;
import com.pcitc.legalAffairs.service.punish.PunishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 处罚信息相关Controller
 * 
 * @author meihongli
 */
@RestController
@RequestMapping("punish")
public class PunishController {
	
	@Autowired
	private PunishService punishService;

	@ApiOperation("新增或编辑")
	@PostMapping("save")
	public Result<?> save(@RequestBody PunishInfoBo bo) {
		return Result.data(punishService.saveOrUpdate(bo));
	}

	@ApiOperation("删除单条数据")
	@PostMapping("delete")
	public Result<?> delete(@RequestParam Long id) {
		punishService.delete(id);
		return Result.status(true);
	}

	@ApiOperation("批量删除数据")
	@PostMapping("deleteBatch")
	public Result<?> deleteBatch(@RequestBody List<Long> id) {
		punishService.delete(id);
		return Result.status(true);
	}

	@ApiOperation("废弃数据")
	@PostMapping("discard")
	public Result<?> discard(@RequestParam Long id) {
		punishService.discard(id);
		return Result.status(true);
	}

	@ApiOperation("根据ID获取")
	@GetMapping("getById/{id}")
	public Result<?> getById(@PathVariable String id) {
		return Result.data(punishService.getById(id));
	}

	@ApiOperation("分页查询数据列表")
	@PostMapping("page")
	public Result<?> page(@RequestBody PunishQueryBo bo) {
		return Result.data(punishService.page(bo));
	}

}
