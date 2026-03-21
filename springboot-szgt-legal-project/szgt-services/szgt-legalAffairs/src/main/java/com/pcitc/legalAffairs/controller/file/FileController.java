package com.pcitc.legalAffairs.controller.file;

import com.pcitc.common.entity.Result;
import com.pcitc.common.fastdfs.service.FastDFSService;
import com.pcitc.legalAffairs.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/***
 * @description 文件上传接口(统一存储接口)
 * @author leigang
 * @date 2019年10月22日 17:46:04
 *
 */
@RestController
@RequestMapping("/common/file")
public class FileController {

    private FastDFSService fastDFSService;

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setFastDFSService(FastDFSService fastDFSService) {
        this.fastDFSService = fastDFSService;
    }


    @PostMapping(value = "/uploadImg")
    public Result picUpload(
            @RequestParam(value = "file", required = false) MultipartFile uploadFile) throws Exception {
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String url = fastDFSService.uploadFile(uploadFile.getBytes(), extName);
        //返回图片信息
        Map<String, String> map = new HashMap<>();
        map.put("extName", extName);
        map.put("url", url);
        map.put("fileName", originalFilename);
        return Result.data(map);
    }


    @PostMapping(value = "/deleteImage")
    public Result deleteImage(@RequestParam String serverPath) {
        //1 删除成功 0 图片已经删除 2图片 删除异常
        Integer id = fileService.deleteImage(serverPath);
        if (id == 1) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }


}
