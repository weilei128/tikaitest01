package com.pcitc.szgt.contract.pageoffice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.attachment.service.AttachmentService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.pageoffice.model.OfficeAttachmentModel;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RedisUtils;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件修改、保存
 */
@RestController
public class EditAndSaveController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private RedisUtils redisUtils;
//
//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 打开文件
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/filenormal", method= RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String, Object> map){

        UserInfo userInfo = currentUserUtil.currentUserInfo();
        String contextPath = request.getContextPath();
        String fileurl = request.getParameter("fileUrl");
//        String request_code = request.getParameter("request_code");
//        String paramStr = redisUtils.strget("contract_pageoffice" + request_code);
//
//        Map<String, String> params = null;
//        try {
//            params = objectMapper.readValue(paramStr, new TypeReference<HashMap<String, String>>() {
//            });
//        } catch (IOException e) {
//            throw new BaseException("参数解析失败", 500);
//        }

        // TODO 附件信息
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        OpenModeType mode = checkOpenModeType(fileurl);

//        String fileurl = attachmentVo.getDocUrl() + attachmentVo.getAttachmentPath();
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setTitlebar(false);
        poCtrl.setMenubar(false);
        //添加自定义按钮
        poCtrl.addCustomToolButton("保存","Save",1);
        poCtrl.setJsFunction_AfterDocumentSaved("CloseWin");
//        poCtrl.addCustomToolButton("打印设置","PrintSet",0);
//        poCtrl.addCustomToolButton("打印","PrintFile",6);
//        poCtrl.addCustomToolButton("全屏/还原", "IsFullScreen", 4);
//        poCtrl.addCustomToolButton("-", "", 0);
//        poCtrl.addCustomToolButton("关闭","Close",21);
        //设置保存页面
        poCtrl.setSaveFilePage(pageOfficeConfig.getContext() + "/save" + attachmentVo.toParamString());

        byte[] encode = Base64.getUrlEncoder().encode(fileurl.getBytes());
        String encodeFileUrl = new String(encode);
        //打开Word文档
        poCtrl.webOpen( pageOfficeConfig.getOpencontext() + "/attachment/downloadforpgo?fileurl=" + encodeFileUrl + "&access_token=" + RestTemplateUtil.getToken(),
                mode,userInfo.getSysUser().getfCname());
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return new ModelAndView("NormalOpen");
    }

    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, OfficeAttachmentModel attachmentVo, HttpServletResponse response){

        FileSaver fs = new FileSaver(request, response);
        String fileExtName = attachmentVo.getExtension();
        String fileName = attachmentVo.getAttachmentName();
        int fileSize = fs.getFileSize();

        String contextPath = request.getContextPath();
        String url = "http://localhost:" + currentPort + contextPath + "/attachment/uploadreturndetail";

        InputStreamResource inputStreamResource = new InputStreamResource(fs.getFileStream()){
            @Override
            public String getFilename() {
                return fileName + "." + fileExtName;
            }

            @Override
            public long contentLength() throws IOException {
                return fileSize == 0 ? 1 : fileSize;
            }
        };

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("file", inputStreamResource);
        paramMap.add("PropertyID", attachmentVo.getPropertyID());
        paramMap.add("AttachmentTypeName", attachmentVo.getAttachmentTypeName());
        paramMap.add("AttachmentType", attachmentVo.getAttachmentType());
        paramMap.add("AttachmentName", attachmentVo.getAttachmentName());
        paramMap.add("TypeCode", attachmentVo.getTypeCode());
        paramMap.add("PropertyModel", attachmentVo.getPropertyModel());
        paramMap.add("Section", attachmentVo.getSection());
        paramMap.add("Remark", attachmentVo.getRemark());
        paramMap.add("access_token", RestTemplateUtil.getToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);
        HttpEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String uploadResult = result.getBody();

        try {
            DataResult<AttachmentResultVo> dataResult = objectMapper.
                    readValue(uploadResult, new TypeReference<DataResult<AttachmentResultVo>>() {
                    });
            AttachmentResultVo data = dataResult.getData();
            fs.setCustomSaveResult(data.getDocUrl() + data.getAttachmentPath());

        } catch (IOException e) {
            StringWriter trace = new StringWriter();
            e.printStackTrace(new PrintWriter(trace));
        }

        fs.close();
    }

    private OpenModeType checkOpenModeType(String var1){
        if (!var1.toLowerCase().endsWith("doc") && !var1.toLowerCase().endsWith("docx") && !var1.toLowerCase().endsWith("docm") && !var1.toLowerCase().endsWith("rtf") && !var1.toLowerCase().endsWith("wps")) {
            if (!var1.toLowerCase().endsWith(".xls") && !var1.toLowerCase().endsWith(".xlsx") && !var1.toLowerCase().endsWith(".xlsm") && !var1.toLowerCase().endsWith(".et")) {
                if (!var1.toLowerCase().endsWith(".ppt") && !var1.toLowerCase().endsWith(".pptx")) {
                    if (var1.toLowerCase().endsWith(".vsd")) {
                        return OpenModeType.vsdNormalEdit;
                    }
                }else{
                    return OpenModeType.pptNormalEdit;
                }
            }else{
                return OpenModeType.xlsNormalEdit;
            }
        }else{
            return OpenModeType.docRevisionOnly;
        }

        return OpenModeType.docRevisionOnly;
    }

    private OfficeAttachmentModel getAtta(Map<String, String> params){
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAttachmentName(params.get("attachmentName"));
        attachmentVo.setAttachmentPath(params.get("attachmentPath"));
        attachmentVo.setPropertyID(params.get("propertyID"));
        attachmentVo.setExtension(params.get("extension"));
        attachmentVo.setRemark(params.get("remark"));
        attachmentVo.setDocUrl(params.get("docUrl"));
        attachmentVo.setAccess_token(RestTemplateUtil.getToken());

        return attachmentVo;
    }

}
