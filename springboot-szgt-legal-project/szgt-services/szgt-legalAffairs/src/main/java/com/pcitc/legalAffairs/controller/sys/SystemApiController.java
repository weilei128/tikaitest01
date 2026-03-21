package com.pcitc.legalAffairs.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.service.userorg.UserOrgService;

@RestController("systemLawController")
@RequestMapping("law/system")
public class SystemApiController {
	
	@Autowired
	private UserOrgService userOrgService;

	@PostMapping("queryOrganization")
	public Result queryOrganization() {
		int showDepartment = 0;
		List<Map<String, Object>> orgInfo = userOrgService.getOrgInfo(showDepartment != 0);
		return Result.data(orgInfo);
	}
}
