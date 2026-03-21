package com.pcitc.legalAffairs.controller.authorize;

import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeInfoBo;
import com.pcitc.legalAffairs.bo.authorize.AuthorizeStampedFileBo;
import com.pcitc.legalAffairs.service.authorize.AuthorizeFileService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权书办理
 */
@RestController
@RequestMapping("authorize/file")
public class AuthorizeFileController {

    @Autowired
    private AuthorizeFileService authorizeFileService;

    /**
     * 暂存盖章授权书信息
     * @param bo
     * @return
     */
    @RequestMapping("saveTemp")
    public Result<Object> saveTemp(@RequestBody AuthorizeStampedFileBo bo) {
        authorizeFileService.saveTemp(bo);
        Result<Object> result = Result.status(true);
        return result;
    }

    /**
     * 保存并完成授权书信息
     * @param bo
     * @return
     */
    @RequestMapping("saveAndConfirm")
    public Result<Object> saveAndConfirm(@RequestBody AuthorizeStampedFileBo bo) {
        authorizeFileService.saveAndConfirm(bo);
        Result<Object> result = Result.status(true);
        return result;
    }

    /**
     * 编辑并暂存授权书信息
     * @param bo
     * @return
     */
    @RequestMapping("updateTemp")
    public Result<Object> updateTemp(@RequestBody AuthorizeStampedFileBo bo) {
        authorizeFileService.updateTemp(bo);
        Result<Object> result = Result.status(true);
        return result;
    }
    /**
     * 编辑并完成授权书信息
     * @param bo
     * @return
     */
    @RequestMapping("updateAndConfirm")
    public Result<Object> updateAndConfirm(@RequestBody AuthorizeStampedFileBo bo) {
        authorizeFileService.updateAndConfirm(bo);
        Result<Object> result = Result.status(true);
        return result;
    }

    /**
     * 打印人转移
     * @param bo
     * @return
     */
    @RequestMapping("printPersonTransfer")
    public Result<Object> printPersonTransfer(@RequestBody AuthorizeInfoBo bo) {
        authorizeFileService.printPersonTransfer(bo);
        Result<Object> result = Result.status(true);
        return result;
    }

    /**
     * 根据ID获取
     * 
     * @param id
     * @return
     */
    @RequestMapping("getById")
    public Result<Object> getById(@RequestParam String id) {
        AuthorizeStampedFileBo bo = authorizeFileService.findById(id);
        return Result.data(bo);
    }

    /**
     * 根据授权ID获取
     * @param authorizeId
     * @return
     */
    @RequestMapping("getByAuthorizeId")
    public Result<Object> getByAuthorizeId(@RequestParam String authorizeId) {
        AuthorizeStampedFileBo bo = authorizeFileService.findByAuthorizeId(authorizeId);
        return Result.data(bo);
    }

}