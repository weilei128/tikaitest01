package com.pcitc.system.controller;

import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.exception.BaseException;
import com.pcitc.common.fastdfs.service.FastDFSService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("fastDFS")
public class FastDFSController {
    @Autowired
    private FastDFSService fastDFSService;
    @PostMapping("fileUpload")
    @ApiOperation("单个文件上传")
    public Result fileUpload(@RequestParam("file") MultipartFile file){
        Result result = new Result();
        if (file.isEmpty()){
            throw new BaseException("上传文件为空，请选择一个文件进行上传！",500);
        }
        String resultPath = null;
        String author = "";
        try {
            resultPath = fastDFSService.saveFile(file,author);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(resultPath)){
            result.setCode(500);
            result.setData(resultPath);
            result.setMsg("上传文件失败，请重新上传！");
            result.setSuccess(false);
        }else {
            result.setCode(200);
            result.setData(resultPath);
            result.setMsg("上传文件成功！");
            result.setSuccess(true);
        }
        return result;
    }
    @PostMapping("deleteFile")
    @ApiOperation("单个文件删除")
    public Result deleteFile(String fileId) throws Exception {
        fastDFSService.deleteFile(fileId);
        return Result.success(ResultCode.SUCCESS);
    }
    @PostMapping("downloadFile")
    @ApiOperation("单个文件下载")
    public Result downloadFile(String fileId) throws Exception {
        fastDFSService.downloadFile(fileId);
        return Result.success(ResultCode.SUCCESS);
    }
}
