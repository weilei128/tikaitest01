package com.pcitc.legalAffairs.controller.dps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.dps.DpsNextBo;
import com.pcitc.legalAffairs.bo.dps.DpsQueryBo;
import com.pcitc.legalAffairs.service.dps.DpsQueryService;

@RestController
@RequestMapping("dps/query")
public class DpsQueryController {
	
	@Autowired
	private DpsQueryService dpsQueryService;
	
	@PostMapping("nextExecutor")
	public Result getNextExecutor(@RequestBody DpsQueryBo bo) {
		List<DpsNextBo> executor = dpsQueryService.getNextExecutor(bo);
		return Result.data(executor);
	}
}
