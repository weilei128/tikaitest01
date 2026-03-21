package com.pcitc.legalAffairs.controller.punish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.IdBo;
import com.pcitc.legalAffairs.bo.punish.PunishFileBo;
import com.pcitc.legalAffairs.service.punish.PunishFileService;

/**
 * 处罚信息附件Controller
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("punish/file")
public class PunishFileController {

	@Autowired
	private PunishFileService punishFileService;
	
	@PostMapping("append")
	public Result<Long> append(@RequestBody PunishFileBo bo) {
		Long id = punishFileService.append(bo);
		return Result.data(id);
	}
	
	@PostMapping("delete")
	public Result<?> delete(@RequestBody IdBo id) {
		punishFileService.delete(id.getId());
		return Result.status(true);
	}

}