package com.pcitc.legalAffairs.controller.authorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeInfoBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeLicenseeBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeQueryBo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeInfoService;
import com.pcitc.legalAffairs.service.authorize.AuthorizeLicenseeService;

/**
 * 授权申请Controller
 * 
 * @author meihongli
 *
 */
@RestController
@RequestMapping("authorize/apply")
public class AuthorizeApplyController {

	@Autowired
	private AuthorizeInfoService authorizeInfoService;
//	@Autowired
//	private AuthorizeLicenseeService authorizeLicenseeService;
	
	/**
	 * 新增临时保存
	 * @param bo
	 * @return
	 */
	@PostMapping("saveTemp")
	public Result saveTemp(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.saveTemp(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 新增保存
	 * @param bo
	 * @return
	 */
	@PostMapping("save")
	public Result save(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.save(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 编辑临时保存
	 * @param bo
	 * @return
	 */
	@PostMapping("updateTemp")
	public Result updateTemp(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.updateTemp(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 编辑保存
	 * @param bo
	 * @return
	 */
	@PostMapping("update")
	public Result update(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.update(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 编辑临时保存, 不含被授权人信息
	 * @param bo
	 * @return
	 */
	@PostMapping("updateTempNoLicensee")
	public Result updateTempNoLicensee(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.updateTempNoLicensee(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 编辑保存, 不含被授权人信息
	 * @param bo
	 * @return
	 */
	@PostMapping("updateNoLicensee")
	public Result updateNoLicensee(@RequestBody AuthorizeInfoBo bo) {
		Long id = authorizeInfoService.updateNoLicensee(bo);
		Result result = Result.data(id);
		return result;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@PostMapping("delete")
	public Result delete(@RequestBody List<Long> id) {
		authorizeInfoService.delete(id);
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 废弃
	 * @param id
	 * @return
	 */
	@PostMapping("discard")
	public Result discard(@RequestParam Long id) {
		authorizeInfoService.discard(id);
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 根据ID获取数据
	 * @param id
	 * @return
	 */
	@PostMapping("getById")
	public Result getById(@RequestParam String id) {
		AuthorizeInfoBo bo = authorizeInfoService.getById(id);
		return Result.data(bo);
	}
	
	/**
	 * 分页列表查询
	 * @param bo
	 * @return
	 */
	@PostMapping("page")
	public Result page(@RequestBody AuthorizeQueryBo bo) {
		IPage<AuthorizeInfoBo> page = authorizeInfoService.pageAuthorizeInfo(bo);
		return Result.data(page);
	}
	
	/**
	 * 保存被授权人信息
	 * @param bos
	 * @return
	 */
	@PostMapping("saveLicensee")
	public Result saveLicensee(@RequestBody AuthorizeInfoBo bos) {
//		authorizeLicenseeService.save(bos.getLicensee(), bos.getFId());
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 批量修改被授权人信息
	 * @param bos
	 * @return
	 */
	@PostMapping("updateLicensee")
	public Result updateLicensee(@RequestBody AuthorizeInfoBo bos) {
//		authorizeLicenseeService.update(bos.getLicensee(), bos.getFId());
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 批量删除被授权人信息
	 * @param ids
	 * @return
	 */
	@PostMapping("deleteLicensee")
	public Result deleteLicensee(@RequestBody List<Long> ids) {
//		authorizeLicenseeService.delete(ids);
		Result result = Result.status(true);
		return result;
	}
	
	/**
	 * 根据授权ID查询被授权人信息
	 * @param authorizeId
	 * @return
	 */
	@PostMapping("getLicenseeByAuthorizeId")
	public Result getLicenseeByAuthorizeId(@RequestParam String authorizeId) {
//		List<AuthorizeLicenseeBo> list = authorizeLicenseeService.getByAuthorizeId(authorizeId);
//		return Result.data(list);
		Result result = Result.status(true);
		return result;
	}
}
