package com.pcitc.legalAffairs.controller.dict;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.service.dict.DictionaryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("dict")
public class SysDictController {
	
	@ApiOperation("清除系统中数据字典信息缓存")
	@RequestMapping("clearBuffer")
	public Result clearBuffer() {
		DictionaryService.clearBuffer();
		return Result.status(true);
	}
}
