package com.pcitc.legalAffairs.controller.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeInfoService;
import com.pcitc.legalAffairs.vo.authorize.AuthorizeInfoVo;

/**
 * 事项授权查询
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("authorize/query")
public class AuthorizeQueryController {

	@Autowired
	private AuthorizeInfoService authorizeInfoService;
	
	@PostMapping("query")
	public Result query(@RequestBody AuthorizeQueryBo bo) {
		IPage<AuthorizeInfoVo> authorizeQuery = authorizeInfoService.authorizeQuery(bo);
		return Result.data(authorizeQuery);
	}
}
