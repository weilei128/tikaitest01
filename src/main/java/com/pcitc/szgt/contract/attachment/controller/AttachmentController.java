package com.pcitc.szgt.contract.attachment.controller;

import com.pcitc.szgt.contract.attachment.model.vo.AttachmentQueryVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentUploadVo;
import com.pcitc.szgt.contract.attachment.service.AttachmentService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.attachment.AttachmentConfig;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("attachment")
public class AttachmentController {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentConfig attachmentConfig;

    @PostMapping("upload")
    public DataResult<?> uploadAttachment(AttachmentUploadVo attachmentUploadVo){
        return attachmentService.uploadFile(attachmentUploadVo);
    }

    @PostMapping("uploadreturndetail")
    public DataResult<?> uploadAttachmentReturnDetail(AttachmentUploadVo attachmentUploadVo){
        return attachmentService.uploadFileReturnDetail(attachmentUploadVo);
    }

    @GetMapping("query")
    public DataResult<List<AttachmentResultVo>> squeryAttachment(AttachmentQueryVo queryVo){
        return attachmentService.queryOneAttachment(queryVo);
    }

    @GetMapping("queryMulti")
    public DataResult<List<AttachmentResultVo>> queryMultiAttachment(AttachmentQueryVo queryVo){
        return attachmentService.queryMultiAttachment(queryVo);
    }

    @GetMapping("queryAll")
    public DataResult<List<AttachmentResultVo>> queryAllAttachment(AttachmentQueryVo queryVo){
        return attachmentService.queryAllAttachment(queryVo);
    }

    @PostMapping("delete")
    public DataResult<?> deleteAttachment(@RequestParam String attachmentId){
        return attachmentService.deleteAttachment(attachmentId);
    }

    @PostMapping("order")
    public DataResult<?> orderAttachment(@RequestBody List<String> attids){
        return attachmentService.orderAttachment(attids);
    }

    @GetMapping("download")
    public void downloadAttachment(HttpServletResponse response, String fileurl, String filename){

        int indexOf = fileurl.indexOf("/group");
        //String substring = fileurl.substring(indexOf);
        String substring = indexOf < 0 ? "/group" : fileurl.substring(indexOf);
        String resurl = "http://" + attachmentConfig.toIpString() + substring;
        byte[] bytes = restTemplateUtil.BinaryRequest(resurl);
        try {
            filename = java.net.URLEncoder.encode(filename.trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String suffixName = "";
        if(fileurl.lastIndexOf(".") != -1){
            suffixName = "." + fileurl.substring(fileurl.lastIndexOf(".") + 1);
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + filename + suffixName);
        response.setHeader("content-length", bytes.length + "");
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("downloadforpgo")
    public void downloadAttachment2(HttpServletResponse response, String fileurl){
        byte[] decode = Base64.getUrlDecoder().decode(fileurl);
        fileurl = new String(decode);

        int indexOf = fileurl.indexOf("/group");
        String substring = indexOf < 0 ? "/group" : fileurl.substring(indexOf);
        String resurl = "http://" + attachmentConfig.toIpString() + substring;
        log.info("----resurl =={}",resurl);
        byte[] bytes = restTemplateUtil.BinaryRequest(resurl);

        String suffixName = "";
        if(fileurl.lastIndexOf(".") != -1){
            suffixName = "." + fileurl.substring(fileurl.lastIndexOf(".") + 1);
        }

        response.setHeader("Content-Disposition", "attachment; filename=pgofile" + suffixName);
        response.setHeader("content-length", bytes.length + "");
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
