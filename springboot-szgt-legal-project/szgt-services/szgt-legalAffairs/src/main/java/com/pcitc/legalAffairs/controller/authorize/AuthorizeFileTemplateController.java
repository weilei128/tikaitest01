package com.pcitc.legalAffairs.controller.authorize;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeFileTempQueryBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeFileTemplateBo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeFileTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权书模板管理Controller
 * @author meihongli
 */
@RestController
@RequestMapping("authorize/template")
public class AuthorizeFileTemplateController {

    @Autowired
    private AuthorizeFileTemplateService fileTempService;

    @PostMapping("save")
    public Result save(@RequestBody AuthorizeFileTemplateBo bo) {
        fileTempService.save(bo);
        Result result = Result.status(true);
		return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody AuthorizeFileTemplateBo bo) {
        fileTempService.update(bo);
        Result result = Result.status(true);
		return result;
    }

    @PostMapping("delete")
    public Result delete(@RequestBody List<Long> bo) {
        fileTempService.delete(bo);
        Result result = Result.status(true);
		return result;
    }

    /**
     * 启用
     */
    @PostMapping("enable")
    public Result enable(@RequestParam Long templateId) {
        fileTempService.enable(templateId);
        Result result = Result.status(true);
		return result;
    }

    /**
     * 禁用
     */
    @PostMapping("disable")
    public Result disable(@RequestParam Long templateId) {
        fileTempService.disable(templateId);
        Result result = Result.status(true);
		return result;
    }

    /**
     * 批量启用
     */
    @PostMapping("enableBatch")
    public Result enableBatch(@RequestBody List<Long> bo) {
        fileTempService.enable(bo);
        Result result = Result.status(true);
		return result;
    }

    /**
     * 批量禁用
     */
    @PostMapping("disableBatch")
    public Result disableBatch(@RequestBody List<Long> bo) {
        fileTempService.disable(bo);
        Result result = Result.status(true);
		return result;
    }

    /**
     * 根据ID查询
     * @param templateId
     * @return
     */
    @PostMapping("getById")
    public Result getById(@RequestParam String templateId) {
        AuthorizeFileTemplateBo byId = fileTempService.getById(templateId);
        Result result = Result.data(byId);
		return result;
    }

    /**
     * 分页列表
     * @param templateId
     * @return
     */
    @PostMapping("page")
    public Result page(@RequestBody AuthorizeFileTempQueryBo templateId) {
        IPage<AuthorizeFileTemplateBo> page = fileTempService.page(templateId);
        Result result = Result.data(page);
		return result;
    }

}