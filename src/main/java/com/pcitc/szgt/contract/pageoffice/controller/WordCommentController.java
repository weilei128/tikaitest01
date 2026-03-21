package com.pcitc.szgt.contract.pageoffice.controller;

import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.config.pageoffice.PageOfficeConfig;
import com.pcitc.szgt.contract.pageoffice.model.OfficeAttachmentModel;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 添加批注&显示批注列表
 */
@Api(value = "WordCommentController" , tags = "添加批注&显示批注列表")
@RestController
public class WordCommentController {

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private PageOfficeConfig pageOfficeConfig;

    @Value("${server.port}")
    private String currentPort;

    @GetMapping("wordcomment")
    public ModelAndView wordComment(HttpServletRequest request, Map<String, Object> map){

        String contextPath = request.getContextPath();
        UserInfo userInfo = currentUserUtil.currentUserInfo();

        // TODO 附件信息
        OfficeAttachmentModel attachmentVo = null;

        String fileurl = attachmentVo.getDocUrl() + attachmentVo.getAttachmentPath();
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(pageOfficeConfig.getContext() + "/poserver.zz"); //此行必须
        poCtrl.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
        poCtrl.setJsFunction_AfterDocumentSaved("CloseWin");
        poCtrl.setOfficeToolbars(false);//隐藏Office工具
        poCtrl.setTitlebar(false);
        poCtrl.setMenubar(false);
        poCtrl.addCustomToolButton("保存", "Save()", 1);
        poCtrl.addCustomToolButton("新建批注", "InsertComment()", 3);
        //文件保存方法
        poCtrl.setSaveFilePage(pageOfficeConfig.getContext() + "/save" + attachmentVo.toParamString());
        //打开文件
        poCtrl.webOpen(pageOfficeConfig.getOpencontext() + "/getFile?fileurl=" + fileurl,
                OpenModeType.docCommentOnly, userInfo.getSysUser().getfCname());

        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        ModelAndView word = new ModelAndView("WordComment");

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
