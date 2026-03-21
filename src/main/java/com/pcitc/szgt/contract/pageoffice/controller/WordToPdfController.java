package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.pageoffice.model.OfficeAttachmentModel;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class WordToPdfController {

    @Value("${server.port}")
    private String currentPort;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @GetMapping("wordtopdf")
    public ModelAndView wordToPdf(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        // TODO 附件信息
        OfficeAttachmentModel attachmentVo = null;
        String fileurl = attachmentVo.getDocUrl() + attachmentVo.getAttachmentPath();
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //设置服务器页面
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz");
        poCtrl.setTitlebar(false);
        poCtrl.setMenubar(false);
        //添加自定义按钮
        poCtrl.addCustomToolButton("另存为PDF文件", "SaveAsPDF()", 1);
        poCtrl.setJsFunction_AfterDocumentSaved("CloseWin");
//        poCtrl.addCustomToolButton("打印设置","PrintSet",0);
//        poCtrl.addCustomToolButton("打印","PrintFile",6);
//        poCtrl.addCustomToolButton("全屏/还原", "IsFullScreen", 4);
//        poCtrl.addCustomToolButton("-", "", 0);
//        poCtrl.addCustomToolButton("关闭","Close",21);
        //设置保存页面
        poCtrl.setSaveFilePage(pageOfficeConfig.getContext() + "/save" + attachmentVo.toParamString());
        //打开Word文档
        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl,
                OpenModeType.docNormalEdit,userInfo.getSysUser().getfCname());
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        ModelAndView word = new ModelAndView("ToPdf");

        return word;
    }

    private OfficeAttachmentModel getAtta(HttpSession session){
        OfficeAttachmentModel attachmentVo = new OfficeAttachmentModel();
        attachmentVo.setAttachmentName((String)session.getAttribute("attachmentName"));
        attachmentVo.setAttachmentPath((String)session.getAttribute("attachmentPath"));
        attachmentVo.setPropertyID((String)session.getAttribute("propertyID"));
        attachmentVo.setExtension((String)session.getAttribute("extension"));
        attachmentVo.setRemark((String)session.getAttribute("remark"));
        attachmentVo.setDocUrl((String)session.getAttribute("docUrl"));
        attachmentVo.setAccess_token((String)session.getAttribute("access_token"));

        return attachmentVo;
    }
}
