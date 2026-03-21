package com.pcitc.legalAffairs.controller.authorize;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeExerciseBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeExerciseQueryBo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeExerciseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行权报告录入controller
 * 
 * @author meihongli
 */
@RestController
@RequestMapping("authorize/exercise")
public class AuthorizeExerciseController {

	@Autowired
	private AuthorizeExerciseService authExerciseService;

	@PostMapping("save")
	public Result save(@RequestBody AuthorizeExerciseBo bo) {
		authExerciseService.save(bo);
		Result result = Result.status(true);
		return result;
	}

	@PostMapping("update")
	public Result update(@RequestBody AuthorizeExerciseBo bo) {
		authExerciseService.update(bo);
		Result result = Result.status(true);
		return result;
	}

	@PostMapping("saveTemp")
	public Result saveTemp(@RequestBody AuthorizeExerciseBo bo) {
		authExerciseService.saveTemp(bo);
		Result result = Result.status(true);
		return result;
	}
	
	@PostMapping("updateTemp")
	public Result updateTemp(@RequestBody AuthorizeExerciseBo bo) {
		authExerciseService.updateTemp(bo);
		Result result = Result.status(true);
		return result;
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam Long exerciseId) {
		authExerciseService.delete(exerciseId);
		Result result = Result.status(true);
		return result;
	}

	@PostMapping("deleteBatch")
	public Result deleteBatch(@RequestBody List<Long> exerciseId) {
		authExerciseService.delete(exerciseId);
		Result result = Result.status(true);
		return result;
	}

	@PostMapping("getByAuthorize")
	public Result getByAuthorizeId(@RequestParam String authorizeId) {
		AuthorizeExerciseBo bo = authExerciseService.getOneByAuthorizeId(authorizeId);
		Result result = Result.data(bo);
		return result;
	}

	@PostMapping("listExercises")
	public Result listExercises(@RequestBody AuthorizeExerciseQueryBo bo) {
		IPage<AuthorizeExerciseBo> info = authExerciseService.listExerciseInfo(bo);
		Result result = Result.data(info);
		return result;
	}
}